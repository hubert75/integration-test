package com.binarapps.integrationtest;

import com.binarapps.integrationtest.client.IntegrationClient;
import com.binarapps.integrationtest.client.RetrofitApi;
import com.binarapps.integrationtest.http.dto.PagedList;
import com.binarapps.integrationtest.http.dto.Transactions;
import com.binarapps.integrationtest.http.utils.AutoValueGsonFactory;
import com.binarapps.integrationtest.http.utils.PageIndexTransformer;
import com.binarapps.integrationtest.http.utils.TransactionsToPagedListMapper;
import com.google.gson.GsonBuilder;
import io.reactivex.Single;
import io.reactivex.observers.TestObserver;
import okhttp3.OkHttpClient;
import org.junit.Before;
import org.junit.Test;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.Assert.assertEquals;


public class RetrofitTransactionTests {

    private RetrofitApi api;

    private TransactionsToPagedListMapper mapper;

    private PageIndexTransformer transformer;

    @Before
    public void setUp() {
        this.api = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                        .registerTypeAdapterFactory(AutoValueGsonFactory.create())
                        .create()))
                .baseUrl("https://virtserver.swaggerhub.com/empsp0/integration-test/1.0.0/")
                .client(new OkHttpClient.Builder().build())
                .build().create(RetrofitApi.class);

        this.mapper = new TransactionsToPagedListMapper();
        this.transformer = new PageIndexTransformer();
    }

    @Test
    public void requestTest() {
        Single<Transactions> transactions =
                api.getTransactions("John Doe", "babf75bd-0481-4699-800f-63f69bdb23f6");
        testSingle(transactions.map(t -> PagedList.create(t.pageIndex(), t.get())), 10);
    }

    @Test
    public void integrationClientTest() {
        IntegrationClient integrationClient = new IntegrationClient(api, mapper);
        Single<PagedList> transactions =
                integrationClient.getTransactions("John Doe", 5,
                        transformer.prepareRequestParams(
                                java.util.Optional.of("babf75bd-0481-4699-800f-63f69bdb23f6")));
        testSingle(transactions, 5);

    }

    private void testSingle(Single<PagedList> transactions, int listSize) {
        TestObserver<PagedList> testObserver = new TestObserver<>();
        testObserver.assertNotSubscribed();
        transactions.subscribe(testObserver);
        testObserver.assertSubscribed();
        testObserver.awaitTerminalEvent();
        testObserver
                .assertComplete()
                .assertNoErrors()
                .assertValueCount(1);
        PagedList transactionPagedList = testObserver.values().get(0);
        assertEquals(transactionPagedList.get().size(), listSize);
    }


}

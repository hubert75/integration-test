package com.binarapps.integrationtest.http.utils;

import com.binarapps.integrationtest.http.dto.PagedList;
import com.binarapps.integrationtest.http.dto.TransactionListParams;
import com.binarapps.integrationtest.http.dto.Transactions;
import io.reactivex.functions.Function;

public final class TransactionsToPagedListMapper {

    public Function<Transactions, PagedList> mapTransactionsToPagedList(Integer offsetIndex, Integer pageSize) {
        return t -> {
            TransactionListParams responseParams =
                    prepareTransactionsResponseParams(t.pageIndex(), offsetIndex, pageSize, t.get().size());
            return PagedList.create(responseParams.pageIndex(),
                    t.get().subList(responseParams.firstIndex(), responseParams.lastIndex()));
        };
    }

    private TransactionListParams prepareTransactionsResponseParams(String pageIndex, Integer offset,
                                                                    Integer pageSize, Integer allTransactionsCount) {
        final int lastPrintedIndex = offset + pageSize;
        return TransactionListParams.builder()
                .setPageIndex(getPageIndex(pageIndex, lastPrintedIndex, allTransactionsCount))
                .setFirstIndex(Math.min(offset, allTransactionsCount))
                .setLastIndex(Math.min(lastPrintedIndex, allTransactionsCount))
                .build();
    }

    private String getPageIndex(String pageIndex, Integer lastPrinted, Integer allTransactionsCount) {
        if (allTransactionsCount > lastPrinted) {
            return pageIndex + "__" + lastPrinted;
        }
        return null;
    }

}

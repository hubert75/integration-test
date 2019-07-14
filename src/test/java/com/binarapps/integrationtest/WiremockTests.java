package com.binarapps.integrationtest;

import com.binarapps.integrationtest.utils.JsonReader;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.Assert.assertEquals;

public class WiremockTests {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8200);

    @Test
    public void test() throws IOException, URISyntaxException {
        String body = JsonReader.loadJsonFile();
        stubFor(get(urlEqualTo("/link?accountId=John&pageIndex=d290f1ee-6c54-4b01-90e6-d701748f0851"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));
        CloseableHttpClient httpClient = HttpClients.createDefault();

        String url = "http://localhost:8200/";
        URIBuilder builder = new URIBuilder(url + "link");
        builder.setParameter("accountId", "John").setParameter("pageIndex", "d290f1ee-6c54-4b01-90e6-d701748f0851");
        HttpGet request = new HttpGet(builder.build());

        HttpResponse httpResponse = httpClient.execute(request);
        String stringResponse = convertHttpResponseToString(httpResponse);
        verify(getRequestedFor(urlEqualTo("/link?accountId=John&pageIndex=d290f1ee-6c54-4b01-90e6-d701748f0851")));
        assertEquals(200, httpResponse.getStatusLine().getStatusCode());
        assertEquals("application/json", httpResponse.getFirstHeader("Content-Type").getValue());
        assertEquals(body, stringResponse);
    }

    private String convertHttpResponseToString(HttpResponse httpResponse) throws IOException {
        InputStream inputStream = httpResponse.getEntity().getContent();
        return convertInputStreamToString(inputStream);
    }

    private String convertInputStreamToString(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8);
        String string = scanner.useDelimiter("\\Z").next();
        scanner.close();
        return string;
    }

}

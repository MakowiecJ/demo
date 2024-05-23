package com.example.demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;

public class MultiThreadRequestSender {

    private static final String[] ENDPOINTS = {
            "http://localhost:8080/converters/fo",
            "http://localhost:8080/converters/libre",
            "http://localhost:8080/converters/xwpf",
            "http://localhost:8080/converters/spiredoc",
//            "http://localhost:8080/converters/convertapi",
            "http://localhost:8080/converters/aspose",
            "http://localhost:8080/converters/pdfoffice",
            "http://localhost:8080/converters/apryse"
    };

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int numberOfThreads = 40;  // Number of concurrent threads
        int numberOfRequests = 40; // Total number of requests per endpoint

        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        List<Future<String>> futures = new ArrayList<>();

        for (String endpoint : ENDPOINTS) {
            for (int i = 0; i < numberOfRequests; i++) {
                futures.add(executorService.submit(() -> sendPostRequest(endpoint)));
            }
        }

        for (Future<String> future : futures) {
            try {
                System.out.println(future.get());
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);
    }

    private static String sendPostRequest(String url) {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(new StringEntity("")); // Adjust the entity if needed

            try (CloseableHttpResponse response = client.execute(httpPost)) {
                int statusCode = response.getCode();
                String responseBody = EntityUtils.toString(response.getEntity());

                if (statusCode >= 200 && statusCode < 300) {
                    return "Success: " + url + " - Response: " + responseBody;
                } else {
                    return "Failed: " + url + " with status code " + statusCode + " - Response: " + responseBody;
                }
            }
        } catch (IOException | ParseException e) {
            return "Error: " + e.getMessage();
        }
    }
}


package org.vaadin.example;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.vaadin.example.MainView.*;


public class GET {
    static String getData(String url1, String url2){
        try {
            String resource;

            if (url2 == null){
                resource = String.format(api + url1);
            }
            else{
                resource = String.format(api + url1 + "/" + url2);
            }

            request= HttpRequest
                    .newBuilder(new URI(resource))
                    .header("Content-Type","application/json")
                    .GET()
                    .build();
            response=cliente.send(request,HttpResponse.BodyHandlers.ofString());

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return (String) response.body();
    }
}

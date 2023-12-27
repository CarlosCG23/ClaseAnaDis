package org.vaadin.example;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.data.renderer.ComponentRenderer;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.vaadin.example.MainView.*;

public class DELETE {
    static void deleteData(String url1, String url2, long ip_from){
        try {
            String resource;
            if(ip_from != 0){
                resource = String.format(api + url1 + "/" + url2 + "/" + ip_from);
                request= HttpRequest
                        .newBuilder(new URI(resource))
                        .header("Content-Type","application/json")
                        .DELETE()
                        .build();
                response=cliente.send(request,HttpResponse.BodyHandlers.ofString());
            }
            else {
                resource = String.format(api + url1 + "/" + url2);
                request= HttpRequest
                        .newBuilder(new URI(resource))
                        .header("Content-Type","application/json")
                        .DELETE()
                        .build();
                response=cliente.send(request,HttpResponse.BodyHandlers.ofString());
            }

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static void removeInvitation(DatosJSONComplejoHIJO Aux) {
        //System.out.println(Aux.ip_from);
        DELETE.deleteData("DatosJSONcomplejoPADRE", "DatosJSONcomplejoHIJO", Aux.ip_from);

        GridConfig.configureGrid(gridDatosJSONComplejoHIJO);
    }
}

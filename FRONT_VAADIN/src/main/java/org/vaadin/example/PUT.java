package org.vaadin.example;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.vaadin.example.MainView.*;

public class PUT {

    static void putData(String url1, String url2, long ip_from, DatosJSONComplejoHIJO datosJSONComplejoHIJO){
        try {
            String resource = String.format(api + url1 + "/" + url2 + "/" + ip_from);
            if(datosJSONComplejoHIJO != null){
                request= HttpRequest
                        .newBuilder(new URI(resource))
                        .header("Content-Type","application/json")
                        .PUT(HttpRequest.BodyPublishers.ofString(datosJSONComplejoHIJO.toString()))
                        .build();
                response=cliente.send(request,HttpResponse.BodyHandlers.ofString());
            }
            else {
                request= HttpRequest
                        .newBuilder(new URI(resource))
                        .header("Content-Type","application/json")
                        .PUT(HttpRequest.BodyPublishers.ofString(""))
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

    static VerticalLayout createDialogLayoutModificarDato(DatosJSONComplejoHIJO AuxDatosJSONComplejoHijo) {
        H2 headline = new H2("Añadir Dato");
        headline.getStyle().set("margin", "var(--lumo-space-m) 0 0 0")
                .set("font-size", "1.5em").set("font-weight", "bold");

        Button buttonEditar = new Button("Editar");
        Button buttonCancelar = new Button("Cancelar");

        FormLayout DatoFormLayout = new FormLayout();

        TextField ipFromFile = new TextField();
        ipFromFile.setLabel("IP From");
        ipFromFile.setReadOnly(true);
        ipFromFile.setValue(String.valueOf(AuxDatosJSONComplejoHijo.ip_from));

        TextField ipToFile = new TextField();
        ipToFile.setLabel("IP To");
        ipToFile.setReadOnly(true);
        ipToFile.setValue(String.valueOf(AuxDatosJSONComplejoHijo.ip_to));

        TextField countryCodeFile = new TextField();
        countryCodeFile.setLabel("Country Code");
        countryCodeFile.setPlaceholder(AuxDatosJSONComplejoHijo.country_code);

        TextField countryNameFile = new TextField();
        countryNameFile.setLabel("Country Name");
        countryNameFile.setPlaceholder(AuxDatosJSONComplejoHijo.country_name);

        TextField latitudeFile = new TextField();
        latitudeFile.setLabel("Latitude");
        latitudeFile.setPlaceholder(String.valueOf(AuxDatosJSONComplejoHijo.latitude));

        TextField longitudeFile = new TextField();
        longitudeFile.setLabel("Longitude");
        longitudeFile.setPlaceholder(String.valueOf(AuxDatosJSONComplejoHijo.longitude));


        DatoFormLayout.add(ipFromFile, ipToFile, countryCodeFile, countryNameFile, latitudeFile, longitudeFile);

        DatoFormLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("1px", 1),
                new FormLayout.ResponsiveStep("600px", 2),
                new FormLayout.ResponsiveStep("700px", 3));


        buttonCancelar.addClickListener(e -> {
            dialog.close();
            dialog.removeAll();
        });
        buttonEditar.addClickListener(e -> {

            DatosJSONComplejoHIJO Aux = new DatosJSONComplejoHIJO();

            Aux.setIp_from(Long.parseLong(ipFromFile.getValue()));
            Aux.setIp_to(Long.parseLong(ipToFile.getValue()));

            if(countryCodeFile.isEmpty())
            {
                Aux.setCountry_code(AuxDatosJSONComplejoHijo.country_code);
            }
            else
            {
                Aux.setCountry_code(countryCodeFile.getValue());
            }

            if(countryNameFile.isEmpty())
            {
                Aux.setCountry_name(AuxDatosJSONComplejoHijo.country_name);
            }
            else
            {
                Aux.setCountry_name(countryNameFile.getValue());
            }

            if(latitudeFile.isEmpty())
            {
                Aux.setLatitude(AuxDatosJSONComplejoHijo.latitude);
            }
            else
            {
                Aux.setLatitude(Double.parseDouble(latitudeFile.getValue()));
            }

            if(longitudeFile.isEmpty())
            {
                Aux.setLongitude(AuxDatosJSONComplejoHijo.longitude);
            }
            else
            {
                Aux.setLongitude(Float.parseFloat(longitudeFile.getValue()));
            }

            PUT.putData("DatosJSONcomplejoPADRE", "DatosJSONcomplejoHIJO", AuxDatosJSONComplejoHijo.ip_from, Aux);

            GridConfig.configureGrid(gridDatosJSONComplejoHIJO);

            dialog.close();
            dialog.removeAll();
        });

        VerticalLayout dialogLayout = new VerticalLayout(headline, DatoFormLayout, buttonEditar, buttonCancelar);
        dialogLayout.setPadding(false);
        dialogLayout.setAlignItems(FlexComponent.Alignment.STRETCH);
        dialogLayout.getStyle().set("min-width", "900px")
                .set("max-width", "100%").set("height", "80%");
        return dialogLayout;
    }
}

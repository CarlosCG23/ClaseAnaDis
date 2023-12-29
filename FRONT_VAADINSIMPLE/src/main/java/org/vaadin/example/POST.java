package org.vaadin.example;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.ParseException;

import static org.vaadin.example.MainView.*;

public class POST {

    static void postData(String url1, String url2, DatosJSONSimple AuxDatosJSONComplejoHijo) {
        try {

            String resource;

            if (url2 == null){

                resource = String.format(api + url1);

                request = HttpRequest
                        .newBuilder(new URI(resource))
                        .header("Content-Type", "application/json")
                        .POST(HttpRequest.BodyPublishers.ofString(""))
                        .build();

                response = cliente.send(request, HttpResponse.BodyHandlers.ofString());
            }
            else{

                resource = String.format(api + url1 + "/" + url2);

                request = HttpRequest
                        .newBuilder(new URI(resource))
                        .header("Content-Type", "application/json")
                        .POST(HttpRequest.BodyPublishers.ofString(AuxDatosJSONComplejoHijo.toString()))
                        .build();

                response = cliente.send(request, HttpResponse.BodyHandlers.ofString());
            }

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
/*
    static VerticalLayout createDialogLayoutAnadirDato() throws ParseException {

        H2 headline = new H2("Añadir Dato");
        headline.getStyle().set("margin", "var(--lumo-space-m) 0 0 0")
                .set("font-size", "1.5em").set("font-weight", "bold");

        Button buttonGuardar = new Button("Guardar");
        Button buttonCancelar = new Button("Cancelar");

        FormLayout DatoFormLayout = new FormLayout();

        TextField ipFromFile = new TextField();
        ipFromFile.setLabel("IP From");
        ipFromFile.setRequired(true);
        ipFromFile.setRequiredIndicatorVisible(true);
        ipFromFile.setErrorMessage("El Campo es requerido");

        TextField ipToFile = new TextField();
        ipToFile.setLabel("IP To");
        ipToFile.setRequired(true);
        ipToFile.setRequiredIndicatorVisible(true);
        ipToFile.setErrorMessage("El Campo es requerido");

        TextField countryCodeFile = new TextField();
        countryCodeFile.setLabel("Country Code");
        countryCodeFile.setRequired(true);
        countryCodeFile.setRequiredIndicatorVisible(true);
        countryCodeFile.setErrorMessage("El Campo es requerido");

        TextField countryNameFile = new TextField();
        countryNameFile.setLabel("Country Name");
        countryNameFile.setRequired(true);
        countryNameFile.setRequiredIndicatorVisible(true);
        countryNameFile.setErrorMessage("El Campo es requerido");

        TextField latitudeFile = new TextField();
        latitudeFile.setLabel("Latitude");
        latitudeFile.setRequired(true);
        latitudeFile.setRequiredIndicatorVisible(true);
        latitudeFile.setErrorMessage("El Campo es requerido");

        TextField longitudeFile = new TextField();
        longitudeFile.setLabel("Longitude");
        longitudeFile.setRequired(true);
        longitudeFile.setRequiredIndicatorVisible(true);
        longitudeFile.setErrorMessage("El Campo es requerido");

        DatoFormLayout.add(ipFromFile, ipToFile, countryCodeFile, countryNameFile, latitudeFile, longitudeFile);

        DatoFormLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("1px", 1),
                new FormLayout.ResponsiveStep("600px", 2),
                new FormLayout.ResponsiveStep("700px", 3));


        buttonCancelar.addClickListener(e -> {
            dialog.close();
            dialog.removeAll();
        });

        buttonGuardar.addClickListener(e -> {

            DatosJSONComplejoHIJO Aux = new DatosJSONComplejoHIJO(Long.parseLong(ipFromFile.getValue()),
                    Long.parseLong(ipToFile.getValue()),
                    countryCodeFile.getValue(), countryNameFile.getValue(),
                    Double.parseDouble(latitudeFile.getValue()), Float.parseFloat(longitudeFile.getValue()));

            POST.postData("DatosJSONcomplejoPADRE", "DatosJSONcomplejoHIJO", Aux);

            GridConfig.configureGrid(gridDatosJSONComplejoHIJO);

            dialog.close();
            dialog.removeAll();
        });

        VerticalLayout dialogLayout = new VerticalLayout(headline, DatoFormLayout, buttonGuardar, buttonCancelar);
        dialogLayout.setPadding(false);
        dialogLayout.setAlignItems(Alignment.STRETCH);
        dialogLayout.getStyle().set("min-width", "900px")
                .set("max-width", "100%").set("height", "80%");
        return dialogLayout;
    }
    */

}

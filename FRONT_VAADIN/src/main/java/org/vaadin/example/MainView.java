package org.vaadin.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.LocalDateRenderer;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * A sample Vaadin view class.
 * <p>
 * To implement a Vaadin view just extend any Vaadin component and
 * use @Route annotation to announce it in a URL as a Spring managed
 * bean.
 * Use the @PWA annotation make the application installable on phones,
 * tablets and some desktop browsers.
 * <p>
 * A new instance of this class is created for every new user and every
 * browser tab/window.
 */
@Route
@PWA(name = "Vaadin Application",
        shortName = "Vaadin App",
        description = "This is an example Vaadin application.",
        enableInstallPrompt = false)
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class MainView extends VerticalLayout {
    //Button buttonAnadirData = new Button("Añadir Data");

    HttpRequest request;
    HttpClient cliente = HttpClient.newBuilder().build();
    HttpResponse response;

    final Gson gson = new Gson();

    private static final String api = "http://localhost:8085/";

    DatosJSONComplejoHIJO AuxDatosJSONComplejoHijo = new DatosJSONComplejoHIJO();

    ArrayList<DatosJSONComplejoHIJO> DatosJSONComplejoHIJOList = new ArrayList<>();

    Grid<DatosJSONComplejoHIJO> gridDatosJSONComplejoHIJO = new Grid<>(DatosJSONComplejoHIJO.class);

    Button buttonAnadirDato = new Button("Añadir Dato");
    Dialog dialog = new Dialog();

    public DatosJSONcomplejoPADRE DatosJSONComplejoPadreApi(){

        DatosJSONcomplejoPADRE DatosJSONComplejoPadre;

        String StringDatosJSONComplejoPadre = getData("DatosJSONcomplejoPADRE", null);

        DatosJSONComplejoPadre = gson.fromJson(StringDatosJSONComplejoPadre, DatosJSONcomplejoPADRE.class);

        return DatosJSONComplejoPadre;
    }

    private void postData(String url1, String url2, DatosJSONComplejoHIJO AuxDatosJSONComplejoHijo) {
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

    private String getData(String url1, String url2){
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

    private void deleteData(String url1, String url2, long ip_from){
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

    private void putData(String url1, String url2, long ip_from, DatosJSONComplejoHIJO datosJSONComplejoHIJO){
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

    public MainView() {
        // ----------------------------------------------
        // llamada a Post Inicial de todo a la api
        postData("DatosJSONcomplejoPADRE", null, null);
        // ----------------------------------------------

        // ----------------------------------------------
        // Llamada Get del Arraylist
        Type listaDatosHijo = new TypeToken<ArrayList<DatosJSONComplejoHIJO>>() {}.getType();
        DatosJSONComplejoHIJOList = gson.fromJson(getData("DatosJSONcomplejoPADRE","DatosJSONcomplejoHIJO"), listaDatosHijo);
        // ----------------------------------------------

        // ----------------------------------------------
        // Creacion del grid
        gridDatosJSONComplejoHIJO.setItems(DatosJSONComplejoPadreApi().getDatos());
        gridDatosJSONComplejoHIJO.setColumns("ip_from", "ip_to", "country_code", "country_name", "latitude", "longitude");

        gridDatosJSONComplejoHIJO.getColumnByKey("ip_from")
                .setAutoWidth(true);
        gridDatosJSONComplejoHIJO.getColumnByKey("ip_to")
                .setAutoWidth(true);
        gridDatosJSONComplejoHIJO.getColumnByKey("country_code")
                .setHeader("Country Code").setAutoWidth(true);
        gridDatosJSONComplejoHIJO.getColumnByKey("country_name")
                .setHeader("Country Name").setAutoWidth(true);
        gridDatosJSONComplejoHIJO.getColumnByKey("latitude")
                .setHeader("Latitude").setAutoWidth(true);
        gridDatosJSONComplejoHIJO.getColumnByKey("longitude")
                .setHeader("Longitude").setAutoWidth(true);
        gridDatosJSONComplejoHIJO.addColumn(
                new ComponentRenderer<>(Button::new, (button, DatoHijo) -> {
                    button.addThemeVariants(ButtonVariant.LUMO_ICON,
                            ButtonVariant.LUMO_ERROR,
                            ButtonVariant.LUMO_TERTIARY);
                    button.addClickListener(e -> this.removeInvitation(DatoHijo));
                    button.setIcon(new Icon(VaadinIcon.TRASH));
                })).setHeader("Manage");
        // ----------------------------------------------

        gridDatosJSONComplejoHIJO.addItemClickListener(
                event -> {
                    AuxDatosJSONComplejoHijo = new DatosJSONComplejoHIJO(event.getItem().ip_from, event.getItem().ip_to,
                            event.getItem().country_code, event.getItem().country_name,
                            event.getItem().latitude, event.getItem().longitude);
                    dialog.getElement().setAttribute("aria-label", "Equipo seleccionado");
                    VerticalLayout dialogLayout = createDialogLayoutModificarDato(AuxDatosJSONComplejoHijo);
                    dialog.add(dialogLayout);
                    dialog.setCloseOnEsc(false);
                    dialog.setCloseOnOutsideClick(false);
                    dialog.setDraggable(true);
                    dialog.setResizable(true);
                    dialog.open();
                });

        // ----------------------------------------------
        // Creacion del boton añadir dato, con evento
        buttonAnadirDato.addClickListener(
                e -> {
                    VerticalLayout dialogLayout = null;
                    try {
                        dialogLayout = createDialogLayoutAnadirDato();
                    } catch (ParseException ex) {
                        throw new RuntimeException(ex);
                    }
                    dialog.add(dialogLayout);
                    dialog.setCloseOnEsc(false);
                    dialog.setCloseOnOutsideClick(false);
                    dialog.setDraggable(true);
                    dialog.setResizable(true);
                    dialog.open();
                    if (!dialog.isOpened()){
                        dialog.removeAll();
                        DatosJSONComplejoHIJOList.clear();
                    }
                }
        );
        // ----------------------------------------------

        // ----------------------------------------------
        // Creacion de los text fields del tab "Otros"
        TextField userName = new TextField();
        userName.setPrefixComponent(VaadinIcon.USER.create());
        userName.setLabel("User Name");
        userName.setReadOnly(true);
        userName.setValue(DatosJSONComplejoPadreApi().getOtros().getNombre());

        TextField userAge = new TextField();
        userAge.setLabel("User Age");
        userAge.setReadOnly(true);
        userAge.setValue(String.valueOf(DatosJSONComplejoPadreApi().getOtros().getEdad()));
        // ----------------------------------------------

        // ----------------------------------------------
        // Esto es para crear diferentes tabs y paginas
        Tab tabDatas = new Tab("Datas");
        Div pageDatas = new Div();
        pageDatas.setWidth(90f, Unit.PERCENTAGE);
        pageDatas.add(gridDatosJSONComplejoHIJO, buttonAnadirDato);
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        pageDatas.setVisible(true);

        Tab tabOtros = new Tab("Otros");
        Div pageOtros = new Div();
        pageOtros.setWidth(90f, Unit.PERCENTAGE);
        pageOtros.add(userName, userAge);
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        pageOtros.setVisible(false);
        // ----------------------------------------------

        // ----------------------------------------------
        // Esto es un hash map para asociar las tabs a las paginas
        Map<Tab, Component> tabsToPages = new HashMap<>();
        tabsToPages.put(tabDatas, pageDatas);
        tabsToPages.put(tabOtros, pageOtros);
        // ----------------------------------------------

        // ----------------------------------------------
        // Gestionar el cambio entre pestañas
        Tabs tabs = new Tabs(tabDatas, tabOtros);

        tabs.addThemeVariants(TabsVariant.LUMO_EQUAL_WIDTH_TABS);
        tabs.addSelectedChangeListener(event ->{
            tabsToPages.values().forEach(page -> page.setVisible(false));
            Component selectedPage = tabsToPages.get(tabs.getSelectedTab());
            selectedPage.setVisible(true);
        });
        // Estetico
        tabs.setSizeFull();
        // ----------------------------------------------

        // Añadirlo al main view
        add(tabs, pageDatas, pageOtros);
    }

    private VerticalLayout createDialogLayoutAnadirDato() throws ParseException {

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

            postData("DatosJSONcomplejoPADRE", "DatosJSONcomplejoHIJO", Aux);

            gridDatosJSONComplejoHIJO.setItems(DatosJSONComplejoPadreApi().getDatos());
            gridDatosJSONComplejoHIJO.setColumns("ip_from", "ip_to", "country_code", "country_name", "latitude", "longitude");

            gridDatosJSONComplejoHIJO.getColumnByKey("ip_from")
                    .setAutoWidth(true);
            gridDatosJSONComplejoHIJO.getColumnByKey("ip_to")
                    .setAutoWidth(true);
            gridDatosJSONComplejoHIJO.getColumnByKey("country_code")
                    .setHeader("Country Code").setAutoWidth(true);
            gridDatosJSONComplejoHIJO.getColumnByKey("country_name")
                    .setHeader("Country Name").setAutoWidth(true);
            gridDatosJSONComplejoHIJO.getColumnByKey("latitude")
                    .setHeader("Latitude").setAutoWidth(true);
            gridDatosJSONComplejoHIJO.getColumnByKey("longitude")
                    .setHeader("Longitude").setAutoWidth(true);
            gridDatosJSONComplejoHIJO.addColumn(
                    new ComponentRenderer<>(Button::new, (button, DatoHijo) -> {
                        button.addThemeVariants(ButtonVariant.LUMO_ICON,
                                ButtonVariant.LUMO_ERROR,
                                ButtonVariant.LUMO_TERTIARY);
                        button.addClickListener(event -> this.removeInvitation(DatoHijo));
                        button.setIcon(new Icon(VaadinIcon.TRASH));
                    })).setHeader("Manage");

            dialog.close();
            dialog.removeAll();
        });

        VerticalLayout dialogLayout = new VerticalLayout(headline, DatoFormLayout, buttonGuardar, buttonCancelar);
        dialogLayout.setPadding(false);
        dialogLayout.setAlignItems(FlexComponent.Alignment.STRETCH);
        dialogLayout.getStyle().set("min-width", "900px")
                .set("max-width", "100%").set("height", "80%");
        return dialogLayout;
    }

    private void removeInvitation(DatosJSONComplejoHIJO Aux) {
        //System.out.println(Aux.ip_from);
        deleteData("DatosJSONcomplejoPADRE", "DatosJSONcomplejoHIJO", Aux.ip_from);

        gridDatosJSONComplejoHIJO.setItems(DatosJSONComplejoPadreApi().getDatos());
        gridDatosJSONComplejoHIJO.setColumns("ip_from", "ip_to", "country_code", "country_name", "latitude", "longitude");

        gridDatosJSONComplejoHIJO.getColumnByKey("ip_from")
                .setAutoWidth(true);
        gridDatosJSONComplejoHIJO.getColumnByKey("ip_to")
                .setAutoWidth(true);
        gridDatosJSONComplejoHIJO.getColumnByKey("country_code")
                .setHeader("Country Code").setAutoWidth(true);
        gridDatosJSONComplejoHIJO.getColumnByKey("country_name")
                .setHeader("Country Name").setAutoWidth(true);
        gridDatosJSONComplejoHIJO.getColumnByKey("latitude")
                .setHeader("Latitude").setAutoWidth(true);
        gridDatosJSONComplejoHIJO.getColumnByKey("longitude")
                .setHeader("Longitude").setAutoWidth(true);
        gridDatosJSONComplejoHIJO.addColumn(
                new ComponentRenderer<>(Button::new, (button, DatoHijo) -> {
                    button.addThemeVariants(ButtonVariant.LUMO_ICON,
                            ButtonVariant.LUMO_ERROR,
                            ButtonVariant.LUMO_TERTIARY);
                    button.addClickListener(e -> this.removeInvitation(DatoHijo));
                    button.setIcon(new Icon(VaadinIcon.TRASH));
                })).setHeader("Manage");
    }

    private VerticalLayout createDialogLayoutModificarDato(DatosJSONComplejoHIJO AuxDatosJSONComplejoHijo) {
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

            putData("DatosJSONcomplejoPADRE", "DatosJSONcomplejoHIJO", AuxDatosJSONComplejoHijo.ip_from, Aux);

            gridDatosJSONComplejoHIJO.setItems(DatosJSONComplejoPadreApi().getDatos());
            gridDatosJSONComplejoHIJO.setColumns("ip_from", "ip_to", "country_code", "country_name", "latitude", "longitude");

            gridDatosJSONComplejoHIJO.getColumnByKey("ip_from")
                    .setAutoWidth(true);
            gridDatosJSONComplejoHIJO.getColumnByKey("ip_to")
                    .setAutoWidth(true);
            gridDatosJSONComplejoHIJO.getColumnByKey("country_code")
                    .setHeader("Country Code").setAutoWidth(true);
            gridDatosJSONComplejoHIJO.getColumnByKey("country_name")
                    .setHeader("Country Name").setAutoWidth(true);
            gridDatosJSONComplejoHIJO.getColumnByKey("latitude")
                    .setHeader("Latitude").setAutoWidth(true);
            gridDatosJSONComplejoHIJO.getColumnByKey("longitude")
                    .setHeader("Longitude").setAutoWidth(true);
            gridDatosJSONComplejoHIJO.addColumn(
                    new ComponentRenderer<>(Button::new, (button, DatoHijo) -> {
                        button.addThemeVariants(ButtonVariant.LUMO_ICON,
                                ButtonVariant.LUMO_ERROR,
                                ButtonVariant.LUMO_TERTIARY);
                        button.addClickListener(event -> this.removeInvitation(DatoHijo));
                        button.setIcon(new Icon(VaadinIcon.TRASH));
                    })).setHeader("Manage");

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

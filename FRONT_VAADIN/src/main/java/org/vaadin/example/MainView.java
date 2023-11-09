package org.vaadin.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.component.textfield.TextField;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

    public MainView() {
        // ----------------------------------------------
        // llamada al Post Inicial de todo a la api
        postData("DatosJSONcomplejoPADRE", null, null);
        // ----------------------------------------------

        // ----------------------------------------------
        // Creacion del grid
        //Type listaDataGeneral = new TypeToken<ArrayList<DatosJSONComplejoHIJO>>() {}.getType();
        //DatosJSONComplejoHIJOList = gson.fromJson(getData("DatosPadreGeneral","DatosGeneral"), listaDataGeneral);

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
        // ----------------------------------------------

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
        // Esto es para crear diferentes tabs y paginas
        Tab tabDatas = new Tab("Datas");
        Div pageDatas = new Div();
        pageDatas.setWidth(90f, Unit.PERCENTAGE);
        pageDatas.add(gridDatosJSONComplejoHIJO);
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
}

package org.vaadin.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.server.PWA;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.lang.reflect.Type;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A sample Vaadin view class.
 * <p>
 * To implement a Vaadin view just extend any Vaadin component and use @Route
 * annotation to announce it in a URL as a Spring managed bean.
 * <p>
 * A new instance of this class is created for every new user and every browser
 * tab/window.
 * <p>
 * The main view contains a text field for getting the user name and a button
 * that shows a greeting message in a notification.
 */
@Route
public class MainView extends VerticalLayout {

    static HttpRequest request;
    static HttpClient cliente = HttpClient.newBuilder().build();
    static HttpResponse response;

    static String api = "http://localhost:8084/";

    final Gson gson = new Gson();

    ArrayList<DatosJSONSimple> DatosJSONSimpleList = new ArrayList<>();
    DatosJSONSimple AuxDatosJSONSimple = new DatosJSONSimple();

    static Grid<DatosJSONSimple> gridDatosJSONSimple = new Grid<>(DatosJSONSimple.class);

    Button buttonAnadirDato = new Button("Añadir Dato");

    static Dialog dialog = new Dialog();

    public MainView() {

        POST.postData("DatosJSONSimple", null, null);

        Type listaDatosHijo = new TypeToken<ArrayList<DatosJSONSimple>>() {}.getType();
        DatosJSONSimpleList = gson.fromJson(GET.getData("DatosJSONSimple", null), listaDatosHijo);

        GridConfig.configureGrid(gridDatosJSONSimple);

        gridDatosJSONSimple.addItemClickListener(
                event -> {
                    AuxDatosJSONSimple = new DatosJSONSimple(event.getItem().ip_from, event.getItem().ip_to,
                            event.getItem().country_code, event.getItem().country_name,
                            event.getItem().latitude, event.getItem().longitude);
                    dialog.getElement().setAttribute("aria-label", "Equipo seleccionado");
                    VerticalLayout dialogLayout = PUT.createDialogLayoutModificarDato(AuxDatosJSONSimple);
                    dialog.add(dialogLayout);
                    dialog.setCloseOnEsc(false);
                    dialog.setCloseOnOutsideClick(false);
                    dialog.setDraggable(true);
                    dialog.setResizable(true);
                    dialog.open();
                });

        buttonAnadirDato.addClickListener(
                e -> {
                    VerticalLayout dialogLayout = null;
                    try {
                        dialogLayout = POST.createDialogLayoutAnadirDato();
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
                        DatosJSONSimpleList.clear();
                    }
                }
        );

        Tab tabDatas = new Tab("Datas");
        Div pageDatas = new Div();
        pageDatas.setWidth(90f, Unit.PERCENTAGE);
        pageDatas.add(gridDatosJSONSimple, buttonAnadirDato);
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        pageDatas.setVisible(true);

        Map<Tab, Component> tabsToPages = new HashMap<>();
        tabsToPages.put(tabDatas, pageDatas);

        Tabs tabs = new Tabs(tabDatas);
        tabs.setSizeFull();

        add(tabs, pageDatas);
    }
}

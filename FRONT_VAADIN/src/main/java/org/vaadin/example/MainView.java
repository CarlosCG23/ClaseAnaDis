package org.vaadin.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
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
import com.vaadin.flow.component.select.Select;
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

    //HttpRequest request;
    //HttpClient cliente = HttpClient.newBuilder().build();
    //HttpResponse response;
    static HttpRequest request;
    static HttpClient cliente = HttpClient.newBuilder().build();
    static HttpResponse response;

    final Gson gson = new Gson();
    Button buttonAnadirDato = new Button("Añadir Dato");
    static Dialog dialog = new Dialog();

    static Select<String> comboBox = new Select<>();
    static List<String> ComboBoxItems = Arrays.asList("ES", "US");

    static String api = "http://localhost:8085/";

    DatosJSONComplejoHIJO AuxDatosJSONComplejoHijo = new DatosJSONComplejoHIJO();

    ArrayList<DatosJSONComplejoHIJO> DatosJSONComplejoHIJOList = new ArrayList<>();

    static Grid<DatosJSONComplejoHIJO> gridDatosJSONComplejoHIJO = new Grid<>(DatosJSONComplejoHIJO.class);

    public MainView() {
        /*
        ArrayList <DatosJSONComplejoHIJO> pruebaDH = new ArrayList<>();

        pruebaDH.add(new DatosJSONComplejoHIJO(123456789, 98765432, "qwerty", "poiuytr"
                , 2.22, (long) 9.99));
        pruebaDH.add(new DatosJSONComplejoHIJO(123456789, 98765432, "qwerty", "poiuytr"
                , 2.22, (long) 9.99));
        pruebaDH.add(new DatosJSONComplejoHIJO(123456789, 98765432, "qwerty", "poiuytr"
                , 2.22, (long) 9.99));

        OtrosJSONComplejoHIJO pruebaO = new OtrosJSONComplejoHIJO("Carlos", 12);

        DatosJSONcomplejoPADRE pruebaDP = new DatosJSONcomplejoPADRE(pruebaDH, pruebaO);

        System.out.println(pruebaDP);
         */
        // ----------------------------------------------
        // llamada a Post Inicial de todo a la api
        POST.postData("DatosJSONcomplejoPADRE", null, null);
        // ----------------------------------------------

        // ----------------------------------------------
        // Llamada Get del Arraylist
        Type listaDatosHijo = new TypeToken<ArrayList<DatosJSONComplejoHIJO>>() {}.getType();
        DatosJSONComplejoHIJOList = gson.fromJson(GET.getData("DatosJSONcomplejoPADRE","DatosJSONcomplejoHIJO"), listaDatosHijo);
        // ----------------------------------------------

        // ----------------------------------------------
        // Creacion del grid
        GridConfig.configureGrid(gridDatosJSONComplejoHIJO);
        // ----------------------------------------------

        gridDatosJSONComplejoHIJO.addItemClickListener(
                event -> {
                    AuxDatosJSONComplejoHijo = new DatosJSONComplejoHIJO(event.getItem().ip_from, event.getItem().ip_to,
                            event.getItem().country_code, event.getItem().country_name,
                            event.getItem().latitude, event.getItem().longitude);
                    dialog.getElement().setAttribute("aria-label", "Equipo seleccionado");
                    VerticalLayout dialogLayout = PUT.createDialogLayoutModificarDato(AuxDatosJSONComplejoHijo);
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
                        DatosJSONComplejoHIJOList.clear();
                    }
                }
        );
        // ----------------------------------------------

        // Crear el combo box

        comboBox.setLabel("Selecciona un elemento");
        comboBox.setItems(ComboBoxItems);

        // Añadir un listener al combo box para manejar la selección
        comboBox.addValueChangeListener(event -> {
            String selectedItem = event.getValue();
            updateGridData(selectedItem);
        });




        // ----------------------------------------------
        // Creacion de los text fields del tab "Otros"
        TextField userName = new TextField();
        userName.setPrefixComponent(VaadinIcon.USER.create());
        userName.setLabel("User Name");
        userName.setReadOnly(true);
        userName.setValue(DatosJSONcomplejoPADRE.DatosJSONComplejoPadreApi().getOtros().getNombre());

        TextField userAge = new TextField();
        userAge.setLabel("User Age");
        userAge.setReadOnly(true);
        userAge.setValue(String.valueOf(DatosJSONcomplejoPADRE.DatosJSONComplejoPadreApi().getOtros().getEdad()));
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

        Tab tabCB = new Tab("Combo Box Data");
        Div pageCB = new Div();
        pageCB.setWidth(90f, Unit.PERCENTAGE);
        pageCB.add(comboBox);
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        pageCB.setVisible(false);
        // ----------------------------------------------

        // ----------------------------------------------
        // Esto es un hash map para asociar las tabs a las paginas
        Map<Tab, Component> tabsToPages = new HashMap<>();
        tabsToPages.put(tabDatas, pageDatas);
        tabsToPages.put(tabOtros, pageOtros);
        tabsToPages.put(tabCB, pageCB);
        // ----------------------------------------------

        // ----------------------------------------------
        // Gestionar el cambio entre pestañas
        Tabs tabs = new Tabs(tabDatas, tabOtros, tabCB);

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
        add(tabs, pageDatas, pageOtros, pageCB);
    }

    private void updateGridData(String selectedItem) {

        Notification.show(selectedItem);


    }
}

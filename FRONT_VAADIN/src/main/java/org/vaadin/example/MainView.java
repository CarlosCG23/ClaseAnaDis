package org.vaadin.example;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import org.springframework.beans.factory.annotation.Autowired;

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
    Button buttonAnadirData = new Button("Añadir Data");

    public MainView() {

        TextField NombreOtros = new TextField();
        NombreOtros.setLabel("Nombre");
        NombreOtros.setValue("Carlos");
        NombreOtros.setReadOnly(true);

        TextField EdadOtros = new TextField();
        EdadOtros.setLabel("Edad");
        EdadOtros.setValue("24");
        EdadOtros.setReadOnly(true);

        Tab tabDatas = new Tab("Datas");
        Div pageDatas = new Div();
        pageDatas.setWidth(90f, Unit.PERCENTAGE);
        pageDatas.add(buttonAnadirData);
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        pageDatas.setVisible(true);

        Tab tabOtros = new Tab("Otros");
        Div pageOtros = new Div();
        pageOtros.setWidth(90f, Unit.PERCENTAGE);
        pageOtros.add(NombreOtros, EdadOtros);
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        pageOtros.setVisible(false);

        Map<Tab, Component> tabsToPages = new HashMap<>();
        tabsToPages.put(tabDatas, pageDatas);
        tabsToPages.put(tabOtros, pageOtros);

        Tabs tabs = new Tabs(tabDatas, tabOtros);

        tabs.addThemeVariants(TabsVariant.LUMO_EQUAL_WIDTH_TABS);
        tabs.addSelectedChangeListener(event ->{
            tabsToPages.values().forEach(page -> page.setVisible(false));
            Component selectedPage = tabsToPages.get(tabs.getSelectedTab());
            selectedPage.setVisible(true);
        });

        tabs.setSizeFull();

        add(tabs, pageDatas, pageOtros);
    }

}

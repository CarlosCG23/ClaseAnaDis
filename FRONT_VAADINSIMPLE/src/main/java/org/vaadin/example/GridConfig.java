package org.vaadin.example;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.data.renderer.ComponentRenderer;

import java.util.List;

public class GridConfig {


    static List<String> columnNames = List.of("ip_from", "ip_to", "country_code", "country_name", "latitude", "longitude");
    static List<String> headers = List.of("IP From", "IP To", "Country Code", "Country Name", "Latitude", "Longitude");
    static <DatosJSONSimple> void configureGrid(Grid<org.vaadin.example.DatosJSONSimple> grid)
    {

        grid.setItems(DatosJSONcomplejoPADRE.DatosJSONComplejoPadreApi().getDatos());
        grid.setColumns("ip_from", "ip_to", "country_code", "country_name", "latitude", "longitude");

        for (int i = 0; i < columnNames.size(); i++) {
            grid.getColumnByKey(columnNames.get(i)).setHeader(headers.get(i)).setAutoWidth(true);
        }

        grid.addColumn(
                new ComponentRenderer<>(Button::new, (button, DatoHijo) -> {
                    button.addThemeVariants(ButtonVariant.LUMO_ICON,
                            ButtonVariant.LUMO_ERROR,
                            ButtonVariant.LUMO_TERTIARY);
                    button.addClickListener(event -> DELETE.removeInvitation(DatoHijo));
                    button.setIcon(new Icon(VaadinIcon.TRASH));
                })).setHeader("Manage");
    }
}

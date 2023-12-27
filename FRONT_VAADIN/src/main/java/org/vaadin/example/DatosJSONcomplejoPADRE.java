package org.vaadin.example;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.google.gson.Gson;

import java.util.ArrayList;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
public class DatosJSONcomplejoPADRE {
    static Gson gson = new Gson();
    ArrayList<DatosJSONComplejoHIJO> Datos = new ArrayList<>();
    OtrosJSONComplejoHIJO Otros = new OtrosJSONComplejoHIJO();

    public DatosJSONcomplejoPADRE() {
    }

    public DatosJSONcomplejoPADRE(ArrayList<DatosJSONComplejoHIJO> datos, OtrosJSONComplejoHIJO otros) {
        Datos = datos;
        Otros = otros;
    }

    public ArrayList<DatosJSONComplejoHIJO> getDatos() {
        return Datos;
    }

    public void setDatos(ArrayList<DatosJSONComplejoHIJO> datos) {
        Datos = datos;
    }

    public OtrosJSONComplejoHIJO getOtros() {
        return Otros;
    }

    public void setOtros(OtrosJSONComplejoHIJO otros) {
        Otros = otros;
    }

    @Override
    public String toString() {
        return "DatosJSONcomplejoPADRE{" +
                "Datos=" + Datos +
                ", Otros=" + Otros +
                '}';
    }

    static DatosJSONcomplejoPADRE DatosJSONComplejoPadreApi(){

        DatosJSONcomplejoPADRE DatosJSONComplejoPadre;

        String StringDatosJSONComplejoPadre = GET.getData("DatosJSONcomplejoPADRE", null);

        DatosJSONComplejoPadre = gson.fromJson(StringDatosJSONComplejoPadre, DatosJSONcomplejoPADRE.class);

        return DatosJSONComplejoPadre;
    }
}

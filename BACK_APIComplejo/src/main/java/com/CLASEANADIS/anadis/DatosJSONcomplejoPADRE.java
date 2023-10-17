package com.CLASEANADIS.anadis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.ArrayList;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
public class DatosJSONcomplejoPADRE {
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
}

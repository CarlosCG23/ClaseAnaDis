package com.CLASEANADIS.anadis;

import java.util.ArrayList;

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

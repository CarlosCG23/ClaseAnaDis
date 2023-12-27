package com.CLASEANADIS.anadis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.ArrayList;
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
public class PaisesJSONComplejoPADRE {

    ArrayList<PaisesJSONComplejoHijo> US = new ArrayList<>();
    ArrayList<PaisesJSONComplejoHijo> ES = new ArrayList<>();

    public PaisesJSONComplejoPADRE() {
    }

    public PaisesJSONComplejoPADRE(ArrayList<PaisesJSONComplejoHijo> US, ArrayList<PaisesJSONComplejoHijo> ES) {
        this.US = US;
        this.ES = ES;
    }

    public ArrayList<PaisesJSONComplejoHijo> getUS() {
        return US;
    }

    public void setUS(ArrayList<PaisesJSONComplejoHijo> US) {
        this.US = US;
    }

    public ArrayList<PaisesJSONComplejoHijo> getES() {
        return ES;
    }

    public void setES(ArrayList<PaisesJSONComplejoHijo> ES) {
        this.ES = ES;
    }

    @Override
    public String toString() {
        return "PaisesJSONComplejoPADRE{" +
                "US=" + US +
                ", ES=" + ES +
                '}';
    }
}

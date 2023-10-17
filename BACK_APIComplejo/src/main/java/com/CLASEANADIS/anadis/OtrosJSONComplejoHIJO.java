package com.CLASEANADIS.anadis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
public class OtrosJSONComplejoHIJO {

    @JsonProperty("nombre")
    private String nombre;
    @JsonProperty("edad")
    public int edad;

    public OtrosJSONComplejoHIJO() {
    }

    public OtrosJSONComplejoHIJO(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "OtrosJSONComplejoHIJO{" +
                "nombre='" + nombre + '\'' +
                ", edad=" + edad +
                '}';
    }
}

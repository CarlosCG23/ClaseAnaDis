package com.CLASEANADIS.anadis;

public class OtrosJSONComplejoHIJO {

    private String nombre;
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

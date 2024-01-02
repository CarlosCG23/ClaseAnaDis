package org.vaadin.example;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static org.vaadin.example.MainView.*;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
public class DatosJSONSimple {
    static Gson gson = new Gson();
    @JsonProperty("ip_from")
    public long ip_from;
    @JsonProperty("ip_to")
    public long ip_to;
    @JsonProperty("country_code")
    public String country_code;
    @JsonProperty("country_name")
    public String country_name;
    @JsonProperty("latitude")
    public double latitude;
    @JsonProperty("longitude")
    public float longitude;

    public DatosJSONSimple() {
    }

    public DatosJSONSimple(long ip_from, long ip_to, String country_code, String country_name, double latitude, float longitude) {
        this.ip_from = ip_from;
        this.ip_to = ip_to;
        this.country_code = country_code;
        this.country_name = country_name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public long getIp_from() {
        return ip_from;
    }

    public void setIp_from(long ip_from) {
        this.ip_from = ip_from;
    }

    public long getIp_to() {
        return ip_to;
    }

    public void setIp_to(long ip_to) {
        this.ip_to = ip_to;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "{\n" +
                "\t\"ip_from\": " + ip_from +
                ",\n\t\"ip_to\": " + ip_to +
                ",\n\t\"country_code\": \"" + country_code + '\"' +
                ",\n\t\"country_name\": \"" + country_name + '\"' +
                ",\n\t\"latitude\": " + latitude +
                ",\n\t\"longitude\": " + longitude +
                "\n}";
    }

    static ArrayList<DatosJSONSimple> DatosJSONSimpleApi(){
        Type datos = new TypeToken<ArrayList<DatosJSONSimple>>(){}.getType();

        ArrayList<DatosJSONSimple> datosJSONSimple;

        String StringDatosJSONSimple = GET.getData("DatosJSONSimple", null);

        datosJSONSimple = gson.fromJson(StringDatosJSONSimple, datos);

        return datosJSONSimple;
    }
}


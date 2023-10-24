package com.CLASEANADIS.anadis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
public class DatosJSONComplejoHIJO {
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

    public DatosJSONComplejoHIJO() {
    }

    public DatosJSONComplejoHIJO(long ip_from, long ip_to, String country_code, String country_name, double latitude, float longitude) {
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
        return "DatosJSONComplejoHIJO{" +
                "ip_from=" + ip_from +
                ", ip_to=" + ip_to +
                ", country_code='" + country_code + '\'' +
                ", country_name='" + country_name + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}

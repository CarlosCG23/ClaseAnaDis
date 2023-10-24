package com.CLASEANADIS.anadis;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class LectorJSON {
    public DatosJSONcomplejoPADRE LecturaJSONcomplejo(){
        String json;
        DatosJSONcomplejoPADRE datosJSONcomplejoPADRE = new DatosJSONcomplejoPADRE();

        Gson gson = new Gson();

        json = "";

        try (BufferedReader br = new BufferedReader(new FileReader("JSONip_Complejo.json"))){
            String linea;

            while ((linea = br.readLine()) != null){
                json+= linea;
                //System.out.println(linea);
            }

            datosJSONcomplejoPADRE = gson.fromJson(json, DatosJSONcomplejoPADRE.class);
            System.out.println("Sesion guardada con exito");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return datosJSONcomplejoPADRE;
    }
}

package com.CLASEANADIS.anadis;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EscritorCCJSON {
    public void EscrituraCC(ArrayList<DatosJSONComplejoHIJO> datosjson) throws FileNotFoundException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();;
        //Type listType = new TypeToken<List<DatosJSONComplejoHIJO>>() {}.getType();
        //List<DatosJSONComplejoHIJO> dataPoints = datosjson;

        // Agrupar por la variable "MsCode"
        Map<String, List<DatosJSONComplejoHIJO>> groupedByMsCode = datosjson.stream()
                .collect(Collectors.groupingBy(DatosJSONComplejoHIJO::getCountry_code));

        groupedByMsCode.values().forEach(list -> list.forEach(dataPoint -> dataPoint.setCountry_code(null)));
        //System.out.println(groupedByMsCode);

        // Creamos un nuevo archivo JSON con los resultados
        try (FileWriter writer = new FileWriter("CountryCode.json")) {
            gson.toJson(groupedByMsCode, writer);
            System.out.println("Archivo creado.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

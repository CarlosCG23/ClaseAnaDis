package org.vaadin.example;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.vaadin.example.MainView.*;


public class GET {

    static String getData(String url1, String url2){
        try {
            String resource;

            if (url2 == null){
                resource = String.format(api + url1);
            }
            else{
                resource = String.format(api + url1 + "/" + url2);
            }

            request= HttpRequest
                    .newBuilder(new URI(resource))
                    .header("Content-Type","application/json")
                    .GET()
                    .build();
            response=cliente.send(request,HttpResponse.BodyHandlers.ofString());

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return (String) response.body();
    }
}

/*
public void opcion2(ArrayList<DatosJSON> datosjson, String path) throws FileNotFoundException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();;
        Type listType = new TypeToken<List<DatosJSON>>() {}.getType();
        List<DatosJSON> dataPoints = gson.fromJson(new FileReader("cp-national-datafile.json"), listType);

        Scanner scan = new Scanner(System.in);

        // Agrupar por la variable "MsCode"
        Map<String, List<DatosJSON>> groupedByMsCode = dataPoints.stream()
                .collect(Collectors.groupingBy(DatosJSON::getMsCode));

        groupedByMsCode.values().forEach(list -> list.forEach(dataPoint -> dataPoint.setMsCode(null)));
        // Creamos un nuevo archivo JSON con los resultados
        try (FileWriter writer = new FileWriter("MsCode_json.json")) {
            gson.toJson(groupedByMsCode, writer);
            System.out.println("Archivo MsCode_json.json creado.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {scan.nextLine();} catch(Exception e){}
    }
 */
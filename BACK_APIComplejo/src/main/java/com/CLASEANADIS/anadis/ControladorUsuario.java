package com.CLASEANADIS.anadis;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class ControladorUsuario {
    public DatosJSONcomplejoPADRE NewDatosPadres = new DatosJSONcomplejoPADRE();

    // POST de todo (JSON Complejo)
    @PostMapping(path = "/DatosJSONcomplejoPADRE")
    public ResponseEntity<DatosJSONcomplejoPADRE> nuevoEmpresa() {

        LectorJSON Leer = new LectorJSON();

        this.NewDatosPadres = Leer.LecturaJSONcomplejo();

        return new ResponseEntity<DatosJSONcomplejoPADRE>(this.NewDatosPadres, HttpStatus.CREATED);
    }

    // POST de un dato nuevo del JSON complejo
    @PostMapping(path = "/DatosJSONcomplejoPADRE/DatosJSONcomplejoHIJO",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<DatosJSONComplejoHIJO>> nuevoUsuario(@RequestBody DatosJSONComplejoHIJO nuevoDato) {

        this.NewDatosPadres.getDatos().add(nuevoDato);

        return new ResponseEntity<ArrayList<DatosJSONComplejoHIJO>>(this.NewDatosPadres.getDatos(), HttpStatus.CREATED);
    }

    // GET de todo
    @GetMapping("/DatosJSONcomplejoPADRE")
    public DatosJSONcomplejoPADRE GetDatosPadre(){return this.NewDatosPadres;}

    // GET del arraylist de Datos
    @GetMapping("/DatosJSONcomplejoPADRE/DatosJSONcomplejoHIJO")
    public ArrayList<DatosJSONComplejoHIJO> GetDatos(){return this.NewDatosPadres.getDatos();}

    // GET de un solo dato en base al atributo ip_from
    @GetMapping("/DatosJSONcomplejoPADRE/DatosJSONcomplejoHIJO/{ip_from}")
    public ResponseEntity<DatosJSONComplejoHIJO> GetDato(@PathVariable long ip_from){

        DatosJSONComplejoHIJO auxDatos = new DatosJSONComplejoHIJO();
        //System.out.println(NewDatosPadres.getDatos().size());

        for (int i = 0; i < NewDatosPadres.getDatos().size(); i++) {
            if (NewDatosPadres.getDatos().get(i).getIp_from() == ip_from) {
                auxDatos = NewDatosPadres.getDatos().get(i);
                return new ResponseEntity<DatosJSONComplejoHIJO>(auxDatos, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // GET del atributo country_code de un dato en base al atributo ip_from
    @GetMapping("/DatosJSONcomplejoPADRE/DatosJSONcomplejoHIJO/{ip_from}/CountryCode")
    public ResponseEntity<String> GetDatoContryCode(@PathVariable long ip_from){

        String auxDatos;

        for (int i = 0; i < NewDatosPadres.getDatos().size(); i++) {
            if (NewDatosPadres.getDatos().get(i).getIp_from() == ip_from) {
                auxDatos = NewDatosPadres.getDatos().get(i).country_code;
                return new ResponseEntity<String>(auxDatos, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // GET de objeto "Otros"
    @GetMapping("/DatosJSONcomplejoPADRE/OtrosJSONcomplejoHIJO")
    public OtrosJSONComplejoHIJO GetOtros(){return this.NewDatosPadres.getOtros();}

    // GET del atributo nombre del objeto otros
    @GetMapping("/DatosJSONcomplejoPADRE/OtrosJSONcomplejoHIJO/Nombre")
    public String GetOtrosNombre(){return this.NewDatosPadres.getOtros().getNombre();}

    // DELETE de un solo dato en base al atributo ip_from
    @DeleteMapping("/DatosJSONcomplejoPADRE/DatosJSONcomplejoHIJO/{ip_from}")
    public ResponseEntity<ArrayList<DatosJSONComplejoHIJO>> DeleteDato(@PathVariable long ip_from){
        for (int i = 0; i < NewDatosPadres.getDatos().size(); i++){
            if(NewDatosPadres.getDatos().get(i).getIp_from() == ip_from){
                NewDatosPadres.getDatos().remove(i);
                return new ResponseEntity<ArrayList<DatosJSONComplejoHIJO>>(this.NewDatosPadres.getDatos(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // DELETE del objeto "otros"
    @DeleteMapping("/DatosJSONcomplejoPADRE/OtrosJSONcomplejoHIJO")
    public ResponseEntity<OtrosJSONComplejoHIJO> DeleteOtro(){
        NewDatosPadres.getOtros().setNombre("");
        NewDatosPadres.getOtros().setEdad(0);
        return new ResponseEntity<OtrosJSONComplejoHIJO>(this.NewDatosPadres.getOtros(), HttpStatus.OK);
    }
}

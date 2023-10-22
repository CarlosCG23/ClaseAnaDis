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
}

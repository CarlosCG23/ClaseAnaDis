package com.CLASEANADIS.anadis;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class ControladorUsuario {
    public DatosJSONcomplejoPADRE NewDatosPadres = new DatosJSONcomplejoPADRE();

    @PostMapping(path = "/DatosJSONcomplejoPADRE")
    public ResponseEntity<DatosJSONcomplejoPADRE> nuevoEmpresa() {

        LectorJSON Leer = new LectorJSON();

        this.NewDatosPadres = Leer.LecturaJSONcomplejo();

        return new ResponseEntity<DatosJSONcomplejoPADRE>(this.NewDatosPadres, HttpStatus.CREATED);
    }
}

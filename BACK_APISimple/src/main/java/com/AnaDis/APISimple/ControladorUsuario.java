package com.AnaDis.APISimple;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class ControladorUsuario {

    public ArrayList<DatosJSONSimple> NewDatosSimple = new ArrayList<>();

    // POST de todo (JSON Simple)
    @PostMapping(path = "/DatosJSONSimple")
    public ResponseEntity<ArrayList<DatosJSONSimple>> nuevoDatosSimple() {

        LectorJSON Leer = new LectorJSON();

        this.NewDatosSimple = Leer.LecturaJSONsimple();

        return new ResponseEntity<ArrayList<DatosJSONSimple>>(this.NewDatosSimple, HttpStatus.CREATED);
    }

    // POST de un dato nuevo del JSON Simple
    @PostMapping(path = "/DatosJSONSimple/Dato",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<DatosJSONSimple>> nuevoDato(@RequestBody DatosJSONSimple nuevoDato) {

        this.NewDatosSimple.add(nuevoDato);

        return new ResponseEntity<ArrayList<DatosJSONSimple>>(this.NewDatosSimple, HttpStatus.CREATED);
    }

    // GET de todo json simple
    @GetMapping("/DatosJSONSimple")
    public ArrayList<DatosJSONSimple> GetDatos(){return this.NewDatosSimple;}

    // GET de un solo dato en base al atributo ip_from
    @GetMapping("/DatosJSONSimple/Dato/{ip_from}")
    public ResponseEntity<DatosJSONSimple> GetDato(@PathVariable long ip_from){

        DatosJSONSimple auxDatos = new DatosJSONSimple();
        //System.out.println(NewDatosPadres.getDatos().size());

        for (int i = 0; i < NewDatosSimple.size(); i++) {
            if (NewDatosSimple.get(i).getIp_from() == ip_from) {
                auxDatos = NewDatosSimple.get(i);
                return new ResponseEntity<DatosJSONSimple>(auxDatos, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // GET del atributo country_code de un dato en base al atributo ip_from
    @GetMapping("/DatosJSONSimple/Dato/{ip_from}/ip_from")
    public ResponseEntity<Long> GetDatoIpFrom(@PathVariable long ip_from){

        long auxDatos;

        for (int i = 0; i < NewDatosSimple.size(); i++) {
            if (NewDatosSimple.get(i).getIp_from() == ip_from) {
                auxDatos = NewDatosSimple.get(i).ip_from;
                return new ResponseEntity<Long>(auxDatos, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // DELETE de un solo dato en base al atributo ip_from
    @DeleteMapping("/DatosJSONSimple/Dato/{ip_from}")
    public ResponseEntity<ArrayList<DatosJSONSimple>> DeleteDato(@PathVariable long ip_from){
        for (int i = 0; i < NewDatosSimple.size(); i++){
            if(NewDatosSimple.get(i).getIp_from() == ip_from){
                NewDatosSimple.remove(i);
                return new ResponseEntity<ArrayList<DatosJSONSimple>>(this.NewDatosSimple, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/DatosJSONSimple/Dato/{ip_from}")
    public ResponseEntity<ArrayList<DatosJSONSimple>> PutDato(@RequestBody DatosJSONSimple nuevoDato, @PathVariable long ip_from){

        long auxIP_From, auxIP_to;

        for (int i = 0; i < NewDatosSimple.size(); i++){
            if(NewDatosSimple.get(i).getIp_from() == ip_from){

                auxIP_From = NewDatosSimple.get(i).ip_from;
                auxIP_to = NewDatosSimple.get(i).ip_to;

                NewDatosSimple.set(i, nuevoDato);

                // ip_from e ip_to no se pueden modificar
                NewDatosSimple.get(i).setIp_from(auxIP_From);
                NewDatosSimple.get(i).setIp_to(auxIP_to);


                return new ResponseEntity<ArrayList<DatosJSONSimple>>(this.NewDatosSimple, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

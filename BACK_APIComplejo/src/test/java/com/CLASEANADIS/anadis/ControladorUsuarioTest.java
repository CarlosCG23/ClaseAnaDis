package com.CLASEANADIS.anadis;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static com.CLASEANADIS.anadis.ControladorUsuario.NewDatosPadres;
import static org.junit.jupiter.api.Assertions.*;

class ControladorUsuarioTest {

    DatosJSONComplejoHIJO datosJSONComplejoHIJO1;
    DatosJSONComplejoHIJO datosJSONComplejoHIJO2;
    ControladorUsuario controladorUsuario = new ControladorUsuario();

    @BeforeEach
    void setUp() {
        datosJSONComplejoHIJO1 = new DatosJSONComplejoHIJO(12345678,9876543,
                "ES", "qwerty",
                9.99,(float) 1.11);
        datosJSONComplejoHIJO2 = new DatosJSONComplejoHIJO(12345672,9876543,
                "ES", "qwerty",
                9.99,(float) 1.11);

        ArrayList<DatosJSONComplejoHIJO> datosJSONComplejoHIJOArrayList = new ArrayList<>();
        datosJSONComplejoHIJOArrayList.add(datosJSONComplejoHIJO1);
        datosJSONComplejoHIJOArrayList.add(datosJSONComplejoHIJO2);

        NewDatosPadres = new DatosJSONcomplejoPADRE(datosJSONComplejoHIJOArrayList, new OtrosJSONComplejoHIJO());
    }

    @AfterEach
    void tearDown() {
    }

    @Test // Comprobar si se ha añadido un dato
    void nuevoDato() {
        ResponseEntity<ArrayList<DatosJSONComplejoHIJO>> responseEntity = controladorUsuario.nuevoDato(datosJSONComplejoHIJO1);

        if(responseEntity.getStatusCode().is2xxSuccessful())
        {
            assertTrue(true);
        }
        else
        {
            fail();
        }
    }

    @Test // comprobar si se se puede eliminar un dato
    void deleteDatoSuccess() throws FileNotFoundException {

        ResponseEntity<ArrayList<DatosJSONComplejoHIJO>> responseEntity = controladorUsuario.DeleteDato(12345678);

        if (responseEntity.getStatusCode().is2xxSuccessful())
        {
            assertTrue(true);
        }
        else
        {
            fail();
        }
    }

    @Test // comprobar si se no se puede eliminar un dato porque no existe
    void deleteDatoFail() {

        ResponseEntity<ArrayList<DatosJSONComplejoHIJO>> responseEntity = controladorUsuario.DeleteDato(0);

        if (responseEntity.getStatusCode().is4xxClientError())
        {
            assertTrue(true);
        }
        else
        {
            fail();
        }
    }

    @Test // comprobar si se ha modificado un dato
    void putDatoSuccess() {

        ResponseEntity <ArrayList<DatosJSONComplejoHIJO>> responseEntity = controladorUsuario.PutDato(datosJSONComplejoHIJO2, 12345678);

        if(responseEntity.getStatusCode().is2xxSuccessful())
        {
            assertTrue(true);
        }
        else
        {
            fail();
        }
    }

    @Test // comprobar si se ha modificado un dato
    void putDatoFail() {

        ResponseEntity <ArrayList<DatosJSONComplejoHIJO>> responseEntity = controladorUsuario.PutDato(datosJSONComplejoHIJO2, 0);

        if(responseEntity.getStatusCode().is2xxSuccessful())
        {
            assertTrue(true);
        }
        else
        {
            fail();
        }
    }
}
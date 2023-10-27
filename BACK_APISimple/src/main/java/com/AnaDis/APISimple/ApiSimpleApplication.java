package com.AnaDis.APISimple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication
public class ApiSimpleApplication {

	public static void main(String[] args) {
		/*
		LectorJSON lectorJSON = new LectorJSON();
		ArrayList<DatosJSONSimple> datosJSONSimples = new ArrayList<>();

		datosJSONSimples = lectorJSON.LecturaJSONsimple();
		System.out.println(datosJSONSimples);
		*/

		SpringApplication.run(ApiSimpleApplication.class, args);
	}

}

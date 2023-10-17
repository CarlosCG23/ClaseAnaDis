package com.CLASEANADIS.anadis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AnadisApplication {

	public static void main(String[] args) {
		/*
		LectorJSON lectorJSON = new LectorJSON();
		DatosJSONcomplejoPADRE datosJSONcomplejoPADRE = new DatosJSONcomplejoPADRE();
		datosJSONcomplejoPADRE = lectorJSON.LecturaJSONcomplejo();
		System.out.println(datosJSONcomplejoPADRE);
		*/

		SpringApplication.run(AnadisApplication.class, args);
	}

}

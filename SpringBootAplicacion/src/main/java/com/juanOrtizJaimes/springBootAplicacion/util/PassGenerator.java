package com.juanOrtizJaimes.springBootAplicacion.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PassGenerator {

	public static void main(String ...args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
       
        //El String que mandamos al metodo encode es el password que queremos encriptar.
        System.out.println(bCryptPasswordEncoder.encode("2704"));
        /*
         * Resultado: $2a$04$n6WIRDQlIByVFi.5rtQwEOTAzpzLPzIIG/O6quaxRKY2LlIHG8uty
         */
	}
}

package com.test03;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;

import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

public class MTest {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
		
		KeyPair key = generator.generateKeyPair();
		
		System.out.println(generator.getAlgorithm());
		System.out.println("개인키 : " + key.getPrivate().getEncoded());
		System.out.println("공개키 : " + key.getPublic());
		
		Object res = NimbusJwtDecoder.withPublicKey((RSAPublicKey) key.getPublic()).build();
		System.out.println(res);
	}

}

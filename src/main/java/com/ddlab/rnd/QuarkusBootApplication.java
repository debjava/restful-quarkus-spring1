package com.ddlab.rnd;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class QuarkusBootApplication {

	public static void main(String[] args) {
		System.out.println("Starting Quarkus application");
		Quarkus.run(args);
	}

}

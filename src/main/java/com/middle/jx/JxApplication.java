package com.middle.jx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JxApplication {

	public static void main(String[] args) {
		configureWindowsRootSslTrustStore();
		SpringApplication.run(JxApplication.class, args);
	}

	private static void configureWindowsRootSslTrustStore() {
		String osName = System.getProperty("os.name", "").toLowerCase();
		if (osName.contains("windows")) {
			System.setProperty("javax.net.ssl.trustStoreType", "Windows-ROOT");
			System.setProperty("javax.net.ssl.trustStoreProvider", "SunMSCAPI");
		}
	}

}

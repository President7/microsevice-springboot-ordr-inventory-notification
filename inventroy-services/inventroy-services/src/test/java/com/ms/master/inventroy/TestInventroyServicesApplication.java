package com.ms.master.inventroy;

import org.springframework.boot.SpringApplication;

public class TestInventroyServicesApplication {

	public static void main(String[] args) {
		SpringApplication.from(InventroyServicesApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}

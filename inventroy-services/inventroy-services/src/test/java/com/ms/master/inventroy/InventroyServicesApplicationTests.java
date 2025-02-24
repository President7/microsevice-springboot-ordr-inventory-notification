package com.ms.master.inventroy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MySQLContainer;
import io.restassured.RestAssured;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class InventroyServicesApplicationTests {
	@ServiceConnection
	static MySQLContainer mySQLContainer=new MySQLContainer("mysql:8.0.32");
	@LocalServerPort
	private Integer port;
	@BeforeEach
	void setup(){
		RestAssured.baseURI="http://localhost";
		RestAssured.port=port;
	}
	static{
		mySQLContainer.start();
	}

@Test
//	void contextLoads() {
//	}
void shouldReadInventory()
{
	var response=RestAssured.given()
			.when()
			.get("http://localhost:8082/api/inventory?quantity=100&skuCode=galaxy_14")
			.then()
			.log().all()
			.statusCode(200)
			.extract().response().as(Boolean.class);
	assertTrue(response);
	var negativeResponse=RestAssured.given()
			.when()
			.get()
			.then()
			.log().all()
			.statusCode(200)
			.extract().response().as(Boolean.class);
		assertFalse(negativeResponse);
}
}

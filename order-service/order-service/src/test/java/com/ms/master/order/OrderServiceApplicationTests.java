package com.ms.master.order;
import com.ms.master.order.stubs.InventryClientsStubs;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MySQLContainer;
import static org.junit.Assert.assertThat;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 0)
class OrderServiceApplicationTests {
	@ServiceConnection
	static MySQLContainer mySQLContainer = new MySQLContainer<>("mysql:8.0.32");
	@LocalServerPort
	private Integer port;
	@BeforeEach
	void setup()
	{
		RestAssured.baseURI="http://localhost";
		RestAssured.port=port;
	}

static
	{
		mySQLContainer.start();
	}
	@Test
	void shouldSubmitOrder() {
    String submitOrderJson= """
        {
            "quantity": "1",
            "skuCode": "i_phone_15",
            "price": "1000"
        }
		
		""";
		InventryClientsStubs.stubInventryCall("i_phone_15", 1);
	var responseBodyString=RestAssured.given()
            .contentType("application/json")
			.body(submitOrderJson)
			.when()
			.post("/api/order")
			.then()
			.log().all()
            .statusCode(201)
			.extract()
            .body().asString();
       assertThat(responseBodyString, Matchers.is("Order place successFully"));
	}
}

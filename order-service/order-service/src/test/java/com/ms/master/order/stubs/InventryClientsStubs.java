package com.ms.master.order.stubs;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class InventryClientsStubs  {
public static void stubInventryCall(String skuCode, Integer quantity)
{
    stubFor(get(urlEqualTo("/api/inventory?skuCode=" +skuCode +"&quantity="+quantity))
            .willReturn(aResponse()
                    .withStatus(200)
                    .withHeader("Content-Type", "Application/json")
                    .withBody("true")));
}
}

package com.devcortes.webflux.server;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = EventReactiveController.class)
class EventReactiveControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void eventGetById() {
        Event responseBody = this.webTestClient.get()
                .uri("/12")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Event.class)
                .returnResult()
                .getResponseBody();

        Assertions.assertNotEquals(responseBody, null);
        Assertions.assertEquals(responseBody.getId(), 12);
    }

}
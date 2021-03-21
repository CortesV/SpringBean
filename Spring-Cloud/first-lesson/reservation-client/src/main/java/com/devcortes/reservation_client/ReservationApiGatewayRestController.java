package com.devcortes.reservation_client;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reservations")
public class ReservationApiGatewayRestController{

	@Autowired
	private RestTemplate restTemplate;

/*	@Autowired
	@Output(Source.OUTPUT)
	private MessageChannel messageChannel;*/

	@HystrixCommand(fallbackMethod = "getReservationNamesFallback")
	@RequestMapping(method = RequestMethod.GET, value = "/names")
	public ResponseEntity<Collection<String>> getReservationNames() {
		ParameterizedTypeReference<Resources<Reservation>> resourcesParameterizedTypeReference = new ParameterizedTypeReference<Resources<Reservation>>() {
		};
		ResponseEntity<Resources<Reservation>> exchange = restTemplate
				.exchange("http://reservation-service/reservations", HttpMethod.GET, null,
						resourcesParameterizedTypeReference);
		return new ResponseEntity<Collection<String>>(exchange.getBody().getContent().stream().map(Reservation::getReservationName).collect(Collectors.toList
				()), HttpStatus.OK);
	}

	public ResponseEntity<Collection<String>> getReservationNamesFallback () {
		return new ResponseEntity<Collection<String>>(Collections.emptyList(), HttpStatus.OK);
	}

/*	@RequestMapping(method = RequestMethod.POST)
	public void write(@RequestBody Reservation reservation) {
		this.messageChannel.send(MessageBuilder.withPayload(reservation.getReservationName()).build());
	}*/
}

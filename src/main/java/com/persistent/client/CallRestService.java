package com.persistent.client;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.persistent.model.Person;

@Component
public class CallRestService implements CommandLineRunner{
	
	private static void callRestService() {
		RestTemplate template = new RestTemplate();
		Person person = template.getForObject("http://localhost:8080/person/1", Person.class);
		System.out.println("Person Details are : "+person.toString());

		
		
	}
	
	private static void getAllListofPerson() {
		RestTemplate restTemplate = new RestTemplate();
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setObjectMapper(new ObjectMapper());
		restTemplate.getMessageConverters().add(converter);		
		ResponseEntity<Person[]> response = restTemplate.getForEntity("http://localhost:8080/person/list", Person[].class);
		List<Person>persons= Arrays.asList(response.getBody());
		for(Person per:persons) {
			System.out.println("Person Details are : "+per.toString());
		}
	
	}
	
	private static void getPersonMap() {
		RestTemplate restTemplate = new RestTemplate();
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setObjectMapper(new ObjectMapper());
		restTemplate.getMessageConverters().add(converter);		
		Map<String, Person> response = restTemplate.getForObject("http://localhost:8080/person/all", Map.class);
		for(Entry<String,Person> per:response.entrySet()) {
			System.out.println("Person Details are : "+per.toString());
		}
	
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Printing Person---------------------------------------");
		callRestService();
		System.out.println("Printing List---------------------------------------");
		getAllListofPerson();
		System.out.println("Printing Map---------------------------------------");
		getPersonMap();
		
	}
}

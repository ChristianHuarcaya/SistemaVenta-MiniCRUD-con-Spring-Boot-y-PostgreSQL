package com.idat.semana6.controller;

import org.springframework.http.MediaType;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.idat.semana6.entidad.Persona;
import com.idat.semana6.service.IPersonaService;

@RestController
@RequestMapping("/personas")
public class PersonaController {

	@Autowired
	private IPersonaService service;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Persona>> listar() {
		List<Persona> lista = service.listar();
		if (lista == null) {
			lista = new ArrayList<Persona>();
		}
		return new ResponseEntity<List<Persona>>(lista, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Persona> buscar(@PathVariable("id") Long id) {
		Persona per = service.buscar(id);
		if (per == null) {
			per = new Persona();
		}
		return new ResponseEntity<Persona>(per, HttpStatus.OK);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Persona> registrar(@RequestBody Persona persona) {
	    Persona per = service.registrar(persona);
	    return new ResponseEntity<>(per, HttpStatus.CREATED); // 👈 esto permite que Swagger muestre la respuesta
	}


}
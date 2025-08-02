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

import com.idat.semana6.entidad.Venta;

import com.idat.semana6.service.IVentaService;

@RestController
@RequestMapping("/ventas")
public class VentaController {
	@Autowired
	private IVentaService service;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Venta>> listar() {
		List<Venta> lista = service.listar();
		if (lista == null) {
			lista = new ArrayList<Venta>();
		}
		return new ResponseEntity<List<Venta>>(lista, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Venta> buscar(@PathVariable("id") Long id) {
		Venta venta = service.buscar(id);
		if (venta == null) {
			venta = new Venta();
		}
		return new ResponseEntity<Venta>(venta, HttpStatus.OK);
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Venta> registrar(@RequestBody Venta venta) {
		Venta vent = service.registrar(venta);
		URI loc = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(vent.getId()).toUri();
		return ResponseEntity.created(loc).build();
	}

}

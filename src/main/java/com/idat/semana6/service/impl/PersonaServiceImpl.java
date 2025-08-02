package com.idat.semana6.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.idat.semana6.entidad.Persona;
import com.idat.semana6.entidad.Producto;
import com.idat.semana6.repository.IPersonaRepository;
import com.idat.semana6.repository.IProductoRepository;
import com.idat.semana6.service.IPersonaService;
import com.idat.semana6.service.IProductoService;

@Service
public class PersonaServiceImpl implements IPersonaService {

    @Autowired
    private IPersonaRepository repository;

    @Override
    public Persona registrar(Persona t) {
        
        return repository.save(t);
    }

    @Override
    public Persona modificar(Persona t) {
        Optional<Persona> opt = repository.findById(t.getId());
        return opt.isPresent() ? repository.save(t) : null;
    }

    @Override
    public boolean eliminar(Long id) {
        Optional<Persona> opt = repository.findById(id);
        if (opt.isPresent()) {
            repository.delete(opt.get());
            return true;
        }
        return false;
    }

    @Override
    public Persona buscar(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Persona> listar() {
        return repository.findAll();
    }

    @Override
    public Page<Persona> listarPagina(Pageable page) {
        return repository.findAll(page);
    }
}



package com.idat.semana6.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.idat.semana6.entidad.Producto;
import com.idat.semana6.repository.IProductoRepository;
import com.idat.semana6.service.IProductoService;

@Service
public class ProductoServiciImpl implements IProductoService {

    @Autowired
    private IProductoRepository repository;

    @Override
    public Producto registrar(Producto t) {
        
        return repository.save(t);
    }

    @Override
    public Producto modificar(Producto t) {
        Optional<Producto> opt = repository.findById(t.getId());
        if (opt.isPresent()) { // ✅ CORREGIDO: verificar si está presente
            return repository.save(t);
        }
        return null;
    }

    @Override
    public boolean eliminar(Long id) {
        Optional<Producto> opt = repository.findById(id);
        if (opt.isPresent()) { // ✅ CORREGIDO: usar isPresent en vez de != null
            repository.delete(opt.get());
            return true;
        }
        return false;
    }

    @Override
    public Producto buscar(Long id) {
        return repository.findById(id).orElse(null); // ✅ Manejo seguro del Optional
    }

    @Override
    public List<Producto> listar() {
        return repository.findAll();
    }

    @Override
    public Page<Producto> listarPagina(Pageable page) {
        return repository.findAll(page);
    }
}


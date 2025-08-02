package com.idat.semana6.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.idat.semana6.entidad.Producto;

public interface IProductoRepository extends JpaRepository<Producto,Long> {

}

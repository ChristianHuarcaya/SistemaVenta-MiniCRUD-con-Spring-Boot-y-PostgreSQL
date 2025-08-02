package com.idat.semana6.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.idat.semana6.entidad.Persona;

public interface IPersonaRepository extends JpaRepository<Persona,Long> {

}

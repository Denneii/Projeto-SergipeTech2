package com.fleet.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fleet.Entities.Carro;

public interface CarroRepository extends JpaRepository<Carro, Integer> {
}

package com.fleet.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fleet.Entities.Moto;

public interface MotoRepository extends JpaRepository<Moto, Integer> {
}

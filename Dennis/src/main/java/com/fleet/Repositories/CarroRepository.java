package com.fleet.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fleet.Entities.Carro;

@Repository
interface CarroRepository extends JpaRepository<Carro, Long>{}
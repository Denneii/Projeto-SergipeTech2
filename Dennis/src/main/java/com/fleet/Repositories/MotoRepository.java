package com.fleet.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fleet.Entities.Moto;

@Repository
interface MotoRepository extends JpaRepository<Moto, Long>{}

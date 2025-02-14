package com.fleet.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fleet.Entities.Veiculo;

public interface VeiculoRepository extends JpaRepository<Veiculo, Integer> {

    @Query(value = "SELECT * FROM veiculo v WHERE "
            + "(:tipo IS NULL OR v.tipo = :tipo) AND "
            + "(:modelo IS NULL OR v.modelo ILIKE CONCAT('%', :modelo, '%')) AND "
            + "(:cor IS NULL OR v.cor ILIKE CONCAT('%', :cor, '%')) AND "
            + "(:ano IS NULL OR v.ano = :ano)", nativeQuery = true)
    List<Veiculo> filterVeiculos(@Param("tipo") String tipo,
            @Param("modelo") String modelo,
            @Param("cor") String cor,
            @Param("ano") Integer ano);
}

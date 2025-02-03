package com.fleet.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fleet.Entities.Veiculo;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

    // Método genérico para buscar veículos de qualquer tipo com base em parâmetros
    @Query("SELECT v FROM Veiculo v WHERE (:modelo IS NULL OR v.modelo LIKE %:modelo%) " +
            "AND (:ano IS NULL OR v.ano = :ano)")
    List<Veiculo> filtrarVeiculos(String modelo, Integer ano);

    // Método específico para filtrar veículos por tipo (Carro ou Moto)
    @Query("SELECT v FROM Veiculo v WHERE (:tipo IS NULL OR v.dtype = :tipo) " +
            "AND (:modelo IS NULL OR v.modelo LIKE %:modelo%) " +
            "AND (:ano IS NULL OR v.ano = :ano)")
    <T extends Veiculo> List<T> findByTipo(String tipo, String modelo, Integer ano, Class<T> tipoClasse);
}

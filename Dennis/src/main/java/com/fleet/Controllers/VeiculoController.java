package com.fleet.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fleet.Entities.Carro;
import com.fleet.Entities.Moto;
import com.fleet.Entities.Veiculo;
import com.fleet.Repositories.VeiculoRepository;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoRepository veiculoRepository;

    // Método para buscar veículos com base em filtros
    @GetMapping
    public List<Veiculo> buscar(
            @RequestParam(value = "tipo", required = false) String tipo,
            @RequestParam(value = "modelo", required = false) String modelo,
            @RequestParam(value = "ano", required = false) Integer ano
    ) {
        if (tipo != null) {
            if (tipo.equalsIgnoreCase("carro")) {
                return veiculoRepository.findByTipo(tipo, modelo, ano, Carro.class);
            } else if (tipo.equalsIgnoreCase("moto")) {
                return veiculoRepository.findByTipo(tipo, modelo, ano, Moto.class);
            }
        }
        return veiculoRepository.filtrarVeiculos(modelo, ano);
    }

    // Método para buscar um veículo por ID
    @GetMapping("/{id}")
    public Optional<Veiculo> buscarVeiculo(@PathVariable Long id) {
        return veiculoRepository.findById(id);
    }

    // Método para adicionar um veículo
    @PostMapping
    public Veiculo adicionarVeiculo(@RequestBody Veiculo veiculo){
        return veiculoRepository.save(veiculo);
    }

    // Método para atualizar um veículo
    @PutMapping("/{id}")
    public Veiculo atualizarVeiculo(@PathVariable Long id, @RequestBody Veiculo veiculo) {
        veiculo.setId(id);
        return veiculoRepository.save(veiculo);
    }

    // Método para deletar um veículo
    @DeleteMapping("/{id}")
    public void deletarVeiculo(@PathVariable Long id) {
        veiculoRepository.deleteById(id);
    }
}

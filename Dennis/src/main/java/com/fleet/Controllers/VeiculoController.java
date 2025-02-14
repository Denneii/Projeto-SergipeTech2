package com.fleet.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.fleet.Repositories.CarroRepository;
import com.fleet.Repositories.MotoRepository;
import com.fleet.Repositories.VeiculoRepository;

@RestController
@RequestMapping("/api/veiculos")
@CrossOrigin(origins = "*")
public class VeiculoController {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private CarroRepository carroRepository;

    @Autowired
    private MotoRepository motoRepository;

    // Listar todos os veículos
    @GetMapping
    public List<Veiculo> getAllVeiculos() {
        return veiculoRepository.findAll();
    }

    // Buscar veículo por ID
    @GetMapping("/{id}")
    public Veiculo getVeiculoById(@PathVariable Integer id) {
        return veiculoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));
    }

    // Criar veículo (CARRO ou MOTO)
    @PostMapping
    public Veiculo createVeiculo(@RequestBody Veiculo veiculo) {
        if (veiculo instanceof Carro) {
            return carroRepository.save((Carro) veiculo);
        } else if (veiculo instanceof Moto) {
            return motoRepository.save((Moto) veiculo);
        } else {
            throw new RuntimeException("Tipo de veículo não reconhecido");
        }
    }

    // Atualizar veículo existente
    @PutMapping("/{id}")
    public Veiculo updateVeiculo(@PathVariable Integer id, @RequestBody Veiculo updatedVeiculo) {
        Veiculo existing = veiculoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));

        // Atualiza atributos comuns
        existing.setModelo(updatedVeiculo.getModelo());
        existing.setFabricante(updatedVeiculo.getFabricante());
        existing.setAno(updatedVeiculo.getAno());
        existing.setPreco(updatedVeiculo.getPreco());
        existing.setCor(updatedVeiculo.getCor());

        if (existing instanceof Carro && updatedVeiculo instanceof Carro) {
            ((Carro) existing).setQuantidadePortas(((Carro) updatedVeiculo).getQuantidadePortas());
            ((Carro) existing).setTipoCombustivel(((Carro) updatedVeiculo).getTipoCombustivel());
            return carroRepository.save((Carro) existing);
        } else if (existing instanceof Moto && updatedVeiculo instanceof Moto) {
            ((Moto) existing).setCilindrada(((Moto) updatedVeiculo).getCilindrada());
            return motoRepository.save((Moto) existing);
        } else {
            throw new RuntimeException("Tipos de veículo incompatíveis para atualização");
        }
    }

    // Excluir veículo pelo ID
    @DeleteMapping("/{id}")
    public void deleteVeiculo(@PathVariable Integer id) {
        veiculoRepository.deleteById(id);
    }

    // Endpoint para filtrar veículos
    @GetMapping("/filter")
    public List<Veiculo> filterVeiculos(@RequestParam(required = false) String tipo,
            @RequestParam(required = false) String modelo,
            @RequestParam(required = false) String cor,
            @RequestParam(required = false) Integer ano) {
        return veiculoRepository.filterVeiculos(tipo, modelo, cor, ano);
    }
}

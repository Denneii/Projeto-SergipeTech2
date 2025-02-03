const apiUrl = 'http://localhost:8080/veiculos';

function filtrarVeiculos(event) {
    event.preventDefault();

    const tipo = document.getElementById('filtroTipo').value;
    const modelo = document.getElementById('filtroModelo').value;
    const ano = document.getElementById('filtroAno').value;

    const url = new URL(apiUrl);
    if (tipo) url.searchParams.append('tipo', tipo);
    if (modelo) url.searchParams.append('modelo', modelo);
    if (ano) url.searchParams.append('ano', ano);

    fetch(url)
        .then(response => response.json())
        .then(veiculos => {
            const tabela = document.querySelector('#tabelaVeiculos tbody');
            tabela.innerHTML = '';

            veiculos.forEach(veiculo => {
                const tr = document.createElement('tr');
                tr.innerHTML = `
                    <td>${veiculo.id}</td>
                    <td>${veiculo.modelo}</td>
                    <td>${veiculo.fabricante}</td>
                    <td>${veiculo.ano}</td>
                    <td>${veiculo.preco}</td>
                    <td>${veiculo.tipo}</td>
                    <td>
                        <button onclick="editarVeiculo(${veiculo.id})">Editar</button>
                        <button onclick="deletarVeiculo(${veiculo.id})">Deletar</button>
                    </td>
                `;
                tabela.appendChild(tr);
            });
        })
        .catch(error => {
            console.error('Erro ao buscar veículos:', error);
        });
}

function salvarVeiculo(event) {
    event.preventDefault();

    const modelo = document.getElementById('modelo').value;
    const fabricante = document.getElementById('fabricante').value;
    const ano = document.getElementById('ano').value;
    const preco = document.getElementById('preco').value;
    const tipo = document.getElementById('tipo').value;

    const veiculo = {
        modelo,
        fabricante,
        ano: parseInt(ano),
        preco: parseFloat(preco),
        tipo
    };
    console.log(veiculo);
    fetch(apiUrl, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(veiculo)
        })
        .then(response => response.json())
        .then(data => {
            alert('Veículo salvo com sucesso!');
            fecharFormularioCadastro();
            filtrarVeiculos(event);
        })
        .catch(error => {
            console.error('Erro ao salvar veículo:', error);
        });
}

function editarVeiculo(id) {

}

function deletarVeiculo(id) {
    fetch(`${apiUrl}/${id}`, {
            method: 'DELETE'
        })
        .then(() => {
            alert('Veículo deletado com sucesso!');
            filtrarVeiculos(event);
        })
        .catch(error => {
            console.error('Erro ao deletar veículo:', error);
        });
}

function abrirFormularioCadastro() {
    document.getElementById('formularioCadastro').style.display = 'block';
    document.getElementById('btnAdicionar').style.display = 'none';
}

function fecharFormularioCadastro() {
    document.getElementById('formularioCadastro').style.display = 'none';
    document.getElementById('btnAdicionar').style.display = 'block';
}

document.addEventListener('DOMContentLoaded', () => {
    filtrarVeiculos(event);
});
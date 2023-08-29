package br.com.calculadoraimpostoacoes.adapters;

import br.com.calculadoraimpostoacoes.model.Operacao;

public class  CompraAdapter implements OperacaoAdapter {
    private double mediaPonderada;

    public CompraAdapter(double mediaPonderada) {
        this.mediaPonderada = mediaPonderada;
    }

    @Override
    public void executar(Operacao operacao) {
        int quantidadeComprada = operacao.quantity;
        double valorCompra = operacao.unitCost;

        // Calcula a nova média ponderada após a compra
        double novaMediaPonderada = ((quantidadeComprada * mediaPonderada) + (quantidadeComprada * valorCompra)) /
            (quantidadeComprada + quantidadeComprada);

        // Atualiza a média ponderada
        mediaPonderada = novaMediaPonderada;

        System.out.println("Média ponderada atualizada: " + mediaPonderada);
    }
}


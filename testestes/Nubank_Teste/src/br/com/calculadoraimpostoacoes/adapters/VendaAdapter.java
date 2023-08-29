package br.com.calculadoraimpostoacoes.adapters;

import br.com.calculadoraimpostoacoes.model.Operacao;

class VendaAdapter implements OperacaoAdapter {
    private double mediaPonderada;
    private double prejuizo;

    VendaAdapter(double mediaPonderada, double prejuizo) {
        this.mediaPonderada = mediaPonderada;
        this.prejuizo = prejuizo;
    }

    @Override
    public void executar(Operacao operacao) {
        int quantidadeVendida = operacao.quantity;
        double valorVenda = operacao.unitCost;

        if (valorVenda > mediaPonderada) {
            double lucro = (valorVenda - mediaPonderada) * quantidadeVendida;

            if (prejuizo > 0) {
                if (prejuizo >= lucro) {
                    prejuizo -= lucro;
                    lucro = 0;
                } else {
                    lucro -= prejuizo;
                    prejuizo = 0;
                }
            }

            if (lucro > 20000.0) {
                double imposto = lucro * 0.2;
                System.out.println("[{\"tax\":" + String.format("%.2f", imposto) + "}]");
            } else {
                System.out.println("[{\"tax\": 0.00}]");
            }
        } else {
            double perda = (mediaPonderada - valorVenda) * quantidadeVendida;
            prejuizo += perda;

            System.out.println("[{\"tax\": 0.00}]");
        }
    }
}
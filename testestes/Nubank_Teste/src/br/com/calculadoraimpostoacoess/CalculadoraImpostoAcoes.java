package br.com.calculadoraimpostoacoess;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.calculadoraimpostoacoes.adapters.CompraAdapter;
import br.com.calculadoraimpostoacoes.adapters.OperacaoAdapter;
import br.com.calculadoraimpostoacoes.model.Operacao;

public class CalculadoraImpostoAcoes {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double mediaPonderada = 0.0;
        double prejuizo = 0.0;

        while (true) {
            System.out.println("Digite a lista de operações em formato JSON:");
            String input = scanner.nextLine();

            JSONArray operacoesArray = new JSONArray(input);

            for (int i = 0; i < operacoesArray.length(); i++) {
                JSONObject operacaoObj = operacoesArray.getJSONObject(i);
                String operacaoTipo = operacaoObj.getString("operation");
                int quantidade = operacaoObj.getInt("quantity");
                double valor = operacaoObj.getDouble("unit-cost");

                OperacaoAdapter operacaoAdapter;
                if (operacaoTipo.equalsIgnoreCase("buy")) {
                    operacaoAdapter = new CompraAdapter(mediaPonderada);
                } else if (operacaoTipo.equalsIgnoreCase("sell")) {
                    operacaoAdapter = new VendaAdapter(mediaPonderada, prejuizo);
                } else {
                    System.out.println("Operação inválida. Digite buy ou sell.");
                    continue;
                }

                operacaoAdapter.executar(new Operacao(operacaoTipo, valor, quantidade));
            }
        }
    }
}
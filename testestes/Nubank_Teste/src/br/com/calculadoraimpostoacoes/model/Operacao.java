package br.com.calculadoraimpostoacoes.model;

import java.util.Scanner;

public class Operacao {
    public String operation;
    public double unitCost;
    public int quantity;

    Operacao(String operation, double unitCost, int quantity) {
        this.operation = operation;
        this.unitCost = unitCost;
        this.quantity = quantity;
    }

	
}


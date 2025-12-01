package com.feevale;

public abstract class Item {
    private double precoFabrica;
    private String nome;

    public Item(double precoFabrica, String nome){
        this.precoFabrica = precoFabrica;
        this.nome = nome;
    }

    public abstract double calcularPreco();

    public double getPrecoFabrica() {
        return precoFabrica;
    }

    public String getNome() {
        return nome;
    }

    public void setPrecoFabrica(double precoFabrica) {
        this.precoFabrica = precoFabrica;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}

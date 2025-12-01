package com.feevale.modelo;
public class Comida extends Item{
    private int id;
    private String tipoPao, recheio;
    private double precoVenda;

    public Comida(String nome, double precoFabrica, int recheio){//pastéis
        super(precoFabrica, nome);
        this.precoVenda = calcularPreco();
        switch (recheio){
            case 1:
                this.recheio = "Frango";
                break;
            case 2:
                this.recheio = "Carne";
                break;
            case 3:
                this.recheio = "Queijo";
                break;
            case 4:
                this.recheio = "Calabresa";
                break;
            case 5:
                this.recheio = "Palmito";
                break;
            case 6:
                this.recheio = "Quatro Queijos";
                break;
            default:
                System.out.println("Pane no sistema, alguém me desconfigurou.");
        }
    }
    public Comida(String nome, double precoFabrica, int tipoPao, int recheio){//sanduíches
        super(precoFabrica, nome);
        this.precoVenda = calcularPreco();
        switch (tipoPao){
            case 1:
                this.tipoPao = "Branco";
                break;
            case 2:
                this.tipoPao = "Integral";
                break;
            default:
                System.out.println("Pane no sistema, alguém me desconfigurou.");
        }
        switch (recheio){
            case 1:
                this.recheio = "Frango Desfiado";
                break;
            case 2:
                this.recheio = "Atum";
                break;
            case 3:
                this.recheio = "Presunto e Queijo";
                break;
            default:
                System.out.println("Pane no sistema, alguém me desconfigurou.");
        }
    }

    public double calcularPreco(){ //para simular o preço mais alto de lancheria/restaurante
        double a = getPrecoFabrica()*1.15; //nesta classe, "preço de fábrica" seria o preço de todos os ingredientes 
        return a;
    }

    public int getId() {
        return id;
    }

    public double getPrecoVenda() {
        return precoVenda;
    }

    public String getTipoPao() {
        return tipoPao;
    }

    public String getRecheio() {
        return recheio;
    }
}

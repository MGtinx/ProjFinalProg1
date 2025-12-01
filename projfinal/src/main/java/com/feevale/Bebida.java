package com.feevale;

public class Bebida extends Item{
    private int id;
    private String tipo;
    private double precoVenda;

    public Bebida(String nome, double precoFabrica, int tipo){//bebidas
        super(precoFabrica, nome);
        switch (tipo){
            case 1:
                this.tipo = "Coca Lata";
                setPrecoFabrica(3);
                break;
            case 2:
                this.tipo = "Coca 600mL";
                setPrecoFabrica(5);
                break;
            case 3:
                this.tipo = "Guaraná Lata";
                setPrecoFabrica(3);
                break;
            case 4:
                this.tipo = "Guaraná 600mL";
                setPrecoFabrica(5);
                break;
            case 5:
                this.tipo = "Limoneto 500mL";
                setPrecoFabrica(4);
                break;
            case 6:
                this.tipo = "H2OH 500mL";
                setPrecoFabrica(4);
                break;
            case 7:
                this.tipo = "Água 500mL";
                setPrecoFabrica(3);
                break;
            case 8:
                this.tipo = "Caldo de Cano Copo";
                setPrecoFabrica(7);
                break;
            default:
                System.out.println("Pane no sistema, alguém me desconfigurou.");
        }
        this.precoVenda = calcularPreco();
    }

    public double calcularPreco(){ //para simular o preço mais alto de lancheria/restaurante
        double a = getPrecoFabrica()*1.25;
        return a;
    }

    public int getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public double getPrecoVenda() {
        return precoVenda;
    }
}

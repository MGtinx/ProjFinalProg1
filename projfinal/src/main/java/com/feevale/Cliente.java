package com.feevale;
import java.util.Random;

public class Cliente implements Identificador {
    private int id;

    public Cliente(){
        this.id = geradorId();
    }

    public int geradorId(){
        Random random = new Random(); //gerar números entre 100 e 999
        int n = random.nextInt(900) + 100;
        return n;
    }

    public int getId() {
        return id;
    }
}

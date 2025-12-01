package com.feevale;
import java.util.ArrayList;

public class AdminEstabelecimento {
    private int contadorAtendimento = 0;
    private ArrayList<TotemAtendimento> listaAtendimentos = new ArrayList<TotemAtendimento>();
    private ArrayList<TotemAtendimento> atendimentosAtivos = new ArrayList<TotemAtendimento>();

    public void addAtendimento(TotemAtendimento atendimento){
        listaAtendimentos.add(atendimento);
        atendimentosAtivos.add(atendimento);
        setContadorAtendimento(contadorAtendimento + 1);
        System.out.println("Atendimento " + contadorAtendimento + " adicionado. Senha: " + atendimento.getSenha());
    }

    public void avancarStatusAtendimento(int senha) {
        for (int i = 0; i < atendimentosAtivos.size(); i++) {
            TotemAtendimento atendimento = atendimentosAtivos.get(i);
            if (atendimento.getSenha() == senha) {
                String statusAnterior = atendimento.getStatus();
                atendimento.avancarStatus();
                System.out.println("Status do atendimento " + senha + " alterado de '" + statusAnterior + "' para '" + atendimento.getStatus() + "'");
                if (atendimento.getStatus().equals("Entregue")) {
                    atendimentosAtivos.remove(i);
                    System.out.println("Atendimento " + senha + " entregue e removido da lista de ativos.");
                }
                break;
            }
        }
    }

    public void avancarStatusPrimeiro() {
        if (!atendimentosAtivos.isEmpty()) {
            TotemAtendimento primeiro = atendimentosAtivos.get(0); // PRIMEIRO da lista
            avancarStatusAtendimento(primeiro.getSenha());
        } else {
            System.out.println("Nenhum atendimento ativo para avançar status.");
        }
    }

    public TotemAtendimento getAtendimentoAtivo() {
        if (!atendimentosAtivos.isEmpty()) {
            return atendimentosAtivos.get(0);
        }
        return null;
    }

    public ArrayList<TotemAtendimento> getAtendimentosAtivos() {
        return atendimentosAtivos;
    }

    public ArrayList<TotemAtendimento> getListaAtendimentos() {
        return listaAtendimentos;
    }

    public int getContadorAtendimento() {
        return contadorAtendimento;
    }

    public void setContadorAtendimento(int contadorAtendimento) {
        this.contadorAtendimento = contadorAtendimento;
    }
}
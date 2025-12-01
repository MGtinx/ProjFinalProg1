package com.feevale;

import java.util.ArrayList;

public class AdminEstabelecimento {
    private int contadorAtendimento = 0;
    private ArrayList<TotemAtendimento> atendimentos = new ArrayList<TotemAtendimento>();
    private ArrayList<TotemAtendimento> atendimentosAtivos = new ArrayList<TotemAtendimento>();

    public void atualizarTotem(TotemAtendimento totem) {
        // Não precisamos mais do totemAtual único
    }

    public void addAtendimento(TotemAtendimento atendimento){
        // Adiciona à lista geral de atendimentos
        atendimentos.add(atendimento);
        // Adiciona à lista de atendimentos ativos (não entregues)
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
                
                System.out.println("Status do atendimento " + senha + 
                                " alterado de '" + statusAnterior + "' para '" + atendimento.getStatus() + "'");
                
                // Se o status for "Entregue", remove da lista de ativos
                if (atendimento.getStatus().equals("Entregue")) {
                    atendimentosAtivos.remove(i);
                    System.out.println("Atendimento " + senha + " entregue e removido da lista de ativos.");
                }
                break;
            }
        }
    }

    // Avança o status do PRIMEIRO atendimento ativo (mais antigo)
    public void avancarStatusPrimeiroAtendimento() {
        if (!atendimentosAtivos.isEmpty()) {
            TotemAtendimento primeiro = atendimentosAtivos.get(0); // PRIMEIRO da lista
            avancarStatusAtendimento(primeiro.getSenha());
        } else {
            System.out.println("Nenhum atendimento ativo para avançar status.");
        }
    }

    // Método para obter o primeiro atendimento ativo (para exibição)
    public TotemAtendimento getPrimeiroAtendimentoAtivo() {
        if (!atendimentosAtivos.isEmpty()) {
            return atendimentosAtivos.get(0);
        }
        return null;
    }

    // Getter para atendimentos ativos (não entregues)
    public ArrayList<TotemAtendimento> getAtendimentosAtivos() {
        return atendimentosAtivos;
    }

    // Getter para todos os atendimentos
    public ArrayList<TotemAtendimento> getAtendimentos() {
        return atendimentos;
    }

    public int getContadorAtendimento() {
        return contadorAtendimento;
    }

    public void setContadorAtendimento(int contadorAtendimento) {
        this.contadorAtendimento = contadorAtendimento;
    }
}
package com.feevale;

import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;



public class AdminControlador {

    @FXML
    private TextArea painelInfoAtendimentos;

    @FXML
    private Button avancarStatus, trocaParaTotem;

    private AdminEstabelecimento admin = App.getAdmin();

    @FXML
    private void initialize() {
        atualizarPainelInfo();
    }

    @FXML
    private void avancarStatus() {
        TotemAtendimento primeiroAtendimento = admin.getAtendimentoAtivo();
        if (primeiroAtendimento != null) {
            String senha = String.valueOf(primeiroAtendimento.getSenha());
            String statusAnterior = primeiroAtendimento.getStatus();
            
            admin.avancarStatusPrimeiro();
            
            System.out.println("Status do PRIMEIRO atendimento (Senha: " + senha + 
                            ") avançado de '" + statusAnterior + "'");
        } else {
            System.out.println("Nenhum atendimento ativo para avançar status.");
        }
        atualizarPainelInfo();
    }

    private void atualizarPainelInfo() {
        StringBuilder info = new StringBuilder();
        info.append("=== ATENDIMENTOS ATIVOS ===\n");
        ArrayList<TotemAtendimento> atendimentosAtivos = admin.getAtendimentosAtivos();
        if (atendimentosAtivos.isEmpty()) {
            info.append("Nenhum atendimento ativo\n\n");
        } else {
            for (int i = 0; i < atendimentosAtivos.size(); i++) {
                TotemAtendimento atendimento = atendimentosAtivos.get(i);
                if (i == 0) {
                    info.append(">>> PRÓXIMO A PREPARAR <<<\n");
                }
                info.append("Senha: ").append(atendimento.getSenha()).append(" | ");
                info.append("Cliente ID: ").append(atendimento.getCliente().getId()).append(" | ");
                info.append("Status: ").append(atendimento.getStatus()).append(" | ");
                info.append("Valor: R$ ").append(formatarValorTotal(atendimento.getValorTotal())).append("\n");
                for (Item item : atendimento.getCarrinho()) {
                    if (item instanceof Comida) {
                        Comida comida = (Comida) item;
                        if (comida.getTipoPao() == null) {
                            info.append("  - Pastel de ").append(comida.getRecheio()).append("\n");
                        } else {
                            info.append("  - Sanduíche ").append(comida.getRecheio())
                                .append(" (Pão ").append(comida.getTipoPao()).append(")\n");
                        }
                    } else if (item instanceof Bebida) {
                        Bebida bebida = (Bebida) item;
                        info.append("  - ").append(bebida.getTipo()).append("\n");
                    }
                }      
                if (i == 0) {
                    info.append(">>> FIM PRÓXIMO <<<\n");
                }
                info.append("\n");
            }
        }
        info.append("=== HISTÓRICO COMPLETO ===\n");
        ArrayList<TotemAtendimento> todosAtendimentos = admin.getListaAtendimentos();
        if (todosAtendimentos.isEmpty()) {
            info.append("Nenhum atendimento no histórico\n");
        } else {
            int numeroAtendimento = 1;
            for (TotemAtendimento atendimento : todosAtendimentos) {
                info.append(numeroAtendimento).append(". ");
                info.append("Senha: ").append(atendimento.getSenha()).append(" | ");
                info.append("Cliente: ").append(atendimento.getCliente().getId()).append(" | ");
                info.append("Valor: R$ ").append(formatarValorTotal(atendimento.getValorTotal())).append(" | ");
                info.append("Status: ").append(atendimento.getStatus()).append("\n");
                numeroAtendimento++;
            }
        }
        info.append("\n=== ESTATÍSTICAS ===\n");
        info.append("Total de atendimentos: ").append(admin.getContadorAtendimento()).append("\n");
        info.append("Atendimentos ativos: ").append(atendimentosAtivos.size()).append("\n");
        info.append("Próxima senha: ").append(TotemAtendimento.getProximaSenha()).append("\n");
        painelInfoAtendimentos.setText(info.toString());
    }

    private String formatarValorTotal(double valor) {
        return String.format("%.2f", valor).replace('.', ',');
    }

    @FXML
    private void trocaParaTotem() throws IOException {
        App.setRoot("totem");
    }
}
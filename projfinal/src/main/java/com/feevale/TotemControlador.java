package com.feevale;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class TotemControlador {

    @FXML private Button removerUltimo, trocaParaAdmin, confirmar;
    @FXML private Text itensPedido, valorTotal;

    private TotemAtendimento totem = App.getTotemAtual();

    @FXML
    private void initialize() {
        System.out.println("TotemControlador inicializado! Senha atual: " + totem.getSenha());
        atualizarTela();
    }

    // Pastéis
    @FXML
    private void addPastelFrango() { totem.addItemP(1); atualizarTela(); }
    
    @FXML
    private void addPastelCarne() { totem.addItemP(2); atualizarTela(); }
    
    @FXML
    private void addPastelQueijo() { totem.addItemP(3); atualizarTela(); }
    
    @FXML
    private void addPastelCalabresa() { totem.addItemP(4); atualizarTela(); }
    
    @FXML
    private void addPastelPalmito() { totem.addItemP(5); atualizarTela(); }
    
    @FXML
    private void addPastel4Queijos() { totem.addItemP(6); atualizarTela(); }

    // Sanduíches Pão Branco
    @FXML
    private void addSanduicheBrancoFrango() { totem.addItemS(1, 1); atualizarTela(); }
    
    @FXML
    private void addSanduicheBrancoAtum() { totem.addItemS(2, 1); atualizarTela(); }
    
    @FXML
    private void addSanduicheBrancoPresuntoQueijo() { totem.addItemS(3, 1); atualizarTela(); }

    // Sanduíches Pão Integral
    @FXML
    private void addSanduicheIntegralFrango() { totem.addItemS(1, 2); atualizarTela(); }
    
    @FXML
    private void addSanduicheIntegralAtum() { totem.addItemS(2, 2); atualizarTela(); }
    
    @FXML
    private void addSanduicheIntegralPresuntoQueijo() { totem.addItemS(3, 2); atualizarTela(); }

    // Bebidas
    @FXML
    private void addCocaLata() { totem.addItemB(1); atualizarTela(); }
    
    @FXML
    private void addCoca600() { totem.addItemB(2); atualizarTela(); }
    
    @FXML
    private void addGuaranaLata() { totem.addItemB(3); atualizarTela(); }
    
    @FXML
    private void addGuarana600() { totem.addItemB(4); atualizarTela(); }
    
    @FXML
    private void addLimoneto() { totem.addItemB(5); atualizarTela(); }
    
    @FXML
    private void addH2OH() { totem.addItemB(6); atualizarTela(); }
    
    @FXML
    private void addAgua() { totem.addItemB(7); atualizarTela(); }
    
    @FXML
    private void addCaldoCana() { totem.addItemB(8); atualizarTela(); }

    @FXML
    private void removerUltimoItem() { 
        totem.removerItem(); 
        atualizarTela(); 
    }

    @FXML
    private void confirmarPedido() { 
        System.out.println("Confirmando pedido - Senha a ser gerada: " + TotemAtendimento.getProximaSenha());
        
        // Salva a senha antes de finalizar
        int senhaFinal = TotemAtendimento.getProximaSenha();
        totem.finalizarAtendimento(); 
        
        // Mostra a senha do pedido finalizado
        itensPedido.setText("Pedido finalizado! Senha: " + senhaFinal + "\n\nAdicione novos itens para próximo atendimento");
        valorTotal.setText("R$ 0,00");
        
        // Prepara um NOVO atendimento para o próximo cliente
        App.novoAtendimentoNoTotem();
        this.totem = App.getTotemAtual();
        
        System.out.println("Novo atendimento criado. Próxima senha: " + TotemAtendimento.getProximaSenha());
    }

    private void atualizarTela() {
        // Atualizar lista de itens
        StringBuilder itensText = new StringBuilder();
        for (Item item : totem.getCarrinho()) {
            if (item instanceof Comida) {
                Comida comida = (Comida) item;
                if (comida.getTipoPao() == null) {
                    itensText.append("Pastel de ").append(comida.getRecheio()).append("\n");
                } else {
                    itensText.append("Sanduíche ").append(comida.getRecheio())
                             .append(" - Pão ").append(comida.getTipoPao()).append("\n");
                }
            } else if (item instanceof Bebida) {
                Bebida bebida = (Bebida) item;
                itensText.append(bebida.getTipo()).append("\n");
            }
        }
        
        if (itensText.length() == 0) {
            itensText.append("Carrinho vazio");
        }
        
        itensPedido.setText(itensText.toString());

        // Atualizar valor total
        valorTotal.setText("R$ " + formatarValor(totem.getValorTotal()));
    }

    private String formatarValor(double valor) {
        return String.format("%.2f", valor).replace('.', ',');
    }

    @FXML
    private void trocaParaAdmin() throws IOException {
        App.setRoot("admin");
    }
}
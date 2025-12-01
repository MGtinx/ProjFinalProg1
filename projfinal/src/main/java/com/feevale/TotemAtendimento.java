package com.feevale;

import java.util.ArrayList;

public class TotemAtendimento implements Identificador{
    private Cliente cliente;
    private ArrayList<Item> carrinho;
    private double valorTotal;
    private int senha;
    private String status;
    private static int contadorSenha = 1;

    public TotemAtendimento(){
        this.carrinho = new ArrayList<Item>();
        this.cliente = new Cliente();
        this.valorTotal = 0;
        this.senha = 0;
        this.status = "Em preparo"; 
    }

    public void addItemP(int recheio) {
        Comida pastel = new Comida(null, 10, recheio);
        carrinho.add(pastel);
        setValorTotal(valorTotal + pastel.getPrecoVenda());
        notificarAdmin();
    }

    public void addItemB(int tipo) {
        Bebida bebida = new Bebida(null, 1, tipo);
        carrinho.add(bebida);
        setValorTotal(valorTotal + bebida.getPrecoVenda());
        notificarAdmin();
    }

    public void addItemS(int recheio, int tipoPao) {
        Comida sanduiche = new Comida(null, 8, tipoPao, recheio);
        carrinho.add(sanduiche);
        setValorTotal(valorTotal + sanduiche.getPrecoVenda());
        notificarAdmin();
    }

    public void removerItem(){
        if (!carrinho.isEmpty()) {
            Item removido = carrinho.remove(carrinho.size() - 1);
            setValorTotal(valorTotal - removido.calcularPreco());
            notificarAdmin();
        }
    }

    public double calcularTotal(){
        double total = 0;
        for (Item i : carrinho){
            total = total + i.calcularPreco();
        }
        return total;
    }
    
    public int geradorId(){
        // Usa o contador de atendimentos + 1 como senha
        return App.getAdmin().getContadorAtendimento() + 1;
    }

    private void notificarAdmin() {
        AdminEstabelecimento admin = App.getAdmin();
        admin.atualizarTotem(this);
    }
    
    public void avancarStatus() {
    switch (status) {
        case "Em preparo":
            setStatus("Pronto");
            break;
        case "Pronto":
            setStatus("Entregue");
            break;
        case "Entregue":
            // Não faz nada, já está no status final
            break;
    }
}

    public void finalizarAtendimento(){
        if (carrinho.isEmpty()) {
            System.out.println("Nenhum item.");
            return;
        }
        
        // Gera senha sequencial única
        this.senha = contadorSenha;
        contadorSenha++;
        
        notificarAdmin();
        App.getAdmin().addAtendimento(this);
        
        // NÃO limpa o atendimento atual - ele será mantido na lista de ativos
        // O totem criará um novo atendimento para o próximo cliente
    }

    // Método para criar um NOVO atendimento (usado pelo totem)
    public static TotemAtendimento criarNovoAtendimento() {
        return new TotemAtendimento();
    }

    // Getters e Setters
    public int getSenha() {
        return senha;
    }

    public void setSenha(int senha) {
        this.senha = senha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public ArrayList<Item> getCarrinho() {
        return carrinho;
    }

    public void setStatus(String novoStatus) {
        this.status = novoStatus;
        notificarAdmin();
    }

    public String getStatus() {
        return status;
    }

    public static int getProximaSenha() {
        return contadorSenha;
    }

    public void setCarrinho(ArrayList<Item> carrinho) {
        this.carrinho = carrinho;
        }
}
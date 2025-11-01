package model;

import util.Tela;

import java.util.ArrayList;
import java.util.List;

public class Inventario implements Cloneable,java.io.Serializable{
    private List<Item> itens;

    public Inventario(){
    //array list é um tipo de vetor do java, como se fosse uma lista do python aonde
    // você pode adicionar e remover da lista sem se preocupar em ficar falando o espaço do vetor
        this.itens  = new ArrayList<>();
    }

    //construtor de copia
    public Inventario(Inventario original){
        this.itens = new ArrayList<>();

        //para cada item do inventario original
        for(Item itemOriginal : original.itens){

            Item copiaDoItem = new Item(itemOriginal);

            this.itens.add(copiaDoItem);
        }
    }

    public void adicionarItem(Item itemParaAdicionar) throws Exception {
        for (Item itemExistente : this.itens) {
            if (itemExistente.equals(itemParaAdicionar)) {
                int novaQuantidade = itemExistente.getQuantidade() + itemParaAdicionar.getQuantidade();
                itemExistente.setQuantidade((short) novaQuantidade);
                return;
            }
        }
        //caso ele nao passe no loop, significa q é um item novo e nao um item repetido
        this.itens.add(itemParaAdicionar);
    }

    public boolean removerItem(String nomeDoItem, int quantidadeParaRemover) throws Exception {

        for(int i = 0; i < this.itens.size(); i++){
            Item itemExistente = this.itens.get(i); // pega o item no indice 'i'

            //verifica se o item do indice 'i' é o item q queremos remover
            if(itemExistente.getNome().equalsIgnoreCase(nomeDoItem)){

                int novaQuantidade = itemExistente.getQuantidade() - quantidadeParaRemover;

                if (novaQuantidade <= 0){
                    //se for menor ou igual a zero significa q o item acabou 'usamos todas as poçoes e estamos sem agora
                    this.itens.remove(i);
                }else{
                    //caso nao apenas diminui a qtd msm
                    itemExistente.setQuantidade((short)novaQuantidade);
                }

                //encontrou o item e removeu ele
                return true;
            }
        }
        //se chegar aqui é pq nao tem aquele item no inventario
        return false;
    }

    public List<Item> getItensUsaveisEmBatalha() {
        List<Item> itensUsaveis = new ArrayList<>();
        for (Item item : this.itens) {
            String efeito = item.getEfeito();
            if (efeito.equals("CURA") || efeito.equals("DANO_X") || efeito.equals("DEFESA_X")) {
                itensUsaveis.add(item);
            }
        }
        return itensUsaveis; // Retorna uma NOVA lista apenas com os itens filtrados
    }

    @Override
    public Inventario clone(){
        return new Inventario(this);
    }

    @Override
    public String toString() {
        // 1. Chama o seu algoritmo de ordenação (bubble sort)
        // que você já tem em 'listarItens'.
        int n = this.itens.size();
        for (int i = 0; i < n-1; i++){
            for (int j = 0; j < n-1; j++){
                Item itemAtual = this.itens.get(j);
                Item itemProximo = this.itens.get(j + 1);

                if(itemAtual.compareTo(itemProximo) > 0){
                    this.itens.set(j, itemProximo);
                    this.itens.set(j + 1, itemAtual);
                }
            }
        }

        // 2. Constrói a String
        if (this.itens.isEmpty()) {
            return "Inventario [Vazio]";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Inventario [\n");
        for (Item item : this.itens) {
            sb.append("  - ").append(item.getNome()).append(" (x").append(item.getQuantidade()).append(")\n");
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        Inventario outro = (Inventario) obj;

        // Se o número de tipos de itens for diferente, os inventários são diferentes
        if (this.itens.size() != outro.itens.size()) return false;

        // Ordena ambas as listas para comparar

        int n = this.itens.size();
        for (int i = 0; i < n-1; i++){ // Ordena this.itens
            for (int j = 0; j < n-1; j++){
                if(this.itens.get(j).compareTo(this.itens.get(j+1)) > 0){
                    Item temp = this.itens.get(j);
                    this.itens.set(j, this.itens.get(j+1));
                    this.itens.set(j+1, temp);
                }
            }
        }

        for (int i = 0; i < n-1; i++){ // Ordena outro.itens
            for (int j = 0; j < n-1; j++){
                if(outro.itens.get(j).compareTo(outro.itens.get(j+1)) > 0){
                    Item temp = outro.itens.get(j);
                    outro.itens.set(j, outro.itens.get(j+1));
                    outro.itens.set(j+1, temp);
                }
            }
        }

        // Compara item por item (nome e quantidade)
        for (int i = 0; i < this.itens.size(); i++) {
            Item thisItem = this.itens.get(i);
            Item outroItem = outro.itens.get(i);

            // Compara o item (usando Item.equals, que checa nome e efeito)
            if (!thisItem.equals(outroItem)) return false;

            // Compara a quantidade
            if (thisItem.getQuantidade() != outroItem.getQuantidade()) return false;
        }
        // Se passou por tudo, são iguais
        return true;
    }

    @Override
    public int hashCode() {
        //numero maligno
        int ret = 666;
        // Para um hashCode consistente com o equals,
        // precisamos ordenar a lista primeiro.
        int n = this.itens.size();
        for (int i = 0; i < n-1; i++){
            for (int j = 0; j < n-1; j++){
                if(this.itens.get(j).compareTo(this.itens.get(j+1)) > 0){
                    Item temp = this.itens.get(j);
                    this.itens.set(j, this.itens.get(j+1));
                    this.itens.set(j+1, temp);
                }
            }
        }

        // Agora calcula o hash com base nos itens ordenados
        for (Item item : this.itens) {
            ret = 31 * ret + item.hashCode(); // Usa o hash do item (nome+efeito)
            ret = 31 * ret + item.getQuantidade(); // Adiciona a quantidade
        }

        if (ret < 0) ret = -ret;
        return ret;
    }

}

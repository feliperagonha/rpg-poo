import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Inventario {
    private List<Item> itens;

    public Inventario(){
    //array list é um tipo de vetor do java, como se fosse uma lista do python aonde
    // você pode adicionar e remover da lista sem se preocupar em ficar falando o espaço do vetor
        this.itens  = new ArrayList<>();
    }

    public void adicionarItem(Item itemParaAdicionar) {
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

    public void listarItens(){
        if(this.itens.isEmpty()){
            System.out.println("O inventario está vazio.");
            return;
        }

        /*Collections.sort(this.itens); Perguntar para o professor se pode usar isso
        basicamente o metodo collections.sort ele pega o primeiro item e ver se tem o implements
        comparable, se sim ele chama o compareTo e verifica: se der um num negativo = o primeiro item vem antes
        do proximo, se for positivo = o primeiro vem dps, se for 0 é pq sao iguais para a ordenação*/

        //algoritmo de ordenação
        int n = this.itens.size(); //pega qnts itens tem na lista

        for (int i = 0; i < n-1; i++){
            for (int j = 0; j < n-1; j++){
                Item itemAtual = this.itens.get(j);
                Item itemProximo = this.itens.get(j + 1);

                if(itemAtual.compareTo(itemProximo) > 0){
                    //significa q o atual vai na frente do proximo
                    this.itens.set(j, itemProximo); //joga o proximo pra tras aonde tava  o atual
                    this.itens.set(j + 1, itemAtual); // joga o atual pra frente, aonde tava o proximo
                }
            }
        }

        System.out.println("---Inventario---");
        for (Item item : this.itens){
            System.out.println("- " + item.getNome() + " (x" + item.getQuantidade() + ")");
        }
        System.out.println("-----------------");
    }
}

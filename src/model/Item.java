package model;

public class Item implements Comparable<Item>{
    private String nome;
    private String descricao;
    private String efeito;
    private short quantidade;

    public Item(String nome, String descricao, String efeito, short quantidade){
        this.nome = nome;
        this.descricao = descricao;
        this.efeito = efeito;
        this.quantidade = quantidade;
    }

    public Item(Item original){
        this.nome = original.nome;
        this.descricao = original.descricao;
        this.efeito = original.efeito;
        this.quantidade = original.quantidade;
    }


    public String getNome() {return this.nome;}
    public String getDescricao() {return this.descricao;}
    public String getEfeito() {return this.efeito;}
    public short getQuantidade() {return this.quantidade;}

    public void setNome(String nome){this.nome = nome;}
    public void setDescricao(String descricao){this.descricao = descricao;}
    public void setEfeito(String efeito){this.efeito = efeito;}
    public void setQuantidade(short quantidade){this.quantidade = quantidade;}

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (obj.getClass() != this.getClass()) return false;
    
        Item outroItem = (Item) obj;
        
        return this.nome.equalsIgnoreCase(outroItem.nome) &&
               this.efeito.equalsIgnoreCase(outroItem.efeito);
    }


    @Override
    public int hashCode() {
        int ret = 666; 
        ret = ret * 31 + (nome != null ? nome.hashCode() : 0);
        ret = ret * 31 + (efeito != null ? efeito.hashCode() : 0);
        return ret;
}

    @Override
    public int compareTo(Item outroItem) {
        return this.nome.compareToIgnoreCase(outroItem.nome);
    }


    @Override
    public String toString(){
        return String.format("model.Item{\nNome= '%s'\nQuantidade = %d\nDescrição = '%s'\nEfeito = '%s'}",
                getNome(),
                getQuantidade(),
                getDescricao(),
                getEfeito());
    }
}

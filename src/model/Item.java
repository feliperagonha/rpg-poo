package model;

public class Item implements Comparable<Item>,Cloneable,java.io.Serializable    {
    private String nome;
    private String descricao;
    private String efeito;
    private short quantidade;
    private short potencia;

    public Item(String nome, String descricao, String efeito, short quantidade,short potencia) throws Exception {
        if(nome == null || nome.trim().isEmpty()) throw new Exception("Nome do item não pode ser nulo ou vazio");
        if (efeito == null || efeito.trim().isEmpty()) throw new Exception("Efeito do item não pode ser nulo ou vazio");
        if (quantidade < 1) throw new Exception("Quantidade do item deve ser positiva");
        if (potencia < 1) throw new Exception("Potência do item deve ser positiva");
        this.nome = nome;
        this.descricao = descricao;
        this.efeito = efeito;
        this.quantidade = quantidade;
        this.potencia = potencia;
    }

    public Item(Item original){
        this.nome = original.nome;
        this.descricao = original.descricao;
        this.efeito = original.efeito;
        this.quantidade = original.quantidade;
        this.potencia = original.potencia;
    }


    public String getNome() {return this.nome;}
    public String getDescricao() {return this.descricao;}
    public String getEfeito() {return this.efeito;}
    public short getQuantidade() {return this.quantidade;}
    public int getPotencia() { return this.potencia; }


    // --- SETTERS (com o novo setter) ---
    public void setNome(String nome) throws Exception {
        if (nome == null || nome.trim().isEmpty()) throw new Exception("Nome do item não pode ser nulo ou vazio");
        this.nome = nome;
    }
    public void setDescricao(String descricao) { this.descricao = descricao; } // Descrição pode ser vazia

    public void setEfeito(String efeito) throws Exception {
        if (efeito == null || efeito.trim().isEmpty()) throw new Exception("Efeito do item não pode ser nulo ou vazio");
        this.efeito = efeito;
    }

    public void setQuantidade(short quantidade) throws Exception {
        if (quantidade < 0) throw new Exception("Quantidade não pode ser negativa"); // 0 é ok se remover
        this.quantidade = quantidade;
    }

    public void setPotencia(short potencia) throws Exception {
        if (potencia < 1) throw new Exception("Potência do item deve ser positiva"); // <-- VALIDAÇÃO
        this.potencia = potencia;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (obj.getClass() != this.getClass()) return false;
    
        Item outroItem = (Item) obj;
        
        return this.nome.equalsIgnoreCase(outroItem.nome) &&
                this.efeito.equalsIgnoreCase(outroItem.efeito)&&
                this.potencia == outroItem.potencia;
    }


    @Override
    public int hashCode() {
        int ret = 666; 
        ret = ret * 31 + (nome != null ? nome.hashCode() : 0);
        ret = ret * 31 + (efeito != null ? efeito.hashCode() : 0);
        ret = ret * 31 + this.potencia;
        if (ret < 0) ret = -ret;
        return ret;
    }

    @Override
    public int compareTo(Item outroItem) {
        // Ordena por nome, e se o nome for igual, ordena por potência
        int comparacaoNome = this.nome.compareToIgnoreCase(outroItem.nome);
        if (comparacaoNome != 0) {
            return comparacaoNome;
        }
        // Se os nomes são iguais, desempata pela potência
        return Integer.compare(this.potencia, outroItem.potencia);
    }

    @Override
    public Object clone() {
        try {
            return new Item(this); // Chama o construtor de cópia
        } catch (Exception e) {
            return null; // Não deve acontecer
        }
    }

    @Override
    public String toString() {
        return String.format("Item{\n  Nome= '%s'\n  Quantidade = %d\n  Descrição = '%s'\n  Efeito = '%s' (Potência: %d)}",
                getNome(), getQuantidade(), getDescricao(), getEfeito(), getPotencia());
    }
}

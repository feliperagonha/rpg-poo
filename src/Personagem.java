
/*
 A classe personagem é uma classe abstract impedindo que nao seja possivel ter uma
 instancia direto dessa classe [ex: new Personagem();], sendo possivel instacia apenas as
 subclasse berseker,oraculo,cacador.
 */

import java.util.Objects;

public abstract class Personagem implements Cloneable {
    private String nome;
    private int pontosVida;
    private int pontosVidaMax;
    private int ataque;
    private int defesa;
    private byte nivel;
    private Inventario inventario;

    // Construtor normal
    public Personagem(String nome, int pontosVida, int ataque, int defesa,byte nivel) throws Exception
    {
        if(nome   ==  null) throw new Exception("Nome Ausente");
        if(pontosVida  < 1) throw new Exception("Vida inválida");
        if(ataque      < 0) throw new Exception("Ataque inválido");
        if(defesa      < 0) throw new Exception("Defesa inválida");
        if(nivel       < 1) throw new Exception("Nível Inválido");
        this.nome = nome;
        this.pontosVidaMax = pontosVida;
        this.pontosVida = pontosVida;
        this.ataque = ataque;
        this.defesa = defesa;
        this.nivel = nivel;
        this.inventario = new Inventario();
    }

    // Construtor de cópia
    public Personagem(Personagem outro)  throws Exception
    {
        if (outro == null){ throw new Exception("Personagem para cópia ausente"); }
        this.nome          = outro.nome;
        this.pontosVida    = outro.pontosVida;
        this.pontosVidaMax = outro.pontosVidaMax;
        this.ataque        = outro.ataque;
        this.defesa        = outro.defesa;
        this.nivel         = outro.nivel;
        this.inventario    = outro.inventario.clone();
    }

    // Getters 
    public String getNome()           {return nome;}
    public int getPontosVida()        {return pontosVida;}
    public int getPontosVidaMax()     {return pontosVidaMax;}
    public int getAtaque()            {return ataque;}
    public int getDefesa()            {return defesa;}
    public byte getNivel()           {return nivel;}
    public Inventario getInventario() {return inventario;}

    // Setters
    public void setPontosVida(int pontosVida){this.pontosVida = pontosVida;}
    public void setPontosVidaMax(int pontosVidaMax) throws Exception
    {
        if (pontosVidaMax < 1) {
            throw new Exception("Vida máxima deve ser maior que zero");
        }
        this.pontosVidaMax = pontosVidaMax;
    }
    
    public void setNome(String nome) throws Exception
    {
        if (nome == null) { throw new Exception("Nome ausente");}
        this.nome = nome;
    }

    public void setAtaque(int ataque) throws Exception
    {
        if (ataque < 0) { throw new Exception("Ataque inválido");}
        this.ataque = ataque;
    }

    public void setDefesa(int defesa) throws Exception 
    {
        if (defesa < 0) { throw new Exception("Defesa inválido");}
        this.defesa = defesa;
    }

    public void setNivel(byte nivel) throws Exception 
    {
        if (nivel < 1) { throw new Exception("Nível inválido");}
        this.nivel = nivel;
    }

    public void setInventario(Inventario inventario) throws Exception 
    {
        if (inventario == null) throw new Exception("Inventário ausente");
        this.inventario = inventario.clone(); // Deep copy
    }

    // Método abstrato que cada subclasse implementará
    public abstract String habilidadeEspecial();

    public void mostrarInfos()
    {
        System.out.printf("Nome: '%s'\nHP: %d/%d\nAtaque: %d\nDefesa: %d\nNível: %d\n",
            getNome(), getPontosVida(), getPontosVidaMax(), getAtaque(), getDefesa(), getNivel());
    }

    public boolean estaVivo(){
        return this.getPontosVida() > 0;
    }

    // Método Dano do Personagem
    public void receberDano(int dano) throws Exception {
        if(dano < 0) throw new Exception("Dano inválido");
        int danoReal = Math.max(0, dano - this.defesa); // Dano - Defesa
        this.pontosVida -= danoReal;
        if (this.pontosVida < 0) {
            this.pontosVida = 0;
        }
    }
    // Método Curar
    public void curar(int quantidade) throws Exception {
        if (quantidade < 0) throw new Exception("Quantidade de cura inválida.");
        if (quantidade > 0) {
            this.pontosVida += quantidade;
            if (this.pontosVida > this.pontosVidaMax) { // validação se a cura der mais que a vida maxima
                this.pontosVida = this.pontosVidaMax;
            }
        }
    }

    @Override
    public boolean equals(Object obj) 
    {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Personagem outro = (Personagem) obj;
        return nivel  == outro.nivel  &&
               ataque == outro.ataque &&
               defesa == outro.defesa &&
               Objects.equals(nome, outro.nome);
    }

    @Override
    public int hashCode()
    {
        int ret = 666;
        ret = ret * 31 + nome.hashCode(); // Proteção 
        ret = ret * 31 + ataque;
        ret = ret * 31 + defesa;
        ret = ret * 31 + nivel;
        return ret;
    }

    @Override
    public String toString(){
        return String.format("nome: '%s', HP: %d/%d, ataque = %d, defesa = %d, nivel = %d",
        nome, pontosVida, pontosVidaMax, ataque, defesa, nivel);
    }

    @Override
    public Personagem clone() {
        try {
            Personagem clonado = (Personagem) super.clone();
            clonado.inventario = this.inventario.clone(); // DEEP COPY
            return clonado;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Erro ao clonar personagem", e);
        }
}
}




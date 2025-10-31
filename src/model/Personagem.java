package model;
/*
 A classe personagem é uma classe abstract impedindo que nao seja possivel ter uma
 instancia direto dessa classe [ex: new model.Personagem();], sendo possivel instacia apenas as
 subclasse berseker,oraculo,cacador.
 */

import java.util.Objects;

public abstract class Personagem implements Cloneable,java.io.Serializable {
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


    public String habilidadeEspecial(Personagem alvo) throws Exception {
        return this.getNome() + " não possui uma habilidade especial ativa.";
    }
    //sobrecarga
    public String habilidadeEspecial(int escolha, Personagem alvo) throws Exception {
        return this.habilidadeEspecial(alvo);
    }

    public boolean aplicarPassivaDeAtaque(Personagem alvo) throws Exception {
        return false; // Por padrão, personagens não têm passiva
    }

    public void mostrarInfos()
    {
        System.out.println(this);
    }

    public boolean estaVivo(){
        return this.getPontosVida() > 0;
    }

    // Método Dano do model.Personagem
    public void receberDano(int dano) throws Exception {
        if(dano < 0) throw new Exception("Dano inválido");
        int danoReal = Math.max(0, dano - this.defesa); // Dano - Defesa
        this.pontosVida -= danoReal;
        if (this.pontosVida < 0) {
            this.pontosVida = 0;
        }
    }

    // Método para dano direto que IGNORA a defesa (usado em armadilhas/eventos)
    public void receberDanoDireto(int dano) throws Exception {
        if(dano < 0) throw new Exception("Dano inválido");
        this.pontosVida -= dano;
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
        ret = ret * 31 + Objects.hashCode(nome);
        ret = ret * 31 + ataque;
        ret = ret * 31 + defesa;
        ret = ret * 31 + nivel;
        return ret;
    }

    @Override
    public String toString(){
        // 1. Pega o status base
        String statusBase = String.format("Nome: '%s'\nHP: %d/%d\nAtaque: %d\nDefesa: %d\nNível: %d",
                this.nome, this.pontosVida, this.pontosVidaMax, this.ataque, this.defesa, this.nivel);

        // 2. Pede ao inventário a sua String (CHAMA O INVENTARIO.TOSTRING())
        String statusInventario = this.inventario.toString();

        // 3. Retorna a combinação dos dois
        return statusBase + "\n" + statusInventario;
    }

    @Override
    public Object clone() {
        try {
            Personagem clonado = (Personagem) super.clone();
            clonado.inventario = this.inventario.clone(); // DEEP COPY
            return clonado;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Erro ao clonar personagem", e);
        }
}
}




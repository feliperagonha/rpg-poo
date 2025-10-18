
/*
 A classe personagem é uma classe abstract impedindo que nao seja possivel ter uma
 instancia direto dessa classe [ex: new Personagem();], sendo possivel instacia apenas as
 subclasse berseker,oraculo,cacador.
 */
public abstract class Personagem {
    protected String nome;
    protected byte pontosVida;
    protected int ataque;
    protected int defesa;
    protected short nivel;
    protected Inventario inventario;

    public Personagem(String nome, byte pontosVida, int ataque, int defesa,short nivel){
        this.nome = nome;
        this.pontosVida = pontosVida;
        this.ataque = ataque;
        this.defesa = defesa;
        this.nivel = nivel;
        this.inventario = new Inventario(); // cada personagem vai ter o seu proprio inventario
    }
}



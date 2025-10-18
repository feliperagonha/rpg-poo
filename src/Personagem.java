
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

    public void mostrarInfos(){
        System.out.printf("nome: '%s'\npontosDeVida = %d\nataque = %d\ndefesa = %d\nnivel = %d",nome,pontosVida,ataque,defesa,nivel);;
    };


    @Override
    public String toString(){
        return String.format("nome: '%s', pontosDeVida = %d, ataque = %d, defesa = %d, nivel = %d",nome,pontosVida,ataque,defesa,nivel);
    }
}



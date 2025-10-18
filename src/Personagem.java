
/*
 A classe personagem é uma classe abstract impedindo que nao seja possivel ter uma
 instancia direto dessa classe [ex: new Personagem();], sendo possivel instacia apenas as
 subclasse berseker,oraculo,cacador.
 */
public abstract class Personagem {
    private String nome;
    private int pontosVida;
    private int ataque;
    private int defesa;
    private short nivel;
    private Inventario inventario;

    public Personagem(String nome, byte pontosVida, int ataque, int defesa,short nivel){
        this.setNome(nome);
        this.setPontosVida(pontosVida);
        this.setAtaque(ataque);
        this.setDefesa(defesa);
        this.setNivel(nivel);
        this.setInventario();
    }

    public String getNome() {
        return nome;
    }

    public byte getPontosVida() {
        return pontosVida;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getDefesa() {
        return defesa;
    }

    public short getNivel() {
        return nivel;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public void setPontosVida(byte pontosVida) {
        this.pontosVida = pontosVida;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public void setDefesa(int defesa) {
        this.defesa = defesa;
    }

    public void setNivel(short nivel) {
        this.nivel = nivel;
    }

    public void setInventario() {
        this.inventario = new Inventario();
    }

    public void mostrarInfos(){
        System.out.printf("nome: '%s'\npontosDeVida = %d\nataque = %d\ndefesa = %d\nnivel = %d",getNome(),getPontosVida(),getAtaque(),getDefesa(),getNivel());;
    };

    @Override
    public String toString(){
        return String.format("nome: '%s', pontosDeVida = %d, ataque = %d, defesa = %d, nivel = %d",nome,pontosVida,ataque,defesa,nivel);
    }
}



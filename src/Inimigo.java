//Lista de Inimigos (Atributos)
//
// Draugr (Inimigo Ato 1):
// - pontosVida: 40
// - ataque: 8
// - defesa: 5
//
// Serpente Marinha (Inimigo Ato 2):
// - pontosVida: 100
// - ataque: 12
// - defesa: 8
//
// Jarl, Líder dos Lobos de Gelo (Chefe Final):
// - pontosVida: 200
// - Ataque: 15
// - Defesa: 10
//
// 


public class Inimigo extends Personagem {
    
    public Inimigo(String nome, int pontosVida, int ataque, int defesa, byte nivel) throws Exception {
        super(nome, pontosVida, ataque, defesa, nivel);
    }
    
    // Construtor de cópia
    public Inimigo(Inimigo outro) throws Exception {
        super(outro);
    }
    
    // Habilidade do Inimigo
    @Override
    public String habilidadeEspecial() {
        int bonusAtaque = 3;
        try {
            this.setAtaque(this.getAtaque() + bonusAtaque);
            return this.getNome() + " usa ATAQUE FEROZ! Ataque +" + bonusAtaque;
        } catch (Exception e) {
            return "Erro ao ativar habilidade!";
        }
    }
    
    // MÉTODOS AUXILIARES PARA CRIAR INIMIGOS DE CADA ATO
    
    public static Inimigo criarDraugr() throws Exception {
        return new Inimigo("Draugr", 40, 8, 5, (byte)1);
    }

    public static Inimigo criarSerpenteMarinha() throws Exception {
        return new Inimigo("Serpente Marinha", 100, 12, 8, (byte)2);
    }

    public static Inimigo criarJarl() throws Exception {
        Inimigo jarl = new Inimigo("Jarl dos Lobos de Gelo", 200, 15, 10, (byte)3);
        return jarl;
    }
}
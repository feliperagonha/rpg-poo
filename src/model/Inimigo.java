package model;//Lista de Inimigos (Atributos)
//
// Draugr (model.Inimigo Ato 1):
// - pontosVida: 40
// - ataque: 8
// - defesa: 5
//
// Serpente Marinha (model.Inimigo Ato 2):
// - pontosVida: 100
// - ataque: 11
// - defesa: 8
//
// Jarl, Líder dos Lobos de Gelo (Chefe Final):
// - pontosVida: 150
// - Ataque: 14
// - Defesa: 9
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
    
    // Habilidade do model.Inimigo
    @Override
    public String habilidadeEspecial(Personagem alvo) {
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
        Inimigo jarl = new Inimigo("Jarl dos Lobos de Gelo", 150, 14, 9, (byte)3);
        return jarl;
    }

    @Override
    public Object clone() {
        // 1. Chama o clone da classe mãe (Personagem.clone())
        // O Personagem.clone() JÁ FAZ A CÓPIA PROFUNDA do inventário.

        Inimigo clonado = (Inimigo) super.clone();

            /* 2. A classe Inimigo não tem atributos próprios (como 'pontosMagia').
                Todos os atributos (hp, ataque) são primitivos
                e já foram copiados pelo 'super.clone()'.
                Então, não precisamos fazer mais nada.*/
        return clonado;
    }

    @Override
    public boolean equals(Object obj) {
        // 1. Chama o equals da classe mãe (Personagem.equals)
        // Isso vai checar nome, nível, ataque, defesa, etc.
        if (!super.equals(obj)) {
            return false;
        }

        // 2. Se o super.equals passou, checa se são da mesma classe.
        // Isso impede que um Inimigo seja 'equals' a um Cacador.
        if(this.getClass() != obj.getClass()) {
            return false;
        }

        // 3. Como Inimigo não tem atributos próprios (além dos que
        // já estão em Personagem), não precisamos comparar mais nada.

        return true;
    }

    @Override
    public int hashCode() {
        // 1. Pega o hashCode da classe mãe (baseado em nome, stats, etc.)
        int ret = super.hashCode();

        // 2. Adiciona o "DNA" desta classe específica.
        // Usamos um número primo (ex: 29) para diferenciar de um 'Cacador'.
        ret = 29 * ret + "Inimigo".hashCode(); // Identificador da classe

        if (ret < 0) ret = -ret;
        return ret;
    }

    @Override
    public String toString() {
        // 1. Pega a string base da classe mãe (Personagem.toString)
        String stringDaClasseMae = super.toString();

        // 2. Adiciona a informação desta classe
        return "CLASSE: Inimigo\n" + stringDaClasseMae;
    }
}
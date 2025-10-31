package model;// Baseada na classe Guerreiro
// Características da classe:
// - HP: 150 (o mais alto entre as classes)
// - Ataque: 20 (o mais alto entre as classes)
// - Defesa: 5 (a mais baixa entre as classes)
// - Nível inicial: 1
// 
// Habilidade Especial: Fúria Selvagem
// - Aumenta temporariamente o ataque em 50%
// - Reduz a defesa em 2 pontos durante a fúria
import util.Tela;
public class Berserker extends Personagem{

    public Berserker() throws Exception {
        super("Berserker", 150, 20, 5, (byte)1);
    }
    
    // construtor de cópia
    public Berserker(Berserker outro) throws Exception {
        super(outro);
    }

    @Override
    public String habilidadeEspecial(Personagem alvo) throws Exception {
        int bonusAtaque = (int)(this.getAtaque() * 0.5);
        try {
            this.setAtaque(this.getAtaque() + bonusAtaque);
            this.setDefesa(Math.max(0, this.getDefesa() - 2));
            return this.getNome() + " ativa FÚRIA SELVAGEM! Ataque +" + bonusAtaque + ", Defesa -2";
        } catch (Exception e) {
            return "Erro ao ativar habilidade!";
        }
    }
    @Override
    public boolean aplicarPassivaDeAtaque(Personagem alvo) throws Exception {
        // "QUANDO"
        if (this.getPontosVida() < (this.getPontosVidaMax() * 0.5)) {

            // "O QUÊ" (Reutilizando seu método existente)
            String narracao = this.habilidadeEspecial(alvo);
            Tela.narrar(narracao); // A própria classe narra

            return true; // Sim, ativou
        }
        return false; // Não ativou
    }

    @Override
    public Object clone() {
            // 1. Chama o clone da classe mãe (Personagem.clone())
            // O Personagem.clone() JÁ FAZ A CÓPIA PROFUNDA do inventário.

            Berserker clonado = (Berserker) super.clone();

            /* 2. A classe Berserker não tem atributos próprios (como 'pontosMagia').
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
        // Isso impede que um Berserker seja 'equals' a um Cacador.
        if(this.getClass() != obj.getClass()) {
            return false;
        }

        // 3. Como Berserker não tem atributos próprios (além dos que
        // já estão em Personagem), não precisamos comparar mais nada.

        return true;
    }

    @Override
    public int hashCode() {
        // 1. Pega o hashCode da classe mãe (baseado em nome, stats, etc.)
        int ret = super.hashCode();

        // 2. Adiciona o "DNA" desta classe específica.
        // Usamos um número primo (ex: 29) para diferenciar de um 'Cacador'.
        ret = 29 * ret + "Berserker".hashCode(); // Identificador da classe

        if (ret < 0) ret = -ret;
        return ret;
    }

    @Override
    public String toString() {
        // 1. Pega a string base da classe mãe (Personagem.toString)
        String stringDaClasseMae = super.toString();

        // 2. Adiciona a informação desta classe
        return "CLASSE: Berserker\n" + stringDaClasseMae;
    }
}

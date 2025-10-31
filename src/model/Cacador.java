package model;// Baseado na classe ranged(arqueiro,atirador, etc...)
// Características da classe:
// - HP: 100
// - Ataque: 10
// - Defesa: 10
// - Nível inicial: 1
// 
// Habilidade especial do Oráculo: Tiro Preciso
// - Dobra o ataque temporariamente

import util.Dado;
import util.Tela;

public class Cacador extends Personagem{

    public Cacador() throws Exception {
        super("Caçador", 100, 10, 10, (byte)1);
    }
    
    // Construtor de cópia
    public Cacador(Cacador outro) throws Exception {
        super(outro);
    }
    
    @Override
    public String habilidadeEspecial(Personagem alvo) {
        int bonusAtaque = this.getAtaque();
        try {
            this.setAtaque(this.getAtaque() + bonusAtaque);
            return this.getNome() + " ativa TIRO PRECISO! Ataque dobrado para " + this.getAtaque();
        } catch (Exception e) {
            return "Erro ao ativar habilidade!";
        }
    }

    @Override
    public boolean aplicarPassivaDeAtaque(Personagem alvo) throws Exception {
        // "QUANDO"
        if (Dado.rolar(100) <= 25) {

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
        Cacador clonado = (Cacador) super.clone();

        // 2. A classe Cacador não tem atributos próprios (como 'pontosMagia')
        //Todos os atributos (hp, ataque) são primitivos
        // e já foram copiados pelo 'super.clone()'.
        // Então, não precisamos fazer mais nada.

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
        // Isso impede que um Cacador seja 'equals' a um Berserker.
        if (this.getClass() != obj.getClass()) {
            return false;
        }

        // 3. Como Cacador não tem atributos próprios (além dos que
        // já estão em Personagem), não precisamos comparar mais nada.

        return true;
    }

    @Override
    public int hashCode() {
        // 1. Pega o hashCode da classe mãe (baseado em nome, stats, etc.)
        int ret = super.hashCode();

        // 2. Adiciona o "DNA" desta classe específica.
        // Usamos um número primo (ex: 29) para diferenciar de um 'Berseker'.
        ret = 29 * ret + "Cacador".hashCode(); // Identificador da classe

        if (ret < 0) ret = -ret;
        return ret;
    }

    @Override
    public String toString() {
        // 1. Pega a string base da classe mãe (Personagem.toString)
        String stringDaClasseMae = super.toString();

        // 2. Adiciona a informação desta classe
        return "CLASSE: Cacador\n" + stringDaClasseMae;
    }
}
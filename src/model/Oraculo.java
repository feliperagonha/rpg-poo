package model;

// Baseada na classe Mago
// Características da classe:
// - HP: 80
// - Ataque: 5 
// - Defesa: 15 
// - Nível inicial: 1
// 
// Habilidade especial do Oráculo: Visão Rúnica
// - Aumenta defesa em 10 e regenera 20 HP
public class Oraculo extends Personagem{
    public Oraculo() throws Exception {
        super("Oráculo", 80, 5, 15, (byte)1);
    }
    
    // Construtor de cópia 
    public Oraculo(Oraculo outro) throws Exception {
        super(outro);
    }

    @Override
    public String habilidadeEspecial(Personagem alvo) { // Adiciona 'alvo'
        return "O Oráculo precisa escolher qual habilidade usar no sub-menu.";
    }

    @Override
    public String habilidadeEspecial(int escolha, Personagem alvo) throws Exception {
        switch (escolha) {
            case 1: // Curar (não precisa de alvo)
                if (this.getPontosVida() >= this.getPontosVidaMax()) {
                    return this.getNome() + " já está com a vida máxima!";
                }
                this.curar(25);
                return this.getNome() + " usa a runa de CURA! (HP +25 -> " + this.getPontosVida() + "/" + this.getPontosVidaMax() + ")";
            case 2: // Amaldiçoar (PRECISA de alvo)
                if (alvo == null) {
                    return this.getNome() + " tentou amaldiçoar, mas não havia alvo!";
                }
                if (alvo.getDefesa() <= 0) {
                    return alvo.getNome() + " já está com a defesa mínima! A maldição falha.";
                }
                this.amaldicoar(alvo);
                return this.getNome() + " usa a runa de AMALDIÇOAR sobre " + alvo.getNome() + "! (Defesa Reduzida para " + alvo.getDefesa() + ")";
            default:
                return this.getNome() + " decide não usar uma habilidade.";
        }
    }

    public void amaldicoar(Personagem alvo) throws Exception {
        int reducaoDefesa = 5;
        int novaDefesa = Math.max(0, alvo.getDefesa() - reducaoDefesa);
        alvo.setDefesa(novaDefesa);
    }

    @Override
    public Object clone() {
        // 1. Chama o clone da classe mãe (Personagem.clone())
        // O Personagem.clone() JÁ FAZ A CÓPIA PROFUNDA do inventário.
        Oraculo clonado = (Oraculo) super.clone();

        // 2. A classe Oraculo não tem atributos próprios (como 'pontosMagia')
        // Todos os atributos (hp, ataque) são primitivos
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
        // Isso impede que um Oraculo seja 'equals' a um Cacador.
        if (this.getClass() != obj.getClass()) {
            return false;
        }

        // 3. Como Oraculo não tem atributos próprios (além dos que
        // já estão em Personagem), não precisamos comparar mais nada.

        return true;
    }

    @Override
    public int hashCode() {
        // 1. Pega o hashCode da classe mãe (baseado em nome, stats, etc.)
        int ret = super.hashCode();

        // 2. Adiciona o "DNA" desta classe específica.
        // Usamos um número primo (ex: 29) para diferenciar de um 'Cacador'.
        ret = 29 * ret + "Oraculo".hashCode(); // Identificador da classe

        if (ret < 0) ret = -ret;
        return ret;
    }

    @Override
    public String toString() {
        // 1. Pega a string base da classe mãe (Personagem.toString)
        String stringDaClasseMae = super.toString();

        // 2. Adiciona a informação desta classe
        return "CLASSE: Oraculo\n" + stringDaClasseMae;
    }

}
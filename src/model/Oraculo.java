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
    public void amaldicoar(Personagem alvo) throws Exception
    {
        int reducaoDefesa = 5;
        int novaDefesa = Math.max(0, alvo.getDefesa() - reducaoDefesa);
        alvo.setDefesa(novaDefesa);
    }


}
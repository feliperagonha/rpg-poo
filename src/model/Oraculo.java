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
    public String habilidadeEspecial() {
        return "O Oráculo precisa escolher qual habilidade usar no sub-menu.";
    }

    @Override
    public String habilidadeEspecial(int escolha) throws Exception {
        switch (escolha) {
            case 1: // Curar
                this.curar(25);
                return this.getNome() + " usa a runa de CURA! (HP +25)";
            case 2: // Amaldiçoar
                // [LÓGICA DA MALDIÇÃO PENDENTE]
                return this.getNome() + " usa a runa de AMALDIÇOAR! (Defesa do model.Inimigo Reduzida)";
            default:
                return this.getNome() + " decide não usar uma habilidade.";
        }
    }
}
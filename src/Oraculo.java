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
        try {
            this.setDefesa(this.getDefesa() + 10);
            this.curar(20);
            return this.getNome() + " ativa VISÃO RÚNICA! Defesa +10, HP +20";
        } catch (Exception e) {
            return "Erro ao ativar habilidade!";
        }
    }
}
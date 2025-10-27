// Baseado na classe ranged(arqueiro,atirador, etc...)
// Características da classe:
// - HP: 100
// - Ataque: 10
// - Defesa: 10
// - Nível inicial: 1
// 
// Habilidade especial do Oráculo: Tiro Preciso
// - Dobra o ataque temporariamente

public class Cacador extends Personagem{

    public Cacador() throws Exception {
        super("Caçador", 100, 10, 10, (byte)1);
    }
    
    // Construtor de cópia
    public Cacador(Cacador outro) throws Exception {
        super(outro);
    }
    
    @Override
    public String habilidadeEspecial() {
        int bonusAtaque = this.getAtaque();
        try {
            this.setAtaque(this.getAtaque() + bonusAtaque);
            return this.getNome() + " ativa TIRO PRECISO! Ataque dobrado para " + this.getAtaque();
        } catch (Exception e) {
            return "Erro ao ativar habilidade!";
        }
    }
}
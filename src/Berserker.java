// Baseada na classe Guerreiro
// Características da classe:
// - HP: 150 (o mais alto entre as classes)
// - Ataque: 20 (o mais alto entre as classes)
// - Defesa: 5 (a mais baixa entre as classes)
// - Nível inicial: 1
// 
// Habilidade Especial: Fúria Selvagem
// - Aumenta temporariamente o ataque em 50%
// - Reduz a defesa em 2 pontos durante a fúria

public class Berserker extends Personagem {

    public Berserker() throws Exception {
        super("Berserker", 150, 20, 5, (byte)1);
    }
    
    // construtor de cópia
    public Berserker(Berserker outro) throws Exception {
        super(outro);
    }
    
    @Override
    public String habilidadeEspecial() {
        int bonusAtaque = (int)(this.getAtaque() * 0.5);
        try {
            this.setAtaque(this.getAtaque() + bonusAtaque);
            this.setDefesa(Math.max(0, this.getDefesa() - 2));
            return this.getNome() + " ativa FÚRIA SELVAGEM! Ataque +" + bonusAtaque + ", Defesa -2";
        } catch (Exception e) {
            return "Erro ao ativar habilidade!";
        }
    }
}

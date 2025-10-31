package game;

public class Main{
    public static void main(String[] args) {
        try {
            
            Jogo jogo = new Jogo();
            jogo.iniciar();
            
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
}
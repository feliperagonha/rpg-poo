package game;

public class Main{
    public static void main(String[] args) {
        try {
            // Configura UTF-8 no Windows
            System.setProperty("file.encoding", "UTF-8");
            System.setProperty("console.encoding", "UTF-8");
            
            // Para Windows, força UTF-8
            if (System.getProperty("os.name").toLowerCase().contains("windows")) {
                System.out.println("\033[1mConfigurando emojis...\033[0m");
            }
            
            Jogo jogo = new Jogo();
            jogo.iniciar();
            
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
}
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.sound.sampled.*;

public class Tela {
    private static final int VELOCIDADE_TEXTO = 30;
    private static Clip somDeDigitacao;

    private static void carregarSom() {
        try {
            File arquivoDeSom = new File("som_texto.wav");
            if (arquivoDeSom.exists()) {
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(arquivoDeSom);
                somDeDigitacao = AudioSystem.getClip();
                somDeDigitacao.open(audioStream);
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.err.println("Aviso: Nao foi possivel carregar o efeito sonoro de texto.");
            somDeDigitacao = null;
        }
    }

    private static void imprimirComEfeito(String texto) {
        if (somDeDigitacao == null) {
            carregarSom();
        }

        for (char letra : texto.toCharArray()) {
            System.out.print(letra);
            System.out.flush();

            if (somDeDigitacao != null) {
                somDeDigitacao.setFramePosition(0);
                somDeDigitacao.start();
            }

            try {
                Thread.sleep(VELOCIDADE_TEXTO);
            } catch (InterruptedException e) {
                System.out.print(texto.substring(texto.indexOf(letra)));
                break;
            }
        }
        System.out.println();
    }

    public static void limparTela() {
        try {
            String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (final Exception e) {

        }
    }

    private static void esperarEnter(){
        System.out.println("\n[Pressione enter para continuar...]");
        Teclado.getUmString();
        limparTela();
    }

    private static List<String> lerArquivo(String nomeArquivo) {
        List<String> linhas = new ArrayList<>();
        try (BufferedReader arquivo = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = arquivo.readLine()) != null) {
                linhas.add(linha);
            }
        } catch (IOException e) {
            System.err.println("Erro: Não foi possível ler o arquivo '" + nomeArquivo + "'. Verifique se ele está na pasta raiz do projeto.");
        }
        return linhas;
    }

    public static void imprimirHistoria() {
        List<String> linhas = lerArquivo("historia.txt");
        int contadorDeLinhas = 0;

        if (linhas.isEmpty()) {
            return;
        }

        for (String linha : linhas) {
            imprimirComEfeito(linha);
            contadorDeLinhas++;

            if (contadorDeLinhas % 3 == 0 && contadorDeLinhas < linhas.size()) {
                esperarEnter();
            }
        }
    }

    public void narrar(String texto){
        imprimirComEfeito(texto);
    }

    public static void imprimirMenuPrincipal() {
        System.out.println("=== A Runa Despertada ===");
        System.out.println("1. Novo Jogo");
        System.out.println("2. Carregar Jogo");
        System.out.println("3. Sair");
    }

    public static void imprimirStatus(Personagem personagem) {
        System.out.println("--- STATUS ---");
        personagem.mostrarInfos();
        System.out.println("--------------");
    }

    public static void imprimirStatusBatalha(Personagem jogador, Personagem inimigo) {
        System.out.println("----------------------------------------");
        System.out.printf("| %s: %d HP    vs    %s: %d HP |\n",
                jogador.getNome(), jogador.getPontosVida(),
                inimigo.getNome(), inimigo.getPontosVida());
        System.out.println("----------------------------------------");
    }

    public static void imprimirAcaoDeCombate(String acao) {
        imprimirComEfeito(acao);
    }

    public static void imprimirMapa() {
        System.out.println("--- Mapa ---");
        System.out.println("(Floresta) -- (Vila) -- (Porto)");
        System.out.println("                  |");
        System.out.println("               (Mar)");
    }

}

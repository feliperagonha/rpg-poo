package util;

import model.Oraculo;
import model.Personagem;

import java.io.*;
import java.nio.charset.StandardCharsets;

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

    public static void esperarEnter(){
        System.out.println("\n[Pressione enter para continuar...]");
        Teclado.getUmString();
        limparTela();
    }

    private static List<String> lerArquivo(String nomeArquivo) {
        List<String> linhas = new ArrayList<>();
        try (BufferedReader arquivo = new BufferedReader(new InputStreamReader(
                new FileInputStream(nomeArquivo),
                StandardCharsets.UTF_8
                )
            )) {
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
        limparTela();
        List<String> linhas = lerArquivo("historia/intro.txt");
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
    public static void imprimirArquivoTxt(String caminhoDoArquivo) {

        List<String> linhas = lerArquivo(caminhoDoArquivo);

        if (linhas.isEmpty()) {
            return; // Arquivo não encontrado ou vazio
        }

        for (String linha : linhas) {
            if (linha.equals("---")) {
                esperarEnter();
            } else {
                imprimirComEfeito(linha);
            }
        }
    }
    public static  void narrar(String texto){
        imprimirComEfeito(texto);
    }

    public static void imprimirMenuPrincipal() {
        limparTela();
        System.out.println("=== A Runa Despertada ===");
        System.out.println("1. Novo Jogo"             );
        System.out.println("2. Carregar Jogo"         );
        System.out.println("3. Sair"                  );
    }

    public static void imprimirStatus(Personagem personagem) {
        System.out.println("--- STATUS ---");
        personagem.mostrarInfos();
        System.out.println("--------------");
    }

    public static void imprimirStatusBatalha(Personagem jogador, Personagem inimigo) {
        System.out.println("----------------------------------");
        // Formato: | NOME: HP/HPMax (ATK:X|DEF:X) vs NOME: HP/HPMax (ATK:X|DEF:X) |
        System.out.printf("| %s: %d|%d HP  vs  %s: %d|%d HP |\n" +
                        "(ATK:%d|DEF:%d)      (ATK:%d|DEF:%d)",

                // Variáveis da Linha 1
                jogador.getNome(),          // 1. %s
                jogador.getPontosVida(),    // 2. %d
                jogador.getPontosVidaMax(), // 3. %d
                inimigo.getNome(),          // 4. %s (CORRIGIDO)
                inimigo.getPontosVida(),    // 5. %d (CORRIGIDO)
                inimigo.getPontosVidaMax(), // 6. %d (CORRIGIDO)

                // Variáveis da Linha 2
                jogador.getAtaque(),        // 7. %d (CORRIGIDO)
                jogador.getDefesa(),        // 8. %d (CORRIGIDO)
                inimigo.getAtaque(),        // 9. %d (CORRIGIDO)
                inimigo.getDefesa()         // 10. %d (CORRIGIDO)
        );
        // Você não tem um \n no final, então vou adicionar um
        System.out.println();
        System.out.println("----------------------------------");
    }


    public static void imprimirMenuBatalha(Personagem jogador) { // Recebe o jogador
        System.out.println("--- O que você faz? ---");
        System.out.println("1. Ataque Básico"       );

        if (jogador.getClass() == Oraculo.class) { // Usando getClass()
            System.out.println("2. Usar Habilidade");
            System.out.println("3. Usar Item");
        } else {
            System.out.println("2. Usar Item");
        }
        System.out.println("------------------------------");
    }
    //o oraculo é o unico q tem um sub menu so pra ele pq ele é o unico q tem habilidade ativa
    public static void imprimirMenuOraculo()
    {
        System.out.println("--- Habilidades de Oráculo ---");

        System.out.println("1. Habilidade: Curar");
        System.out.println("2. Habilidade: Amaldiçoar");
        System.out.println("3. Voltar");
        System.out.println("------------------------------");
    }
}

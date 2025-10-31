package game;

//imports para o save/load
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.File;

import model.*;
import util.*;
import java.util.List;
import java.util.ArrayList;


public class Jogo{
    private Personagem jogador;
    private Personagem savePoint = null;
    private int ataqueOriginal = 0;
    private int defesaOriginal = 0;
    private boolean buffAtivo = false;

    public void iniciar(){
        exibirMenuPrincipal();
    }

    private void exibirMenuPrincipal(){
        int opc = -1;
        while(opc != 3){
            Tela.imprimirMenuPrincipal();
            try{
                Tela.narrar("Digite sua escolha:");
                opc = Teclado.getUmInt();

                switch (opc){
                    case 1:
                        novoJogo();
                        break;

                    case 2:
                        carregarJogo();
                        opc = 3;
                        break;
                    case 3:
                        Tela.narrar("Saindo do jogo... Até a proxima!");
                        break;
                    default:
                        Tela.narrar("Opcao invalida");
                        Tela.esperarEnter();
                        break;
                }

            }catch (Exception e){
                Tela.narrar("Erro: Entrada invalida. Por favor digite um numero");
                Teclado.getUmString(); //pra limpar o buffer
                Tela.esperarEnter();
            }
        }
    }

    private void novoJogo() throws Exception{
        Tela.imprimirHistoria();
        Tela.esperarEnter();

        Tela.imprimirArquivoTxt("historia/intro_pedra.txt");
        Tela.esperarEnter();
        this.jogador = criarPersonagem();

        salvarCheckpoint();

        Tela.narrar("\nPersonagem criado com sucesso!");
        Tela.imprimirStatus(this.jogador);
        Tela.esperarEnter();
        iniciarJornada();
    }

    private void salvarCheckpoint() {
        try {
            // 1. CHECKPOINT (EM MEMÓRIA ram) -
            this.savePoint = (Personagem) this.jogador.clone();

            // 2. SAVE (EM DISCO hd) - Salva o clone em um arquivo
            try (FileOutputStream fos = new FileOutputStream("save.dat");
                 ObjectOutputStream oos = new ObjectOutputStream(fos))
            {
                oos.writeObject(this.savePoint);
            }

            Tela.narrar("\n...Checkpoint salvo! (Início do Ato " + this.jogador.getNivel() + ")");
            Tela.esperarEnter();

        } catch (Exception e) {
            Tela.narrar("\n[ERRO] Não foi possível salvar o checkpoint: " + e.getMessage());
            Tela.esperarEnter();
        }
    }

    private void carregarJogo() throws Exception {
        File saveFile = new File("save.dat");
        if (!saveFile.exists()) {
            Tela.narrar("Nenhum jogo salvo encontrado!");
            Tela.esperarEnter();
            return; // Volta ao menu principal
        }

        try (FileInputStream fis = new FileInputStream(saveFile);
             ObjectInputStream ois = new ObjectInputStream(fis))
        {
            this.savePoint = (Personagem) ois.readObject();
            this.jogador = (Personagem) this.savePoint.clone();

            Tela.narrar("Jogo carregado com sucesso!");
            Tela.imprimirStatus(this.jogador);
            Tela.esperarEnter();

            iniciarJornada();

        } catch (Exception e) {
            Tela.narrar("\n[ERRO] Não foi possível carregar o jogo: " + e.getMessage());
            Tela.esperarEnter();
        }
    }

    private Personagem criarPersonagem() throws Exception {
    Tela.limparTela();
    Tela.imprimirArquivoTxt("historia/criacao_personagem.txt");

    int escolhaClasse = -1;
    Personagem personagemEscolhido = null;

    // Loop para escolha da classe
    while (personagemEscolhido == null) {
        Tela.narrar("Escolha sua classe (1-3):");
        try {
            escolhaClasse = Teclado.getUmInt();
            switch (escolhaClasse) {
                case 1:
                    personagemEscolhido = new Berserker();
                    break;
                case 2:
                    personagemEscolhido = new Oraculo();
                    break;
                case 3:
                    personagemEscolhido = new Cacador();
                    break;
                default: Tela.narrar("Classe inválida!"); break;
            }
        } catch (Exception e) {
            Tela.narrar("Erro: Entrada inválida."); Teclado.getUmString();
        }
    }
    Tela.narrar("Você escolheu ser um " + personagemEscolhido.getNome() + "!");

    // Loop para escolha do nome
    String nomeEscolhido = "";
    while (nomeEscolhido.trim().isEmpty()) {
        Tela.narrar("\nComo você se chama?");
        nomeEscolhido = Teclado.getUmString();
        if (nomeEscolhido.trim().isEmpty()) {
            Tela.narrar("O nome não pode ser vazio.");
        }
    }
    // Define o nome escolhido no personagem
    personagemEscolhido.setNome(nomeEscolhido);

    return personagemEscolhido;
}

    private void iniciarJornada() throws Exception {
        // Usa o nível do jogador (que foi carregado) para pular ao Ato correto
        switch (this.jogador.getNivel()) {
            case 1:
                ato1();
                break;
            case 2:
                ato2();
                break;
            case 3:
                ato3();
                break;
            default: // Segurança
                Tela.narrar("Erro no save, iniciando do Ato 1.");
                this.jogador.setNivel((byte)1);
                ato1();
                break;
        }
    }

    private void avancarParaAto(byte proximoAto) throws Exception {
        this.jogador.setNivel(proximoAto);
        salvarCheckpoint();

        if (proximoAto == 2) {
            ato2();
        } else if (proximoAto == 3) {
            ato3();
        }
    }

    // ========== ATO I: O INVERNO SEM FIM ==========
    private void ato1() throws Exception {
        this.jogador.setNivel((byte)1);
        salvarCheckpoint();

        Tela.limparTela();
        Tela.imprimirArquivoTxt("historia/ato1/inicio.txt");
        Tela.esperarEnter();


        decisaoAto1();
    }

    private void decisaoAto1() throws Exception {
        int escolha = -1;
        while (escolha != 1 && escolha != 2) {
            Tela.limparTela();
            Tela.imprimirArquivoTxt("historia/ato1/decisao_menu.txt");

            try {
                Tela.narrar("Sua escolha:");
                escolha = Teclado.getUmInt();

                if (escolha == 1) {
                    caminhoSeguroAto1();
                } else if (escolha == 2) {
                    caminhoArriscadoAto1();
                } else {
                    Tela.narrar("Opção inválida!");
                    Tela.esperarEnter();
                }
            } catch (Exception e) {
                Tela.narrar("Entrada inválida!");
                Teclado.getUmString();
                Tela.esperarEnter();
            }
        }
    }

    // CAMINHO 1: Atracar na ilha (SEGURO - batalha direta)
    private void caminhoSeguroAto1() throws Exception {
        Tela.limparTela();
        Tela.imprimirArquivoTxt("historia/ato1/caminho_seguro.txt");
        Tela.esperarEnter();

        Inimigo draugr = new Inimigo("Draugr", 40, 8, 5, (byte)1);
        batalhar(draugr);

        if (jogador.estaVivo()) {
            darRecompensaBatalha(); // da a recompensa
            // tentar implementar soma do nivel do usuário, aumenta o nivel, dano e defesa em 20%
            Tela.narrar("Você derrotou os Draugr e segue viagem...\n");
            avancarParaAto((byte)2);//atualiza o nivel e avanca para o proximo ato
        }
    }

    private void caminhoArriscadoAto1() throws Exception {
        Tela.limparTela();
        Tela.imprimirArquivoTxt("historia/ato1/caminho_arriscado_intro.txt");
        Tela.esperarEnter();

        Tela.imprimirArquivoTxt("historia/ato1/caminho_arriscado_perigo.txt");
        Tela.esperarEnter();

        int escolhaArmadilha = -1;
        while (escolhaArmadilha != 1 && escolhaArmadilha != 2) {
            Tela.imprimirArquivoTxt("historia/ato1/armadilha_decisao_menu.txt");

            try {
                Tela.narrar("Sua escolha:");
                escolhaArmadilha = Teclado.getUmInt();

                if (escolhaArmadilha == 1) {
                    // ========== OPÇÃO 1: TENTAR PASSAR PELOS MORCEGOS ==========
                    Tela.narrar("\n🎲 Rolando dados...");
                    int resultado = Dado.rolar(20);
                    Tela.narrar("Você tirou: " + resultado + " (necessário 11+ para se livrar dos morcegos)\n");
                    Tela.esperarEnter();

                    if (resultado >= 11) {
                        // SUCESSO - dano leve e recompensas
                        Tela.imprimirArquivoTxt("historia/ato1/armadilha_sucesso.txt");
                        darRecompensaArmadilha(); // DÁ 2 POÇÕES ALEATÓRIAS

                        int dano = 15;
                        int defesaAtual = jogador.getDefesa();
                        jogador.receberDano(dano + defesaAtual);

                        Tela.narrar("Você sofreu " + dano + " de dano pelas mordidas!");
                        Tela.narrar("HP atual: " + jogador.getPontosVida() + "/" + jogador.getPontosVidaMax() + "\n");
                        Tela.esperarEnter();

                        if (jogador.estaVivo()) {
                            Tela.narrar("Você atravessa a caverna e sai do outro lado!\n");
                            Tela.esperarEnter();
                            ato2();
                        }

                    } else {
                        // FALHA - dano pesado
                        Tela.imprimirArquivoTxt("historia/ato1/armadilha_falha.txt");

                        int dano = 30;
                        int defesaAtual = jogador.getDefesa();
                        jogador.receberDano(dano + defesaAtual);

                        Tela.narrar("Você sofreu " + dano + " de dano!");
                        Tela.narrar("HP atual: " + jogador.getPontosVida() + "/" + jogador.getPontosVidaMax() + "\n");
                        Tela.esperarEnter();

                        if (!jogador.estaVivo()) {
                            Tela.narrar("💀 Você foi devorado pelos morcegos... FIM DE JOGO");
                            return;
                        }

                        Tela.narrar("Gravemente ferido, você cambaleia para fora da caverna.");
                        Tela.narrar("Você precisa se recuperar antes de continuar...\n");
                        Tela.esperarEnter();
                        ato2();
                    }

                } else if (escolhaArmadilha == 2) {
                    // ========== OPÇÃO 2: FUGIR (COM CHANCE DE FALHA) ==========
                    Tela.narrar("\n🎲 Você escolheu fugir, mas há chance de estar cercado pelos morcegos!");
                    Tela.narrar("Rolando dados...");
                    int resultadoFuga = Dado.rolar(20);
                    Tela.narrar("Você tirou: " + resultadoFuga + " (necessário 11+ para fugir com sucesso)\n");
                    Tela.esperarEnter();

                    if (resultadoFuga >= 11) {
                        // SUCESSO NA FUGA - volta sem dano
                        Tela.narrar("✅ SUCESSO! Você consegue recuar com segurança!");
                        Tela.narrar("Com prudência, você sai da caverna e decide atracar na praia.\n");
                        Tela.esperarEnter();
                        caminhoSeguroAto1(); // Volta para o caminho seguro

                    } else {
                        // FALHA NA FUGA - é cercado e sofre dano máximo
                        Tela.narrar("❌ FALHA! Os morcegos te cercaram antes que você pudesse fugir!");
                        Tela.imprimirArquivoTxt("historia/ato1/armadilha_falha.txt");

                        int dano = 40; // ✅ Dano alto mas não mata (era getPontosVidaMax)
                        int defesaAtual = jogador.getDefesa();
                        jogador.receberDano(dano + defesaAtual);

                        Tela.narrar("Você sofreu " + dano + " de dano!");
                        Tela.narrar("HP atual: " + jogador.getPontosVida() + "/" + jogador.getPontosVidaMax() + "\n");
                        Tela.esperarEnter();

                        if (!jogador.estaVivo()) {
                            Tela.narrar("💀 Você foi devorado pelos morcegos... FIM DE JOGO");
                            return;
                        }

                        Tela.narrar("Gravemente ferido, você cambaleia para fora da caverna.");
                        Tela.narrar("Você precisa se recuperar antes de continuar...\n");
                        Tela.esperarEnter();
                        ato2();
                    }

                } else {
                    Tela.narrar("Opção inválida!");
                    Tela.esperarEnter();
                }
            } catch (Exception e) {
                Tela.narrar("Entrada inválida!");
                Teclado.getUmString();
                Tela.esperarEnter();
            }
        }
    }
    


    // ========== ATO II: O MAR DAS SERPENTES ==========
    private void ato2() throws Exception {
        Tela.limparTela();
        Tela.imprimirArquivoTxt("historia/ato2/inicio.txt");
        salvarCheckpoint();
        decisaoAto2();
    }

    private void decisaoAto2() throws Exception {
        int escolha = -1;
        while (escolha != 1 && escolha != 2) {
            Tela.limparTela();
            Tela.imprimirArquivoTxt("historia/ato2/decisao_menu.txt");

            try {
                Tela.narrar("Sua escolha:");
                escolha = Teclado.getUmInt();

                if (escolha == 1) {
                    caminhoArriscadoAto2();
                } else if (escolha == 2) {
                    caminhoSeguroAto2();
                } else {
                    Tela.narrar("Opção inválida!");
                    Tela.esperarEnter();
                }
            } catch (Exception e) {
                Tela.narrar("Entrada inválida!");
                Teclado.getUmString();
                Tela.esperarEnter();
            }
        }
    }

    // CAMINHO 1: Investigar destroços (ARRISCADO - batalha + recompensa)
    private void caminhoArriscadoAto2() throws Exception {
        Tela.limparTela();
        Tela.imprimirArquivoTxt("historia/ato2/caminho_arriscado.txt");
        Tela.esperarEnter();

        Inimigo serpenteMarinha = new Inimigo("Serpente Marinha", 100, 12, 8, (byte)2);
        batalhar(serpenteMarinha);

        if (jogador.estaVivo()) {
            Tela.narrar("Após derrotar a serpente, você explora os destroços.");
            darRecompensaBatalha();


            Tela.narrar("Você segue em frente...\n");
            avancarParaAto((byte)3);//atualiza o nivel e avanca para o proximo ato
        }
    }



    // CAMINHO 2: Contornar a área (SEGURO - penalidade leve)
    private void caminhoSeguroAto2() throws Exception {
        Tela.limparTela();
        Tela.imprimirArquivoTxt("historia/ato2/caminho_seguro.txt");

        int danoExaustao = 15;
        jogador.receberDanoDireto(danoExaustao);
        Tela.narrar("Você sofreu " + danoExaustao + " de dano pela exaustão!");
        Tela.narrar("HP atual: " + jogador.getPontosVida() + "/" + jogador.getPontosVidaMax() + "\n");
        Tela.esperarEnter();

        if (!jogador.estaVivo()) {
            Tela.narrar("💀 A exaustão foi demais... FIM DE JOGO");
            return;
        }

        Tela.narrar("A viagem se torna mais demorada, mas vocês seguem...\n");
        avancarParaAto((byte)3);//atualiza o nivel e avanca para o proximo ato
    }

    // ========== ATO III: A CHEGADA EM THULE ==========
    private void ato3() throws Exception {
        Tela.limparTela();
        Tela.imprimirArquivoTxt("historia/ato3/inicio.txt");
        salvarCheckpoint();
        enfrentarBossFinal();
    }

    private void enfrentarBossFinal() throws Exception {
        Tela.limparTela();
        Tela.imprimirArquivoTxt("historia/ato3/batalha_final_intro.txt");
        Tela.esperarEnter();

        // Inimigo jarl = new Inimigo("Jarl, Líder dos Lobos de Gelo", 200, 15, 10, (byte)5);
        Inimigo jarl = new Inimigo("Jarl, Líder dos Lobos de Gelo", 150, 13, 9, (byte)5);
        batalhar(jarl);



        if (jogador.estaVivo()) {
            // VITÓRIA
            Tela.limparTela();
            Tela.imprimirArquivoTxt("historia/ato3/final_vitoria.txt");
        } else {
            // DERROTA
            Tela.limparTela();
            Tela.imprimirArquivoTxt("historia/ato3/final_derrota.txt");
        }
    }



    public boolean batalhar(Inimigo inimigo) throws Exception {
        salvarStatsOriginais();
        Tela.narrar("Um " + inimigo.getNome() + " selvagem aparece!");
        while (this.jogador.estaVivo() && inimigo.estaVivo()) {
            Tela.imprimirStatusBatalha(this.jogador, inimigo);
            Tela.imprimirMenuBatalha(this.jogador);

            int escolha = -1;
            try {
                escolha = Teclado.getUmInt();
            } catch (Exception e) {
                Tela.narrar("Opção inválida! Tente novamente.");
                continue;
            }


            boolean turnoGasto = true;


            int escolhaMapeada = escolha;
            if (this.jogador.getClass() != Oraculo.class && escolha > 1) {
                escolhaMapeada = escolha + 1; // 2->3 (Usar Item)
            }



            switch (escolhaMapeada) {
                case 1: // Ataque Básico
                    Tela.narrar("Você decide usar um ataque básico!");
                    // Este método JÁ FAZ o jogador atacar E o inimigo contra-atacar
                    tratarTurnoAtaquePadrao(inimigo);
                    // Turno foi gasto
                    break;

                case 2: // Usar Habilidade (Só Oráculo)
                    if (this.jogador.getClass() == Oraculo.class) {
                        // tratarTurnoOraculo executa a habilidade E JÁ FAZ o inimigo contra-atacar,
                        // e retorna false SÓ se escolheu "Voltar".
                        turnoGasto = tratarTurnoOraculo(inimigo);
                    } else {
                        Tela.narrar("Escolha inválida! Você perdeu seu turno.");
                        // Precisa do ataque inimigo aqui se perdeu o turno
                        executarTurno(inimigo, this.jogador); // Renomeado
                    }
                    break;

                case 3: // Usar Item
                    boolean itemFoiUsado = usarItem();
                    if (itemFoiUsado) {
                        // Se usou item, inimigo contra-ataca
                        if (inimigo.estaVivo()) {
                            executarTurno(inimigo, this.jogador); // Renomeado
                        }
                    } else {
                        // Se cancelou, turno NÃO foi gasto
                        turnoGasto = false;
                    }
                    break;
                default:
                    Tela.narrar("Escolha inválida! Você perdeu seu turno.");
                    // Precisa do ataque inimigo aqui se perdeu o turno
                    executarTurno(inimigo, this.jogador); // Renomeado
                    break;
            }

        }


        if (this.jogador.estaVivo()) {
            Tela.narrar("\n*** VITÓRIA! ***");
            Tela.narrar("Você derrotou o " + inimigo.getNome() + "!");
            resetarStatsAposBatalha(); // RESETA os buffs
            Tela.esperarEnter();
            return true;
        } else {
            Tela.narrar("\n*** DERROTA ***");
            Tela.narrar("Você foi derrotado... Retornando ao último checkpoint.");
            resetarStatsAposBatalha(); // RESETA os buffs

            // RESPAWN: Clona o checkpoint de volta para o jogador
            this.jogador = (Personagem) this.savePoint.clone();

            Tela.imprimirStatus(this.jogador);

            Tela.esperarEnter();
        }
        return false;
    }



    private boolean tratarTurnoOraculo(Inimigo inimigo) throws Exception {
        Tela.imprimirMenuOraculo();
        int escolhaMagia = -1;
        try {
            escolhaMagia = Teclado.getUmInt();
        } catch (Exception e) {
            escolhaMagia = -1;
        }

        String narracaoResultado = "";
        boolean habilidadeExecutada = false;
        switch (escolhaMagia) {
            case 1: // Habilidade: Curar
                narracaoResultado = this.jogador.habilidadeEspecial(1, null);
                Tela.narrar(narracaoResultado);
                habilidadeExecutada = true;
                break; // Sai do switch

            case 2: // Habilidade: Amaldiçoar
                narracaoResultado = this.jogador.habilidadeEspecial(2, inimigo);
                Tela.narrar(narracaoResultado);
                habilidadeExecutada = true;
                break;

            case 3:
                Tela.narrar("Você decide esperar...");
                return false;

            default:
                Tela.narrar("Opção de magia inválida! Você perdeu seu turno.");
                habilidadeExecutada = true; // Perdeu o turno, mas inimigo ataca
                break; // Sai do switch
        }


        if (habilidadeExecutada && inimigo.estaVivo()) {
            executarTurno(inimigo, this.jogador); // Renomeado
        }
        return true;
    }



    // Método para dar 1 poção aleatória (caminho seguro - após vencer batalha)
    private void darRecompensaBatalha() throws Exception {
        int tipo = Dado.rolar(3); // 1, 2 ou 3
        Item pocao = criarPocaoAleatoria(tipo);

        Tela.narrar("📦 Você encontrou: " + pocao.getNome() + "!");
        Tela.narrar("   " + pocao.getDescricao());
        jogador.getInventario().adicionarItem(pocao);
        Tela.esperarEnter();
    }

    // Método para dar 2 poções aleatórias (caminho arriscado - sucesso na armadilha)
    private void darRecompensaArmadilha() throws Exception {
        Tela.narrar("📦 Você encontrou 2 poções mágicas!\n");

        // Primeira poção
        int tipo1 = Dado.rolar(3);
        Item pocao1 = criarPocaoAleatoria(tipo1);
        Tela.narrar("   1) " + pocao1.getNome() + " - " + pocao1.getDescricao());
        jogador.getInventario().adicionarItem(pocao1);

        // Segunda poção (pode ser igual)
        int tipo2 = Dado.rolar(3);
        Item pocao2 = criarPocaoAleatoria(tipo2);
        Tela.narrar("   2) " + pocao2.getNome() + " - " + pocao2.getDescricao());
        jogador.getInventario().adicionarItem(pocao2);

        Tela.narrar("");
        Tela.esperarEnter();
    }

    // Cria uma poção baseada no tipo (1=Cura, 2=Dano, 3=Defesa)
    private Item criarPocaoAleatoria(int tipo) throws Exception {
        switch (tipo) {
            case 1:
                return new Item("Poção da Cura", "Restaura 40 HP", "CURA", (short)1,(short)40);
            case 2:
                return new Item("Poção do Dano", "Dobra seu ataque temporariamente", "DANO_X", (short)1,(short)2);
            case 3:
                return new Item("Poção da Defesa", "Dobra sua defesa temporariamente", "DEFESA_X", (short)1,(short)2);
            default:
                return new Item("Poção da Cura", "Restaura 40 HP", "CURA", (short)1,(short)40);
        }
    }

    private boolean usarItem() throws Exception {
        Tela.limparTela();
        Tela.narrar("=== INVENTÁRIO ===\n");

        // Filtra apenas itens USÁVEIS em batalha
        List<Item> itensUsaveis = this.jogador.getInventario().getItensUsaveisEmBatalha();

        // Verifica se tem itens usáveis
        if (itensUsaveis.isEmpty()) {
            Tela.narrar("Você não possui itens usáveis em batalha!");
            Tela.narrar("(Apenas poções mágicas podem ser usadas durante o combate)\n");
            Tela.esperarEnter();
            return false;
        }

        // Exibe menu numerado
        for (int i = 0; i < itensUsaveis.size(); i++) {
            Item item = itensUsaveis.get(i);
            Tela.narrar((i + 1) + ". " + item.getNome() + " (x" + item.getQuantidade() + ") - " + item.getDescricao());
        }
        Tela.narrar((itensUsaveis.size() + 1) + ". Cancelar");
        Tela.narrar("-----------------------\n");

        // Lê escolha do jogador
        int escolha = -1;
        try {
            Tela.narrar("Escolha o item (digite o número):");
            escolha = Teclado.getUmInt();
        } catch (Exception e) {
            Tela.narrar("\n[ERRO] Entrada inválida!");
            Tela.esperarEnter();
            return false;
        }

        // Verifica se cancelou
        if (escolha == itensUsaveis.size() + 1) {
            Tela.narrar("Você decidiu não usar nenhum item.");
            Tela.esperarEnter();
            return false;
        }

        // Verifica se a escolha é válida
        if (escolha < 1 || escolha > itensUsaveis.size()) {
            Tela.narrar("\n[ERRO] Opção inválida!");
            Tela.esperarEnter();
            return false;
        }

        // Pega o item escolhido
        Item itemEscolhido = itensUsaveis.get(escolha - 1);
        String nomeItem = itemEscolhido.getNome();
        String efeito = itemEscolhido.getEfeito();
        int potencia = itemEscolhido.getPotencia();

        // Remove 1 unidade do inventário
        if (!this.jogador.getInventario().removerItem(nomeItem, 1)) {
            Tela.narrar("\n[ERRO] Não foi possível usar o item!");
            Tela.esperarEnter();
            return false;
        }

        // Aplica o efeito
        Tela.limparTela();
        Tela.narrar("\n[ITEM USADO] " + nomeItem + "\n");

        if (efeito.equals("CURA")) {
            this.jogador.curar(potencia); // <-- USA A POTÊNCIA
            Tela.narrar("💚 Você recuperou " + potencia + " HP!");

        } else if (efeito.equals("DANO_X")) { // <-- MUDA PARA O EFEITO GENÉRICO
            int ataqueOriginal = this.jogador.getAtaque();
            int novoAtaque = ataqueOriginal * potencia; // <-- USA A POTÊNCIA
            this.jogador.setAtaque(novoAtaque);
            Tela.narrar("⚔️ Seu ataque foi multiplicado por " + potencia + "! (" + ataqueOriginal + " → " + novoAtaque + ")");
            Tela.narrar("   O efeito dura até o fim da batalha!");

        } else if (efeito.equals("DEFESA_X")) { // <-- MUDA PARA O EFEITO GENÉRICO
            int defesaOriginal = this.jogador.getDefesa();
            int novaDefesa = defesaOriginal * potencia; // <-- USA A POTÊNCIA
            this.jogador.setDefesa(novaDefesa);
            Tela.narrar("🛡️ Sua defesa foi multiplicada por " + potencia + "! (" + defesaOriginal + " → " + novaDefesa + ")");
            Tela.narrar("   O efeito dura até o fim da batalha!");
        }


        Tela.narrar("\nHP atual: " + this.jogador.getPontosVida() + "/" + this.jogador.getPontosVidaMax());
        Tela.esperarEnter();
        return true;
    }

    // Método para salvar stats originais antes da batalha
    private void salvarStatsOriginais() {
        if (!buffAtivo) {
            ataqueOriginal = jogador.getAtaque();
            defesaOriginal = jogador.getDefesa();
            buffAtivo = true;
        }
    }

    // Método para resetar stats após a batalha
    private void resetarStatsAposBatalha() throws Exception {
        if (buffAtivo) {
            jogador.setAtaque(ataqueOriginal);
            jogador.setDefesa(defesaOriginal);
            buffAtivo = false;
        }
    }


    public void tratarTurnoAtaquePadrao(Inimigo inimigo) throws Exception {
        if(this.jogador.getClass() == Berserker.class){ // LENTO
            // Inimigo ataca primeiro
            executarTurno(inimigo, this.jogador);
            if (this.jogador.estaVivo()){
                // Jogador (Berserker) ataca depois
                executarTurno(this.jogador, inimigo);
            }
        } else { // RÁPIDO (Caçador/Oráculo no Ataque Básico)
            // Jogador ataca primeiro
            executarTurno(this.jogador, inimigo);
            if (inimigo.estaVivo()) {
                // Inimigo ataca depois
                executarTurno(inimigo, this.jogador);
            }
        }
    }

    public void executarTurno(Personagem atacante, Personagem alvo) throws Exception {
        int resultadoDado = Dado.rolar(9); //passando um dado de 6 lados

        int ataqueBase = atacante.getAtaque();
        int defesaBaseOriginal = atacante.getDefesa();
        int ataqueBaseComBonus = atacante.getAtaque();

        String narracaoHabilidade = null;
        boolean habilidadeAtivada = false;

        habilidadeAtivada = atacante.aplicarPassivaDeAtaque(alvo); //evita ter q usar getClass pra duto

        // Atualiza o ataqueBaseComBonus SE a habilidade tiver ativado
        if (habilidadeAtivada) {
            ataqueBaseComBonus = atacante.getAtaque();
        }

        int ataqueTotal = ataqueBaseComBonus + resultadoDado;
        int defesaAlvo = alvo.getDefesa();
        Tela.narrar(atacante.getNome() + " ataca com " + ataqueBaseComBonus + " + ("
        + resultadoDado + " no dado) = " + ataqueTotal + " de força!");

        // chama o metodo de receber o dano aonde é calculado o dano, aplica e retona o dano causa para poder ser usado aqui
        int danoReal = alvo.receberDano(ataqueTotal);


        // Exibe a narrativa correta
        if (danoReal == 0) {
            Tela.narrar(alvo.getNome() + " defende " + defesaAlvo + " e bloqueia o ataque completamente!");
        } else {
            Tela.narrar(alvo.getNome() + " defende " + defesaAlvo + " e recebe " + danoReal + " de dano!");
        }
        Tela.narrar(" ");
        if (habilidadeAtivada) {
            atacante.setAtaque(ataqueBase); // Volta ao ataque normal


            if (atacante.getClass() == Berserker.class) {
                atacante.setDefesa(defesaBaseOriginal);
            }
            Tela.narrar(atacante.getNome() + " volta ao normal.");
            Tela.esperarEnter();
        }
        Tela.esperarEnter();
        Tela.limparTela();
    }
}

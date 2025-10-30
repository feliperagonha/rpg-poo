package game;

import model.*;
import util.*;
import java.util.List;        
import java.util.ArrayList;   


public class Jogo{
    private Personagem jogador;
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
                        Tela.narrar("Função carregar jogo");
                        Tela.esperarEnter();
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
        Tela.imprimirMenuSaves();
        int slot = -1;

        while(slot < 1 || slot > 3) {
            try {
                Tela.narrar("Escolha o slot (de 1 a 3)");
                slot = Teclado.getUmInt();
                if (slot < 1 || slot > 3) {
                    Tela.narrar("Slot inválido!");
                }

            } catch (Exception e) {
                Tela.narrar("Erro: Entrada inválida. Por favor, digite 1, 2 ou 3.");
                Teclado.getUmString();
            }
        }

        Tela.narrar("O jogo vai ser salvo no slot " + slot + ".");
        Tela.esperarEnter();

        Tela.limparTela();
        Tela.imprimirHistoria();
        Tela.narrar("\nAo tocar a pedra, suas mãos formigam...");
        Tela.narrar("Um brilho intenso emana das runas, cegando você momentaneamente!");
        Tela.esperarEnter();


        this.jogador = criarPersonagem();

        Tela.narrar("\nPersonagem criado com sucesso!");
        Tela.imprimirStatus(this.jogador);
        Tela.esperarEnter();
        iniciarJornada();
    }

    private Personagem criarPersonagem() throws Exception {
    Tela.limparTela();
    Tela.narrar("--- Criação de Personagem ---\n");
    Tela.narrar("A energia da runa ressoa em você. Quem você se tornará?\n");
    
    Tela.narrar("1. O BERSERKER (Guerreiro)");
    Tela.narrar("    HP: 150 | ATK: 20 | DEF: 5");
    Tela.narrar("   Fúria viking devastadora em combate\n");
    
    Tela.narrar("2. O ORACULO (Mago)");
    Tela.narrar("    HP: 80 | ATK: 5 | DEF: 15");
    Tela.narrar("   Magias de cura e maldição arcana\n");
    
    Tela.narrar("3. O CAÇADOR (Arqueiro)");
    Tela.narrar("    HP: 120 | ATK: 12 | DEF: 4");
    Tela.narrar("   Precisão mortal e ataques críticos\n");
    
    Tela.narrar("--------------------------------");

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
        ato1();
    }
    
    // ========== ATO I: O INVERNO SEM FIM ==========
    private void ato1() throws Exception {
        Tela.limparTela();
        Tela.narrar("--- A Jornada Começa ---");
        Tela.narrar("Seu pequeno barco, o 'Corvo Marinho', parte em meio ao gelo.");
        Tela.narrar("Após um dia de viagem, você avista uma ilha coberta de névoa,");
        Tela.narrar("de onde ecoa um som lamentoso. O mar à frente está agitado.\n");
        Tela.esperarEnter();
        
        decisaoAto1();
    }

    private void decisaoAto1() throws Exception {
        int escolha = -1;
        while (escolha != 1 && escolha != 2) {
            Tela.limparTela();
            Tela.narrar("--- O QUE FAZER? ---");
            Tela.narrar("1. Atracar na ilha nebulosa (Pode haver suprimentos)");
            Tela.narrar("2. Explorar uma caverna na costa (Caminho mais rápido, mas perigoso)");
            Tela.narrar("--------------------\n");
            
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
        Tela.narrar("Você ancora em uma praia de areia negra.");
        Tela.narrar("Ao explorar, você encontra os restos de um acampamento.");      
        Tela.esperarEnter();
        
        Tela.narrar("O som lamentoso se intensifica...");
        Tela.narrar("Um grupo de DRAUGR (guerreiros mortos-vivos) emerge do chão!\n");
        Tela.esperarEnter();
        
        Inimigo draugr = new Inimigo("Draugr", 40, 8, 5, (byte)1);
        batalhar(draugr);
        
        if (jogador.estaVivo()) {
            darRecompensaBatalha(); // da a recompensa
            Tela.narrar("Você derrotou os Draugr e segue viagem...\n");
            Tela.esperarEnter();
            ato2();
        }
    }

    // CAMINHO 2: Caverna com morcegos (ARRISCADO - armadilha OBRIGATÓRIA)
    private void caminhoArriscadoAto1() throws Exception {
        Tela.limparTela();
        Tela.narrar("Você decide explorar uma caverna na costa.");
        Tela.narrar("A entrada é estreita e úmida. Ecos estranhos vêm do fundo.");
        Tela.narrar("Você acende uma tocha e entra...\n");
        Tela.esperarEnter();
        
        Tela.narrar("De repente, centenas de MORCEGOS despertam e atacam!");
        Tela.narrar("Eles voam em direção ao seu rosto, mordendo e arranhando!\n");
        Tela.esperarEnter();
        
        Tela.narrar("⚠️ PERIGO IMINENTE! ⚠️");
        Tela.narrar("Você está sendo cercado por uma nuvem de morcegos raivosos!");
        Tela.narrar("Não há como lutar contra tantos!\n");
        Tela.esperarEnter();
        
        int escolhaArmadilha = -1;
        while (escolhaArmadilha != 1 && escolhaArmadilha != 2) {
            Tela.narrar("--- O QUE FAZER? ---");
            Tela.narrar("1. Tentar passar correndo pela nuvem! (Rolar dados - Teste de Agilidade)");
            Tela.narrar("2. Fugir da caverna e voltar para a praia");
            Tela.narrar("--------------------\n");
            
            try {
                Tela.narrar("Sua escolha:");
                escolhaArmadilha = Teclado.getUmInt();
                
                if (escolhaArmadilha == 1) {
                    // TENTAR PASSAR
                    Tela.narrar("\n🎲 Rolando dados...");
                    int resultado = Dado.rolar(20);
                    Tela.narrar("Você tirou: " + resultado + " (necessário > 11 para conseguir se livrar dos morcegos.)\n");
                    Tela.esperarEnter();
                    
                    if (resultado >= 11) {
                        // SUCESSO - dano leve
                        Tela.narrar("✅ SUCESSO!");
                        Tela.narrar("Você corre protegendo o rosto com o manto!");
                        Tela.narrar("Alguns morcegos te mordem, mas você consegue passar!\n");
                        darRecompensaArmadilha(); // DA 2 POÇÕES ALEATÓRIAS
                        
                        int dano = 15;
                        int defesaAtual = jogador.getDefesa();
                        jogador.receberDano(dano + defesaAtual); // Dano direto ignorando defesa
                        
                        Tela.narrar("Você sofreu " + dano + " de dano pelas mordidas!");
                        Tela.narrar("HP atual: " + jogador.getPontosVida() + "/" + jogador.getPontosVidaMax() + "\n");
                        Tela.esperarEnter();
                        
                        if (jogador.estaVivo()) {
                            Tela.narrar("Você atravessa a caverna e sai do outro lado...");
                            Tela.narrar("📦 Você encontrou um baú escondido!");
                            Tela.narrar("Ganhou: Tônico Revigorante (+2 na vida máxima)!\n");
                            
                            Item tonico = new Item("Tônico Revigorante", "Aumenta a resistência", "+2 HP Max", (short)1);
                            jogador.getInventario().adicionarItem(tonico);
                            jogador.setPontosVidaMax(jogador.getPontosVidaMax() + 2);
                            
                            Tela.esperarEnter();
                            ato2();
                        }
                        
                    } else {
                        // FALHA - dano pesado
                        Tela.narrar("❌ FALHA!");
                        Tela.narrar("Os morcegos te cobrem completamente!");
                        Tela.narrar("Suas mordidas são venenosas e causam dor intensa!\n");
                        
                        int dano = jogador.getPontosVidaMax();
                        int defesaAtual = jogador.getDefesa();
                        jogador.receberDano(dano + defesaAtual); // Dano direto
                        
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
                        
                        // Vai para o ato 2 enfraquecido
                        ato2();
                    }
                    
                } else if (escolhaArmadilha == 2) {
                    // FUGIR - volta para o caminho seguro
                    Tela.narrar("Com sabedoria, você recua antes que seja tarde demais!");
                    Tela.narrar("Você sai da caverna e decide atracar na praia.\n");
                    Tela.esperarEnter();
                    caminhoSeguroAto1();
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
        Tela.narrar("--- Ato II: O Mar das Serpentes ---");
        Tela.narrar("Após sobreviver ao primeiro desafio, a confiança da tripulação cresce.");
        Tela.narrar("O mar se acalma, mas o céu adquire uma cor esverdeada e doentia.");
        Tela.narrar("Vocês entraram no infame Mar das Serpentes.");
        Tela.narrar("À distância, você vê os destroços de outro navio viking,");
        Tela.narrar("mas algo grande se move sob a superfície perto dele.\n");
        Tela.esperarEnter();
        
        decisaoAto2();
    }
    
    private void decisaoAto2() throws Exception {
        int escolha = -1;
        while (escolha != 1 && escolha != 2) {
            Tela.limparTela();
            Tela.narrar("--- O QUE FAZER? ---");
            Tela.narrar("1. Investigar os destroços (Pode haver tesouros, mas o risco é mais evidente)");
            Tela.narrar("2. Fugir (Caminho mais longo, mas mais seguro)");
            Tela.narrar("--------------------\n");
            
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
        Tela.narrar("Você decide investigar os destroços.");
        Tela.narrar("Conforme se aproximam, algo gigantesco emerge da água!");
        Tela.narrar("Uma SERPENTE MARINHA protege seu ninho nos destroços!\n");
        Tela.esperarEnter();
        
        Inimigo serpenteMarinha = new Inimigo("Serpente Marinha", 100, 12, 8, (byte)2);
        batalhar(serpenteMarinha);
        
        if (jogador.estaVivo()) {
            Tela.narrar("Após derrotar a serpente, você explora os destroços.");
            darRecompensaBatalha();
           
            Tela.esperarEnter();
            Tela.narrar("Você segue em frente...\n");
            Tela.esperarEnter();
            ato3();
        }
    }
    
    // CAMINHO 2: Contornar a área (SEGURO - penalidade leve)
    private void caminhoSeguroAto2() throws Exception {
        Tela.limparTela();
        Tela.narrar("Você decide navegar contornando a área perigosa.");
        Tela.narrar("Vocês gastam meio dia remando para evitar o perigo.");
        Tela.narrar("Estão seguros, mas exaustos e com menos suprimentos.\n");
        
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
        Tela.esperarEnter();
        ato3();
    }
    
    // ========== ATO III: A CHEGADA EM THULE ==========
    private void ato3() throws Exception {
        Tela.limparTela();
        Tela.narrar("--- Ato III: A Chegada em Thule ---");
        Tela.narrar("Finalmente, após semanas no mar, vocês avistam...");
        Tela.narrar("Uma ilha verdejante, banhada por uma luz dourada. THULE!");
        Tela.narrar("Mas ao se aproximarem, vocês veem que a ilha é guardada");
        Tela.narrar("por um clã rival, os 'Lobos de Gelo', que já reivindicaram o lugar.\n");
        Tela.esperarEnter();
        
        Tela.narrar("O líder deles, um Jarl gigantesco, os aguarda na praia.");
        Tela.narrar("JARL: 'Esta terra sagrada é nossa! Voltem para seu inverno e morram,");
        Tela.narrar("ou deixem seus ossos em nossa praia!'\n");
        Tela.esperarEnter();
        
        Tela.narrar("A jornada termina aqui.");
        Tela.narrar("É lutar pela sobrevivência do seu povo ou falhar no último obstáculo.\n");
        Tela.esperarEnter();
        
        enfrentarBossFinal();
    }
    
    private void enfrentarBossFinal() throws Exception {
        Tela.limparTela();
        Tela.narrar("======================================");
        Tela.narrar("    ⚔️ BATALHA FINAL! ⚔️");
        Tela.narrar("======================================\n");
        Tela.esperarEnter();
        
        Inimigo jarl = new Inimigo("Jarl, Líder dos Lobos de Gelo", 200, 15, 10, (byte)5);
        batalhar(jarl);
        
        if (jogador.estaVivo()) {
            // VITÓRIA
            Tela.limparTela();
            Tela.narrar("======================================");
            Tela.narrar("         ✨ VITÓRIA! ✨");
            Tela.narrar("======================================\n");
            Tela.narrar("Com o Jarl derrotado, os Lobos de Gelo se rendem.");
            Tela.narrar("Vocês conquistaram um novo lar para seu povo.");
            Tela.narrar("A saga da 'Runa Despertada' será cantada por gerações.");
            Tela.narrar("\n🏆 A MISSÃO FOI UM SUCESSO! 🏆\n");
            Tela.esperarEnter();
        } else {
            // DERROTA
            Tela.limparTela();
            Tela.narrar("======================================");
            Tela.narrar("         💀 DERROTA 💀");
            Tela.narrar("======================================\n");
            Tela.narrar("Você cai na areia da praia.");
            Tela.narrar("A última visão que você tem é do Jarl rindo");
            Tela.narrar("enquanto sua tripulação é derrotada.");
            Tela.narrar("A esperança do seu clã morre com você.");
            Tela.narrar("\n❌ A MISSÃO FALHOU ❌\n");
            Tela.esperarEnter();
        }
    }



    public void batalhar(Inimigo inimigo) throws Exception {
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
                            Tela.narrar("--- Turno do Inimigo ---");
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
        } else {
            Tela.narrar("\n*** DERROTA ***");
            Tela.narrar("Você foi derrotado... Fim de jogo.");
            resetarStatsAposBatalha(); // RESETA os buffs
            Tela.esperarEnter();
        }
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
            Tela.narrar("--- Turno do Inimigo ---");
            executarTurno(inimigo, this.jogador); // Renomeado
        }
        return true;
    }
    

    // Método para usar item do inventário durante a batalha
    /*
    private boolean usarItem() throws Exception {
        Tela.limparTela();
        Tela.narrar("=== INVENTÁRIO ===");
        
        // Lista os itens disponíveis
        this.jogador.getInventario().listarItens();
        
        Tela.narrar("\nDigite o nome EXATO do item que deseja usar (ou digite 'cancelar'):");
        String nomeItem = Teclado.getUmString();
        
        if (nomeItem.equalsIgnoreCase("cancelar")) {
            Tela.narrar("Você decidiu não usar nenhum item.");
            Tela.esperarEnter();
            return false ;
        }
        
        // Verifica se o item existe e remove 1 unidade
        if (this.jogador.getInventario().removerItem(nomeItem, 1)) {


            // Aplica o efeito do item baseado no nome/tipo
            if (nomeItem.toLowerCase().contains("hidromel") || 
                nomeItem.toLowerCase().contains("poção") || 
                nomeItem.toLowerCase().contains("cura")) {
                
                int cura = 40; // Hidromel cura 40 HP
                this.jogador.curar(cura);
                Tela.narrar("\n[ITEM USADO] " + nomeItem);
                Tela.narrar("Você recuperou " + cura + " HP!");
            } else {
                // Item genérico
                Tela.narrar("\n[ITEM USADO] " + nomeItem);
                Tela.narrar("Você usou o item com sucesso!");
            }
            Tela.narrar("HP atual: " + this.jogador.getPontosVida() + "/" + this.jogador.getPontosVidaMax());
            Tela.esperarEnter();
            return true;
        } else {
            Tela.narrar("\n[ERRO] Você não possui esse item no inventário!");
            Tela.narrar("Verifique o nome exato e tente novamente.");
            Tela.esperarEnter();
            return false;
        }
    }
    */

        // Método para dar 1 poção aleatória (caminho seguro - após vencer batalha)
    private void darRecompensaBatalha() {
        int tipo = Dado.rolar(3); // 1, 2 ou 3
        Item pocao = criarPocaoAleatoria(tipo);
        
        Tela.narrar("📦 Você encontrou: " + pocao.getNome() + "!");
        Tela.narrar("   " + pocao.getDescricao());
        jogador.getInventario().adicionarItem(pocao);
        Tela.esperarEnter();
    }

    // Método para dar 2 poções aleatórias (caminho arriscado - sucesso na armadilha)
    private void darRecompensaArmadilha() {
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
    private Item criarPocaoAleatoria(int tipo) {
        switch (tipo) {
            case 1:
                return new Item("Poção da Cura", "Restaura 40 HP", "CURA", (short)1);
            case 2:
                return new Item("Poção do Dano", "Dobra seu ataque temporariamente", "DANO_2X", (short)1);
            case 3:
                return new Item("Poção da Defesa", "Dobra sua defesa temporariamente", "DEFESA_2X", (short)1);
            default:
                return new Item("Poção da Cura", "Restaura 40 HP", "CURA", (short)1);
        }
    }

    private boolean usarItem() throws Exception {
        Tela.limparTela();
        Tela.narrar("=== INVENTÁRIO ===\n");
        
        // Filtra apenas itens USÁVEIS em batalha
        List<Item> itensUsaveis = new ArrayList<>();
        for (Item item : this.jogador.getInventario().getItens()) {
            String efeito = item.getEfeito();
            if (efeito.equals("CURA") || efeito.equals("DANO_2X") || efeito.equals("DEFESA_2X")) {
                itensUsaveis.add(item);
            }
        }
        
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
            // Poção da Cura
            int cura = 40;
            this.jogador.curar(cura);
            Tela.narrar("💚 Você recuperou " + cura + " HP!");
            
        } else if (efeito.equals("DANO_2X")) {
            // Poção do Dano - DOBRA o ataque
            int ataqueOriginal = this.jogador.getAtaque();
            int novoAtaque = ataqueOriginal * 2;
            this.jogador.setAtaque(novoAtaque);
            Tela.narrar("⚔️ Seu ataque DOBROU! (" + ataqueOriginal + " → " + novoAtaque + ")");
            Tela.narrar("   O efeito dura até o fim da batalha!");
            
        } else if (efeito.equals("DEFESA_2X")) {
            // Poção da Defesa - DOBRA a defesa
            int defesaOriginal = this.jogador.getDefesa();
            int novaDefesa = defesaOriginal * 2;
            this.jogador.setDefesa(novaDefesa);
            Tela.narrar("🛡️ Sua defesa DOBROU! (" + defesaOriginal + " → " + novaDefesa + ")");
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
        int resultadoDado = Dado.rolar(6); //passando um dado de 6 lados

        int ataqueBase = atacante.getAtaque();
        int defesaBaseOriginal = atacante.getDefesa();
        int ataqueBaseComBonus = atacante.getAtaque();

        String narracaoHabilidade = null;
        boolean habilidadeAtivada = false;


        //habilidades passivas berseker
        if(atacante.getClass() == Berserker.class && atacante.getPontosVida() < (atacante.getPontosVidaMax() * 0.5)){ //50% da vida maxima
            narracaoHabilidade = atacante.habilidadeEspecial(alvo);
            ataqueBaseComBonus = atacante.getAtaque();
            habilidadeAtivada = true;
        }


        if(atacante.getClass() == Cacador.class){
            if (Dado.rolar(100) <= 25){
                narracaoHabilidade = atacante.habilidadeEspecial(alvo);
                ataqueBaseComBonus = atacante.getAtaque();
                habilidadeAtivada = true;
            }
        }
        int ataqueTotal = ataqueBaseComBonus + resultadoDado;
        int defesaAlvo = alvo.getDefesa();

        if(narracaoHabilidade != null){
            Tela.narrar(narracaoHabilidade);
        }
        Tela.narrar(atacante.getNome() + " ataca com " + ataqueBaseComBonus + " + (" 
        + resultadoDado + " no dado) = " + ataqueTotal + " de força!");

        // Calcula o dano ANTES de aplicar
        int danoReal = Math.max(0, ataqueTotal - defesaAlvo);

        // Aplica o dano
        alvo.receberDano(ataqueTotal);

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



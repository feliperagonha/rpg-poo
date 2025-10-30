package game;

import model.*;
import util.*;


public class Jogo{
    private Personagem jogador;

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
    Tela.narrar("--- Criação de Personagem ---");
    Tela.narrar("A energia da runa ressoa em você. Quem você se tornará?");
    Tela.narrar("1. O Berserker (Guerreiro): Força bruta indomável.");
    Tela.narrar("2. O Oráculo (Mago): Conexão com a sabedoria ancestral.");
    Tela.narrar("3. O Caçador (Arqueiro): Instintos e precisão letais.");
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
    Tela.limparTela();
    
    // ========== ATO I: O INVERNO SEM FIM ==========
    Tela.narrar("=== ATO I: O INVERNO SEM FIM ===");
    Tela.narrar("\nO Fimbulvetr, o 'Inverno Interminável', castiga sua vila há três anos.");
    Tela.narrar("O sol é uma memória pálida, e os fiordes estão congelados.");
    Tela.narrar("A comida está acabando. O Ancião do clã declarou que, sem a intervenção dos deuses,");
    Tela.narrar("não haverá uma próxima primavera.\n");
    Tela.esperarEnter();
    
    Tela.narrar("Agora, a vila deposita em você sua última esperança:");
    Tela.narrar("liderar uma expedição pelo mar em busca da mítica ilha de Thule,");
    Tela.narrar("onde se diz que o verão é eterno.\n");
    Tela.esperarEnter();
    
    Tela.narrar("--- A Jornada Começa ---");
    Tela.narrar("Seu pequeno barco, o 'Corvo Marinho', parte em meio ao gelo.");
    Tela.narrar("Após um dia de viagem, você avista uma ilha coberta de névoa,");
    Tela.narrar("de onde ecoa um som lamentoso. O mar à frente está agitado.\n");
    Tela.esperarEnter();
    
    // DECISÃO 1
    int decisao1 = -1;
    while (decisao1 != 1 && decisao1 != 2) {
        Tela.narrar("=== DECISÃO 1 ===");
        Tela.narrar("1. Atracar na ilha nebulosa (Pode haver suprimentos, mas o som é sinistro)");
        Tela.narrar("2. Enfrentar o mar agitado (Caminho direto, mas arriscado)");
        Tela.narrar("\nEscolha (1 ou 2):");
        
        try {
            decisao1 = Teclado.getUmInt();
            if (decisao1 != 1 && decisao1 != 2) {
                Tela.narrar("Opção inválida! Escolha 1 ou 2.");
            }
        } catch (Exception e) {
            Tela.narrar("Entrada inválida!");
            Teclado.getUmString();
        }
    }
    
    Tela.limparTela();
    
    if (decisao1 == 1) {
        // Escolheu a ILHA
        Tela.narrar("Você ancora em uma praia de areia negra.");
        Tela.narrar("Ao explorar, você encontra os restos de um acampamento abandonado.");
        
        // Adiciona item HIDROMEL
        Item hidromel = new Item(
            "Hidromel de Boa Qualidade",
            "Um hidromel forte e doce que revigora o espírito.",
            "Cura",
            (short) 1
        );
        this.jogador.getInventario().adicionarItem(hidromel);
        
        Tela.narrar("\n[ITEM ADQUIRIDO] Hidromel de Boa Qualidade (Cura 40 HP)");
        Tela.esperarEnter();
        
        Tela.narrar("\nO som lamentoso se intensifica...");
        Tela.narrar("Um grupo de DRAUGR (guerreiros mortos-vivos) emerge do chão!\n");
        Tela.esperarEnter();
        
        // COMBATE contra DRAUGR
        Inimigo draugr = Inimigo.criarDraugr();
        batalhar(draugr);
        
        if (!this.jogador.estaVivo()) {
            return; // Jogador morreu, fim de jogo
        }
        
    } else {
        // Escolheu o MAR
        Tela.narrar("Você decide seguir em frente pelo mar agitado.");
        Tela.narrar("Uma onda massiva atinge o barco, jogando suprimentos ao mar!");
        Tela.narrar("Vocês sobrevivem, mas a jornada será mais difícil.\n");
        
        // Jogador perde 20 HP
        int danoMar = 20;
        int novaVida = Math.max(1, this.jogador.getPontosVida() - danoMar);
        this.jogador.setPontosVida(novaVida);
        
        Tela.narrar("[DANO] Você perdeu " + danoMar + " HP pela tempestade!");
        Tela.narrar("HP atual: " + this.jogador.getPontosVida() + "/" + this.jogador.getPontosVidaMax());
        Tela.esperarEnter();
    }
    
    // ========== ATO II: O MAR DAS SERPENTES ==========
    Tela.limparTela();
    Tela.narrar("\n=== ATO II: O MAR DAS SERPENTES ==="                                               );
    Tela.narrar("\nApós sobreviver ao seu primeiro desafio, a confiança da tripulação em você cresce.");
    Tela.narrar("O mar se acalma, mas o céu adquire uma cor esverdeada e doentia."                    );
    Tela.narrar("Vocês entraram no infame Mar das Serpentes.\n"                                       );
    Tela.esperarEnter();
    
    Tela.narrar("À distância, você vê os destroços de outro navio viking,");
    Tela.narrar("mas algo grande se move sob a superfície perto dele...\n");
    Tela.esperarEnter();
    
    // DECISÃO 2
    int decisao2 = -1;
    while (decisao2 != 1 && decisao2 != 2) {
        Tela.narrar("=== DECISÃO 2 ==="                                               );
        Tela.narrar("1. Investigar os destroços (Pode haver tesouros, mas é perigoso)");
        Tela.narrar("2. Navegar contornando a área (Mais seguro, mas longo)"          );
        Tela.narrar("\nEscolha (1 ou 2):"                                             );
        
        try {
            decisao2 = Teclado.getUmInt();
            if (decisao2 != 1 && decisao2 != 2) {
                Tela.narrar("Opção inválida! Escolha 1 ou 2.");
            }
        } catch (Exception e) {
            Tela.narrar("Entrada inválida!");
            Teclado.getUmString();
        }
    }
    
    Tela.limparTela();
    
    if (decisao2 == 1) {
        // Escolheu DESTROÇOS
        Tela.narrar("Conforme se aproximam, a água começa a borbulhar violentamente...");
        Tela.narrar("Uma gigantesca SERPENTE MARINHA emerge da água!"                  );
        Tela.narrar("Ela protege seu ninho nos destroços! A batalha é inevitável!\n"   );
        Tela.esperarEnter();
        
        // COMBATE contra SERPENTE MARINHA
        Inimigo serpente = Inimigo.criarSerpenteMarinha();
        batalhar(serpente);
        
        if (!this.jogador.estaVivo()) {
            return; // Jogador morreu
        }
        
        Tela.narrar("\nApós derrotar a serpente, vocês exploram os destroços...");
        
        // Adiciona item MACHADO COM RUNAS
        Item machado = new Item(
            "Machado com Runas de Gelo",
            "Um machado encontrado em um naufrágio. As runas na lâmina brilham com um frio intenso.",
            "Buff",
            (short) 1
        );
        this.jogador.getInventario().adicionarItem(machado);
        
        // Aumenta o ataque permanentemente
        int ataqueAtual = this.jogador.getAtaque();
        this.jogador.setAtaque(ataqueAtual + 3);
        
        Tela.narrar("[ITEM ADQUIRIDO] Machado com Runas de Gelo!" );
        Tela.narrar("[BUFF PERMANENTE] Seu ataque aumentou em +3!");
        Tela.narrar("Ataque atual: " + this.jogador.getAtaque()         );
        Tela.esperarEnter();
        
    } else {
        // Escolheu CONTORNAR
        Tela.narrar("Vocês gastam meio dia remando para contornar a área perigosa.");
        Tela.narrar("Estão seguros, mas exaustos e com menos suprimentos."         );
        Tela.narrar("A viagem se torna mais demorada.\n"                           );
        
        // Jogador perde 15 HP
        int danoExaustao = 15;
        int novaVida = Math.max(1, this.jogador.getPontosVida() - danoExaustao);
        this.jogador.setPontosVida(novaVida);
        
        Tela.narrar("[DANO] Você perdeu " + danoExaustao + " HP pela exaustão!");
        Tela.narrar("HP atual: " + this.jogador.getPontosVida() + "/" + this.jogador.getPontosVidaMax());
        Tela.esperarEnter();
    }
    
    // ========== ATO III: A CHEGADA EM THULE ==========
    Tela.limparTela();
    Tela.narrar("\n=== ATO III: A CHEGADA EM THULE ==="                   );
    Tela.narrar("\nFinalmente, após semanas no mar, vocês avistam..."     );
    Tela.narrar("Uma ilha verdejante, banhada por uma luz dourada. THULE.");
    Tela.narrar("O verão eterno existe!\n"                                );
    Tela.esperarEnter();
    
    Tela.narrar("Mas ao se aproximarem, vocês veem que a ilha é guardada por um clã rival,");
    Tela.narrar("os 'Lobos de Gelo', que já reivindicaram o lugar."                        );
    Tela.narrar("O líder deles, um Jarl gigantesco, os aguarda na praia.\n"                );
    Tela.esperarEnter();
    
    Tela.narrar("JARL: 'Esta terra sagrada é nossa! Voltem para seu inverno e morram,");
    Tela.narrar("ou deixem seus ossos em nossa praia!'\n"                             );
    Tela.esperarEnter();
    
    Tela.narrar("A jornada termina aqui. É lutar pela sobrevivência do seu povo");
    Tela.narrar("ou falhar no último obstáculo.\n"                              );
    Tela.esperarEnter();
    
    // COMBATE FINAL contra JARL
    Inimigo jarl = Inimigo.criarJarl();
    batalhar(jarl);
    
    // CONCLUSÕES
    Tela.limparTela();
    if (this.jogador.estaVivo()) {
        // VITÓRIA
        Tela.narrar("\n========================================"            );
        Tela.narrar("=== CONCLUSÃO: SUCESSO ==="                            );
        Tela.narrar("========================================\n"            );
        Tela.narrar("Com o Jarl derrotado, os Lobos de Gelo se rendem."     );
        Tela.narrar("Vocês conquistaram um novo lar para seu povo!"         );
        Tela.narrar("A saga da 'Runa Despertada' será cantada por gerações.");
        Tela.narrar("\n*** A MISSÃO FOI UM SUCESSO! ***\n");
        Tela.esperarEnter();
        
    } else {
        // DERROTA
        Tela.narrar("\n========================================" );
        Tela.narrar("=== CONCLUSÃO: FALHA ==="                   );
        Tela.narrar("========================================\n" );
        Tela.narrar("Você cai na areia da praia."                );
        Tela.narrar("A última visão que você tem é do Jarl rindo");
        Tela.narrar("enquanto sua tripulação é derrotada."       );
        Tela.narrar("A esperança do seu clã morre com você."     );
        Tela.narrar("\n*** A MISSÃO FALHOU. ***\n"               );
        Tela.esperarEnter();
    }
}
    public void batalhar(Inimigo inimigo) throws Exception {
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
                escolhaMapeada = escolha + 1; // 2->3 (Item), 3->4 (Fugir)
            }

            // --- Ações do Jogador E Contra-Ataque do Inimigo ---
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

                case 4: // Fugir
                    boolean fugiu = tentarFugir();
                    if (fugiu) {
                        return; // Sai da batalha
                    } else {
                        // Se falhou em fugir, inimigo contra-ataca
                        if (inimigo.estaVivo()) {
                            Tela.narrar("--- Turno do Inimigo ---");
                            executarTurno(inimigo, this.jogador); // Renomeado
                        }
                    }
                    // Turno foi gasto (tentativa de fuga)
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
            Tela.esperarEnter();
        } else {
            Tela.narrar("\n*** DERROTA ***");
            Tela.narrar("Você foi derrotado... Fim de jogo.");
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

        // Método para tentar fugir da batalha (usa rolagem de dados)
    private boolean tentarFugir() throws Exception {
        Tela.narrar("\n=== TENTANDO FUGIR ===");
        Tela.narrar("Você tenta escapar do combate...");
        Tela.esperarEnter();
        
        // Jogador rola um dado de 20 lados
        int dadoJogador = Dado.rolar(20);
        Tela.narrar("Você rolou: " + dadoJogador);
        
        // Precisa tirar 11 ou mais para fugir (55% de chance)
        if (dadoJogador >= 11) {
            Tela.narrar("\n[SUCESSO] Você conseguiu fugir da batalha!");
            Tela.esperarEnter();
            return true; // Fugiu com sucesso
        } else {
            Tela.narrar("\n[FALHA] Você não conseguiu fugir!");
            Tela.narrar("O inimigo aproveita sua hesitação...");
            Tela.esperarEnter();
            return false; // Não conseguiu fugir
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
        Tela.narrar(atacante.getNome() + " ataca com " + ataqueBaseComBonus +
                " + (" + resultadoDado + " no dado) = " + ataqueTotal + " de força!");

        alvo.receberDano(ataqueTotal);

        if (ataqueTotal <= defesaAlvo) {
            Tela.narrar(alvo.getNome() + " defende o ataque completamente!");
        } else {
            int danoParaNarrar = Math.max(0, ataqueTotal - defesaAlvo);
            Tela.narrar(alvo.getNome() + " defende " + defesaAlvo + " e recebe " + danoParaNarrar + " de dano!");
            Tela.narrar(" \n");
        }
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



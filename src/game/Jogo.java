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

        Tela.narrar("\nmodel.Personagem criado com sucesso!");
        Tela.imprimirStatus(this.jogador);
        Tela.esperarEnter();
        iniciarJornada();
    }

    private Personagem criarPersonagem() throws Exception {
    Tela.limparTela();
    Tela.narrar("--- Criação de model.Personagem ---");
    Tela.narrar("A energia da runa ressoa em você. Quem você se tornará?");
    Tela.narrar("1. O model.Berserker (Guerreiro): Força bruta indomável.");
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

    private void iniciarJornada() {
        Tela.limparTela();
        Tela.narrar("--- A Jornada Começa ---");
        Tela.narrar("Você reúne sua pequena tripulação e parte no 'Corvo Marinho'...");
        Tela.narrar("\n(Aqui a história continuará...)");
        Tela.esperarEnter();

        // [TAREFA PENDENTE: Implementar a lógica dos Atos I, II, III aqui]
        // Ex:
        // util.Tela.narrar("ATO I: O INVERNO SEM FIM");
        // util.Tela.narrar("Você avista uma ilha nebulosa...");
        // int decisao1 = util.Teclado.getUmInt(); ...
        // if (decisao1 == 1) {
        //    batalhar(model.Inimigo.criarDraugr());
        // } else { ... }
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


            boolean turnoGasto = false;

            // Mapeia a escolha se não for Oráculo
            int escolhaMapeada = escolha;
            if (this.jogador.getClass() != Oraculo.class && escolha > 1) {
                escolhaMapeada = escolha + 1; // 2->3 (Item), 3->4 (Fugir)
            }


            switch (escolhaMapeada) {
                case 1: // Ataque Básico
                    Tela.narrar("Você decide usar um ataque básico!");
                    tratarTurnoAtaquePadrao(inimigo);
                    turnoGasto = true;
                    break;

                case 2: // Usar Habilidade (Só Oráculo chega aqui com escolhaMapeada == 2)
                    if (this.jogador.getClass() == Oraculo.class) {
                        // Chama o método que trata o sub-menu.
                        // Ele retorna TRUE se uma habilidade foi usada, FALSE se escolheu "Voltar".
                        turnoGasto = tratarTurnoOraculo(inimigo);
                    } else {
                        Tela.narrar("Escolha inválida! Você perdeu seu turno.");
                        turnoGasto = true; // Perdeu o turno
                    }
                    break;

                case 3: // Usar Item
                    Tela.narrar("Função 'Usar Item' ainda em construção...");
                    // [TAREFA PENDENTE: Lógica de Usar Item]
                    turnoGasto = true; // Gasta o turno mesmo que falhe (ou mude isso depois)
                    break;

                case 4: // Fugir
                    Tela.narrar("Função 'Fugir' ainda em construção...");
                    // [TAREFA PENDENTE: Lógica de Fuga]
                    turnoGasto = true; // Gasta o turno mesmo que falhe (ou mude isso depois)
                    break;

                default:
                    Tela.narrar("Escolha inválida! Você perdeu seu turno.");
                    turnoGasto = true; // Perdeu o turno
                    break;
            }

            // Turno do Inimigo: Só acontece se o jogador GASTOU o turno
            // e se o inimigo ainda está vivo.
            if (turnoGasto && inimigo.estaVivo()) {
                Tela.narrar("--- Turno do Inimigo ---");
                // [LÓGICA DE IA DO INIMIGO PENDENTE]
                tratarTurnoAtaquePadrao(inimigo);
            }
            // Se turnoGasto for FALSE (porque o Oráculo escolheu "Voltar"),
            // o loop while recomeça, mostrando o menu principal novamente.
        }

        // Fim da Batalha (igual a antes)
        if (this.jogador.estaVivo()) {
            Tela.narrar("Você venceu a batalha!");
            Tela.esperarEnter();
        } else {
            Tela.narrar("Você foi derrotado. Fim de jogo.");
            Tela.esperarEnter();
        }
    }

    public boolean tratarTurnoOraculo(Inimigo inimigo) throws Exception {
        Tela.imprimirMenuOraculo();
        int escolhaMagia = -1;
        try{
            escolhaMagia = Teclado.getUmInt();
        }catch(Exception e){
            escolhaMagia = -1;
        }

        switch (escolhaMagia) {
            case 1:
                Tela.narrar(this.jogador.habilidadeEspecial(1)); // Chama habilidade 1 (Curar)
                return true; // Habilidade usada, turno GASTO
            case 2: // Habilidade: Amaldiçoar
                Tela.narrar(this.jogador.habilidadeEspecial(2)); // Chama habilidade 2 (Amaldiçoar)
                // [LÓGICA DA MALDIÇÃO PENDENTE NO ORACULO.JAVA]
                return true; // Habilidade usada, turno GASTO
            case 3: // Voltar
                Tela.narrar("Você decide esperar...");
                return true; // Escolheu voltar, turno NÃO GASTO
            default:
                Tela.narrar("Opção de magia inválida! Você perdeu seu turno.");
                return true; // Opção inválida, turno GASTO
        }
    }

    public void tratarTurnoAtaquePadrao(Inimigo inimigo) throws Exception {
        if(this.jogador.getClass() == Berserker.class){
            //serve para q o inimigo sempre ataque primeiro do q o berseker
            executarTurno(inimigo, this.jogador);
            if (this.jogador.estaVivo()){
                executarTurno(this.jogador, inimigo);
            }

        }else{
            //se no metodo q chama esse ja verificamos se ele é ou nao oraculo e aqui ja verificamos se ele é berseker entao so sobra o cacador;
            executarTurno(this.jogador, inimigo);
            if (inimigo.estaVivo()) {
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
            narracaoHabilidade = atacante.habilidadeEspecial();
            ataqueBaseComBonus = atacante.getAtaque();
            habilidadeAtivada = true;
        }


        if(atacante.getClass() == Cacador.class){
            if (Dado.rolar(100) <= 25){
                narracaoHabilidade = atacante.habilidadeEspecial();
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
        }
        if (habilidadeAtivada) {
            atacante.setAtaque(ataqueBase); // Volta ao ataque normal


            if (atacante.getClass() == Berserker.class) {
                atacante.setDefesa(defesaBaseOriginal);
            }
            Tela.narrar(atacante.getNome() + " volta ao normal.");
        }

    }


}



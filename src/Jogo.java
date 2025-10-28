public class Jogo {
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



    public void batalhar(Inimigo inimigo) throws Exception {
        Tela.narrar("Um " + inimigo.getNome() + " selvagem aparece");
        while(this.jogador.estaVivo() && inimigo.estaVivo()) {
            Tela.imprimirStatusBatalha(this.jogador, inimigo);
            Tela.imprimirMenuBatalha(this.jogador);

            int escolha = -1;
            try{
                escolha = Teclado.getUmInt();
            }catch(Exception e){
                Tela.narrar("Opcao invalida! Tente Novamente");
                continue;
            }

            switch (escolha){
                case 1: //atacar
                    if(this.jogador.getClass() == Oraculo.class){
                        tratarTurnoOraculo(inimigo);
                    }else{
                        tratarTurnoAtaquePadrao(inimigo);
                    }
                    break;
                case 2:
                    Tela.narrar("Função 'Usar item' ainda em construção");
                    executarTurno(inimigo, this.jogador);
                    break;

                case 3:

                    Tela.narrar("Função 'Fugir' ainda em construção");
                    executarTurno(inimigo, this.jogador);
                    break;

                default:
                    Tela.narrar("Escolha invalida! Vocé perdeu seu turno.");
                    executarTurno(inimigo, this.jogador);
                    break;

            }
        }
        if(this.jogador.estaVivo()){
            Tela.narrar("Vocé venceu a batalha");
        }else{
            Tela.narrar("Voce foi derrotado. Fim de jogo.");
        }
    }

    public void tratarTurnoOraculo(Inimigo inimigo) throws Exception {
        Tela.imprimirMenuOraculo();
        int escolhaMagia = -1;
        try{
            escolhaMagia = Teclado.getUmInt();
        }catch(Exception e){
            escolhaMagia = -1;
        }

        switch (escolhaMagia) {
            case 1:
                Tela.narrar(this.jogador.getNome() + " usa seu cajado de esmeralda para um ataque básico");
                executarTurno(this.jogador, inimigo);
                if (inimigo.estaVivo()) {
                    executarTurno(inimigo, this.jogador);
                }
                break;

            case 2:
                Tela.narrar(this.jogador.getNome() + " usa a runa de CURA!");
                //colocar o metodo para curar do oraculo

                if (inimigo.estaVivo()) {
                    executarTurno(inimigo, this.jogador);
                }
                break;
            case 3:
                Tela.narrar(this.jogador.getNome() + " usa a runa de AMALDIÇOAR!");
                // colocar metodo de amaldicoar do oraculo

                if (inimigo.estaVivo()) {
                    executarTurno(inimigo, this.jogador);
                }
                break;

            case 4:
                Tela.narrar("Você decide esperar...");
                // é para voltar para o menu, caso ele decida q ao inves de atacar vai usar um item do inventario
                break;

            default:
                Tela.narrar("Opção de magia inválida! Você perdeu seu turno.");
                executarTurno(inimigo, this.jogador);
                break;

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

        Tela.narrar(atacante.getNome() + " ataca com " + ataqueBase + " + (" + resultadoDado + " no dado) = " + ataqueTotal + " de força!");

    }
}

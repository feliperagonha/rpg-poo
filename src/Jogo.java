public class Jogo {
    private Personagem jogador;

    public void iniciar(){
        Tela.imprimirHistoria();


        System.out.println("\n\n--- FIM DA INTRODUÇÃO ---");
        System.out.println("O jogo está começando");
    }

    public void batalhar(Inimigo inimigo){
        Tela.narrar("Um " + inimigo.getNome() + " selvagem aparece");
        while(this.jogador.estaVivo() && inimigo.estaVivo()) {
            Tela.imprimirStatusBatalha(this.jogador, inimigo);
            Tela.imprimirMenuDeBatalha();

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

    public void tratarTurnoOraculo(Inimigo inimigo){
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

    public void tratarTurnoAtaquePadrao(Inimigo inimigo){
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

    public void executarTurno(Personagem atacante, Personagem alvo){
        int resultadoDado = Dado.rolar(6); //passando um dado de 6 lados

        int ataqueBase = atacante.getAtaque();

        //habilidades passivas berseker
        if(atacante.getClass() == Berserker.class && atacante.getPontosVida() < (150 * 0.5)){ //50% da vida maxima
            Tela.narrar("FURIA DO BERSEKER ATIVADA!");
            ataqueBase += 5;
        }

        int ataqueTotal = ataqueBase + resultadoDado;
        int defesaAlvo = alvo.getDefesa();

        int dano = ataqueTotal - defesaAlvo;

        //habilidade passiva do cacador
        if(atacante.getClass() == Cacador.class){
            if (Dado.rolar(100) <= 25){
                Tela.narrar("GOLPE CRITICO DO CAÇADOR");
                dano *= 2;
            }
        }

        Tela.narrar(atacante.getNome() + " ataca com " + ataqueBase + " + (" + resultadoDado + " no dado) = " + ataqueTotal + " de força!");

        if(dano > 0){
            Tela.narrar(alvo.getNome() + " defende " + defesaAlvo + " e recebe " + dano + " de dano!");
            alvo.receberDano(dano);
        }else{
            Tela.narrar(alvo.getNome() + " defende o ataque completamente!");
        }
    }
}

# 🛡️ A Runa Despertada

## 🎮 Descrição

**A Runa Despertada** é um **RPG de texto** ambientado na **Era Viking**, com fortes influências da **mitologia nórdica**.  
O jogador assume o papel de um herói que descobre possuir poderes mágicos concedidos por uma runa ancestral.  
A jornada o levará por uma **expedição épica** até uma ilha mítica, com o objetivo de **salvar sua vila de um inverno eterno**.  
Durante a aventura, o jogador poderá escolher entre **três classes distintas**, cada uma com seus próprios atributos, habilidades e desafios.

---

## ⚔️ Principais Funcionalidades (Features)

- **📖 História Imersiva:**  
  A narrativa é carregada a partir de um arquivo externo (`historia.txt`), permitindo fácil atualização da história sem alterar o código.

- **⌨️ Interface de Texto Dinâmica:**  
  O texto é exibido com um efeito de "máquina de escrever", letra por letra, acompanhado de um som nostálgico que remete aos RPGs clássicos.

- **🧹 Terminal Limpo:**  
  O terminal é limpo automaticamente após certas interações, garantindo uma experiência fluida e sem poluição visual.

- **🧠 Entrada de Dados Robusta:**  
  Uma classe utilitária (`util.Teclado.java`) é responsável por todo o gerenciamento de entrada do usuário, com tratamento de erros e validações para evitar falhas durante o jogo.

- **⚖️ Sistema de Classes Balanceado:**  
  Três classes jogáveis — **model.Berserker**, **Oráculo** e **Caçador** — cada uma com suas próprias vantagens, desvantagens e mecânicas únicas.

- **🎲 Sistema de Combate por Turnos:**  
  Combates baseados em atributos e rolagem de dados (D6), garantindo imprevisibilidade e estratégia nas batalhas.

---

## 💻 Tecnologias Utilizadas

- **Linguagem:** Java (padrão SE)  
- **Leitura e Escrita:** `BufferedReader` e `FileReader` para manipulação de arquivos e entrada via console  
- **Áudio:** Biblioteca `javax.sound.sampled.*` para reprodução de efeitos sonoros (`.wav`)  
- **Paradigma:** Programação Orientada a Objetos (POO)  

> ⚠️ **Observação:**  
> Certifique-se de que os arquivos `historia.txt` e `som_texto.wav` estejam na **pasta raiz** do projeto, pois o jogo os busca nesse diretório durante a execução.

---

## 🚀 Como Compilar e Executar

### 🧰 Pré-requisitos
- **JDK (Java Development Kit)** instalado (versão 8 ou superior)
- **Terminal** ou **Prompt de Comando** configurado para uso do `javac` e `java`

### 🔧 Compilação
No diretório raiz do projeto, execute:
```bash

javac -d out/production/rpg-poo -sourcepath src src\game\Main.java

▶️ Execução

Após a compilação, rode o jogo com:

java -cp out/production/rpg-poo game.Main



👨‍💻 Autor e Contexto

Autor: Bruno César e Felipe Ragonha

Instituição: Pontifícia Universidade Católica de Campinas (PUC-Campinas)

Disciplina: Paradigmas e Programação Orientada a Objetos

Descrição:
Este projeto foi desenvolvido com fins acadêmicos, explorando os princípios de Programação Orientada a Objetos, boas práticas de código, e imersão narrativa em jogos de texto.

🧭 Licença

Este projeto é de uso educacional e não comercial.
Sinta-se à vontade para estudar, modificar e aprimorar o código, desde que as devidas atribuições sejam mantidas.

“Os ventos do norte sussurram segredos antigos... a runa despertou.”

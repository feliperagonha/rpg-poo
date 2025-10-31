# 🗡️ A Runa Despertada

Um RPG de texto ambientado na Era Viking, onde você lidera uma expedição épica para salvar sua vila do inverno eterno.

---

## 🚀 Como Compilar e Executar

### Pré-requisitos
- **JDK (Java Development Kit)** instalado no sistema
- Terminal (PowerShell/CMD no Windows, ou Terminal no Linux/Mac)

### Estrutura de Arquivos
- **Pasta `historia/`**: Contém todos os arquivos de narrativa (`.txt`)
- **Arquivo `som_texto.wav`**: Efeito sonoro (deve estar na pasta raiz)

### Compilação
Execute o seguinte comando a partir da **raiz do projeto**:
**Use esse comando antes de rodar os proximos para que o terminal mostre os emojis**
```powershell
  chcp 65001
```
```powershell
javac -d out/production/rpg-poo -sourcepath src src\game\Main.java
```

### Execução
Após compilar, execute o jogo com:

```powershell
java -cp out/production/rpg-poo game.Main
```

---

## 📖 Descrição do Projeto

**A Runa Despertada** é um RPG textual imersivo que transporta o jogador para a Era Viking. Após descobrir uma runa ancestral misteriosa, você se torna o líder de uma expedição crucial para salvar sua vila do **Fimbulvetr** — o inverno eterno que ameaça destruir tudo. Sua jornada o levará até a ilha mítica de **Thule**, onde desafios mortais e decisões épicas aguardam.

O jogo combina narrativa rica, sistema de combate estratégico e mecânicas de inventário, tudo apresentado através de uma interface de console imersiva com efeitos visuais e sonoros.

---

## ⚔️ Principais Funcionalidades

### 🎭 **Motor de História Externo**
- Toda a narrativa, diálogos e menus são carregados dinamicamente de arquivos `.txt` localizados na pasta `historia/`
- Permite edição e expansão da história sem modificar o código-fonte
- Facilita a manutenção e localização do conteúdo narrativo

### 🖥️ **UI de Console Imersiva**
- Classe `Tela` dedicada gerencia toda a saída visual do jogo
- **Efeito "máquina de escrever"**: Texto exibido letra por letra para criar imersão
- **Efeito sonoro dinâmico**: Reproduz `som_texto.wav` a cada caractere usando `javax.sound.sampled`
- **Limpeza de tela**: Utiliza comandos nativos (`cls` no Windows, `clear` no Linux/Mac) para manter o terminal organizado

### ⌨️ **Tratamento de Entrada Robusto**
- Classe `Teclado` gerencia toda entrada do usuário via `BufferedReader`
- Tratamento de exceções (`NumberFormatException`) previne crashes por entrada inválida
- Validação de opções de menu garante experiência fluida

### 📦 **Estrutura de Pacotes POO**
Código organizado seguindo princípios de Programação Orientada a Objetos:
- **`game`**: Lógica principal do jogo e fluxo de execução
- **`model`**: Entidades do jogo (personagens, itens, inventário)
- **`util`**: Ferramentas utilitárias (tela, teclado, dados)

### 🎒 **Sistema de Inventário Completo**
- Implementação de `Inventario` e `Item` com lógica de empilhamento
- **Sistema de "stack"**: Usa `equals()` para agrupar itens idênticos
- **Ordenação alfabética**: Implementa `Bubble Sort` manual usando `compareTo()`
- **Cópia profunda**: Método `clone()` para duplicar inventários de inimigos derrotados

### ⚡ **Sistema de Batalha Híbrido** (FUNCIONALIDADE CRÍTICA)

#### Habilidades Passivas (Automáticas)
- **Berserker (Guerreiro)**: Ativa "Fúria" automaticamente quando HP < 50%, aumentando o dano
- **Caçador (Arqueiro)**: 25% de chance de acerto crítico automático em cada ataque
- Verificadas e ativadas dentro do método `Jogo.executarTurno()`

#### Habilidades Ativas (Sob Demanda)
- **Oráculo (Mago)**: Única classe com acesso ao botão "2. Usar Habilidade"
- Abre sub-menu específico (`Tela.imprimirMenuOraculo()`) com opções:
  - **Curar**: Restaura HP do Oráculo
  - **Amaldiçoar**: Reduz defesa do inimigo temporariamente

---

## 📁 Estrutura de Pacotes

```
src/
├── game/
│   ├── Main.java          # Ponto de entrada da aplicação
│   └── Jogo.java          # Motor principal do jogo
├── model/
│   ├── Personagem.java    # Classe abstrata base
│   ├── Guerreiro.java     # Berserker (passiva: Fúria)
│   ├── Mago.java          # Oráculo (ativa: Curar/Amaldiçoar)
│   ├── Arqueiro.java      # Caçador (passiva: Crítico)
│   ├── Inimigo.java       # Entidades hostis
│   ├── Inventario.java    # Gerenciamento de itens
│   └── Item.java          # Estrutura de dados de itens
└── util/
    ├── Tela.java          # Gerenciamento de saída (View)
    ├── Teclado.java       # Gerenciamento de entrada (Controller)
    └── Dado.java          # Gerador de números aleatórios
```

---

## 🛡️ Visão Geral das Classes e Métodos

### **`game.Main`**
Ponto de entrada da aplicação. Instancia e inicializa o objeto `Jogo`, delegando o controle do fluxo principal.

### **`game.Jogo`**
O **motor central** do jogo. Responsável por:
- Gerenciar o fluxo narrativo (`iniciar()`, `novoJogo()`, `iniciarJornada()`, `ato1()`, etc.)
- Orquestrar o sistema de combate (`batalhar()`, `tratarTurnoAtaquePadrao()`, `tratarTurnoOraculo()`)
- Executar a lógica de turnos (`executarTurno()`), incluindo ativação de habilidades passivas

### **`model.Personagem`** ⭐
Classe **abstrata** que serve como base para todas as entidades do jogo.

**Responsabilidades:**
- Gerenciar atributos fundamentais (`pontosVidaMax`, `pontosVida`, `ataque`, `defesa`)
- Validar valores através de `throws Exception` em construtores e setters
- Implementar `receberDano()` com cálculo de defesa
- Implementar `curar()` com verificação de HP máximo
- Suportar `clone()` para cópia profunda de objetos

### **Subclasses de Personagem**

#### **`model.Guerreiro` (Berserker)**
- Define stats balanceados com ênfase em HP e ataque
- **Habilidade Passiva**: "Fúria" ativa automaticamente com HP < 50%

#### **`model.Mago` (Oráculo)**
- Stats equilibrados entre ataque e utilidade
- **Habilidades Ativas**: Sobrescreve `habilidadeEspecial(int escolha, Personagem alvo)` para permitir escolha entre "Curar" e "Amaldiçoar"

#### **`model.Arqueiro` (Caçador)**
- Stats focados em ataque com defesa moderada
- **Habilidade Passiva**: 25% de chance de acerto crítico automático

### **`model.Inimigo`**
Subclasse de `Personagem` representando adversários.

**Características:**
- Métodos de fábrica estáticos (ex: `criarDraugr()`, `criarJotunn()`)
- Define stats e comportamentos específicos de cada tipo de inimigo
- Herda toda a lógica de combate de `Personagem`

### **`model.Inventario`**
Gerenciador de coleção de itens com funcionalidades avançadas.

**Funcionalidades:**
- Implementa `Cloneable` para cópia profunda (essencial para saque de inimigos)
- Ordenação automática de itens (alfabética via `Bubble Sort`)
- Sistema de empilhamento inteligente usando `Item.equals()`

### **`model.Item`**
Estrutura de dados para itens do jogo.

**Características:**
- Implementa `Comparable<Item>` para ordenação alfabética
- Sobrescreve `equals()` para permitir empilhamento de itens idênticos
- Gerencia nome, descrição e quantidade

### **`util.Tela`** (View)
Camada de **apresentação** que gerencia toda a saída visual.

**Métodos Principais:**
- `imprimirArquivoDeHistoria()`: Lê arquivos `.txt` com pausas em marcadores `---`
- `imprimirArquivoTxt()`: Lê arquivos `.txt` sem pausas (menus rápidos)
- `imprimirMenuOraculo()`: Sub-menu específico para habilidades do Oráculo
- Efeito "máquina de escrever" com som integrado

### **`util.Teclado`** (Controller)
Camada de **controle** que gerencia toda a entrada do usuário.

**Características:**
- Utiliza `BufferedReader` para leitura eficiente
- Métodos robustos com tratamento de exceções
- Validação de entrada para prevenir crashes

### **`util.Dado`**
Classe utilitária para geração de números aleatórios.

**Funcionalidade:**
- Método `rolar(int lados)`: Simula rolagem de dados usando `Math.random()`
- Usado para mecânicas de chance (críticos, esquivas, etc.)

---

## 👥 Autores e Contexto

**Desenvolvedores:**
- Bruno César
- Felipe Ragonha

**Contexto Acadêmico:**  
Projeto desenvolvido para a disciplina **"Paradigmas e Programação Orientada a Objetos" (PPOO)** na **PUC-Campinas**.

---

## 🎮 Sobre o Jogo

Entre na pele de um herói viking e embarque em uma jornada épica através de terras gélidas e perigosas. Escolha sua classe, gerencie seu inventário, tome decisões estratégicas em combate e desvende os mistérios da runa ancestral. O destino de sua vila está em suas mãos!

**Que os deuses nórdicos guiem sua espada!** ⚔️🛡️

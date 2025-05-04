# Interpretador REPL em Java

Este projeto é um **interpretador simples estilo REPL (Read-Eval-Print Loop)**, implementado em Java, inspirado no Capítulo 2 do livro texto da disciplina.

Ele permite que o usuário:

- Avalie expressões matemáticas básicas (`+`, `-`, `*`, `/`)
- Declare variáveis com valores de expressões
- Reutilize variáveis em outras expressões
- Interaja com o sistema de forma contínua até digitar `sair`

---

## Funcionalidades

- [x] Atribuição de variáveis (`x = 10 + 5`)
- [x] Avaliação de expressões (`x * 2`)
- [x] Reutilização de variáveis
- [x] Interface REPL (loop de leitura/execução contínua)

---

## Como executar

### Pré-requisitos:
- Java instalado (JDK 8 ou superior)
- Terminal de comando (cmd, PowerShell ou bash)

### Passos:

```bash
javac InterpretadorREPL.java
java InterpretadorREPL

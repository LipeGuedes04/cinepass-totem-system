# 🏢 Sistema de Autoatendimento para Totens de Cinema - CinePass

## 🎯 O que o projeto faz?

O sistema simula o funcionamento de um totem de autoatendimento de cinema (CinePass). O usuário interage com uma interface de terminal para selecionar o **nome do filme**, escolher o tipo de entrada (**Inteira ou Meia-Entrada**) e definir a **quantidade de ingressos**. O programa valida as entradas em tempo real, permite acessar uma **bomboniere virtual** para adicionar combos de pipoca e refrigerante, processa o pagamento (com validação de saldo incremental para dinheiro) e emite os bilhetes digitais com o cálculo exato do troco.

### 🔄 Fluxo da Aplicação
```text
[ Início/Totem ] ➔ [ Escolha do Filme & Ingressos ] ➔ [ Menu Bomboniere ] ➔ [ Tela de Pagamento ] ➔ [ Emissão de Ingressos & Troco ]
```

---

## 🎨 Design do Código (Como as regras conversam)

Este projeto foi construído utilizando uma arquitetura de controle baseada em **Máquinas de Estado com Loops While**, permitindo navegar de forma fluida e não linear entre menus, telas de compras e validações compulsórias. Veja a estrutura limpa do design:

```text
       ┌────────────────────────┐
       │   Abertura do Totem    │ (Controle de inicialização/espera)
       └───────────┬────────────┘
                   │
                   ▼
       ┌────────────────────────┐
       │   Seleção de Filme     │ ◄───┐ (Loop principal ativo)
       └───────────┬────────────┘     │
                   │                  │
                   ▼                  │
       ┌────────────────────────┐     │ (Reiniciar nova compra)
       │ Validação de Ingressos │     │
       └───────────┬────────────┘     │
                   │                  │
                   ▼                  │
       ┌────────────────────────┐     │
       │   Menu Bomboniere      │ ────┤
       └───────────┬────────────┘
                   │
                   ▼
       ┌────────────────────────┐
       │   Fluxo de Pagamento   │ (Validação de cédulas e troco)
       └────────────────────────┘
```

### 📋 Mapeamento de Variáveis e Estruturas

| Estrutura / Variável | O que ela faz no código? |
| :--- | :--- |
| 🚀 `CineTotem.java` | Classe principal que envelopa toda a inteligência de controle de fluxo e regras do cinema. |
| 🎬 `nomeFilme` | String que captura o título do filme escolhido pelo cliente no terminal. |
| 🎫 `precoIngresso` | Ponto flutuante (double) definido dinamicamente (R\$ 30,00 para inteira ou R\$ 15,00 para meia). |
| 🔢 `qtdIngressos` | Inteiro que define a quantidade de assentos, protegido contra valores menores que 1. |
| 🍿 `qtdCombo` | Armazena a quantidade de combos de pipoca + refrigerante (R\$ 25,00 cada) adicionados na bomboniere. |
| 💳 `totalPedido` | Variável global acumuladora que soma dinamicamente ingressos, produtos e calcula o saldo final. |

---

## 🧠 Conceitos de Programação Praticados

* **Navegação por Estados Complexos:** Uso de variáveis controladoras (`menuSistema`) associadas a laços `while` independentes para saltar ou avançar telas (como pular a bomboniere direto para o pagamento).
* **Validação de Filtro de Entrada:** Loops internos que bloqueiam o avanço do terminal enquanto o usuário digitar opções inválidas de ingressos ou quantidades incorretas.
* **Amortização Dinâmica de Cédulas:** Algoritmo de caixa que retém o fluxo de pagamento em dinheiro e calcula de forma incremental quanto falta até cobrir o valor total devido.

---

## 💻 Exemplo Prático de Uso

Imagine a seguinte simulação de uma compra de ingresso de meia-entrada e adição de combo de pipoca rodando direto no totem:

```text
=== BEM-VINDO AO CINEPASS ===
Deseja iniciar o seu atendimento?
1 - Iniciar 
2 - Desligar Totem
1

--- SELEÇÃO DE FILME ---
Qual o nome do filme que deseja assistir? 
Interestelar
Selecione o tipo de ingresso: 
1 - Inteira (R\$ 30,00) 
2 - Meia-Entrada (R\$ 15,00)
2
Quantos ingressos deseja comprar? 
2

Deseja ir para a bomboniere ou fechar o pedido?
1 - Comprar Pipoca & Combo 
2 - Fechar Pedido direto 
3 - Cancelar Tudo
1

--- BOMBONIERE CINEPASS ---
Deseja adicionar o Combo Mega (Pipoca + Refri) por R\$ 25,00? (S/N)
S
Quantos combos deseja adicionar? 
1
🎉 1 Combo(s) adicionado(s) com sucesso!
Subtotal atual do pedido: R\$ 55.00

1 - Adicionar mais combos \n4 - Avançar para o Pagamento
4

--- TELA DE PAGAMENTO ---
Filme Escolhido: Interestelar
Total a pagar: R\$ 55.00

Escolha a forma de pagamento: 
1 - Dinheiro (Totem aceita notas) 
2 - Cartão/Pix
1
Insira as notas no totem (Valor Pago R\$): 50.00
❌ Saldo insuficiente! Falta R\$ 5.00. Insira mais notas: 10.00

🎟️ Compra aprovada! Retire seu troco de R\$ 5.00 reais.

=================================
  OBRIGADO E TENHA UM BOM FILME! 
=================================

Deseja iniciar uma nova compra no totem?
1 - Sim, Iniciar 
2 - Não, Desligar
2
```

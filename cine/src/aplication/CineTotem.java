package aplication;

import java.util.Scanner;


public class CineTotem {

	public static void main(String[] args) {

		String nomeFilme;
		double precoIngresso = 0.0;
		int qtdIngressos;
		double totalPedido = 0.0;

		Scanner totem = new Scanner(System.in);

		System.out.println("=== BEM-VINDO AO CINEPASS ===");
		System.out.println("Deseja iniciar o seu atendimento?");
		System.out.println("1 - Iniciar \n2 - Desligar Totem");
		int menuSistema = totem.nextInt();
		totem.nextLine();

		while (menuSistema == 2) {
			System.out.println("Totem em modo de espera... Insira 1 para iniciar.");
			menuSistema = totem.nextInt();
			totem.nextLine();
		}

		while (menuSistema == 1) {

			System.out.println("\n--- SELEÇÃO DE FILME ---");
			System.out.println("Qual o nome do filme que deseja assistir? ");
			nomeFilme = totem.nextLine();

			System.out.println("Selecione o tipo de ingresso: \n1 - Inteira (R$ 30,00) \n2 - Meia-Entrada (R$ 15,00)");
			int tipoIngresso = totem.nextInt();
			totem.nextLine();

	
			while (tipoIngresso != 1 && tipoIngresso != 2) {
				System.out.println("❌ Opção inválida! Escolha 1 para Inteira ou 2 para Meia:");
				tipoIngresso = totem.nextInt();
				totem.nextLine();
			}

	
			precoIngresso = (tipoIngresso == 1) ? 30.00 : 15.00;

			System.out.println("Quantos ingressos deseja comprar? ");
			qtdIngressos = totem.nextInt();
			totem.nextLine();

		
			while (qtdIngressos < 1) {
				System.out.println("❌ Quantidade inválida! Digite pelo menos 1 ingresso:");
				qtdIngressos = totem.nextInt();
				totem.nextLine();
			}

		
			totalPedido += (precoIngresso * qtdIngressos);

			System.out.println("\nDeseja ir para a bomboniere ou fechar o pedido?");
			System.out.println("1 - Comprar Pipoca & Combo \n2 - Fechar Pedido direto \n3 - Cancelar Tudo");
			menuSistema = totem.nextInt();
			totem.nextLine();

		
			while (menuSistema == 1) {
				System.out.println("\n--- BOMBONIERE CINEPASS ---");
				System.out.println("Deseja adicionar o Combo Mega (Pipoca + Refri) por R$ 25,00? (S/N)");
				String querCombo = totem.next();
				totem.nextLine();

				if (querCombo.equalsIgnoreCase("S")) {
					System.out.println("Quantos combos deseja adicionar? ");
					int qtdCombo = totem.nextInt();
					totem.nextLine();

					while (qtdCombo < 1) {
						System.out.println("❌ Digite uma quantidade válida (mínimo 1):");
						qtdCombo = totem.nextInt();
						totem.nextLine();
					}

					double valorCombos = qtdCombo * 25.00;
					totalPedido += valorCombos;
					System.out.printf("🎉 %d Combo(s) adicionado(s) com sucesso!\n", qtdCombo);
				}

				System.out.printf("Subtotal atual do pedido: R$ %.2f\n", totalPedido);
				System.out.println("\n1 - Adicionar mais combos \n4 - Avançar para o Pagamento");
				menuSistema = totem.nextInt();
				totem.nextLine();
			}

			
			while (menuSistema == 4 || menuSistema == 2) { 
			
				System.out.println("\n--- TELA DE PAGAMENTO ---");
				System.out.printf("Filme Escolhido: %s\n", nomeFilme);
				System.out.printf("Total a pagar: R$ %.2f\n", totalPedido);
				System.out.println("\nEscolha a forma de pagamento: \n1 - Dinheiro (Totem aceita notas) \n2 - Cartão/Pix");
				int opcaoPgto = totem.nextInt();
				totem.nextLine();

				if (opcaoPgto == 1) {
					System.out.print("Insira as notas no totem (Valor Pago R$): ");
					double valorPago = totem.nextDouble();
					totem.nextLine();

					
					while (valorPago < totalPedido) {
						double falta = totalPedido - valorPago;
						System.out.printf("❌ Saldo insuficiente! Falta R$ %.2f. Insira mais notas: ", falta);
						double novaNota = totem.nextDouble();
						totem.nextLine();
						valorPago += novaNota;
					}

					
					if (valorPago > totalPedido) {
						double troco = valorPago - totalPedido;
						System.out.printf("\n🎟️ Compra aprovada! Retire seu troco de R$ %.2f reais.\n", troco);
					} else {
						System.out.println("\n🎟️ Compra aprovada com valor exato!");
					}

				} else {
					System.out.println("\n🎟️ Transação aprovada no cartão! Imprimindo ingressos...");
				}

				
				menuSistema = 3;
			}

		
			while (menuSistema == 3) {
				System.out.println("\n=================================");
				System.out.println("  OBRIGADO E TENHA UM BOM FILME! ");
				System.out.println("=================================");
				System.out.println("\nDeseja iniciar uma nova compra no totem?");
				System.out.println("1 - Sim, Iniciar \n2 - Não, Desligar");
				menuSistema = totem.nextInt();
				totem.nextLine();
			}
		}

		System.out.println("Encerrando totem com segurança...");
		totem.close();
	}
}

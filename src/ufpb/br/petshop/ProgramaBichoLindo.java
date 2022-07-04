package ufpb.br.petshop;

import javax.swing.JOptionPane;

import java.io.IOException;
import java.util.List;

public class ProgramaBichoLindo {

	public static void main(String[] args) {
		PetShop bichoLindo = new PetShop("Bicho Lindo", "João Pessoa", "5543-90.1234", "@BichoLindoooo");

		boolean continuar = true;
		try {
			bichoLindo.recuperarDados();
			JOptionPane.showMessageDialog(null, "Dados recuperados");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		} catch (AnimalNaoExisteNaListaException | TratamentoNaoExistenteException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		while (continuar) {
			String opcao = JOptionPane.showInputDialog("Menu\n" + "1.Cadastrar Animal\n"
					+ "2.Finalizar o Tratamento do animal\n" + "3.Pesquisar animais na Lista que Comeca Com\n"
					+ "4.Pesquisar Animais Não Atendidos\n" + "5.Adicionar Tratamento ao animal\n" + "6.Preco Total\n"
					+ "7. Pagar Atendimento\n" + "8.Sair\n");
			switch (opcao) {
			case "1":
				try {
					String nome = JOptionPane.showInputDialog("Nome:");
					String raca = JOptionPane.showInputDialog("Raça:");
					String tamanho = JOptionPane.showInputDialog("Tamanho:");
					int idade = Integer.parseInt(JOptionPane.showInputDialog("Idade:"));
					Animal animal = new Animal(nome, raca, tamanho, idade);
					boolean cadastrou = bichoLindo.cadastrarAnimal(animal);
					if (cadastrou) {
						JOptionPane.showMessageDialog(null, "O animal foi cadastrado com sucesso");
					}
				} catch (JaContemAnimalException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
					JOptionPane.showMessageDialog(null, "Ocorreu um erro na digitação da idade. Tente novamente");
				}
				break;
			case "2":
				try {
					String nome = JOptionPane.showInputDialog("Nome:");
					String raca = JOptionPane.showInputDialog("Raça:");
					String tamanho = JOptionPane.showInputDialog("Tamanho:");
					int idade = Integer.parseInt(JOptionPane.showInputDialog("Idade:"));
					Animal animal = new Animal(nome, raca, tamanho, idade);
					String nomeTratamento = JOptionPane.showInputDialog("Nome do Tratamento:");
					boolean finalizou = bichoLindo.finalizarTratamento(animal, nomeTratamento);
					if (finalizou) {
						JOptionPane.showMessageDialog(null, "O atendimento do animal foi finalizado com sucesso");
					}
				} catch (ListaTratamentoVaziaException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
					JOptionPane.showMessageDialog(null,
							"Ocorreu um erro. Verifique se o tipo de taratamento foi cadastrado na lista do animal");
				} catch (TratamentoNaoExistenteException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
					JOptionPane.showMessageDialog(null, "Verifique se você digitou os dados corretamente");
				} catch (AnimalNaoExisteNaListaException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
					JOptionPane.showMessageDialog(null, "Verifique se você digitou os dados corretamente");
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
					JOptionPane.showMessageDialog(null, "Ocorreu um erro na digitação da idade. Tente novamente");
				}
				break;
			case "3":
				char prefixo = JOptionPane.showInputDialog("Prefixo:").charAt(0);
				List<Animal> animaisComecaCom = bichoLindo.pesquisarAnimaisComecaCom(prefixo);
				if (animaisComecaCom.size() == 0) {
					JOptionPane.showMessageDialog(null,
							"Nenhum dos animais da Lista tem o nome que começa com " + prefixo);
				} else {
					for (Animal a : animaisComecaCom) {
						JOptionPane.showMessageDialog(null, a.toString());
					}
				}
				break;
			case "4":
				List<Animal> animaisNaoAtendidos = bichoLindo.pesquisarAnimaisNaoAtendidos();
				if (animaisNaoAtendidos.size() == 0) {
					JOptionPane.showMessageDialog(null, "Todos os animais da lista foram atendidos");
				} else {
					for (Animal a : animaisNaoAtendidos) {
						JOptionPane.showMessageDialog(null, a.toString());
					}
				}
				break;
			case "5":
				try {
					String nome = JOptionPane.showInputDialog("Nome:");
					String raca = JOptionPane.showInputDialog("Raça:");
					String tamanho = JOptionPane.showInputDialog("Tamanho:");
					int idade = Integer.parseInt(JOptionPane.showInputDialog("Idade:"));
					Animal animal = new Animal(nome, raca, tamanho, idade);
					String tratamento = JOptionPane.showInputDialog("Tipo de Tratamento\n" + "1.BANHO\n"
							+ "2.HIDRATAÇÃO\n" + "3.TOSA NA MÁQUINA\n" + "4.TOSA NA TESOURA\n" + "5.TOSA HIGIÊNICA\n"
							+ "6.DESEMBARAÇAMENTO\n" + "7.TINGIMENTO DOS PELOS\n" + "8.ESCOVAÇÃO DE DENTES\n"
							+ "9.LIMPEZA DE OUVIDO\n" + "10.CORTE DE UNHAS\n");
					boolean adicionado = bichoLindo.adicionarTratamento(animal, tratamento);
					if (adicionado) {
						JOptionPane.showMessageDialog(null, "O tratamento foi adiconado a lista com sucesso");
					}
				} catch (AnimalNaoExisteNaListaException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
					JOptionPane.showMessageDialog(null,
							"Tente Novamente. Verifique se os dados foram digitados corretamente");
				} catch (TratamentoNaoExistenteException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
					JOptionPane.showMessageDialog(null, "Ocorreu um erro na digitação da idade. Tente novamente");
				}
				break;
			case "6":
				try {
					String nome = JOptionPane.showInputDialog("Nome:");
					String raca = JOptionPane.showInputDialog("Raça:");
					String tamanho = JOptionPane.showInputDialog("Tamanho:");
					int idade = Integer.parseInt(JOptionPane.showInputDialog("Idade:"));
					Animal animal = new Animal(nome, raca, tamanho, idade);
					double precoTotal = bichoLindo.precoTotal(animal);
					JOptionPane.showMessageDialog(null, "O preço do atendimento foi de R$" + precoTotal);
				} catch (AnimalNaoExisteNaListaException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
					JOptionPane.showMessageDialog(null, "TENTE NOVAMENTE");
				}
				break;
			case "7":
				try {
					String nome = JOptionPane.showInputDialog("Nome:");
					String raca = JOptionPane.showInputDialog("Raça:");
					String tamanho = JOptionPane.showInputDialog("Tamanho:");
					int idade = Integer.parseInt(JOptionPane.showInputDialog("Idade:"));
					Animal animal = new Animal(nome, raca, tamanho, idade);
					boolean pagou = bichoLindo.pagarAtendimento(animal);
					if (pagou) {
						JOptionPane.showMessageDialog(null, "PAGAMENTO EFETUADO COM SUCESSO");
						JOptionPane.showMessageDialog(null,
								"OBRIGADO PELA PREFERÊNCIA A FAMÍLIA  DO PET SHOP BICHO LINDO AGRADECE");
					}
				} catch (AnimalNaoExisteNaListaException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
					JOptionPane.showMessageDialog(null, "TENTE NOVAMENTE");
				}
				break;
			case "8":
				try {
					bichoLindo.salvarDados();
					JOptionPane.showMessageDialog(null, "Os dados foram salvos");
					continuar = false;
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
				break;
			default:
				JOptionPane.showMessageDialog(null, "Opção Inválida");
				break;
			}
		}

		JOptionPane.showMessageDialog(null, "FIM DO PROGRAMA!!");

	}
}

package ufpb.br.petshop;

import java.util.List;
import java.util.ArrayList;
import java.io.IOException;

public class PetShop implements PetShopInterface {

	private String nome;
	private String endereco;
	private String cnpj;
	private String instagram;
	private List<Animal> animais;
	private GravadorDeDados gravador;
	private GravadorDeDados gravadorTratamentos;

	public PetShop(String nome, String endereco, String cnpj, String instagram) {
		this.nome = nome;
		this.endereco = endereco;
		this.cnpj = cnpj;
		this.instagram = instagram;
		this.animais = new ArrayList<>();
		this.gravador = new GravadorDeDados("animais.txt");
		this.gravadorTratamentos = new GravadorDeDados("tratamento.txt");
	}

	@Override
	public boolean cadastrarAnimal(Animal animal) throws JaContemAnimalException {
		if (this.animais.contains(animal)) {
			throw new JaContemAnimalException("A lista já possui o animal cadastrado");
		} else {
			this.animais.add(animal);
			return true;
		}
	}

	@Override
	public boolean finalizarTratamento(Animal animal, String nomeTratamento)
			throws ListaTratamentoVaziaException, TratamentoNaoExistenteException, AnimalNaoExisteNaListaException {
		if (this.animais.contains(animal)) {
			for (Animal a : this.animais) {
				if (a.equals(animal)) {
					if (a.getNomeTratamentos().size() == 0) {
						throw new ListaTratamentoVaziaException(
								"Nehum tipo de tratamento foi encontrado na lista do animal");
					} else {
						return a.retirarTratamento(nomeTratamento);
					}
				}
			}
		}

		throw new AnimalNaoExisteNaListaException("O Animal não está na Lista");
	}

	@Override
	public List<Animal> pesquisarAnimaisNaoAtendidos() {
		return this.animais;
	}

	@Override
	public List<Animal> pesquisarAnimaisComecaCom(char prefixo) {
		List<Animal> animaisComecaCom = new ArrayList<>();
		for (Animal a : this.animais) {
			if (a.getNome().charAt(0) == prefixo) {
				animaisComecaCom.add(a);
			}
		}
		return animaisComecaCom;
	}

	@Override
	public boolean adicionarTratamento(Animal animal, String nomeTratamento)
			throws AnimalNaoExisteNaListaException, TratamentoNaoExistenteException {
		if (this.animais.contains(animal)) {
			for (Animal a : this.animais) {
				if (a.equals(animal)) {
					a.incrementarTratamento(nomeTratamento);
					return true;
				}
			}
		}
		throw new AnimalNaoExisteNaListaException("O Animal não está na Lista");
	}

	@Override
	public double precoTotal(Animal animal) throws AnimalNaoExisteNaListaException {
		if (this.animais.contains(animal)) {
			for (Animal a : this.animais) {
				if (a.equals(animal)) {
					return a.getValorTotal();
				}
			}
		}
		throw new AnimalNaoExisteNaListaException("O Animal não está na Lista");
	}

	@Override
	public boolean pagarAtendimento(Animal animal) throws AnimalNaoExisteNaListaException {
		if (this.animais.contains(animal)) {
			for (Animal a : this.animais) {
				if (a.equals(animal)) {
					this.animais.remove(a);
					return true;
				}
			}
		}
		throw new AnimalNaoExisteNaListaException("Animal não existe na lista");
	}

	@Override
	public void salvarDados() throws IOException {
		List<String> texto = new ArrayList<>();
		List<String> tratamentos = new ArrayList<>();
		List<String> textoTratametos = new ArrayList<>();
		for (Animal a : this.animais) {
			String linha = null;
			linha = (a.getNome() + "#" + a.getRaca() + "#" + a.getTamanho() + "#" + a.getIdade());
			texto.add(linha);
		}
		this.gravador.gravaTextoEmArquivo(texto);

		for (Animal a : this.animais) {
			tratamentos = a.getNomeTratamentos();
			String linhaTratamentos = tratamentos.get(0);
			for (int i = 1; i < tratamentos.size(); i++) {
				linhaTratamentos += "#" + tratamentos.get(i);
			}
			textoTratametos.add(linhaTratamentos);
		}
		this.gravadorTratamentos.gravaTextoEmArquivo(textoTratametos);
	}

	@Override
	public void recuperarDados() throws IOException, TratamentoNaoExistenteException, AnimalNaoExisteNaListaException {
		List<String> texto = this.gravador.recuperarTextoDeArquivo();
		List<String> nomeTratamentos = this.gravadorTratamentos.recuperarTextoDeArquivo();
		int tamanho = texto.size();
		String[][] animais = new String[tamanho][2];
		for (int i = 0; i < tamanho; i++) {
			String linha = texto.get(i);
			animais[i][0] = linha;
			String linhaTratamento = nomeTratamentos.get(i);
			animais[i][1] = linhaTratamento;
		}
		for (int k = 0; k < tamanho; k++) {
			String[] dados = new String[4];
			dados = animais[k][0].split("#");
			Animal a = new Animal(dados[0], dados[1], dados[2], Integer.parseInt(dados[3]));
			this.animais.add(a);
			String[] tratamentos = animais[k][1].split("#");
			for (int g = 0; g < tratamentos.length; g++) {
				adicionarTratamento(a, tratamentos[g]);
			}
		}

	}

}
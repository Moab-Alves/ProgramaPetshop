package ufpb.br.petshop;

import java.util.List;
import java.io.IOException;

public interface PetShopInterface {

	boolean cadastrarAnimal(Animal animal) throws JaContemAnimalException;

	boolean finalizarTratamento(Animal animal, String nomeTratamento)
			throws ListaTratamentoVaziaException, TratamentoNaoExistenteException, AnimalNaoExisteNaListaException;

	List<Animal> pesquisarAnimaisNaoAtendidos();

	List<Animal> pesquisarAnimaisComecaCom(char prefixo);

	boolean adicionarTratamento(Animal animal, String nomeTratamento)
			throws AnimalNaoExisteNaListaException, TratamentoNaoExistenteException;

	double precoTotal(Animal animal) throws AnimalNaoExisteNaListaException;

	boolean pagarAtendimento(Animal animal) throws AnimalNaoExisteNaListaException;

	void salvarDados() throws IOException;

	void recuperarDados() throws IOException, TratamentoNaoExistenteException, AnimalNaoExisteNaListaException;
}

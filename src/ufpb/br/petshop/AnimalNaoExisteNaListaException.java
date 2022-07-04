package ufpb.br.petshop;

public class AnimalNaoExisteNaListaException extends Exception{

    public AnimalNaoExisteNaListaException(String mensagem){
        super(mensagem);
    }
}

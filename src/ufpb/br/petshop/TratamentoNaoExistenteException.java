package ufpb.br.petshop;

public class TratamentoNaoExistenteException extends Exception{

    public TratamentoNaoExistenteException(String mensagem){
        super(mensagem);
    }
}

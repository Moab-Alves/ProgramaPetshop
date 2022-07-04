package ufpb.br.petshop;

import java.util.ArrayList;
import java.util.Objects;
import java.util.List;

public class Animal {

    private String nome;
    private String raca;
    private String tamanho;
    private int idade;
    private double valorTotal;
    private List<Double> precosTratamentos;
    private List<String> nomeTratamentos;

    public Animal(String nome, String raca, String tamanho, int idade){
        this.nome = nome;
        this.raca = raca;
        this.tamanho = tamanho;
        this.idade = idade;
        this.precosTratamentos = new ArrayList<>();
        this.nomeTratamentos = new ArrayList<>();
        this.valorTotal = 0.0;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
    
    public double getValorTotal() {
    	return this.valorTotal;
    }
    

    public List<Double> getPrecosTratamentos(){
        return this.precosTratamentos;
    }

    public boolean incrementarTratamento(String nomeTratamento) throws TratamentoNaoExistenteException{
        if (nomeTratamento.equals("banho")) {
            precosTratamentos.add(Tratamento.TIPO_BANHO);
            this.valorTotal += Tratamento.TIPO_BANHO;
            nomeTratamentos.add(nomeTratamento);
            return true;
        } else if (nomeTratamento.equals("hidratação")) {
            precosTratamentos.add(Tratamento.TIPO_HIDRATACAO);
            this.valorTotal += Tratamento.TIPO_HIDRATACAO;
            nomeTratamentos.add(nomeTratamento);
            return true;
        } else if (nomeTratamento.equals("tosa na máquina")) {
            precosTratamentos.add(Tratamento.TIPO_TOSA_NA_MAQUINA);
            this.valorTotal += Tratamento.TIPO_TOSA_NA_MAQUINA;
            nomeTratamentos.add(nomeTratamento);
            return true;
        } else if (nomeTratamento.equals("tosa na tesoura")) {
            precosTratamentos.add(Tratamento.TIPO_TOSA_NA_TESOURA);
            this.valorTotal += Tratamento.TIPO_TOSA_NA_TESOURA;
            nomeTratamentos.add(nomeTratamento);
            return true;
        } else if (nomeTratamento.equals("tosa higiênica")) {
            precosTratamentos.add(Tratamento.TIPO_TOSA_HIGIENICA);
            this.valorTotal += Tratamento.TIPO_TOSA_HIGIENICA;
            nomeTratamentos.add(nomeTratamento);
            return true;
        } else if (nomeTratamento.equals("desembaraçamento")) {
            precosTratamentos.add(Tratamento.TIPO_DESEMBARACAMENTO);
            this.valorTotal += Tratamento.TIPO_DESEMBARACAMENTO;
            nomeTratamentos.add(nomeTratamento);
            return true;
        } else if (nomeTratamento.equals("tingimento dos pelos")) {
            precosTratamentos.add(Tratamento.TIPO_TINGIMENTO_DOS_PELOS);
            this.valorTotal += Tratamento.TIPO_TINGIMENTO_DOS_PELOS;
            nomeTratamentos.add(nomeTratamento);
            return true;
        } else if (nomeTratamento.equals("escovação de dentes")) {
            precosTratamentos.add(Tratamento.TIPO_ESCOVACAO_DE_DENTES);
            this.valorTotal += Tratamento.TIPO_ESCOVACAO_DE_DENTES;
            nomeTratamentos.add(nomeTratamento);
            return true;
        } else if (nomeTratamento.equals("limpeza de ouvido")) {
            precosTratamentos.add(Tratamento.TIPO_LIMPEZA_DE_OUVIDO);
            this.valorTotal += Tratamento.TIPO_LIMPEZA_DE_OUVIDO;
            nomeTratamentos.add(nomeTratamento);
            return true;
        } else if (nomeTratamento.equals("corte de unhas")) {
            precosTratamentos.add(Tratamento.TIPO_CORTE_DE_UNHAS);
            this.valorTotal += Tratamento.TIPO_CORTE_DE_UNHAS;
            nomeTratamentos.add(nomeTratamento);
            return true;
        }
        throw new TratamentoNaoExistenteException("O tratamento informado não é oferecido pelo Pet Shop");
    }

    public boolean retirarTratamento(String nomeTratamento) throws TratamentoNaoExistenteException{
        for (int i  = 0; i < nomeTratamentos.size(); i++){
            if(nomeTratamentos.get(i).equals(nomeTratamento)){
                this.nomeTratamentos.remove(nomeTratamento);
                this.precosTratamentos.remove(i);
                return true;
            }
        }
        throw new TratamentoNaoExistenteException("O tratamento informado não está na lista");
    }

    public List<String> getNomeTratamentos(){
        return this.nomeTratamentos;
    }
    
  
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return idade == animal.idade && nome.equals(animal.nome) && raca.equals(animal.raca) && tamanho.equals(animal.tamanho);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, raca, tamanho, idade);
    }

    public StringBuilder texto(){
        StringBuilder texto1 = new StringBuilder();
        texto1.append("TRATAMENTOS:\n--------------------------------------\n");
        for(int i = 0; i < this.nomeTratamentos.size(); i++){
            texto1.append("Nome: " + this.nomeTratamentos.get(i) + ", Preço: " + this.precosTratamentos.get(i)+ "\n");
            texto1.append("------------------------------------------\n");
        }
        return texto1;
    }

    @Override
    public String toString(){
        return "Dados do Animal:\n"
                +"Nome: " + this.nome + "\n"
                +"Raça: " + this.raca + "\n"
                +"Tamanho: " + this.tamanho + "\n"
                +"Idade: " + this.idade + "\n"
                + texto();
    }

}

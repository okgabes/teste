
public class Cliente {
    private String nome;
    private double valorConsumido = 0;

    public Cliente(String nome){
        setNome(nome);
    }

    public Cliente(String nome, double valorConsumido){
        setNome(nome);
        setValorConsumido(valorConsumido);
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setValorConsumido(double valorConsumido) {
        this.valorConsumido = valorConsumido;
    }
    public double getValorConsumido() {
        return valorConsumido;
    }
    
    //faz a soma e salva no valor valorConsumido
    public void somarValorConsumido(double valor) {
        double valorSomado = getValorConsumido() + valor;
        setValorConsumido(valorSomado);
    }

    @Override
	public String toString() {
		return  " | " + this.getNome() + " | " + this.getValorConsumido();
	}
    
}

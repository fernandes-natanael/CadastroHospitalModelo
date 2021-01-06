package dados;

public class PessoaSaudavel extends Pessoa {
	private Integer idade;

	public PessoaSaudavel(String nome, char sexo, int idade) {
		super(nome, sexo);
		this.setIdade(idade);
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String toString() {
		return "Indentificador: " + this.getIndentificador() + "\nNome: " + getNome().toUpperCase() + "\nGenero: "
				+ retornaSexo(this.getSexo()) + "\nIdade: " + getIdade();
	}
}

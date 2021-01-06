package dados;

public class PessoaContaminada extends Pessoa {
	private Character situacao;

	public PessoaContaminada(String nome, char sexo, char situacao) {
		super(nome, sexo);
		this.setSituacao(situacao);
	}

	public char getSituacao() {
		return situacao;
	}

	public void setSituacao(Character situacao) {
		this.situacao = situacao;
	}

	public static String retornaSituacao(char situacao) {
		return situacao == 'C' ? "CURADO(A)"
				: situacao == 'E' ? "EM TRATAMENTO" : situacao == 'F' ? "FALECIDO(A)" : "-";
	}

	public String toString() {
		return "Indentificador: " + this.getIndentificador() + "\nNome: " + getNome().toUpperCase() + "\nGenero: "
				+ retornaSexo(this.getSexo()) + "\nSituacao: " + retornaSituacao(getSituacao());
	}
}

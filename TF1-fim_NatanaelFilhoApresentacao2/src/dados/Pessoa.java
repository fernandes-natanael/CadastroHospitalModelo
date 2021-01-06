package dados;

import dao.PessoaDAO;

public abstract class Pessoa {
	private StringBuilder nome;
	private Character sexo;
	private Integer indentificador;

	public Pessoa(String nome, char sexo) {
		this.setNome(nome);
		this.setSexo(sexo);
	}

	public String getNome() {
		return nome.toString();
	}

	public void setNome(String nome) {
		this.nome = new StringBuilder(nome);
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(Character sexo) {
		this.sexo = sexo;
	}

	public void setIndentificador(Integer indentificador) {
		this.indentificador = indentificador;
	}

	public Integer getIndentificador() {
		return indentificador;
	}

	public static String retornaSexo(char sexo) {
		return sexo == 'M' ? "MASCULINO" : sexo == 'F' ? "FEMININO" : "-";
	}

	public void cadastrarPessoa(Pessoa pessoa) {
		new PessoaDAO().cadastrarPessoa(pessoa);
	}
}

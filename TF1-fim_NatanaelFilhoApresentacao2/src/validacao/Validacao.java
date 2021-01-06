package validacao;

import dados.PessoaContaminada;
import dados.PessoaSaudavel;
import visao.Visao;

public class Validacao {

	public static boolean isSaudavelValida(String nome, char sexo, int idade) {
		final int MINIMO = 3, MAXIMO = 90;
		if (nome.trim().length() >= MINIMO && nome.trim().length() <= MAXIMO && nome != null) {
			PessoaSaudavel pessoa = new PessoaSaudavel(nome, sexo, idade);
			pessoa.cadastrarPessoa(pessoa);
			return true;
		}
		Visao.janelaErro("Nome devera ter minimo de " + MINIMO + " e maximo de " + MAXIMO);
		return false;
	}

	public static boolean isContaminadoValida(String nome, char sexo, char situacao) {
		final int MINIMO = 3, MAXIMO = 90;
		if (nome.trim().length() >= MINIMO && nome.trim().length() <= MAXIMO && nome != null) {
			PessoaContaminada pessoa = new PessoaContaminada(nome, sexo, situacao);
			pessoa.cadastrarPessoa(pessoa);
			return true;
		}
		Visao.janelaErro("Nome devera ter minimo de " + MINIMO + " e maximo de " + MAXIMO);
		return false;
	}

}

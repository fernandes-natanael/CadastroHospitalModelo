package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import dados.ColecaoPessoas;
import dados.Pessoa;
import dados.PessoaContaminada;
import dados.PessoaSaudavel;
import interfaces.AnalisaDados;
import ordenadoras.OrdenaPorIndentificador;
import ordenadoras.OrdenaPorNome;
import visao.Visao;

public class PessoaDAO implements AnalisaDados {

	public void cadastrarPessoa(Pessoa pessoa) {
		String query = "insert into pessoa(nome, genero, saude, idade) value (?,?,?,?)";
		Connection conector = null;
		try {
			PreparedStatement preparador = null;
			conector = new Conexao().getConnection();
			preparador = conector.prepareStatement(query);
			preparador.setString(1, pessoa.getNome());
			preparador.setString(2, String.valueOf(pessoa.getSexo()));
			if (pessoa instanceof PessoaSaudavel) {
				preparador.setString(3, null);
				preparador.setInt(4, ((PessoaSaudavel) pessoa).getIdade());
			} else {
				preparador.setString(3, String.valueOf(((PessoaContaminada) pessoa).getSituacao()));
				preparador.setString(4, null);
			}
			preparador.execute();
			ResultSet chaves = preparador.executeQuery("select last_insert_id();");
			chaves.next();
			int indentificador = chaves.getInt(1);
			preparador.close();
			Visao.janelaPadrao("Cadastro bem sucedido\nSeu indentificador: " + indentificador);
		} catch (SQLException e) {
			Visao.janelaErro("Erro inesperado. Cadastro nao efetuado: " + e.getMessage());
		} finally {
			try {
				if (conector != null)
					conector.close();
			} catch (SQLException e) {
				Visao.janelaErro("Conector não fechou corretamentes");
			}
		}
	}

	public void relatorioEspecifico() {
		int pessoaSaudaveis = 0;
		int pessoaEmTratamento = 0;
		int pessoaCuradas = 0;
		int mulheresFalecidas = 0;
		int homensFalecidos = 0;
		int totalPessoas = 0;
		Connection conector = null;
		try {
			PreparedStatement preparador = null;
			conector = new Conexao().getConnection();
			String query = "SELECT * FROM pessoa";
			preparador = conector.prepareStatement(query);
			ResultSet montador = preparador.executeQuery(query);

			while (montador.next()) {
				if (montador.getString("saude") == null)
					pessoaSaudaveis++;
				else {
					switch (montador.getString("saude").charAt(0)) {
					case 'C':
						pessoaCuradas++;
						break;
					case 'E':
						pessoaEmTratamento++;
						break;
					case 'F':
						if (montador.getString("genero").charAt(0) == 'F')
							mulheresFalecidas++;
						else
							homensFalecidos++;
					}
				}
				totalPessoas++;
			}
			preparador.close();
			Visao.relatorioFinal(new int[] { pessoaSaudaveis, pessoaEmTratamento, pessoaCuradas, mulheresFalecidas,
					homensFalecidos, totalPessoas });
		} catch (SQLException e) {
			Visao.mensagemConsole("Erro inesperado: " + e.getMessage() + "\n\nPrograma Finalizado sem relatorio Final");

		} finally {
			try {
				if (conector != null)
					conector.close();
			} catch (SQLException e) {
				Visao.janelaErro("Conector não fechou corretamentes");
			}
		}

	}

	public void resultadoConsulta(int indentificarConsulta) {
		Connection conector = null;
		try {
			Pessoa pessoa = null;
			PreparedStatement preparador = null;
			conector = new Conexao().getConnection();
			String query = "SELECT * FROM pessoa WHERE idPessoa = " + indentificarConsulta;
			preparador = conector.prepareStatement(query);
			ResultSet montador = preparador.executeQuery(query);

			if (montador.next()) {
				if (montador.getString("saude") == null)
					pessoa = new PessoaSaudavel(montador.getString("nome"), montador.getString("genero").charAt(0),
							montador.getInt("idade"));
				else
					pessoa = new PessoaContaminada(montador.getString("nome"), montador.getString("genero").charAt(0),
							montador.getString("saude").charAt(0));
				pessoa.setIndentificador(montador.getInt("idPessoa"));
				Visao.janelaConsulta(pessoa.toString());
			} else
				Visao.janelaConsulta("Paciente especifico nao foi encontrado");
			preparador.close();

		} catch (SQLException e) {
			Visao.janelaErro(("Erro: " + e.getMessage()));
		} finally {
			try {
				if (conector != null)
					conector.close();
			} catch (SQLException e) {
				Visao.janelaErro("Conector não fechou corretamentes");
			}
		}

	}

	public List<Pessoa> listaPorIndentificador() {
		ColecaoPessoas grupo = new ColecaoPessoas();
		Connection conector = null;
		try {
			conector = new Conexao().getConnection();
			String query = "select * from pessoa";
			PreparedStatement preparador = conector.prepareStatement(query);
			ResultSet montador = preparador.executeQuery();
			while (montador.next()) {
				Pessoa pessoa;
				if (montador.getString("saude") == null)
					pessoa = new PessoaSaudavel(montador.getString("nome"), montador.getString("genero").charAt(0),
							montador.getInt("idade"));
				else
					pessoa = new PessoaContaminada(montador.getString("nome"), montador.getString("genero").charAt(0),
							montador.getString("saude").charAt(0));

				pessoa.setIndentificador(montador.getInt("idPessoa"));
				grupo.setPaciente(pessoa);
			}
			preparador.close();
		} catch (SQLException e) {
			Visao.janelaErro("Listagem cancelada.\nErro inesperado: " + e.getMessage());
			grupo = null;
		} finally {
			Collections.sort(grupo.getListaPacientes(), new OrdenaPorIndentificador());
			try {
				if (conector != null)
					conector.close();
			} catch (SQLException e) {
				Visao.janelaErro("Conector não fechou corretamentes");
			}
		}
		if (grupo.getListaPacientes().isEmpty())
			throw new NullPointerException();
		else
			return grupo.getListaPacientes();

	}

	public List<Pessoa> listaPesquisados(String verificar) {
		ColecaoPessoas grupo = new ColecaoPessoas();
		Connection conector = null;
		try {
			PreparedStatement preparador = null;
			ResultSet montador = null;
			conector = new Conexao().getConnection();
			String query = "SELECT * FROM pessoa ORDER by nome";
			preparador = conector.prepareStatement(query);
			montador = preparador.executeQuery(query);
			while (montador.next()) {
				if (montador.getString("nome").toUpperCase().contains(verificar.toUpperCase())) {
					Pessoa pessoa;
					if (montador.getString("saude") == null)
						pessoa = new PessoaSaudavel(montador.getString("nome"), montador.getString("genero").charAt(0),
								montador.getInt("idade"));
					else
						pessoa = new PessoaContaminada(montador.getString("nome"),
								montador.getString("genero").charAt(0), montador.getString("saude").charAt(0));
					pessoa.setIndentificador(montador.getInt("idPessoa"));
					grupo.setPaciente(pessoa);
				}
			}
			preparador.close();
		} catch (SQLException e) {
			Visao.janelaErro("Pesquisa cancelada.\nErro inesperado: " + e.getMessage());
			grupo = null;
		} finally {
			Collections.sort(grupo.getListaPacientes(), new OrdenaPorNome());
			try {
				if (conector != null)
					conector.close();
			} catch (SQLException e) {
				Visao.janelaErro("Conector não fechou corretamentes");
			}
		}

		if (grupo.getListaPacientes().isEmpty())
			throw new NullPointerException();
		else
			return grupo.getListaPacientes();
	}
}

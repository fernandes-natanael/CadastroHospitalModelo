package interfaces;

import java.util.List;

import dados.Pessoa;

public interface AnalisaDados {

	List<Pessoa> listaPorIndentificador();

	List<Pessoa> listaPesquisados(String verificar);

	void resultadoConsulta(int indentificador);

	void relatorioEspecifico();

}

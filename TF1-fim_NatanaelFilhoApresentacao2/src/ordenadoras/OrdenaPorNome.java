package ordenadoras;

import java.util.Comparator;

import dados.Pessoa;

public class OrdenaPorNome implements Comparator<Pessoa> {

	public int compare(Pessoa p1, Pessoa p2) {
		return p1.getNome().compareTo(p2.getNome());
	}

}

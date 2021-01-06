package ordenadoras;

import java.util.Comparator;
import dados.Pessoa;

public class OrdenaPorIndentificador implements Comparator<Pessoa> {

	public int compare(Pessoa p1, Pessoa p2) {
		return p1.getIndentificador().compareTo(p1.getIndentificador());
	}

}

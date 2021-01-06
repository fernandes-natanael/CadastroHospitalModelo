package dados;

import java.util.ArrayList;
import java.util.List;

public class ColecaoPessoas {
	List<Pessoa> listaPacientes;

	public ColecaoPessoas() {
		listaPacientes = new ArrayList<Pessoa>();
	}

	public List<Pessoa> getListaPacientes() {
		return listaPacientes;
	}

	public void setPaciente(Pessoa pacientes) {
		this.listaPacientes.add(pacientes);
	}
}

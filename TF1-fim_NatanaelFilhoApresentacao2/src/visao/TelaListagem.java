package visao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import dados.Pessoa;
import dados.PessoaContaminada;
import dados.PessoaSaudavel;

public class TelaListagem extends JFrame {
	private JPanel painel;
	private JTable listaPacientes;

	public TelaListagem(List<Pessoa> lista) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Hospital Modelo");
		setBounds(100, 100, 800, 500);
		setResizable(false);
		setLocationRelativeTo(null);

		painel = new JPanel();
		setContentPane(painel);
		painel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		painel.add(scrollPane, BorderLayout.CENTER);

		listaPacientes = new JTable();
		listaPacientes.setFont(new Font("Arial", Font.PLAIN, 16));
		listaPacientes.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null }, },
				new String[] { "ID", "Nome", "Genero", "Situacao", "Idade" }) {
		});
		listaPacientes.getColumnModel().getColumn(0).setPreferredWidth(30);
		listaPacientes.getColumnModel().getColumn(1).setPreferredWidth(100);
		listaPacientes.getColumnModel().getColumn(2).setPreferredWidth(15);
		listaPacientes.getColumnModel().getColumn(3).setPreferredWidth(40);
		listaPacientes.getColumnModel().getColumn(4).setPreferredWidth(43);
		listaPacientes.setRowHeight(20);
		DefaultTableModel modelo = (DefaultTableModel) listaPacientes.getModel();
		modelo.setNumRows(0);
		for (Pessoa pessoa : lista) {
			if (pessoa instanceof PessoaSaudavel)
				modelo.addRow(new Object[] { pessoa.getIndentificador(), pessoa.getNome().toUpperCase(),
						Pessoa.retornaSexo(pessoa.getSexo()), "-", ((PessoaSaudavel) pessoa).getIdade() });
			else
				modelo.addRow(new Object[] { pessoa.getIndentificador(), pessoa.getNome().toUpperCase(),
						Pessoa.retornaSexo(pessoa.getSexo()),
						PessoaContaminada.retornaSituacao(((PessoaContaminada) pessoa).getSituacao()), "-", });
		}

		JTableHeader cabecalho = listaPacientes.getTableHeader();
		cabecalho.setBackground(Color.LIGHT_GRAY);
		cabecalho.setFont(new Font("Arial", Font.PLAIN, 20));
		scrollPane.setViewportView(listaPacientes);

		JButton botaoFechar = new JButton("Fechar");
		botaoFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		painel.add(botaoFechar, BorderLayout.SOUTH);

		JLabel tamanhoLista = new JLabel("Resultado da lista: " + lista.size() + " pacientes encontrados");
		tamanhoLista.setFont(new Font("Arial", Font.PLAIN, 20));
		painel.add(tamanhoLista, BorderLayout.NORTH);
		setVisible(true);
	}
}

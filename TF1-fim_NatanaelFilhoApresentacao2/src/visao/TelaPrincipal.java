package visao;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.PessoaDAO;
import visao.TelaCadastroContaminado;
import visao.TelaCadastroSaudavel;
import visao.TelaPrincipal;

public class TelaPrincipal extends JFrame {

	private JPanel painel;
	private JTextField textFieldConsulta;
	private JTextField textFieldPesquisa;

	public TelaPrincipal() {
		setResizable(false);
		setTitle("Hospital Modelo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 350);
		setLocationRelativeTo(null);

		painel = new JPanel();
		setContentPane(painel);
		painel.setLayout(null);

		textFieldConsulta = new JTextField();
		textFieldConsulta.setFont(new Font("Arial", Font.PLAIN, 15));
		textFieldConsulta.setBounds(474, 117, 116, 30);
		painel.add(textFieldConsulta);
		textFieldConsulta.setColumns(10);

		textFieldPesquisa = new JTextField();
		textFieldPesquisa.setFont(new Font("Arial", Font.PLAIN, 15));
		textFieldPesquisa.setBounds(474, 189, 116, 30);
		painel.add(textFieldPesquisa);
		textFieldPesquisa.setColumns(10);
		getContentPane().setLayout(null);

		JButton botaoPessoaSaudavel = new JButton("Cadastrar Saudavel");
		botaoPessoaSaudavel.setMnemonic('1');
		botaoPessoaSaudavel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaCadastroSaudavel();
			}
		});
		botaoPessoaSaudavel.setFont(new Font("Arial", Font.PLAIN, 24));
		botaoPessoaSaudavel.setBounds(30, 35, 390, 75);
		getContentPane().add(botaoPessoaSaudavel);

		JButton botaoPessoaContaminado = new JButton("Cadastrar Contaminado ");
		botaoPessoaContaminado.setMnemonic('2');
		botaoPessoaContaminado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaCadastroContaminado();
			}
		});
		botaoPessoaContaminado.setFont(new Font("Arial", Font.PLAIN, 24));
		botaoPessoaContaminado.setBounds(30, 145, 390, 75);
		painel.add(botaoPessoaContaminado);

		JButton botaoSair = new JButton("Sair");
		botaoSair.setBounds(30, 233, 124, 50);
		painel.add(botaoSair);
		botaoSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new PessoaDAO().relatorioEspecifico();
				System.exit(0);
			}
		});
		botaoSair.setFont(new Font("Arial", Font.PLAIN, 26));
		botaoSair.setMnemonic('S');

		JButton botaoListar = new JButton("Listar");
		botaoListar.setMnemonic('L');
		botaoListar.setFont(new Font("Arial", Font.PLAIN, 20));
		botaoListar.setBounds(474, 35, 135, 42);
		botaoListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent acao) {
				try {
					new TelaListagem(new PessoaDAO().listaPorIndentificador());
				} catch (NullPointerException e) {
					Visao.janelaPadrao("Dados não encontrados para a listagem");
				}
			}
		});
		painel.add(botaoListar);

		JButton botaoConsultar = new JButton("Consultar");
		botaoConsultar.setMnemonic('C');
		botaoConsultar.setFont(new Font("Arial", Font.PLAIN, 20));
		botaoConsultar.setBounds(606, 115, 135, 33);
		botaoConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new PessoaDAO().resultadoConsulta(Integer.parseInt(textFieldConsulta.getText()));
				} catch (NumberFormatException e) {
					Visao.janelaErro("Insira indentificador corretamente para consultar");
				}
				textFieldConsulta.setText("");
			}

		});
		painel.add(botaoConsultar);

		JButton botaoPesquisar = new JButton("Pesquisar");
		botaoPesquisar.setMnemonic('P');
		botaoPesquisar.setFont(new Font("Arial", Font.PLAIN, 20));
		botaoPesquisar.setBounds(602, 189, 139, 31);
		botaoPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent acao) {
				try {
					new TelaListagem(new PessoaDAO().listaPesquisados(textFieldPesquisa.getText()));
				} catch (NullPointerException e) {
					Visao.janelaPadrao("Pesquisa não encontrou resultados");
				}
				textFieldPesquisa.setText("");
			}
		});
		painel.add(botaoPesquisar);
		setVisible(true);
	}
}

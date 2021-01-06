package visao;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import validacao.Validacao;

public class TelaCadastroContaminado extends JFrame {
	private JPanel painel;
	private JTextField textFieldNome;

	public TelaCadastroContaminado() {
		setResizable(false);
		setTitle("Hospital Modelo");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		painel = new JPanel();
		setContentPane(painel);
		painel.setLayout(null);

		// Relacionados ao nome
		JLabel labelNome = new JLabel("Nome:");
		labelNome.setFont(new Font("Arial", Font.PLAIN, 20));
		labelNome.setBounds(40, 50, 60, 20);
		painel.add(labelNome);

		textFieldNome = new JTextField();
		textFieldNome.setFont(new Font("Arial", Font.PLAIN, 20));
		textFieldNome.setBounds(100, 50, 300, 30);
		painel.add(textFieldNome);
		textFieldNome.setColumns(10);

		// Relacionados ao Genero
		ButtonGroup grupoSexo = new ButtonGroup();

		JLabel labelSexo = new JLabel("Genero:");
		labelSexo.setFont(new Font("Arial", Font.PLAIN, 20));
		labelSexo.setBounds(40, 130, 72, 16);
		painel.add(labelSexo);

		JRadioButton radioButtonMasculino = new JRadioButton("Masculino");
		radioButtonMasculino.setFont(new Font("Arial", Font.PLAIN, 20));
		radioButtonMasculino.setActionCommand("M");
		radioButtonMasculino.setBounds(130, 121, 125, 25);
		radioButtonMasculino.setSelected(true);
		painel.add(radioButtonMasculino);

		JRadioButton radioButtonFeminino = new JRadioButton("Feminino");
		radioButtonFeminino.setFont(new Font("Arial", Font.PLAIN, 20));
		radioButtonFeminino.setActionCommand("F");
		radioButtonFeminino.setBounds(259, 121, 127, 25);
		painel.add(radioButtonFeminino);

		grupoSexo.add(radioButtonMasculino);
		grupoSexo.add(radioButtonFeminino);

		// Relacionados o situacao
		ButtonGroup grupoSituacao = new ButtonGroup();

		JLabel labelSitucao = new JLabel("Situacao:");
		labelSitucao.setFont(new Font("Arial", Font.PLAIN, 20));
		labelSitucao.setBounds(40, 205, 82, 16);
		painel.add(labelSitucao);

		JRadioButton radioCurado = new JRadioButton("Curado");
		radioCurado.setFont(new Font("Arial", Font.PLAIN, 20));
		radioCurado.setActionCommand("C");
		radioCurado.setSelected(true);
		radioCurado.setBounds(130, 200, 95, 25);
		painel.add(radioCurado);

		JRadioButton radioTratamento = new JRadioButton("Em Tramento");
		radioTratamento.setFont(new Font("Arial", Font.PLAIN, 20));
		radioTratamento.setActionCommand("E");
		radioTratamento.setBounds(230, 200, 147, 25);
		painel.add(radioTratamento);

		JRadioButton radioFalecido = new JRadioButton("Falecido");
		radioFalecido.setFont(new Font("Arial", Font.PLAIN, 20));
		radioFalecido.setActionCommand("F");
		radioFalecido.setBounds(380, 202, 103, 25);
		painel.add(radioFalecido);

		grupoSituacao.add(radioCurado);
		grupoSituacao.add(radioTratamento);
		grupoSituacao.add(radioFalecido);

		JButton botaoCadastrar = new JButton("Cadastrar");
		botaoCadastrar.setFont(new Font("Arial", Font.PLAIN, 20));
		botaoCadastrar.setBounds(40, 280, 125, 33);
		botaoCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent acao) {
				if (Validacao.isContaminadoValida(textFieldNome.getText().trim(),
						grupoSexo.getSelection().getActionCommand().charAt(0),
						grupoSituacao.getSelection().getActionCommand().charAt(0)))
					dispose();
			}
		});
		painel.add(botaoCadastrar);

		JButton botaoCancelar = new JButton("Cancelar");
		botaoCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		botaoCancelar.setFont(new Font("Arial", Font.PLAIN, 20));
		botaoCancelar.setBounds(175, 280, 125, 33);
		painel.add(botaoCancelar);
		setVisible(true);
	}
}

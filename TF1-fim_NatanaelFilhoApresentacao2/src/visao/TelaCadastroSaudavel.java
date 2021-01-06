package visao;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import validacao.Validacao;

public class TelaCadastroSaudavel extends JFrame {

	private JTextField textFieldNome;

	public TelaCadastroSaudavel() {
		setResizable(false);
		setTitle("Hospital Modelo");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		// Coisas do painel relacionadas ao NOME
		JLabel labelNome = new JLabel("Nome:");
		labelNome.setFont(new Font("Arial", Font.PLAIN, 20));
		labelNome.setBounds(40, 50, 60, 20);
		getContentPane().add(labelNome);

		textFieldNome = new JTextField();
		textFieldNome.setFont(new Font("Arial", Font.PLAIN, 20));
		textFieldNome.setBounds(100, 50, 300, 30);
		getContentPane().add(textFieldNome);
		textFieldNome.setColumns(10);

		// Coisas do painel relacionadas ao SEXO
		JLabel labelSexo = new JLabel("Genero:");
		labelSexo.setFont(new Font("Arial", Font.PLAIN, 20));
		labelSexo.setBounds(41, 128, 72, 16);
		getContentPane().add(labelSexo);

		ButtonGroup grupoSexo = new ButtonGroup();
		JRadioButton radioButtonMasculino = new JRadioButton("Masculino");
		radioButtonMasculino.setFont(new Font("Arial", Font.PLAIN, 20));
		radioButtonMasculino.setActionCommand("M");
		radioButtonMasculino.setSelected(true);
		radioButtonMasculino.setBounds(130, 121, 125, 25);
		getContentPane().add(radioButtonMasculino);

		JRadioButton radioButtonFeminino = new JRadioButton("Feminino");
		radioButtonFeminino.setFont(new Font("Arial", Font.PLAIN, 20));
		radioButtonFeminino.setActionCommand("F");
		radioButtonFeminino.setBounds(259, 121, 127, 25);
		getContentPane().add(radioButtonFeminino);

		getContentPane().add(radioButtonFeminino);

		grupoSexo.add(radioButtonMasculino);
		grupoSexo.add(radioButtonFeminino);

		// Coisas do painel relacionadas a IDADE
		JLabel labelIdade = new JLabel("Idade:");
		labelIdade.setFont(new Font("Arial", Font.PLAIN, 20));
		labelIdade.setBounds(41, 198, 56, 16);
		getContentPane().add(labelIdade);

		JSpinner spinnerIdade = new JSpinner();
		spinnerIdade.setFont(new Font("Arial", Font.PLAIN, 20));
		spinnerIdade.setModel(new SpinnerNumberModel(20, 0, 130, 1));
		spinnerIdade.setBounds(132, 185, 50, 34);
		getContentPane().add(spinnerIdade);

		JButton botaoCadastrar = new JButton("Cadastrar");
		botaoCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent acao) {
				if (Validacao.isSaudavelValida(textFieldNome.getText(),
						grupoSexo.getSelection().getActionCommand().charAt(0),
						Integer.parseInt(spinnerIdade.getValue().toString())))
					dispose();
			}
		});
		botaoCadastrar.setFont(new Font("Arial", Font.PLAIN, 20));
		botaoCadastrar.setBounds(69, 284, 125, 34);
		getContentPane().add(botaoCadastrar);

		JButton botaoCancelar = new JButton("Cancelar");
		botaoCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		botaoCancelar.setFont(new Font("Arial", Font.PLAIN, 20));
		botaoCancelar.setBounds(206, 284, 125, 34);
		getContentPane().add(botaoCancelar);
		setVisible(true);
	}
}

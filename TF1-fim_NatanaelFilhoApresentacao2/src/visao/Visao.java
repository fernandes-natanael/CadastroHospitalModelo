package visao;

import javax.swing.JOptionPane;

public class Visao {

	public static void limpaTela(int limite) {
		for (int i = 0; i < limite; i++)
			System.out.println();
	}

	public static void espacaTela(int limite) {
		for (int i = 0; i < limite; i++)
			System.out.print(" ");
	}

	public static String colocaEspacos(int limite) {
		StringBuilder espacos = new StringBuilder();
		for (int i = 0; i < limite; i++)
			espacos.append(" ");
		return espacos.toString();
	}

	public static void mensagemConsole(String mensagem) {
		System.out.println(mensagem);
	}

	public static void janelaErro(String mensagem) {
		JOptionPane.showMessageDialog(null, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
	}

	public static void janelaConsulta(String mensagem) {
		JOptionPane.showMessageDialog(null, mensagem, "Consulta", JOptionPane.PLAIN_MESSAGE);
	}

	public static void janelaPadrao(String mensagem) {
		JOptionPane.showMessageDialog(null, mensagem, "Informacao", JOptionPane.INFORMATION_MESSAGE);
	}

	public static void relatorioFinal(int[] dados) {
		limpaTela(30);
		espacaTela(20);
		System.out.println(String.format("%02d", dados[0]) + " = NAO CONTAMINADAS");
		espacaTela(20);
		System.out.println(String.format("%02d", dados[1]) + " = CONTAMINADAS EM TRATAMENTO");
		espacaTela(20);
		System.out.println(String.format("%02d", dados[2]) + " = CONTAMINADAS CURADAS");
		espacaTela(20);
		System.out.println(String.format("%02d", dados[3]) + " = MULHERES CONTAMINADAS FALECIDAS");
		espacaTela(20);
		System.out.println(String.format("%02d", dados[4]) + " = HOMENS CONTAMINADOS FALECIDOS");
		espacaTela(20);
		System.err.println(String.format("%02d", dados[5]) + " = TOTAL DE PESSOAS CADASTRADAS");
	}
}

package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class frmCrud {

	private JFrame frmCadastroDeAlunos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmCrud window = new frmCrud();
					window.frmCadastroDeAlunos.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public frmCrud() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCadastroDeAlunos = new JFrame();
		frmCadastroDeAlunos.setTitle("Cadastro de Alunos");
		frmCadastroDeAlunos.setBounds(100, 100, 450, 300);
		frmCadastroDeAlunos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}

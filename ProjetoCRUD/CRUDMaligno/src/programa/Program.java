package programa;

import daos.AlunosDAO;
import dbos.Aluno;

public class Program {
	public static void main(String[] args) {

		try {
			Aluno eu = new Aluno("19358", "Giovana", "giovana@pinheiro.com");
			AlunosDAO dao = new AlunosDAO();
			// dao.create(eu);
			dao.update(eu);
			Aluno lucas = new Aluno("aaaaa","l1cas","lucas@olar");
			System.out.println(lucas);
		} catch (Exception erro) {
			System.out.println(erro.getMessage());
		}
	}
}

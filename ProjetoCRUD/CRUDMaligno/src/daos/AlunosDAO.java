package daos;

import java.sql.SQLException;
import bd.BDSQLServer;
import bd.core.MeuResultSet;
import dbos.Aluno;
import interfaces.CrudInterface;

public class AlunosDAO implements CrudInterface<Aluno> {

	@Override
	public void create(Aluno entidade) throws Exception {
		if (entidade == null)
			throw new Exception("O campo aluno n�o foi preenchido");

		if (raExiste(entidade.getRa()))
			throw new Exception("Este RA j� existe");

		try {
		String sql;
		sql = "INSERT INTO ALUNO VALUES (?, ?, ?)";
		BDSQLServer.COMANDO.prepareStatement(sql);
		BDSQLServer.COMANDO.setString(1, entidade.getRa());
		BDSQLServer.COMANDO.setString(2, entidade.getNome());
		BDSQLServer.COMANDO.setString(3, entidade.getEmail());
		BDSQLServer.COMANDO.executeUpdate();
		BDSQLServer.COMANDO.commit();
		} catch(SQLException erro) {
			// System.out.println(erro.getMessage());
			System.out.println("Erro ao inserir dados de Aluno");
		}
	}

	@Override
	public boolean raExiste(String cod) throws Exception {
		if (cod == null || cod.length() != 5)
			throw new Exception("RA � invalido!");

		boolean raEhValido = false;

		try {
			String sql;
			sql = "SELECT * FROM ALUNO WHERE RA = ?";
			BDSQLServer.COMANDO.prepareStatement(sql);
			BDSQLServer.COMANDO.setString(1, cod);
			MeuResultSet rs = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();
			raEhValido = rs.first();
		} catch (SQLException erro) {
			// System.out.println(erro.getMessage());
			throw new Exception("Erro ao buscar RA");
		}
		return raEhValido;
	}

	@Override
	public void update(Aluno entidade) throws Exception {

		if(entidade == null)
			throw new Exception("Valor de campo inv�lido");
		
		if(!raExiste(entidade.getRa()))
			throw new Exception("Este RA n�o existe");
		
		try {
			String sql = "UPDATE ALUNO SET nome = ?, email = ? WHERE ra = ?";
			BDSQLServer.COMANDO.prepareStatement(sql);
			BDSQLServer.COMANDO.setString(1, entidade.getNome());
			BDSQLServer.COMANDO.setString(2, entidade.getEmail());
			BDSQLServer.COMANDO.setString(3, entidade.getRa());
			BDSQLServer.COMANDO.executeUpdate();
			BDSQLServer.COMANDO.commit();
		} catch (SQLException erro) {
			// System.out.println(erro.getMessage());
			System.out.println("Erro ao atualizar aluno");
		}
	}

	@Override
	public void delete(String ra) throws Exception {
		if (ra == null)
			throw new Exception("RA inv�lido");

		if (!raExiste(ra))
			throw new Exception("Este RA n�o existe!");

		try {
			String sql = "DELETE * FROM ALUNO WHERE RA = ?";
			BDSQLServer.COMANDO.prepareStatement(sql);
			BDSQLServer.COMANDO.setString(1, ra);
			BDSQLServer.COMANDO.executeQuery();
			BDSQLServer.COMANDO.commit();
		} catch (SQLException erro) {
			// System.out.println(erro.getMessage());
			System.out.println("Erro ao deletar usu�rio");
		}
	}
}

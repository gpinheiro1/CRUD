package daos;

import java.sql.SQLException;

import bd.BDSQLServer;
import dbos.Fez;
import interfaces.CrudInterface;

public class FazemDAO implements CrudInterface<Fez>{

	@Override
	public void create(Fez entidade) throws Exception  {
		if (entidade == null)
			throw new Exception("O campo das matérias feitas pelo Aluno não foi preenchido");

		if (raExiste(entidade.getRa()))
			throw new Exception("Este RA já existe");

		try {
		String sql;
		sql = "INSERT INTO FEZ VALUES (?, ?, ?, ?)";
		BDSQLServer.COMANDO.prepareStatement(sql);
		BDSQLServer.COMANDO.setString(1, entidade.getRa());
		BDSQLServer.COMANDO.setString(2, entidade.getCodMateria());
		BDSQLServer.COMANDO.setFloat(3, entidade.getNota());
		BDSQLServer.COMANDO.setInt(3, entidade.getFrequencia());
		BDSQLServer.COMANDO.executeUpdate();
		BDSQLServer.COMANDO.commit();
		} catch(SQLException erro) {
			// System.out.println(erro.getMessage());
			System.out.println("Erro ao inserir dados");
		}
	}

	@Override
	public boolean raExiste(String cod) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void update(Fez entidade) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String cod) {
		// TODO Auto-generated method stub
		
	}
	

}

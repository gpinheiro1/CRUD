package daos;

import java.sql.SQLException;

import bd.BDSQLServer;
import bd.core.MeuResultSet;
import dbos.Fez;
import interfaces.CrudInterface;

public class FazemDAO implements CrudInterface<Fez, String>{

	@Override
	public void create(Fez entidade) throws Exception  {
		if (entidade == null)
			throw new Exception("O campo das materias feitas pelo Aluno nao foi preenchido");

		if (existe(entidade.getRa()))
			throw new Exception("Este RA ja existe");

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
	public boolean existe(String cod) throws Exception {
		if(cod == null)
			throw new Exception("Codigo invalido!");
		
		boolean codEhValido = false;
		
		try {
			String sql = "SELECT * FROM FEZ WHERE CODMATERIA = ?";
			BDSQLServer.COMANDO.prepareStatement(sql);
			BDSQLServer.COMANDO.setString(1, cod);
			BDSQLServer.COMANDO.executeQuery();
			MeuResultSet rs = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();
			codEhValido = rs.first();
			
		}catch(SQLException erro){
			
			System.out.println(erro.getMessage());
		}
		return codEhValido;
	}

	@Override
	public void update(Fez entidade) throws Exception{
		if(entidade == null)
			throw new Exception("Campo invalido");
		
		if(!existe(entidade.getCodMateria()))
			throw new Exception("Codigo nao encontrado");
				
		try {
			String sql = "UPDATE FEZ SET CODMATERIA = ?, NOTA = ?, FREQUENCIA = ? WHERE RA = ?";
			BDSQLServer.COMANDO.prepareStatement(sql);
			BDSQLServer.COMANDO.setString(1, entidade.getCodMateria());
			BDSQLServer.COMANDO.setFloat(2, entidade.getNota());
			BDSQLServer.COMANDO.setInt(3, entidade.getFrequencia());
			BDSQLServer.COMANDO.setString(4, entidade.getRa());
			BDSQLServer.COMANDO.executeUpdate();
			BDSQLServer.COMANDO.commit();
			
		}catch(SQLException erro) {
			System.out.println(erro.getMessage());
		}
	}

	@Override
	public void delete(String cod) throws Exception {

		if(cod == null)
			throw new Exception("Campo invalido");
		
		if(!existe(cod))
			throw new Exception("Codigo nao encontrado");
				
		try {
			String sql = "DELETE FROM FEZ WHERE CODMATERIA = ?";
			BDSQLServer.COMANDO.prepareStatement(sql);
			BDSQLServer.COMANDO.setString(1, cod);
			BDSQLServer.COMANDO.executeUpdate();
			BDSQLServer.COMANDO.commit();
			
		}catch(SQLException erro) {
			System.out.println(erro.getMessage());
		}
	}
	

}

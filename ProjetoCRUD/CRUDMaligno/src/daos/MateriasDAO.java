package daos;

import java.sql.SQLException;

import bd.BDSQLServer;
import bd.core.MeuResultSet;
import dbos.Materia;
import interfaces.CrudInterface;

public class MateriasDAO implements CrudInterface<Materia, String> {

	@Override
	public boolean create(Materia entidade) throws Exception {
		if (entidade == null)
			throw new Exception("O campo materia nao foi preenchido");
                return false;
	}

	@Override
	public boolean existe(String codMateria) throws Exception {
		if (codMateria == null || codMateria.length() != 5)
			throw new Exception("Codigo da materia invalido");

		boolean codigoEhValido = false;

		try {
			String sql = "SELECT * FROM Materia WHERE codMateria = ?";
			BDSQLServer.COMANDO.prepareStatement(sql);
			BDSQLServer.COMANDO.executeQuery();
			MeuResultSet rs = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();
			codigoEhValido = rs.first();

		} catch (SQLException erro) {
			// System.out.println(erro.getMessage());
			throw new Exception("Erro ao buscar codigo");
		}
		return codigoEhValido;
	}

	@Override
	public void update(Materia entidade) throws Exception {
		if (entidade == null)
			throw new Exception("Campo materia invalido");

		if (!existe(entidade.getCodMateria()))
			throw new Exception("Codigo nao encontrado!");

		try {
			String sql = "UPDATE MATERIA SET CODMATERIA = ?, NOMEMATERIA = ?";
			BDSQLServer.COMANDO.prepareStatement(sql);
			BDSQLServer.COMANDO.setString(1, entidade.getCodMateria());
			BDSQLServer.COMANDO.setString(2, entidade.getNomeMateria());
			BDSQLServer.COMANDO.executeQuery();
			BDSQLServer.COMANDO.commit();
		} catch (SQLException erro) {
			System.out.println(erro.getMessage());
			//System.out.println("Erro ao atualizar materia");
		}
	}

	@Override
	public void delete(String cod) throws Exception {
		if(cod == null)
			throw new Exception("Codigo invalido");
		if(!existe(cod))
			throw new Exception("O codigo nao existe");
		
		try {
		String sql = "DELETE FROM MATERIA WHERE CODMATERIA = ?";
		BDSQLServer.COMANDO.prepareStatement(sql);
		BDSQLServer.COMANDO.setString(1, cod);
		BDSQLServer.COMANDO.executeUpdate();
		BDSQLServer.COMANDO.commit();
		}catch(SQLException erro) {
			
			System.out.println(erro.getMessage());
		}
	}
}

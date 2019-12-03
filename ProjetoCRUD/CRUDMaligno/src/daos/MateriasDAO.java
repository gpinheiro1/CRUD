package daos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bd.BDSQLServer;
import bd.core.MeuResultSet;
import dbos.Materia;
import interfaces.CrudInterface;

public class MateriasDAO implements CrudInterface<Materia, String> {

	@Override
	public void create(Materia entidade) throws Exception {
		if (entidade == null)
			throw new Exception("O campo materia nao foi preenchido");
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
	
	public static List<Materia> nomeMateriasSemReprovacao() throws Exception {
		List<Materia> materias = new ArrayList<Materia>();
		String sql = "SELECT M.NOMEMATERIA FROM MATERIA M, FEZ F WHERE" +
					"M.CODMATERIA = F.CODMATERIA GROUP BY M.NOMEMATERIA HAVING AVG(F.NOTA) >= 5";
		BDSQLServer.COMANDO.prepareStatement(sql);
		MeuResultSet rs = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();
		while(rs.next()) {
			Materia materia = new Materia("DS201", "Tecnicas De Programacao");
			materia.setCodMateria(rs.getString("codMateria"));
			materia.setNomeMateria(rs.getString("nomeMateria"));
			materias.add(materia);
		}
		return materias;
	}
	
	public static List<Materia> nomeMateriasOrdenadasPorMediaDeAluno() throws Exception {
		List<Materia> materias = new ArrayList<Materia>();
		String sql = "SELECT M.NOMEMATERIA FROM MATERIA M, FEZ F, WHERE" +
		"M.CODMATERIA = F.CODMATERIA GROUP BY M.NOMEMATERIA ORDER BY AVG(F.NOTA)";
		BDSQLServer.COMANDO.prepareStatement(sql);
		MeuResultSet rs = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();
		while(rs.next()) {
			Materia materia = new Materia();
			materia.setNomeMateria(rs.getString("nomeMateria"));
			materia.setCodMateria(rs.getString("codMateria"));
			materias.add(materia);
		}
		return materias;
	}
}

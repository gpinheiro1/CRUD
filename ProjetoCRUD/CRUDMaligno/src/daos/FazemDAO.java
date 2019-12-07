package daos;

import java.sql.SQLException;

import bd.BDSQLServer;
import bd.core.MeuResultSet;
import dbos.Fez;
import interfaces.CrudInterface;
import java.util.List;

public class FazemDAO implements CrudInterface<Fez, String>{

	@Override
	public boolean create(Fez entidade) throws Exception  {
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
		int linhasAfetadas = BDSQLServer.COMANDO.executeUpdate();
		BDSQLServer.COMANDO.commit();
            
                return linhasAfetadas > 0;
		} catch(SQLException erro) {
			// System.out.println(erro.getMessage());
			System.out.println("Erro ao inserir dados");
                        return false;
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
	public boolean update(Fez entidade) throws Exception{
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
			int linhasAfetadas = BDSQLServer.COMANDO.executeUpdate();
			BDSQLServer.COMANDO.commit();
                        
                        return linhasAfetadas > 0;
			
		}catch(SQLException erro) {
			System.out.println(erro.getMessage());
                        return false;
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

    @Override
    public List<Fez> read(String filtro) {
        return null;
    }

    @Override
    public List<Fez> readAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
	

}

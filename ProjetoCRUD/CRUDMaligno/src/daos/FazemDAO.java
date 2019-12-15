package daos;

import java.sql.SQLException;

import bd.BDSQLServer;
import bd.core.MeuResultSet;
import dbos.Fez;
import interfaces.CrudInterface;
import java.util.ArrayList;
import java.util.List;

public class FazemDAO implements CrudInterface<Fez, String>{

	@Override
	public boolean create(Fez entidade) throws Exception  {
		if (entidade == null)
			throw new Exception("O campo das materias feitas pelo Aluno nao foi preenchido");

		if (!existeRa(entidade.getRa()))
			throw new Exception("Este RA não existe");
                
                if(!existeMateria(entidade.getCodMateria()))
                        throw new Exception("Esta materia nao existe");

		try {
		String sql;
		sql = "INSERT INTO FEZ VALUES (?, ?, ?, ?)";
		BDSQLServer.COMANDO.prepareStatement(sql);
		BDSQLServer.COMANDO.setString(1, entidade.getRa());
		BDSQLServer.COMANDO.setString(2, entidade.getCodMateria());
		BDSQLServer.COMANDO.setFloat(3, entidade.getNota());
		BDSQLServer.COMANDO.setInt(4, entidade.getFrequencia());
		int linhasAfetadas = BDSQLServer.COMANDO.executeUpdate();
		BDSQLServer.COMANDO.commit();
            
                return linhasAfetadas > 0;
		} catch(SQLException erro) {
			 System.out.println(erro.getMessage());
			//System.out.println("Erro ao inserir dados");
                        return false;
		}
	}

	@Override
	public boolean existeRa(String cod) throws Exception {
		if (cod == null || cod.length() != 5) {
            throw new Exception("RA invalido!");
        }

        boolean raEhValido = false;

        try {
            String sql;
            sql = "SELECT * FROM ALUNO WHERE RA = ?";
            BDSQLServer.COMANDO.prepareStatement(sql);
            BDSQLServer.COMANDO.setString(1, cod);
            MeuResultSet rs = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();
            raEhValido = rs.first();
        } catch (SQLException erro) {
            System.out.println(erro.getMessage());
        }
        return raEhValido;
    }
        
        public boolean existeMateria(String materia) throws Exception {
		if(materia == null)
			throw new Exception("Materia invalida!");
                
		
		boolean codEhValido = false;
		try {
			String sql = "SELECT * FROM Materia WHERE codMateria = ?";
			BDSQLServer.COMANDO.prepareStatement(sql);
			BDSQLServer.COMANDO.setString(1, materia);
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
		
		if(!existeRa(entidade.getRa()))
			throw new Exception("RA nao encontrado");
				
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
		
		if(!existeRa(cod))
			throw new Exception("Codigo nao encontrado");
				
		try {
			String sql = "DELETE FROM FEZ WHERE RA = ?";
			BDSQLServer.COMANDO.prepareStatement(sql);
			BDSQLServer.COMANDO.setString(1, cod);
			BDSQLServer.COMANDO.executeUpdate();
			BDSQLServer.COMANDO.commit();
			
		}catch(SQLException erro) {
			System.out.println(erro.getMessage());
		}
	}

    @Override
    public List<Fez> read(String ra) throws Exception {
        List<Fez> listaFez = new ArrayList<>();
        String sql = "SELECT * FROM Fez WHERE ra = ?"; 
        BDSQLServer.COMANDO.prepareStatement(sql);
        BDSQLServer.COMANDO.setString(1, ra);
        MeuResultSet rs = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();
        while(rs.next()) {
            listaFez.add(
                new Fez(
                    rs.getString("ra"),
                    rs.getString("codMateria"),
                    rs.getFloat("nota"),
                    rs.getInt("frequencia")
                )
            );
        }
        return listaFez;
    }

    @Override
    public List<Fez> readAll() throws Exception {
        List<Fez> listaFez = new ArrayList<>();
        String sql = "SELECT * FROM Fez";
        BDSQLServer.COMANDO.prepareStatement(sql);
        MeuResultSet rs = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();
        while (rs.next()) {
           listaFez.add(
                new Fez(
                    rs.getString("ra"),
                    rs.getString("codMateria"),
                    rs.getFloat("nota"),
                    rs.getInt("frequencia")
                )
            );
        }
        return listaFez;
    }
}

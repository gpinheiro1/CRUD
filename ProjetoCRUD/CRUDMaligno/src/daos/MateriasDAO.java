package daos;

import java.sql.SQLException;

import bd.BDSQLServer;
import bd.core.MeuResultSet;
import dbos.Materia;
import interfaces.CrudInterface;
import java.util.ArrayList;
import java.util.List;

public class MateriasDAO implements CrudInterface<Materia, String> {

	@Override
	public boolean create(Materia entidade) throws Exception {
		if (entidade == null)
			throw new Exception("O campo materia nao foi preenchido");
                
                if(existeRa(entidade.getCodMateria()))
                    throw new Exception("Este codigo ja existe na base de dados!");
                
                try{
                    String sql = "INSERT INTO MATERIA VALUES (?, ?)";
                    BDSQLServer.COMANDO.prepareStatement(sql);
                    BDSQLServer.COMANDO.setString(1, entidade.getCodMateria());
                    BDSQLServer.COMANDO.setString(2, entidade.getNomeMateria());
                    int linhasAfetadas = BDSQLServer.COMANDO.executeUpdate();
                    BDSQLServer.COMANDO.commit();
                    
                    return linhasAfetadas > 0;
                }catch(SQLException erro){
                    return false;
                }
	}

	@Override
	public boolean existeRa(String codMateria) throws Exception {
		if (codMateria == null || codMateria.length() != 5)
			throw new Exception("Codigo da materia invalido");

		boolean codigoEhValido = false;

		try {
			String sql = "SELECT * FROM Materia WHERE codMateria = ?";
			BDSQLServer.COMANDO.prepareStatement(sql);
                        BDSQLServer.COMANDO.setString(1, codMateria);
			MeuResultSet rs = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();
			codigoEhValido = rs.first();

		} catch (SQLException erro) {
			throw new Exception("Erro ao buscar codigo");
		}
		return codigoEhValido;
	}

	@Override
	public boolean update(Materia entidade) throws Exception {
		if (entidade == null)
			throw new Exception("Campo materia invalido");

		if (!existeRa(entidade.getCodMateria()))
			throw new Exception("Codigo nao encontrado!");

		try {
			String sql = "UPDATE MATERIA SET NOMEMATERIA = ? WHERE CODMATERIA = ?";
			BDSQLServer.COMANDO.prepareStatement(sql);
			BDSQLServer.COMANDO.setString(1, entidade.getNomeMateria());
			BDSQLServer.COMANDO.setString(2, entidade.getCodMateria());
			int linhasAfetadas = BDSQLServer.COMANDO.executeUpdate();
			BDSQLServer.COMANDO.commit();
                        
                        return linhasAfetadas > 0;
		} catch (SQLException erro) {
			System.out.println(erro.getMessage());
			return false;
		}
	}

	@Override
	public void delete(String cod) throws Exception {
		if(cod == null)
			throw new Exception("Codigo invalido");
		if(!existeRa(cod))
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
        
        //METODO RELATORIO
        public static List<String> nomeMateriasSemReprovacao() throws Exception{
            List<String> listaMaterias = new ArrayList<>();
            String sql = "nomeMateriasSemReprovacao_sp";
            BDSQLServer.COMANDO.prepareStatement(sql);
            MeuResultSet rs = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();
            while(rs.next()){
                listaMaterias.add(
                        rs.getString("nomeMateria")
                );
            }
            return listaMaterias;
        }
        
        //METODO RELATORIO
        public static List<String[]> nomeMateriasOrdenadasporMediasAlunos() throws Exception{
            List<String[]> listaMaterias = new ArrayList<>();
            String sql = "nomeMateriasPorMediaAlunos_sp";
            BDSQLServer.COMANDO.prepareStatement(sql);
            MeuResultSet rs = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();
            while(rs.next()){
                listaMaterias.add(
                    new String[] {
                        rs.getString("nomeMateria"),
                        Float.toString(rs.getFloat("media"))
                    }
                );
            }
            return listaMaterias;
        }

    @Override
    public List<Materia> read(String codigo) throws Exception{
        if(codigo == null)
            throw new Exception("Codigo invalido");
       
        List<Materia> materias = new ArrayList<>();
        String sql = "SELECT * FROM Materia WHERE CODMATERIA = ?";
        BDSQLServer.COMANDO.prepareStatement(sql);
        BDSQLServer.COMANDO.setString(1, codigo);
        MeuResultSet rs = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();
        while(rs.next()){
            Materia mat = new Materia();    
            mat.setCodMateria(rs.getString("codMateria"));
            mat.setNomeMateria(rs.getString("nomeMateria"));
            materias.add(mat);
        }
        return materias;
    }

    @Override
    public List<Materia> readAll() throws Exception {
       List<Materia> listaMaterias = new ArrayList<>();
            String sql = "SELECT * FROM MATERIA";
            BDSQLServer.COMANDO.prepareStatement(sql);
            MeuResultSet rs = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();
            while(rs.next()){
                Materia materia = new Materia();
                materia.setCodMateria(rs.getString("codMateria"));
                materia.setNomeMateria(rs.getString("nomeMateria"));
                listaMaterias.add(materia);
            }
       return listaMaterias;
    }
}

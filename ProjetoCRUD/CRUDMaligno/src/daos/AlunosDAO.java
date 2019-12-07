package daos;

import java.sql.SQLException;

import bd.BDSQLServer;
import bd.core.MeuResultSet;
import dbos.Aluno;
import interfaces.CrudInterface;
import java.util.ArrayList;
import java.util.List;

public class AlunosDAO implements CrudInterface<Aluno, String> {

    @Override
    public boolean create(Aluno entidade) throws Exception {
        if (entidade == null)
            throw new Exception("O campo aluno nao foi preenchido");

        if (existe(entidade.getRa()))
            throw new Exception("Este RA ja existe");

        try {
            String sql;
            sql = "INSERT INTO ALUNO VALUES (?, ?, ?)";
            BDSQLServer.COMANDO.prepareStatement(sql);
            BDSQLServer.COMANDO.setString(1, entidade.getRa());
            BDSQLServer.COMANDO.setString(2, entidade.getNome());
            BDSQLServer.COMANDO.setString(3, entidade.getEmail());
            int linhasAfetadas = BDSQLServer.COMANDO.executeUpdate();
            BDSQLServer.COMANDO.commit();
            
            return linhasAfetadas > 0;
        } catch (SQLException erro) {
            System.out.println(erro.getMessage());
            return false;
        }
    }

    @Override
    public boolean existe(String ra) throws Exception {
        if (ra == null || ra.length() != 5)
            throw new Exception("RA invalido!");

        boolean raEhValido = false;

        try {
            String sql;
            sql = "SELECT * FROM ALUNO WHERE RA = ?";
            BDSQLServer.COMANDO.prepareStatement(sql);
            BDSQLServer.COMANDO.setString(1, ra);
            MeuResultSet rs = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();
            raEhValido = rs.first();
        } catch (SQLException erro) {
             System.out.println(erro.getMessage());
        }
        return raEhValido;
    }

    @Override
    public boolean update(Aluno entidade) throws Exception {

        if (entidade == null)
            throw new Exception("Valor de campo invalido");

        if (!existe(entidade.getRa()))
            throw new Exception("Este RA nao existe");

        try {
            String sql = "UPDATE ALUNO SET nome = ?, email = ? WHERE ra = ?";
            BDSQLServer.COMANDO.prepareStatement(sql);
            BDSQLServer.COMANDO.setString(1, entidade.getNome());
            BDSQLServer.COMANDO.setString(2, entidade.getEmail());
            BDSQLServer.COMANDO.setString(3, entidade.getRa());
            int linhasAfetadas = BDSQLServer.COMANDO.executeUpdate();
            BDSQLServer.COMANDO.commit();
            
            return linhasAfetadas > 0;
            
        } catch (SQLException erro) {
            System.out.println(erro.getMessage());
            return false;
        }
    }

    @Override
    public void delete(String ra) throws Exception {
        if (ra == null)
            throw new Exception("RA invalido");

        if (!existe(ra))
            throw new Exception("Este RA nao existe!");

        try {
            String sql = "DELETE FROM ALUNO WHERE RA = ?";
            BDSQLServer.COMANDO.prepareStatement(sql);
            BDSQLServer.COMANDO.setString(1, ra);
            BDSQLServer.COMANDO.executeUpdate();
            BDSQLServer.COMANDO.commit();
        } catch (SQLException erro) {
            // System.out.println(erro.getMessage());
            System.out.println("Erro ao deletar usuario");
        }
    }
    
    //METODO RELATORIO
    public static List<Aluno> frequenciaZero() throws Exception {
    		List<Aluno> alunos = new ArrayList<>();
    		String sql = "SELECT A.NOME FROM ALUNO A, FEZ F WHERE F.RA = A.RA AND F.FREQUENCIA = 0";
    		BDSQLServer.COMANDO.prepareStatement(sql);
    		MeuResultSet rs = (MeuResultSet)BDSQLServer.COMANDO.executeQuery();
    		while(rs.next()) {
    			Aluno aluno = new Aluno();
    			aluno.setNome(rs.getString("nome"));
    			aluno.setRa(rs.getString("ra"));
    			aluno.setEmail(rs.getString("email"));
    			alunos.add(aluno);
    	}
    	return alunos;
    }
    
    //METODO RELATORIO
    public static List<Aluno> nomesPorMediaDeAlunos() throws Exception {
    	List<Aluno> alunos = new ArrayList<>();
    	String sql = "SELECT A.NOME FROM ALUNO A, FEZ F, MATERIA M WHERE" +
    				"A.RA = F.RA AND M.CODMATERIA = F.CODMATERIA GROUP BY A.NOME ORDER BY AVG(F.NOTA)";
    	BDSQLServer.COMANDO.prepareStatement(sql);
    	MeuResultSet rs = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();
    	while(rs.next()) {
    		Aluno alu = new Aluno();
    		alu.setNome(rs.getString("nome"));
    		alu.setRa(rs.getString("ra"));
    		alu.setEmail(rs.getString("email"));
    		alunos.add(alu);
    	}
    	return alunos;
    }

    @Override
    public List<Aluno> read(String ra) throws Exception {
        List<Aluno> listaAlunos = new ArrayList<>();
        String sql = "SELECT * FROM ALUNO WHERE RA = ?";
        BDSQLServer.COMANDO.prepareStatement(sql);
        MeuResultSet rs = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();
        while(rs.next()){
            Aluno alu = new Aluno();
            alu.setRa(rs.getString("ra"));
            alu.setNome(rs.getString("nome"));
            alu.setEmail(rs.getString("email"));
            listaAlunos.add(alu);
        }
        return listaAlunos;
    }

    @Override
    public List<Aluno> readAll() throws Exception{
       List<Aluno> listaAlunos = new ArrayList<>();
       String sql = "SELECT * FROM ALUNO";
       BDSQLServer.COMANDO.prepareStatement(sql);
       MeuResultSet rs = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();
       while(rs.next()){
           Aluno alu = new Aluno();
           alu.setRa(rs.getString("ra"));
           alu.setNome(rs.getString("nome"));
           alu.setEmail(rs.getString("email"));
           listaAlunos.add(alu);
       }
       return listaAlunos;
    }
}

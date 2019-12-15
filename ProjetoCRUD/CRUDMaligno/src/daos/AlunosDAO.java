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
        if (entidade == null) {
            throw new Exception("O campo aluno nao foi preenchido");
        }

        if (existeRa(entidade.getRa())) {
            throw new Exception("Este RA ja existe");
        }

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
    public boolean existeRa(String ra) throws Exception {
        if (ra == null || ra.length() != 5) {
            throw new Exception("RA invalido!");
        }

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

        if (entidade == null) {
            throw new Exception("Valor de campo invalido");
        }

        if (!existeRa(entidade.getRa())) {
            throw new Exception("Este RA nao existe");
        }

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
    
    public boolean updateNome(String ra, String nome) throws Exception {
        
        if (ra == null || nome == null) {
            throw new Exception("Valor de campo invalido");
        }

        if (!existeRa(ra)) {
            throw new Exception("Este RA nao existe");
        }

        try {
            String sql = "UPDATE ALUNO SET nome = ? WHERE ra = ?";
            BDSQLServer.COMANDO.prepareStatement(sql);
            BDSQLServer.COMANDO.setString(1, nome);
            BDSQLServer.COMANDO.setString(2, ra);
            int linhasAfetadas = BDSQLServer.COMANDO.executeUpdate();
            BDSQLServer.COMANDO.commit();

            return linhasAfetadas > 0;

        } catch (SQLException erro) {
            System.out.println(erro.getMessage());
            return false;
        }
    }

    public boolean updateEmail(String ra, String email) throws Exception {
     
        if (ra == null || email == null) {
            throw new Exception("Valor de campo invalido");
        }

        if (!existeRa(ra)) {
            throw new Exception("Este RA nao existe");
        }

        try {
            String sql = "UPDATE ALUNO SET email = ? WHERE ra = ?";
            BDSQLServer.COMANDO.prepareStatement(sql);
            BDSQLServer.COMANDO.setString(1, email);
            BDSQLServer.COMANDO.setString(2, ra);
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
        if (ra == null) {
            throw new Exception("RA invalido");
        }

        if (!existeRa(ra)) {
            throw new Exception("Este RA nao existe!");
        }

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
        String sql = "frequenciaAlunos_sp";
        BDSQLServer.COMANDO.prepareStatement(sql);
        MeuResultSet rs = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();
        while (rs.next()) {
            alunos.add(
                new Aluno(
                    rs.getString("ra"),
                    rs.getString("nome"),
                    rs.getString("email")
                )
            );
        }
        return alunos;
    }

    //METODO RELATORIO
    public static List<String[]> alunosPorMediaMaterias() throws Exception {
        List<String[]> alunos = new ArrayList<>();
        String sql = "alunosPorMediaMaterias_sp";
        BDSQLServer.COMANDO.prepareStatement(sql);
        MeuResultSet rs = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();
        while (rs.next()) {
            alunos.add(new String[]{
                rs.getString("nome"),
                Float.toString(rs.getFloat("media"))
            });
        }
        return alunos;
    }

    @Override
    public List<Aluno> read(String ra) throws Exception {
        List<Aluno> listaAlunos = new ArrayList<>();
        String sql = "SELECT * FROM Aluno WHERE ra = ?";
        BDSQLServer.COMANDO.prepareStatement(sql);
        BDSQLServer.COMANDO.setString(1, ra);
        MeuResultSet rs = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();
        while (rs.next()) {
            listaAlunos.add(
                new Aluno(
                    rs.getString("ra"),
                    rs.getString("nome"),
                    rs.getString("email")
                )
            );
        }
        return listaAlunos;
    }

    @Override
    public List<Aluno> readAll() throws Exception {
        List<Aluno> listaAlunos = new ArrayList<>();
        String sql = "SELECT * FROM ALUNO";
        BDSQLServer.COMANDO.prepareStatement(sql);
        MeuResultSet rs = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();
        while (rs.next()) {
            Aluno alu = new Aluno();
            alu.setRa(rs.getString("ra"));
            alu.setNome(rs.getString("nome"));
            alu.setEmail(rs.getString("email"));
            listaAlunos.add(alu);
        }
        return listaAlunos;
    }
}

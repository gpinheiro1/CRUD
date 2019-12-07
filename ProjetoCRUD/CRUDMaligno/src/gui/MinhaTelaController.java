/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import daos.AlunosDAO;
import daos.MateriasDAO;
import dbos.Aluno;
import dbos.Materia;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author Gi
 */
public class MinhaTelaController {
    
    private MateriasDAO materiasDAO;
    private AlunosDAO alunosDAO;
    private JTable tabela;
    private JFrame frame;
    
    public MinhaTelaController(JTable tabela) {
        materiasDAO = new MateriasDAO();
        alunosDAO = new AlunosDAO();
        this.tabela = tabela;
    }
    
    public MinhaTelaController(JTable tabela, JFrame frame) {
        this(tabela);
        this.frame = frame;
    }
    
    private void exibirMaterias() {
        try{
            List<Materia> lista = materiasDAO.readAll();
            exibirMaterias(lista);
        }catch(Exception e){
            mostrarMensagem(e.getMessage() + "Ocorreu um erro!");
        }
    }
    
    private void exibirMaterias(List<Materia> lista) {
        String[] colunas = { "Código", "Nome" };
        String[][] data = new String[lista.size()][1];
        
        for (int i = 0; i < lista.size(); i++){
            data[i][0] = lista.get(i).getCodMateria();
            data[i][1] = lista.get(i).getNomeMateria();
        }
        
        this.tabela = new JTable(data, colunas);
        this.tabela.updateUI();
    }
    
    private void exibirAlunos() {
        try{
            List<Aluno> lista = alunosDAO.readAll();
            exibirAlunos(lista);
        }catch(Exception erro){
           // mostrarMensagem(erro.getMessage() + "Nao foi possivel exibir os alunos");
        }
    }
    private void exibirAlunos(List<Aluno> lista){
        String[] colunas = { "Ra", "Nome", "Email" };
        String[][] data = new String[lista.size()][2];
        
        for(int i = 0; i < lista.size(); i++){
            data[i][0] = lista.get(i).getRa();
            data[i][1] = lista.get(i).getNome();
            data[i][2] = lista.get(i).getEmail();
        }
        
        this.tabela = new JTable(data, colunas);
    }
    
    public void pesquisarMateria(String codigo) {
        try {
            List<Materia> lista = materiasDAO.read(codigo);
            exibirMaterias(lista);
        } catch (Exception e) {
            mostrarMensagem("Nao foi possivel pesquisar a materia");
        }
    }
    
    public void pesquisarAluno(String ra) {
        try{
            List<Aluno> lista = alunosDAO.read(ra);
            
        }catch(Exception erro){
            mostrarMensagem("Nao foi possivel pesquisar o aluno");
        }
    }
   
    public void salvarMateria(String codigo, String nome) {
        if (nome.isEmpty() && codigo.isEmpty()) {
            mostrarMensagem("Os campos nome e codigo nao podem ficar vazios!");
            return;
        }
        
        try {
            boolean deuCerto = materiasDAO.create(new Materia(codigo, nome));
            if (deuCerto) {
                mostrarMensagem("Materia cadastrada com sucesso");
                exibirMaterias();
            } else
                mostrarMensagem("Erro ao cadastrar");
        } catch(Exception e) {
            mostrarMensagem("Houve um erro, tente novamente!");
        }
    }
    
    public void excluirMateria(String codigo) throws Exception{
        try{
              materiasDAO.delete(codigo);
                mostrarMensagem("Matéria deletada com sucesso!");
        }catch(Exception e){
            mostrarMensagem("Erro ao deletar matéria");
        }
    }
    
    public void salvarAluno(String ra, String nome, String email) {
        if(ra.isEmpty() && nome.isEmpty() && email.isEmpty()){
            mostrarMensagem("Estes campos nao podem ficar vazios!");
        return;
        }
    
    try{
        boolean deuCerto = alunosDAO.create(new Aluno(ra, nome, email));
        if(deuCerto){
            mostrarMensagem("Aluno cadastrado com sucesso!");
            exibirAlunos();
            
        } else
            mostrarMensagem("Erro ao cadastrar aluno!");
    }catch(Exception e){
        mostrarMensagem("Houve um erro, tente novamente");
    }
}
    private void mostrarMensagem(String texto) {
        JOptionPane.showMessageDialog(this.frame, texto);
    }
    
    public void alterarMateria(String codigo, String nome) {
        try{
            boolean deuCerto = materiasDAO.update(new Materia(codigo, nome));
            if(deuCerto){
                mostrarMensagem("Informacoes alteradas com sucesso!");
            }else
                mostrarMensagem("Erro ao atualizar materia");
            
        }catch(Exception e){
            mostrarMensagem("Houve um erro, tente novamente!");
        }
    }

    void excluirAluno(String ra) throws Exception {
        try{
              alunosDAO.delete(ra);
                mostrarMensagem("Usuário deletado com sucesso!");
        }catch(Exception e){
            mostrarMensagem("Erro ao deletar usuário");
        }
    }

    void alterarAluno(String ra, String nome, String email) {
        try{
            boolean deuCerto = alunosDAO.update(new Aluno(ra, nome, email));
            if(deuCerto){
                mostrarMensagem("Informacoes alteradas com sucesso!");
            }else
                mostrarMensagem("Erro ao atualizar aluno");
            
        }catch(Exception e){
            mostrarMensagem("Houve um erro, tente novamente!");
        }
    }
}

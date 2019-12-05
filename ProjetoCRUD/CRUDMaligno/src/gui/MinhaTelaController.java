/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import daos.AlunosDAO;
import daos.MateriasDAO;
import dbos.Materia;
import java.util.List;
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
    
    public MinhaTelaController(JTable tabela) {
        materiasDAO = new MateriasDAO();
        alunosDAO = new AlunosDAO();
        this.tabela = tabela;
    }
    
    private void exibirMaterias() {
        List<Materia> lista = materiasDAO.readAll();
        exibirMaterias(lista);
    }
    
    private void exibirMaterias(List<Materia> lista) {
        String[] colunas = { "Código", "Nome" };
        String[][] data = new String[lista.size()][2];
        
        for (int i = 0; i < lista.size(); i++){
            data[i][0] = lista.get(i).getCodMateria();
            data[i][1] = lista.get(i).getNomeMateria();
        }
        
        this.tabela = new JTable(data, colunas);
    }
    
    public void pesquisarMateria(String codigo) {
        List<Materia> lista = materiasDAO.read(codigo);
        exibirMaterias(lista);
    }
   
    public void salvarMateria(String codigo, String nome) {
        if (nome.isEmpty() && codigo.isEmpty()) {
            mostrarMensagem("Nome e o código não pode ficar vazio");
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
            mostrarMensagem("Algum erro aconteceu. Tente novamente!");
        }
    }
    
    private void mostrarMensagem(String texto) {
        JOptionPane.showMessageDialog(null, texto);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.rff.interfaces;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author refra
 */
public interface CRUDViewInterface {

    public void preencherTabelaPesquisar(ArrayList<Object> listaItensEncontrados);

    public void preencherCampos(Object model);

    public void limparCampos();

    /**
     * Parâmetros = inserir, editar, salvar, excluir, cancelar
     *
     * @param CRUDCommand
     */
    public boolean doCRUD(String CRUDCommand);

    /**
     * Retorna um modelo de objeto dos campos preenchidos
     *
     * @return
     */
    public Object getModel();

    public Object getSelectedModel();

    public void doMsg(String msg);

    public void setActionListener(ActionListener al);

    public void setPanelComponentState(Boolean state);

}

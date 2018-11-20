/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.rff.controller;

import br.com.rff.interfaces.CRUDViewInterface;
import br.com.rff.interfaces.ControllerInterface;
import br.com.rff.interfaces.DAOInterface;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author refra
 */
public class ControllerModelo implements ActionListener {

    ControllerInterface myController;
    CRUDViewInterface myCrudView;
    DAOInterface myDAO;

    public ControllerModelo(ControllerInterface myController, CRUDViewInterface myCrudView, DAOInterface myDAO) {
        this.myController = myController;
        this.myCrudView = myCrudView;
        this.myDAO = myDAO;
        this.myCrudView.setActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("An further attack: ~>" + e.getActionCommand() + "<~");

        if (e.getActionCommand().toLowerCase().equals("inserir")) {
            inserir();
        } else if (e.getActionCommand().toLowerCase().equals("editar")) {
            editar();
        } else if (e.getActionCommand().toLowerCase().equals("salvar")) {
            salvar();
        } else if (e.getActionCommand().toLowerCase().equals("excluir")) {
            excluir();
        } else if (e.getActionCommand().toLowerCase().equals("cancelar")) {
            cancelar();
        } else if (e.getActionCommand().toLowerCase().equals("pesquisar")) {
            pesquisar();
        } else if (e.getActionCommand().toLowerCase().equals("abrir")) {
            abrir();
        }
    }

    private void inserir() {
        this.myCrudView.doCRUD("inserir");
        this.myCrudView.limparCampos();
        this.myCrudView.setPanelComponentState(true);
    }

    private void salvar() {
        String msg = this.myController.verificarCamposObrigatorios();
        if (msg == null) {
            this.myCrudView.doCRUD("salvar");
            Object myObject = this.myCrudView.getModel();
            myDAO.salvar(myObject);
            this.myCrudView.preencherCampos(myDAO.getLast());;
        } else {
            this.myCrudView.doMsg(msg);
        }
    }

    private void editar() {
        this.myCrudView.doCRUD("editar");
        this.myCrudView.setPanelComponentState(true);
    }

    private void cancelar() {
        this.myCrudView.doCRUD("cancelar");
        this.myCrudView.limparCampos();
        this.myCrudView.setPanelComponentState(false);
    }

    private void excluir() {
        if (this.myCrudView.doCRUD("excluir")) {
            this.myDAO.remover(myCrudView.getModel());
            this.myCrudView.limparCampos();
            this.myCrudView.setPanelComponentState(false);
        } else {
            this.myCrudView.doMsg("Falha na exclusão!");
        }
    }

    private void pesquisar() {
        this.myCrudView.preencherTabelaPesquisar(myDAO.getAll());
    }

    private void abrir() {
        this.myCrudView.limparCampos();
        this.myCrudView.preencherCampos(myDAO.getByID(myCrudView.getSelectedModel()));
    }

}

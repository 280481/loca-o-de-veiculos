/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.rff.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author refra
 */
public class ViewModelo extends JFrame implements ActionListener {
    
    protected JPanel panelTOP, panelMID, panelBOT, panelINTERNO, panelPesquisa;
    protected JButton btInserir, btEditar, btSalvar, btRemover, btCancelar, btExit, btPesquisar, btAbrir;
    protected JTextField edPesquisa;
    protected JTabbedPane tabbedPanel;
    protected JTable tabela;
    protected DefaultTableModel tm;
    
    public ViewModelo(String titulo) {
        super(titulo);
        init();
    }
    
    private void init() {
        this.setLayout(new BorderLayout());
        this.panelTOP = new JPanel(new FlowLayout());
        this.panelMID = new JPanel(new GridLayout(0, 2));
        this.panelBOT = new JPanel();
        this.panelINTERNO = new JPanel();
        this.panelPesquisa = new JPanel(new BorderLayout());
        this.add(panelTOP, BorderLayout.NORTH);
        panelTOP.setSize(new Dimension(500, 300));
        this.add(panelBOT, BorderLayout.SOUTH);
        
        btInserir = new JButton("Inserir");
        
        btEditar = new JButton("Editar");
        
        btCancelar = new JButton("Cancelar");
        
        btSalvar = new JButton("Salvar");
        
        btRemover = new JButton("Excluir");
        
        btExit = new JButton("Sair");
        btExit.addActionListener(this);
        
        JPanel tab1 = new JPanel(new BorderLayout());
        tab1.add(panelMID);
        tabbedPanel = new JTabbedPane();
        tabbedPanel.addTab("Formulário", tab1);
        tabbedPanel.addTab("Pesquisa", panelPesquisa);
        
        JPanel panelPesquisaTop = new JPanel(new FlowLayout());
        JPanel panelPesquisaMID = new JPanel(new BorderLayout());
        
        panelPesquisa.add(panelPesquisaTop, BorderLayout.NORTH);
        btPesquisar = new JButton("Pesquisar");
        
        btAbrir = new JButton("Abrir");
        
        edPesquisa = new JTextField();
        panelPesquisaTop.add(edPesquisa);
        panelPesquisaTop.add(btPesquisar);
        panelPesquisaTop.add(btAbrir);
        
        tm = new DefaultTableModel(1, 0);
        tabela = new JTable(tm);
        panelPesquisa.add(panelPesquisaMID, BorderLayout.CENTER);
        panelPesquisaMID.add(tabela, BorderLayout.CENTER);
        
        this.add(tabbedPanel, BorderLayout.CENTER);
        panelTOP.add(btInserir);
        panelTOP.add(btEditar);
        panelTOP.add(btCancelar);
        panelTOP.add(btSalvar);
        panelTOP.add(btRemover);
        panelTOP.add(panelINTERNO);
        panelINTERNO.add(btExit, BorderLayout.EAST);
        panelMID.setEnabled(false);
        this.setCRUDButtons("salvar");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.tabela.setColumnSelectionAllowed(false);
        
        edPesquisa.setSize(new Dimension(200, 100));
        
        setPanelCompEnable(false);
    }

    /**
     * Parâmetros = inserir, editar, salvar, excluir, cancelar
     *
     * @param CRUDCommand
     */
    protected void setCRUDButtons(String CRUDCommand) {
        String myCRUD = CRUDCommand.toLowerCase();
        if (myCRUD.equals("inserir") || myCRUD.equals("novo") || myCRUD.equals("editar")) {
            btEditar.setEnabled(false);
            btInserir.setEnabled(false);
            btRemover.setEnabled(false);
            btSalvar.setEnabled(true);
            btCancelar.setEnabled(true);
            panelMID.setEnabled(true);
        } else if (myCRUD.equals("salvar") || myCRUD.equals("excluir") || myCRUD.equals("cancelar")) {
            btEditar.setEnabled(true);
            btInserir.setEnabled(true);
            btRemover.setEnabled(true);
            btSalvar.setEnabled(false);
            btCancelar.setEnabled(false);
            panelMID.setEnabled(false);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().toLowerCase().equals("sair")) {
            this.dispose();
        }
    }
    
    public void setActionListenerPAI(ActionListener al) {
        btInserir.addActionListener(al);
        btEditar.addActionListener(al);
        btCancelar.addActionListener(al);
        btSalvar.addActionListener(al);
        btRemover.addActionListener(al);
        btAbrir.addActionListener(al);
        btPesquisar.addActionListener(al);
    }
    
    private void setPanelEnabled(JPanel panel, Boolean isEnabled) {
        panel.setEnabled(isEnabled);
        
        Component[] components = panel.getComponents();
        
        for (Component component : components) {
            if (component instanceof JPanel) {
                setPanelEnabled((JPanel) component, isEnabled);
            }
            component.setEnabled(isEnabled);
        }
    }
    
    protected void setPanelCompEnable(Boolean state) {
        setPanelEnabled(this.panelMID, state);
    }
}

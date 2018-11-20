package br.com.rff.controller;

import br.com.rff.DAO.DaoUsuario;
import br.com.rff.model.Usuario;
import br.com.rff.services.Conexao;
import br.com.rff.view.TelaPrincipal;
import br.com.rff.view.TelaUsuario;
import br.com.rff.view.Telinha;
import com.sun.prism.j2d.J2DPipeline;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.JLabel;

public class ControllerPrincipal implements ActionListener {
    
    private TelaPrincipal minhaView;
    private Usuario user;
    
    public ControllerPrincipal(Usuario user) {
        this.user = user;
        minhaView = new TelaPrincipal(this);
        minhaView.abrir();
        this.minhaView.addControllerActionListener(this);
        this.minhaView.getmPainel().add(new JLabel("Usuário: " + this.user.getLogin()));
        this.minhaView.getmPainel().add(new JLabel(new Date().toString()));
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        if (e.getActionCommand().equals("pessoa")) {
            minhaView.getmDesktop().add( new Telinha());
        } else if (e.getActionCommand().equals("marca")) {
            ControllerMarca controllerMarca = new ControllerMarca();
            //minhaView.getmDesktop().add(controllerMarca.getTelaMarca());
        }
    }
    
}

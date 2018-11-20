package br.com.rff.view;

import br.com.rff.model.Usuario;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author lukas
 */
public class TelaUsuario extends JFrame implements ActionListener {

    private Usuario user;

    private JTextField edLogin;
    private JPasswordField edSenha;
    private JButton btLogin, btSair;

    public TelaUsuario() {
        super("::Login");
        init();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("login.sair")) {
            System.exit(1);
        }

    }

    private void init() {
        user = new Usuario();
        this.setLayout(new GridLayout(0, 2));

        edLogin = new JTextField();
        edSenha = new JPasswordField();
        btLogin = new JButton("Logar");
        btLogin.setActionCommand("login.logar");
        btSair = new JButton("Sair");
        btSair.addActionListener(this);
        btSair.setActionCommand("login.sair");

        this.add(new JLabel("Login:"));
        this.add(edLogin);
        this.add(new JLabel("Senha:"));
        this.add(edSenha);
        this.add(btSair);
        this.add(btLogin);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public void abrir() {
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    public Usuario getUsuario() {
        user.setLogin(edLogin.getText());
        String strPass = new String(edSenha.getPassword()).trim();
        user.setSenha(strPass);
        
        return user;
    }


    public void addControllerActionListener(ActionListener al) {
        btLogin.addActionListener(al);
    }

    public void terminate() {
        this.dispose();
    }
    
}

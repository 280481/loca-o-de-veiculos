/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.rff.controller;

import br.com.rff.DAO.DaoMarca;
import br.com.rff.interfaces.ControllerInterface;
import br.com.rff.model.Marca;
import br.com.rff.view.TelaMarca;

/**
 *
 * @author refra
 */
public class ControllerMarca implements ControllerInterface {

    private TelaMarca telaMarca;
    private DaoMarca daoMarca;

    public TelaMarca getTelaMarca() {
        return telaMarca;
    }

    public ControllerMarca() {
        daoMarca = new DaoMarca();
        telaMarca = new TelaMarca();
        ControllerModelo CM = new ControllerModelo(this, telaMarca, daoMarca);

    }

    @Override
    public String verificarCamposObrigatorios() {
        Marca marca = (Marca) telaMarca.getModel();
        if (!marca.getTitulo().trim().equals("")) {
            return null;
        }
        return "Campo título vazio!";
    }

}

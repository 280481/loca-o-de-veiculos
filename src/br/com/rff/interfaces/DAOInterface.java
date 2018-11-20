package br.com.rff.interfaces;

import java.util.ArrayList;

public interface DAOInterface {

    public void salvar(Object model);

    public ArrayList<Object> getAll();

    public Object getByID(Object model);

    public void remover(Object model);
    
    public Object getLast();
}

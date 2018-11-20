/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.rff.model;

/**
 *
 * @author refra
 */
public class Automovel {
    private int idAutomovel;
    private String placa;
    private String cor;
    private String ano;
    private String tipoCombustivel;
    private Double kmAtual;
    private String renavam;
    private String chasis;
    private Double vlLocacaoHora;
    private Double vlLocacaoKm;
    private String situacao;
    private Modelo modelo;

    public int getIdAutomovel() {
        return idAutomovel;
    }

    public void setIdAutomovel(int idAutomovel) {
        this.idAutomovel = idAutomovel;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getTipoCombustivel() {
        return tipoCombustivel;
    }

    public void setTipoCombustivel(String tipoCombustivel) {
        this.tipoCombustivel = tipoCombustivel;
    }

    public Double getKmAtual() {
        return kmAtual;
    }

    public void setKmAtual(Double kmAtual) {
        this.kmAtual = kmAtual;
    }

    public String getRenavam() {
        return renavam;
    }

    public void setRenavam(String renavam) {
        this.renavam = renavam;
    }

    public String getChasis() {
        return chasis;
    }

    public void setChasis(String chasis) {
        this.chasis = chasis;
    }

    public Double getVlLocacaoHora() {
        return vlLocacaoHora;
    }

    public void setVlLocacaoHora(Double vlLocacaoHora) {
        this.vlLocacaoHora = vlLocacaoHora;
    }

    public Double getVlLocacaoKm() {
        return vlLocacaoKm;
    }

    public void setVlLocacaoKm(Double vlLocacaoKm) {
        this.vlLocacaoKm = vlLocacaoKm;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }
}

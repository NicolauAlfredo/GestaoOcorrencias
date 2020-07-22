/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Objects;

/**
 *
 * @author user
 */
public class Profissao {

    private Integer idProfissao;
    private String nomeProfissao;

    public Profissao() {
    }

    public Integer getIdProfissao() {
        return idProfissao;
    }

    public void setIdProfissao(Integer idProfissao) {
        this.idProfissao = idProfissao;
    }

    public String getNomeProfissao() {
        return nomeProfissao;
    }

    public void setNomeProfissao(String nomeProfissao) {
        this.nomeProfissao = nomeProfissao;
    }

    @Override
    public String toString() {
        return nomeProfissao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.idProfissao);
        hash = 37 * hash + Objects.hashCode(this.nomeProfissao);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Profissao other = (Profissao) obj;
        if (!Objects.equals(this.nomeProfissao, other.nomeProfissao)) {
            return false;
        }
        if (!Objects.equals(this.idProfissao, other.idProfissao)) {
            return false;
        }
        return true;
    }

}

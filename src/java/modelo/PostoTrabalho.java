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
public class PostoTrabalho {

    private Integer idPostoTrabalho;
    private String nomePostoTrabalho;
    private Integer numeroPostoTrabalho;
    private Municipio municipio;

    public PostoTrabalho() {
    }

    public Integer getIdPostoTrabalho() {
        return idPostoTrabalho;
    }

    public void setIdPostoTrabalho(Integer idPostoTrabalho) {
        this.idPostoTrabalho = idPostoTrabalho;
    }

    public String getNomePostoTrabalho() {
        return nomePostoTrabalho;
    }

    public void setNomePostoTrabalho(String nomePostoTrabalho) {
        this.nomePostoTrabalho = nomePostoTrabalho;
    }

    public Integer getNumeroPostoTrabalho() {
        return numeroPostoTrabalho;
    }

    public void setNumeroPostoTrabalho(Integer numeroPostoTrabalho) {
        this.numeroPostoTrabalho = numeroPostoTrabalho;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    @Override
    public String toString() {
        return nomePostoTrabalho;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.idPostoTrabalho);
        hash = 47 * hash + Objects.hashCode(this.nomePostoTrabalho);
        hash = 47 * hash + Objects.hashCode(this.numeroPostoTrabalho);
        hash = 47 * hash + Objects.hashCode(this.municipio);
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
        final PostoTrabalho other = (PostoTrabalho) obj;
        if (!Objects.equals(this.nomePostoTrabalho, other.nomePostoTrabalho)) {
            return false;
        }
        if (!Objects.equals(this.idPostoTrabalho, other.idPostoTrabalho)) {
            return false;
        }
        if (!Objects.equals(this.numeroPostoTrabalho, other.numeroPostoTrabalho)) {
            return false;
        }
        if (!Objects.equals(this.municipio, other.municipio)) {
            return false;
        }
        return true;
    }

}

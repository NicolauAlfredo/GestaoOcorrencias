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
public class Provincia {

    private Integer idProvincia;
    private String nomeProvincia;

    public Provincia() {
    }

    public Integer getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(Integer idProvincia) {
        this.idProvincia = idProvincia;
    }

    public String getNomeProvincia() {
        return nomeProvincia;
    }

    public void setNomeProvincia(String nomeProvincia) {
        this.nomeProvincia = nomeProvincia;
    }

    @Override
    public String toString() {
        return nomeProvincia;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.idProvincia);
        hash = 83 * hash + Objects.hashCode(this.nomeProvincia);
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
        final Provincia other = (Provincia) obj;
        if (!Objects.equals(this.nomeProvincia, other.nomeProvincia)) {
            return false;
        }
        if (!Objects.equals(this.idProvincia, other.idProvincia)) {
            return false;
        }
        return true;
    }

}

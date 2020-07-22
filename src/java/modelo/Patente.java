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
public class Patente {

    private Integer idPatente;
    private String nomePatente;

    public Patente() {
    }

    public Integer getIdPatente() {
        return idPatente;
    }

    public void setIdPatente(Integer idPatente) {
        this.idPatente = idPatente;
    }

    public String getNomePatente() {
        return nomePatente;
    }

    public void setNomePatente(String nomePatente) {
        this.nomePatente = nomePatente;
    }

    @Override
    public String toString() {
        return nomePatente;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.idPatente);
        hash = 37 * hash + Objects.hashCode(this.nomePatente);
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
        final Patente other = (Patente) obj;
        if (!Objects.equals(this.nomePatente, other.nomePatente)) {
            return false;
        }
        if (!Objects.equals(this.idPatente, other.idPatente)) {
            return false;
        }
        return true;
    }

}

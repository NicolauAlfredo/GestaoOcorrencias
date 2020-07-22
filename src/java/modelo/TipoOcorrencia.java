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
public class TipoOcorrencia {

    private Integer idTipoOcorrencia;
    private String nomeTipoOcorrencia;

    public TipoOcorrencia() {
    }

    public Integer getIdTipoOcorrencia() {
        return idTipoOcorrencia;
    }

    public void setIdTipoOcorrencia(Integer idTipoOcorrencia) {
        this.idTipoOcorrencia = idTipoOcorrencia;
    }

    public String getNomeTipoOcorrencia() {
        return nomeTipoOcorrencia;
    }

    public void setNomeTipoOcorrencia(String nomeTipoOcorrencia) {
        this.nomeTipoOcorrencia = nomeTipoOcorrencia;
    }

    @Override
    public String toString() {
        return "TipoOcorrencia{" + "idTipoOcorrencia=" + idTipoOcorrencia + ", nomeTipoOcorrencia=" + nomeTipoOcorrencia + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.idTipoOcorrencia);
        hash = 53 * hash + Objects.hashCode(this.nomeTipoOcorrencia);
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
        final TipoOcorrencia other = (TipoOcorrencia) obj;
        if (!Objects.equals(this.nomeTipoOcorrencia, other.nomeTipoOcorrencia)) {
            return false;
        }
        if (!Objects.equals(this.idTipoOcorrencia, other.idTipoOcorrencia)) {
            return false;
        }
        return true;
    }

    
}

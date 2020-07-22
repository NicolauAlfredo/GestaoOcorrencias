/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author user
 */
public class Ocorrencia {

    private Integer idOcorrencia;
    private Date dataOcorrencia;
    private String horaOcorrencia;
    private String descricaoOcorrencia;
    private String ruaOcorrencia;
    private String cidadeOcorrencia;
    private String bairroOcorrencia;
    private String proximidadeOcorrencia;
    private Autuado autuado;
    private Autuante autuante;
    private TipoOcorrencia tipoOcorrencia;
    private Testemunha testemunha;
    private Testemunha testemunha1;
    private Municipio municipio;

    public Ocorrencia() {
    }

    public Integer getIdOcorrencia() {
        return idOcorrencia;
    }

    public void setIdOcorrencia(Integer idOcorrencia) {
        this.idOcorrencia = idOcorrencia;
    }

    public Date getDataOcorrencia() {
        return dataOcorrencia;
    }

    public void setDataOcorrencia(Date dataOcorrencia) {
        this.dataOcorrencia = dataOcorrencia;
    }

    public String getHoraOcorrencia() {
        return horaOcorrencia;
    }

    public void setHoraOcorrencia(String horaOcorrencia) {
        this.horaOcorrencia = horaOcorrencia;
    }

    public String getDescricaoOcorrencia() {
        return descricaoOcorrencia;
    }

    public void setDescricaoOcorrencia(String descricaoOcorrencia) {
        this.descricaoOcorrencia = descricaoOcorrencia;
    }

    public String getRuaOcorrencia() {
        return ruaOcorrencia;
    }

    public void setRuaOcorrencia(String ruaOcorrencia) {
        this.ruaOcorrencia = ruaOcorrencia;
    }

    public String getCidadeOcorrencia() {
        return cidadeOcorrencia;
    }

    public void setCidadeOcorrencia(String cidadeOcorrencia) {
        this.cidadeOcorrencia = cidadeOcorrencia;
    }

    public String getBairroOcorrencia() {
        return bairroOcorrencia;
    }

    public void setBairroOcorrencia(String bairroOcorrencia) {
        this.bairroOcorrencia = bairroOcorrencia;
    }

    public String getProximidadeOcorrencia() {
        return proximidadeOcorrencia;
    }

    public void setProximidadeOcorrencia(String proximidadeOcorrencia) {
        this.proximidadeOcorrencia = proximidadeOcorrencia;
    }

    public Autuado getAutuado() {
        return autuado;
    }

    public void setAutuado(Autuado autuado) {
        this.autuado = autuado;
    }

    public Autuante getAutuante() {
        return autuante;
    }

    public void setAutuante(Autuante autuante) {
        this.autuante = autuante;
    }

    public TipoOcorrencia getTipoOcorrencia() {
        return tipoOcorrencia;
    }

    public void setTipoOcorrencia(TipoOcorrencia tipoOcorrencia) {
        this.tipoOcorrencia = tipoOcorrencia;
    }

    public Testemunha getTestemunha() {
        return testemunha;
    }

    public void setTestemunha(Testemunha testemunha) {
        this.testemunha = testemunha;
    }

    public Testemunha getTestemunha1() {
        return testemunha1;
    }

    public void setTestemunha1(Testemunha testemunha1) {
        this.testemunha1 = testemunha1;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    @Override
    public String toString() {
        return "Ocorrencia{" + "idOcorrencia=" + idOcorrencia + ", dataOcorrencia=" + dataOcorrencia + ", horaOcorrencia=" + horaOcorrencia + ", descricaoOcorrencia=" + descricaoOcorrencia + ", ruaOcorrencia=" + ruaOcorrencia + ", cidadeOcorrencia=" + cidadeOcorrencia + ", bairroOcorrencia=" + bairroOcorrencia + ", proximidadeOcorrencia=" + proximidadeOcorrencia + ", autuado=" + autuado + ", autuante=" + autuante + ", tipoOcorrencia=" + tipoOcorrencia + ", testemunha=" + testemunha + ", testemunha1=" + testemunha1 + ", municipio=" + municipio + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.idOcorrencia);
        hash = 41 * hash + Objects.hashCode(this.dataOcorrencia);
        hash = 41 * hash + Objects.hashCode(this.horaOcorrencia);
        hash = 41 * hash + Objects.hashCode(this.descricaoOcorrencia);
        hash = 41 * hash + Objects.hashCode(this.ruaOcorrencia);
        hash = 41 * hash + Objects.hashCode(this.cidadeOcorrencia);
        hash = 41 * hash + Objects.hashCode(this.bairroOcorrencia);
        hash = 41 * hash + Objects.hashCode(this.proximidadeOcorrencia);
        hash = 41 * hash + Objects.hashCode(this.autuado);
        hash = 41 * hash + Objects.hashCode(this.autuante);
        hash = 41 * hash + Objects.hashCode(this.tipoOcorrencia);
        hash = 41 * hash + Objects.hashCode(this.testemunha);
        hash = 41 * hash + Objects.hashCode(this.testemunha1);
        hash = 41 * hash + Objects.hashCode(this.municipio);
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
        final Ocorrencia other = (Ocorrencia) obj;
        if (!Objects.equals(this.horaOcorrencia, other.horaOcorrencia)) {
            return false;
        }
        if (!Objects.equals(this.descricaoOcorrencia, other.descricaoOcorrencia)) {
            return false;
        }
        if (!Objects.equals(this.ruaOcorrencia, other.ruaOcorrencia)) {
            return false;
        }
        if (!Objects.equals(this.cidadeOcorrencia, other.cidadeOcorrencia)) {
            return false;
        }
        if (!Objects.equals(this.bairroOcorrencia, other.bairroOcorrencia)) {
            return false;
        }
        if (!Objects.equals(this.proximidadeOcorrencia, other.proximidadeOcorrencia)) {
            return false;
        }
        if (!Objects.equals(this.idOcorrencia, other.idOcorrencia)) {
            return false;
        }
        if (!Objects.equals(this.dataOcorrencia, other.dataOcorrencia)) {
            return false;
        }
        if (!Objects.equals(this.autuado, other.autuado)) {
            return false;
        }
        if (!Objects.equals(this.autuante, other.autuante)) {
            return false;
        }
        if (!Objects.equals(this.tipoOcorrencia, other.tipoOcorrencia)) {
            return false;
        }
        if (!Objects.equals(this.testemunha, other.testemunha)) {
            return false;
        }
        if (!Objects.equals(this.testemunha1, other.testemunha1)) {
            return false;
        }
        if (!Objects.equals(this.municipio, other.municipio)) {
            return false;
        }
        return true;
    }

    
}

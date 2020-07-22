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
public class Autuado {

    private Integer idAutuado;
    private String nomeAutuado;
    private String paiAutuado;
    private String maeAutuado;
    private String biAutuado;
    private String residenciaAutuado;
    private Date dataNascimentoAutuado;
    private Sexo sexo;
    private String proximidadeAutuado;
    private String estadoCivilAutuado;
    private Date dataEmissaoBiAutuado;
    private Date dataValidadeBiAutuado;
    private String telefoneAutuado;
    private Profissao profissao;
    private Municipio municipio;

    public Autuado() {
    }

    public Integer getIdAutuado() {
        return idAutuado;
    }

    public void setIdAutuado(Integer idAutuado) {
        this.idAutuado = idAutuado;
    }

    public String getNomeAutuado() {
        return nomeAutuado;
    }

    public void setNomeAutuado(String nomeAutuado) {
        this.nomeAutuado = nomeAutuado;
    }

    public String getPaiAutuado() {
        return paiAutuado;
    }

    public void setPaiAutuado(String paiAutuado) {
        this.paiAutuado = paiAutuado;
    }

    public String getMaeAutuado() {
        return maeAutuado;
    }

    public void setMaeAutuado(String maeAutuado) {
        this.maeAutuado = maeAutuado;
    }

    public String getBiAutuado() {
        return biAutuado;
    }

    public void setBiAutuado(String biAutuado) {
        this.biAutuado = biAutuado;
    }

    public String getResidenciaAutuado() {
        return residenciaAutuado;
    }

    public void setResidenciaAutuado(String residenciaAutuado) {
        this.residenciaAutuado = residenciaAutuado;
    }

    public Date getDataNascimentoAutuado() {
        return dataNascimentoAutuado;
    }

    public void setDataNascimentoAutuado(Date dataNascimentoAutuado) {
        this.dataNascimentoAutuado = dataNascimentoAutuado;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public String getProximidadeAutuado() {
        return proximidadeAutuado;
    }

    public void setProximidadeAutuado(String proximidadeAutuado) {
        this.proximidadeAutuado = proximidadeAutuado;
    }

    public String getEstadoCivilAutuado() {
        return estadoCivilAutuado;
    }

    public void setEstadoCivilAutuado(String estadoCivilAutuado) {
        this.estadoCivilAutuado = estadoCivilAutuado;
    }

    public Date getDataEmissaoBiAutuado() {
        return dataEmissaoBiAutuado;
    }

    public void setDataEmissaoBiAutuado(Date dataEmissaoBiAutuado) {
        this.dataEmissaoBiAutuado = dataEmissaoBiAutuado;
    }

    public Date getDataValidadeBiAutuado() {
        return dataValidadeBiAutuado;
    }

    public void setDataValidadeBiAutuado(Date dataValidadeBiAutuado) {
        this.dataValidadeBiAutuado = dataValidadeBiAutuado;
    }

    public String getTelefoneAutuado() {
        return telefoneAutuado;
    }

    public void setTelefoneAutuado(String telefoneAutuado) {
        this.telefoneAutuado = telefoneAutuado;
    }

    public Profissao getProfissao() {
        return profissao;
    }

    public void setProfissao(Profissao profissao) {
        this.profissao = profissao;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    @Override
    public String toString() {
        return "Autuado{" + "idAutuado=" + idAutuado + ", nomeAutuado=" + nomeAutuado + ", paiAutuado=" + paiAutuado + ", maeAutuado=" + maeAutuado + ", biAutuado=" + biAutuado + ", residenciaAutuado=" + residenciaAutuado + ", dataNascimentoAutuado=" + dataNascimentoAutuado + ", sexo=" + sexo + ", proximidadeAutuado=" + proximidadeAutuado + ", estadoCivilAutuado=" + estadoCivilAutuado + ", dataEmissaoBiAutuado=" + dataEmissaoBiAutuado + ", dataValidadeBiAutuado=" + dataValidadeBiAutuado + ", telefoneAutuado=" + telefoneAutuado + ", profissao=" + profissao + ", municipio=" + municipio + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.idAutuado);
        hash = 73 * hash + Objects.hashCode(this.nomeAutuado);
        hash = 73 * hash + Objects.hashCode(this.paiAutuado);
        hash = 73 * hash + Objects.hashCode(this.maeAutuado);
        hash = 73 * hash + Objects.hashCode(this.biAutuado);
        hash = 73 * hash + Objects.hashCode(this.residenciaAutuado);
        hash = 73 * hash + Objects.hashCode(this.dataNascimentoAutuado);
        hash = 73 * hash + Objects.hashCode(this.sexo);
        hash = 73 * hash + Objects.hashCode(this.proximidadeAutuado);
        hash = 73 * hash + Objects.hashCode(this.estadoCivilAutuado);
        hash = 73 * hash + Objects.hashCode(this.dataEmissaoBiAutuado);
        hash = 73 * hash + Objects.hashCode(this.dataValidadeBiAutuado);
        hash = 73 * hash + Objects.hashCode(this.telefoneAutuado);
        hash = 73 * hash + Objects.hashCode(this.profissao);
        hash = 73 * hash + Objects.hashCode(this.municipio);
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
        final Autuado other = (Autuado) obj;
        if (!Objects.equals(this.nomeAutuado, other.nomeAutuado)) {
            return false;
        }
        if (!Objects.equals(this.paiAutuado, other.paiAutuado)) {
            return false;
        }
        if (!Objects.equals(this.maeAutuado, other.maeAutuado)) {
            return false;
        }
        if (!Objects.equals(this.biAutuado, other.biAutuado)) {
            return false;
        }
        if (!Objects.equals(this.residenciaAutuado, other.residenciaAutuado)) {
            return false;
        }
        if (!Objects.equals(this.proximidadeAutuado, other.proximidadeAutuado)) {
            return false;
        }
        if (!Objects.equals(this.estadoCivilAutuado, other.estadoCivilAutuado)) {
            return false;
        }
        if (!Objects.equals(this.telefoneAutuado, other.telefoneAutuado)) {
            return false;
        }
        if (!Objects.equals(this.idAutuado, other.idAutuado)) {
            return false;
        }
        if (!Objects.equals(this.dataNascimentoAutuado, other.dataNascimentoAutuado)) {
            return false;
        }
        if (this.sexo != other.sexo) {
            return false;
        }
        if (!Objects.equals(this.dataEmissaoBiAutuado, other.dataEmissaoBiAutuado)) {
            return false;
        }
        if (!Objects.equals(this.dataValidadeBiAutuado, other.dataValidadeBiAutuado)) {
            return false;
        }
        if (!Objects.equals(this.profissao, other.profissao)) {
            return false;
        }
        if (!Objects.equals(this.municipio, other.municipio)) {
            return false;
        }
        return true;
    }

  
    
}

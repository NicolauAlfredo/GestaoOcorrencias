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
public class Testemunha {

    private Integer idTestemunha;
    private String nomeTestemunha;
    private String paiTestemunha;
    private String maeTestemunha;
    private String biTestemunha;
    private String residenciaTestemunha;
    private Date dataNascimentoTestemunha;
    private Sexo sexo;
    private String proximidadeTestemunha;
    private String estadoCivilTestemunha;
    private Date dataEmissaoBiTestemunha;
    private Date dataValidadeBiTestemunha;
    private String telefoneTestemunha;
    private Municipio municipio;
    private Profissao profissao;

    public Testemunha() {
    }

    public Integer getIdTestemunha() {
        return idTestemunha;
    }

    public void setIdTestemunha(Integer idTestemunha) {
        this.idTestemunha = idTestemunha;
    }

    public String getNomeTestemunha() {
        return nomeTestemunha;
    }

    public void setNomeTestemunha(String nomeTestemunha) {
        this.nomeTestemunha = nomeTestemunha;
    }

    public String getPaiTestemunha() {
        return paiTestemunha;
    }

    public void setPaiTestemunha(String paiTestemunha) {
        this.paiTestemunha = paiTestemunha;
    }

    public String getMaeTestemunha() {
        return maeTestemunha;
    }

    public void setMaeTestemunha(String maeTestemunha) {
        this.maeTestemunha = maeTestemunha;
    }

    public String getBiTestemunha() {
        return biTestemunha;
    }

    public void setBiTestemunha(String biTestemunha) {
        this.biTestemunha = biTestemunha;
    }

    public String getResidenciaTestemunha() {
        return residenciaTestemunha;
    }

    public void setResidenciaTestemunha(String residenciaTestemunha) {
        this.residenciaTestemunha = residenciaTestemunha;
    }

    public Date getDataNascimentoTestemunha() {
        return dataNascimentoTestemunha;
    }

    public void setDataNascimentoTestemunha(Date dataNascimentoTestemunha) {
        this.dataNascimentoTestemunha = dataNascimentoTestemunha;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public String getProximidadeTestemunha() {
        return proximidadeTestemunha;
    }

    public void setProximidadeTestemunha(String proximidadeTestemunha) {
        this.proximidadeTestemunha = proximidadeTestemunha;
    }

    public String getEstadoCivilTestemunha() {
        return estadoCivilTestemunha;
    }

    public void setEstadoCivilTestemunha(String estadoCivilTestemunha) {
        this.estadoCivilTestemunha = estadoCivilTestemunha;
    }

    public Date getDataEmissaoBiTestemunha() {
        return dataEmissaoBiTestemunha;
    }

    public void setDataEmissaoBiTestemunha(Date dataEmissaoBiTestemunha) {
        this.dataEmissaoBiTestemunha = dataEmissaoBiTestemunha;
    }

    public Date getDataValidadeBiTestemunha() {
        return dataValidadeBiTestemunha;
    }

    public void setDataValidadeBiTestemunha(Date dataValidadeBiTestemunha) {
        this.dataValidadeBiTestemunha = dataValidadeBiTestemunha;
    }

    public String getTelefoneTestemunha() {
        return telefoneTestemunha;
    }

    public void setTelefoneTestemunha(String telefoneTestemunha) {
        this.telefoneTestemunha = telefoneTestemunha;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public Profissao getProfissao() {
        return profissao;
    }

    public void setProfissao(Profissao profissao) {
        this.profissao = profissao;
    }

    @Override
    public String toString() {
        return "Testemunha{" + "idTestemunha=" + idTestemunha + ", nomeTestemunha=" + nomeTestemunha + ", paiTestemunha=" + paiTestemunha + ", maeTestemunha=" + maeTestemunha + ", biTestemunha=" + biTestemunha + ", residenciaTestemunha=" + residenciaTestemunha + ", dataNascimentoTestemunha=" + dataNascimentoTestemunha + ", sexo=" + sexo + ", proximidadeTestemunha=" + proximidadeTestemunha + ", estadoCivilTestemunha=" + estadoCivilTestemunha + ", dataEmissaoBiTestemunha=" + dataEmissaoBiTestemunha + ", dataValidadeBiTestemunha=" + dataValidadeBiTestemunha + ", telefoneTestemunha=" + telefoneTestemunha + ", municipio=" + municipio + ", profissao=" + profissao + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.idTestemunha);
        hash = 67 * hash + Objects.hashCode(this.nomeTestemunha);
        hash = 67 * hash + Objects.hashCode(this.paiTestemunha);
        hash = 67 * hash + Objects.hashCode(this.maeTestemunha);
        hash = 67 * hash + Objects.hashCode(this.biTestemunha);
        hash = 67 * hash + Objects.hashCode(this.residenciaTestemunha);
        hash = 67 * hash + Objects.hashCode(this.dataNascimentoTestemunha);
        hash = 67 * hash + Objects.hashCode(this.sexo);
        hash = 67 * hash + Objects.hashCode(this.proximidadeTestemunha);
        hash = 67 * hash + Objects.hashCode(this.estadoCivilTestemunha);
        hash = 67 * hash + Objects.hashCode(this.dataEmissaoBiTestemunha);
        hash = 67 * hash + Objects.hashCode(this.dataValidadeBiTestemunha);
        hash = 67 * hash + Objects.hashCode(this.telefoneTestemunha);
        hash = 67 * hash + Objects.hashCode(this.municipio);
        hash = 67 * hash + Objects.hashCode(this.profissao);
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
        final Testemunha other = (Testemunha) obj;
        if (!Objects.equals(this.nomeTestemunha, other.nomeTestemunha)) {
            return false;
        }
        if (!Objects.equals(this.paiTestemunha, other.paiTestemunha)) {
            return false;
        }
        if (!Objects.equals(this.maeTestemunha, other.maeTestemunha)) {
            return false;
        }
        if (!Objects.equals(this.biTestemunha, other.biTestemunha)) {
            return false;
        }
        if (!Objects.equals(this.residenciaTestemunha, other.residenciaTestemunha)) {
            return false;
        }
        if (!Objects.equals(this.proximidadeTestemunha, other.proximidadeTestemunha)) {
            return false;
        }
        if (!Objects.equals(this.estadoCivilTestemunha, other.estadoCivilTestemunha)) {
            return false;
        }
        if (!Objects.equals(this.telefoneTestemunha, other.telefoneTestemunha)) {
            return false;
        }
        if (!Objects.equals(this.idTestemunha, other.idTestemunha)) {
            return false;
        }
        if (!Objects.equals(this.dataNascimentoTestemunha, other.dataNascimentoTestemunha)) {
            return false;
        }
        if (this.sexo != other.sexo) {
            return false;
        }
        if (!Objects.equals(this.dataEmissaoBiTestemunha, other.dataEmissaoBiTestemunha)) {
            return false;
        }
        if (!Objects.equals(this.dataValidadeBiTestemunha, other.dataValidadeBiTestemunha)) {
            return false;
        }
        if (!Objects.equals(this.municipio, other.municipio)) {
            return false;
        }
        if (!Objects.equals(this.profissao, other.profissao)) {
            return false;
        }
        return true;
    }

    
}

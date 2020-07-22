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
public class Autuante {
    private Integer idAutuante;
    private String nomeAutuante;
    private String paiAutuante;
    private String maeAutuante;
    private String biAutuante;
    private String residenciaAutuante;
    private Date dataNascimentoAutuante;
    private Sexo sexo;
    private Double alturaAutuante;
    private Date dataEmissaoBiAutuante;
    private Date dataValidadeBiAutuante;
    private Integer nipAutuante;
    private String telefoneAutuante;
    private Patente patente;
    private Municipio municipio;
    private PostoTrabalho postoTrabalho;

    public Autuante() {
    }

    public Integer getIdAutuante() {
        return idAutuante;
    }

    public void setIdAutuante(Integer idAutuante) {
        this.idAutuante = idAutuante;
    }

    public String getNomeAutuante() {
        return nomeAutuante;
    }

    public void setNomeAutuante(String nomeAutuante) {
        this.nomeAutuante = nomeAutuante;
    }

    public String getPaiAutuante() {
        return paiAutuante;
    }

    public void setPaiAutuante(String paiAutuante) {
        this.paiAutuante = paiAutuante;
    }

    public String getMaeAutuante() {
        return maeAutuante;
    }

    public void setMaeAutuante(String maeAutuante) {
        this.maeAutuante = maeAutuante;
    }

    public String getBiAutuante() {
        return biAutuante;
    }

    public void setBiAutuante(String biAutuante) {
        this.biAutuante = biAutuante;
    }

    public String getResidenciaAutuante() {
        return residenciaAutuante;
    }

    public void setResidenciaAutuante(String residenciaAutuante) {
        this.residenciaAutuante = residenciaAutuante;
    }

    public Date getDataNascimentoAutuante() {
        return dataNascimentoAutuante;
    }

    public void setDataNascimentoAutuante(Date dataNascimentoAutuante) {
        this.dataNascimentoAutuante = dataNascimentoAutuante;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public Double getAlturaAutuante() {
        return alturaAutuante;
    }

    public void setAlturaAutuante(Double alturaAutuante) {
        this.alturaAutuante = alturaAutuante;
    }

    public Date getDataEmissaoBiAutuante() {
        return dataEmissaoBiAutuante;
    }

    public void setDataEmissaoBiAutuante(Date dataEmissaoBiAutuante) {
        this.dataEmissaoBiAutuante = dataEmissaoBiAutuante;
    }

    public Date getDataValidadeBiAutuante() {
        return dataValidadeBiAutuante;
    }

    public void setDataValidadeBiAutuante(Date dataValidadeBiAutuante) {
        this.dataValidadeBiAutuante = dataValidadeBiAutuante;
    }

    public Integer getNipAutuante() {
        return nipAutuante;
    }

    public void setNipAutuante(Integer nipAutuante) {
        this.nipAutuante = nipAutuante;
    }

    public String getTelefoneAutuante() {
        return telefoneAutuante;
    }

    public void setTelefoneAutuante(String telefoneAutuante) {
        this.telefoneAutuante = telefoneAutuante;
    }

    public Patente getPatente() {
        return patente;
    }

    public void setPatente(Patente patente) {
        this.patente = patente;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public PostoTrabalho getPostoTrabalho() {
        return postoTrabalho;
    }

    public void setPostoTrabalho(PostoTrabalho postoTrabalho) {
        this.postoTrabalho = postoTrabalho;
    }

    @Override
    public String toString() {
        return "Autuante{" + "idAutuante=" + idAutuante + ", nomeAutuante=" + nomeAutuante + ", paiAutuante=" + paiAutuante + ", maeAutuante=" + maeAutuante + ", biAutuante=" + biAutuante + ", residenciaAutuante=" + residenciaAutuante + ", dataNascimentoAutuante=" + dataNascimentoAutuante + ", sexo=" + sexo + ", alturaAutuante=" + alturaAutuante + ", dataEmissaoBiAutuante=" + dataEmissaoBiAutuante + ", dataValidadeBiAutuante=" + dataValidadeBiAutuante + ", nipAutuante=" + nipAutuante + ", telefoneAutuante=" + telefoneAutuante + ", patente=" + patente + ", municipio=" + municipio + ", postoTrabalho=" + postoTrabalho + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.idAutuante);
        hash = 13 * hash + Objects.hashCode(this.nomeAutuante);
        hash = 13 * hash + Objects.hashCode(this.paiAutuante);
        hash = 13 * hash + Objects.hashCode(this.maeAutuante);
        hash = 13 * hash + Objects.hashCode(this.biAutuante);
        hash = 13 * hash + Objects.hashCode(this.residenciaAutuante);
        hash = 13 * hash + Objects.hashCode(this.dataNascimentoAutuante);
        hash = 13 * hash + Objects.hashCode(this.sexo);
        hash = 13 * hash + Objects.hashCode(this.alturaAutuante);
        hash = 13 * hash + Objects.hashCode(this.dataEmissaoBiAutuante);
        hash = 13 * hash + Objects.hashCode(this.dataValidadeBiAutuante);
        hash = 13 * hash + Objects.hashCode(this.nipAutuante);
        hash = 13 * hash + Objects.hashCode(this.telefoneAutuante);
        hash = 13 * hash + Objects.hashCode(this.patente);
        hash = 13 * hash + Objects.hashCode(this.municipio);
        hash = 13 * hash + Objects.hashCode(this.postoTrabalho);
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
        final Autuante other = (Autuante) obj;
        if (!Objects.equals(this.nomeAutuante, other.nomeAutuante)) {
            return false;
        }
        if (!Objects.equals(this.paiAutuante, other.paiAutuante)) {
            return false;
        }
        if (!Objects.equals(this.maeAutuante, other.maeAutuante)) {
            return false;
        }
        if (!Objects.equals(this.biAutuante, other.biAutuante)) {
            return false;
        }
        if (!Objects.equals(this.residenciaAutuante, other.residenciaAutuante)) {
            return false;
        }
        if (!Objects.equals(this.telefoneAutuante, other.telefoneAutuante)) {
            return false;
        }
        if (!Objects.equals(this.idAutuante, other.idAutuante)) {
            return false;
        }
        if (!Objects.equals(this.dataNascimentoAutuante, other.dataNascimentoAutuante)) {
            return false;
        }
        if (this.sexo != other.sexo) {
            return false;
        }
        if (!Objects.equals(this.alturaAutuante, other.alturaAutuante)) {
            return false;
        }
        if (!Objects.equals(this.dataEmissaoBiAutuante, other.dataEmissaoBiAutuante)) {
            return false;
        }
        if (!Objects.equals(this.dataValidadeBiAutuante, other.dataValidadeBiAutuante)) {
            return false;
        }
        if (!Objects.equals(this.nipAutuante, other.nipAutuante)) {
            return false;
        }
        if (!Objects.equals(this.patente, other.patente)) {
            return false;
        }
        if (!Objects.equals(this.municipio, other.municipio)) {
            return false;
        }
        if (!Objects.equals(this.postoTrabalho, other.postoTrabalho)) {
            return false;
        }
        return true;
    }

    
}

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
public class Administrador {

    private Integer idAdministrador;
    private String nomeAdministrador;
    private Date dataNascimentoAdministrador;
    private Sexo sexo;
    private String biAdministrador;
    private Integer nipAdministrador;
    private String emailAdministrador;
    private String telefoneAdministrador;
    private String palavraPasseAdministrador;

    public Administrador() {
    }

    public Integer getIdAdministrador() {
        return idAdministrador;
    }

    public void setIdAdministrador(Integer idAdministrador) {
        this.idAdministrador = idAdministrador;
    }

    public String getNomeAdministrador() {
        return nomeAdministrador;
    }

    public void setNomeAdministrador(String nomeAdministrador) {
        this.nomeAdministrador = nomeAdministrador;
    }

    public Date getDataNascimentoAdministrador() {
        return dataNascimentoAdministrador;
    }

    public void setDataNascimentoAdministrador(Date dataNascimentoAdministrador) {
        this.dataNascimentoAdministrador = dataNascimentoAdministrador;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public String getBiAdministrador() {
        return biAdministrador;
    }

    public void setBiAdministrador(String biAdministrador) {
        this.biAdministrador = biAdministrador;
    }

    public Integer getNipAdministrador() {
        return nipAdministrador;
    }

    public void setNipAdministrador(Integer nipAdministrador) {
        this.nipAdministrador = nipAdministrador;
    }

    public String getEmailAdministrador() {
        return emailAdministrador;
    }

    public void setEmailAdministrador(String emailAdministrador) {
        this.emailAdministrador = emailAdministrador;
    }

    public String getTelefoneAdministrador() {
        return telefoneAdministrador;
    }

    public void setTelefoneAdministrador(String telefoneAdministrador) {
        this.telefoneAdministrador = telefoneAdministrador;
    }

    public String getPalavraPasseAdministrador() {
        return palavraPasseAdministrador;
    }

    public void setPalavraPasseAdministrador(String palavraPasseAdministrador) {
        this.palavraPasseAdministrador = palavraPasseAdministrador;
    }

    @Override
    public String toString() {
        return "Administrador{" + "idAdministrador=" + idAdministrador + ", nomeAdministrador=" + nomeAdministrador + ", dataNascimentoAdministrador=" + dataNascimentoAdministrador + ", sexo=" + sexo + ", biAdministrador=" + biAdministrador + ", nipAdministrador=" + nipAdministrador + ", emailAdministrador=" + emailAdministrador + ", telefoneAdministrador=" + telefoneAdministrador + ", palavraPasseAdministrador=" + palavraPasseAdministrador + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.idAdministrador);
        hash = 97 * hash + Objects.hashCode(this.nomeAdministrador);
        hash = 97 * hash + Objects.hashCode(this.dataNascimentoAdministrador);
        hash = 97 * hash + Objects.hashCode(this.sexo);
        hash = 97 * hash + Objects.hashCode(this.biAdministrador);
        hash = 97 * hash + Objects.hashCode(this.nipAdministrador);
        hash = 97 * hash + Objects.hashCode(this.emailAdministrador);
        hash = 97 * hash + Objects.hashCode(this.telefoneAdministrador);
        hash = 97 * hash + Objects.hashCode(this.palavraPasseAdministrador);
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
        final Administrador other = (Administrador) obj;
        if (!Objects.equals(this.nomeAdministrador, other.nomeAdministrador)) {
            return false;
        }
        if (!Objects.equals(this.biAdministrador, other.biAdministrador)) {
            return false;
        }
        if (!Objects.equals(this.emailAdministrador, other.emailAdministrador)) {
            return false;
        }
        if (!Objects.equals(this.telefoneAdministrador, other.telefoneAdministrador)) {
            return false;
        }
        if (!Objects.equals(this.palavraPasseAdministrador, other.palavraPasseAdministrador)) {
            return false;
        }
        if (!Objects.equals(this.idAdministrador, other.idAdministrador)) {
            return false;
        }
        if (!Objects.equals(this.dataNascimentoAdministrador, other.dataNascimentoAdministrador)) {
            return false;
        }
        if (this.sexo != other.sexo) {
            return false;
        }
        if (!Objects.equals(this.nipAdministrador, other.nipAdministrador)) {
            return false;
        }
        return true;
    }

    
}

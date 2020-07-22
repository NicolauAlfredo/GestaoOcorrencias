/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author user
 */
public enum EstadoCivil {
    SOLTEIRO("Solteiro/a"),
    NUMARELACAO("Numa relação"),
    NOIVO("Noivo/a"),
    CASADO("Casado/a"),
    NUMAUNIAOCIVIL("Numa união cívil"),
    NUMAUNIAODEFACTO("Numa união de facto"),
    NUMARELACAOABERTA("Numa relação aberta"),
    ECOMPLICADO("É complicado"),
    SEPARADO("Separado/a"),
    DIVORCIADO("Divorciado/a"),
    VIUVO("Viúvo/a");

    private String status;

    private EstadoCivil(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}

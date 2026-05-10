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
public enum Sexo {

    MASCULINO("M", "Masculino"),
    FEMININO("F", "Feminino");

    private final String abreviatura;
    private final String extensao;

    private Sexo(String abreviatura, String extensao) {
        this.abreviatura = abreviatura;
        this.extensao = extensao;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    /*
     * Mantido por compatibilidade com código antigo.
     */
    public String getAbrevitura() {
        return abreviatura;
    }

    public String getExtensao() {
        return extensao;
    }

    public static Sexo fromExtensao(String valor) {

        if (valor == null) {
            return null;
        }

        for (Sexo sexo : values()) {

            if (sexo.getExtensao().equalsIgnoreCase(valor)) {
                return sexo;
            }
        }

        return null;
    }
}

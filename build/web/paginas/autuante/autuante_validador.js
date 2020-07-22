function autuanteValidador() {
    var mensagemErro = "";
    nome_autuante = document.getElementById("nome_autuante").value;
    bi_autuante = document.getElementById("bi_autuante").value;
    residencia_autuante = document.getElementById("residencia_autuante").value;
    naturalidade_autuante = document.getElementById("select_municipio_autuante").value;
    data_nascimento_autuante = document.getElementById("data_nascimento_autuante").value;
    altura_autuante = document.getElementById("altura_autuante").value;
    data_emissao_bi_autuante = document.getElementById("data_emissao_bi_autuante").value;
    data_validade_bi_autuante = document.getElementById("data_validade_bi_autuante").value;
    numero_autuante = document.getElementById("numero_autuante").value;
    patente_autuante = document.getElementById("_autuante").value;
    posto_trabalho_autuante = document.getElementById("select_patente_autuante").value;

    if (nome_autuante == "") {
        mensagemErro += "PREENCHA O CAMPO NOME! \n";
    }

    if (bi_autuante == "") {
        mensagemErro += "PREENCHA O CAMPO BILHETE DE IDENTIDADE! \n";
    }

    if (residencia_autuante == "") {
        mensagemErro += "PREENCHA O CAMPO RESIDÊNCIA! \n";
    }

    if (naturalidade_autuante == "") {
        mensagemErro += "SELECIONE O MUNICÍPIO! \n";
    }

    if (document.form_autuante.sexo_autuante[0].checked == false && document.form_autuante.sexo_autuante[1].checked == false) {
        mensagemErro += "SELECIONE O SEXO! \n";
    }

    if (data_nascimento_autuante == "") {
        mensagemErro += "PREENCHA O CAMPO DATA DE NASCIMENTO! \n";
    }

    if (data_emissao_bi_autuante == "") {
        mensagemErro += "PREENCHA O CAMPO DATA DE EMISSÃO DO BILHETE DE IDENTIDADE! \n";
    }

    if (data_validade_bi_autuante == "") {
        mensagemErro += "PREENCHA O CAMPO DATA DE VALIDADE DO BILHETE DE IDENTIDADE! \n";
    }

    if (altura_autuante == "") {
        mensagemErro += "PREENCHA O CAMPO ALTURA! \n";
    }

    if (numero_autuante == "") {
        mensagemErro += "PREENCHA O CAMPO NÚMERO! \n";
    }

    if (patente_autuante == "") {
        mensagemErro += "PREENCHA O CAMPO PATENTE! \n";
    }

    if (posto_trabalho_autuante == "") {
        mensagemErro += "PREENCHA O CAMPO POSTO DE TRABALHO! \n";
    }

    if (mensagemErro != "") {
        alert(mensagemErro);
        return false;
    }
}



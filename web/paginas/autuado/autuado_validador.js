function autuadoValidador() {
    var mensagemErro = "";
    nome_autuado = document.getElementById("nome_autuado").value;
    bi_autuado = document.getElementById("bi_autuado").value;
    residencia_autuado = document.getElementById("residencia_autuado").value;
    proximidade_autuado = document.getElementById("proximidade_autuado").value;
    naturalidade_autuado = document.getElementById("select_municipio_autuado").value;
    data_nascimento_autuado = document.getElementById("data_nascimento_autuado").value;
    data_emissao_bi_autuado = document.getElementById("data_emissao_bi_autuado").value;
    data_validade_bi_autuado = document.getElementById("data_validade_bi_autuado").value;
    estado_civil_autuado = document.getElementById("estado_civil_autuado").value;

    if (nome_autuado == "") {
        mensagemErro += "PREENCHA O CAMPO NOME! \n";
    }

    if (bi_autuado == "") {
        mensagemErro += "PREENCHA O CAMPO BILHETE DE IDENTIDADE! \n";
    }

    if (residencia_autuado == "") {
        mensagemErro += "PREENCHA O CAMPO RESIDÊNCIA! \n";
    }

    if (proximidade_autuado == "") {
        mensagemErro += "PREENCHA O CAMPO PROXIMIDADE! \n";
    }

    if (naturalidade_autuado == "") {
        mensagemErro += "SELECIONE O MUNICÍPIO! \n";
    }

    if (document.form_autuado.sexo_autuado[0].checked == false && document.form_autuado.sexo_autuado[1].checked == false) {
        mensagemErro += "SELECIONE O SEXO! \n";
    }

    if (data_nascimento_autuado == "") {
        mensagemErro += "PREENCHA O CAMPO DATA DE NASCIMENTO! \n";
    }

    if (data_emissao_bi_autuado == "") {
        mensagemErro += "PREENCHA O CAMPO DATA DE EMISSÃO DO BILHETE DE IDENTIDADE! \n";
    }

    if (data_validade_bi_autuado == "") {
        mensagemErro += "PREENCHA O CAMPO DATA DE VALIDADE DO BILHETE DE IDENTIDADE! \n";
    }

    if (estado_civil_autuado == "") {
        mensagemErro += "PREENCHA O CAMPO ESTADO CÍVIL! \n";
    }

    if (mensagemErro != "") {
        alert(mensagemErro);
        return false;
    }
}



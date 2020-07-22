function ocorrenciaValidador() {
    var mensagemErro = "";
    data_ocorrencia = document.getElementById("data_ocorrencia").value;
    hora_ocorrencia = document.getElementById("hora_ocorrencia").value;
    rua_ocorrencia = document.getElementById("rua_ocorrencia").value;
    cidade_ocorrencia = document.getElementById("cidade_ocorrencia").value;
    descricao_ocorrencia = document.getElementById("descricao_ocorrencia").value;
    select_autuante = document.getElementById("select_autuante").value;
    select_tipo_ocorrencia = document.getElementById("select_tipo_ocorrencia").value;
    select_autuado = document.getElementById("select_autuado").value;
    bairro_ocorrencia = document.getElementById("bairro_ocorrencia").value;
    proximidade_ocorrencia = document.getElementById("proximidade_ocorrencia").value;
    select_municipio = document.getElementById("select_municipio").value;
    select_testemunha = document.getElementById("select_testemunha").value;
    select_testemunha_sec = document.getElementById("select_municipio").value;

    if (data_ocorrencia == "") {
        mensagemErro += "PREENCHA O CAMPO DATA! \n";
    }

    if (hora_ocorrencia == "") {
        mensagemErro += "PREENCHA O CAMPO HORA! \n";
    }

    if (rua_ocorrencia == "") {
        mensagemErro += "PREENCHA O CAMPO RUA! \n";
    }

    if (cidade_ocorrencia == "") {
        mensagemErro += "PREENCHA O CAMPO CIDADE! \n";
    }

    if (descricao_ocorrencia == "") {
        mensagemErro += "PREENCHA O CAMPO DESCRIÇÃO! \n";
    }

    if (select_autuante == "") {
        mensagemErro += "SELECIONE O AUTUANTE! \n";
    }

    if (select_tipo_ocorrencia == "") {
        mensagemErro += "SELECIONE O TIPO DE OCORRÊNCIA! \n";
    }

    if (select_autuado == "") {
        mensagemErro += "SELECIONE O AUTUADO! \n";
    }

    if (bairro_ocorrencia == "") {
        mensagemErro += "PREENCHA O CAMPO BAIRRO! \n";
    }

    if (proximidade_ocorrencia == "") {
        mensagemErro += "PREENCHA O CAMPO PROXIMIDADE! \n";
    }

    if (select_municipio == "") {
        mensagemErro += "SELECIONE O MUNICÍPIO! \n";
    }
    
    if (select_testemunha == "") {
        mensagemErro += "SELECIONE A 1º TESTEMUNHA! \n";
    }
    
    if (select_testemunha_sec == "") {
        mensagemErro += "SELECIONE A 2º TESTEMUNHA! \n";
    }

    if (mensagemErro != "") {
        alert(mensagemErro);
        return false;
    }
}



function testemunhaValidador() {
    var mensagemErro = "";
    nome_testemunha = document.getElementById("nome_testemunha").value;
    bi_testemunha = document.getElementById("bi_testemunha").value;
    residencia_testemunha = document.getElementById("residencia_testemunha").value;
    select_municipio_testemunha = document.getElementById("select_municipio_testemunha").value;
    data_nascimento_testemunha = document.getElementById("data_nascimento_testemunha").value;
    altura_testemunha = document.getElementById("altura_testemunha").value;
    estado_civil_testemunha = document.getElementById("estado_civil_testemunha").value;
    data_emissao_bi_testemunha = document.getElementById("data_emissao_bi_testemunha").value;
    data_validade_bi_testemunha = document.getElementById("data_validade_bi_testemunha").value;

    if (nome_testemunha == "") {
        mensagemErro += "PREENCHA O CAMPO NOME! \n";
    }

    if (bi_testemunha == "") {
        mensagemErro += "PREENCHA O CAMPO BILHETE DE IDENTIDADE! \n";
    }

    if (residencia_testemunha == "") {
        mensagemErro += "PREENCHA O CAMPO RESIDÊNCIA! \n";
    }

    if (select_municipio_testemunha == "") {
        mensagemErro += "SELECIONE O MUNICÍPIO! \n";
    }

    if (document.form_testemunha.sexo_testemunha[0].checked == false && document.form_testemunha.sexo_testemunha[1].checked == false) {
        mensagemErro += "SELECIONE O SEXO! \n";
    }

    if (data_nascimento_testemunha == "") {
        mensagemErro += "PREENCHA O CAMPO DATA DE NASCIMENTO! \n";
    }

    if (altura_testemunha == "") {
        mensagemErro += "PREENCHA O CAMPO ALTURA! \n";
    }

    if (estado_civil_testemunha == "") {
        mensagemErro += "PREENCHA O CAMPO ESTADO CÍVIL! \n";
    }

    if (data_emissao_bi_testemunha == "") {
        mensagemErro += "PREENCHA O CAMPO DATA DE EMISSÃO DO BILHETE DE IDENTIDADE! \n";
    }

    if (data_validade_bi_testemunha == "") {
        mensagemErro += "PREENCHA O CAMPO DATA DE VALIDADE DO BILHETE DE IDENTIDADE! \n";
    }

    if (mensagemErro != "") {
        alert(mensagemErro);
        return false;
    }
}



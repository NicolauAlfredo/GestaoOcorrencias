function postoTrabalhoValidador() {
    var mensagemErro = "";
    nome_posto_trabalho = document.getElementById("nome_posto_trabalho").value;
    numero_posto_trabalho = document.getElementById("numero_posto_trabalho").value;
    select_municipio = document.getElementById("select_municipio").value;
    
    if (nome_posto_trabalho == "") {
        mensagemErro += "PREENCHA O CAMPO NOME! \n";
    }

    if (numero_posto_trabalho == "") {
        mensagemErro += "PREENCHA O CAMPO NÚMERO! \n";
    }

    if (select_municipio == "") {
        mensagemErro += "SELECIONE O MUNICÍPIO! \n";
    }

    if (mensagemErro != "") {
        alert(mensagemErro);
        return false;
    }
}



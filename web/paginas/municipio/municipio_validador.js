function municipioValidador() {
    var mensagemErro = "";
    nome_municipio = document.getElementById("nome_municipio").value;
    provincia_municipio = document.getElementById("select_provincia").value;

    if (nome_municipio == "") {
        mensagemErro += "PREENCHA O CAMPO NOME! \n";
    }

    if (provincia_municipio == "") {
        mensagemErro += "SELECIONE A PROVÍNCIA! \n";
    }
    
    if (mensagemErro != "") {
        alert(mensagemErro);
        return false;
    }
}



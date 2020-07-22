function patenteValidador() {
    var mensagemErro = "";
    nome_patente = document.getElementById("nome_patente").value;

    if (nome_patente == "") {
        mensagemErro += "PREENCHA O CAMPO NOME! \n";
    }

    if (mensagemErro != "") {
        alert(mensagemErro);
        return false;
    }
}



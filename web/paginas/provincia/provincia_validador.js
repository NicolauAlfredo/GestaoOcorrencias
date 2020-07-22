function provinciaValidador() {
    var mensagemErro = "";
    nome_provincia = document.getElementById("nome_provincia").value;

    if (nome_autuado == "") {
        mensagemErro += "PREENCHA O CAMPO NOME! \n";
    }

    if (mensagemErro != "") {
        alert(mensagemErro);
        return false;
    }
}



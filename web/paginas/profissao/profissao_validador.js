function profissaoValidador() {
    var mensagemErro = "";
    nome_profissao = document.getElementById("nome_profissao").value;

    if (nome_profissao == "") {
        mensagemErro += "PREENCHA O CAMPO NOME! \n";
    }

    if (mensagemErro != "") {
        alert(mensagemErro);
        return false;
    }
}



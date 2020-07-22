function tipoValidador() {
    var mensagemErro = "";
    nome_tipo_ocorrencia = document.getElementById("nome_tipo_ocorrencia").value;
   

    if (nome_tipo_ocorrencia == "") {
        mensagemErro += "PREENCHA O CAMPO NOME! \n";
    }

    if (mensagemErro != "") {
        alert(mensagemErro);
        return false;
    }
}



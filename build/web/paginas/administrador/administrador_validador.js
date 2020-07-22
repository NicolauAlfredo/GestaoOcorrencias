function administradorValidador() {
    var mensagemErro = "";
    nome = document.getElementById("nome_administrador").value;
    data_nascimento = document.getElementById("data_nascimento_administrador").value;
    bi = document.getElementById("bi_administrador").value;
    nip = document.getElementById("nip_administrador").value;
    palavra_passe = document.getElementById("palavra_passe_administrador").value;


    if (nome == "") {
        mensagemErro += "PREENCHA O CAMPO NOME! \n";
    }

    if (data_nascimento == "") {
        mensagemErro += "PREECHA O CAMPO DATA DE NASCIMENTO! \n";
    }
     if (document.form_administrador.sexo_administrador[0].checked == false && document.form_administrador.sexo_administrador[1].checked == false) {
        mensagemErro += "SELECIONE O SEXO! \n";
    }
    
     if (bi == "") {
        mensagemErro += "PREENCHA O CAMPO BILHETE DE IDENTIDADE! \n";
    }

    if (nip == "") {
        mensagemErro += "PREECHA O CAMPO NIP! \n";
    }
    
    if (palavra_passe == "") {
        mensagemErro += "PREECHA O CAMPO PALAVRA-PASSE! \n";
    }

    if (mensagemErro != "") {
        alert(mensagemErro);
        return false;
    }
}



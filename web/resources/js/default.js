
function verificar(xhr, status, args, dlg) {
    if (args.validationFailed || args.erro) {
        PF(dlg).jq.effect("shake", {times: 5}, 100);
    } else {
        PF(dlg).hide();
    }
}


function cepBuscaAutomaticoNovo() {
    var logradouro = $(".logradouro-novo");
    var complemento = $(".compl-novo");
    var cidade = $(".cidade-novo");
    var uf = $(".uf-novo");

    $.ajax({
        url: "https://viacep.com.br/ws/" + $(".cep-novo").val().replace("-", "") + "/json/",
        type: "get",
        dataTypes: "json"
    }).done(function (s) {
        if (!s.hasOwnProperty("erro")) {

            PF('seleciona-estado-novo').selectValue(s.uf);
            cidade.val(s.localidade);
            complemento.val(s.complemento);
            logradouro.val(s.logradouro);

        }else{
            PF('seleciona-estado-novo').selectValue("SP");
            cidade.val("");
            complemento.val("");
            logradouro.val("");
            
        }
    });
}




function cepBuscaAutomaticoEditar() {
    var logradouro = $(".logradouro-edit");
    var complemento = $(".compl-edit");
    var cidade = $(".cidade-edit");
    var uf = $(".uf-edit");

    $.ajax({
        url: "https://viacep.com.br/ws/" + $(".cep-edit").val().replace("-", "") + "/json/",
        type: "get",
        dataTypes: "json"
    }).done(function (s) {
        if (!s.hasOwnProperty("erro")) {
            PF('seleciona-estado-edit').selectValue(s.uf);
            cidade.val(s.localidade);
            complemento.val(s.complemento);
            logradouro.val(s.logradouro);

        }else{
            PF('seleciona-estado-edit').selectValue("SP");
            cidade.val("");
            complemento.val("");
            logradouro.val("");
            
        }
    });
}
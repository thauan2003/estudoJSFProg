function maxCaracteres(campo, qtde) {
    if (campo.value.length >= qtde) {
        campo.value = campo.value.substr(0, qtde);
        return true;
    }
}

function contaCaracters(id) {
    var value = document.forms["form"]["form:" + id].value;
    var texto = document.forms["form"]["form:" + id].options[document.forms["form"]["form:" + id].selectedIndex].text;
    //var texto = frm.opt.options[frm.opt.selectedIndex].text;
    //alert(texto.length);
    var tamanho = texto.length;
    var pixel = (tamanho * 5).toFixed(0);
    if (tamanho <= 12) {
        pixel = (tamanho * 10).toFixed(0);
    }
    if (tamanho > 12) {
        pixel = (tamanho * 8).toFixed(0);
    }
    document.forms["form"]["form:" + id].style.width = pixel + "px";
}

function retornaTecla(e) {
    var tecla = e.keyCode ? e.keyCode : e.which ? e.which : e.charCode;
    return tecla;

}

function SomenteNumero(e) {
    var tecla = e.keyCode ? e.keyCode : e.which ? e.which : e.charCode;
    if ((tecla > 47 && tecla < 58) || tecla === 9) {
        return true;
    } else {
        if (tecla === 8 || tecla === 0 || tecla === 46)
            return true;
        else
            return false;
    }
}

function SomenteNumeroFF(e) {
//    var tecla = window.event ? event.keyCode : e.which;
//    if (tecla > 44 && tecla < 58 || tecla > 95 && tecla < 106 || tecla == 08) {
//        return false;
//    } else {
//        return true;
//    }
    numReg = /^[0-9]+$/;
    if (numReg.test(e.value)) {
        return true;
    } else {
        e.value = e.value.substring(0, e.value.length - 1);
    }
}


function validaCNS(vlrCNS) {
    // Formulário que contem o campo CNS
    var soma = new Number;
    var resto = new Number;
    var dv = new Number;
    var pis = new String;
    var resultado = new String;
    var tamCNS = vlrCNS.length;
    if ((tamCNS) != 15) {
        alert("Numero de CNS invalido");
        return false;
    }
    pis = vlrCNS.substring(0, 11);

    //se numero inicia com  7 8 ou 9 tem que usar esse
    if (pis.substring(0, 1) == '8' || pis.substring(0, 1) == '7' || pis.substring(0, 1) == '9') {
        var pis;
        var resto;
        var dv;
        var soma;
        var resultado;
        var result;
        result = 0;

        pis = Obj.value.substring(0, 15);

        if (pis == "")
        {
            return false
        }

        if ((Obj.value.substring(0, 1) != "7") && (Obj.value.substring(0, 1) != "8") && (Obj.value.substring(0, 1) != "9"))
        {
            alert("Atenção! Número Provisório inválido!");
            return false
        }

        soma = ((parseInt(pis.substring(0, 1), 10)) * 15)
                + ((parseInt(pis.substring(1, 2), 10)) * 14)
                + ((parseInt(pis.substring(2, 3), 10)) * 13)
                + ((parseInt(pis.substring(3, 4), 10)) * 12)
                + ((parseInt(pis.substring(4, 5), 10)) * 11)
                + ((parseInt(pis.substring(5, 6), 10)) * 10)
                + ((parseInt(pis.substring(6, 7), 10)) * 9)
                + ((parseInt(pis.substring(7, 8), 10)) * 8)
                + ((parseInt(pis.substring(8, 9), 10)) * 7)
                + ((parseInt(pis.substring(9, 10), 10)) * 6)
                + ((parseInt(pis.substring(10, 11), 10)) * 5)
                + ((parseInt(pis.substring(11, 12), 10)) * 4)
                + ((parseInt(pis.substring(12, 13), 10)) * 3)
                + ((parseInt(pis.substring(13, 14), 10)) * 2)
                + ((parseInt(pis.substring(14, 15), 10)) * 1);

        resto = soma % 11;

        if (resto == 0)
        {
            return true;
        } else
        {
            alert("Atenção! Número Provisório inválido!");
            return false;
        }
    }

    soma = (((Number(pis.substring(0, 1))) * 15) +
            ((Number(pis.substring(1, 2))) * 14) +
            ((Number(pis.substring(2, 3))) * 13) +
            ((Number(pis.substring(3, 4))) * 12) +
            ((Number(pis.substring(4, 5))) * 11) +
            ((Number(pis.substring(5, 6))) * 10) +
            ((Number(pis.substring(6, 7))) * 9) +
            ((Number(pis.substring(7, 8))) * 8) +
            ((Number(pis.substring(8, 9))) * 7) +
            ((Number(pis.substring(9, 10))) * 6) +
            ((Number(pis.substring(10, 11))) * 5));
    resto = soma % 11;
    dv = 11 - resto;


    if (dv == 11) {
        dv = 0;
    }
    if (dv == 10) {
        soma = (((Number(pis.substring(0, 1))) * 15) +
                ((Number(pis.substring(1, 2))) * 14) +
                ((Number(pis.substring(2, 3))) * 13) +
                ((Number(pis.substring(3, 4))) * 12) +
                ((Number(pis.substring(4, 5))) * 11) +
                ((Number(pis.substring(5, 6))) * 10) +
                ((Number(pis.substring(6, 7))) * 9) +
                ((Number(pis.substring(7, 8))) * 8) +
                ((Number(pis.substring(8, 9))) * 7) +
                ((Number(pis.substring(9, 10))) * 6) +
                ((Number(pis.substring(10, 11))) * 5) + 2);
        resto = soma % 11;
        dv = 11 - resto;
        resultado = pis + "001" + String(dv);
    } else {
        resultado = pis + "000" + String(dv);
    }
    if (vlrCNS != resultado) {
        alert("Numero de CNS invalido");
        return false;
    } else {
        alert("Numero de CNS válido");
        return true;
    }


}

function nomeComercial() {
    if (document.forms["form"]["form:nomeCom"].value.length === 0) {
        document.forms["form"]["form:nomeCom"].value = document.forms["form"]["form:med"].value.replaceAll("\n", " ").replaceAll("\t", " ");
    }
}
// JavaScript Document
function removeAcento(obj) {
    var str = new String(obj.value);
    var acentos = new String('àâêôûãõáéíóúçüÀÂÊÔÛÃÕÁÉÍÓÚÇÜ´`^~¨' + '%@$#&*');
    var SemAcento = new String('aaeouaoaeioucuAAEOUAOAEIOUCU');
    var c = new String();
    var i = new Number();
    var x = new Number();
    var res = '';
    for (i = 0; i < str.length; i++) {
        c = str.substring(i, i + 1);
        for (x = 0; x < acentos.length; x++) {
            if (acentos.substring(x, x + 1) == c) {
                c = SemAcento.substring(x, x + 1);
            }
        }
        res += c;
    }
    obj.value = res;
}

function removeNegativo(obj) {
    var str = new String(obj.value);
    var acentos = new String('-+*/%$&#@');
    var SemAcento = new String('');
    var c = new String();
    var i = new Number();
    var x = new Number();
    var res = '';
    for (i = 0; i < str.length; i++) {
        c = str.substring(i, i + 1);
        for (x = 0; x < acentos.length; x++) {
            if (acentos.substring(x, x + 1) == c) {
                c = SemAcento.substring(x, x + 1);
            }
        }
        res += c;
    }
    obj.value = res;
}
function removeAspas(obj) {
    var str = new String(obj.value);
    var acentos = new String('' + "'" + '""' + '%@$#&*');
    var SemAcento = new String('');
    var c = new String();
    var i = new Number();
    var x = new Number();
    var res = '';
    for (i = 0; i < str.length; i++) {
        c = str.substring(i, i + 1);
        for (x = 0; x < acentos.length; x++) {
            if (acentos.substring(x, x + 1) == c) {
                c = SemAcento.substring(x, x + 1);
            }
        }
        res += c;
    }
    obj.value = res;
}

function removeDuploEspaco(obj) {
    var str = new String(obj.value);
    var res = '';
    res = str.replace("  ", " ");
    obj.value = res;
}

function moveRelogio() {

    atualizaHoraServidor();
    if (document.forms["FormMenu"]["FormMenu:horaAtualSevidor"] !== undefined && document.forms["form"]["form:txtHoraCad"] !== undefined) {
        document.forms["form"]["form:txtHoraCad"].value = document.forms["FormMenu"]["FormMenu:horaAtualSevidor"].value;
    }
    setTimeout("moveRelogio()", 1000)
}

function moveRelogios() {

    atualizaHoraServidor();
    if (document.forms["form"]["form:horaAtualSevidor"] !== undefined && document.forms["form"]["form:txtHoraCad"] !== undefined) {
        document.forms["form"]["form:txtHoraCad"].value = document.forms["form"]["form:horaAtualSevidor"].value;
    }
    setTimeout("moveRelogios()", 1000)
}

function moveRelogioGeneric(form, id) {
    atualizaHoraServidor();
    document.forms[form][form + ":" + id].value = document.forms["FormMenu"]["FormMenu:horaAtualSevidor"].value;

    setTimeout(function () {
        moveRelogioGeneric(form, id);
    }, 1000)
}

function carregarfoto() {
    var endereco = enderecofoto.value

    foto.innerHTML = "<img src='" + endereco + "'><br><INPUT type='file' ID='enderecofoto' onchange=carregarfoto();>"
}

//              Script criado por Rodrigo dos Santos Tavares.

function reload() {

    //    window.setTimeout("history.go()", 5000);
    document.location.reload();
}


function abrirURL(URL) {
    window.open(URL, "URL", "width=210,height=250,scrollbars=YES");
}

function abrirURL1(URL) {
    window.open(URL, "URL", "width=800,height=900,scrollbars=YES");
}

function abrirURL2(URL) {
    window.open(URL, "URL", "width=1000,height=600,scrollbars=YES");
}
var pop;
function abrirURLVerifica(URL) {
    var h = screen.availHeight;
    var w = screen.availWidth;
    var porcentWidth = 80;
    var porcentHeight = 100;
    if (w <= 1024) {
        porcentWidth = porcentWidth + 25;
        if (porcentWidth > 100) {
            porcentWidth = 100;
        }
    }
    w = (porcentWidth * w) / 100;
    h = (porcentHeight * h) / 100;
    h = h - 70;
    w = w - 20;
    var LeftPosition = (screen.width) ? (screen.width - w) / 2 : 0;
    pop = window.open(URL, "URL" + Math.floor((Math.random() * 500) + 1), "left=" + LeftPosition + ", width=" + w + ",height=" + h + ",scrollbars=YES");
    pop.sessionStorage.setItem("statusPopup", "true");
    timer = setInterval('polling()', 1000);
}

function abrirURLVerificaPorcent(porcentWidth, porcentHeight, URL) {
    var h = screen.availHeight;
    var w = screen.availWidth;
    if (w <= 1024) {
        porcentWidth = porcentWidth + 25;
        if (porcentWidth > 100) {
            porcentWidth = 100;
        }
    }
    w = (porcentWidth * w) / 100;
    h = (porcentHeight * h) / 100;
    h = h - 70;
    w = w - 20;
    var LeftPosition = (screen.width) ? (screen.width - w) / 2 : 0;
    pop = window.open(URL, "URL" + Math.floor((Math.random() * 500) + 1), "left=" + LeftPosition + ",width=" + w + ",height=" + h + ",scrollbars=YES");
    pop.sessionStorage.setItem("statusPopup", "true");
    timer = setInterval('polling()', 1000);
}

function polling() {
    if (pop && pop.closed) {
        clearInterval(timer);
        window.sessionStorage.setItem("statusPopup", "false");
    }
}

var verificaMostraMenu = function () {
    for (var i = 0; i < arguments.length; i++) {
        if (sessionStorage.getItem("statusPopup") === 'true') {
            document.getElementById(arguments[i]).style.display = 'none';
        } else {
            document.getElementById(arguments[i]).style.display = 'inline';
        }
    }
}

function abrirImpressos(URL) {
    window.open(URL, "URL", "scrollbars=YES");
}
function abrirImpressosFull(URL) {
    var h = screen.availHeight;
    var w = screen.availWidth;
    h = h - 70;
    w = w - 20;
    window.open(URL, "URL", "width=" + w + ",height=" + h + ",scrollbars=YES");
}

function mascaraFinanceiro(el)
{
    var val = el.value;
    var len = val.length;
    var f = '';

    // substituir por replace( '.', '' )
    val = val.substring(0, val.indexOf('.')) + val.substring(val.indexOf('.') + 1, val.length);

    for (var i = 0; i < len; i++)
    {
        if (i == len - 3)
            f += '.';

        if (i > 0 || val.charAt(i) != '0')
            f += val.charAt(i);
    }

    el.value = f;
}

function moeda(z) {
    v = z.value;
    v = v.replace(/\D/g, "")
    v = v.replace(/[0-9]{12}/, "inválido")
    v = v.replace(/(\d{1})(\d{1,4})$/, "$1,$2")
    z.value = v;
}
function moedaPonto(z) {
    v = z.value;
    v = v.replace(/\D/g, "")
    v = v.replace(/[0-9]{12}/, "inválido")
    v = v.replace(/(\d{1})(\d{1,4})$/, "$1.$2")
    z.value = v;
}
function porcentagem(z) {
    v = z.value;
    v = v.replace(/\D/g, "")
    v = v.replace(/[0-9]{12}/, "inválido")
    v = v.replace(/(\d{1})(\d{1,1})$/, "$1,$2")
    z.value = v;
}
function moedaPonto2Casas(z) {
    v = z.value;
    v = v.replace(/\D/g, "")
    v = v.replace(/[0-9]{12}/, "inválido")
    v = v.replace(/(\d{1})(\d{1,2})$/, "$1.$2")
    z.value = v;
}
function moedaVirgula2Casas(el) {
//    v = z.value;
//    v = v.replace(/\D/g, "")
//    v = v.replace(/[0-9]{12}/, "inválido")
//    v = v.replace(/(\d{1})(\d{1,2})$/, "$1,$2")
//    z.value = v;
    var v = el.value;
    v = v.replace(/\D/g, "")  //permite digitar apenas números
    v = v.replace(/[0-9]{16}/, "inválido")   //limita pra máximo 999.999.999,99
    v = v.replace(/(\d{1})(\d{14})$/, "$1.$2")  //coloca ponto antes dos últimos 8 digitos
    v = v.replace(/(\d{1})(\d{11})$/, "$1.$2")  //coloca ponto antes dos últimos 8 digitos
    v = v.replace(/(\d{1})(\d{8})$/, "$1.$2")  //coloca ponto antes dos últimos 8 digitos;
    v = v.replace(/(\d{1})(\d{5})$/, "$1.$2")  //coloca ponto antes dos últimos 5 digitos
    v = v.replace(/(\d{1})(\d{1,2})$/, "$1,$2")	//coloca virgula antes dos últimos 2 digitos
    el.value = v;
}


function mascaraDecimal(el) {
    var val = el.value;
    var len = val.length;
    var f = '';

    // substituir por replace( '.', '' )
    val = val.substring(0, val.indexOf('.')) + val.substring(val.indexOf('.') + 1, val.length);

    for (var i = 0; i < len; i++)
    {
        if (i == len - 3)
            f += '.';

        if (i > 0 || val.charAt(i) != '0')
            f += val.charAt(i);
    }

    el.value = f;
}

function mascaraDecimal5(el) {
    var val = el.value;
    var len = val.length;
    var f = '';

    // substituir por replace( '.', '' )
    val = val.substring(0, val.indexOf('.')) + val.substring(val.indexOf('.') + 1, val.length);

    for (var i = 0; i < len; i++)
    {
        if (i == len - 3)
            f += '.';

        if (i > -1)
            f += val.charAt(i);
    }

    f = f.replace(/\D/g, ""); //permite digitar apenas números
    f = f.replace(/(\d{1})(\d{1,2})$/, "$1.$2"); //coloca virgula antes dos últimos 2 digitos
    el.value = f;
}

function mascaraDecimalVirgula(el) {
    var val = el.value;
    var len = val.length;
    var f = '';

    if ((event.keyCode >= 48 && event.keyCode <= 57) || (event.keyCode >= 96 && event.keyCode <= 105)) {
        // substituir por replace( '.', '' )
        val = val.substring(0, val.indexOf(',')) + val.substring(val.indexOf(',') + 1, val.length);

        for (var i = 0; i < len; i++)
        {
            if (i == len - 3)
                f += ',';

            if (i >= 0 || val.charAt(i) != '0')
                f += val.charAt(i);
        }

        el.value = f;
    }
}

function mascaraDecimalFF(el)
{
    var v = el.value;
    if (v.indexOf(".") > -1) {
        var x = v.split(".");
        var zeros = "000";
        if (x[1].length < 4) {
            zeros = zeros.substring(0, (4 - x[1].length));
            v = x[0] + "." + x[1] + zeros;
        }
    }
    v = v.replace(/\D/g, "")  //permite digitar apenas números
    v = v.replace(/(\d{1})(\d{4})$/, "$1.$2")  //coloca ponto antes dos últimos 5 digitos
    el.value = v;
}

function mascaraDecimal2(el) {
    //4 Casas depois da Virgula e pode começar com 0
    var val = el.value;
    var len = val.length;
    var f = '';

    if ((event.keyCode >= 48 && event.keyCode <= 57) || (event.keyCode >= 96 && event.keyCode <= 105)) {
        // substituir por replace( '.', '' )
        val = val.substring(0, val.indexOf(',')) + val.substring(val.indexOf(',') + 1, val.length);

        for (var i = 0; i < len; i++)
        {
            if (i == len - 4)
                f += ',';

            if (i > 0 || val.charAt(i) != ' ')
                f += val.charAt(i);
        }

        el.value = f;
    }
}

function mascaraDecimal3(el)
{
    var val = el.value;
    var len = val.length;
    var f = '';

    // substituir por replace( '.', '' )
    val = val.substring(0, val.indexOf('.')) + val.substring(val.indexOf('.') + 1, val.length);

    for (var i = 0; i < len; i++)
    {
        if (i == len - 5)
            f += '.';
        if (i > -1)
            f += val.charAt(i);
    }

    el.value = f;
}

function mascaraDecimal3Virgula(el)
{
    var val = el.value;
    var len = val.length;
    var f = '';

    // substituir por replace( '.', '' )
    val = val.substring(0, val.indexOf(',')) + val.substring(val.indexOf(',') + 1, val.length);

    for (var i = 0; i < len; i++)
    {
        if (i == len - 5)
            f += ',';
        if (i > -1)
            f += val.charAt(i);
    }

    el.value = f;
}

function mascaraDecimal4PontoVirgula(el)
{
    var v = el.value;
    v = v.replace(/\D/g, "")  //permite digitar apenas números
    v = v.replace(/[0-9]{12}/, "inválido")   //limita pra máximo 999.999.999,99
    v = v.replace(/(\d{1})(\d{10})$/, "$1.$2")  //coloca ponto antes dos últimos 8 digitos
    v = v.replace(/(\d{1})(\d{7})$/, "$1.$2")  //coloca ponto antes dos últimos 8 digitos
//    v = v.replace(/(\d{1})(\d{5})$/, "$1.$2")  //coloca ponto antes dos últimos 5 digitos
    v = v.replace(/(\d{1})(\d{1,4})$/, "$1,$2")	//coloca virgula antes dos últimos 2 digitos
    el.value = v;
}

function mascaraValorNotaSaida(el)
{
    var v = el.value;
    if (v.indexOf(".") > -1) {
        var x = v.split(".");
        var zeros = "0000";
        if (x[1].length < 4) {
            zeros = zeros.substring(0, (4 - x[1].length));
            v = x[0] + "." + x[1] + zeros;
        }
    }
    v = v.replace(/\D/g, "")  //permite digitar apenas números
    v = v.replace(/(\d{1})(\d{4})$/, "$1.$2")  //coloca ponto antes dos últimos 5 digitos
    el.value = v;
}

function mascaraValorNovo(el)
{
    var v = el.value;
    if (v.indexOf(".") > -1) {
        var x = v.split(".");
        var zeros = "00";
        if (x[1].length < 2) {
            zeros = zeros.substring(0, (2 - x[1].length));
            v = x[0] + "." + x[1] + zeros;
        }
    }
    v = v.replace(/\D/g, "")  //permite digitar apenas números
    v = v.replace(/(\d{1})(\d{2})$/, "$1.$2")  //coloca ponto antes dos últimos 5 digitos
    el.value = v;
}

function mascaraDecimal2Casas(el)
{
    var v = el.value;
    if (v.indexOf(",") > -1) {
        var x = v.split(",");
        var zeros = "00";
        if (x[1].length < 2) {
            zeros = zeros.substring(0, (2 - x[1].length));
            v = x[0] + "." + x[1] + zeros;
        }
    }
    v = v.replace(/\D/, "");
    v = v.replace(/^[0]+/, "");
    v = v.replace(/^(\d{1,})(\d{2})$/, "$1.$2");  //coloca ponto antes dos últimos 3 digitos
    el.value = v;
}

function mascaraValor2Casas(el)
{
    var v = el.value;
    v = v.replace(/\D/g, "")  //permite digitar apenas números
    v = v.replace(/[0-9]{12}/, "inválido")   //limita pra máximo 999.999.999,99
    v = v.replace(/(\d{1})(\d{10})$/, "$1.$2")  //coloca ponto antes dos últimos 8 digitos
    v = v.replace(/(\d{1})(\d{7})$/, "$1.$2")  //coloca ponto antes dos últimos 8 digitos
//    v = v.replace(/(\d{1})(\d{5})$/, "$1.$2")  //coloca ponto antes dos últimos 5 digitos
    v = v.replace(/(\d{1})(\d{1,2})$/, "$1,$2")	//coloca virgula antes dos últimos 2 digitos
    el.value = v;
}
function mascaraAlturaCm(el)
{
    var v = el.value;
    v = v.replace(/\D/g, "")  //permite digitar apenas números
    v = v.replace(/(\d{2})(\d{1,2})$/, "$1,$2")	//coloca virgula antes dos últimos 2 digitos
    el.value = v;
}
function mascaraPeso2Casas(el)
{
    /*Guilherme 01/12/2015*/
    var v = el.value;
    v = v.replace(/\D/g, "")  //permite digitar apenas números
    v = v.replace(/(\d{1})(\d{1,2})$/, "$1,$2")	//coloca virgula antes dos últimos 2 digitos
    el.value = v;
}

function mascaraPeso3Casas2(el)
{
    /*Guilherme 01/12/2015*/
    var v = el.value;
    v = v.replace(/\D/g, "")  //permite digitar apenas números
    v = v.replace(/(\d{1})(\d{1,3})$/, "$1,$2")	//coloca virgula antes dos últimos 2 digitos
    el.value = v;
}

function mascaraPeso3Casas(el)
{
    var v = el.value;
    if (v.indexOf(",") > -1) {
        var x = v.split(",");
        var zeros = "000";
        if (x[1].length < 3) {
            zeros = zeros.substring(0, (3 - x[1].length));
            v = x[0] + "," + x[1] + zeros;
        }
    }
    v = v.replace(/\D/, "");
    v = v.replace(/^[0]+/, "");
    v = v.replace(/^(\d{1,})(\d{3})$/, "$1,$2");  //coloca ponto antes dos últimos 3 digitos
    el.value = v;
}

function mascaraLitros3Casas(el)
{
    var v = el.value;
    if (v.indexOf(".") > -1) {
        var x = v.split(".");
        var zeros = "000";
        if (x[1].length < 3) {
            zeros = zeros.substring(0, (3 - x[1].length));
            v = x[0] + "." + x[1] + zeros;
        }
    }
    v = v.replace(/\D/, "");
    v = v.replace(/^[0]+/, "");
    v = v.replace(/^(\d{1,})(\d{3})$/, "$1.$2");  //coloca ponto antes dos últimos 3 digitos
    el.value = v;
}

function mascaraDecimal4(el) {
    //4 Casas depois da Virgula e pode começar com 0
    var val = el.value;
    var len = val.length;
    var f = '';

    if ((event.keyCode >= 48 && event.keyCode <= 57) || (event.keyCode >= 96 && event.keyCode <= 105) || (event.keyCode === 8)) {
        // substituir por replace( '.', '' )
        val = val.substring(0, val.indexOf('.')) + val.substring(val.indexOf('.') + 1, val.length);

        for (var i = 0; i < len; i++)
        {
            if (i == len - 3)
                f += '.';

            if (i > 0 || val.charAt(i) != '0')
                f += val.charAt(i);
        }

        el.value = f;
    }
}
//***************************************************************
function Janela() {


    win1 = new Window('1', {
        className: "alphacube",
        title: "Sample1",
        width: 200,
        height: 150,
        top: 70,
        left: 100
    });
    win1.getContent().innerHTML = "<h1>1</h1>";

}

//***************************************************************


//****************************************************************
function msg2() {
    "Teste entao";
}

function mensagem(msg)
{
    window.alert(msg);
}
function foco(campo)
{
    campo.focus();
}
function ultimaAlteracao()
{
    document.write(document.lastModified);
}
function maiuscula(campo)
{
    var value;
    campo.value = campo.value.toUpperCase();
    value = campo.value;
    return value;
}
function minuscula(campo)
{
    return campo.toLowerCase();
}

function Tecla(e) {
    if (document.all) // Internet Explorer
        var tecla = event.keyCode;
    else if (document.layers) // Nestcape
        var tecla = e.which;
    //if (tecla > 47 && tecla < 58) // numeros de 0 a 9
    if (tecla < 47 || tecla > 58) // letras de A a Z
        return true;
    else {
        if (tecla != 8) // backspace
            event.keyCode = 0;//return false;
        else
            return true;
        alert("Caracteres Invalidos");
    }
}

function validarCampo() {
    var temp
    if (document.form.txtNome.value == "") {
        alert("Campo deve ser preenchido !")
        return false
    }
    return true
}

//*** VALIDA��ES ******************************//
// JavaScript Document

//adiciona mascara de cnpj
function MascaraCNPJ(cnpj) {
    if (mascaraInteiro(cnpj) == false) {
        event.returnValue = false;
    }
    return formataCampo(cnpj, '00.000.000/0000-00', event);
}

function MascaraPlaca(placa) {
    placa.value = placa.value.toUpperCase();
    return formataCampo(placa, '000-0000', event);
}

//adiciona mascara de cep
function MascaraCep(cep) {
    if (mascaraInteiro(cep) == false) {
        event.returnValue = false;
    }
    return formataCampo(cep, '00.000-000', event);
}
//adiciona mascara de certidao nasc. nova
function MascaraCertidaoNova(num) {
    if (SomenteNumero(event) === false) {
        return false;
    } else {
        return formataCampo(num, '000000-00-00-0000-0-00000-000-0000000-00', event);
    }
}

//adiciona mascara de Rg
function MascaraRg(rg) {
    if (mascaraInteiro(rg) == false) {
        event.returnValue = false;
    }
    return formataCampo(rg, '00.000.000-0', event);
}
// teste do Guilherme mascara FF
function txtBoxFormat(objForm, strField, sMask, evtKeyPress) {
    var i, nCount, sValue, fldLen, mskLen, bolMask, sCod, nTecla;

    if (document.all) { // Internet Explorer
        nTecla = evtKeyPress.keyCode;
    } else if (document.layers) { // Nestcape
        nTecla = evtKeyPress.which;
    } else if (document.getElementById) { // FireFox
        nTecla = evtKeyPress.which;
    }
    sValue = document.forms["form"]["form:" + strField].value;


    sValue = sValue.toString().replace("-", "");
    sValue = sValue.toString().replace("-", "");
    sValue = sValue.toString().replace(".", "");
    sValue = sValue.toString().replace(".", "");
    sValue = sValue.toString().replace("/", "");
    sValue = sValue.toString().replace("/", "");
    sValue = sValue.toString().replace("(", "");
    sValue = sValue.toString().replace("(", "");
    sValue = sValue.toString().replace(")", "");
    sValue = sValue.toString().replace(")", "");
    sValue = sValue.toString().replace(" ", "");
    sValue = sValue.toString().replace(" ", "");
    fldLen = sValue.length;
    mskLen = sMask.length;

    i = 0;
    nCount = 0;
    sCod = "";
    mskLen = fldLen;

    while (i <= mskLen) {
        bolMask = ((sMask.charAt(i) == "-") || (sMask.charAt(i) == ".") || (sMask.charAt(i) == "/"))
        bolMask = bolMask || ((sMask.charAt(i) == "(") || (sMask.charAt(i) == ")") || (sMask.charAt(i) == " "))

        if (bolMask) {
            sCod += sMask.charAt(i);
            mskLen++;
        } else {
            sCod += sValue.charAt(nCount);
            nCount++;
        }

        i++;
    }

//    objForm[strField].value = sCod;
    document.forms["form"]["form:" + strField].value = sCod;

    if (nTecla != 8) { // backspace
        if (sMask.charAt(i - 1) == "9") {
            return ((nTecla > 47) && (nTecla < 58));
        } else { // qualquer caracter...
            return true;
        }
    } else {
        return true;
    }
}

//adiciona mascara de data
function MascaraData(data) {
    if (mascaraInteiro(data) == false) {
        event.returnValue = false;
    }
    var date = data.value;
    if (date.length > 10) {
        var retorno = date.substring(0, 10);
        data.value = retorno;
        return true;
    }
    return formataCampo(data, '00/00/0000', event);
}


function mascaraDataFF(campoData) {
    var data = campoData.value;
    if (data.length == 2) {
        data = data + '/';
        campoData.value = data;
        return true;
    }
    if (data.length == 5) {
        data = data + '/';
        campoData.value = data;
        return true;
    }
    if (data.length >= 10) {
        campoData.value = campoData.value.substr(0, 10);
        return true;
    }
}

function mascaraHoraFF(campoHora) {
    var hora = campoHora.value;
    if (hora.length == 2) {
        hora = hora + ':';
        campoHora.value = hora;
        return true;
    }
    if (hora.length == 5) {
        hora = hora + ':';
        campoHora.value = hora;
        return true;
    }
    if (hora.length >= 8) {
        campoHora.value = campoHora.value.substr(0, 8);
        return true;
    }
}

//adiciona mascara de hora
function MascaraHora(hora) {
    if (mascaraInteiro(hora) == false) {
        event.returnValue = false;
    }
    return formataCampo(hora, '00:00:00', event);
}

//adiciona mascara ao telefone
function MascaraTelefone(tel) {
    if (mascaraInteiro(tel) == false) {
        event.returnValue = false;
    }
    return formataCampo(tel, '(00) 0000-0000', event);
}

//adiciona mascara ao CPF
function MascaraCPF(cpf) {
    if (mascaraInteiro(cpf) == false) {
        event.returnValue = false;
    }
    return formataCampo(cpf, '000.000.000-00', event);
}

//valida telefone
function ValidaTelefone(tel) {
    exp = /\(\d{2}\)\ \d{4}\-\d{4}/
    if (!exp.test(tel.value))
        alert('Numero de Telefone Invalido!');
}

//valida CEP
function ValidaCep(cep) {
    exp = /\d{2}\.\d{3}\-\d{3}/
    if (!exp.test(cep.value))
        alert('Numero de Cep Invalido!');
}

//valida data
function ValidaData(data) {
    exp = /\d{2}\/\d{2}\/\d{4}/
    if (!exp.test(data.value))
        alert('Data Invalida!');
}

//valida data2
function ValidaData2(campo) {
    var expReg = /^(([0-2]\d|[3][0-1])\/([0]\d|[1][0-2])\/[1-2][0-9]\d{2})$/;
    var msgErro = 'Formato invalido de data.';
    if ((campo.value.match(expReg)) && (campo.value != '')) {
        var dia = campo.value.substring(0, 2);
        var mes = campo.value.substring(3, 5);
        var ano = campo.value.substring(6, 10);
        if ((mes == 4 || mes == 6 || mes == 9 || mes == 11) && (dia > 30)) {
            alert("Dia incorreto !!! O mes especificado contem no maximo 30 dias.");
            return false;
        } else {
            if (ano % 4 != 0 && mes == 2 && dia > 28) {
                alert("Data incorreta!! O mes especificado contem no maximo 28 dias.");
                return false;
            } else {
                if (ano % 4 == 0 && mes == 2 && dia > 29) {
                    alert("Data incorreta!! O mes especificado contem no maximo 29 dias.");
                    return false;
                } else {
                    //alert ("Data correta!");
                    return true;
                }
            }
        }
    } else {
        alert(msgErro);
        //campo.focus();
        return false;
    }
}


function remove(str, sub) {
    i = str.indexOf(sub);
    r = "";
    if (i == -1)
        return str;
    r += str.substring(0, i) + remove(str.substring(i + sub.length), sub);
    return r;
}

//valida o CPF digitado
function ValidarCPF(Objcpf) {
    var cpf = Objcpf.value;
    var filtro = /^\d{3}.\d{3}.\d{3}-\d{2}$/i;
    if (!filtro.test(cpf)) {
        window.alert("CPF inválido. Tente novamente.");
        return false;
    }
    cpf = remove(cpf, ".");
    cpf = remove(cpf, "-");
    if (cpf.length != 11 || cpf == "00000000000" || cpf == "11111111111" ||
            cpf == "22222222222" || cpf == "33333333333" || cpf == "44444444444" ||
            cpf == "55555555555" || cpf == "66666666666" || cpf == "77777777777" ||
            cpf == "88888888888" || cpf == "99999999999") {
        window.alert("CPF inválido. Tente novamente.");
        return false;
    }

    soma = 0;
    for (i = 0; i < 9; i++)
        soma += parseInt(cpf.charAt(i)) * (10 - i);
    resto = 11 - (soma % 11);
    if (resto == 10 || resto == 11)
        resto = 0;
    if (resto != parseInt(cpf.charAt(9))) {
        window.alert("CPF inválido. Tente novamente.");
        return false;
    }
    soma = 0;
    for (i = 0; i < 10; i ++)
        soma += parseInt(cpf.charAt(i)) * (11 - i);
    resto = 11 - (soma % 11);
    if (resto == 10 || resto == 11)
        resto = 0;
    if (resto != parseInt(cpf.charAt(10))) {
        window.alert("CPF inválido. Tente novamente.");
        return false;
    }
    return true;
}

//valida numero inteiro com mascara
function mascaraInteiro() {
    var Digitato = event.keyCode ? event.keyCode : event.which ? event.which : event.charCode;
//    alert(Digitado);
    if (Digitato < 48 || Digitato > 57) {
        event.returnValue = false;
        return false;
    }
    return true;
}

//valida o CNPJ digitado
function ValidarCNPJ(ObjCnpj) {
    var cnpj = ObjCnpj.value;
    var valida = new Array(6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2);
    var dig1 = new Number;
    var dig2 = new Number;

    exp = /\.|\-|\//g
    cnpj = cnpj.toString().replace(exp, "");
    var digito = new Number(eval(cnpj.charAt(12) + cnpj.charAt(13)));

    for (i = 0; i < valida.length; i++) {
        dig1 += (i > 0 ? (cnpj.charAt(i - 1) * valida[i]) : 0);
        dig2 += cnpj.charAt(i) * valida[i];
    }
    dig1 = (((dig1 % 11) < 2) ? 0 : (11 - (dig1 % 11)));
    dig2 = (((dig2 % 11) < 2) ? 0 : (11 - (dig2 % 11)));

    if (((dig1 * 10) + dig2) != digito)
        alert('CNPJ Invalido!');

}

//formata de forma generica os campos
function formataCampo(campo, Mascara, evento) {
    var boleanoMascara;

//    var Digitato = evento.keyCode;
    var Digitato = evento.keyCode ? evento.keyCode : evento.which ? evento.which : evento.charCode;
    exp = /\-|\.|\:|\/|\(|\)| /g
    campoSoNumeros = campo.value.toString().replace(exp, "");

    var posicaoCampo = 0;
    var NovoValorCampo = "";
    var TamanhoMascara = campoSoNumeros.length;
    ;

    if (Digitato !== 8) { // backspace
        for (i = 0; i <= TamanhoMascara; i++) {
            boleanoMascara = ((Mascara.charAt(i) == "-") || (Mascara.charAt(i) == ".")
                    || (Mascara.charAt(i) == "/") || (Mascara.charAt(i) == ":"))
            boleanoMascara = boleanoMascara || ((Mascara.charAt(i) == "(")
                    || (Mascara.charAt(i) == ")") || (Mascara.charAt(i) == " "))
            if (boleanoMascara) {
                NovoValorCampo += Mascara.charAt(i);
                TamanhoMascara++;
            } else {
                NovoValorCampo += campoSoNumeros.charAt(posicaoCampo);
                posicaoCampo++;
            }
        }
        if (Mascara.length >= 40) {
            NovoValorCampo = NovoValorCampo.substr(0, 39);
        }
        campo.value = NovoValorCampo;
        return true;
    } else {
        return true;
    }
}

// Formata os campos  valores R$
function MascaraMoeda(objTextBox, SeparadorMilesimo, SeparadorDecimal, e) {
    var sep = 0;
    var key = '';
    var i = j = 0;
    var len = len2 = 0;
    var strCheck = '0123456789';
    var aux = aux2 = '';
    var whichCode = (window.Event) ? e.which : e.keyCode;
    if (whichCode == 13)
        return true;
    key = String.fromCharCode(whichCode); // Valor para o c�digo da Chave
    if (strCheck.indexOf(key) == -1)
        return false; // Chave inv�lida
    len = objTextBox.value.length;
    for (i = 0; i < len; i++)
        if ((objTextBox.value.charAt(i) != '0') && (objTextBox.value.charAt(i) != SeparadorDecimal))
            break;
    aux = '';
    for (; i < len; i++)
        if (strCheck.indexOf(objTextBox.value.charAt(i)) != -1)
            aux += objTextBox.value.charAt(i);
    aux += key;
    len = aux.length;
    if (len == 0)
        objTextBox.value = '';
    if (len == 1)
        objTextBox.value = '0' + SeparadorDecimal + '0' + aux;
    if (len == 2)
        objTextBox.value = '0' + SeparadorDecimal + aux;
    if (len > 2) {
        aux2 = '';
        for (j = 0, i = len - 3; i >= 0; i--) {
            if (j == 3) {
                aux2 += SeparadorMilesimo;
                j = 0;
            }
            aux2 += aux.charAt(i);
            j++;
        }
        objTextBox.value = '';
        len2 = aux2.length;
        for (i = len2 - 1; i >= 0; i--)
            objTextBox.value += aux2.charAt(i);
        objTextBox.value += SeparadorDecimal + aux.substr(len - 2, len);
    }
    return false;
}


//******************** FIM DE VALIDA��ES ************************//

function Limpar(valor, validos) {
    // retira caracteres invalidos da string
    var result = "";
    var aux;
    for (var i = 0; i < valor.length; i++) {
        aux = validos.indexOf(valor.substring(i, i + 1));
        if (aux >= 0) {
            result += aux;
        }
    }
    return result;
}

//Formata n�mero tipo moeda usando o evento onKeyDown

function Formata(campo, tammax, teclapres, decimal) {
    var tecla = teclapres.keyCode;
    vr = Limpar(campo.value, "0123456789");
    tam = vr.length;
    dec = decimal

    if (tam < tammax && tecla != 8) {
        tam = vr.length + 1;
    }

    if (tecla == 8)
    {
        tam = tam - 1;
    }

    if (tecla == 8 || tecla >= 48 && tecla <= 57 || tecla >= 96 && tecla <= 105)
    {

        if (tam <= dec)
        {
            campo.value = vr;
        }

        if ((tam > dec) && (tam <= 5)) {
            campo.value = vr.substr(0, tam - 2) + "." + vr.substr(tam - dec, tam);
        }
        if ((tam >= 6) && (tam <= 8)) {
            campo.value = vr.substr(0, tam - 5) + "." + vr.substr(tam - 5, 3) + "." + vr.substr(tam - dec, tam);
        }
        if ((tam >= 9) && (tam <= 11)) {
            campo.value = vr.substr(0, tam - 8) + "." + vr.substr(tam - 8, 3) + "." + vr.substr(tam - 5, 3) + "." + vr.substr(tam - dec, tam);
        }
        if ((tam >= 12) && (tam <= 14)) {
            campo.value = vr.substr(0, tam - 11) + "." + vr.substr(tam - 11, 3) + "." + vr.substr(tam - 8, 3) + "." + vr.substr(tam - 5, 3) + "." + vr.substr(tam - dec, tam);
        }
        if ((tam >= 15) && (tam <= 17)) {
            campo.value = vr.substr(0, tam - 14) + "." + vr.substr(tam - 14, 3) + "." + vr.substr(tam - 11, 3) + "." + vr.substr(tam - 8, 3) + "." + vr.substr(tam - 5, 3) + "." + vr.substr(tam - 2, tam);
        }
    }

}


function verificaInteiroComVirgula() {
    if (event.keyCode != 44) {
        if (event.keyCode < 48 || event.keyCode > 57) {
            event.keyCode = 0;
            return false;
        }
    }
    return true;
}

function verificaInteiro() {
    var tecla = window.event ? event.keyCode : e.which;
    if (tecla < 48 || tecla > 57) {
        event.keyCode = 0;
        return false;
    }
    return true;
}

function verificaInteiro2(event) {
    var tecla = event.keyCode ? event.keyCode : event.which ? event.which : event.charCode;
//    alert(tecla);
    if (tecla < 48 || tecla > 57) {
        event.keyCode = 0;
        return false;
    }
    return true;
}

function verifica1e2(e) {
    var tecla = window.event ? event.keyCode : e.which;
    if (tecla !== 49 && tecla !== 50) {
        event.keyCode = 0;
        return false;
    } else {
        e.value = "";
        e.value = e.value;
        return true;
    }
}

function click() {
    if (event.button == 2 || event.button == 3) {
        oncontextmenu = 'return false';
    }
    document.onmousedown = click
    document.oncontextmenu = new Function("return false;")
}

var popup = null;
var intervalo = null;
function imprimirIText(args, rel) {
    var URL = "/Apollo/" + rel;
    if (args.validationFailed) {
        PF('mpProc').hide();
    } else {
        PF('mpProc').hide();
        intervalo = window.setInterval(fechaMpProc, 500);
        if (popup === null || popup.closed) {
            popup = window.open(URL, "URL", "scrollbars=YES");
        } else {
            popup.close();
            popup = window.open(URL, "URL", "scrollbars=YES");
        }
        PF('mpProc').show();
    }
}

function imprimirITextPorcent(args, porcentWidth, porcentHeight, rel) {
    var URL = "/Apollo/" + rel;
    if (args.validationFailed) {
        PF('mpProc').hide();
    } else {
        PF('mpProc').hide();
        intervalo = window.setInterval(fechaMpProc, 500);

        var h = screen.availHeight;
        var w = screen.availWidth;
        if (w <= 1024) {
            porcentWidth = porcentWidth + 25;
            if (porcentWidth > 100) {
                porcentWidth = 100;
            }
        }
        w = (porcentWidth * w) / 100;
        h = (porcentHeight * h) / 100;
        h = h - 70;
        w = w - 20;
        var LeftPosition = (screen.width) ? (screen.width - w) / 2 : 0;

        if (popup === null || popup.closed) {
            popup = window.open(URL, "URL", "left=" + LeftPosition + ",width=" + w + ",height=" + h + ",scrollbars=YES");
        } else {
            popup.close();
            popup = window.open(URL, "URL", "left=" + LeftPosition + ",width=" + w + ",height=" + h + ",scrollbars=YES");
        }
        PF('mpProc').show();
    }
}

function imprimirITextFull(args, rel) {
    var URL = "/Apollo/" + rel;
    var h = screen.availHeight;
    var w = screen.availWidth;
    h = h - 70;
    w = w - 20;
    if (args.validationFailed) {
        PF('mpProc').hide();
    } else {
        PF('mpProc').hide();
        intervalo = window.setInterval(fechaMpProc, 500);
        if (popup === null || popup.closed) {
            popup = window.open(URL, "URL_text", "width=" + w + ",height=" + h + ",scrollbars=YES");
        } else {
            popup.close();
            popup = window.open(URL, "URL_text", "width=" + w + ",height=" + h + ",scrollbars=YES");
        }
        PF('mpProc').show();
    }
}

function abrePopUpCenter(porcentWidth, porcentHeight, URL) {
    // Ex: porcentWidth = 70; equivale a um popup com 70% do tamanho(horizontal) da tela;
    // Ex: porcentHeight = 100; equivale a um popup com 100% do tamanho(vertical) da tela;
    var nameWindow = new Date().getTime()
    intervalo = window.setInterval(fechaMpProc, 500);
    var h = screen.availHeight;
    var w = screen.availWidth;
    if (w <= 1024) {
        porcentWidth = porcentWidth + 25;
        if (porcentWidth > 100) {
            porcentWidth = 100;
        }
    }
    w = (porcentWidth * w) / 100;
    h = (porcentHeight * h) / 100;
    h = h - 70;
    w = w - 20;
    var LeftPosition = (screen.width) ? (screen.width - w) / 2 : 0;
//    var TopPosition = (screen.height) ? (screen.height - h) / 2 : 0;
    if (popup === null || popup.closed) {
        popup = window.open(URL, nameWindow, 'left=' + LeftPosition + ',width=' + w + ',height=' + h + ',scrollbars=YES, menubar=YES');
    } else {
        popup.close();
        popup = window.open(URL, nameWindow, 'left=' + LeftPosition + ',width=' + w + ',height=' + h + ',scrollbars=YES, menubar=YES');
    }
    PF('mpProc').show();
}

function abrePopUpComReturn(porcentWidth, porcentHeight, funcao, URL) {
    // Guilherme 29-09-2017
    // Serve para chamar uma funçao ao fechar o popup, assim como o "dialogReturn" do primefaces.
    // A funcao não pode ter os "()". Ex: abrePopUpComReturn(70,100, chamaFuncao, '#{utilFaces.contexto}/manipulacaoReceita/receitaNew.xhtml')
    var nameWindow = new Date().getTime();
    intervalo = window.setInterval("fechaMpProcComReturn(" + funcao + ")", 500);
    var h = screen.availHeight;
    var w = screen.availWidth;
    if (w <= 1024) {
        porcentWidth = porcentWidth + 25;
        if (porcentWidth > 100) {
            porcentWidth = 100;
        }
    }
    w = (porcentWidth * w) / 100;
    h = (porcentHeight * h) / 100;
    h = h - 70;
    w = w - 20;
    var LeftPosition = (screen.width) ? (screen.width - w) / 2 : 0;
//    var TopPosition = (screen.height) ? (screen.height - h) / 2 : 0;
    if (popup === null || popup.closed) {
        popup = window.open(URL, nameWindow, 'left=' + LeftPosition + ',width=' + w + ',height=' + h + ',scrollbars=YES, menubar=YES');
    } else {
        popup.close();
        popup = window.open(URL, nameWindow, 'left=' + LeftPosition + ',width=' + w + ',height=' + h + ',scrollbars=YES, menubar=YES');
    }
    PF('mpProc').show();
}

function abrePopUpCenter2(porcentWidth, porcentHeight, URL) {
    // A diferença deste para o abrePopUpCenter, é que este não fecha automatico o mpProc.
    // Ex: porcentWidth = 70; equivale a um popup com 70% do tamanho(horizontal) da tela;
    // Ex: porcentHeight = 100; equivale a um popup com 100% do tamanho(vertical) da tela;
    var h = screen.availHeight;
    var w = screen.availWidth;
    if (w <= 1024) {
        porcentWidth = porcentWidth + 25;
        if (porcentWidth > 100) {
            porcentWidth = 100;
        }
    }
    w = (porcentWidth * w) / 100;
    h = (porcentHeight * h) / 100;
    h = h - 70;
    w = w - 20;
    var LeftPosition = (screen.width) ? (screen.width - w) / 2 : 0;
//    var TopPosition = (screen.height) ? (screen.height - h) / 2 : 0;
    if (popup === null || popup.closed) {
        popup = window.open(URL, 'URLPopUpCenter', 'left=' + LeftPosition + ',width=' + w + ',height=' + h + ',scrollbars=YES');
    } else {
        popup.close();
        popup = window.open(URL, 'URLPopUpCenter', 'left=' + LeftPosition + ',width=' + w + ',height=' + h + ',scrollbars=YES');
    }
    PF('mpProc').show();
}

function fechaMpProc() {
    if (popup.closed) {
        clearInterval(intervalo);
        PF('mpProc').hide();
    }
}
function fechaMpProcComReturn(funcao) {
    if (popup.closed) {
        clearInterval(intervalo);
        PF('mpProc').hide();
        if (typeof funcao === 'function') {
            funcao();
        }
    }
}

function showModal(id) {
    window.location.href = "#" + id;
}
function hideModal(id) {
    window.location.href = "#close";
}

function modalClose() {
    if (location.hash == '#openModal') {
        location.hash = '';
    }
}

document.addEventListener('keyup', function (e) {
    var tecla = e.keyCode ? e.keyCode : e.which ? e.which : e.charCode;
    if (tecla === 27) {
        hideModal(null);
    }
});

document.addEventListener('click', function (e) {
    e.stopPropagation();
}, false);

/**
 * Adiciona a todos os inputs da tela o evento blur, que executa a função replaceValueInput que tira o caracter ENTER e TAB.
 * Colocar o arquivo funcoes.js na tela de acordo com o script abaixo, para que funcione tanto no chrome quanto no firefox.
 * 
 * <script defer="true" type="text/javascript" src="#{utilFaces.contexto}/javax.faces.resource/funcoes.js?ln=js"></script>
 * @returns {undefined}
 */
window.onload = function () {
    var i;
    for (i = 0; i < document.querySelectorAll("input[type='text']").length; i++) {
        document.querySelectorAll("input[type='text']")[i].addEventListener("blur", function (event) {
            event.target.value = event.target.value.replaceAll("\n", " ").replaceAll("\t", " ");
        });
    }
};

String.prototype.replaceAll = function (de, para) {
    var str = this;
    var pos = str.indexOf(de);
    while (pos > -1) {
        str = str.replace(de, para);
        pos = str.indexOf(de);
    }
    return (str);
};
//-----------------------------------------------------
//Funcao: MascaraMoeda
//Sinopse: Mascara de preenchimento de moeda
//Parametro:
//   objTextBox : Objeto (TextBox)
//   SeparadorMilesimo : Caracter separador de milésimos
//   SeparadorDecimal : Caracter separador de decimais
//   e : Evento
//   maxlength : Limite de digitos
//Retorno: Booleano
//Autor: Gabriel Fróes - www.codigofonte.com.br
//-----------------------------------------------------
function MascaraMoeda2(objTextBox, SeparadorMilesimo, SeparadorDecimal, e, maxlength) {
    var sep = 0;
    var key = '';
    var i = j = 0;
    var len = len2 = 0;
    var strCheck = '0123456789';
    var aux = aux2 = '';
    var whichCode = (window.Event) ? e.which : e.keyCode;
    console.log(whichCode);
    if (whichCode == 13 || whichCode == 8 || whichCode == 0)
        return true;
    key = String.fromCharCode(whichCode); // Valor para o código da Chave
    if (strCheck.indexOf(key) == -1)
        return false; // Chave inválida

    // maxlength
    if (objTextBox.value.replaceAll(",", "").replaceAll(".", "").length >= maxlength)
        return false;

    len = objTextBox.value.length;
    for (i = 0; i < len; i++)
        if ((objTextBox.value.charAt(i) != '0') && (objTextBox.value.charAt(i) != SeparadorDecimal))
            break;
    aux = '';
    for (; i < len; i++)
        if (strCheck.indexOf(objTextBox.value.charAt(i)) != -1)
            aux += objTextBox.value.charAt(i);
    aux += key;
    len = aux.length;
    if (len == 0)
        objTextBox.value = '';
    if (len == 1)
        objTextBox.value = '0' + SeparadorDecimal + '0' + aux;
    if (len == 2)
        objTextBox.value = '0' + SeparadorDecimal + aux;
    if (len > 2) {
        aux2 = '';
        for (j = 0, i = len - 3; i >= 0; i--) {
            if (j == 3) {
                aux2 += SeparadorMilesimo;
                j = 0;
            }
            aux2 += aux.charAt(i);
            j++;
        }
        objTextBox.value = '';
        len2 = aux2.length;
        for (i = len2 - 1; i >= 0; i--)
            objTextBox.value += aux2.charAt(i);
        objTextBox.value += SeparadorDecimal + aux.substr(len - 2, len);
    }
    return false;
}

//-----------------------------------------------------
//Funcao: MascaraMoeda
//Sinopse: Mascara de preenchimento de moeda
//Parametro:
//   objTextBox : Objeto (TextBox)
//   SeparadorMilesimo : Caracter separador de milésimos
//   SeparadorDecimal : Caracter separador de decimais
//   e : Evento
//   maxlength : Limite de digitos
//Retorno: Booleano
//Autor: Gabriel Fróes - www.codigofonte.com.br
//-----------------------------------------------------
function MascaraMoeda3(objTextBox, SeparadorMilesimo, SeparadorDecimal, e, maxlength) {
    var sep = 0;
    var key = '';
    var i = j = 0;
    var len = len2 = 0;
    var strCheck = '0123456789';
    var aux = aux2 = '';
    var whichCode = (window.Event) ? e.which : e.keyCode;
    console.log(whichCode);
    if (whichCode == 13 || whichCode == 0 || whichCode == 8)
        return true;
    key = String.fromCharCode(whichCode); // Valor para o código da Chave
    if (strCheck.indexOf(key) == -1)
        return false; // Chave inválida

    // maxlength
    if (objTextBox.value.replaceAll(",", "").replaceAll(".", "").length >= maxlength)
        return false;

    len = objTextBox.value.length;
    for (i = 0; i < len; i++)
        if ((objTextBox.value.charAt(i) != '0') && (objTextBox.value.charAt(i) != SeparadorDecimal))
            break;
    aux = '';
    for (; i < len; i++)
        if (strCheck.indexOf(objTextBox.value.charAt(i)) != -1)
            aux += objTextBox.value.charAt(i);
    aux += key;
    len = aux.length;
    if (len == 0)
        objTextBox.value = '';
    if (len == 1)
        objTextBox.value = '0' + SeparadorDecimal + '0' + aux;
    if (len == 2)
        objTextBox.value = '0' + SeparadorDecimal + aux;
    if (len == 3)
        objTextBox.value = '0' + SeparadorDecimal + aux;
    if (len > 3) {
        aux2 = '';
        for (j = 0, i = len - 4; i >= 0; i--) {
            if (j == 4) {
                aux2 += SeparadorMilesimo;
                j = 0;
            }
            aux2 += aux.charAt(i);
            j++;
        }
        objTextBox.value = '';
        len2 = aux2.length;
        for (i = len2 - 1; i >= 0; i--)
            objTextBox.value += aux2.charAt(i);
        objTextBox.value += SeparadorDecimal + aux.substr(len - 3, len);
    }
    return false;
}
function maxlength(obj, maxlength, counter) {
    var conteudo = obj.value;
    if (conteudo.length >= maxlength) {
        obj.value = conteudo.substr(0, maxlength);
    }

    var length = obj.value.length;
    document.getElementById(counter).value = (length + " de " + maxlength);
}

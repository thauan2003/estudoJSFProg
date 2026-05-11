/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*Guilherme 11-10-2017*/


function formulas(idade) {
    var estatura1 = document.forms["form"]["form:estatura1"].value;
    var pesoAtual = document.forms["form"]["form:pesoAtual"].value;
    var sexo = document.forms["form"]["form:sexo"].value;
//            var idade = #{avaliacaoNutricionistaFaces.avaliacaoNutricionistaSelected.avaFicCodigo.ficPacCodigo.idade};
    document.forms["form"]["form:estatura2"].value = (estatura1 * estatura1).toFixed(2);
    document.forms["form"]["form:pesoIdeal"].value = ((estatura1 * estatura1) * 24.9).toFixed(3);
    if (estatura1 !== "") {
        document.forms["form"]["form:imc"].value = (pesoAtual.replace(",", ".") / (estatura1 * estatura1)).toFixed(2);
    }
    var imc = document.forms["form"]["form:imc"].value;
    if (imc !== "") {
        if (idade < 60) {
            if (imc <= 18.4) {
                document.forms["form"]["form:dxImc"].value = "BAIXO PESO";
            } else if (imc >= 18.5 && imc <= 24.9) {
                document.forms["form"]["form:dxImc"].value = "EUTÓFICO";
            } else if (imc >= 25 && imc <= 29.9) {
                document.forms["form"]["form:dxImc"].value = "SOBREPESO";
            } else if (imc >= 30 && imc <= 34.9) {
                document.forms["form"]["form:dxImc"].value = "OBESIDADE I";
            } else if (imc >= 35 && imc <= 39.9) {
                document.forms["form"]["form:dxImc"].value = "OBESIDADE II";
            } else if (imc >= 40) {
                document.forms["form"]["form:dxImc"].value = "OBESIDADE III";
            }
        } else {
            if (imc < 22) {
                document.forms["form"]["form:dxImc"].value = "BAIXO PESO";
            } else if (imc >= 22 && imc < 27) {
                document.forms["form"]["form:dxImc"].value = "PESO ADEQUADO";
            } else if (imc > 27) {
                document.forms["form"]["form:dxImc"].value = "SOBREPESO";
            }
        }
    }
    var pesoIdeal = document.forms["form"]["form:pesoIdeal"].value;
    if (pesoIdeal !== "") {
        if (estatura1 !== "") {
            if (sexo === "M") {
                document.forms["form"]["form:vct"].value = ((9.53 * idade) + (15.91 * pesoIdeal.replace(",", ".")) + (539.6 * estatura1) - 662).toFixed(2);
            }
            if (sexo === "F") {
                document.forms["form"]["form:vct"].value = ((6.91 * idade) + (9.36 * pesoIdeal.replace(",", ".")) + (726 * estatura1) - 354).toFixed(2);
            }
        }
    }
}


function cintura() {
    var cintura = document.forms["form"]["form:cintura"].value;
    var sexo = document.forms["form"]["form:sexo"].value;

    if (cintura !== "") {
        if (sexo === "M") {
            if (93 > cintura) {
                document.getElementById('aviso').innerHTML = "NÃO EXISTE RISCO";
                document.getElementById('aviso').style.color = "green";
            }
            if (cintura >= 94) {
                if (101 > cintura) {
                    document.getElementById('aviso').innerHTML = "RISCO ELEVADO";
                    document.getElementById('aviso').style.color = "#FF8C00";
                }
            }
            if (cintura >= 102) {
                document.getElementById('aviso').innerHTML = "RISCO MUITO ELEVADO";
                document.getElementById('aviso').style.color = "red";
            }
        }
        if (sexo === "F") {
            if (79 > cintura) {
                document.getElementById('aviso').innerHTML = "NÃO EXISTE RISCO";
                document.getElementById('aviso').style.color = "green";
            }
            if (cintura >= 80) {
                if (87 > cintura) {
                    document.getElementById('aviso').innerHTML = "RISCO ELEVADO";
                    document.getElementById('aviso').style.color = "#FF8C00";
                }
            }
            if (cintura >= 88) {
                document.getElementById('aviso').innerHTML = "RISCO MUITO ELEVADO";
                document.getElementById('aviso').style.color = "red";
            }
        }
    }
}

function dx() {
    var cintura = document.forms["form"]["form:cintura"].value;
    var quadril = document.forms["form"]["form:quadril"].value;
    if (quadril !== "") {
        if (cintura !== "")
            document.forms["form"]["form:dxcq"].value = (cintura / quadril).toFixed(2);
    }
    var dxcq = document.forms["form"]["form:dxcq"].value;
    var sexo = document.forms["form"]["form:sexo"].value;
    if (dxcq !== "") {
        if (sexo === "F") {
            if (0.8 >= dxcq) {
                document.forms["form"]["form:crcq"].value = "GINECÓIDE";
            }
            if (dxcq > 0.8) {
                document.forms["form"]["form:crcq"].value = "ANDRÓIDE";
            }
        }
        if (sexo === "M") {
            if (1 >= dxcq) {
                document.forms["form"]["form:crcq"].value = "GINECÓIDE";
            }
            if (dxcq > 1) {
                document.forms["form"]["form:crcq"].value = "ANDRÓIDE";
            }
        }
    }
}

function campoFB() {
    var tipo = document.forms["form"]["form:tipo_input"].value;
    var pesoAtual = document.forms["form"]["form:pesoAtual"].value;
    pesoAtual = pesoAtual.replace(",", ".");
    if (tipo === "PERDER") {
        document.forms["form"]["form:fb"].value = (pesoAtual * 22).toFixed(2);
    } else if (tipo === "MANTER") {
        document.forms["form"]["form:fb"].value = (pesoAtual * 27).toFixed(2);
    } else if (tipo === "GANHAR") {
        document.forms["form"]["form:fb"].value = (pesoAtual * 32).toFixed(2);
    }
}

function rotina() {
    var desPaes = document.forms["form"]["form:desPaes"].value;
    var colPaes = document.forms["form"]["form:colPaes"].value;
    var almPaes = document.forms["form"]["form:almPaes"].value;
    var lanPaes = document.forms["form"]["form:lanPaes"].value;
    var janPaes = document.forms["form"]["form:janPaes"].value;
    var ceiPaes = document.forms["form"]["form:ceiPaes"].value;
    if (desPaes === "")
        desPaes = "0";
    if (colPaes === "")
        colPaes = "0";
    if (almPaes === "")
        almPaes = "0";
    if (lanPaes === "")
        lanPaes = "0";
    if (janPaes === "")
        janPaes = "0";
    if (ceiPaes === "")
        ceiPaes = "0";
    var totalPaes = parseInt(desPaes) + parseInt(colPaes) + parseInt(almPaes) + parseInt(lanPaes) + parseInt(janPaes) + parseInt(ceiPaes);
    document.getElementById('totalPaes').innerHTML = totalPaes;
    if (5 > parseInt(totalPaes)) {
        document.getElementById('adeqPaes').innerHTML = "B";
        document.getElementById('adeqPaes').style.backgroundColor = "#FF8C00";
    }
    if (parseInt(totalPaes) >= 5) {
        if (9 >= parseInt(totalPaes))
            document.getElementById('adeqPaes').innerHTML = "A";
        document.getElementById('adeqPaes').style.backgroundColor = "green";
    }
    if (parseInt(totalPaes) > 9) {
        document.getElementById('adeqPaes').innerHTML = "E";
        document.getElementById('adeqPaes').style.backgroundColor = "red";
    }

    var desHortal = document.forms["form"]["form:desHortal"].value;
    var colHortal = document.forms["form"]["form:colHortal"].value;
    var almHortal = document.forms["form"]["form:almHortal"].value;
    var lanHortal = document.forms["form"]["form:lanHortal"].value;
    var janHortal = document.forms["form"]["form:janHortal"].value;
    var ceiHortal = document.forms["form"]["form:ceiHortal"].value;
    if (desHortal === "")
        desHortal = "0";
    if (colHortal === "")
        colHortal = "0";
    if (almHortal === "")
        almHortal = "0";
    if (lanHortal === "")
        lanHortal = "0";
    if (janHortal === "")
        janHortal = "0";
    if (ceiHortal === "")
        ceiHortal = "0";
    var totalHortal = parseInt(desHortal) + parseInt(colHortal) + parseInt(almHortal) + parseInt(lanHortal) + parseInt(janHortal) + parseInt(ceiHortal);
    document.getElementById('totalHortal').innerHTML = totalHortal;
    if (4 > parseInt(totalHortal)) {
        document.getElementById('adeqHortal').innerHTML = "B";
        document.getElementById('adeqHortal').style.backgroundColor = "#FF8C00";
    }
    if (parseInt(totalHortal) >= 4) {
        if (5 >= parseInt(totalHortal))
            document.getElementById('adeqHortal').innerHTML = "A";
        document.getElementById('adeqHortal').style.backgroundColor = "green";
    }
    if (parseInt(totalHortal) > 5) {
        document.getElementById('adeqHortal').innerHTML = "E";
        document.getElementById('adeqHortal').style.backgroundColor = "red";
    }

    var desFruta = document.forms["form"]["form:desFruta"].value;
    var colFruta = document.forms["form"]["form:colFruta"].value;
    var almFruta = document.forms["form"]["form:almFruta"].value;
    var lanFruta = document.forms["form"]["form:lanFruta"].value;
    var janFruta = document.forms["form"]["form:janFruta"].value;
    var ceiFruta = document.forms["form"]["form:ceiFruta"].value;
    if (desFruta === "")
        desFruta = "0";
    if (colFruta === "")
        colFruta = "0";
    if (almFruta === "")
        almFruta = "0";
    if (lanFruta === "")
        lanFruta = "0";
    if (janFruta === "")
        janFruta = "0";
    if (ceiFruta === "")
        ceiFruta = "0";
    var totalFruta = parseInt(desFruta) + parseInt(colFruta) + parseInt(almFruta) + parseInt(lanFruta) + parseInt(janFruta) + parseInt(ceiFruta);
    document.getElementById('totalFruta').innerHTML = totalFruta;
    if (3 > parseInt(totalFruta)) {
        document.getElementById('adeqFruta').innerHTML = "B";
        document.getElementById('adeqFruta').style.backgroundColor = "#FF8C00";
    }
    if (parseInt(totalFruta) >= 3) {
        if (5 >= parseInt(totalFruta))
            document.getElementById('adeqFruta').innerHTML = "A";
        document.getElementById('adeqFruta').style.backgroundColor = "green";
    }
    if (parseInt(totalFruta) > 5) {
        document.getElementById('adeqFruta').innerHTML = "E";
        document.getElementById('adeqFruta').style.backgroundColor = "red";
    }

    var desLeite = document.forms["form"]["form:desLeite"].value;
    var colLeite = document.forms["form"]["form:colLeite"].value;
    var almLeite = document.forms["form"]["form:almLeite"].value;
    var lanLeite = document.forms["form"]["form:lanLeite"].value;
    var janLeite = document.forms["form"]["form:janLeite"].value;
    var ceiLeite = document.forms["form"]["form:ceiLeite"].value;
    if (desLeite === "")
        desLeite = "0";
    if (colLeite === "")
        colLeite = "0";
    if (almLeite === "")
        almLeite = "0";
    if (lanLeite === "")
        lanLeite = "0";
    if (janLeite === "")
        janLeite = "0";
    if (ceiLeite === "")
        ceiLeite = "0";
    var totalLeite = parseInt(desLeite) + parseInt(colLeite) + parseInt(almLeite) + parseInt(lanLeite) + parseInt(janLeite) + parseInt(ceiLeite);
    document.getElementById('totalLeite').innerHTML = totalLeite;
    if (3 > parseInt(totalLeite)) {
        document.getElementById('adeqLeite').innerHTML = "B";
        document.getElementById('adeqLeite').style.backgroundColor = "#FF8C00";
    }
    if (parseInt(totalLeite) === 3) {
        document.getElementById('adeqLeite').innerHTML = "A";
        document.getElementById('adeqLeite').style.backgroundColor = "green";
    }
    if (parseInt(totalLeite) > 3) {
        document.getElementById('adeqLeite').innerHTML = "E";
        document.getElementById('adeqLeite').style.backgroundColor = "red";
    }

    var desCarne = document.forms["form"]["form:desCarne"].value;
    var colCarne = document.forms["form"]["form:colCarne"].value;
    var almCarne = document.forms["form"]["form:almCarne"].value;
    var lanCarne = document.forms["form"]["form:lanCarne"].value;
    var janCarne = document.forms["form"]["form:janCarne"].value;
    var ceiCarne = document.forms["form"]["form:ceiCarne"].value;
    if (desCarne === "")
        desCarne = "0";
    if (colCarne === "")
        colCarne = "0";
    if (almCarne === "")
        almCarne = "0";
    if (lanCarne === "")
        lanCarne = "0";
    if (janCarne === "")
        janCarne = "0";
    if (ceiCarne === "")
        ceiCarne = "0";
    var totalCarne = parseInt(desCarne) + parseInt(colCarne) + parseInt(almCarne) + parseInt(lanCarne) + parseInt(janCarne) + parseInt(ceiCarne);
    document.getElementById('totalCarne').innerHTML = totalCarne;
    if (1 > parseInt(totalCarne)) {
        document.getElementById('adeqCarne').innerHTML = "B";
        document.getElementById('adeqCarne').style.backgroundColor = "#FF8C00";
    }
    if (parseInt(totalCarne) >= 1) {
        if (2 >= parseInt(totalCarne))
            document.getElementById('adeqCarne').innerHTML = "A";
        document.getElementById('adeqCarne').style.backgroundColor = "green";
    }
    if (parseInt(totalCarne) > 2) {
        document.getElementById('adeqCarne').innerHTML = "E";
        document.getElementById('adeqCarne').style.backgroundColor = "red";
    }

    var desLegumin = document.forms["form"]["form:desLegumin"].value;
    var colLegumin = document.forms["form"]["form:colLegumin"].value;
    var almLegumin = document.forms["form"]["form:almLegumin"].value;
    var lanLegumin = document.forms["form"]["form:lanLegumin"].value;
    var janLegumin = document.forms["form"]["form:janLegumin"].value;
    var ceiLegumin = document.forms["form"]["form:ceiLegumin"].value;
    if (desLegumin === "")
        desLegumin = "0";
    if (colLegumin === "")
        colLegumin = "0";
    if (almLegumin === "")
        almLegumin = "0";
    if (lanLegumin === "")
        lanLegumin = "0";
    if (janLegumin === "")
        janLegumin = "0";
    if (ceiLegumin === "")
        ceiLegumin = "0";
    var totalLegumin = parseInt(desLegumin) + parseInt(colLegumin) + parseInt(almLegumin) + parseInt(lanLegumin) + parseInt(janLegumin) + parseInt(ceiLegumin);
    document.getElementById('totalLegumin').innerHTML = totalLegumin;
    if (1 > parseInt(totalLegumin)) {
        document.getElementById('adeqLegumin').innerHTML = "B";
        document.getElementById('adeqLegumin').style.backgroundColor = "#FF8C00";
    }
    if (parseInt(totalLegumin) >= 1) {
        if (2 >= parseInt(totalLegumin))
            document.getElementById('adeqLegumin').innerHTML = "A";
        document.getElementById('adeqLegumin').style.backgroundColor = "green";
    }
    if (parseInt(totalLegumin) > 2) {
        document.getElementById('adeqLegumin').innerHTML = "E";
        document.getElementById('adeqLegumin').style.backgroundColor = "red";
    }

    var desGordura = document.forms["form"]["form:desGordura"].value;
    var colGordura = document.forms["form"]["form:colGordura"].value;
    var almGordura = document.forms["form"]["form:almGordura"].value;
    var lanGordura = document.forms["form"]["form:lanGordura"].value;
    var janGordura = document.forms["form"]["form:janGordura"].value;
    var ceiGordura = document.forms["form"]["form:ceiGordura"].value;
    if (desGordura === "")
        desGordura = "0";
    if (colGordura === "")
        colGordura = "0";
    if (almGordura === "")
        almGordura = "0";
    if (lanGordura === "")
        lanGordura = "0";
    if (janGordura === "")
        janGordura = "0";
    if (ceiGordura === "")
        ceiGordura = "0";
    var totalGord = parseInt(desGordura) + parseInt(colGordura) + parseInt(almGordura) + parseInt(lanGordura) + parseInt(janGordura) + parseInt(ceiGordura);
    document.getElementById('totalGord').innerHTML = totalGord;
    if (1 > parseInt(totalGord)) {
        document.getElementById('adeqGord').innerHTML = "B";
        document.getElementById('adeqGord').style.backgroundColor = "#FF8C00";
    }
    if (parseInt(totalGord) >= 1) {
        if (2 >= parseInt(totalGord))
            document.getElementById('adeqGord').innerHTML = "A";
        document.getElementById('adeqGord').style.backgroundColor = "green";
    }
    if (parseInt(totalGord) > 2) {
        document.getElementById('adeqGord').innerHTML = "E";
        document.getElementById('adeqGord').style.backgroundColor = "red";
    }

    var desDoces = document.forms["form"]["form:desDoces"].value;
    var colDoces = document.forms["form"]["form:colDoces"].value;
    var almDoces = document.forms["form"]["form:almDoces"].value;
    var lanDoces = document.forms["form"]["form:lanDoces"].value;
    var janDoces = document.forms["form"]["form:janDoces"].value;
    var ceiDoces = document.forms["form"]["form:ceiDoces"].value;
    if (desDoces === "")
        desDoces = "0";
    if (colDoces === "")
        colDoces = "0";
    if (almDoces === "")
        almDoces = "0";
    if (lanDoces === "")
        lanDoces = "0";
    if (janDoces === "")
        janDoces = "0";
    if (ceiDoces === "")
        ceiDoces = "0";
    var totalDoces = parseInt(desDoces) + parseInt(colDoces) + parseInt(almDoces) + parseInt(lanDoces) + parseInt(janDoces) + parseInt(ceiDoces);
    document.getElementById('totalDoces').innerHTML = totalDoces;
    if (1 > parseInt(totalDoces)) {
        document.getElementById('adeqDoces').innerHTML = "B";
        document.getElementById('adeqDoces').style.backgroundColor = "#FF8C00";
    }
    if (parseInt(totalDoces) >= 1) {
        if (2 >= parseInt(totalDoces))
            document.getElementById('adeqDoces').innerHTML = "A";
        document.getElementById('adeqDoces').style.backgroundColor = "green";
    }
    if (parseInt(totalDoces) > 2) {
        document.getElementById('adeqDoces').innerHTML = "E";
        document.getElementById('adeqDoces').style.backgroundColor = "red";
    }
}

function perfilRotina() {
    var perfil = document.forms["form"]["form:perfil_input"].value;
    if (perfil !== "") {
        zeraPerfil();
        if (perfil === "1200") {
            var pao = document.forms["form"]["form:desPaes"].value;
            if (pao === "") {
                pao = 0;
            }
            pao = parseInt(pao) + 1;
            document.forms["form"]["form:desPaes"].value = pao;

            var fruta = document.forms["form"]["form:colFruta"].value;
            if (fruta === "") {
                fruta = 0;
            }
            fruta = parseInt(fruta) + 1;
            document.forms["form"]["form:colFruta"].value = fruta;

            var carne1 = document.forms["form"]["form:almCarne"].value;
            if (carne1 === "") {
                carne1 = 0;
            }
            carne1 = parseInt(carne1) + 1;
            document.forms["form"]["form:almCarne"].value = carne1;

            var vegetalB1 = document.forms["form"]["form:almHortal"].value;
            if (vegetalB1 === "") {
                vegetalB1 = 0;
            }
            vegetalB1 = parseInt(vegetalB1) + 1;
            document.forms["form"]["form:almHortal"].value = vegetalB1;

            var vegetalA1 = document.forms["form"]["form:almLegumin"].value;
            if (vegetalA1 === "") {
                vegetalA1 = 0;
            }
            vegetalA1 = parseInt(vegetalA1) + 1;
            document.forms["form"]["form:almLegumin"].value = vegetalA1;

            var fruta2 = document.forms["form"]["form:almFruta"].value;
            if (fruta2 === "") {
                fruta2 = 0;
            }
            fruta2 = parseInt(fruta2) + 1;
            document.forms["form"]["form:almFruta"].value = fruta2;

            var carne2 = document.forms["form"]["form:janCarne"].value;
            if (carne2 === "") {
                carne2 = 0;
            }
            carne2 = parseInt(carne2) + 1;
            document.forms["form"]["form:janCarne"].value = carne2;

            var vegetalB2 = document.forms["form"]["form:janHortal"].value;
            if (vegetalB2 === "") {
                vegetalB2 = 0;
            }
            vegetalB2 = parseInt(vegetalB2) + 1;
            document.forms["form"]["form:janHortal"].value = vegetalB2;

            var vegetalA2 = document.forms["form"]["form:janLegumin"].value;
            if (vegetalA2 === "") {
                vegetalA2 = 0;
            }
            vegetalA2 = parseInt(vegetalA2) + 1;
            document.forms["form"]["form:janLegumin"].value = vegetalA2;

            var fruta3 = document.forms["form"]["form:janFruta"].value;
            if (fruta3 === "") {
                fruta3 = 0;
            }
            fruta3 = parseInt(fruta3) + 1;
            document.forms["form"]["form:janFruta"].value = fruta3;

            var ceia = document.forms["form"]["form:ceiLeite"].value;
            if (ceia === "") {
                ceia = 0;
            }
            ceia = parseInt(ceia) + 1;
            document.forms["form"]["form:ceiLeite"].value = ceia;
        } else if (perfil === "1500") {

            var pao = document.forms["form"]["form:desPaes"].value;
            if (pao === "") {
                pao = 0;
            }
            pao = parseInt(pao) + 1;
            document.forms["form"]["form:desPaes"].value = pao;

            var fruta = document.forms["form"]["form:colFruta"].value;
            if (fruta === "") {
                fruta = 0;
            }
            fruta = parseInt(fruta) + 1;
            document.forms["form"]["form:colFruta"].value = fruta;

            var carne1 = document.forms["form"]["form:almCarne"].value;
            if (carne1 === "") {
                carne1 = 0;
            }
            carne1 = parseInt(carne1) + 1;
            document.forms["form"]["form:almCarne"].value = carne1;

            var vegetalB1 = document.forms["form"]["form:almHortal"].value;
            if (vegetalB1 === "") {
                vegetalB1 = 0;
            }
            vegetalB1 = parseInt(vegetalB1) + 1;
            document.forms["form"]["form:almHortal"].value = vegetalB1;

            var vegetalA1 = document.forms["form"]["form:almLegumin"].value;
            if (vegetalA1 === "") {
                vegetalA1 = 0;
            }
            vegetalA1 = parseInt(vegetalA1) + 1;
            document.forms["form"]["form:almLegumin"].value = vegetalA1;

            var fruta2 = document.forms["form"]["form:almFruta"].value;
            if (fruta2 === "") {
                fruta2 = 0;
            }
            fruta2 = parseInt(fruta2) + 1;
            document.forms["form"]["form:almFruta"].value = fruta2;

            var carne2 = document.forms["form"]["form:janCarne"].value;
            if (carne2 === "") {
                carne2 = 0;
            }
            carne2 = parseInt(carne2) + 1;
            document.forms["form"]["form:janCarne"].value = carne2;

            var vegetalB2 = document.forms["form"]["form:janHortal"].value;
            if (vegetalB2 === "") {
                vegetalB2 = 0;
            }
            vegetalB2 = parseInt(vegetalB2) + 1;
            document.forms["form"]["form:janHortal"].value = vegetalB2;

            var vegetalA2 = document.forms["form"]["form:janLegumin"].value;
            if (vegetalA2 === "") {
                vegetalA2 = 0;
            }
            vegetalA2 = parseInt(vegetalA2) + 1;
            document.forms["form"]["form:janLegumin"].value = vegetalA2;

            var fruta3 = document.forms["form"]["form:janFruta"].value;
            if (fruta3 === "") {
                fruta3 = 0;
            }
            fruta3 = parseInt(fruta3) + 1;
            document.forms["form"]["form:janFruta"].value = fruta3;

            var ceia = document.forms["form"]["form:ceiLeite"].value;
            if (ceia === "") {
                ceia = 0;
            }
            ceia = parseInt(ceia) + 1;
            document.forms["form"]["form:ceiLeite"].value = ceia;

            var pao2 = document.forms["form"]["form:ceiPaes"].value;
            if (pao2 === "") {
                pao2 = 0;
            }
            pao2 = parseInt(pao2) + 1;
            document.forms["form"]["form:ceiPaes"].value = pao2;
        } else if (perfil === "1800") {

            var pao = document.forms["form"]["form:desPaes"].value;
            if (pao === "") {
                pao = 0;
            }
            pao = parseInt(pao) + 1;
            document.forms["form"]["form:desPaes"].value = pao;

            var fruta = document.forms["form"]["form:colFruta"].value;
            if (fruta === "") {
                fruta = 0;
            }
            fruta = parseInt(fruta) + 1;
            document.forms["form"]["form:colFruta"].value = fruta;

            var carne1 = document.forms["form"]["form:almCarne"].value;
            if (carne1 === "") {
                carne1 = 0;
            }
            carne1 = parseInt(carne1) + 1;
            document.forms["form"]["form:almCarne"].value = carne1;

            var vegetalB1 = document.forms["form"]["form:almHortal"].value;
            if (vegetalB1 === "") {
                vegetalB1 = 0;
            }
            vegetalB1 = parseInt(vegetalB1) + 1;
            document.forms["form"]["form:almHortal"].value = vegetalB1;

            var vegetalA1 = document.forms["form"]["form:almLegumin"].value;
            if (vegetalA1 === "") {
                vegetalA1 = 0;
            }
            vegetalA1 = parseInt(vegetalA1) + 1;
            document.forms["form"]["form:almLegumin"].value = vegetalA1;

            var fruta2 = document.forms["form"]["form:almFruta"].value;
            if (fruta2 === "") {
                fruta2 = 0;
            }
            fruta2 = parseInt(fruta2) + 1;
            document.forms["form"]["form:almFruta"].value = fruta2;

            var carne2 = document.forms["form"]["form:janCarne"].value;
            if (carne2 === "") {
                carne2 = 0;
            }
            carne2 = parseInt(carne2) + 1;
            document.forms["form"]["form:janCarne"].value = carne2;

            var vegetalB2 = document.forms["form"]["form:janHortal"].value;
            if (vegetalB2 === "") {
                vegetalB2 = 0;
            }
            vegetalB2 = parseInt(vegetalB2) + 1;
            document.forms["form"]["form:janHortal"].value = vegetalB2;

            var vegetalA2 = document.forms["form"]["form:janLegumin"].value;
            if (vegetalA2 === "") {
                vegetalA2 = 0;
            }
            vegetalA2 = parseInt(vegetalA2) + 1;
            document.forms["form"]["form:janLegumin"].value = vegetalA2;

            var fruta3 = document.forms["form"]["form:janFruta"].value;
            if (fruta3 === "") {
                fruta3 = 0;
            }
            fruta3 = parseInt(fruta3) + 1;
            document.forms["form"]["form:janFruta"].value = fruta3;

            var ceia = document.forms["form"]["form:ceiLeite"].value;
            if (ceia === "") {
                ceia = 0;
            }
            ceia = parseInt(ceia) + 1;
            document.forms["form"]["form:ceiLeite"].value = ceia;

            var pao2 = document.forms["form"]["form:ceiPaes"].value;
            if (pao2 === "") {
                pao2 = 0;
            }
            pao2 = parseInt(pao2) + 1;
            document.forms["form"]["form:ceiPaes"].value = pao2;
        }
    } else {
//        alert('asdasd');
        zeraPerfil();
    }
    rotina();
}

function zeraPerfil() {
    document.forms["form"]["form:desPaes"].value = "";
    document.forms["form"]["form:colFruta"].value = "";
    document.forms["form"]["form:almCarne"].value = "";
    document.forms["form"]["form:almHortal"].value = "";
    document.forms["form"]["form:almLegumin"].value = "";
    document.forms["form"]["form:almFruta"].value = "";
    document.forms["form"]["form:janCarne"].value = "";
    document.forms["form"]["form:janHortal"].value = "";
    document.forms["form"]["form:janLegumin"].value = "";
    document.forms["form"]["form:janFruta"].value = "";
    document.forms["form"]["form:ceiLeite"].value = "";
    document.forms["form"]["form:ceiPaes"].value = "";
}

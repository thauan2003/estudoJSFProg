/**
 * 
 */
var link_was_clicked = false;
document.addEventListener("click", function (e) {
    if (e.target.nodeName.toLowerCase() === 'a' || e.target.nodeName.toLowerCase() === 'img' || e.target.nodeName.toLowerCase() === 'input' || e.target.nodeName.toLowerCase() === 'button') {
        link_was_clicked = true;
    }
}, true);

document.onkeydown = function (e) {
    if (e.keyCode === 116) {
        link_was_clicked = true;
    }
};

window.onbeforeunload = alertClose;
function alertClose() {
    if (link_was_clicked) {
        return;
    }
    return 'Você vai perder os dados que não foram salvos.';
}

function link_false() {
    link_was_clicked = false;
    setTimeout(link_false, 1000);
}
link_false();
/* 
 * @name Filter 2.0
 * @author Honorato
 */
$(document).ready(function () {
    $('body').append('<div class="filter-container"></div>');
    $('body').on('click', '.filter', function () {
        $(this).toggleClass('active');
        var option = $(this).attr('for');
        var element = document.getElementById(option);
        $(element).toggleClass('show');
        $('.filter-container').toggleClass('show');
    });
    $('body').on('click', '.filter-container', function() {
        hideFilter();
    });
});
function hideFilter() {
    $('.filter').removeClass('active');
    $('.filter-container').removeClass('show');
    $('.filter-option').removeClass('show');
}
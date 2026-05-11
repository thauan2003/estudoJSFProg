/* 
 * @name Filter 1.0
 * @author Honorato
 */
$(document).ready(function() {
    $('body').on('click', '.filter-button', function() {
        var button = $(this);

        var topContent = button.offset().top + button.innerHeight() + 11;
        var leftContent;

        if ($(this).attr('rel') === 'left') {
            leftContent = button.offset().left - $('#' + button.attr('dir')).innerWidth() + 37;
        } else if ($(this).attr('rel') === 'center') {
            leftContent = button.offset().left - ($('#' + button.attr('dir')).innerWidth() / 2) + 15;
        } else {
            leftContent = button.offset().left - 15;
        }

        $('#' + button.attr('dir')).css({'top': topContent, 'left': leftContent});

        $('#' + button.attr('dir')).addClass('show');

        var topArrow = button.offset().top + button.innerHeight() - 11;
        var leftArrow = button.offset().left + (button.innerWidth() / 2) - 11;
        $('.filter-arrow').css({'top': topArrow, 'left': leftArrow});
        $('.filter-arrow').addClass('show');

        $('.filter-container').addClass('show');
    });
    $('body').on('click', '.filter-container', function() {
        hideFilter();
    });
    $('body').append('<div class="filter-container"></div>');
    $('body').append('<div class="filter-arrow"></div>');
});
function hideFilter() {
    $('.filter-container').removeClass('show');
    $('.filter-content').removeClass('show');
    $('.filter-arrow').removeClass('show');
}


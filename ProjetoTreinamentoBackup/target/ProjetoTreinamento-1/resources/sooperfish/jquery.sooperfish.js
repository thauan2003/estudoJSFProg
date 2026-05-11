/*
 * SooperFish 0.1
 * (c) 2010 Jurriaan Roelofs - SooperThemes.com
 * Inspired by Suckerfish, Superfish and Droppy
 * Licensed GPL: http://www.gnu.org/licenses/gpl.html
 */
$.fn.sooperfish = function(op) {

    var sf = $.fn.sooperfish;
    sf.o = [];
    sf.op = {};
    sf.c = {
        menuClass: 'sf-js-enabled',
        isParent: 'sf-parent',
        arrowClass: 'sf-arrow'
    };
    if ($.easing.easeOutOvershoot) { //set default easing
        sooperEasingShow = 'easeOutOvershoot';
    } else {
        sooperEasingShow = 'linear';
    }
    ;
    if ($.easing.easeInTurbo) {
        sooperEasingHide = 'easeInTurbo';
    } else {
        sooperEasingHide = 'linear';
    }
    ;
    sf.defaults = {
        multiColumn: true,
        dualColumn: 15, //if a submenu has at least this many items it will be divided in 2 columns
        tripleColumn: 30, //if a submenu has at least this many items it will be divided in 3 columns
        hoverClass: 'sfHover',
        delay: 500, //make sure menus only disappear when intended, 500ms is advised by Jacob Nielsen
        animationShow: {height: 'show'},
        speedShow: 600,
        easingShow: sooperEasingShow,
        animationHide: {height: 'hide', opacity: 'hide'},
        speedHide: 200,
        easingHide: sooperEasingHide,
        autoArrows: true, //Adds span elements to parent li elements, projecting arrow images on these items to indicate submenus. I added an alternative image file with white arrows.
        onShow: function() {
        }, //callback after showing menu
        onHide: function() {
        } //callback after hiding menu
    };


    //Merge default settings with o function parameter
    var o = $.extend({}, sf.defaults, op);
    if (!o.sooperfishWidth) {
        o.sooperfishWidth = $('ul:first li:first', this).width(); //if no width is set in options try to read it from DOM
    } else {
        $('ul li', this).width(o.sooperfishWidth) //if width is set in invocation make sure this width is true for all submenus
    }

    this.each(function() {

        //Check dom for submenus
        var parentLi = $('li:has(ul)', this);
        parentLi.each(function() {
            if (o.autoArrows) { //Add autoArrows if requested
                $('>a', this).append('<span class="' + sf.c.arrowClass + '"></span>');
            }
            $(this).addClass(sf.c.isParent);
        });

        $('ul', this).css({left: 'auto', display: 'none'}); //The script needs to use display:none to make the hiding animation possible

        //Divide menu in columns
        //Set width override
        if (o.multiColumn) {
            var uls = $('ul', this);
            uls.each(function() {
                var ulsize = $('>li:not(.backLava)', this).length; //Skip list items added by Lavalamp plugin
                if (ulsize >= o.dualColumn) {
                    if (ulsize >= o.tripleColumn) {
                        $(this).width(3 * o.sooperfishWidth).addClass('multicolumn triplecolumn');
                    } else {
                        $(this).width(2 * o.sooperfishWidth).addClass('multicolumn dualcolumn');
                    }
                }
            });
        }

        var root = this, zIndex = 1000;

        function calculatePosition(objElement, strOffset) {
            var iOffset = 0;
            if (objElement.offsetParent) {
                do {
                    iOffset += objElement[strOffset];
                    objElement = objElement.offsetParent;
                } while (objElement);
            }
            return iOffset;
        }

        function getSubmenu(ele) {
            if (ele.nodeName.toLowerCase() == 'li') {
                var submenu = $('> ul', ele);
                return submenu.length ? submenu[0] : null;
            } else {
                return ele;
            }
        }

        function getActuator(ele) {
            if (ele.nodeName.toLowerCase() == 'ul') {
                return $(ele).parents('li')[0];
            } else {
                return ele;
            }
        }
        var exe;
        function hideSooperfishUl() {
            clearInterval(exe);
            var submenu = getSubmenu(this);
            if (!submenu)
                return;
            $.data(submenu, 'cancelHide', false);
            var li = getActuator(this);
            $('> a', li).removeClass('bgFix');
            setTimeout(function() {
                if (!$.data(submenu, 'cancelHide')) {
                    $(submenu).animate(o.animationHide, o.speedHide, o.easingHide, function() {
                        o.onHide.call(submenu);
                    });
                }
            }, o.delay);
        }

        function showSooperfishUl() {
            var submenu = getSubmenu(this);
            if (!submenu)
                return;
            $.data(submenu, 'cancelHide', true);
            clearInterval(exe);
            exe = setTimeout(function() {
                $(submenu).css({zIndex: zIndex++}).animate(o.animationShow, o.speedShow, o.easingShow, function() {
                    o.onShow.call(submenu);
                });
            }, 500);
//      $(submenu).css({zIndex: zIndex++}).animate(o.animationShow,o.speedShow,o.easingShow,function(){ o.onShow.call(submenu); });
            if (this.nodeName.toLowerCase() == 'ul') {
                var li = getActuator(this);
                $(li).addClass('hover');
                $('> a', li).addClass('hover');
            }

            var li = getActuator(this);
            $('> a', li).addClass('bgFix');

            var pos = calculatePosition(getActuator(this), 'offsetLeft');
            var alt = $('ul', this).height();
            var lar = $(getActuator(this)).width();
            var menu_lar = $('ul', this).width();
            var tela = $(window).width();
            var largura = 0;
            if (lar === 220) {
                largura = menu_lar;
            }else{
                largura = menu_lar - lar;
            }
            if ((pos + largura + 215) > tela) {
                if (lar === 220) {
                    if (menu_lar === 440) {
                        $(getSubmenu(this)).css("margin", "0 0 0 -435px");
                    } else if (menu_lar > 440) {
                        $(getSubmenu(this)).css("margin", "0 0 0 -655px");
                    } else {
                        $(getSubmenu(this)).css("margin", "0 0 0 -215px");
                    }
                } else {
                    $(getSubmenu(this)).css("margin", "0 0 0 " + (lar - menu_lar) + "px");
                }
            } else {
                $('ul ul', this).css("margin", "0 0 0 215px");
            }
        }

        // Bind Events. Yes it's that simple!
        $('li', this).unbind().hover(showSooperfishUl, hideSooperfishUl);

    });

};

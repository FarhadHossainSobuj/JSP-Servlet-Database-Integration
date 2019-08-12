(function($) {
        "use strict";
        jQuery(document).on('ready',function(e) {

            jQuery(window).on('load',function(e) {
                var winwidth = jQuery(window).width();
                if (winwidth > 767) {

                    var heights = [];
                    jQuery('.review_single').each(function() {
                        var height = $(this).height();
                        heights.push(height);
                    });
                    var max = Math.max.apply(Math, heights);
                    jQuery('.review_single').css("height", max);


                    var aheights = [];
                    jQuery('.single_active').each(function() {
                        var aheight = $(this).height();
                        aheights.push(aheight);
                    });
                    var max = Math.max.apply(Math, aheights);
                    jQuery('.single_active').css("height", max);

                    var bheight = jQuery(".banner_main").height();
                    jQuery(".home_content").height(bheight);
                    jQuery(".home_banner").height(bheight);
                    var headheight = jQuery('header').height();
                    var menu_height = jQuery('.custom_nav').height();

                    // FIXED HEADER JS

                    jQuery(window).on('scroll',function(e) {
                        if (jQuery(window).scrollTop() >= 150) {
                            jQuery('.stick').addClass('fixed_header').delay(1000);
                        } else {
                            jQuery('.stick').removeClass('fixed_header');
                        }
                    });

                    // Defining a function to set size for #hero 
                    function fullscreen() {
                        jQuery('.banner_main').css({
                            width: jQuery(window).width(),
                            height: jQuery(window).height() - jQuery('.custom_nav').height(),
                        });
                    }

                    fullscreen();

                    // Run the function in case of window resize
                    jQuery(window).on('resize',function(e) {
                        fullscreen();
                    });

                }
            });

            //Preloader
            setTimeout(function() {
                jQuery('.preloader').fadeOut('slow', function() {});
            }, 2000);

            //Video Popup jS
            jQuery("a#single_image").fancybox();
            jQuery("a#inline").fancybox({
                'hideOnContentClick': true
            });

            /* Apply fancybox to multiple items */
            jQuery('.fancyimg').fancybox();
            jQuery('.fancyvideo').fancybox({
                openEffect: 'none',
                closeEffect: 'none',
                helpers: {
                    media: {}
                }
            });

            jQuery("a.group").fancybox({
                'transitionIn': 'elastic',
                'transitionOut': 'elastic',
                'speedIn': 600,
                'speedOut': 200,
                'overlayShow': false
            });

            

            // WOW js
            new WOW().init();
             //Back To Top
            jQuery(window).on('scroll',function(e) {
                if (jQuery(this).scrollTop() > 400) {
                    jQuery('#back-top').fadeIn();
                } else {
                    jQuery('#back-top').fadeOut();
                }
            });
            // scroll body to 0px on click
            jQuery('#back-top').on('click', function(e) {
                jQuery('#back-top').tooltip('hide');
                jQuery('body,html').animate({
                    scrollTop: 0
                }, 1500);
                return false;
            });

            //Smoth Scroll
             jQuery(function() {
                jQuery('a[href*="#"]:not([href="#"])').on('click',function(e) {
                    var headheight= jQuery("header").height();
                  if (location.pathname.replace(/^\//,'') == this.pathname.replace(/^\//,'') && location.hostname == this.hostname) {
                    var target = jQuery(this.hash);
                    target = target.length ? target : jQuery('[name=' + this.hash.slice(1) +']');
                    if (target.length) {
                      jQuery('html, body').animate({
                        scrollTop: target.offset().top-headheight
                      }, 1000);
                      return false;
                    }
                  }
                });
              });

          });
        })(jQuery);
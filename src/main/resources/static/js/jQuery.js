$(function () {
    // this will get the full URL at the address bar excluding parameters
    let url = (window.location.href).split("?")[0];

    // passes on every "a" tag
    $(".nav-link").each(function() {
        // checks if its the same on the address bar
        if (url === (this.href)) {
            $(this).closest("a").addClass("active");
        }
    });

    $.ajax({
        'url': '/user/isLoggedIn/',
        'success': function(resp) {
            if(resp) {
                $('#login-btn').html("Logout").attr("href", "/invalidate/session")
            }
        }
    });

    setFooterStyle()
    window.onresize = setFooterStyle;
});

function setFooterStyle() {
    const footer = $("#footer");
    const docHeight = $(window).height();
    const footerHeight = footer.outerHeight();
    const footerTop = footer.position().top + footerHeight;
    if (footerTop < docHeight) {
        footer.css('margin-top', (docHeight - footerTop) + 'px');
    } else {
        footer.css('margin-top', '');
    }
    footer.removeClass('invisible');
}
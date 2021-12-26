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
});
$(function () {
    // this will get the full URL at the address bar
    let url = window.location.href;

    // passes on every "a" tag
    $(".nav-link").each(function() {
        // checks if its the same on the address bar
        if (url === (this.href)) {
            $(this).closest("a").addClass("active");
            //for making parent of submenu active
            //$(this).closest("a").parent().parent().addClass("underline");
        }
    });
});
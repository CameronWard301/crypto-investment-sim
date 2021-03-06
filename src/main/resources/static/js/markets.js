$(function () {

    const fiatParam = new URLSearchParams(document.location.search.substring(1)).get("fiat"); //get parameter fiat from url

    let pound = $('#pound');
    let dollar = $('#dollar');
    let euro = $('#euro');

    if (fiatParam === null){ // Default pounds
        pound.removeClass("btn-outline-primary");
        pound.addClass("btn-primary");
    } else if (fiatParam === "USD"){ //dollars
        dollar.removeClass("btn-outline-primary");
        dollar.addClass("btn-primary");
    } else if (fiatParam === "EUR"){ //euros
        euro.removeClass("btn-outline-primary");
        euro.addClass("btn-primary");
    } else{ //default to pounds if other value
        pound.removeClass("btn-outline-primary");
        pound.addClass("btn-primary");
    }

});
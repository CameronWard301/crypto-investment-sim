$(function () {

    let GBP = $('#GBP').val();
    let EUR = $('#EUR').val();
    let USD = $('#USD').val();
    let selected = $('#selected').val();
    let formFiat = $('#fiat');
    let available = $('#available')

    let poundBtn = $('#poundBtn');
    let dollarBtn = $('#dollarBtn');
    let euroBtn = $('#euroBtn');

    if (selected === "EUR"){
        selectEUR()
    } else if (selected === "USD"){
        selectUSD()
    } else{ //default to pounds if other value
        selectGBP();
    }

    poundBtn.click(function () {
       selectGBP()
    });

    dollarBtn.click(function () {
        selectUSD()
    });

    euroBtn.click(function () {
        selectEUR()
    });

    function selectGBP() {
        removeHighlight()
        poundBtn.addClass("btn-primary");
        poundBtn.removeClass("btn-outline-primary");
        formFiat.val("GBP")
        available.html("<b>Available Balance:</b> &#163;"+GBP)
    }

    function selectEUR() {
        removeHighlight()
        euroBtn.addClass("btn-primary");
        euroBtn.removeClass("btn-outline-primary");
        formFiat.val("EUR")
        available.html("<b>Available Balance:</b> &#8364;"+EUR)
    }

    function selectUSD() {
        removeHighlight()
        dollarBtn.addClass("btn-primary");
        dollarBtn.removeClass("btn-outline-primary");
        formFiat.val("USD")
        available.html("<b>Available Balance:</b> $"+USD)
    }

    function removeHighlight(){
        poundBtn.removeClass("btn-primary");
        poundBtn.addClass("btn-outline-primary")
        dollarBtn.removeClass("btn-primary");
        dollarBtn.addClass("btn-outline-primary")
        euroBtn.removeClass("btn-primary");
        euroBtn.addClass("btn-outline-primary")
    }

});
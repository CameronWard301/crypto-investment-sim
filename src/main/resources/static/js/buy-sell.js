$(function () {

    //Select HTML elements
    let btc_value = $("#btc-value").val();
    let eth_value = $("#eth-value").val();
    let ada_value = $("#ada-value").val();
    let eur_value = $("#eur-value").val();
    let usd_value = $("#usd-value").val();
    let coinValues = {
        "BTC": btc_value,
        "ETH": eth_value,
        "ADA": ada_value,
        "GBP": 1,
        "EUR": eur_value,
        "USD": usd_value
    }

    //select users coin amounts:
    let gbp_available = $("#gbp_available").val();
    let usd_available = $("#usd_available").val();
    let eur_available = $("#eur_available").val();
    let btc_available = $("#btc_available").val();
    let eth_available = $("#eth_available").val();
    let ada_available = $("#ada_available").val();

    let poundBtn = $('#poundBtn');
    let dollarBtn = $('#dollarBtn');
    let euroBtn = $('#euroBtn');
    let selected_fiat = $("#selected-fiat");

    let btc_calculator = $("#BTC-calculator");
    let eth_calculator = $("#ETH-calculator");
    let ada_calculator = $("#ADA-calculator");

    let calculator_input = $("#currency");
    let calculator_input_title = $("#calculator-input-title");
    let currency_symbol = $("#currency-symbol");

    //selection boxes
    let convert_from = $("#convertFrom");
    let convert_to = $("#convertTo");

    //input boxes:
    let from_coin_available = $("#fromCoinAvailable");
    let convert_from_amount_symbol = $("#convertFromAmount-symbol");
    let convert_from_amount_input = $("#convertFromAmount");
    let convert_from_amount_error = $("#convertFromAmountError");
    let to_coin_available = $("#toCoinAvailable");
    let convert_to_amount_symbol = $("#convertToAmount-symbol");
    let convert_to_amount_input = $("#convertToAmount");
    let max_amount = $("#maxAmount");

    calculator_input.on('keyup', function () {
        calculator(selected_fiat.val());
    });

    if (selected_fiat === "EUR") {
        selectEUR();
    } else if (selected_fiat === "USD") {
        selectUSD();
    } else { //default to pounds if other value
        selectGBP();
    }

    poundBtn.click(function () {
        selectGBP();
        changeCurrency("£");
        calculator("GBP");
    });

    dollarBtn.click(function () {
        selectUSD();
        changeCurrency("$");
        calculator("USD");
    });

    euroBtn.click(function () {
        selectEUR();
        changeCurrency("€");
        calculator("EUR");
    });

    let removed_from_values = [
        {value: "USD", text:"USD (dollar)"}
    ];
    let removed_to_values = [
        {value: "GBP", text:"GBP (pound)"}
    ];

    convert_from.on("change", function () {
        //Get the value selected
        let from_value = $('#convertFrom :selected')[0];
        //if there is an item previously removed
        if (removed_to_values.length >= 1) {
            //add the item back to the select list
            let item = removed_to_values.pop();
            convert_to.append($('<option>', {
                value: item.value,
                text: item.text
            }));
        }
        //remove the value selected from the other select box
        $("#convertTo option[value='" + from_value.value + "']").remove();
        removed_to_values.push(from_value);
        changeInputCurrency(from_value.value, from_coin_available, convert_from_amount_symbol, convert_from_amount_input);
        setMaxAmount(from_value.value);
        calculateExchange(convert_from_amount_input.val(), convert_from.val(), convert_to.val())
    });


    convert_to.on("change", function () {
        let to_value = $('#convertTo :selected')[0];
        if (removed_from_values.length >= 1){
            let item = removed_from_values.pop();
            convert_from.append($('<option>', {
                value: item.value,
                text: item.text
            }));
        }
        $("#convertfrom option[value='" + to_value.value + "']").remove();
        removed_from_values.push(to_value);
        changeInputCurrency(to_value.value, to_coin_available, convert_to_amount_symbol, convert_to_amount_input);
        calculateExchange(convert_from_amount_input.val(), convert_from.val(), convert_to.val())

    });

    function selectGBP() {
        removeHighlight()
        poundBtn.addClass("btn-primary");
        poundBtn.removeClass("btn-outline-primary");
        selected_fiat.val("GBP")
    }

    function selectEUR() {
        removeHighlight()
        euroBtn.addClass("btn-primary");
        euroBtn.removeClass("btn-outline-primary");
        selected_fiat.val("EUR")
    }

    function selectUSD() {
        removeHighlight()
        dollarBtn.addClass("btn-primary");
        dollarBtn.removeClass("btn-outline-primary");
        selected_fiat.val("USD")
    }

    function removeHighlight() {
        poundBtn.removeClass("btn-primary");
        poundBtn.addClass("btn-outline-primary")
        dollarBtn.removeClass("btn-primary");
        dollarBtn.addClass("btn-outline-primary")
        euroBtn.removeClass("btn-primary");
        euroBtn.addClass("btn-outline-primary")
    }

    function calculator(fiat) {
        let btc
        let eth
        let ada
        let value = calculator_input.val();
        if (fiat === "GBP") {
            btc = value * btc_value;
            eth = value * eth_value;
            ada = value * ada_value;
        } else if (fiat === "USD") {
            btc = value * (btc_value / usd_value);
            eth = value * (eth_value / usd_value);
            ada = value * (ada_value / usd_value);
        } else if (fiat === "EUR") {
            btc = value * (btc_value / eur_value);
            eth = value * (eth_value / eur_value);
            ada = value * (ada_value / eur_value);
        }
        btc_calculator.html("BTC: " + btc)
        eth_calculator.html("ETH: " + eth)
        ada_calculator.html("ADA: " + ada)
    }

    function changeCurrency(symbol) {
        calculator_input_title.html("Value in " + symbol)
        currency_symbol.html(symbol)
        calculator_input.attr("placeholder", symbol);
    }

    function changeInputCurrency(coin, coin_available, amount_symbol, amount_input){
        if (coin === "GBP"){
            coin_available.html("Amount available: " + gbp_available)
            amount_symbol.html("£")
            amount_input.attr("placeholder", "£")
        }
        else if (coin === "USD"){
            coin_available.html("Amount available: " + usd_available)
            amount_symbol.html("$")
            amount_input.attr("placeholder", "$")
        }
        else if (coin === "EUR"){
            coin_available.html("Amount available: " + eur_available)
            amount_symbol.html("€")
            amount_input.attr("placeholder", "€")
        }
        else if (coin === "BTC"){
            coin_available.html("Amount available: " + btc_available)
            amount_symbol.html("&#8383;")
            amount_input.attr("placeholder", "₿")
        }
        else if (coin === "ETH") {
            coin_available.html("Amount available: " + eth_available)
            amount_symbol.html("&#10208;")
            amount_input.attr("placeholder", "⟠")
        }
        else if (coin === "ADA") {
            coin_available.html("Amount available: " + ada_available)
            amount_symbol.html("&#x20B3")
            amount_input.attr("placeholder", '₳')
        }
    }

    convert_from_amount_input.on("keydown keyup change", function (e) {
        calculateExchange($(this).val(), convert_from.val(), convert_to.val());
        if ((Number($(this).val()) > Number(max_amount.val()))
            && e.keyCode !== 46 //keycode for delete
            && e.keyCode !== 8 //keycode for backspace
        ) {
            e.preventDefault();
            $(this).val(Number(max_amount.val()));
            convert_from_amount_error.html("Can't exceed your available balance")
            convert_from_amount_error.removeClass("invisible"); //show error
            convert_from_amount_error.addClass("visible");
        }
        else if ((Number($(this).val()) < 0)
            && e.keyCode !== 46 //keycode for delete
            && e.keyCode !== 8 //keycode for backspace
        ) {
            e.preventDefault();
            $(this).val(0);
            convert_from_amount_error.html("Can't enter value less than 0")
            convert_from_amount_error.removeClass("invisible"); //show error
            convert_from_amount_error.addClass("visible");
        }
        else {
            convert_from_amount_error.removeClass("visible"); //remove error
            convert_from_amount_error.addClass("invisible");
        }
        calculateExchange($(this).val(), convert_from.val(), convert_to.val());
    })

    function calculateExchange(amount, fromCurrency, toCurrency){
        if (fromCurrency === "GBP" || fromCurrency === "USD" || fromCurrency === "EUR"){
            convert_to_amount_input.val(amount*(coinValues[toCurrency]/coinValues[fromCurrency]));
        } else {
            convert_to_amount_input.val((amount/coinValues[fromCurrency])*coinValues[toCurrency]);
        }
        if (toCurrency === "GBP" || toCurrency === "USD" || toCurrency === "EUR"){
            convert_to_amount_input.val(roundDown(convert_to_amount_input.val()))
        }
    }

    function setMaxAmount(fiat){
        if (fiat === "GBP"){
            max_amount.val(gbp_available)
        }
        else if (fiat === "USD"){
            max_amount.val(usd_available)
        }
        else if (fiat === "EUR"){
            max_amount.val(eur_available)
        }
        else if (fiat === "BTC"){
            max_amount.val(gbp_available)
        }
        else if (fiat === "ETH"){
            max_amount.val(eth_available)
        }
        else if (fiat === "ADA"){
            max_amount.val(ada_available)
        }
    }

    function roundDown(num) {
        const re = new RegExp('^-?\\d+(?:\.\\d{0,' + (2 || -1) + '})?');
        return num.toString().match(re)[0];
    }

    calculator("GBP"); //calculate initial values on page load
    changeInputCurrency("GBP", from_coin_available, convert_from_amount_symbol, convert_from_amount_input)
    changeInputCurrency("USD", to_coin_available, convert_to_amount_symbol, convert_to_amount_input)
    max_amount.val(gbp_available)
})
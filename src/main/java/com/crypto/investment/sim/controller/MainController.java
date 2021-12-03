package com.crypto.investment.sim.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class MainController {

    @GetMapping("/")
    public String getIndex() {
        return "start";
    }

    @GetMapping("/markets")
    public String getMarkets(@RequestParam(value="fiat", required = false) Optional<Integer> id, Model model) {
        if (id.isEmpty()){
            //If no parameter show default markets in £££
            model.addAttribute("fiat", 1508);
        } else if (id.get() == 1505){
            //Show markets in $$$
            model.addAttribute("fiat", 1505);
        } else if (id.get() == 1506){
            //Show markets in €€€
            model.addAttribute("fiat", 1506);
        } else{
            //If a different param value is passed fall back on £££
            model.addAttribute("fiat", 1508);
        }
        return "markets";
    }
}

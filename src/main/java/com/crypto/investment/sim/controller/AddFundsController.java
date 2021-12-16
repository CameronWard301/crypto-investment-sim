package com.crypto.investment.sim.controller;

import com.crypto.investment.sim.model.User;
import com.crypto.investment.sim.model.UserAddFunds;
import com.crypto.investment.sim.repos.CoinRepository;
import com.crypto.investment.sim.repos.UserRepository;
import com.crypto.investment.sim.validator.AddFundsValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
public class AddFundsController {


    @InitBinder
    protected void initBinder(WebDataBinder binder){
        binder.addValidators(new AddFundsValidator());
    }

    @Autowired
    public UserRepository userRepo;

    @Autowired
    public CoinRepository coinRepo;


    @RequestMapping("/doAddRemoveCurrency")
    public String addRemove(@Valid @ModelAttribute UserAddFunds userAddFunds, BindingResult result, @RequestParam(value="fiat", required = false) Optional<Integer> id, Model model, HttpSession session, HttpServletRequest request) {
        User USER_SESSION = (User) session.getAttribute("USER_SESSION");
        if (USER_SESSION == null){
            return "redirect:/login";
        }

        model.addAttribute("GBP", USER_SESSION.getGBP());
        model.addAttribute("USD", USER_SESSION.getUSD());
        model.addAttribute("EUR", USER_SESSION.getEUR());

        model.addAttribute("userAddFunds", new UserAddFunds());



        if (id.isEmpty()) {
            //£££
            if (result.hasErrors()){
                model.addAttribute("errors", result.getFieldError("value"));
                return "redirect:addRemoveCurrency?fiat=";
            }
            model.addAttribute("sign", "&#163;");
            model.addAttribute("currency", USER_SESSION.getGBP());
            USER_SESSION.setGBP((float) USER_SESSION.getGBP()+userAddFunds.getValue());
            model.addAttribute("fiat","");
            userRepo.save(USER_SESSION);
            session.setAttribute("USER_SESSION", USER_SESSION);
            return "redirect:addRemoveCurrency";
        } else if (id.get() == 1505) {
            //$$$
            if (result.hasErrors()){
                model.addAttribute("errors", result.getFieldError("value"));
                return "user/addRemoveCurrency";
            }
            model.addAttribute("sign", "$");
            model.addAttribute("currency", USER_SESSION.getUSD());
            USER_SESSION.setUSD((float) USER_SESSION.getUSD()+userAddFunds.getValue());
            model.addAttribute("fiat","1505");
            userRepo.save(USER_SESSION);
            session.setAttribute("USER_SESSION", USER_SESSION);
            return "redirect:addRemoveCurrency?fiat=1505";
        } else if (id.get() == 1506) {
            //€€€
            if (result.hasErrors()){
                model.addAttribute("errors", result.getFieldError("value"));
                return "redirect:addRemoveCurrency?fiat=1506";
            }
            model.addAttribute("sign", "&#8364;");
            model.addAttribute("currency", USER_SESSION.getEUR());
            USER_SESSION.setEUR((float) USER_SESSION.getEUR()+userAddFunds.getValue());
            model.addAttribute("fiat","1506");
            userRepo.save(USER_SESSION);
            session.setAttribute("USER_SESSION", USER_SESSION);
            return "redirect:addRemoveCurrency?fiat=1506";
        } else {
            //£££
            if (result.hasErrors()){
                model.addAttribute("errors", result.getFieldError("value"));
                return "redirect:addRemoveCurrency?fiat=";
            }
            model.addAttribute("sign", "&#163;");
            model.addAttribute("currency", USER_SESSION.getGBP());
            USER_SESSION.setGBP((float) USER_SESSION.getGBP()+userAddFunds.getValue());
            model.addAttribute("fiat","");
            userRepo.save(USER_SESSION);
            session.setAttribute("USER_SESSION", USER_SESSION);
            return "redirect:addRemoveCurrency";
        }
    }

    @RequestMapping("/addRemoveCurrency")
    public String currency(@ModelAttribute UserAddFunds userAddFunds, @RequestParam("fiat") Optional<Integer> id, Model model, HttpSession session, HttpServletRequest request){
        User USER_SESSION = (User) session.getAttribute("USER_SESSION");
        if (USER_SESSION == null){
            return "redirect:/login";
        }
        model.addAttribute("GBP", USER_SESSION.getGBP());
        model.addAttribute("USD", USER_SESSION.getUSD());
        model.addAttribute("EUR", USER_SESSION.getEUR());

        model.addAttribute("userAddFunds", new UserAddFunds());

        if (id.isEmpty()){
            //£££
            model.addAttribute("sign", "&#163;");
            model.addAttribute("currency", USER_SESSION.getGBP());
            model.addAttribute("fiat","");
        } else if (id.get() == 1505){
            //$$$
            model.addAttribute("sign", "$");
            model.addAttribute("currency", USER_SESSION.getUSD());
            model.addAttribute("fiat","1505");

        } else if (id.get() == 1506){
            //€€€
            model.addAttribute("sign", "&#8364;");
            model.addAttribute("currency", USER_SESSION.getEUR());
            model.addAttribute("fiat","1506");

        } else{
            //£££
            model.addAttribute("sign", "&#163;");
            model.addAttribute("currency", USER_SESSION.getGBP());
            model.addAttribute("fiat","");
        }

        return "user/addRemoveCurrency";
    }
}

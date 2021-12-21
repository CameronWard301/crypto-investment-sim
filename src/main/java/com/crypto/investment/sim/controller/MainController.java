package com.crypto.investment.sim.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class MainController {

    @GetMapping("/")
    public String getIndex() {
        return "start";
    }

    /**
     * Checks the current session for the user. The JQuery JS file uses this to swap the login button for the logout button
     * @param session The current HttpSession - the user variable will be present to indicate they are logged in.
     * @return Boolean Ture if user is logged in, False if not
     */
    @GetMapping("/user/isLoggedIn")
    @ResponseBody //Instead of returning a JSP view it sends data directly back to the client calling it
    public Boolean checkAuth(HttpSession session){
        Object USER_SESSION = session.getAttribute("USER_SESSION");
        return USER_SESSION != null; //User not logged in if null
    }

    @GetMapping("/markets")
    public String getMarkets(@RequestParam(value="fiat", required = false) Optional<String> fiat, Model model) {
        if (fiat.isEmpty()){
            //If no parameter show default markets in £££
            model.addAttribute("fiat", "GBP");
        } else if (fiat.get().equals("USD")){
            //Show markets in $$$
            model.addAttribute("fiat", "USD");
        } else if (fiat.get().equals("EUR")){
            //Show markets in €€€
            model.addAttribute("fiat", "EUR");
        } else{
            //If a different param value is passed fall back on £££
            model.addAttribute("fiat", "GBP");
        }
        return "markets";
    }

    /**
     * Destroys the user's session. Send all of these requests to the endpoint /invalidate/session
     * @param request the users request will contain their session info
     * @return redirect back to the home page
     */
    @GetMapping("/invalidate/session")
    public String destroySession(HttpServletRequest request) {
        //invalidate the session , this will clear the data from configured database
        request.getSession().invalidate();
        return "redirect:/";
    }
}

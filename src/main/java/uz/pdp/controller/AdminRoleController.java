package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.pdp.service.LoginService;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
@RequestMapping
public class AdminRoleController {
    @Autowired
    LoginService loginService;
    static String role = "USER";

    @GetMapping("/ksjf")
    public String test(HttpServletRequest request) {
        UUID uuid = loginService.sessionGetEmail(request, role);
        return null;
    }



    @GetMapping("/dasfdsf")
    public String test1(HttpServletRequest request) {
        UUID uuid = loginService.sessionGetEmail(request, role);
        if(uuid!=null){

        }
        return null;
    }
}

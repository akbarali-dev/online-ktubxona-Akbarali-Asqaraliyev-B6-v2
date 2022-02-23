package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uz.pdp.dto.UserDto;
import uz.pdp.model.Role;
import uz.pdp.service.LoginService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    LoginService loginService;

    @GetMapping
    public String login( HttpServletRequest request,
            Model model,
            @RequestParam(required = false, name = "emailOrPhoneNumber") String emailOrPhoneNumber,
            @RequestParam(required = false, name = "password") String password
    ) {
        UserDto userDto = loginService.roles(emailOrPhoneNumber, password);
        if (userDto != null) {
            List<Role> roles = userDto.getRoles();
            HttpSession session = request.getSession();
            session.setAttribute("username", userDto.getEmail());
            for (Role role : roles) {

                if (role.getName().equals("ADMIN")) {
                    return "redirect:/courses/test";
                } else if (role.getName().equals("MENTOR")) {
                    return "redirect:/courses/test";
                } else if (role.getName().equals("USER")) {
                    return "redirect:/courses/test";
                }
            }
        } else {
            model.addAttribute("error", "login or password error");
            return "login";
        }
        return null;
    }

    @GetMapping("/logout/{exit}")
    public String logout(HttpServletRequest request,
                         @PathVariable(required = false) String exit) {
        if(exit.equals("exit")) {
            HttpSession session = request.getSession(false);
            session.invalidate();
        }
        return "login";
    }
}

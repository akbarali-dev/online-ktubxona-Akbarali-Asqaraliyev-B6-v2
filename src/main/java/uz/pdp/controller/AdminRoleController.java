
package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uz.pdp.dao.AdminDao;
import uz.pdp.service.LoginService;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class AdminRoleController {
    @Autowired
    LoginService loginService;

    @Autowired
    AdminDao adminDao;
    static String role = "ADMIN";

    @GetMapping
    public String mainPage(
            Model model,
            HttpServletRequest request) {
        UUID uuid = loginService.sessionGetEmail(request, role);

        if (uuid == null) {
            model.addAttribute("firstPassword", "Enter the password first");
            return "/login";
        }
        return "admin-panel";
    }

    @GetMapping("/messages")
    public String messages(HttpServletRequest request, Model model) {
        UUID uuid = loginService.sessionGetEmail(request, role);

        if (uuid == null) {
            model.addAttribute("firstPassword", "Enter the password first");
            return "/login";
        }
        model.addAttribute("all", "all");
        model.addAttribute("messages", adminDao.getAllMessage());
        return "admin-answer-mentor";
    }

    @GetMapping( "/answer/{messageId}")
    public String answer(@PathVariable(required = false) String messageId,
                Model model
    ){
        model.addAttribute("all", "select");
        model.addAttribute("messages", adminDao.getMessageById(UUID.fromString(messageId)));
        return "admin-answ er-mentor";
    }




}

package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.pdp.dto.CourseDto;
import uz.pdp.service.MentorService;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/mentor")
public class MentorController {
    @Autowired
    MentorService mentorService;
   // @PostMapping
//    public String addCourseMentor(Model model){
//
//    }
}

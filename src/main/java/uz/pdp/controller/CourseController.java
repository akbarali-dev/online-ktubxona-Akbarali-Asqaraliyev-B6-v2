package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.dao.CourseDao;
import uz.pdp.dto.CourseDto;
import uz.pdp.dto.MentorCourseDto;
import uz.pdp.service.CourseService;
import uz.pdp.service.ModuleService;
import uz.pdp.service.UserService;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    CourseService courseService;
    @Autowired
    ModuleService moduleService;

    @Autowired
    UserService userService;

    @Autowired
    CourseDao courseDao;


    @GetMapping("/test")
    public String test(
            @RequestParam(required = false, name = "currentPage") Integer currentPage,
            @RequestParam(required = false, name = "condition") String condition,
            @RequestParam(required = false, name = "text") String text,
            Model model) {
        List<CourseDto> allCourses;
        int interval = 6;
        if (condition == null || condition.equals("main")) {
            condition = "main";
        }
        int size = courseService.courseSize(text, condition);
        model.addAttribute("size", size);
        model.addAttribute("size1", (size % interval == 0 ?
                size / interval : size / interval + 1));
        if (currentPage == null) {
            currentPage = 1;
        }
        if (text != null) {
            condition = text;
        }

        allCourses = courseService.getAllCoursesByPage(interval, currentPage, condition, text);
        if (text != null && size == 0) {
            model.addAttribute("searchGoogle", "searchGoogle");
        }
        model.addAttribute("courseList", allCourses);
        model.addAttribute("interval", interval);
        model.addAttribute("condition", condition);
        model.addAttribute("backType", "main");


        return "taskview-tasks";
    }


    @GetMapping
    public String getAllCourses(Model model,
                                @RequestParam(required = false, name = "currentPage") Integer currentPage,  // TODO: 2/20/2022 add request param
                                @RequestParam(required = false, name = "backType") String backType,  // TODO: 2/20/2022 add request param
                                @RequestParam(required = false, name = "text") String text  // TODO: 2/20/2022 add request param
    ) {

        if (currentPage == null) {
            currentPage = 1;
        }
        int interval = 6;
        int size = courseService.getAllCourseTableCount(text);

        int size2 = ((size % interval == 0) ? size / interval : size / interval + 1);


        List<CourseDto> allCourses = courseService.getAllCourses(interval, currentPage, text);
        model.addAttribute("courseList", allCourses);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("size2", size2);
        model.addAttribute("size", size);
        return "view-courses";
    }

    @GetMapping("/courseAllData/{id}")
    public String getCourseByIdWithAuthor(@PathVariable(required = false) String id, Model model,
                                          @RequestParam(required = false, name = "backType") String backType
    ) {
        backType = backType;
        UUID id1 = UUID.fromString(id);
        CourseDto courseById = courseService.getCourseById(id1);
        model.addAttribute("selectCourse", courseById);
        model.addAttribute("backType", backType);
        return "view-select-course";
    }

    @GetMapping("/{id}")
    public String getCourseById(@PathVariable(required = false) String id, Model model) {
        UUID id1 = UUID.fromString(id);
        CourseDto courseById = courseService.getCourseById(id1);
        model.addAttribute("authors", userService.getAllMentors());
        model.addAttribute("selectCourse", courseById);
        return "course-form";
    }

    @GetMapping("/addCourse")
    public String getCourse(Model model) {
        model.addAttribute("authors", userService.getAllMentors());
        return "course-form";
    }

    @PostMapping
    public String addCourse(@ModelAttribute("courses") CourseDto courseDto, Model model) {
        String str = courseService.addCourse(courseDto);
        model.addAttribute("message", str);
        return "redirect:/courses";
    }

    @DeleteMapping("/{id}")
    public String deleteCourse(@PathVariable String id, Model model) {
        UUID id1 = UUID.fromString(id);
        String str = courseService.deleteCourse(id1);
        model.addAttribute("message", str);
        return "redirect:/courses";
    }

    @GetMapping("/course-table")
    public String mentorCourses(Model model) {
        model.addAttribute("courses", courseDao.getAllCourse());
        return "course-table";
    }

    @GetMapping("/select-mentor")
    public String getAllMentor(Model model) {
        model.addAttribute("mentors", userService.getAllMentors());
        return "add-new-course";
    }

    @PostMapping("/add-course")
    public String addNewCourse(
            @RequestParam("file") MultipartFile file,
            @ModelAttribute("courses-table") MentorCourseDto courseDto) {
        if (!file.isEmpty()) {
            try {
                byte[] fileBytes = file.getBytes();
                courseDao.addNewCourseModuleLesson(courseDto, fileBytes);
                return "redirect:/courses/add-course";
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "redirect:/courses/select-mentor";
    }


}

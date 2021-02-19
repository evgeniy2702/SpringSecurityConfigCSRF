package zhurenko.ua.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import zhurenko.ua.model.User;
import zhurenko.ua.service.UserService;

import javax.persistence.GeneratedValue;

@Controller
public class SecurityController {

    private final UserService userService;

    public SecurityController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/login")
    public String login(@RequestParam(name = "error", required = false)
                                    Boolean error, Model model){
        if(Boolean.TRUE.equals(error)){
            model.addAttribute("error" , true);
        }
        return "signin";
    }

    @GetMapping("/registration")
    public String registrationNew(){
        return"signup";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute User user){
        userService.save(user);
        return "redirect:/login";
    }

//    @GetMapping("/role{user}")
//    public String role(@PathVariable User user){
//
//    }
}

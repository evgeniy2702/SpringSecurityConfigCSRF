package zhurenko.ua.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import zhurenko.ua.model.Owner;
import zhurenko.ua.service.OwnerService;

@Controller
public class SecurityController {

    private final OwnerService ownerService;

    public SecurityController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @RequestMapping({"/login","/signin"})
    public String login(@RequestParam(name = "error", required = false)
                                    Boolean error, Model model){
        if(Boolean.TRUE.equals(error)){
            model.addAttribute("error" , true);
        }
        return "signin";
    }

    @PostMapping("/logout")
    public String logout(){
        return "signin";
    }

    @GetMapping("/registration")
    public String registrationNew(){
        return"signup";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute Owner owner){
        System.out.println(owner);
        ownerService.saveOwner(owner);
        return "redirect:/login";
    }

//    @GetMapping("/role{user}")
//    public String role(@PathVariable User user){
//
//    }
}

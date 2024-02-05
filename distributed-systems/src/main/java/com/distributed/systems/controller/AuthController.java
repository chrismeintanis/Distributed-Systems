package com.distributed.systems.controller;

import com.distributed.systems.dto.StaffDto;
import com.distributed.systems.dto.UserDto;
import com.distributed.systems.entity.Staff;
import com.distributed.systems.entity.User;
import com.distributed.systems.service.StaffService;
import com.distributed.systems.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class AuthController {

    private UserService userService;
    private StaffService staffService;


    public AuthController(UserService userService, StaffService staffService){
        this.userService=userService;
        this.staffService=staffService;
    }

    //home page handler
    @GetMapping("/index")
    public String home(){
        return "index";
    }

    //login request handler
    @GetMapping("/login")
    public String login(){
        return "login";
    }


    //handler method to handle user registration form request
    @GetMapping("/register")
    public String register(Model model){
        UserDto user = new UserDto();
        model.addAttribute("user",user);
        return "register";
    }

    @GetMapping("/register/staff")
    public String register_staff(Model model){
        StaffDto staff = new StaffDto();
        model.addAttribute("staff",staff);
        return "register_staff";
    }

    @PostMapping("/register/staff/save")
    public String registration_staff(@Valid @ModelAttribute("staff") StaffDto staffDto, BindingResult result, Model model){

        Staff existingStaff = staffService.findStaffByEmail(staffDto.getEmail());
        if(existingStaff!=null && existingStaff.getEmail()!=null && !existingStaff.getEmail().isEmpty()){
            result.rejectValue("email",null,"There is already a staff member registered with this email");
        }

        if(result.hasErrors()){
            model.addAttribute("staff",staffDto);
            return "/register/staff";
        }

        staffService.saveStaff(staffDto);
        return "redirect:/register/staff?success";
    }

    //handler method to handle user registration form submit request
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto, BindingResult result, Model model){

        User existingUser = userService.findUserByUsername(userDto.getUsername());
        if(existingUser!=null && existingUser.getUsername()!=null && !existingUser.getUsername().isEmpty()){
            result.rejectValue("email",null,"There is already a user registered with this email");
        }

        if(result.hasErrors()){
            model.addAttribute("user",userDto);
            return "/register";
        }

        userService.saveUser(userDto);
        return "redirect:/register?success";
    }



}

package ua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import ua.entity.User;
import ua.service.UserService;
import ua.validator.UserValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import java.security.Principal;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@SessionAttributes(names="userForm")
public class IndexController {

	@Autowired
	private UserService userService;

	@InitBinder("userForm")
	protected void initBinder(WebDataBinder binder){
		binder.setValidator(new UserValidator(userService));
	}

	@RequestMapping("/")
	public String index(Principal principal){
	//    principal.getName();
		return "user/index";
	}

	@RequestMapping("/admin")
	public String admin(){
		return "admin/admin";
	}
	@RequestMapping("/user")
	public String user(){
		return "user/user";
	}

	@RequestMapping("/login")
	public String login(){
		return "user/login";
	}

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/user/login";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
    }

	@RequestMapping("/registration")
	public String registration(Model model){
		model.addAttribute("userForm", new User());
		return "user/registration";
	}

	@RequestMapping(value="/registration", method=POST)
	public String registration(@ModelAttribute("userForm") @Valid User user, BindingResult br, SessionStatus status, Model model){

        if (br.hasErrors()) {
            return "user/registration";
        }
        userService.save(user);
        status.setComplete();
        return "redirect:user/index";
	}
}

package ua.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import ua.entity.Order;
import ua.entity.User;
import ua.service.OrderService;
import ua.service.UserService;
import ua.validator.UserValidator;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static ua.util.ParamBuilder.buildParams;

/**
 * Created by Admin on 1/24/2017.
 */
@Controller
@RequestMapping("/admin/user")
@SessionAttributes(names="user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    OrderService orderService;
    @ModelAttribute("user")
    public User getForm(){
        return new User();
    }
    @InitBinder("user")
    protected void initBinder(WebDataBinder binder){
        binder.setValidator(new UserValidator(userService));
    }
    @RequestMapping
    public String show(Model model, @PageableDefault Pageable pageable){
        model.addAttribute("users",userService.findAll(pageable));
        return "admin/user";
    }
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable int id, @PageableDefault Pageable pageable){
        userService.delete(id);
        return "redirect:/admin/user"+buildParams(pageable);
    }
    @RequestMapping("/find/{id}")
    public String findById(@PathVariable int id){
        userService.findOne(id);
        return "admin/user";
    }
    @RequestMapping(method=POST)
    public String save(@ModelAttribute("user") @Valid User user, BindingResult br, @RequestParam String action, SessionStatus status,Model model, @PageableDefault Pageable pageable){
        switch (action.toLowerCase()){
            case "save":
                if(br.hasErrors()){
                    model.addAttribute("users",userService.findAll(pageable));
                    return "admin/user";
                }
                userService.save(user);
                status.setComplete();
                return "redirect:/admin/user";
            case "search":
                User founded =userService.findByUsername(user.getUsername());
                if (founded==null){
                    return "redirect:/admin/user"+buildParams(pageable);
                }
                model.addAttribute("user",founded);
                return "redirect:/admin/user"+buildParams(pageable);
        }

        return "redirect:/admin/user"+buildParams(pageable);
    }
    @RequestMapping("/order/{id}")
    public String order(@PathVariable int id,Model model){
        User user=userService.findOne(id);
        model.addAttribute("user",user);
        List<Order> orders=user.getOrders();
        model.addAttribute("orders", orders);
        return "admin/userOrders";
    }
    @RequestMapping("/delete/order/{id}/{orderId}")
    public String deleteOrder(@PathVariable int orderId,@PathVariable int id){
        orderService.delete(orderId);
        return "redirect:/admin/user/order/{id}";
    }
    @RequestMapping("/update/{id}")
    public String update(@PathVariable int id,Model model, @PageableDefault Pageable pageable){
        model.addAttribute("user",userService.findOne(id));
        model.addAttribute("users", userService.findAll(pageable));
        return "admin/user";
    }

    @RequestMapping("/cancel")
    public String cancel(SessionStatus status, @PageableDefault Pageable pageable){
        status.setComplete();
        return "redirect:/admin/user"+buildParams(pageable);
    }
}

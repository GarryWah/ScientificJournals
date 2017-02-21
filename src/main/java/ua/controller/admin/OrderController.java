package ua.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import ua.entity.Order;
import ua.service.OrderService;

/**
 * Created by Admin on 1/22/2017.
 */
@Controller
@RequestMapping("/admin/orders")
@SessionAttributes(names="forms")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @ModelAttribute("form")
    public Order getForm(){
        return new Order();
    }
    @RequestMapping
    public String show(Model model){
        model.addAttribute("orders",orderService.findAll());
        return "order";
    }
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable int id){
        orderService.delete(id);
        return "redirect:/admin/order";
    }
    @RequestMapping("/find/{id}")
    public String findById(@PathVariable int id){
        orderService.findOne(id);
        return "order";
    }
    public String update(@PathVariable int id,Model model){
        model.addAttribute("form",orderService.findAll());
        return "order";
    }
}

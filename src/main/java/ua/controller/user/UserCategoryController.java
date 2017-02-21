package ua.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ua.entity.*;
import ua.service.*;
import ua.validator.JournalFormValidator;

import java.security.Principal;
import java.util.Date;
import java.util.List;

/**
 * Created by Admin on 2/11/2017.
 */
@Controller
@RequestMapping("/user/category")
@SessionAttributes(names="form")
public class UserCategoryController {
    @Autowired
    private CategoryService categoryService;


    @RequestMapping
    public String show(Model model, @PageableDefault Pageable pageable){
        model.addAttribute("categories",categoryService.findAll(pageable));
        return "user/category";
    }

    @RequestMapping("/titles/{id}")
    public String showTitlesFromCategory(@PathVariable int id,Model model){
        Category category = categoryService.loadedCategory(id);
        model.addAttribute("category", category);
        List<JournalTitle> titles = category.getTitles();
        model.addAttribute("titles", titles);
        return "user/titlesInCategory";
    }

    /**
     * Created by Admin on 2/11/2017.
     */
    @Controller
    @RequestMapping("/user/title/show/journal/{id}")
    @SessionAttributes(names="Volume")
    public static class UserListOfVolumesController {
        @Autowired
        private JournalTitleService journalTitleService;
        @Autowired
        private UserService userService;
        @Autowired
        private JournalService journalService;
        @Autowired
        private OrderService orderService;
        @InitBinder("Volume")
        protected void initBinder(WebDataBinder binder){
            binder.setValidator(new JournalFormValidator(journalService));
        }
        @RequestMapping
        public String show(@PathVariable int id, Model model){
            JournalTitle journalTitle = journalTitleService.loadedTitle(id);
            model.addAttribute("journalTitle", journalTitle);
            List<Journal> journals = journalTitle.getJournals();
            model.addAttribute("journals", journals);
            return "/user/volumes";
        }
        @RequestMapping("/{journalId}")
        public String buy(@PathVariable int id,@PathVariable int journalId, Model model,Principal principal){
            JournalTitle journalTitle = journalTitleService.loadedTitle(id);
            List<Journal> journals = journalTitle.getJournals();
            User user=userService.findByUsername(principal.getName());
            Order order=new Order();
            order.setJournal(journalService.findOne(journalId));
            order.setUser(user);
            order.setDateOfPay(new Date());
            orderService.save(order);
            user.getOrders().add(order);
           // problems with authentification if with this: userService.save(user);
            show(id, model);
            return "/user/volumes";
        }

    /*    @RequestMapping("/confirm")
        public String confirm(@PathVariable int id, Model model,Principal principal){
            User user=userService.findByUsername(principal.getName());
            model.addAttribute("user",user);
            return "redirect:/user/volumes";
        }*/

    }
}

package ua.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ua.editor.CategoryEditor;
import ua.entity.Category;
import ua.service.CategoryService;
import ua.service.JournalTitleService;
import ua.validator.JournalTitleValidator;

/**
 * Created by Admin on 2/11/2017.
 */

@Controller
@RequestMapping("/user/title")
@SessionAttributes(names = "UserTitle")
public class UserTitleController {

    @Autowired
    private JournalTitleService journalTitleService;
    @Autowired
    private CategoryService categoryService;

    @InitBinder("UserTitle")
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new JournalTitleValidator(journalTitleService));
        binder.registerCustomEditor(Category.class, new CategoryEditor(categoryService));
    }
    @RequestMapping
    public String show(Model model, @PageableDefault Pageable pageable) {
        model.addAttribute("titles", journalTitleService.findAll(pageable));
        model.addAttribute("categories", categoryService.findAll());
        return "user/title";
    }

}




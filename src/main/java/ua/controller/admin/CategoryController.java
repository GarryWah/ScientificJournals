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
import ua.entity.Category;
import ua.service.CategoryService;
import ua.service.JournalTitleService;
import ua.validator.CategoryValidator;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static ua.util.ParamBuilder.buildParams;

/**
 * Created by Admin on 1/22/2017.
 */
@Controller
@RequestMapping("/admin/category")
@SessionAttributes(names="form")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private JournalTitleService journalTitleService;
    @ModelAttribute("form")
    public Category getForm(){
        return new Category();
    }
    @InitBinder("form")
    protected void initBinder(WebDataBinder binder){
        binder.setValidator(new CategoryValidator(categoryService));
    }
    @RequestMapping
    public String show(Model model, @PageableDefault Pageable pageable){
        model.addAttribute("categories",categoryService.findAll(pageable));
        return "admin/category";
    }
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable int id, @PageableDefault Pageable pageable){
        categoryService.delete(id);
        return "redirect:/admin/category"+buildParams(pageable);
    }
    @RequestMapping("/update/{id}")
    public String update(@PathVariable int id,Model model, @PageableDefault Pageable pageable){
        model.addAttribute("form",categoryService.findOne(id));
        model.addAttribute("categories", categoryService.findAll(pageable));
        return "/admin/category";
    }
    @RequestMapping("/cancel")
    public String cancel(SessionStatus status, @PageableDefault Pageable pageable){
        status.setComplete();
        return "redirect:/admin/category"+buildParams(pageable);
    }
    @RequestMapping(method=POST)
    public String save(@ModelAttribute("form") @Valid Category category, BindingResult br, SessionStatus status,Model model, @PageableDefault Pageable pageable){
        if(br.hasErrors()){
            model.addAttribute("categories",categoryService.findAll(pageable));
            return "/admin/category";
        }
        categoryService.save(category);
        status.setComplete();
        return "redirect:/admin/category"+buildParams(pageable);
    }

}

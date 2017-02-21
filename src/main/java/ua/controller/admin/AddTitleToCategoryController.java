package ua.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import ua.editor.CategoryEditor;
import ua.entity.Category;
import ua.entity.JournalTitle;
import ua.service.CategoryService;
import ua.service.JournalTitleService;
import ua.validator.JournalTitleValidator;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by Admin on 1/29/2017.
 */
@Controller
@RequestMapping("/admin/category/show/title/{id}")
@SessionAttributes(names="TitleToCategory")
public class AddTitleToCategoryController {
    @Autowired
    private JournalTitleService journalTitleService;
    @Autowired
    private CategoryService categoryService;
    @ModelAttribute("TitleToCategory")
    public JournalTitle getForm(){
        return new JournalTitle();
    }
    @InitBinder("TitleToCategory")
    protected void initBinder(WebDataBinder binder){
        binder.setValidator(new JournalTitleValidator(journalTitleService));
        binder.registerCustomEditor(Category.class, new CategoryEditor(categoryService));
    }


    @RequestMapping
    public String show(@PathVariable int id,Model model){
        Category category = categoryService.loadedCategory(id);
        model.addAttribute("category", category);
        List<JournalTitle> titles = category.getTitles();
        model.addAttribute("titles", titles);
        return "/admin/showJournalTitlesByCategory";
    }
    @RequestMapping(method=POST)
    public String addTitle(@ModelAttribute("TitleToCategory") @Valid JournalTitle journalTitle, BindingResult br, SessionStatus status, Model model, @PathVariable int id){
        if(br.hasErrors()){
            show(id,model);
            return "/admin/showJournalTitlesByCategory";
        }
        journalTitle.setCategory(categoryService.findOne(id));
        journalTitleService.save(journalTitle);
        status.setComplete();
        return "redirect:/admin/category/show/title/{id}";
    }
    @RequestMapping("/delete/{titleId}")
    public String deleteTitle(@PathVariable int titleId){
        journalTitleService.delete(titleId);
        return "redirect:/admin/category/show/title/{id}";
    }
    @RequestMapping("/update/{titleId}")
    public String update(@PathVariable int titleId,@PathVariable int id,Model model){
        model.addAttribute("TitleToCategory",journalTitleService.loadedTitle(titleId));
        show(id,model);
        return "/admin/showJournalTitlesByCategory";
    }
    @RequestMapping("/cancel")
    public String cancel(@PathVariable int id,SessionStatus status){
        status.setComplete();
        return "redirect:/admin/category/show/title/{id}";
    }

}

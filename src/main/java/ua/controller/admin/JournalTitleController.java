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
import ua.editor.CategoryEditor;
import ua.entity.*;
import ua.service.CategoryService;
import ua.service.JournalTitleService;
import ua.validator.JournalTitleValidator;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static ua.util.ParamBuilder.buildParams;

/**
 * Created by Admin on 1/22/2017.
 */
@Controller
@RequestMapping("/admin/title")
@SessionAttributes(names="Title")
public class JournalTitleController {
    @Autowired
    private JournalTitleService journalTitleService;
    @Autowired
    private CategoryService categoryService;
    @ModelAttribute("Title")
    public JournalTitle getForm(){
        return new JournalTitle();
    }
    @InitBinder("Title")
    protected void initBinder(WebDataBinder binder){
        binder.setValidator(new JournalTitleValidator(journalTitleService));
        binder.registerCustomEditor(Category.class, new CategoryEditor(categoryService));
    }

    @RequestMapping
    public String show(Model model, @PageableDefault Pageable pageable){
        model.addAttribute("titles",journalTitleService.findAll(pageable));
        model.addAttribute("categories", categoryService.findAll());
        return "admin/title";
    }
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable int id, @PageableDefault Pageable pageable){
        journalTitleService.delete(id);
        return "redirect:/admin/title"+buildParams(pageable);
    }
    @RequestMapping(method=POST)
    public String save(@ModelAttribute("Title") @Valid JournalTitle title, BindingResult br, SessionStatus status,Model model, @PageableDefault Pageable pageable){
        if(br.hasErrors()){
            model.addAttribute("titles",journalTitleService.findAll());
            model.addAttribute("categories", categoryService.findAll());
            return "admin/title";
        }
        journalTitleService.save(title);
        status.setComplete();
        return "redirect:/admin/title"+buildParams(pageable);
    }
    @RequestMapping("/update/{id}")
    public String update(@PathVariable int id,Model model, @PageableDefault Pageable pageable){
        model.addAttribute("Title",journalTitleService.loadedTitle(id));
        show(model,pageable);
        return "/admin/title";
    }

    @RequestMapping("/cancel")
    public String cancel(SessionStatus status, @PageableDefault Pageable pageable){
        status.setComplete();
        return "redirect:/admin/title"+buildParams(pageable);
    }
}

package ua.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import ua.dto.JournalForm;
import ua.editor.CategoryEditor;
import ua.editor.JournalTitleEditor;
import ua.entity.Category;
import ua.entity.Journal;
import ua.entity.JournalTitle;
import ua.service.CategoryService;
import ua.service.JournalService;
import ua.service.JournalTitleService;
import ua.validator.JournalFormValidator;


import javax.validation.Valid;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by Admin on 1/28/2017.
 */
@Controller
@RequestMapping("/admin/title/show/journal/{id}")
@SessionAttributes(names="Add")
public class AddVolumeController {
    @Autowired
    private JournalTitleService journalTitleService;
    @Autowired
    private JournalService journalService;
    @Autowired
    private CategoryService categoryService;

    @ModelAttribute("Add")
    public JournalForm getForm(){
        return new JournalForm();
    }
    @InitBinder("Add")
    protected void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(JournalTitle.class, new JournalTitleEditor(journalTitleService));
        binder.registerCustomEditor(Category.class, new CategoryEditor(categoryService));
        binder.setValidator(new JournalFormValidator(journalService));
    }
    @RequestMapping
    public String show(@PathVariable int id,Model model){
        JournalTitle journalTitle = journalTitleService.loadedTitle(id);
        model.addAttribute("journalTitle", journalTitle);
        List<Journal> journals = journalTitle.getJournals();
        model.addAttribute("journals", journals);
        return "/admin/showJournals";
    }
    @RequestMapping(method=POST)
    public String addVolume(@ModelAttribute("Add") @Valid JournalForm journalForm, BindingResult br, Model model, SessionStatus status, @PathVariable int id){
        if(br.hasErrors()){
            show(id,model);
            return "/admin/showJournals";
        }
        JournalTitle journalTitle = journalTitleService.loadedTitle(id);
        journalForm.setTitle(journalTitle);
        journalService.save(journalForm);
        status.setComplete();
        return "redirect:/admin/title/show/journal/{id}";
    }
    @RequestMapping("/delete/{journalId}")
    public String deleteVolume(@PathVariable int journalId){
        journalService.delete(journalId);
        return "redirect:/admin/title/show/journal/{id}";
    }
    @RequestMapping("/update/{journalId}")
    public String update(@PathVariable int journalId,@PathVariable int id,Model model){
        JournalForm journalForm=new JournalForm();
        Journal journal=journalService.findOne(journalId);
        journalForm.setId(journal.getId());
        journalForm.setTitle(journal.getTitle());
        journalForm.setVolume(String.valueOf(journal.getVolume()));
        journalForm.setYear(String.valueOf(journal.getYear()));
        journalForm.setPrice(String.valueOf(journal.getPrice()));
        model.addAttribute("Add", journalForm);
        show(id,model);
        return "/admin/showJournals";
    }
    @RequestMapping("/cancel")
    public String cancel(@PathVariable int id,SessionStatus status){
        status.setComplete();
        return "redirect:/admin/title/show/journal/{id}";
    }



}

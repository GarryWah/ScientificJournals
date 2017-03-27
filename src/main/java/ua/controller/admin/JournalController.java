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

import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static ua.util.ParamBuilder.buildParams;

/**
 * Created by Admin on 2/15/2017.
 */
@Controller
@RequestMapping("/admin/journal")
@SessionAttributes(names = "journal")
public class JournalController {
    @Autowired
    private JournalService journalService;
    @Autowired
    private JournalTitleService journalTitleService;
    @Autowired
    private CategoryService categoryService;

    @ModelAttribute("journal")
    public JournalForm getForm() {
        return new JournalForm();
    }

    @InitBinder("journal")
    protected void initBinder(WebDataBinder binder) {

        binder.registerCustomEditor(JournalTitle.class, new JournalTitleEditor(journalTitleService));
        binder.registerCustomEditor(Category.class, new CategoryEditor(categoryService));
        binder.setValidator(new JournalFormValidator(journalService));
    }

    @RequestMapping
    public String show(Model model, @PageableDefault Pageable pageable) {
        model.addAttribute("journals", journalService.findAll(pageable));
        model.addAttribute("titles", journalTitleService.findAll());
        return "admin/journal";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable int id, @PageableDefault Pageable pageable) {
        journalService.delete(id);
        return "redirect:/admin/journal" + buildParams(pageable);
    }

    @RequestMapping("/find/{id}")
    public String findById(@PathVariable int id, @PageableDefault Pageable pageable) {
        journalService.findOne(id);
        return "admin/journal";
    }

    @RequestMapping(method = POST)
    public String save(@ModelAttribute("journal") @Valid JournalForm journalForm, BindingResult br, SessionStatus status, Model model, @PageableDefault Pageable pageable) {
        if (br.hasErrors()) {
            model.addAttribute("journals", journalService.findAll(pageable));
            model.addAttribute("titles", journalTitleService.findAll());
            return "admin/journal";
        }
        journalService.save(journalForm);
        status.setComplete();
        return "redirect:/admin/journal" + buildParams(pageable);
    }

    @RequestMapping("/update/{id}")
    public String update(@PathVariable int id, Model model, @PageableDefault Pageable pageable) {
        JournalForm journalForm = new JournalForm();
        Journal journal = journalService.findOne(id);
        journalForm.setId(journal.getId());
        journalForm.setTitle(journal.getTitle());
        journalForm.setVolume(String.valueOf(journal.getVolume()));
        journalForm.setYear(String.valueOf(journal.getYear()));
        journalForm.setPrice(String.valueOf(journal.getPrice()));
        model.addAttribute("journal", journalForm);
        show(model, pageable);
        return "admin/journal";
    }

    @RequestMapping("/cancel")
    public String cancel(SessionStatus status, @PageableDefault Pageable pageable) {
        status.setComplete();
        return "redirect:/admin/journal" + buildParams(pageable);
    }
}

package ua.editor;


import ua.entity.JournalTitle;
import ua.service.JournalTitleService;

import java.beans.PropertyEditorSupport;

/**
 * Created by Admin on 1/30/2017.
 */
public class JournalTitleEditor extends PropertyEditorSupport {
    private final JournalTitleService journalTitleService;

    public JournalTitleEditor(JournalTitleService journalTitleService) {
        this.journalTitleService = journalTitleService;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        JournalTitle journalTitle = journalTitleService.loadedTitle(Integer.valueOf(text));
        setValue(journalTitle);
    }
}

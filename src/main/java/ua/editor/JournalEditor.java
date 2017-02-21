package ua.editor;

import ua.entity.Journal;
import ua.service.JournalService;

import java.beans.PropertyEditorSupport;

/**
 * Created by Admin on 1/29/2017.
 */
public class JournalEditor extends PropertyEditorSupport {

    private final JournalService service;

    public JournalEditor(JournalService service) {
        this.service = service;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        Journal journal = service.findOne(Integer.valueOf(text));
        setValue(journal);
    }
}

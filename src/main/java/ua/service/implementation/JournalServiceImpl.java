package ua.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.dto.JournalForm;
import ua.entity.Journal;
import ua.entity.JournalTitle;
import ua.repository.JournalRepository;
import ua.service.JournalService;

import java.util.List;

/**
 * Created by Admin on 1/21/2017.
 */
@Service
public class JournalServiceImpl implements JournalService {
    @Autowired
    private JournalRepository journalRepository;

    @Override
    public List<Journal> findAll() {
        return journalRepository.findAll();
    }

    @Override
    public void delete(int id) {
        journalRepository.delete(id);

    }

    @Override
    public Journal findOne(int id) {
        return journalRepository.findOne(id);
    }


    @Override
    public void save(JournalForm journalForm) {
        Journal journal = new Journal();
        journal.setId(journalForm.getId());
        journal.setTitle(journalForm.getTitle());
        journal.setVolume(Integer.parseInt(journalForm.getVolume()));
        journal.setYear(Integer.parseInt(journalForm.getYear()));
        journal.setPrice(Integer.parseInt(journalForm.getPrice()));
        journalRepository.save(journal);
    }

    @Override
    public JournalForm findByVolumeAndYear(JournalTitle title, Integer volume, Integer year) {
        Journal journal = null;
        JournalForm journalForm = new JournalForm();
        for (Journal j : findAll()) {
            if (j.getTitle().equals(title) && j.getVolume().equals(volume) && j.getYear().equals(year)) {
                journal = j;
                journalForm.setId(journal.getId());
                journalForm.setTitle(journal.getTitle());
                journalForm.setVolume(String.valueOf(journal.getVolume()));
                journalForm.setYear(String.valueOf(journal.getYear()));
                journalForm.setPrice(String.valueOf(journal.getPrice()));
                return journalForm;
            }
        }

        return null;
    }

    @Override
    public Page<Journal> findAll(Pageable pageable) {
        return journalRepository.findAll(pageable);
    }
}

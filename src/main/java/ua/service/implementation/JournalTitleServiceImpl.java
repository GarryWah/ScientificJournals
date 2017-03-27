package ua.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.entity.*;
import ua.repository.CategoryRepository;
import ua.repository.JournalRepository;
import ua.repository.JournalTitleRepository;
import ua.service.JournalTitleService;

import java.util.List;

/**
 * Created by Admin on 1/21/2017.
 */
@Service
public class JournalTitleServiceImpl implements JournalTitleService {
    @Autowired
    private JournalTitleRepository journalTitleRepository;
    @Autowired
    private JournalRepository journalRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<JournalTitle> findAll() {
        return journalTitleRepository.findAll();
    }


    @Override
    public void delete(int id) {
        journalTitleRepository.delete(id);

    }

    @Override

    public JournalTitle loadedTitle(int id) {
        return journalTitleRepository.loadedTitle(id);

    }

    @Override
    public JournalTitle findByName(String name) {
        return journalTitleRepository.findByName(name);
    }

    @Override
    public List<JournalTitle> findByCategoryId(Integer id) {
        return journalTitleRepository.findByCategoryId(id);
    }

    @Override
    @Transactional
    public void save(JournalTitle title) {

        journalTitleRepository.save(title);

    }

    @Override
    @Transactional
    public void deleteVolume(int id, int journalId) {
        JournalTitle title = journalTitleRepository.findOne(id);
        Journal journal = journalRepository.findOne(journalId);
        title.getJournals().removeIf(s -> s.getId() == journalId);
        journalRepository.delete(journalId);
        journalTitleRepository.save(title);
    }


    @Override
    public Page<JournalTitle> findAll(Pageable pageable) {
        return journalTitleRepository.findAll(pageable);
    }
}


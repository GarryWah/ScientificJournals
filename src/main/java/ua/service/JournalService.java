package ua.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.dto.JournalForm;
import ua.entity.Journal;
import ua.entity.JournalTitle;

import java.util.List;

/**
 * Created by Admin on 1/21/2017.
 */
public interface JournalService {
    List<Journal> findAll();

    void delete(int id);

    Journal findOne(int id);

    void save(JournalForm journalForm);

    Page<Journal> findAll(Pageable pageable);

    JournalForm findByVolumeAndYear(JournalTitle title, Integer volume, Integer year);

}

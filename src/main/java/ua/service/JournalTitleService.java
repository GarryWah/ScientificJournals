package ua.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.entity.JournalTitle;

import java.util.List;

/**
 * Created by Admin on 1/21/2017.
 */
public interface JournalTitleService {
    List<JournalTitle> findAll();
    void delete(int id);
    JournalTitle loadedTitle(int id);
    JournalTitle findByName(String name);
    List<JournalTitle> findByCategoryId(Integer id);
    void save(JournalTitle title);
    void deleteVolume(int id, int journalId);
    Page<JournalTitle> findAll(Pageable pageable);
}

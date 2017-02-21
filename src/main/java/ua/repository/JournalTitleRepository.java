package ua.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.entity.JournalTitle;

import java.util.List;

/**
 * Created by Admin on 1/21/2017.
 */
public interface JournalTitleRepository extends JpaRepository<JournalTitle, Integer> {
    @Query("SELECT distinct t FROM JournalTitle t LEFT JOIN FETCH t.journals WHERE "
            + "t.id=:id")
    JournalTitle loadedTitle(@Param("id") Integer id);
 /*   @Query("SELECT tl FROM Category c JOIN c.titles tl WHERE c.id=:id")
    List<JournalTitle> findByCategoryId(@Param("id")int id);*/

    @Query("SELECT jts FROM Category c JOIN c.titles jts WHERE c.id=:id")
    List<JournalTitle> findByCategoryId(@Param("id") int id);

    @Query("SELECT t FROM JournalTitle t LEFT JOIN FETCH t.category WHERE "
            + "t.id=:id")
    JournalTitle findOne(@Param("id") Integer id);

    @Query("SELECT t FROM JournalTitle t LEFT JOIN FETCH t.category")
    List<JournalTitle> findAll();

    JournalTitle findByName(String name);
    @Query(value="SELECT t FROM JournalTitle t LEFT JOIN FETCH t.category",
            countQuery="SELECT count(t.id) FROM JournalTitle t")
    Page<JournalTitle> findAll(Pageable pageable);


}

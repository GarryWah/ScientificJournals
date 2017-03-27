package ua.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.entity.Journal;

import java.util.List;

/**
 * Created by Admin on 1/21/2017.
 */
public interface JournalRepository extends JpaRepository<Journal, Integer> {


    @Query("SELECT j FROM Journal j LEFT JOIN FETCH j.title jt LEFT JOIN FETCH jt.category where j.id=:id")
    Journal findOne(@Param("id") Integer id);

    @Query("SELECT j FROM Journal j LEFT JOIN FETCH j.title jt LEFT JOIN FETCH jt.category")
    List<Journal> findAll();

    @Query(value = "SELECT j FROM Journal j LEFT JOIN FETCH j.title jt LEFT JOIN FETCH jt.category",
            countQuery = "SELECT count(j.id) FROM Journal j")
    Page<Journal> findAll(Pageable pageable);

}

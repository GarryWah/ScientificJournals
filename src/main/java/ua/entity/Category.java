package ua.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 1/19/2017.
 */
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    private String name;
    @OneToMany
            (mappedBy = "category", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<JournalTitle> titles = new ArrayList<JournalTitle>();

    public Category() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<JournalTitle> getTitles() {
        return titles;
    }

    public void setTitles(List<JournalTitle> titles) {
        this.titles = titles;
    }


    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +

                '}';
    }
}

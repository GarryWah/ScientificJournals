package ua.dto;

import ua.entity.JournalTitle;
import ua.entity.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 2/7/2017.
 */
public class JournalForm {

    private Integer id;
    private JournalTitle title;
    private String volume;
    private String year;
    private String price;

    List<Order> inOrders = new ArrayList<Order>();

    public JournalForm() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public JournalTitle getTitle() {
        return title;
    }

    public void setTitle(JournalTitle title) {
        this.title = title;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<Order> getInOrders() {
        return inOrders;
    }

    public void setInOrders(List<Order> inOrders) {
        this.inOrders = inOrders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JournalForm that = (JournalForm) o;

        if (!getTitle().equals(that.getTitle())) return false;
        if (!getVolume().equals(that.getVolume())) return false;
        if (!getYear().equals(that.getYear())) return false;
        return getPrice().equals(that.getPrice());
    }

    @Override
    public int hashCode() {
        int result = getTitle().hashCode();
        result = 31 * result + getVolume().hashCode();
        result = 31 * result + getYear().hashCode();
        result = 31 * result + getPrice().hashCode();
        return result;
    }
}

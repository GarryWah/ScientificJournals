package ua.entity;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="journal")
@NamedQuery(name="journal.findAll",query = "SELECT j from Journal j join j.title t join t.category c")

public class Journal {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="title_id")
	private JournalTitle title;
	@Column(name="volume")
	private Integer volume;
	@Column (name="year")
	private Integer year;
	@Column(name="price")
	private Integer price;
	@OneToMany(mappedBy = "journal",fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
	List<Order> inOrders=new ArrayList<Order>();

    public Journal() {
    }

	public List<Order> getInOrders() {
		return inOrders;
	}

	public void setInOrders(List<Order> inOrders) {
		this.inOrders = inOrders;
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

	public Integer getVolume() {
		return volume;
	}

	public void setVolume(Integer volume) {
		this.volume = volume;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}


	@Override
    public String toString() {
        return "Journal{" +
                "id=" + id +
                ", title=" + title +
                ", volume=" + volume +
                ", year=" + year +
                ", price=" + price +
                ", inOrders=" + inOrders +
                '}';
    }

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Journal journal = (Journal) o;

		if (!getId().equals(journal.getId())) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = getId().hashCode();
		result = 31 * result + getTitle().hashCode();
		result = 31 * result + getVolume().hashCode();
		result = 31 * result + getYear().hashCode();
		return result;
	}
}

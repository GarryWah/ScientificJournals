package ua.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="bucket")
@NamedQuery(name="order.findAll",query = "SELECT o from Order o")
public class Order {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "journal_id")
	private Journal journal;
    @Column(name = "dateofpay")
	Date dateOfPay;

    public Order() {
    }

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDateOfPay() {
		return dateOfPay;
	}

	public void setDateOfPay(Date dateOfPay) {
		this.dateOfPay = dateOfPay;
	}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Journal getJournal() {
        return journal;
    }

    public void setJournal(Journal journal) {
        this.journal = journal;
    }

	@Override
	public String toString() {
		return "Order{" +
				"id=" + id +
				", user=" + user +
				", journal=" + journal +
				", dateOfPay=" + dateOfPay +
				'}';
	}
}

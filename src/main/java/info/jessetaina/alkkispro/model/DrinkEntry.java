package info.jessetaina.alkkispro.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Column;

@Entity
public class DrinkEntry {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long drink_entry_id;

	@NotNull
	@Basic
	@Temporal(TemporalType.DATE)
	 // Original field name was drink_date. Changed to make the repository's find method (findAllByDrinkingDate) map to the correct field.
	@Column(name="drink_date")
	@JsonProperty("drink_date")
	private Date drinkingDate;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "drink_id")
	private Drink drink;

	@NotNull
	private int drink_quantity;

	@NotNull
	private Double drink_entry_units;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private User user;

	public DrinkEntry() {
	}

	public DrinkEntry(long drink_entry_id, @NotNull Date drinkingDate, @NotNull Drink drink,
			@NotNull int drink_quantity, @NotNull Double drink_entry_units) {
		super();
		this.drink_entry_id = drink_entry_id;
		this.drinkingDate = drinkingDate;
		this.drink = drink;
		this.drink_quantity = drink_quantity;
		this.drink_entry_units = drink_entry_units;
	}

	public long getDrink_entry_id() {
		return drink_entry_id;
	}

	public void setDrink_entry_id(long drink_entry_id) {
		this.drink_entry_id = drink_entry_id;
	}

	public Date getdrinkingDate() {
		return drinkingDate;
	}

	public void setdrinkingDate(Date drinkingDate) {
		this.drinkingDate = drinkingDate;
	}

	public Drink getDrink() {
		return drink;
	}

	public void setDrink(Drink drink) {
		this.drink = drink;
	}

	public int getDrink_quantity() {
		return drink_quantity;
	}

	public void setDrink_quantity(int drink_quantity) {
		this.drink_quantity = drink_quantity;
	}

	public Double getDrink_entry_units() {
		return drink_entry_units;
	}

	public void setDrink_entry_units(Double drink_entry_units) {
		this.drink_entry_units = drink_entry_units;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "DrinkEntry [drink_entry_id=" + drink_entry_id + ", drinkingDate=" + drinkingDate + ", drink=" + drink
				+ ", drink_quantity=" + drink_quantity + ", drink_entry_units=" + drink_entry_units + ", user=" + user + "]";
	}
}

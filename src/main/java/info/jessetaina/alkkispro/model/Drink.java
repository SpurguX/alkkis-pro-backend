package info.jessetaina.alkkispro.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Drink {
	
	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	private long drink_id;
	
	@NotNull private String drink_name;
	@NotNull private double volume;
	@NotNull private double alc_content;
	@NotNull private double units;
	
	
	public Drink() {};
	
	public Drink(@NotNull String drink_name, @NotNull double volume, @NotNull double alc_content,
			@NotNull double units) {
		super();
		this.drink_name = drink_name;
		this.volume = volume;
		this.alc_content = alc_content;
		this.units = units;
	}

	public long getDrink_id() {
		return drink_id;
	}
	public void setDrink_id(long drink_id) {
		this.drink_id = drink_id;
	}
	public String getDrink_name() {
		return drink_name;
	}
	public void setDrink_name(String drink_name) {
		this.drink_name = drink_name;
	}
	public double getVolume() {
		return volume;
	}
	public void setVolume(double volume) {
		this.volume = volume;
	}
	public double getAlc_content() {
		return alc_content;
	}
	public void setAlc_content(double alc_content) {
		this.alc_content = alc_content;
	}
	public double getUnits() {
		return units;
	}
	public void setUnits(double units) {
		this.units = units;
	}
	
	@Override
	public String toString() {
		return "Drink [drink_id=" + drink_id + ", drink_name=" + drink_name + ", volume=" + volume + ", alc_content="
				+ alc_content + ", units=" + units + "]";
	}
	
	
}

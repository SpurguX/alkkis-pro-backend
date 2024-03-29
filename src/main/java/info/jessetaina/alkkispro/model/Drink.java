package info.jessetaina.alkkispro.model;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Drink {

	public enum DrinkType {
		MILD,
		WINE,
		LIQUEUR,
		BOOZE
	}

	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	private long drinkId;
	
	@NotNull 
	private String drinkName;
	
	@NotNull 
	private double volume;
	
	@NotNull
	private double alcContent;
	
	@NotNull 
	private double units;
	
	@NotNull
	private boolean isDefault;

	@NotNull
	@Enumerated(EnumType.STRING)
	private DrinkType type;

	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private User user;
	
	private String icon;
	
	public Drink() {};
	
	public Drink(@NotNull String drink_name, @NotNull double volume, @NotNull double alc_content,
			@NotNull double units, @NotNull boolean is_default, @NotNull DrinkType type) {
		super();
		this.drinkName = drink_name;
		this.volume = volume;
		this.alcContent = alc_content;
		this.units = units;
		this.isDefault = is_default;
		this.type = type;
	}

	public Drink(@NotNull String drink_name, @NotNull double volume, @NotNull double alc_content,
			@NotNull double units, @NotNull DrinkType type, User user) {
		super();
		this.drinkName = drink_name;
		this.volume = volume;
		this.alcContent = alc_content;
		this.units = units;
		this.isDefault = false; // When User is given, Drink is always a saved drink
		this.type = type;
		this.user = user;
	}

	public long getDrinkId() {
		return drinkId;
	}
	
	public void setDrinkId(long drink_id) {
		this.drinkId = drink_id;
	}
	
	public String getDrinkName() {
		return drinkName;
	}
	
	public void setDrinkName(String drink_name) {
		this.drinkName = drink_name;
	}
	
	public double getVolume() {
		return volume;
	}
	
	public void setVolume(double volume) {
		this.volume = volume;
	}
	
	public double getAlcContent() {
		return alcContent;
	}
	
	public void setAlcContent(double alc_content) {
		this.alcContent = alc_content;
	}
	
	public double getUnits() {
		return units;
	}
	
	public void setUnits(double units) {
		this.units = units;
	}
		
	public boolean isDefault() {
		return isDefault;
	}

	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}

	public DrinkType getType() {
		return type;
	}

	public void setType(DrinkType type) {
		this.type = type;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	@Override
	public String toString() {
		return "Drink [drinkId=" + drinkId + ", drinkName=" + drinkName + ", volume=" + volume + ", alcContent="
				+ alcContent + ", units=" + units + ", type=" + type + ", isDefault=" + isDefault + ", user=" + user + "]";
	}
}

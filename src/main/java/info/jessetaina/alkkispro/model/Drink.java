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
	
	private String icon;
	
	public Drink() {};
	
	public Drink(@NotNull String drink_name, @NotNull double volume, @NotNull double alc_content,
			@NotNull double units, @NotNull boolean is_default) {
		super();
		this.drinkName = drink_name;
		this.volume = volume;
		this.alcContent = alc_content;
		this.units = units;
		this.isDefault = is_default; 
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

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	@Override
	public String toString() {
		return "Drink [drinkId=" + drinkId + ", drinkName=" + drinkName + ", volume=" + volume + ", alcContent="
				+ alcContent + ", units=" + units + ", isDefault=" + isDefault + "]";
	}
}

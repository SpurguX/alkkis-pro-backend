package info.jessetaina.alkkispro.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Juoma {
	
	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	private long juoma_id;
	
	@NotNull private String juoma_nimi;
	@NotNull private double tilavuus;
	@NotNull private double vahvuus;
	@NotNull private double annokset;
	
	
	public Juoma() {};
	
	public Juoma(@NotNull String juoma_nimi, @NotNull double tilavuus, @NotNull double vahvuus,
			@NotNull double annokset) {
		super();
		this.juoma_nimi = juoma_nimi;
		this.tilavuus = tilavuus;
		this.vahvuus = vahvuus;
		this.annokset = annokset;
	}

	public long getJuoma_id() {
		return juoma_id;
	}
	public void setJuoma_id(long juoma_id) {
		this.juoma_id = juoma_id;
	}
	public String getJuoma_nimi() {
		return juoma_nimi;
	}
	public void setJuoma_nimi(String juoma_nimi) {
		this.juoma_nimi = juoma_nimi;
	}
	public double getTilavuus() {
		return tilavuus;
	}
	public void setTilavuus(double tilavuus) {
		this.tilavuus = tilavuus;
	}
	public double getVahvuus() {
		return vahvuus;
	}
	public void setVahvuus(double vahvuus) {
		this.vahvuus = vahvuus;
	}
	public double getAnnokset() {
		return annokset;
	}
	public void setAnnokset(double annokset) {
		this.annokset = annokset;
	}
	
	@Override
	public String toString() {
		return "Juoma [juoma_id=" + juoma_id + ", juoma_nimi=" + juoma_nimi + ", tilavuus=" + tilavuus + ", vahvuus="
				+ vahvuus + ", annokset=" + annokset + "]";
	}
	
	
}

package info.jessetaina.alkkispro.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Entity
public class User {
  @Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private long id;

	@Temporal(TemporalType.DATE)
	private Date creation_date;

  @NotNull
  @Column(unique=true)
	private String username;

  @NotNull
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;

	@Temporal(TemporalType.DATE)
	private Date password_set_date;

  public User() {}

  public User(@NotNull String username, @NotNull String password) {
    this.username = username;
    // this.password = password;
    this.setPassword(password);
  }


  @PrePersist
  public void setCreationDate () {
    this.creation_date = new Date();
  }

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

  public void setCreation_date(Date creation_date) {
    this.creation_date = creation_date;
  }

  public Date getCreation_date() {
    return this.creation_date;
  }

	public void setPassword(String password) {
    this.password_set_date = new Date(System.currentTimeMillis());
    BCryptPasswordEncoder bcryptEncoder = new BCryptPasswordEncoder();
    String encodedPassword = bcryptEncoder.encode(password);
		this.password = encodedPassword;
	}


  public long getId() {
    return this.id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Date getPassword_set_date() {
    return this.password_set_date;
  }

  public void setPassword_set_date(Date password_set_date) {
    this.password_set_date = password_set_date;
  }


  @Override
  public String toString() {
    return "User: + " +
    this.id + "; " +
    this.username + "; " +
    // this.password + "; " +
    "created: " + this.creation_date;
  }
}

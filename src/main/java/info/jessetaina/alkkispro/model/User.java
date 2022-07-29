package info.jessetaina.alkkispro.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

// import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Entity
public class User {
  @Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private long id;

	@Temporal(TemporalType.DATE)
	private Date creation_date;

  @NotNull
	private String username;

  @NotNull
	// @JsonIgnore <-- this caused a problem with creating user (that's probably why DTO is sometimes used)
  // TODO make sure password isn't exposed in a JSON response
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

  @Override
  public String toString() {
    return "User: + " +
    this.id + "; " +
    this.username + "; " +
    // this.password + "; " +
    "created: " + this.creation_date;
  }
}

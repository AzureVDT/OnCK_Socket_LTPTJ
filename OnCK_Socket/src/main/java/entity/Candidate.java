package entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedNativeQueries;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.Table;

@Entity
@Table(name = "candidates")
@NamedNativeQueries({
	@NamedNativeQuery(name="getAllCandidates", query="select * from candidates", resultClass = Candidate.class)
})
public class Candidate implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6595829460988958769L;
	@Id
	@Column(name = "candidate_id", nullable = false, unique = true)
	private String id;
	@Column(name = "full_name")
	private String fullName;
	@Column(name = "year_of_birth")
	private int yearOfBirth;
	private String gender;
	private String email;
	private String phone;
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "position_id")
	private Position position;
	
	public Candidate() {
		super();
	}

	public Candidate(String id, String fullName, int yearOfBirth, String gender, String email, String phone,
			String description) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.yearOfBirth = yearOfBirth;
		this.gender = gender;
		this.email = email;
		this.phone = phone;
		this.description = description;
	}
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public int getYearOfBirth() {
		return yearOfBirth;
	}

	public void setYearOfBirth(int yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Candidate other = (Candidate) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Candidate [id=" + id + ", fullName=" + fullName + ", yearOfBirth=" + yearOfBirth + ", gender=" + gender
				+ ", email=" + email + ", phone=" + phone + ", description=" + description + "]";
	}
	
	
}

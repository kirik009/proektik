package lab5.entity;

import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;


@Entity
@Table(name = "patients")
@AttributeOverride(name = "id", column = @Column(name = "`pat_id`"))
public class Patient extends AbstractEntity {
	@Column(unique = true)
	private String patName;
	@Column(unique = true)
	private String surname;
	@Column(unique = true)
	private String phoneNumber;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "patient", cascade = {CascadeType.ALL}, orphanRemoval = true)
	private Set<Illness> illnesses;
	@ManyToOne(cascade = {CascadeType.MERGE})
	@JoinColumn(name = "`doc_id`", nullable = false)
	@JsonProperty(access = Access.WRITE_ONLY)
	private Doctor doctor;
	
	public String getPatName() {
		return patName;
	}

	public void setPatName(String patName) {
		this.patName = patName;
	}
	
	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Set<Illness> getIllnesses() {
		return illnesses;
	}

	public void setIlnesses(Set<Illness> illnesses) {
		this.illnesses = illnesses;
	}
	
	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	@Override
	public String toString() {
		return "PATIENT - " + id + ": [name=" + patName + ", illnesses=" + illnesses + "]";
	}
}

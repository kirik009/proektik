package lab5.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "illnesses")
public class Illness extends AbstractEntity {
	@Column(unique = true)
	private String name;
	@Column(unique = true)
	private String dateStart;
	@Column(unique = true)
	private String dateEnd;
	@ManyToOne(cascade = {CascadeType.MERGE})
	@JoinColumn(name = "`pat_id`", nullable = false)
	@JsonProperty(access = Access.WRITE_ONLY)
	private Patient patient;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getDateStart() {
		return dateStart;
	}

	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}	

	public String getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	@Override
	public String toString() {
		return "ILLNESS  - " + id + ": [name=" + name + ", dateStart=" + dateStart + ", dateEnd=" + dateEnd + "]";
	}
}


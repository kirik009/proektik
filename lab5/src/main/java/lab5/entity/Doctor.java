package lab5.entity;

import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "doctors")
@AttributeOverride(name = "id", column = @Column(name = "`doc_id`"))
public class Doctor extends AbstractEntity {
	@Column(unique = true)
	private String docName;
	@Column(unique = true)
	private String department;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "doctor", cascade = {CascadeType.ALL}, orphanRemoval = true)
	private Set<Patient> patients;

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}
	
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Set<Patient> getPatient() {
		return patients;
	}

	public void setPatient(Set<Patient> patients) {
		this.patients = patients;
	}


	@Override
	public String toString() {
		return "DOCTOR - " + id + ": [docName=" + docName + ", department=" + department + ", patients=" + patients +"]";
	}
}
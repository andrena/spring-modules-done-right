package de.andrena.et14.spring.konferenz;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

@Entity
public class KonferenzEntity {

	@Id
	@GeneratedValue
	private Long id;

	@Column
	private String name;

	@Column
	private String ort;

	@Column
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate datum;

	public Long getId() {
		return id;
	}

	public Konferenz toDto() {
		Konferenz konferenz = new Konferenz(id);
		konferenz.setName(name);
		konferenz.setOrt(ort);
		konferenz.setDatum(datum);
		return konferenz;
	}

	public void updateFrom(Konferenz konferenz) {
		name = konferenz.getName();
		ort = konferenz.getOrt();
		datum = konferenz.getDatum();
	}
}

package de.andrena.et14.spring.vortraege;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import de.andrena.et14.spring.konferenz.KonferenzEntity;

@Entity
public class VortragEntity {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private KonferenzEntity konferenz;

	public Long getId() {
		return id;
	}

	public void setKonferenz(KonferenzEntity konferenz) {
		this.konferenz = konferenz;
	}

	public KonferenzEntity getKonferenz() {
		return konferenz;
	}
}

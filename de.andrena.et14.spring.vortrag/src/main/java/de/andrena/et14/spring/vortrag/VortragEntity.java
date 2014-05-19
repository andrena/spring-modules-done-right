package de.andrena.et14.spring.vortrag;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import de.andrena.et14.spring.konferenz.KonferenzEntity;

@Entity
public class VortragEntity {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private KonferenzEntity konferenz;

	@Column
	private String titel;

	@Column
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime lastInformedVortragende = DateTime.now();

	public Long getId() {
		return id;
	}

	public void setKonferenz(KonferenzEntity konferenz) {
		this.konferenz = konferenz;
	}

	public KonferenzEntity getKonferenz() {
		return konferenz;
	}

	public String getTitel() {
		return titel;
	}

	public void updateLastInformedVortragende() {
		this.lastInformedVortragende = DateTime.now();
	}

	public DateTime getLastInformedVortragende() {
		return lastInformedVortragende;
	}

	public void updateFrom(Vortrag vortrag) {
		titel = vortrag.getTitel();
	}

	public Vortrag toDto() {
		Vortrag vortrag = new Vortrag(id, konferenz.toDto());
		vortrag.setTitel(titel);
		vortrag.setLastInformedVortragende(lastInformedVortragende);
		return vortrag;
	}
}

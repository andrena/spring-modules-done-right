package de.andrena.et14.spring.konferenz;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class KonferenzEntity {

	@Id
	@GeneratedValue
	private Long id;

	public Long getId() {
		return id;
	}

	public Konferenz toDto() {
		// TODO Auto-generated method stub
		return null;
	}
}

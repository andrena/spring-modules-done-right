package de.andrena.et14.spring.konferenz;

import java.io.Serializable;
import java.util.Objects;

import org.joda.time.LocalDate;

public class Konferenz implements Serializable {

	private static final long serialVersionUID = 1L;

	private final Long id;

	private String name;
	private String ort;
	private LocalDate datum;

	public Konferenz() {
		this(null);
	}

	public Konferenz(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	public LocalDate getDatum() {
		return datum;
	}

	public void setDatum(LocalDate datum) {
		this.datum = datum;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Konferenz && idEquals((Konferenz) obj);
	}

	private boolean idEquals(Konferenz other) {
		return Objects.equals(id, other.id);
	}
}

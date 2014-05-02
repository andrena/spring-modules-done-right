package de.andrena.et14.spring.vortrag;

import java.io.Serializable;
import java.util.Objects;

import de.andrena.et14.spring.konferenz.Konferenz;

public class Vortrag implements Serializable {

	private static final long serialVersionUID = 1L;

	private final Long id;
	private final Konferenz konferenz;

	private String titel;

	public Vortrag(Konferenz konferenz) {
		this(null, konferenz);
	}

	public Vortrag(Long id, Konferenz konferenz) {
		this.id = id;
		this.konferenz = konferenz;
	}

	public Long getId() {
		return id;
	}

	public Konferenz getKonferenz() {
		return konferenz;
	}

	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Vortrag && idEquals((Vortrag) obj);
	}

	private boolean idEquals(Vortrag other) {
		return Objects.equals(id, other.id);
	}}

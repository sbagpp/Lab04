package it.polito.tdp.lab04.model;

public class Corso {
	private String codIns;
	private Integer periodoDidattico;
	private Integer crediti;
	private String nome;
	
	public Corso(String codIns, Integer periodoDidattico, Integer crediti, String nome) {
		super();
		this.codIns = codIns;
		this.periodoDidattico = periodoDidattico;
		this.crediti = crediti;
		this.nome = nome;
	}

	public String getCodIns() {
		return codIns;
	}

	public void setCodIns(String codIns) {
		this.codIns = codIns;
	}

	public Integer getPeriodoDidattico() {
		return periodoDidattico;
	}

	public void setPeriodoDidattico(Integer periodoDidattico) {
		this.periodoDidattico = periodoDidattico;
	}

	public Integer getCrediti() {
		return crediti;
	}

	public void setCrediti(Integer crediti) {
		this.crediti = crediti;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codIns == null) ? 0 : codIns.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Corso other = (Corso) obj;
		if (codIns == null) {
			if (other.codIns != null)
				return false;
		} else if (!codIns.equals(other.codIns))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Corso codice di insegnamento=" + codIns + ",\t periodoDidattico=" + periodoDidattico + ",\t crediti=" + crediti + ",\t nome corso="
				+ nome ;
	}
	
	
	
	
}

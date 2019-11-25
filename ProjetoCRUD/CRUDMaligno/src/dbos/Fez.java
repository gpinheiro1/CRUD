package dbos;

import validations.ValidationUtils;

public class Fez implements Cloneable {
	private String ra;
	private String codMateria;
	private float nota;
	private int frequencia;
	
	public Fez(String ra, String codMateria, float nota, int frequencia) throws Exception {
		this.setRa(ra);
		this.setCodMateria(codMateria);
		this.setNota(nota);
		this.setFrequencia(frequencia);
	}
	
	public String getRa() {
		return this.ra;
	}
	
	public void setRa(String ra) throws Exception {
		this.ra = ValidationUtils.validaRa(ra);
	}
	
	public String getCodMateria() {
		return this.codMateria;
	}
	
	public void setCodMateria(String codMateria) throws Exception {
		this.codMateria = ValidationUtils.validaCodMateria(codMateria);
	}
	
	public float getNota() {
		return this.nota;
	}
	
	public void setNota(float nota) throws Exception {
		this.nota = ValidationUtils.validaNota(nota);
	}
	
	public int getFrequencia() {
		return this.frequencia;
	}
	
	public void setFrequencia(int frequencia) throws Exception {
		this.frequencia = ValidationUtils.validaFrequencia(frequencia);
	}
	
	public String toString() {
		String ret = "";
		
		ret += "RA: " + this.ra + "\n";
		ret += "Cod. Matéria: " + this.codMateria + "\n";
		ret += "Nota: " + this.nota + "\n";
		ret += "Frequência: " + this.frequencia;
		
		return ret;
	}
	
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		
		if(obj == null)
			return false;
		
		if(this.getClass() != obj.getClass())
			return false;
		
		Fez fz = (Fez) obj;
		if(!this.ra.equals(fz.ra))
			return false;
		
		if(!this.codMateria.equals(fz.codMateria))
			return false;
		
		if(this.nota != fz.nota)
			return false;
		
		if(this.frequencia != fz.frequencia)
			return false;
		
		return true;
	}
	
	public int hashCode() {
		int ret = 1;
		
		ret = 3 * ret + this.ra.hashCode();
		ret = 3 * ret + this.codMateria.hashCode();
		ret = 3 * ret + new Float(this.nota).hashCode();
		ret = 3 * ret + new Integer(this.frequencia).hashCode();
		
		if(ret < 0)
			ret = -ret;
		
		return ret;
	}
	
	public Fez(Fez modelo) throws Exception {
		if (modelo == null)
			throw new Exception("Modelo ausente!");
		
		this.ra = modelo.ra;
		this.codMateria = modelo.codMateria;
		this.nota = modelo.nota;
		this.frequencia = modelo.frequencia;
	}
	
	public Object clone() {
		Fez ret = null;
		
		try {
			ret = new Fez(this);
		} catch(Exception erro) {}
		
		return ret;
	}
}

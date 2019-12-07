package dbos;

import validations.ValidationUtils;

public class Materia {
	private String codMateria;
	private String nomeMateria;
	
	public Materia (String codMateria, String nomeMateria) throws Exception {
		this.setCodMateria(codMateria);
		this.setNomeMateria(nomeMateria);
	}
        public Materia(){
            this.codMateria = null;
            this.nomeMateria = null;
        }
	
	public String getCodMateria() {
		return this.codMateria;
	}
	
	public void setCodMateria(String codMateria) throws Exception {
		this.codMateria = ValidationUtils.validaCodMateria(codMateria);
	}
	
	public String getNomeMateria() {
		return this.nomeMateria;
	}
	
	public void setNomeMateria(String nomeMateria) throws Exception {
		this.nomeMateria = ValidationUtils.validaNomeMateria(nomeMateria);
	}

	public String toString() {
		String ret = "";
		
		ret += "C�digo da Mat�ria: " + this.codMateria + "\n";
		ret += "Nome da Mat�ria" + this.nomeMateria;
		
		return ret;
	}
	
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		
		if(obj == null)
			return false;
		
		if(this.getClass() != obj.getClass())
			return false;
		
		Materia mat = (Materia) obj;
		
		if(!this.codMateria.equals(mat.codMateria))
			return false;
		
		if(!this.nomeMateria.equals(mat.nomeMateria))
			return false;
		
		return true;
	}
	
	public int hashCode() {
		int ret = 1;
		
		ret = ret * 3 + this.codMateria.hashCode();
		ret = ret * 3 + this.nomeMateria.hashCode();
		
		if(ret < 0)
			ret = -ret;
		
		return ret;
	}
	
	public Materia(Materia modelo) throws Exception {
		if(modelo == null)
			throw new Exception("Modelo ausente!");
		
		this.codMateria = modelo.codMateria;
		this.nomeMateria = modelo.nomeMateria;
	}
	
	public Object clone() {
		Materia ret = null;
		
		try {
			ret = new Materia(this);
		} catch(Exception erro) {}
		
		return ret;
	}
}

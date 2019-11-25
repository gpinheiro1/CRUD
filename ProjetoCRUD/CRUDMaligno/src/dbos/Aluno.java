package dbos;

import validations.ValidationUtils;

public class Aluno implements Cloneable {
	private String ra;
	private String nome;
	private String email;
	
	public Aluno (String ra, String nome, String email) throws Exception {
		this.setRa(ra);
		this.setNome(nome);
		this.setEmail(email);
	}
	
	public String getRa() {
		return this.ra;
	}
	
	public void setRa(String ra) throws Exception {
		this.ra = ValidationUtils.validaRa(ra);
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public void setNome(String nome) throws Exception {
		if(nome == null)
			throw new Exception("O campo nome está vazio!");
		this.nome = nome;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email) throws Exception {
		if(email == null)
			throw new Exception("O campo email está vazio!");
		this.email = email;	
	}
	
	public String toString() {
		String ret = "";
		
		ret += "RA: " + this.ra + "\n";
		ret += "Nome: " + this.nome + "\n";
		ret += "Email: " + this.email;
		
		return ret;
	}
	
	public boolean equals (Object obj) {
		if (this == obj)
			return true;
		
		if(obj == null)
			return false;
		
		if(this.getClass() != obj.getClass())
			return false;
		
		Aluno alu = (Aluno) obj;
		if(!this.ra.equals(alu.ra))
			return false;
		
		if(!this.nome.equals(alu.nome))
			return false;
		
		if(!this.email.equals(alu.email))
			return false;
		
		return true;
	}
	
	public int hashCode() {
		int ret = 1;
		
		ret = 3 * ret + this.ra.hashCode();
		ret = 3 * ret + this.nome.hashCode();
		ret = 3 * ret + this.email.hashCode();
		
		if (ret < 0)
			ret = -ret;
		
		return ret;
	}
	
	public Aluno(Aluno modelo) throws Exception {
		if(modelo == null)
			throw new Exception("Modelo ausente");
		
		this.ra = modelo.ra;
		this.nome = modelo.nome;
		this.email = modelo.email;
	}
	
	public Object clone() {
		Aluno ret = null;
		
		try {
		ret = new Aluno(this);
	} catch(Exception erro) {}
		return ret;
	}
}

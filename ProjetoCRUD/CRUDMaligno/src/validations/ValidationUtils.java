package validations;

public class ValidationUtils {
	
	public static String validaRa(String ra) throws Exception {
		if(ra == null || ra.length() != 5)
			throw new Exception("O campo ra é inválido");
		return ra;
	}
	
	public static float validaNota(float nota) throws Exception {
		if(nota < 0.0 || nota > 10.00)
			throw new Exception("O campo nota é inválido");
		return nota;
	}
	
	public static int validaFrequencia(int frequencia) throws Exception {
		if (frequencia < 0)
			throw new Exception("O campo frequência não aceita números negativos");
		return frequencia;
	}
	
	public static String validaCodMateria(String codMateria) throws Exception {
		if(codMateria == null)
			throw new Exception("O campo não aceita null");
		return codMateria;
	}
	
	public static String validaNomeMateria(String nomeMateria) throws Exception {
		if (nomeMateria == null)
			throw new Exception("O campo não aceita nulo!");
		return nomeMateria;
	}
}

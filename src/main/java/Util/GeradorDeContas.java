package Util;

import java.util.Random;


public class GeradorDeContas {
	
	public static int idCc = 55;
	public static int idCp = 33;
	
	
	public static String geraCp() {
		
		Random random = new Random();
		int primeiroNumero = random. nextInt(999);
		int segundoNumero = random.nextInt(999);
		int digito = random.nextInt(9);
		
		
		
		return Integer.toString(idCp) + Integer.toString(primeiroNumero) + Integer.toString(segundoNumero) + "-" + Integer.toString(digito);		
	}
	
	


	public static String geraCc() {
		
		Random random = new Random();
		int primeiroNumero = random. nextInt(999);
		int segundoNumero = random.nextInt(999);
		int digito = random.nextInt(9);
		
		
		
		return Integer.toString(idCc) + Integer.toString(primeiroNumero) + Integer.toString(segundoNumero) + "-" + Integer.toString(digito);		
	}
	
			
}

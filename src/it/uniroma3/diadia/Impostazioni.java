package it.uniroma3.diadia;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Impostazioni {

	private String Cfu_inziali;
	private String Peso_Max_Borsa;

	public Impostazioni() {
		InputStream impostazioni = Impostazioni.class.getClassLoader().getResourceAsStream("diadia.properties");
		Properties prop= new Properties();
		
		//InputStream impostazioni = Impostazioni.class.getResourceAsStream("/diadia.properties");
		try {
			prop.load(impostazioni);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Cfu_inziali = prop.getProperty("cfu_iniziali");
		Peso_Max_Borsa = prop.getProperty("peso_max_borsa");
	}

	public int getCfuIniziali() {
		return Integer.parseInt(Cfu_inziali);
	}

	public int getPesoMaxBorsa() {
		return Integer.parseInt(Peso_Max_Borsa);
	}
}



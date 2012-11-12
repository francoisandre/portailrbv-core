package fr.obsmip.sedoo.core.misc;

public class StringTools {
	
	public static String protectIdentifier(String name) 
	{
		String aux = name.toUpperCase().replace(" ","");
		return aux;
	}

}

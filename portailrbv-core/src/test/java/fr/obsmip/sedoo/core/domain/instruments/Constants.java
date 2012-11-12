package fr.obsmip.sedoo.core.domain.instruments;

import java.util.HashMap;
import java.util.Locale;

import org.geotoolkit.util.DefaultInternationalString;
import org.opengis.util.InternationalString;

public interface Constants {
	
	public final InternationalString RAIN_JAUGE = new DefaultInternationalString(new HashMap<Locale, String>(){{put(Locale.ENGLISH,"RAIN GAUGE"); put(Locale.FRENCH,"PLUVIOMETRE");}});

}

package fr.obsmip.sedoo.core.dao.csw;

import org.geotoolkit.csw.v202.GetRecordById202;
import org.geotoolkit.security.ClientSecurity;


/**
 * Cette classe n'a comme seul but que de corriger une erreur sur le parametre REQUEST, mal positionné dans la version actuelle
 * @author andre
 *
 */
public class RBVGetRecordById extends GetRecordById202
{
	
	 public RBVGetRecordById(final String serverURL, final ClientSecurity security){
	        super(serverURL, security);
	    }
	@Override
	protected void prepareParameters() {
		super.prepareParameters();
		requestParameters.put("REQUEST", "GetRecordById");
	}
}

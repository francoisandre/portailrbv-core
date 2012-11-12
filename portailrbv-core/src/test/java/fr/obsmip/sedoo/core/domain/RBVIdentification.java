package fr.obsmip.sedoo.core.domain;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.jcip.annotations.ThreadSafe;

import org.geotoolkit.metadata.fra.FRA_DataIdentification;
import org.opengis.metadata.identification.DataIdentification;


@ThreadSafe
@XmlType(name = "RBV_DataIdentification_Type")
@XmlRootElement(name ="RBV_DataIdentification")
public class RBVIdentification extends FRA_DataIdentification
{
	  /**
	 * 
	 */
	private static final long serialVersionUID = -9094539891722185184L;

	public RBVIdentification() {
	    }

	    /**
	     * Constructs a metadata entity initialized with the values from the specified metadata.
	     *
	     * @param source The metadata to copy.
	     */
	    public RBVIdentification(final DataIdentification source) {
	        super(source);
	    }


}

package fr.obsmip.sedoo.core.domain;


import java.io.ByteArrayOutputStream;
import java.util.Collections;

import org.geotoolkit.metadata.iso.acquisition.DefaultAcquisitionInformation;
import org.geotoolkit.metadata.iso.acquisition.DefaultInstrument;
import org.geotoolkit.metadata.iso.citation.DefaultCitation;
import org.geotoolkit.util.DefaultInternationalString;
import org.geotoolkit.xml.XML;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.obsmip.sedoo.core.RBVApplication;
import fr.obsmip.sedoo.core.dao.ObservatoryDAO;
import fr.obsmip.sedoo.core.domain.instruments.Constants;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/META-INF/spring/config-test.xml","classpath:/META-INF/spring/contextProvider.xml","classpath:/META-INF/spring/contacts.xml"})
public class TestRBVMetadata 
{
	final Logger logger =LoggerFactory.getLogger(TestRBVMetadata.class);

	@Test
	public void testObsera() throws Exception 
	{
//		Metadata davisPluviometer = new Metadata();
//		davisPluviometer.setResourceTitle("Mesures pluviométriques relatives au bassin versant de Bras-David");
//		davisPluviometer.setResourceAbstract("Précipitations et températures enregistrées depuis novembre 2011 sur le site 4 du bassin versant de Bras-David");
//		ObservatoryDAO observatoryDAO = RBVApplication.getInstance().getObservatoryDAO();
//		Observatory observa = observatoryDAO.getObservatoryById(1L);
//		Assert.assertNotNull("L'observatoire doit exister", observa);
//		Site site4 = observa.getObservationSiteByName("site 4");
//		Assert.assertNotNull("Le site doit exister", site4);
////		davisPluviometer.setDrainageBasin();
//		
//		DefaultInstrument instrument = new DefaultInstrument();
//		
//		instrument.setType(Constants.RAIN_JAUGE);
//		instrument.setType(new DefaultInternationalString("RAIN JAUGE"));
//		
//		davisPluviometer.getIdentificationInfo().add(new RBVIdentification());
//		
//		DefaultAcquisitionInformation acquisitionInformation = new DefaultAcquisitionInformation();
//		acquisitionInformation.setInstruments(Collections.singletonList(instrument));
//		davisPluviometer.setAcquisitionInformation(Collections.singletonList(acquisitionInformation));
//		
//		RBVIdentification identification = new RBVIdentification();
//		identification.setRelatedCitations(Collections.singletonList(new DefaultCitation("cccc")));
//		
//		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//		XML.marshal(identification, outputStream);
//		String aux = outputStream.toString();
//		System.out.println(MetadataTools.prettyFormat(aux,2));
//		
//		
//		
//		String result = MetadataTools.toXML(davisPluviometer);
////		logger.debug("\n"+result);
	}

		
}

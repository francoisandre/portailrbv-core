package fr.obsmip.sedoo.core.domain;


import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import junit.framework.Assert;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.geotoolkit.metadata.iso.extent.DefaultGeographicBoundingBox;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/META-INF/spring/contextProvider.xml","classpath:/META-INF/spring/contacts.xml"})
public class TestMetadata 
{
	String resourceTitle="resourceTitle";
	String resourceAbstract="resourceAbstract";
	String resourceDOI="10.1007/s00223-003-0070-0";
	List<String> resourceLanguageCodes = new ArrayList() {{ add("fre"); add("eng"); }};
	List<String> keywords = new ArrayList() {{ add("traitement de l'eau"); add("bassin versant"); }};
	List<String> resourceURL= new ArrayList() {{ add("http://www.google.com"); add("http://www.lemonde.fr"); }};
	String metadataLanguageCode="eng";
	String metadataDate="2013-10-26";
	String publicationDate="2012-09-25";
	String creationDate="2011-08-24";
	String lastRevisionDate="2010-07-23";
	double northBoundLatitude = 51.58;
	double southBoundLatitude = 41.56;
	double eastBoundLongitude = 8.49;
	double westBoundLongitude = -4.60;
	DefaultGeographicBoundingBox boundingbox = new DefaultGeographicBoundingBox(westBoundLongitude, eastBoundLongitude, southBoundLatitude, northBoundLatitude);
	
	final Logger logger =LoggerFactory.getLogger(TestMetadata.class);

	@Test
	public void testSetters() throws Exception 
	{
		Metadata metadata = getTestMetadata();
		Assert.assertEquals("La donnée resourceTitle doit être conservée",resourceTitle, metadata.getResourceTitle());
		Assert.assertEquals("La donnée resourceAbstract doit être conservée",resourceAbstract, metadata.getResourceAbstract());
		Assert.assertEquals("La donnée resourceDOI doit être conservée",resourceDOI, metadata.getResourceDOI());
		Assert.assertEquals("La donnée publicationDate doit être conservée",publicationDate, metadata.getPublicationDate());
		Assert.assertEquals("La donnée publicationDate doit être conservée",creationDate, metadata.getCreationDate());
		Assert.assertEquals("La donnée lastRevisionDate doit être conservée",lastRevisionDate, metadata.getLastRevisionDate());
		Assert.assertEquals("La donnée metadataDate doit être conservée",metadataDate, metadata.getMetadataDate());
		Assert.assertEquals("La donnée boundingbox doit être conservée",boundingbox, metadata.getGeographicBoundingBox());
		
		Assert.assertTrue("La donnée resourceLanguageCodes doit être conservée", areStringListEqual(resourceLanguageCodes, metadata.getResourceLanguages()));
		Assert.assertTrue("La donnée resourceURL doit être conservée", areStringListEqual(resourceURL, metadata.getResourceURL()));
		Assert.assertEquals("La donnée metadataLanguageCode doit être conservée", metadataLanguageCode, metadata.getMetadataLanguage());
		
		
		//Données initialisés
		Assert.assertEquals("La donnée hierachyLevels doit contenir une seule entrée",1, metadata.getHierarchyLevels().size());
		
		
	}

	public Metadata getTestMetadata() throws MetadataInitialisationException
	{
		Metadata metadata = new Metadata();
		metadata.setResourceTitle(resourceTitle);
		metadata.setResourceAbstract(resourceAbstract);
		metadata.setResourceDOI(resourceDOI);
		metadata.setResourceLanguages(resourceLanguageCodes);
		metadata.setMetadataLanguage(metadataLanguageCode);
		metadata.setKeywords(keywords);
		metadata.setResourceURL(resourceURL);
		metadata.setPublicationDate(publicationDate);
		metadata.setCreationDate(creationDate);
		metadata.setLastRevisionDate(lastRevisionDate);
		metadata.setMetadataDate(metadataDate);
		metadata.setGeographicBoundingBox(boundingbox);
		
		return metadata;
	}


	@Test
	public void testInspireValidation() throws Exception
	{
		Metadata metadata = getTestMetadata();
		
		String result = MetadataTools.toXML(metadata);
		logger.debug("\n"+result);
		
		HttpPost httpPost = new HttpPost("http://www.inspire-geoportal.eu/INSPIREValidatorService/resources/validation/inspire");
		//xml response: h
		httpPost.addHeader("Accept", "application/xml");
		//html response
		//httpPost.addHeader("Accept", "text/html");
		File tempFile = File.createTempFile("fan", "fan");
		FileUtils.writeStringToFile(tempFile, result);
		
		//ContentBody body = new StringBody(outputStream.toString());
		ContentBody body = new FileBody(tempFile);
		MultipartEntity reqEntity = new MultipartEntity();
		reqEntity.addPart("dataFile", body);
		httpPost.setEntity(reqEntity);
		HttpClient client = new DefaultHttpClient();
		HttpResponse response = client.execute(httpPost);
		String globalErrorMessage = InspireValidatorResponseParser.parseResponse(response);
		Assert.assertEquals("L'analyse Inspire ne doit pas renvoyer d'erreurs", "", globalErrorMessage);
		tempFile.delete();
	}
	
	private static boolean areStringListEqual(List<String> first, List<String> second)
	{
		if ((first == null) && (second == null))
		{
			return true;
		}
		if ((first != null) && (second == null))
		{
			return false;
		}
		if ((first == null) && (second != null))
		{
			return false;
		}
		if (first.size() != second.size())
		{
			return false;
		}	
		
		Iterator<String> firstIterator = first.iterator();
		Iterator<String> secondIterator = second.iterator();
		
		while (firstIterator.hasNext()) 
		{
			String aux1 = firstIterator.next();
			String aux2 = secondIterator.next();
			if (aux1.compareTo(aux2) != 0)
			{
				return false;
			}
		}
		
		return true;
			
	}
	
}

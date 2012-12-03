package fr.obsmip.sedoo.core.dao;

import java.util.ArrayList;
import java.util.List;

import fr.obsmip.sedoo.core.domain.ThesaurusItem;

public class ClimateThesaurusDAO implements ThesaurusDAO
{

	@Override
	public List<ThesaurusItem> getThesaurus(String locale) {
		List<ThesaurusItem> result = new ArrayList<ThesaurusItem>();
		if (locale.compareToIgnoreCase("fr")==0)
		{
			result.add(new ThesaurusItem("1", "MonClimat 1"));
			result.add(new ThesaurusItem("2", "MonClimat 2"));
			result.add(new ThesaurusItem("3", "MonClimat 3"));
		}
		else
		{
			result.add(new ThesaurusItem("1", "MyClimate 1"));
			result.add(new ThesaurusItem("2", "MyClimate 2"));
			result.add(new ThesaurusItem("3", "MyClimate 3"));
		}
		return result;
	}
	
}

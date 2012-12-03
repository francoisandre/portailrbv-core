package fr.obsmip.sedoo.core.dao;

import java.util.ArrayList;
import java.util.List;

import fr.obsmip.sedoo.core.domain.ThesaurusItem;

public class LithologyThesaurusDAO implements ThesaurusDAO
{

	@Override
	public List<ThesaurusItem> getThesaurus(String locale) {
		List<ThesaurusItem> result = new ArrayList<ThesaurusItem>();
		if (locale.compareToIgnoreCase("fr")==0)
		{
			result.add(new ThesaurusItem("1", "MaLytho 1"));
			result.add(new ThesaurusItem("2", "MaLytho 2"));
			result.add(new ThesaurusItem("3", "MaLytho 3"));
		}
		else
		{
			result.add(new ThesaurusItem("1", "MyLytho 1"));
			result.add(new ThesaurusItem("2", "MyLytho 2"));
			result.add(new ThesaurusItem("3", "MyLytho 3"));
		}
		return result;
	}
	
}

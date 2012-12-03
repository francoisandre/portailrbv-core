package fr.obsmip.sedoo.core.dao;

import java.util.List;

import fr.obsmip.sedoo.core.domain.ThesaurusItem;

public interface ThesaurusDAO {

	List<ThesaurusItem> getThesaurus(String locale);

}

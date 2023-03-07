package tn.esprit.biol.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.biol.dao.SearchDao;
import tn.esprit.biol.dao.UserDao;
import tn.esprit.biol.entity.Search;
import tn.esprit.biol.entity.Training;

import java.util.List;

@Service
public class SearchService implements ISearchService{

    @Autowired
    UserDao userDao;
    @Autowired
    SearchDao searchDao;
    @Override
    public Search addSearch(String word,String iduser)
    {

       Search search= new Search();
    search.setExpression(word);
    search.setUser(userDao.findById(iduser).get());
    searchDao.save(search);
    return search;
    }


    @Override
    public List<Search>  getAllSearch()
    {
        return searchDao.findAll();
    }
}

package tn.esprit.biol.service;

import tn.esprit.biol.entity.Search;

import java.util.List;

public interface ISearchService {
    public Search addSearch(String word, String iduser);
    List<Search> getAllSearch();
}

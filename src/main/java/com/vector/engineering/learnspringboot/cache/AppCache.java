package com.vector.engineering.learnspringboot.cache;

import com.vector.engineering.learnspringboot.repository.ConfigJournalAppRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.HashMap;
import java.util.Map;

@Component
public class AppCache {

    public enum KEYS {
        quotes_api,
    }


    @Autowired
    private ConfigJournalAppRepository configJournalAppRepository;

    public Map<String, String> appCacheMap;

    @PostConstruct
    public void init()
    {
        appCacheMap = new HashMap<>();  // we want everytime this init invoke, map should recreate(otherwise multiple entries ho jayegi)
        // get the Config data from the DB and load it to the map
        configJournalAppRepository.findAll().forEach(configJournalApp -> {
            appCacheMap.put(configJournalApp.getKey(), configJournalApp.getValue());
        });
        System.out.println("App Cache initialized with data: " + appCacheMap.size());
    }
}

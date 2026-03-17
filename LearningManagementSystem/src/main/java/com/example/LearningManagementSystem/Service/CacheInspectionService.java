package com.example.LearningManagementSystem.Service;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CacheInspectionService {
    private final CacheManager cacheManager;

    public CacheInspectionService(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }
    public void printCache(String cachename){
        Cache cache= cacheManager.getCache(cachename);
        if(cache!=null){
            System.out.println("Cache contents:");
            System.out.println(Objects.requireNonNull(cache.getNativeCache().toString()));
        }else{
            System.out.println("No such cache:"+cachename);
        }
    }
}

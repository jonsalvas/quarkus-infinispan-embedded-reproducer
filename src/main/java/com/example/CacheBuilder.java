package com.example;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Named;
import org.infinispan.Cache;
import org.infinispan.commons.marshall.JavaSerializationMarshaller;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.configuration.cache.IndexStorage;
import org.infinispan.configuration.global.GlobalConfigurationBuilder;
import org.infinispan.manager.DefaultCacheManager;
@ApplicationScoped
public class CacheBuilder {
  @Produces
  Cache<String, Object> produceFoo() {
    GlobalConfigurationBuilder global = new GlobalConfigurationBuilder();
    global.serialization().marshaller(new JavaSerializationMarshaller());

    ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
    configurationBuilder.indexing().enable().addIndexedEntity(TestModel.class) .storage(IndexStorage.LOCAL_HEAP);

    var cacheManager = new DefaultCacheManager(global.build());
    Cache<String, Object> cache;
    if (!cacheManager.cacheExists("cache")) {
      cacheManager.createCache("cache", configurationBuilder.build());
    }
    cache = cacheManager.getCache("cache");
    return cache;
  }
}

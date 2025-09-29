package com.example;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

import org.infinispan.Cache;
import org.infinispan.commons.api.CacheContainerAdmin;
import org.infinispan.configuration.cache.CacheMode;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.manager.EmbeddedCacheManager;

import io.quarkiverse.infinispan.embedded.Embedded;

@Path("/")
public class TarifResource {

   @Inject
   @Embedded("queryCache")
   Cache<String, TestModel> queryCache;

   @Inject
   EmbeddedCacheManager cacheManager;

   @GET
   @Path("/test")
   public Response test() {
      var model = new TestModel();
      model.setId(1);
      model.setValue(12.1);
      queryCache.put("Hello", model);
      return Response.ok(queryCache.query("from com.example.TestModel where id=1").list()).build();
   }

   @GET
   @Path("/test2")
   public Response test2() {
      Cache<Object, Object> cache = cacheManager.administration()
            .withFlags(CacheContainerAdmin.AdminFlag.VOLATILE)
            .getOrCreateCache("createOnTheFlyCache", new ConfigurationBuilder()
                  .clustering().cacheMode(CacheMode.LOCAL)
                  .indexing().enable().addIndexedEntities(TestModel.class)
                  .build());

      var model = new TestModel();
      model.setId(2);
      model.setValue(45.2);
      cache.put("Hello", model);

      return Response.ok(cache.query("from com.example.TestModel where id=2").list()).build();
   }
}

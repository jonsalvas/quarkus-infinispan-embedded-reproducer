package com.example;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.infinispan.commons.marshall.JavaSerializationMarshaller;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.configuration.global.GlobalConfigurationBuilder;
import org.infinispan.manager.DefaultCacheManager;

@Path("/")
public class TarifResource {

  @POST
  @Path("/test")
  public Response test() {

    GlobalConfigurationBuilder global = new GlobalConfigurationBuilder();
    global.serialization().marshaller(new JavaSerializationMarshaller());

    ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
    configurationBuilder.indexing().enable().addIndexedEntities(TestModel.class);

    var cacheManager = new DefaultCacheManager(global.build());
    var cache = cacheManager.createCache("cache", configurationBuilder.build());

    var model = new TestModel();
    model.id = 1;
    cache.put("Hello", model);

    var queriedModel = cache.query("from cache.TestModel where id=1");
    return Response.ok().build();
  }
}

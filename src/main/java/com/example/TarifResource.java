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

    cache.put("Hello", model);
    return Response.ok().build();
  }
}

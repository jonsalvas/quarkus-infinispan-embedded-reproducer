package com.example;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.infinispan.Cache;
@Path("/")
public class TarifResource {

  @Inject
  Cache<String,Object> cache;
  @POST
  @Path("/test")
  public Response test() {



    var model = new TestModel();
    model.id = 1;
    cache.put("Hello", model);

    var queriedModel = cache.query("from com.example.TestModel where id=1");
    System.out.println(queriedModel.execute());
    return Response.ok().build();
  }
}

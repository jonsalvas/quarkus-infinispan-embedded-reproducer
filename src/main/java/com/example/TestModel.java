package com.example;

import org.infinispan.api.annotations.indexing.Basic;
import org.infinispan.api.annotations.indexing.Indexed;
import org.infinispan.protostream.annotations.Proto;

@Proto
@Indexed
public class TestModel {

  @Basic Integer id;

  Double value;
}

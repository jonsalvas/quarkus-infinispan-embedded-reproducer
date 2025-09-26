package com.example;

import org.infinispan.protostream.GeneratedSchema;
import org.infinispan.protostream.annotations.ProtoSchema;
import org.infinispan.protostream.annotations.ProtoSyntax;

@ProtoSchema(
    syntax = ProtoSyntax.PROTO3,
    dependsOn = {
      org.infinispan.protostream.types.java.CommonTypes.class,
      org.infinispan.protostream.types.java.CommonContainerTypes.class
    },
    includeClasses = {TestModel.class},
    schemaPackageName = "cache")
interface CacheSchema extends GeneratedSchema {}

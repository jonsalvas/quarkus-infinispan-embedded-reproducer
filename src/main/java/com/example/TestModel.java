package com.example;

import org.infinispan.api.annotations.indexing.Basic;
import org.infinispan.api.annotations.indexing.Indexed;

@Indexed
public class TestModel {

  @Basic
  private Integer id;

  private Double value;


   public Integer getId() {
      return id;
   }

   public void setId(int i) {
      this.id = i;
   }

   public void setValue(Double value) {
      this.value = value;
   }

   public Double getValue() {
      return value;
   }
}

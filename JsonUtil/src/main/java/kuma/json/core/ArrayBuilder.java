package main.java.kuma.json.core;

import java.util.LinkedList;
import java.util.List;

import main.java.kuma.json.model.JsonArray;
import main.java.kuma.json.model.JsonBoolean;
import main.java.kuma.json.model.JsonNumber;
import main.java.kuma.json.model.JsonObject;
import main.java.kuma.json.model.JsonString;
import main.java.kuma.json.model.JsonValue;

public class ArrayBuilder {
   // ---------------------------------------------------------------
   private List<JsonValue> list;
   
   // ---------------------------------------------------------------
   public ArrayBuilder() {
      this.list = new LinkedList<JsonValue>();
   }
   
   // ---------------------------------------------------------------
   public ArrayBuilder addObject(JsonObject value) {
      this.list.add(value);
      return this;
   }
   public ArrayBuilder addArray(JsonArray value) {
      this.list.add(value);
      return this;
   }
   public ArrayBuilder addString(String value) {
      this.list.add(new JsonString(value));
      return this;
   }
   public ArrayBuilder addBoolean(Boolean value) {
      this.list.add(new JsonBoolean(value));
      return this;
   }
   public ArrayBuilder addNumber(Number value) {
      this.list.add(new JsonNumber(value));
      return this;
   }
   
   // ---------------------------------------------------------------
   public JsonArray build() {
      return new JsonArray(this.list);
   }
}

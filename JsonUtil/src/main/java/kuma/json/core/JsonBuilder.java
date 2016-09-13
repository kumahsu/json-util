package main.java.kuma.json.core;

import java.util.LinkedList;
import java.util.List;

import main.java.kuma.json.model.JsonArray;
import main.java.kuma.json.model.JsonBoolean;
import main.java.kuma.json.model.JsonNull;
import main.java.kuma.json.model.JsonNumber;
import main.java.kuma.json.model.JsonObject;
import main.java.kuma.json.model.JsonPair;
import main.java.kuma.json.model.JsonString;

public class JsonBuilder {
   // ---------------------------------------------------------------
   private List<JsonPair> pairs;
   
   // ---------------------------------------------------------------
   public JsonBuilder() {
      this.pairs = new LinkedList<JsonPair>();
   }
   
   // ---------------------------------------------------------------
   public JsonBuilder appendObject(String name, JsonObject value) {
      this.pairs.add(new JsonPair(name, value));
      return this;
   }
   public JsonBuilder appendArray(String name, JsonArray value) {
      this.pairs.add(new JsonPair(name, value));
      return this;
   }
   public JsonBuilder appendString(String name, String value) {
      this.pairs.add(new JsonPair(name, new JsonString(value)));
      return this;
   }
   public JsonBuilder appendNumber(String name, Number value) {
      this.pairs.add(new JsonPair(name, new JsonNumber(value)));
      return this;
   }
   public JsonBuilder appendBoolean(String name, Boolean value) {
      this.pairs.add(new JsonPair(name, new JsonBoolean(value)));
      return this;
   }
   public JsonBuilder appendNull(String name) {
      this.pairs.add(new JsonPair(name, JsonNull.VALUE));
      return this;
   }
   
   // ---------------------------------------------------------------
   public JsonObject build() {
      return new JsonObject(this.pairs.toArray(new JsonPair[this.pairs.size()]));
   }
}

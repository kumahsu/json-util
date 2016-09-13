package main.java.kuma.json.model;

public class JsonNull extends JsonValue {
   // ---------------------------------------------------------------
   public static final JsonNull VALUE = new JsonNull();
   
   // ---------------------------------------------------------------
   private JsonNull() { }
   
   // ---------------------------------------------------------------
   @Override
   public String toJson() {
      return "null";
   }

   @Override
   public String toPrettyJson(int indent) {
      return this.toJson();
   }
}

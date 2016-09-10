package java.main.kuma.json.model;

public class JsonPair {
   // ---------------------------------------------------------------
   private String name;
   private JsonValue value;
   
   // ---------------------------------------------------------------
   public JsonPair(String name, JsonValue value) {
      if(name == null) {
         throw new IllegalArgumentException("key is null");
      }
      if(value == null) {
         throw new IllegalArgumentException("value is null");
      }
      this.name = name;
      this.value = value;
   }
   
   // ---------------------------------------------------------------
   public String getName() {
      return this.name;
   }
   public JsonValue getValue() {
      return this.value;
   }
   
   // ---------------------------------------------------------------
   public String toJson() {
      StringBuilder str = new StringBuilder();
      str.append("\"").append(this.name).append("\"").append(":")
         .append(this.value.toJson());
      return str.toString();
   }
   public String toPrettyJson(int indent) {
      StringBuilder str = new StringBuilder();
      str.append(JsonValue.indentOf(indent))
         .append("\"").append(this.name).append("\"").append(":")
         .append(this.value.toPrettyJson(indent));
      return str.toString();
   }
   
   // ---------------------------------------------------------------
   @Override
   public String toString() {
      return this.toPrettyJson(0);
   }
}

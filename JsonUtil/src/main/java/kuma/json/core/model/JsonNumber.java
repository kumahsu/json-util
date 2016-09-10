package main.java.kuma.json.core.model;

public class JsonNumber extends JsonValue {
   // ---------------------------------------------------------------
   private Number value;
   
   // ---------------------------------------------------------------
   public JsonNumber(Number value) {
      if(value == null) {
         throw new IllegalArgumentException("value is null");
      }
      this.value = value;
   }
   
   // ---------------------------------------------------------------
   public Number getValue() {
      return this.value;
   }
   
   // ---------------------------------------------------------------
   @Override
   public String toJson() {
      return String.valueOf(this.value);
   }

   @Override
   public String toPrettyJson(int indent) {
      return this.toJson();
   }
}

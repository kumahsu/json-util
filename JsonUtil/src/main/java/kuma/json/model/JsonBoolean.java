package main.java.kuma.json.model;

public class JsonBoolean extends JsonValue {
   // ---------------------------------------------------------------
   private Boolean value;
   
   // ---------------------------------------------------------------
   public JsonBoolean(Boolean value) {
      if(value == null) {
         throw new IllegalArgumentException("value is null");
      }
      this.value = value;
   }
   
   // ---------------------------------------------------------------
   public Boolean getValue() {
      return this.value;
   }
   
   // ---------------------------------------------------------------
   @Override
   public String toJson() {
      return String.valueOf(this.value).toLowerCase();
   }

   @Override
   public String toPrettyJson(int indent) {
      return this.toJson();
   }
}

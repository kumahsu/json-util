package java.main.kuma.json.model;

public class JsonObject extends JsonValue {
   // ---------------------------------------------------------------
   private JsonPair[] pairs;
   
   // ---------------------------------------------------------------
   public JsonObject(JsonPair[] pairs) {
      if(pairs == null) {
         throw new IllegalArgumentException("pairs is null");
      }
      this.pairs = pairs;
   }
   
   // ---------------------------------------------------------------
   public boolean isEmpty() {
      return this.pairs.length == 0;
   }
   public JsonValue getValue(String name) {
      for(int i = 0; i < this.pairs.length; i++) {
         if(this.pairs[i].getName().equals(name)) {
            return this.pairs[i].getValue();
         }
      }
      return null;
   }
   public boolean existsField(String name) {
      return this.getValue(name) != null;
   }
   public boolean isNull(String name) {
      return this.getValue(name) == JsonNull.VALUE;
   }
   
   // ---------------------------------------------------------------
   public JsonObject getObject(String name) {
      JsonValue value = this.getValue(name);
      if(value == null) {
         throw new IllegalArgumentException("Field does not exist, " + name + ", " + this);
      }
      if (!(value instanceof JsonObject)) {
         throw new IllegalArgumentException("Value is not an object, " + value);
      }
      return (JsonObject)value;
   }
   public JsonArray getArray(String name) {
      JsonValue value = this.getValue(name);
      if(value == null) {
         throw new IllegalArgumentException("Field does not exist, " + name + ", " + this);
      }
      if(!(value instanceof JsonArray)) {
         throw new IllegalArgumentException("Value is not an Array, " + value);
      }
      return (JsonArray)value;
   }
   public Number getNumber(String name) {
      JsonValue value = this.getValue(name);
      if(value == null) {
         throw new IllegalArgumentException("Field does not exist, " + name + ", " + this);
      }
      if(!(value instanceof JsonNumber)) {
         throw new IllegalArgumentException("Value is not a Number, " + value);
      }
      return ((JsonNumber)value).getValue();
   }
   public String getString(String name) {
      JsonValue value = this.getValue(name);
      if(value == null) {
         throw new IllegalArgumentException("Field does not exist, " + name + ", " + this);
      }
      if(!(value instanceof JsonString)) {
         throw new IllegalArgumentException("Value is not a String, " + value);
      }
      return ((JsonString)value).getValue();
   }
   public Boolean getBoolean(String name) {
      JsonValue value = this.getValue(name);
      if(value == null) {
         throw new IllegalArgumentException("Field does not exist, " + name + ", " + this);
      }
      if(!(value instanceof JsonBoolean)) {
         throw new IllegalArgumentException("Value is not a Boolean, " + value);
      }
      return ((JsonBoolean)value).getValue();
   }
   
   // ---------------------------------------------------------------
   @Override
   public String toJson() {
      StringBuilder str = new StringBuilder();
      str.append("{");
      for(int i = 0; i < this.pairs.length; i++) {
         if(i != 0) {
            str.append(",");
         }
         str.append(this.pairs[i].toJson());
      }
      str.append("}");
      return str.toString();
   }

   @Override
   public String toPrettyJson(int indent) {
      StringBuilder str = new StringBuilder();
      str.append(NEW_LINE).append(indentOf(indent)).append("{").append(NEW_LINE);
      for(int i = 0; i < this.pairs.length; i++) {
         if(i != 0) {
            str.append(",").append(NEW_LINE);
         }
         str.append(this.pairs[i].toPrettyJson(indent + 1));
      }
      str.append(NEW_LINE).append(indentOf(indent)).append("}");
      return str.toString();
   }
}

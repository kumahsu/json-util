package main.java.kuma.json.model;

public class JsonString extends JsonValue {
   // ---------------------------------------------------------------
   private static String escapeJson(char value) {
      switch (value) {
      case '"': return "\\\"";
      case '\\': return "\\\\";
      case '/': return "\\/";
      case '\b': return "\\b";
      case '\f': return "\\f";
      case '\n': return "\\n";
      case '\r': return "\\r";
      case '\t': return "\\t";
         default: return String.valueOf(value);
      }
   }
   public static String EscapeJson(String value) {
      StringBuilder str = new StringBuilder();
      for (int i = 0; i < value.length(); i++) {
         str.append(escapeJson(value.charAt(i)));
      }
      return str.toString();
   }

   // ---------------------------------------------------------------
   private String value;
   
   // ---------------------------------------------------------------
   public JsonString(String value) {
      if(value == null) {
         throw new IllegalArgumentException("value is null");
      }
      this.value = value;
   }
   
   // ---------------------------------------------------------------
   public String getValue() {
      return this.value;
   }
   
   // ---------------------------------------------------------------
   @Override
   public String toJson() {
      StringBuilder str = new StringBuilder();
      str.append("\"").append(EscapeJson(this.value)).append("\"");
      return str.toString();
   }

   @Override
   public String toPrettyJson(int indent) {
      return this.toJson();
   }
}

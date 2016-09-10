package main.java.kuma.json.model;

public abstract class JsonValue {
   // ---------------------------------------------------------------
   public static final String NEW_LINE = System.getProperty("line.separator");
   public static final String INDENT = "   ";
   
   // ---------------------------------------------------------------
   public static String indentOf(int indent) {
      StringBuilder str = new StringBuilder();
      for(int i = 0; i < indent; i++) {
         str.append(INDENT);
      }
      return str.toString();
   }
   
   // ---------------------------------------------------------------
   public abstract String toJson();
   public abstract String toPrettyJson(int indent);
   public String toPrettyJson() {
      return this.toPrettyJson(0);
   }
   
   // ---------------------------------------------------------------
   @Override
   public String toString() {
      return this.toPrettyJson();
   }
}

package java.main.kuma.json.model;

import java.util.Collection;
import java.util.Iterator;

public class JsonArray extends JsonValue {
   // ---------------------------------------------------------------
   private Collection<JsonValue> values;
   
   // ---------------------------------------------------------------
   public JsonArray(Collection<JsonValue> values) {
      if(values == null) {
         throw new IllegalArgumentException("values == null");
      }
      this.values = values;
   }
   
   // ---------------------------------------------------------------
   public Collection<JsonValue> getValues() {
      return this.values;
   }
   
   // ---------------------------------------------------------------
   @Override
   public String toJson() {
      StringBuilder str = new StringBuilder();
      str.append("[");
      for(Iterator<JsonValue> i = this.values.iterator(); i.hasNext(); ) {
         str.append(i.next().toJson());
         if(i.hasNext()) {
            str.append(",");
         }
      }
      str.append("]");
      return str.toString();
   }

   @Override
   public String toPrettyJson(int indent) {
      StringBuilder str = new StringBuilder();
      str.append(NEW_LINE).append(indentOf(indent)).append("[").append(NEW_LINE);
      for(Iterator<JsonValue> i = this.values.iterator(); i.hasNext(); ) {
         str.append(i.next().toPrettyJson(indent + 1));
         if(i.hasNext()) {
            str.append(",").append(NEW_LINE);
         }
      }
      str.append(NEW_LINE).append(indentOf(indent)).append("]");
      return str.toString();
   }
}

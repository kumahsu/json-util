package main.java.kuma.json.lex;

public class StringToken extends JsonToken {
   // ---------------------------------------------------------------
   private String value;
   
   // ---------------------------------------------------------------
   public StringToken(String value) {
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
   public int tokenType() {
      return JsonToken.STRING;
   }

}

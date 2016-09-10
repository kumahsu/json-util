package main.java.kuma.json.lex;

public class NumberToken extends JsonToken {
   // ---------------------------------------------------------------
   private Number value;
   
   // ---------------------------------------------------------------
   public NumberToken(Number value) {
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
   public int tokenType() {
      return JsonToken.NUMBER;
   }

}

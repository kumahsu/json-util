package java.main.kuma.json.lex;

public class BooleanToken extends JsonToken {
   // ---------------------------------------------------------------
   private boolean value;
   
   // ---------------------------------------------------------------
   public BooleanToken(Boolean value) {
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
   public int tokenType() {
      return JsonToken.BOOLEAN;
   }

}

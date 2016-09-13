package main.java.kuma.json.lex;

public class NullToken extends JsonToken {
   // ---------------------------------------------------------------
   public static final NullToken VALUE = new NullToken();
   
   // ---------------------------------------------------------------
   private NullToken() { }
   
   // ---------------------------------------------------------------
   @Override
   public int tokenType() {
      return JsonToken.NULL;
   }

}

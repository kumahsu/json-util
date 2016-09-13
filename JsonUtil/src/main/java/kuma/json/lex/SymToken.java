package main.java.kuma.json.lex;

public class SymToken extends JsonToken {
   // ---------------------------------------------------------------
   private char sym;
   
   // ---------------------------------------------------------------
   public SymToken(char sym) {
      this.sym = sym;
   }
   
   // ---------------------------------------------------------------
   @Override
   public int tokenType() {
      return this.sym;
   }

}

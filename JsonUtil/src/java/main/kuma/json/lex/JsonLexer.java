package java.main.kuma.json.lex;

import java.main.kuma.json.common.StringStream;
import java.main.kuma.json.exception.UnexpectedTokenException;

public class JsonLexer {
   // ---------------------------------------------------------------
   private static final int[] NUMBER_PREFIX = new int[] {
         '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
   };
   private static final char[] NUMBER_STOP = new char[] {
         ' ', '\t', '\r', 'n', ','
   };
   
   // ---------------------------------------------------------------
   private StringStream in;
   private JsonToken lastToken;
   
   // ---------------------------------------------------------------
   public JsonLexer(String value) {
      this.in = new StringStream(value);
      this.lastToken = null;
   }
   
   // ---------------------------------------------------------------
   private StringToken _nextStringToken() {
      this.in.expect('"');
      StringBuilder str = new StringBuilder();
      while(!this.in.probe('"')) {
         if(this.in.probe(-1)) {
            throw new UnexpectedTokenException(JsonToken.tokenString(JsonToken.STRING), "EOF");
         } else {
            if(!this.in.probe('\\')) {
               str.append((char)this.in.read());
            } else {
               this.in.expect('\\');
               int read = this.in.read();
               switch(read) {
               case '"': str.append('"'); break;
               case '\\': str.append('\\'); break;
               case '/': str.append('/'); break;
               case 'b': str.append('\b'); break;
               case 'f': str.append('\f'); break;
               case 'n': str.append('\n'); break;
               case 'r': str.append('\r'); break;
               case 't': str.append('\t'); break;
                  default:
                     throw new UnexpectedTokenException("Unknown escape symbol..." + (char)read);
               }
            }
         }
      }
      this.in.expect('"');
      return new StringToken(str.toString());
   }
   private NumberToken _nextNumberToken() {
      String numStr = this.in.readTill(NUMBER_STOP);
      return new NumberToken(Double.parseDouble(numStr));
   }
   private JsonToken _nextToken() {
      this.in.skipSpTbCrLf();
      if      (this.in.probe('{')) { this.in.expect('{'); return new SymToken('{'); }
      else if (this.in.probe('}')) { this.in.expect('}'); return new SymToken('}'); }
      else if (this.in.probe('[')) { this.in.expect('['); return new SymToken('['); }
      else if (this.in.probe(']')) { this.in.expect(']'); return new SymToken(']'); }
      else if (this.in.probe(':')) { this.in.expect(':'); return new SymToken(':'); }
      else if (this.in.probe(',')) { this.in.expect(','); return new SymToken(','); }
      else if (this.in.probe('"')) { return this._nextStringToken(); }
      else if (this.in.probeIgnoreCase("true")) { this.in.expectIgnoreCase("true"); return new BooleanToken(true); }
      else if (this.in.probeIgnoreCase("false")) { this.in.expectIgnoreCase("false"); return new BooleanToken(false); }
      else if (this.in.probeIgnoreCase("null")) { this.in.expectIgnoreCase("null"); return NullToken.VALUE; }
      else if (this.in.probe(NUMBER_PREFIX)) { return this._nextNumberToken(); }
      else {
         throw new UnexpectedTokenException("Unknown token..." + (char)this.in.read());
      }
   }
   
   public JsonToken nextToken() {
      if(this.lastToken == null) {
         return this._nextToken();
      }
      else {
         JsonToken token = this.lastToken;
         this.lastToken = null;
         return token;
      }
   }
   
   // ---------------------------------------------------------------
   public boolean probeToken(int tokenType) {
      if(this.lastToken == null) {
         this.lastToken = this._nextToken();
      }
      return tokenType == this.lastToken.tokenType();
   }
   public boolean probeToken(int[] tokenTypes) {
      for(int i = 0; i < tokenTypes.length; i++) {
         if(this.probeToken(tokenTypes[i])) { return true; }
      }
      return false;
   }
   
   // ---------------------------------------------------------------
   public JsonToken expectToken(int tokenType) {
      JsonToken token = this.nextToken();
      if (tokenType != token.tokenType()) {
         throw new UnexpectedTokenException(JsonToken.tokenString(tokenType), JsonToken.tokenString(token.tokenType()));
      }
      return token;
   }
}

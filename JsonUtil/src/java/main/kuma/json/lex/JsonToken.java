package java.main.kuma.json.lex;

import java.main.kuma.json.exception.UnexpectedTokenException;

public abstract class JsonToken {
   // ---------------------------------------------------------------
   public static final int LBRACE = '{';
   public static final int RBRACE = '}';
   public static final int LBRACKET = '[';
   public static final int RBRACKET = ']';
   public static final int COLON = ':';
   public static final int COMMA = ',';

   public static final int STRING = 0x0101;
   public static final int NAME = 0x0101;
   public static final int NUMBER = 0x0102;
   public static final int NULL = 0x0103;
   public static final int BOOLEAN = 0x0104;
   
   // ---------------------------------------------------------------
   public static String tokenString(int tokenType) {
      switch(tokenType) {
      case LBRACE: return "{";
      case RBRACE: return "}";
      case LBRACKET: return "[";
      case RBRACKET: return "]";
      case COLON: return ":";
      case COMMA: return ",";
      case STRING: return "string";
      case NUMBER: return "number";
      case NULL: return "null";
      case BOOLEAN: return "boolean";
         default:
            throw new UnexpectedTokenException("Unknown token type..." + tokenType);
      }
   }
   
   // ---------------------------------------------------------------
   protected JsonToken() { };
   
   // ---------------------------------------------------------------
   public abstract int tokenType();
}

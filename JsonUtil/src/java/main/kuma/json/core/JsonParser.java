package java.main.kuma.json.core;

import java.main.kuma.json.exception.UnexpectedTokenException;
import java.main.kuma.json.lex.BooleanToken;
import java.main.kuma.json.lex.JsonLexer;
import java.main.kuma.json.lex.JsonToken;
import java.main.kuma.json.lex.NumberToken;
import java.main.kuma.json.lex.StringToken;
import java.main.kuma.json.model.JsonArray;
import java.main.kuma.json.model.JsonBoolean;
import java.main.kuma.json.model.JsonNull;
import java.main.kuma.json.model.JsonNumber;
import java.main.kuma.json.model.JsonObject;
import java.main.kuma.json.model.JsonPair;
import java.main.kuma.json.model.JsonString;
import java.main.kuma.json.model.JsonValue;
import java.util.LinkedList;
import java.util.List;

public class JsonParser {
   // ---------------------------------------------------------------
   private static String expectName(JsonLexer lexer) {
      StringToken strToken = (StringToken)lexer.expectToken(JsonToken.STRING);
      return strToken.getValue();
   }
   
   private static JsonValue expectValue(JsonLexer lexer) {
      if      (lexer.probeToken(JsonToken.LBRACE)) { return expectObject(lexer); }
      else if (lexer.probeToken(JsonToken.LBRACKET)) { return expectArray(lexer); }
      else if (lexer.probeToken(JsonToken.STRING)) { 
         StringToken token = (StringToken)lexer.expectToken(JsonToken.STRING);
         return new JsonString(token.getValue());
      }
      else if (lexer.probeToken(JsonToken.NUMBER)) {
         NumberToken token = (NumberToken)lexer.expectToken(JsonToken.NUMBER);
         return new JsonNumber(token.getValue());
      }
      else if (lexer.probeToken(JsonToken.BOOLEAN)) {
         BooleanToken token = (BooleanToken)lexer.expectToken(JsonToken.BOOLEAN);
         return new JsonBoolean(token.getValue());
      }
      else if (lexer.probeToken(JsonToken.NULL)) {
         lexer.expectToken(JsonToken.NULL);
         return JsonNull.VALUE;
      }
      else {
         throw new UnexpectedTokenException("value", JsonToken.tokenString(lexer.nextToken().tokenType()));
      }
   }
   
   private static JsonObject expectObject(JsonLexer lexer) {
      lexer.expectToken(JsonToken.LBRACE);
      List<JsonPair> pairs = new LinkedList<JsonPair>();
      if(!lexer.probeToken(JsonToken.RBRACE)) {
         while(true) {
            String name = expectName(lexer);
            lexer.expectToken(JsonToken.COLON);
            JsonValue value = expectValue(lexer);
            pairs.add(new JsonPair(name, value));
            if(!lexer.probeToken(JsonToken.COMMA)) {
               break;
            }
            lexer.expectToken(JsonToken.COMMA);
         }
      }
      lexer.expectToken(JsonToken.RBRACE);
      return new JsonObject(pairs.toArray(new JsonPair[pairs.size()]));
   }
   
   private static JsonArray expectArray(JsonLexer lexer) {
      lexer.expectToken(JsonToken.LBRACKET);
      List<JsonValue> values = new LinkedList<JsonValue>();
      if(!lexer.probeToken(JsonToken.RBRACKET)) {
         while(true) {
            values.add(expectValue(lexer));
            if(!lexer.probeToken(JsonToken.COMMA)) {
               break;
            }
            lexer.expectToken(JsonToken.COMMA);
         }
      }
      lexer.expectToken(JsonToken.RBRACKET);
      return new JsonArray(values);
   }
   
   // ---------------------------------------------------------------
   public static JsonObject parseObject(String json) {
      JsonLexer lexer = new JsonLexer(json);
      return expectObject(lexer);
   }
   public static JsonArray parseArray(String json) {
      JsonLexer lexer = new JsonLexer(json);
      return expectArray(lexer);
   }
}

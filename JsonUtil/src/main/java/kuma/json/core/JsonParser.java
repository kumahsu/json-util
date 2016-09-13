package main.java.kuma.json.core;

import java.util.LinkedList;
import java.util.List;

import main.java.kuma.json.exception.UnexpectedTokenException;
import main.java.kuma.json.lex.BooleanToken;
import main.java.kuma.json.lex.JsonLexer;
import main.java.kuma.json.lex.JsonToken;
import main.java.kuma.json.lex.NumberToken;
import main.java.kuma.json.lex.StringToken;
import main.java.kuma.json.model.JsonArray;
import main.java.kuma.json.model.JsonBoolean;
import main.java.kuma.json.model.JsonNull;
import main.java.kuma.json.model.JsonNumber;
import main.java.kuma.json.model.JsonObject;
import main.java.kuma.json.model.JsonPair;
import main.java.kuma.json.model.JsonString;
import main.java.kuma.json.model.JsonValue;

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

package java.main.kuma.json.exception;

public class UnexpectedTokenException extends RuntimeException {
   // ---------------------------------------------------------------
   private static final long serialVersionUID = 6858953949699227539L;
   
   // ---------------------------------------------------------------
   static String getErrorMessage(String expectedToken, String actualToken) {
      StringBuilder str = new StringBuilder();
      str.append("Expecting: [").append(expectedToken).append("],")
         .append("but receiveing: [").append(actualToken).append("]");
      return str.toString();
   }
   
   // ---------------------------------------------------------------
   public UnexpectedTokenException(String message) {
      super(message);
   }
   public UnexpectedTokenException(String expectedToken, String actualToken) {
      this(getErrorMessage(expectedToken, actualToken));
   }
}

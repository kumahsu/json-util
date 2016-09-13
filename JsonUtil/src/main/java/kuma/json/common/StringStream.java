package main.java.kuma.json.common;

import main.java.kuma.json.exception.UnexpectedTokenException;

public class StringStream {
   // ---------------------------------------------------------------
   private static final char[] SpTbCrLf = new char[] { ' ', '\t', '\r', '\n' };
   
   // ---------------------------------------------------------------
   private char[] values;
   private int idx;
   
   // ---------------------------------------------------------------
   public StringStream(char[] values) {
      if(values == null) {
         throw new IllegalArgumentException("values is null");
      }
      this.values = values;
      this.idx = 0;
   }
   public StringStream(String value) {
      this(value.toCharArray());
   }
   
   // ---------------------------------------------------------------
   public StringStream skip(char[] values) {
      boolean match = true;
      while(this.idx < this.values.length && match) {
         match = false;
         for(int i = 0; i < values.length; i++) {
            if(this.values[this.idx] == values[i]) {
               match = true;
               break;
            }
         }
         if(match) {
            this.idx++;
         }
      }
      return this;
   }
   public StringStream skipSpTbCrLf() {
      return this.skip(SpTbCrLf);
   }
   
   // ---------------------------------------------------------------
   public int read() {
      return (this.idx >= this.values.length)? -1 : this.values[this.idx++];
   }
   
   // ---------------------------------------------------------------
   public boolean match(int read, char[] values) {
      for(int i = 0; i < values.length; i++) {
         if(read == values[i]) { 
            return true;
         }
      }
      return false;
   }
   public String readTill(char[] stops) {
      StringBuilder str = new StringBuilder();
      int read = this.read();
      while(read != -1 && !this.match(read, stops)) {
         str.append((char)read);
         read = this.read();
      }
      if(read != -1) {
         this.idx--;
      }
      return str.toString();
   }
   
   // ---------------------------------------------------------------
   public boolean probe(int value) {
      return this.values[this.idx] == value;
   }
   public boolean probe(int[] values) {
      for(int i = 0; i < values.length; i++) {
         if(this.values[this.idx] == values[i]) {
            return true;
         }
      }
      return false;
   }
   private boolean _probe(String value, boolean ignoreCase) {
      for(int i = this.idx, j = 0; 
          i < this.values.length && j < value.length(); 
          i++, j++) 
      {
         if(ignoreCase) {
            if(!String.valueOf(value.charAt(j)).equalsIgnoreCase(String.valueOf((char)this.values[i]))) {
               return false;
            }
         }
         else {
            if(this.values[i] != value.charAt(j)) {
               return false;
            }
         }
      }
      return true;
   }
   public boolean probe(String value) {
      return this._probe(value, false);
   }
   public boolean probeIgnoreCase(String value) {
      return this._probe(value, true);
   }
   
   // ---------------------------------------------------------------
   public StringStream expect(int value) {
      int read = this.read();
      if(value != read) {
         throw new UnexpectedTokenException(String.valueOf((char)value), String.valueOf((char)read));
      }
      return this;
   }
   private StringStream _expect(String value, boolean ignoreCase) {
      for(int i = 0; i < value.length(); i++) {
         if(ignoreCase) {
            int read = this.read();
            if(!String.valueOf(value.charAt(i)).equalsIgnoreCase(String.valueOf((char)read))) {
               throw new UnexpectedTokenException(value, String.valueOf((char)read));
            }
         } else {
            this.expect(value.charAt(i));
         }
      }
      return this;
   }
   public StringStream expect(String value) {
      return this._expect(value, false);
   }
   public StringStream expectIgnoreCase(String value) {
      return this._expect(value, true);
   }
}

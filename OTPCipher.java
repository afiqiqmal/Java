/*
   modified by hafiq
   One time pad cipher and decrypt
   any expert can advise me to improve this code...tq..
*/
import java.util.*;

public class OTPCipher{
   public static void main(String[] args){
      String text = "REPEAT ATTACK TONIGHT";
      String key = RandomAlpha(3);
      String k = "";
      int len = text.length();
      if(key.length()<len){
         int count = 0;
         for(int x=0;x<len;x++){  
            if(count == key.length()) 
               count = 0;
               
            k = k+key.charAt(count);
            count++;
         }
         key = k;
      }
      
      String enc = OTPEncryption(text,key);
      System.out.println("Plaintext : "+text);
      System.out.println("Encrypted : "+enc);
      System.out.println("Decrypted : "+OTPDecryption(enc,key));
   }
   
   public static String RandomAlpha(int len){
      Random r = new Random();
      String key = "";
      for(int x=0;x<len;x++)
         key = key + (char) (r.nextInt(26) + 'A');
      return key;
   }
   
   public static String OTPEncryption(String text,String key){
      String alphaU = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
      String alphaL = "abcdefghijklmnopqrstuvwxyz";
      
      int len = text.length();

      String sb = "";
      for(int x=0;x<len;x++){
         char get = text.charAt(x);
         char keyget = key.charAt(x);
         if(Character.isUpperCase(get)){
            int index = alphaU.indexOf(get);
            int keydex = alphaU.indexOf(Character.toUpperCase(keyget));
            
            int total = (index + keydex) % 26;
            
            sb = sb+ alphaU.charAt(total);
         }
         else if(Character.isLowerCase(get)){
            int index = alphaL.indexOf(get);
            int keydex = alphaU.indexOf(Character.toLowerCase(keyget));
            
            int total = (index + keydex) % 26;
            
            sb = sb+ alphaL.charAt(total);
         }
         else{
            sb = sb + get;
         }
      }
      
      return sb;
   }
   public static String OTPDecryption(String text,String key){
      String alphaU = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
      String alphaL = "abcdefghijklmnopqrstuvwxyz";
      
      int len = text.length();
      
      String sb = "";
      for(int x=0;x<len;x++){
         char get = text.charAt(x);
         char keyget = key.charAt(x);
         if(Character.isUpperCase(get)){
            int index = alphaU.indexOf(get);
            int keydex = alphaU.indexOf(Character.toUpperCase(keyget));

            int total = (index - keydex) % 26;
            total = (total<0)? total + 26 : total;
            
            sb = sb+ alphaU.charAt(total);
         }
         else if(Character.isLowerCase(get)){
            int index = alphaL.indexOf(get);
            int keydex = alphaU.indexOf(Character.toLowerCase(keyget));
            
            int total = (index - keydex) % 26;
            total = (total<0)? total + 26 : total;
            
            sb = sb+ alphaL.charAt(total);
         }
         else{
            sb = sb + get;
         }
      }
      
      return sb;
   }
   
}
import java.util.Scanner;
public class Asiri_Norah_Palindrome_Assignment {

   public static void main(String[]args)
   {
       Scanner scanner = new Scanner(System.in);
       System.out.println("Enter the word:");
       String string = scanner.nextLine();

       if(Palindrome_Assignment(string)==true)
           System.out.println(string + " is a palindrome");
       else
           System.out.println(string + " is not a palindrome");
   }

        public static boolean Palindrome_Assignment(String word)
        {
            if(word.length() == 0 || word.length() == 1)
                return true;
            if(word.charAt(0) == word.charAt(word.length()-1))
                return Palindrome_Assignment(word.substring(1, word.length()-1));
            return false;
        }


    }
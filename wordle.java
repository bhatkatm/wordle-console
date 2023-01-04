/**
 * 
 */
package wordleButMultipleTimes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * @author tanma
 *
 */
public class wordle {

    /**
     * @param args
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("This is BHAT-KARDLE");

        ArrayList<String> wordBank = readAppsFromDictionary("english.txt");

        Random rand = new Random(); // instance of random class
        int upperbound = wordBank.size();
        // generate random values from 0-wordBank's size-1
        int int_random = rand.nextInt(upperbound) + 1;

        boolean hasWon = false;

        String answer = wordBank.get(int_random);
        String known = "-----";
        String used = "";
        String notUsed = "abcdefghijklmnopqrstuvwxyz";

        // System.out.println(answer);

        for (int i = 1; i <= 6; i++) {
            System.out.println("Attempt #" + i);
            String attempt = sc.nextLine();

            for (int j = 1; j <= 6; j--) {
                if (attempt.length() != 5) {
                    System.out.println("Guess has to be 5 letters only.");
                    attempt = sc.nextLine();
                } else if (wordBank.contains(attempt) == false) {
                    System.out.println("Has to be actual word. Guess again!");
                    attempt = sc.nextLine();
                } else {

                    break;
                }
            }

            if (attempt.equalsIgnoreCase(answer)) {
                System.out.println("You Win!!!");
                hasWon = true;
                break;
            }

            for (int a = 0; a <= 4; a++) {
                // System.out.println(notUsed);
                // System.out.println(attempt.charAt(a));
                notUsed = notUsed.replace(attempt.charAt(a), '-');
                if (used.indexOf(attempt.charAt(a)) == -1)
                    used += attempt.charAt(a);
                // System.out.println(notUsed);
                if (answer.indexOf(attempt.charAt(a)) != -1) {
                    if (answer.charAt(a) == attempt.charAt(a)) {
                        // System.out.println(attempt.charAt(a)
                        // + " is in the word and correct place");
                        char[] chars = known.toCharArray();
                        // replace character at the specified position in a char
                        // array
                        chars[a] = answer.charAt(a);
                        // convert the character array back into a string
                        known = String.valueOf(chars);
                    } else {
                        System.out.println(attempt.charAt(a)
                                + " is in the word and incorrect place");
                    }
                }

            }
            
            char tempArray[] = used.toCharArray();
            Arrays.sort(tempArray);
            used = new String(tempArray);
            
            System.out.println();
            System.out.println("The known word so far is: " + known);
            System.out.println("Not Used: " + notUsed);
            System.out.println("Used: " + used);
            System.out.println();
        }
        if (hasWon == false) {
            System.out.println("You Lose!");
            System.out.println("Word was: " + answer);

        }
        sc.close();
        // ArrayList<String> wordBank = atlasMaker("5LetterWords.txt");

        // System.out.println(wordBank);

    }

    private static ArrayList<String> readAppsFromDictionary(String string) {
        ArrayList<String> wordBank = new ArrayList<String>();

        Scanner sc;

        try {
            sc = new Scanner(new File(string));

            String line = sc.nextLine();

            while (sc.hasNext()) {
                line = sc.nextLine();

                if (line.length() == 5) {
                    wordBank.add(line);
                }

            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        FileWriter myObj;
        try {
            myObj = new FileWriter("5LetterWords.txt", false);
            PrintWriter pr = new PrintWriter(myObj);

            for (String a : wordBank) {
                pr.write(a + "\n");
            }

            pr.close();
            myObj.close();

            // HashMap<String, String> map = new HashMap<String,
            // String>();

        } catch (IOException e1) {            
            e1.printStackTrace();
        }

        // System.out.println(wordBank.size());
        return wordBank;
    }
}

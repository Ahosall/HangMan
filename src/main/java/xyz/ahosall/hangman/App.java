package xyz.ahosall.hangman;

import java.util.HashMap;
import java.util.Scanner;

import xyz.ahosall.hangman.structures.RandomWord;
import xyz.ahosall.hangman.structures.Word;
import xyz.ahosall.hangman.utils.Api;
import xyz.ahosall.hangman.utils.XmlProcessor;

public class App {

    static HashMap<String, String> getWord() {
        Api api = new Api();
        XmlProcessor xmlProcessor = new XmlProcessor();

        HashMap<String, String> result = new HashMap<>();

        try {
            RandomWord randomWord = api.getData("/random", RandomWord.class);
            Word word = api.getData("/word/" + randomWord.word, Word.class);

            result.put("word", word.word);
            result.put("meaning", xmlProcessor.processXml(word.xml, "def"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    static String gibbetArt(int points) {
        String[] art = {
                ".__.\n|  |\n|  \n|\n|\n|",
                ".__.\n|  |\n|  O\n|  \n|\n|",
                ".__.\n|  |\n|  O\n|  |\n|\n|",
                ".__.\n|  |\n|  O\n| /|\n|\n|",
                ".__.\n|  |\n|  O\n| /|\\\n|\n|",
                ".__.\n|  |\n|  O\n| /|\\\n| /\n|",
                ".__.\n|  |\n|  O\n| /|\\\n| / \\\n|",
        };

        return art[7 - points];
    }

    public static void main(String[] args) throws Exception {
        Scanner entrySc = new Scanner(System.in);

        int points = 7;
        HashMap<String, String> word = getWord();

        String wordValue = word.get("word");
        char[] guessWord = wordValue.replaceAll("[a-z]", "_").toCharArray();

        while (points > 0) {
            System.out.println(word.get("meaning"));
            System.out.println(gibbetArt(points));
            System.out.println("|  " + new String(guessWord).toUpperCase());

            if (wordValue.equals(new String(guessWord)))
                break;

            System.out.println("\nQual é a palavra?");
            String entry = entrySc.nextLine().toLowerCase();

            if (entry.length() == 1 && wordValue.contains(entry)) {
                for (int i = 0; i < guessWord.length; i++) {
                    if (wordValue.charAt(i) == entry.charAt(0))
                        guessWord[i] = entry.charAt(0);
                }
                continue;
            } else if (wordValue.equals(entry)) {
                guessWord = entry.toCharArray();
                continue;
            }

            points--;
        }

        if (points > 0) {
            System.out.println("\nVocê venceu, parabéns!");
        } else {
            System.out.println("\nVocê perdeu! A palavra era: " + wordValue.toUpperCase());
        }

        entrySc.close();
    }
}

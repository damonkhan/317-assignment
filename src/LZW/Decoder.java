package LZW;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

public class Decoder {
    public static void main(String[] args) {
        Node[] dictionary = new Node[512];
        int currentPhrase = 0;
        for (int i = 0; i < 256; i++) {
            dictionary[i] = new Node((byte)0,(char)i);
            currentPhrase = i;
        }
        currentPhrase++;
        try {
            Stack<Character> phraseBuilder = new Stack<>();
            InputStreamReader isRead = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isRead);
            String in = br.readLine();
            int prevPhrase = 0;
            int phrase = Integer.parseInt(in);
            printPhrase(dictionary, phrase);
            while(in!=null)
            {
                in = br.readLine();
                prevPhrase = phrase;
                phrase = Integer.parseInt(in);
                Node n = new Node(prevPhrase, getOldestParent(dictionary,phrase));
               dictionary[currentPhrase] = n;
               printPhrase(dictionary, phrase);
               currentPhrase++;
            }

            /*while(phrase != -1)
            {
                dictionary[currentPhrase] = new Node(phrase, dictionary[phrase].data);
                System.out.write((char)dictionary[phrase].data);
                phrase = System.in.read();
                currentPhrase++;
            }*/

        }
        catch(Exception e)
        {
            System.out.print(e);
        }
    }

    public static void printPhrase(Node[] array, int phraseNum)
    {
        try
        {
            Stack<Character> fullPhrase = new Stack<>();
            do {
                fullPhrase.push(array[phraseNum].data);
                phraseNum = array[phraseNum].previous;
            }
            while(phraseNum != 0);
            while(fullPhrase.empty()==false)
            {
                System.out.print(fullPhrase.pop());
            }
        }
        catch (Exception e)
        {
            System.err.println(e);
        }
    }

    public static char getOldestParent(Node[] array, int phraseNum)
    {
        while(array[phraseNum].previous!=0)
        {
          phraseNum = array[phraseNum].previous;
        }
        return array[phraseNum].data;
    }
}

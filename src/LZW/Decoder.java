package LZW;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

import static LZW.Encoder.maxSize;

public class Decoder {
    public static void main(String[] args) {
        maxSize = 512;
        Node[] dictionary = initialise(maxSize);
        int currentPhrase = 256;
        try {
            InputStreamReader isRead = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isRead);
                String in = br.readLine();
                int prevPhrase = 0;
                int phrase = Integer.parseInt(in);
                printPhrase(dictionary, phrase);
                currentPhrase++;
                while(in!=null)
                {
                    in = br.readLine();
                    prevPhrase = phrase;
                    phrase = Integer.parseInt(in);
                    //if not a reset symbol, create new Node
                    if(phrase!=0)
                    {
                        Node n = new Node(prevPhrase, getOldestParent(dictionary, phrase,prevPhrase));
                        //add to our phrase dictionary
                        dictionary[currentPhrase] = n;
                        currentPhrase++;
                        //print out the phrase
                        printPhrase(dictionary,phrase);
                    }
                    else
                    {
                        //if reset symbol then reset array
                        dictionary = initialise(maxSize);
                        currentPhrase = 256;
                        in = br.readLine();
                        phrase = Integer.parseInt(in);
                        printPhrase(dictionary, phrase);
                        currentPhrase++;
                    }
                }
                //dictionary = initialise(maxSize);
                //currentPhrase = 257;

        }
        catch(Exception e)
        {
            System.out.print(e);
        }
    }
    //uses a stack and prints out full phrase of any phrase number
    public static void printPhrase(Node[] array, int phraseNum)
    {
        phraseNum = phraseNum;
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
    //gets the oldest parent of a phrase, it must be one of the original 255 byte values
    public static char getOldestParent(Node[] array, int phraseNum, int prev)
    {
        if(array[phraseNum] == null)
        {
            while(array[prev].previous!=0)
            {
                prev = array[prev].previous;
            }
            return array[prev].data;
        }
        while(array[phraseNum].previous!=0)
        {
          phraseNum = array[phraseNum].previous;
        }
        return array[phraseNum].data;
    }
    //An array of Nodes to store our phrases
    public static Node[] initialise(int maxSize)
    {
        Node[] dictionary = new Node[maxSize];
        dictionary[0] = new Node(0, (char)0);
        for (int i = 1; i <= 256; i++) {
            dictionary[i] = new Node((byte)0,(char)(i-1));
        }
        return dictionary;
    }
}

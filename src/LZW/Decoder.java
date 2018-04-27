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
                    if(phrase!=0)
                    {
                        Node n = new Node(prevPhrase, getOldestParent(dictionary, phrase));
                        dictionary[currentPhrase] = n;
                        currentPhrase++;
                        printPhrase(dictionary,phrase);
                    }
                    else
                    {
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

    public static char getOldestParent(Node[] array, int phraseNum)
    {
        if(array[phraseNum] == null)
        {
            return '0';
        }
        while(array[phraseNum].previous!=0)
        {
          phraseNum = array[phraseNum].previous;
        }
        return array[phraseNum].data;
    }

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

package LZW;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Encoder {



    public static void main(String[] args)
    {
        Trie trie = new Trie(300);
        trie.Initialize();
        int byteArraySize = 10;
        byte[] buffer = new byte[byteArraySize];
        int len = 0;
        //String input = "thefrogwasfrogthewas";
        int currentPos = 0;
        Byte c,p;
        int i = 0;

        try
        {
                InputStream input = System.in;
                len = input.read(buffer);
                while (len != -1) {
                    //System.out.print(buffer);
                    len = input.read(buffer);
                }
        }

        catch(Exception e)
        {
            System.err.print(e);
        }


        p = (buffer[currentPos]);

        while (currentPos < byteArraySize-1)
        {
            currentPos++;
            c = buffer[currentPos];


            if (trie.Search(p+c) != -1)
            {
                p = p + c;
            }
            else {
                //System.out.print(trie.Search(p));
                trie.insert(p+c);
                p = c;
            }
        }

        //System.out.print(trie.Search(p));
        System.out.println();

    }
}

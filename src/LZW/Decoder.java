package LZW;

import java.util.ArrayList;

public class Decoder {
    public static void main(String[] args) {
        Tree[] root = new Tree[256];
        int currentPhrase = 0;
        for (int i = 0; i < 256; i++) {
            root[i] = new Tree(i, (byte) i);
            root[i].children = new ArrayList();
            currentPhrase = i;
        }
        try {
            int phrase = System.in.read();
            int i = 0;
            while(root[i].phrase != phrase)
            {
                i++;
            }

        }
        catch(Exception e)
        {
            System.out.print(e);
        }
    }
}

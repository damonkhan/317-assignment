package LZW;

import java.io.*;
import java.util.ArrayList;


public class Encoder {



    public static void main(String[] args)
    {
        Tree[] root = new Tree[256];
        int currentPhrase = 0;
        for(int i = 0; i<256; i++)
        {
            root[i] = new Tree(i, (byte)i);
            root[i].children = new ArrayList();
            currentPhrase = i;
        }

        try
        {
            Tree curr = null;
            int read = 0;
            read = System.in.read();
            while(read != -1)
            {
            byte b = (byte)read;
            int i=0;
            while(root[i].data != b)
                {
                    i++;
                }
            curr = root[i];
                    read = System.in.read();
                    b = (byte)read;
                    //search returning -1 when wa exists, maybe curr is wrong
                    int search = curr.children.indexOf(b);
                    while(search != -1)
                    {
                        curr = (Tree)curr.children.get(search);
                        read = System.in.read();
                        b = (byte)read;
                        search = curr.children.indexOf(b);
                    }
                    System.out.println(curr.phrase);
                    Tree newNode = new Tree(currentPhrase, b);
                    curr.children.add(newNode);
                }
            System.out.print(read);





        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }


}

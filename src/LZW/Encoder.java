package LZW;

import com.sun.prism.shader.Solid_TextureSecondPassLCD_AlphaTest_Loader;

import javax.swing.text.Style;
import java.io.*;
import java.util.ArrayList;


public class Encoder {

    public static int maxSize = 9;

    public static void main(String[] args)
    {
        if(args.length != 1)
        {
            System.err.print("Usage: enter and integer to represent max number of bits");
            return;
        }
        if(Integer.parseInt(args[0]) <= 8)
        {
            System.err.print("Value must be greater than 8");
            return;
        }
        maxSize = Integer.parseInt(args[0]);
        maxSize = (int)Math.pow((double)2,(double)maxSize);
        Trie[] root = initialise(maxSize);
        int currentPhrase = 257;
        try
        {
            Trie curr;
            int read;
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
                if(System.in.available() == 0)
                {
                    System.out.print(curr.data);
                    System.out.println();
                    System.out.print("eof");
                    return;
                }
                read = System.in.read();
                b = (byte)read;

                int search = find(curr.children, b);
                while(search != -1)
                {
                    curr = (Trie)curr.children.get(search);
                    read = System.in.read();
                    b = (byte)read;
                    search = find(curr.children,b);
                }
                System.out.println(curr.phrase);
                Trie newNode = new Trie(currentPhrase, b);
                currentPhrase++;
                if(currentPhrase > maxSize)
                {
                    System.out.println(root[0].phrase);
                    root = initialise(maxSize);
                    currentPhrase = 257;
                }
                curr.children.add(newNode);
            }
            System.out.print(read);

        }
        catch(Exception e)
        {
            System.out.println(e);
        }

    }

    public static int find(ArrayList<Trie> list, byte b)
    {
        if(list == null)
        {
            return -1;
        }
        for(int i=0; i<list.size();i++)
        {
            if(list.get(i).data == b)
            {
                return i;
            }
        }
        return -1;
    }

    public static Trie[] initialise(int max)
    {
        Trie[] root = new Trie[max];
        root[0] = new Trie(0,(byte)0);
        for(int i = 1; i<=256; i++)
        {
            root[i] = new Trie(i, (byte)(i-1));
            root[i].children = new ArrayList();
        }
        return root;
    }


}
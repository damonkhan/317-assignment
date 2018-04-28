package LZW;

import com.sun.prism.shader.Solid_TextureSecondPassLCD_AlphaTest_Loader;

import javax.swing.text.Style;
import java.io.*;
import java.util.ArrayList;


public class Encoder {
    //public so all classes can access
    public static int maxSize = 9;

    public static void main(String[] args)
    {
        //args checks
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
        //current phrase is one more than full
        int currentPhrase = 257;
        try
        {
            //read data in one char at a time
            Trie curr;
            int read;
            while(System.in.read()<0)
            {
                System.in.read();
            }
            read = System.in.read();
            while(read != -1)
            {
                byte b = (byte)read;
                int i=0;
                //find the index that has b
                while(root[i].data != b)
                {
                    i++;
                }
                //set curr as the node which holds b
                curr = root[i];
                //check if we are at the end of our input
                if(System.in.available() == 0)
                {
                    //output the final data
                    System.out.print(curr.data);
                    System.out.println();
                    //add end of file marker for bit packer
                    System.out.print("eof");
                    return;
                }
                //read next item
                read = System.in.read();
                b = (byte)read;
                //call find method to return index of b
                int search = find(curr.children, b);
                //if it exists then keep searching new characters until mismatch
                while(search != -1)
                {
                    curr = (Trie)curr.children.get(search);
                    read = System.in.read();
                    b = (byte)read;
                    search = find(curr.children,b);
                }
                //output phrase number
                System.out.println(curr.phrase);
                //create new node
                Trie newNode = new Trie(currentPhrase, b);
                currentPhrase++;
                //check for full trie
                if(currentPhrase > maxSize)
                {
                    //reset everything
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

    //returns the index of b
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
    //initialises trie array with all 255 values
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
package LZW;

import java.io.*;
import java.util.ArrayList;


public class Encoder {



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
        int maxSize = Integer.parseInt(args[0]);
        maxSize = 2*maxSize;
        Trie[] root = new Trie[256];
        int currentPhrase = 0;
        for(int i = 0; i<256; i++)
        {
            root[i] = new Trie(i, (byte)i);
            root[i].children = new ArrayList();
            currentPhrase = i;
        }
        currentPhrase++;

        try
        {
            Trie curr = null;
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
                if(System.in.available() == 0)
                {
                    System.out.print(curr.data);
                    return;
                }
                read = System.in.read();
                b = (byte)read;
                //search returning -1 when wa exists, maybe curr is wrong
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
                    //output reset symbol
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


}
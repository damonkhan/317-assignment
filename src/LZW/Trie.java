package LZW;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

public class Trie {
    int phrase;
    byte data;
    ArrayList children;

    public Trie(int p, byte d)
    {
        this.phrase = p;
        this.data = d;
        this.children = new ArrayList<Trie>();
    }
}



package LZW;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
//data type for encoder, stores a phrase number, some data value and a list of its children
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



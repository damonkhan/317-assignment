package LZW;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

public class Tree {
    int phrase;
    byte data;
    ArrayList children;

    public Tree(int p, byte d)
    {
        this.phrase = p;
        this.data = d;
    }
}



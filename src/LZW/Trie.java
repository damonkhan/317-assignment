package LZW;

public class Trie {
    Byte[] dictionary;
    int current;

    public Trie(int size)
    {

        dictionary = new Byte[size];
        current = 0;
    }

    public void Initialize()
    {
        int i = 0;
        for(int c = 0; c<256; c++)
        {
            Integer.toBinaryString(i) & 0b11111111;

            String s = ""+(char)c.getBytes("UTF-8");
            dictionary[i] = s.getBytes("UTF-8");
            i++;
        }
        current = i+1;
        System.out.println("Current = " + current);
    }


    // Searches the dictionary for a
    // matching phrase
    public int Search(Byte phrase) {
        if (dictionary.length == 0)
            return 0;

        for (int i = 0; i < dictionary.length; i++) {
            if (dictionary[i] == null)
                break;

            if (dictionary[i].compareTo(phrase) == 0)
                return i;
        }

        // phrase not found in dictionary
        return -1;
    }

    public void insert(String item)
    {
        dictionary[current] = item;
        current++;
    }

    public String getIndex(int index)
    {
        return dictionary[index];
    }

    public int getCurrent()
    {
        return current;
    }

    public int getSize()
    {
        return dictionary.length;
    }
}

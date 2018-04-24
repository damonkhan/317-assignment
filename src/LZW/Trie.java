package LZW;
import java.lang.Character;

public class Trie {
    char[] dictionary;
    int current;

    public Trie(int size)
    {

        dictionary = new char[size];
        current = 0;
    }

    public void Initialize()
    {
        for(int i=0;i<257;i++)
        {
            dictionary[i] = (char)i;
        }
    }


    // Searches the dictionary for a
    // matching phrase
    public int Search(char phrase) {
        if (dictionary.length == 0)
            return 0;

        for (int i = 0; i < dictionary.length; i++) {
            if (dictionary[i] == '\0')
                break;

            if (dictionary[i] == phrase)
                return i;
        }

        // phrase not found in dictionary
        return -1;
    }

    public void insert(char item)
    {
        dictionary[current] = item;
        current++;
    }

    public int getIndex(int index)
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

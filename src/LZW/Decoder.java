package LZW;

public class Decoder {

    public static void main(String[] args)
    {
        Trie trie = new Trie(200);
        trie.Initialize();
        String input = "8371686981787086648269817870837168866482";
        String s,c = "";
        int currentPos = 0;
        int oldValue = Integer.parseInt("" + input.charAt(currentPos));
        System.out.print(trie.getIndex(oldValue));
        while(currentPos < input.length()-1)
        {
            currentPos++;
            int newValue = Integer.parseInt("" + input.charAt(currentPos));
            if(trie.Search(trie.getIndex(newValue))==-1)
            {
                s = trie.getIndex(oldValue);
                s = s + c;
            }
            else
            {
                s = trie.getIndex(newValue);
                System.out.print(s);
                c = "" + s.charAt(0);
                trie.insert(trie.getIndex(oldValue) + c);
                oldValue = newValue;
            }
        }

    }
}

package LZW;

//data type for decoder, stores a value and a parent
public class Node {
    int previous;
    char data;

    public Node(int parent, char b)
    {
        this.previous = parent;
        this.data = b;
    }
}

package LZW;

import sun.reflect.annotation.ExceptionProxy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

import static LZW.Encoder.maxSize;

public class bitUnpacker {
    public static void main(String[] args) {
        try {
            //maxSize = 9; //uncomment for testing
            Stack<Character> stack = new Stack<>();
            //read one bit
            int read = System.in.read();
            while (read != -1) {
                String in = "";
                //read the number of bits specified by maxSize global variable
                for (int i = 0; i < maxSize; i++) {
                    stack.push((char) read);
                    read = System.in.read();
                }
                //a stack is used to make sure the order is correct
                while (stack.empty() != true) {
                    in += stack.pop();
                }
                //call get int method to get int of decimal string
                System.out.println(getInt(in));
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }
    //takes a string of 0's and 1's and turns it into the decimal equiv
    public static int getInt(String n)
    {
        int bNum = 0;
        int dNum = 0;
        int p = 0;
        bNum = Integer.parseInt(n);

        while (true) {
            if (bNum == 0) {
                break;
            } else {
                int temp = bNum % 10;
                dNum += temp * Math.pow(2, p);
                bNum = bNum / 10;
                p++;
            }
        }
        return dNum;
    }

}

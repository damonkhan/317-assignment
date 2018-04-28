package LZW;

import sun.reflect.annotation.ExceptionProxy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.util.Stack;

import static LZW.Encoder.maxSize;

public class bitUnpacker {
    public static void main(String[] args) {
        try {
            maxSize = 9;
            InputStreamReader isRead = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isRead);
            Stack<Character> stack = new Stack<>();

            int read = System.in.read();
            while (read != -1) {
                String in = "";
                for (int i = 0; i < maxSize; i++) {
                    stack.push((char) read);
                    read = System.in.read();
                }
                while (stack.empty() != true) {
                    in += stack.pop();
                }
                System.out.println(getInt(in));
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

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

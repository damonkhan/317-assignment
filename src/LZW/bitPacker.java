package LZW;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

import static LZW.Encoder.maxSize;

public class bitPacker {
    public static void main(String[] args) {
        try {
            //use a stack to view binary numbers in a readable order, keep all stack lines commented if using bitUnpacker
            //maxSize = 9;
            String eof = "eof";
            //Stack<Byte> byteStack = new Stack<Byte>();
            InputStreamReader isRead = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isRead);
            String in = br.readLine();
            int value = Integer.parseInt(in);
            byte buf[] = new byte[maxSize-1];
            int i = 0;
            int byteCount = 0;
            while((in.compareTo(eof)!=0))
            {
                //get byte value by sing modulo 2 method
                buf[byteCount] = (byte)(value%2);
                byteCount++;
                i++;
                value = value/2;
                //when we have a byte of data, output it
                if(byteCount == 8)
                {
                    for(int j = 0; j<=7; j++) {
                        //byteStack.push(buf[j]);
                        System.out.print(buf[j]);
                    }
                    byteCount = 0;
                }
                //when we have read max bits of our int, get next int
                if(i==maxSize)
                {
                    in = br.readLine();
                    if(in.compareTo(eof)==0)
                    {
                        for(int m=0; m<byteCount;m++)
                        {
                            //byteStack.push(buf[m]);
                            System.out.print(buf[m]);
                        }
                        //used to make binary numbers the right way, commented out for bitUnpacker
                        /*while(byteCount!=8)
                        {
                            //byteStack.push((byte)0);
                            System.out.print((byte)0);
                            byteCount++;
                        }*/
                        break;
                    }
                    value = Integer.parseInt(in);
                    i = 0;
                }
            }
            /*int k=0;
            while(byteStack.empty()!=true) {
                System.out.print(byteStack.pop());
                k++;
                if(k == 9)
                {
                    System.out.println("\n");
                    k=0;
                }
            }*/


        } catch (Exception e) {
            System.err.println(e);
        }
    }
}

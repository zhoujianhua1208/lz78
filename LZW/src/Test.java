import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        System.out.println("Please enter your file path: ");
        Scanner in = new Scanner(System.in);
        String filename = in.nextLine();
        LZW lzw = new LZW(filename);
        long startTime = System.currentTimeMillis();
        lzw.encode();
        lzw.decode();
        long endTime = System.currentTimeMillis();
        System.out.println("程序运行时间："+(endTime-startTime)+"ms");
        lzw.compressedRate();
        //System.out.println();
    }
}

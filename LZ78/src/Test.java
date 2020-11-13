import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        System.out.println("Please enter your file path: ");
        Scanner in = new Scanner(System.in);
        String filename = in.nextLine();
        LZ78 lz78 = new LZ78(filename);
        long startTime = System.currentTimeMillis();
        lz78.encode();
        lz78.decode();
        long endTime = System.currentTimeMillis();
        System.out.println("程序运行时间："+(endTime-startTime)+"ms");
        lz78.compressedRate();
    }
}

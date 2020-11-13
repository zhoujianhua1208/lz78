import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class IOHandler {

    public String fileToString(String filename){
        String toEncode = "";
        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
            StringBuffer stringBuffer = new StringBuffer();
            String line = null;
            while((line =bufferedReader.readLine())!=null)
            {
                stringBuffer.append(line).append("\n");
            }
            toEncode = stringBuffer.toString();
            toEncode = toEncode.substring(0, toEncode.length() - 1);
        }catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return toEncode;
    }

    public void StringToFile(String filename, String res){
        FileWriter writer;
        try {
            writer = new FileWriter(filename);
            writer.write(res);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

import java.util.ArrayList;
import java.util.HashMap;

public class LZW {

    private String filename;
    private ArrayList<Short> code;
    private char end;

    public LZW(String filename){
        code = new ArrayList<>();
        this.filename = filename;
    }

    public void encode(){
        HashMap<String, Short>dic = new HashMap<>();
        IOHandler helper = new IOHandler();
        String text = helper.fileToString(filename);
        for(int i = 0; i<text.length(); i++){
            if(dic.containsKey(String.valueOf(text.charAt(i))))
                continue;
            dic.put(String.valueOf(text.charAt(i)), (short)(dic.size()));
        }
        this.end = text.charAt(text.length() - 1);
        char next;
        int i = 0 ;
        String tmp = "";
        while(i < text.length()){
            tmp = "";
            tmp += text.charAt(i);
            if (i + 1 == text.length())
                break;
            next = text.charAt(i + 1);
            while (dic.containsKey(tmp)) {
                tmp += next;
                i++;
                if (i + 1 == text.length()) {
                    break;
                }
                next = text.charAt(i + 1);
            }
            tmp = tmp.substring(0, tmp.length() - 1);
            next = text.charAt(i);
            code.add(dic.get(tmp));
            tmp += next;
            dic.put(tmp, (short) (dic.size()));
        }
    }

    public void decode(){
        HashMap<String, Short>dic = new HashMap<>();
        HashMap<Short, String>mp =new HashMap<>();
        IOHandler helper = new IOHandler();
        String text = helper.fileToString(filename);
        for(int i = 0; i<text.length(); i++){
            if(dic.containsKey(String.valueOf(text.charAt(i))))
                continue;
            dic.put(String.valueOf(text.charAt(i)), (short)(dic.size()));
            mp.put((short)(dic.size() - 1), String.valueOf(text.charAt(i)));
        }
        String res = "";
        String pre = "", output = "";
        output = mp.get(code.get(0));
        res += output;
        for(int i =1 ; i < code.size() ; i++){
            pre = output;
            output = mp.get(code.get(i));
            res += output;
            if(output.length() != 0)
            mp.put((short)(mp.size()), pre + output.charAt(0));
        }
        res += String.valueOf(end);
        helper.StringToFile("output.txt", res);
    }

    public void compressedRate(){
        System.out.println("压缩率" + (14349-code.size()*2 - 1)/14349.0);
    }
}

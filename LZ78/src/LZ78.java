import java.util.ArrayList;
import java.util.HashMap;

public class LZ78 {
    private class inner{
        public short num;
        public char c;
        public inner(short num, char c){
            this.num = num;
            this.c = c;
        }
    }
    ArrayList<inner>dic;
    private String filename;

    public LZ78(String filename){
        this.filename = filename;
        dic = new ArrayList<>();
    }

    public void encode(){
        HashMap<String, Short>mp = new HashMap<>();
        IOHandler helper = new IOHandler();
        String text = helper.fileToString(filename);
        String tmp = "";
        for(int i = 0; i < text.length(); i++){
            tmp += text.charAt(i);
            if(mp.containsKey(tmp) == false){
                mp.put(tmp, (short) (mp.size()+1));
                if(tmp.length() == 1){
                    dic.add(new inner((short) 0, text.charAt(i)));
                }else{
                    tmp = tmp.substring(0, tmp.length() - 1);
                    dic.add(new inner(mp.get(tmp), text.charAt(i)));
                }
                tmp = "";
            }
        }
        if(tmp != ""){
            dic.add(new inner(mp.get(tmp), '#'));
        }
    }

    public void decode(){
        HashMap<Short, String>mp = new HashMap<>();
        String res = "";
        String tmp = "";
        for(int i = 0; i < dic.size(); i++){
            tmp += dic.get(i).c;
            if(dic.get(i).num == 0){
                res += tmp;
                mp.put((short) (mp.size()+1), tmp);
                tmp = "";
            }else{
                String output = "";
                if(dic.get(i).c != '#')
                    output = mp.get(dic.get(i).num);
                res += (output + tmp);
                mp.put((short) (mp.size()+1), output+tmp);
                tmp = "";
            }
        }
        IOHandler helper = new IOHandler();
        helper.StringToFile("out.txt",res);
    }

    public void compressedRate(){
        System.out.println("压缩率" + (14349-dic.size()*3)/14349.0);
    }
}

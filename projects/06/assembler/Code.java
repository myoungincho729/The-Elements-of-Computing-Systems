import java.util.HashMap;

public class Code {
    private static boolean init = false;
    private static String[] cmpcode = {
        "0101010","0111111","0111010","0001100","0110000",
        "0001101","0110001","0001111","0110011","0011111",
        "0110111","0001110","0110010","0000010","0010011",
        "0000111","0000000","0010101",
        "1110000","1110001","1110011","1110111","1110010",
        "1000010","1010011","1000111","1000000","1010101"
    };
    private static String[] cmp = {
        "0","1","-1","D","A","!D","!A","-D","-A","D+1","A+1",
        "D-1","A-1","D+A","D-A","A-D","D&A","D|A","M","!M",
        "-M","M+1","M-1","D+M","D-M","M-D","D&M","D|M"
    };
    public static HashMap<String, String> mp = new HashMap<>();
    public static HashMap<String, String> jumpMap = new HashMap<>();

    Code(){
        if (init == false){
            for (int i=0;i<cmp.length;i++)
                mp.put(cmp[i], cmpcode[i]);
            jumpMap.put("null", "000");
            jumpMap.put("JGT", "001");
            jumpMap.put("JEQ", "010");
            jumpMap.put("JGE", "011");
            jumpMap.put("JLT", "100");
            jumpMap.put("JNE", "101");
            jumpMap.put("JLE", "110");
            jumpMap.put("JMP", "111");
            init = true;
        }
    }
    public static String dest(String dst) {
        char[] charset = {'0', '0', '0'};
        if (dst.contains("A")) charset[0] = '1'; 
        if (dst.contains("D")) charset[1] = '1'; 
        if (dst.contains("M")) charset[2] = '1'; 
        String ret = String.valueOf(charset);
        return ret;
    }
    public static String comp(String cmp) {
        System.out.println(cmp);
        System.out.println(mp.get(cmp));
        return mp.get(cmp); 
    }
    public static String jump(String jmp) {
        return jumpMap.get(jmp);
    }
}

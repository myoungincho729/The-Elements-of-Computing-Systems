import java.util.HashMap;

public class SymbolTable {
    public static HashMap<String, Integer> table = new HashMap<>();
    private boolean init = false;
    private String[] symbol = {
        "R0","R1","R2","R3","R4","R5","R6","R7","R8","R9","R10",
        "R11","R12","R13","R14", "R15","SCREEN","KBD",
        "SP","LCL","ARG","THIS","THAT"
    };
    private int[] value = {
        0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,
        16384,24576,0,1,2,3,4
    };
    SymbolTable(){
        if (init == false){
            for (int i=0;i<value.length;i++){
                table.put(symbol[i], value[i]);
            }
            init = true;
        }
    }
    public void addEntry(String sym, int addr){
        table.put(sym, addr);
    }
    public boolean contains(String sym){
        return table.containsKey(sym);
    }
    public int getAddress(String sym){
        return table.get(sym);
    }
    public void print(){
        for (String s : table.keySet())
            System.out.println(s + " : " + table.get(s));
    }
}

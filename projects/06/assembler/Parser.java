public class Parser {

    public String givenString;

    public Parser(String a) {
        givenString = a;
        int pos = givenString.indexOf("//");
        if (pos != -1) givenString = givenString.substring(0, pos);
        givenString = givenString.trim();
    }
    public boolean advance(){
        if (givenString.length() == 0) return false;
        if (givenString.charAt(0) == '(' && givenString.charAt(givenString.length()-1) == ')')
            return false;
        return true;
    }
    public boolean isLabel(){
        if (givenString.equals("")) return false;
        if (givenString.substring(0, 2).equals("\n\n")) return false;
        if (givenString.charAt(0) == '(' && givenString.charAt(givenString.length()-1) == ')')
            return true;
        return false;
    }
    public instructionType instructionType() {
        if (givenString.charAt(0) == '@') return instructionType.A;
        else return instructionType.C;
    }
    public int address(){
        String ret = givenString.substring(1);
        return Integer.parseInt(ret);
    }
    public boolean isSymbol(){
        char firstChar = givenString.charAt(1);
        if (Character.isAlphabetic(firstChar)) return true;
        return false;
    }
    public String symbol(){
        String ret = givenString.substring(1);
        return ret;
    }
    public String dest(){
        if (givenString.contains("=") == false) return "null";
        String[] spl = givenString.split("=");
        return spl[0];
    }
    public String comp(){
        if (givenString.contains("=") == false){
            String[] spl = givenString.split("[;]");
            return spl[0];
        }
        String[] spl = givenString.split("[=;]");
        return spl[1];
    }
    public String jump(){
        String[] spl = givenString.split("[;]");
        return spl.length == 2 ? spl[1] : "null";
    }
    
}

enum instructionType {
    A, C;
}

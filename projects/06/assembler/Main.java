import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Main
 */
public class Main {
    public static void main(String[] args) throws IOException{
        //read file -> List<String>
        String fileName = args[0];
        String fileNotDot = args[0].substring(0, args[0].indexOf(".", 0));
        String hackName = fileNotDot + ".hack";
        List<String> lines = Files.readAllLines(Paths.get(fileName));
        List<String> codes = new ArrayList<>();
        Code code = new Code();
        SymbolTable table = new SymbolTable();
        int symNum = 16;
        List<String> noBlank = new ArrayList<>();

        for (int i=0;i<lines.size();i++){
            Parser p = new Parser(lines.get(i));
            if (p.givenString.equals("") || p.givenString.startsWith("//")) continue;
            noBlank.add(p.givenString);
        }
        
        int labcnt = 0;
        for (int i=0;i<noBlank.size();i++){
            Parser p = new Parser(noBlank.get(i));
            
            if (p.isLabel() == true){
                labcnt++;
                String label = p.givenString.substring(1, p.givenString.length()-1);
                table.addEntry(label, i + 1 - labcnt);
            }
        }
        
        for (String a : noBlank){
            Parser p = new Parser(a);
            if (p.advance() == false) continue;
            String codeToAdd = "";
            if (p.instructionType() == instructionType.A){
                codeToAdd += "0";
                int addr;
                if (p.isSymbol()){
                    System.out.println(p.givenString);
                    if (table.contains(p.symbol())) {
                        addr = table.getAddress(p.symbol());
                    }
                    else {
                        table.addEntry(p.symbol(), symNum++);
                        addr = table.getAddress(p.symbol());
                    }
                }
                else addr = p.address();
                String binary = Integer.toString(addr, 2);
                codeToAdd += "0".repeat(15 - binary.length());
                codeToAdd += binary;
            }
            if (p.instructionType() == instructionType.C){
                codeToAdd += "111";
                
                codeToAdd += code.comp(p.comp());
                codeToAdd += code.dest(p.dest());
                codeToAdd += code.jump(p.jump());
            }
            // System.out.println(codeToAdd);
            codes.add(codeToAdd);
        }
        table.print();
        File file = new File(hackName);
        FileWriter fw = new FileWriter(file);
        for (String c : codes){
            fw.write(c);
            fw.write("\n");
        }
        fw.close();
    }
}
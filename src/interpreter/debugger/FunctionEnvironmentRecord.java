package interpreter.debugger;
import java.util.Set;

/**
 *
 * @author admin
 */
public class FunctionEnvironmentRecord {
    private SymbolTable table;
    private String functName;
    private Integer startLine;
    private Integer endLine;
    private Integer currentLine;
    
    
    public static void main(String args[]){
        FunctionEnvironmentRecord record = new FunctionEnvironmentRecord();
        FunctionEnvironmentRecordUnitTests.test_1(record);
    }
    
    
    public FunctionEnvironmentRecord(){
        table = new SymbolTable();
    }
    
    public void setFunction(String name,int start, int end){
        functName = name;
        startLine = start;
        endLine = end;
        currentLine = null;
    }
    
    
    public void setStartLine(int n){
        startLine = n;
    }
    
    public int getStartLine(){
        return startLine;
    }

    public void setEndLine(int n){
        endLine = n;
    }
    
    public int getEndLine(){
        return endLine;
    }
    
    public void setCurrentLine(int n){
        currentLine = n;
    }
    
    public int getCurrentLine(){
        return currentLine;
    }
    
    public String getFunctionName(){
        return functName;
    }
    
    public Set<String> getCurrentVariables(){
        return table.keys();
    }
    
    public int getVariableOffset(String name){
        return table.get(name);
    }
    public void beginScope(){
        FunctionEnvironmentRecord freshRecord = new FunctionEnvironmentRecord();
        table.beginScope();
    }
    
    
    public void enter(String key, int value){
        table.put(key, value);
        
    }
    
    public void pop(int n){
        table.pop(n);
    }
    
    
    public String stringifyRecord(){
        return "(" + this.stringifyTable() + "," + 
                (functName!=null ? functName : "-")+
                "," + 
                (startLine!=null ?startLine :"-") +
                "," + 
                (endLine!=null ?endLine :"-")+
                "," + 
                (currentLine!=null ?currentLine :"-") + 
                ")";
    }
    
    // Returns a string of the key value pairs <a/1,b/0,...>
    public String stringifyTable(){
        String tableState = "<";
        java.util.Set<String> keys =table.keys() ;
        for (String key : keys ){
            tableState += key+ "/" + table.get(key)+",";
        }
        // take of trailing comma. 
        if ( tableState.length()>1) tableState = tableState.substring(0, tableState.length()-1);
        tableState+= ">";
                
        return tableState;
    }
    
}


class FunctionEnvironmentRecordUnitTests{
    
    
    /**
     * Tests the following series of commands: 
     * 
     * BS				Begin scope
     * Function g 1 20	Set the fields for function name, start and end
     * Line 5				Set the current line to n
     * Enter a 4  		Enter the var/value pair in the symbol table
     * Enter b 2
     * Enter c 7
     * Enter i 3
     * Pop 1				Remove the last n var/value pairs from the symbol table
     * POP 2
     * POP 1
     * 
     * @param record
     * @return 
     */
    public static void test_1(FunctionEnvironmentRecord record){
        System.out.println("BS");
        record.beginScope();
        
        System.out.println("Function g 1 20");
        record.setFunction("g", 1, 20);
        
        System.out.println("Line 5");
        record.setCurrentLine(5);
        
        System.out.print("Enter a 4 \t");
        record.enter("a", 4);       
        System.out.println(record.stringifyRecord());
        
        System.out.print("Enter b 2 \t");
        record.enter("b", 2);       
        System.out.println(record.stringifyRecord());
        
        System.out.print("Enter c 7 \t");
        record.enter("c", 7);       
        System.out.println(record.stringifyRecord());
        
        System.out.print("Enter a 1 \t");
        record.enter("a", 1);       
        System.out.println(record.stringifyRecord());
        
        System.out.print("Pop 2     \t");
        record.pop(2);
        System.out.println(record.stringifyRecord());
        
         System.out.print("POP 1     \t");
        record.pop(1);
        System.out.println(record.stringifyRecord());
        
    }
    
    
    
    
}
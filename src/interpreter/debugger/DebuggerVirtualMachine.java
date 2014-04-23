package interpreter.debugger;
import interpreter.*;
import interpreter.ByteCode.BinaryOpTable;
import interpreter.ByteCode.ByteCode;
import java.util.EnumSet;
import java.util.Stack;
import java.util.Vector;

/**
 *When the debug flag is set, the interpreter will create an instance of a
 * DebuggerVM to execute the program.   
 * 
 * 
 * @author Raskolnikov
 */


/**
 * Milestone 3: 
 *     help, set/clear breakpoints, display the current function,
 *      continue execution, quit execution
 *      display variable(s)
 * 
 */
public class DebuggerVirtualMachine extends VirtualMachine{
    Vector<String> sourceFile;
    Stack<FunctionEnvironmentRecord> EnvironmentRecordStack;
    boolean[] breakPoints;  // Keeps track of whether or not 
    
    
    
    
    public DebuggerVirtualMachine(Program prog, Vector<String> source){
        super(prog);
        sourceFile = source;
        EnvironmentRecordStack = new Stack<FunctionEnvironmentRecord>();
        breakPoints = new boolean[sourceFile.size()];
        HelperMethods.initializeToFalse(breakPoints);
        
    }
    // Each time a function is entered in the Program, the address the VM should
    // return to once the Program returns from the function is pushed onto the
    // returnAddrs stack. 
    // Every ByteCode object should have an exectute( VirtualMacine ) method. 
    // 
    public void executeProgram(){
        pc = 0;
        runStack = new RunTimeStack();
        returnAddrs = new Stack();
        isRunning = true;
        BinaryOpTable.init();
        
        System.out.println("-----Running in Debugger Mode-----");
        // VM should be responsible for dumping to Console. 
        
        while (isRunning){
            ByteCode code = program.getCode(pc);
            code.execute(this);
            // only dump if the current code is not DUMP and dumping is set. 
            if (!code.getClass().getName().equals("interpreter.ByteCode.DumpByteCode")
                    && dumping){
                System.out.println(code.toString());
                runStack.dump();
            }
            pc++;
        }
    }
    
    public void setCurrentLineNumber(int n){
        currentRecord().setCurrentLine(n);
    }
    
    
    public Vector<String> getSourceFile(){
        return sourceFile;
    }
    
    private FunctionEnvironmentRecord currentRecord(){
        return EnvironmentRecordStack.peek();
    }
    
    public void setCurrentLine(int n){
        currentRecord().setCurrentLine(n);
    }
    
    public int getCurrentLine(){
        return currentRecord().getCurrentLine();
    }
    
    public void setFunction(String name, int start, int end){
        
        FunctionEnvironmentRecord newRecord = new FunctionEnvironmentRecord();
        newRecord.beginScope();
        newRecord.setFunction(name, start, end);
        EnvironmentRecordStack.push(newRecord);
    }
    
    public void beginScope(){
        FunctionEnvironmentRecord newRecord = new FunctionEnvironmentRecord();
    
    }
    
}


class HelperMethods{
    
    public static void initializeToFalse(boolean[] b){
        for (int i =0 ; i< b.length; i++){
            b[i] = false;
        }
    }
}
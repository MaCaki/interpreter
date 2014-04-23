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
    private Vector<AnnotatedSourceLine> sourceCodeLines;
    private Stack<FunctionEnvironmentRecord> EnvironmentRecordStack;
    private int currentLineNumber;
    
    public DebuggerVirtualMachine(Program prog, Vector<String> source){
        super(prog);
        sourceCodeLines = AnnotatedSourceLine.annotate(source);
        EnvironmentRecordStack = new Stack<FunctionEnvironmentRecord>();
    }
    
    /**
     * This must run before the VM can start executing the program. 
     */
    public void initialize(){
        pc = 0;
        runStack = new RunTimeStack();
        returnAddrs = new Stack();
        isRunning = true;
        currentLineNumber = 0;
        BinaryOpTable.init();
    }
    
    /**
     * This will launch the initialized VM to start running until it hits a 
     * break point. 
     */
    public void continueRunning(){
        isRunning = true;
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
            /* Check to see if current line has a break point*/
            
            if (isBreakPointSet(getCurrentLineNumber())) isRunning = false;
            
        }
    }
    
 
    
    
    /*  ---------   Source Code Getters and Setters -----*/
    
    
    public String getSourceCodeLine(int n){
        AnnotatedSourceLine AnnotatedLine = sourceCodeLines.get(n);
        String line = AnnotatedLine.getLine();
        return line;
    }
    
     public void setBreakPoint(int n){
        sourceCodeLines.get(n-1).setBreakPoint();
    }
     
      public void clearBreakPoint(int n){
        sourceCodeLines.get(n-1).clearBreakPoint();
    }
    
    public boolean isBreakPointSet(int n){
        return sourceCodeLines.get(n-1).isBreakPointSet();
    }
    
    public int sourceCodeSize(){
        return sourceCodeLines.size();
    }
    
    
    /*  ---------  FunctionEnvironStack   Getters and Setters -----*/
    private FunctionEnvironmentRecord currentRecord(){
        return EnvironmentRecordStack.peek();
    }
    
      public void setCurrentLineNumber(int n){
        currentLineNumber =n;
        if (!EnvironmentRecordStack.empty()){
            currentRecord().setCurrentLine(n);
        }
    }
    
    public int getCurrentLineNumber(){
        return currentLineNumber;
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



/**
 * A helper class to contain source code text as well as information about
 * the source code such as if a break point is set.  
 * 
 * @author Raskolnikov
 */
class AnnotatedSourceLine{

    static Vector<AnnotatedSourceLine> annotate(Vector<String> source) {
        Vector<AnnotatedSourceLine> annotatedVector = new Vector<AnnotatedSourceLine>();
        
        for (int i=0; i<source.size(); i++){
            AnnotatedSourceLine annotatedLine = new AnnotatedSourceLine(source.get(i));
            annotatedVector.add(annotatedLine);
        }
        return annotatedVector;
    }
    private boolean isBreakPointSet;
    private String sourceCodeLine;
    
    AnnotatedSourceLine(String line, boolean breakpoint){
        isBreakPointSet = breakpoint;
        sourceCodeLine = line;
    }
    
    AnnotatedSourceLine(String line){
        isBreakPointSet = false;
        sourceCodeLine = line;
    }
    
    public String getLine(){
        return sourceCodeLine;
    }
    
    public boolean isBreakPointSet(){
        return isBreakPointSet;
    }
    
    public void setBreakPoint(){
        isBreakPointSet = true;
    }
    
    public void clearBreakPoint(){
        isBreakPointSet = false;
    }
    
}
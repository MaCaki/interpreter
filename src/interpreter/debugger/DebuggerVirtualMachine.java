package interpreter.debugger;
import interpreter.*;
import interpreter.ByteCode.*;
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
    private Stack<Integer> validBreakPoints; 
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
        beginScope();
        validBreakPoints = new Stack<Integer>();
        inventoryValidBreakPoints();
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
        AnnotatedSourceLine AnnotatedLine = sourceCodeLines.get(n-1);
        String line = AnnotatedLine.getLine();
        return line;
    }
    
     public boolean setBreakPoint(int n){
        if (validBreakPoints.contains(n)){
            sourceCodeLines.get(n-1).setBreakPoint();
            return true;
        } 
        return false;
    }
     
    public void clearBreakPoint(int n){
        sourceCodeLines.get(n-1).clearBreakPoint();
    }
    
    public boolean isBreakPointSet(int n){
        if (n>1) return sourceCodeLines.get(n-1).isBreakPointSet();
        return false;
    }
    
    public int sourceCodeSize(){
        return sourceCodeLines.size();
    }
    
    public Integer[] getBreakPoints() {
        Vector<Integer> breakPoints = new Vector<Integer>();
        
        for ( int line =1; line<=sourceCodeSize(); line++){
            if (isBreakPointSet(line)) breakPoints.add(line);
        }
        
        Integer[] a = new Integer[0];
        //if (breakPoints.isEmpty()) return new Integer[0];
        return (Integer[])breakPoints.toArray(a);
        
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
    
    /*
     * Sets the name, beginline and end line of the current record.  This
     * is executed when a FUNCTION byte code is called. 
     */
    public void setFunction(String name, int start, int end){
        FunctionEnvironmentRecord topRecord = EnvironmentRecordStack.peek();
        topRecord.setFunction(name, start, end);
        topRecord.setCurrentLine(currentLineNumber);
    }
    
    /*
     * Creates a new, empty funct env. record and pushes it onto the record 
     * stack. This is done at initialization, and every time a CALL byte code
     * is encountered. 
     */
    public void beginScope(){
        FunctionEnvironmentRecord newRecord = new FunctionEnvironmentRecord();
        newRecord.beginScope();
        EnvironmentRecordStack.push(newRecord);
    }
    
    public void loadLiteral(String varname){
        FunctionEnvironmentRecord currentRecord = EnvironmentRecordStack.peek();
        currentRecord.enter(varname, getCurrentOffset());
    }

    
    
    /*
     * Goes through the program object and adds all of the argmuents of the
     * LINE byte codes except for -1's. 
     */
    private void inventoryValidBreakPoints(){
        for(int i=0; i< program.getNumberOfByteCodes(); i++){
            ByteCode code = program.getCode(i);
            if ( code.getClass().getName().equals("interpreter.ByteCode.LineByteCode")){
                int line =((LineByteCode)code).lineNumber();
                if (line != -1)validBreakPoints.add(line);
            }
        }
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
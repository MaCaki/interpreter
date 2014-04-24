package interpreter;
import java.util.*;
import interpreter.ByteCode.*;
/**
 *
 * @author admin
 */
public class VirtualMachine {
    
    protected int pc;
    protected Stack<Integer> returnAddrs;
    protected RunTimeStack runStack;
    protected boolean isRunning;
    protected Program program;
    public boolean dumping = false;
    boolean haltReached=false;
           
            
    public VirtualMachine(Program prog){
        program = prog;
        
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
    
    public void incrementPc(){
        pc++;
    }
    
    public void setPc(int n){
        pc = n;
    }
    
    public int getPc(){
        return pc;
    }
    
    public void pushReturnAddrs(int n){
        returnAddrs.push(n);
    }
    public int popReturnAddrs(){
        return returnAddrs.pop();
    }
    
   
    public void turnOffVm(){
        isRunning = false;
        haltReached = true;
    }
    
    public boolean doneExecuting(){
        return haltReached;
    }
    
    public void newFrameOnRunTimeStackAt( int numArguments){
        runStack.newFrameAt(numArguments);
    }
    
    public int popRunStack(){
        return runStack.pop();
    }
    
    public void pushRunStack(int n){
        runStack.push(n);
    }
    
    public int getValueAtOffset(int n){
        return runStack.getValueAtOffset(n);
    }
    
    public void popRunStackFrame(){
        runStack.popFrame();
    }
    
    public int storeRunStack(int offset){
        return runStack.store(offset);
    }
    
    public int peekRunStack(){
        return runStack.peek();
    }
    
    public int getCurrentOffset(){
        return runStack.currentOffset();
    }

}

package interpreter;
import java.util.*;
import interpreter.ByteCode.*;
/**
 *
 * @author admin
 */
public class VirtualMachine {
    
    public int pc;
    private Stack<Integer> returnAddrs;
    public RunTimeStack runStack;
    private boolean isRunning;
    private Program program;
    public boolean dumping = false;
           
            
    VirtualMachine(Program prog){
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
            pc++;
        }
    }
    
    
    public void pushReturnAddrs(int n){
        returnAddrs.push(n);
    }
    public int popReturnAddrs(){
        return returnAddrs.pop();
    }
    
   
    public void turnOffVm(){
        isRunning = false;
    }
    
    public void newFrameOnRunTimeStackAt( int numArguments){
        runStack.newFrameAt(numArguments);
    }
    
    
}

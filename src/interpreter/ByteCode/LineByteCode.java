package interpreter.ByteCode;
import interpreter.VirtualMachine;
import interpreter.debugger.DebuggerVirtualMachine;

/**
 * Updates the line number for the Debugger VM
 * 
 * @author Raskolnikov
 */
public class LineByteCode extends ByteCode{
    int lineNumber;
       
    public void init(String arguments[]){
        lineNumber = Integer.parseInt(arguments[1]);
    }
    
    public int lineNumber(){
        return lineNumber;
    }
    
    // Every bytecode will be responsible for its own execution. 
    public void execute(VirtualMachine vm){
        ((DebuggerVirtualMachine)vm).setCurrentLineNumber(lineNumber);
    }
    
}
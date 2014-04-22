package interpreter.ByteCode;
import interpreter.VirtualMachine;
import interpreter.debugger.DebuggerVirtualMachine;

/**
 * Updates the line number for the Debugger VM
 * 
 * @author Raskolnikov
 */
public class LineByteCode {
    int lineNumber;
       
    public void init(String arguments[]){
        lineNumber = Integer.parseInt(arguments[1]);
    }
    
    // Every bytecode will be responsible for its own execution. 
    public void execute(DebuggerVirtualMachine vm){
        vm.setLineNumber(lineNumber);
    }
    
}
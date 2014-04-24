package interpreter.ByteCode;
import interpreter.ByteCode.ByteCode;
import interpreter.VirtualMachine;
import interpreter.debugger.DebuggerVirtualMachine;

/**
 * A debugger bytecode that tells the debugger VM what the name and offset
 * is of the formal arguments that are being passed to the interpreter. 
 * 
 * 
 * Example: 
 *  FORMAL n 0
 * 
 * @author Raskolnikov
 */
public class FormalByteCode extends ByteCode{
    
    String name; 
    int offset;
    
    public void init(String args[]){
        name = args[1];
        offset = Integer.parseInt(args[2]);
    }
    
    public void execute(VirtualMachine vm){
        ((DebuggerVirtualMachine)vm).enterIntoCurrentRecord(name, offset);
        
    }
}

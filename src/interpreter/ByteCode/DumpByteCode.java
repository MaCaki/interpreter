package interpreter.ByteCode;
import interpreter.VirtualMachine;
/**
 * This bytecode indicates to the VM whether or not to dump out the current 
 * state of the program at each execution of a bytecode.  It is for debugging
 * purposes only.
 * 
 * @author admin
 */
public class DumpByteCode extends ByteCode{
    String state;
    
    // the array of arguments will simply be ["DUMP"]["ON"] or ["OFF"]
    public void init(String arguments[]){
        state = arguments[1];
    }
    
    public void execute(VirtualMachine vm){
        
    }
    
}

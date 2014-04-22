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
    String args;
    
    // the array of arguments will simply be ["DUMP"]["ON"] or ["OFF"]
    public void init(String arguments[]){
        args = arguments[1];
    }
    
    public void execute(VirtualMachine vm){
        if (args.contains("ON"))  vm.dumping = true;
        if (args.contains("OFF")) vm.dumping = false;
    }
    
    public String toString(){
        return "";
    }
}

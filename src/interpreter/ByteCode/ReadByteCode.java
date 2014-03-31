package interpreter.ByteCode;
import interpreter.VirtualMachine;
/**
 * READ;
 * 
 * Read an integer,  prompt the user for input; put the value just read on 
 * top of the stack.  
 *
 * @author admin
 */
public class ReadByteCode extends ByteCode{
     public void init(String arguments[]){}
    
    // Every bytecode will be responsible for its own execution. 
    public void execute(VirtualMachine vm){}
}

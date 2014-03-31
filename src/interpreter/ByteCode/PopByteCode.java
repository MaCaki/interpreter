package interpreter.ByteCode;
import interpreter.VirtualMachine;
/**
 *Pop the top n levels of the runtime stack
 * @author admin
 */
public class PopByteCode extends ByteCode{
     public void init(String arguments[]){}
    
    // Every bytecode will be responsible for its own execution. 
    public void execute(VirtualMachine vm){}
}

package interpreter.ByteCode;
import interpreter.VirtualMachine;
/**
 * LIT n - the VM loads the value n to the top of the stack. 
 * @author admin
 */
public class LitByteCode extends ByteCode{
    int value;
    
    public void init(String arguments[]){
        value = Integer.parseInt(arguments[1]);
    }    
    
    public void execute(VirtualMachine vm){
        
    }
}

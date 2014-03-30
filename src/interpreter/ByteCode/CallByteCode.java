package interpreter.ByteCode;
import interpreter.VirtualMachine;
/**
 * CALL <funcname> - transfer control to the indicated function. 
 * 
 * 
 * @author admin
 */
public class CallByteCode extends ByteCode{
    String funcname;
    
    public void init(String arguments[]){
        funcname = arguments[1];
    }
    
    public void exectute(VirtualMachine vm){
        
    }
}

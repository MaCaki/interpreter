package interpreter.ByteCode;
import interpreter.VirtualMachine;
/**
 * STORE n <id>
 * 
 * - pop the top of the stack; store value into the offset n from the 
 * start of the frame;  <id> is used as a comment, it's the variable name 
 * where the data is stored. 
 * 
 * @author admin
 */
public class StoreByteCode extends ByteCode{
    
    int value;
    String id;
    
    public void init(String arguments[]){
        value = Integer.parseInt(arguments[1]);
        id = arguments[2];
    }
    
    public void execute(VirtualMachine vm){
        
    }
    
}

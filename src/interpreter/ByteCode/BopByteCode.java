package interpreter.ByteCode;
import interpreter.VirtualMachine;
/**
 * BOP <binary op> 
 * Pop the top two levels of the stack an perform indicated operation : 
 * operations are: +-/* == != <= > >= > | &. 
 * 
 * The lower level is the first operand. 
 * @author admin
 */
public class BopByteCode extends ByteCode{
    
    String binaryOp;
    
    public void init(String arguments[]){
        binaryOp = arguments[1];
    }
    
    public void execute(VirtualMachine vm){
    }

       
}

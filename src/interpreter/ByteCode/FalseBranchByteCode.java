
package interpreter.ByteCode;
import interpreter.VirtualMachine;
/**
 *Ex: FALSEBRANCH <label> 
 * This pops the top of the stack and checks whether or not it is false (0).
 * If it is false, then branch to <label>, else execute the next byte code. 
 * 
 * @author admin
 */
public class FalseBranchByteCode extends ByteCode{
    
    String label;
      
    public void init(String arguments[]){
        label = arguments[1];
    }
    
    public void execute(VirtualMachine vm){}
    
}


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
    
    public String targetLabel;
    public int targetAddrs;
      
    public void init(String arguments[]){
        targetLabel = arguments[1];
    }
    
    public void execute(VirtualMachine vm){
        if (vm.popRunStack()==0) vm.setPc(targetAddrs);
       }
    
    public String toString(){
        return "FALSEBRANCH " + targetLabel;
    }
}


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
        if (vm.runStack.pop()==0) vm.pc = targetAddrs;
        
        if (vm.dumping) {
            System.out.println("FALSEBRANCH " + targetLabel);
            vm.runStack.dump();
        }
    }
    
}

package interpreter.ByteCode;
import interpreter.VirtualMachine;
/**
 * LABEL <label>; 
 * this indicates the target for the branching code such as FALSEBRANCH
 * or GOTO.  
 * @author admin
 */
public class LabelByteCode extends ByteCode{
    
    public String label;
    
    
    public void init(String arguments[]){
        label = arguments[1];
    }
    
    public void execute(VirtualMachine vm){
         if (vm.dumping) {
            System.out.println("LABEL " + label);
            vm.runStack.dump();
        }
    }
    
}

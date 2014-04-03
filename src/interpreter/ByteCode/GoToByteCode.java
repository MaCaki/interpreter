package interpreter.ByteCode;
import interpreter.VirtualMachine;
/**
 *
 * @author admin
 */
public class GoToByteCode extends ByteCode{
    
    public String label;
    public int targetAddrs;
    
    public void init(String arguments[]){
        label = arguments[1];
    }
    
    public void execute(VirtualMachine vm){
        vm.pc= targetAddrs;
        
        if (vm.dumping) {
            System.out.println("GOTO " + label);
            vm.runStack.dump();
        }
    }
    
}

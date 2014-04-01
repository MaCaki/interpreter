package interpreter.ByteCode;
import interpreter.VirtualMachine;
/**
 *
 * @author admin
 */
public class GoToByteCode extends ByteCode{
    
    private String label;
    
    public void init(String arguments[]){
        label = arguments[1];
    }
    
    public void execute(VirtualMachine vm){
        vm.pc= Integer.parseInt(label);
        
        if (vm.dumping) {
            System.out.println("GOTO " + label);
            vm.runStack.dump();
        }
    }
    
}

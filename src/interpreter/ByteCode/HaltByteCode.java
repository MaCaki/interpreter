package interpreter.ByteCode;
import interpreter.VirtualMachine;

/**
 * Stops the VirtualMachine and quits the program. 
 * @author admin
 */
public class HaltByteCode extends ByteCode{
    
    public void execute(VirtualMachine vm){
        vm.turnOffVm();
    }
   
    
}

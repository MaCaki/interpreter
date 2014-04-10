package interpreter.ByteCode;
import interpreter.VirtualMachine;
/**
 * CALL <funcname> - transfer control to the indicated function. 
 * 
 * 
 * @author admin
 */
public class CallByteCode extends ByteCode{
    public String func;
    public int targetAddrs;
    
    
    public void init(String arguments[]){
        func = arguments[1];
    }
    
    public void execute(VirtualMachine vm){
        // store the address of where the VM left off at the CallByteCode. 
        // this will be incremented after the ReturnByteCode is executed within 
        // the VM
        vm.pushReturnAddrs(vm.getPc());
        vm.setPc(targetAddrs);
    }
    
    public String toString(){
        return "CALL " + func;
    }
}

package interpreter.ByteCode;
import interpreter.VirtualMachine;
/**
 *RETURN <funcname> - Return from the current function; <funcname> is used 
 * as a comment to indicate the current function. 
 * 
 * @author admin
 */
public class ReturnByteCode extends ByteCode{
    
    String func = "";
    
    public void init(String arguments[]){
        if (arguments.length>1) func = arguments[1];
    }
    
    // Every bytecode will be responsible for its own execution. 
    public void execute(VirtualMachine vm){
        vm.pc = vm.popReturnAddrs();
        vm.runStack.popFrame();
        
        if (vm.dumping) {
            System.out.println("RETURN " + func);
            vm.runStack.dump();
        }
    }
}

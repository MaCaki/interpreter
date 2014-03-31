package interpreter.ByteCode;
import interpreter.VirtualMachine;
/**
 * LIT n - the VM loads the value n to the top of the stack. 
 * @author admin
 */
public class LitByteCode extends ByteCode{
    int value;
    String varName = "";
    
    public void init(String arguments[]){
        value = Integer.parseInt(arguments[1]);
        if (arguments.length>2) varName = arguments[2];
    }    
    
    public void execute(VirtualMachine vm){
        vm.runStack.push(value);
        if (vm.dumping) {
            System.out.println("LIT " + value + " " + varName );
            vm.runStack.dump();
        }
    }
}

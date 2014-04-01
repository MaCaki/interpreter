package interpreter.ByteCode;
import interpreter.VirtualMachine;
/**
 * STORE n <id>
 * 
 * - pop the top of the stack; store value into the offset n from the 
 * start of the frame;  <id> is used as a comment, it's the variable name 
 * where the data is stored. 
 * 
 * @author admin
 */
public class StoreByteCode extends ByteCode{
    
    int offset;
    String id = "";
    
    public void init(String arguments[]){
        offset = Integer.parseInt(arguments[1]);
        if (arguments.length>2) id = arguments[2];
    }
    
    public void execute(VirtualMachine vm){
        vm.runStack.store(offset);
        
        if (vm.dumping) {
            System.out.println("STORE " + offset + " " + id);
            vm.runStack.dump();
        }
    }
    
}

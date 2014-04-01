package interpreter.ByteCode;
import interpreter.VirtualMachine;
/**
 *
 * @author Raskolnikov
 */
public class LoadByteCode extends ByteCode{
    
    int offset;
    String id;
    
    public void init(String arguments[]){
        offset = Integer.parseInt(arguments[1]);
        id = arguments[2];
    }
    
    public void execute(VirtualMachine vm){
        int var = vm.runStack.load(offset);
        vm.runStack.push(var);
        
        if (vm.dumping) {
            System.out.println("LOAD " + offset + " " + id);
            vm.runStack.dump();
        }
        
    }
    
}

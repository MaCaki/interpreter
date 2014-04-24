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
        int var = vm.getValueAtOffset(offset);
        vm.pushRunStack(var);
    }
    
    
    public String toString(){
        return "LOAD " + offset + " " + id + "      " + "<load " +id + ">";
    }
}

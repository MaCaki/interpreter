package interpreter.ByteCode;
import interpreter.VirtualMachine;
/**
 *Pop the top n levels of the runtime stack
 * @author admin
 */
public class PopByteCode extends ByteCode{
    int numLevels;
    
    public void init(String arguments[]){
        numLevels = Integer.parseInt(arguments[1]);
    }
    
    public void execute(VirtualMachine vm){
        for(int i=0; i<numLevels; i++) vm.popRunStack();
        
    }
    
    public String toString(){
        return "POP " + numLevels;
    }
}

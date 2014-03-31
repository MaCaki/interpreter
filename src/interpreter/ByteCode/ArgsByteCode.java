package interpreter.ByteCode;
import interpreter.VirtualMachine;

/**
 * ARGS n - where n is the number of arguments. 
 * 
 * Used prior to calling a function. 
 * 
 * This instruction is immediately followed by the CALL instruction; the 
 * function has n args so ARGS n instructs the interpreter to set up a new frame
 * n down from the top, so it will include the arguments. 
 * 
 * @author admin
 */

public class ArgsByteCode extends ByteCode{
    int numArguments;
    
    public void init(String arguments[]){
        numArguments = Integer.parseInt(arguments[1]);
    }
        
    public void execute(VirtualMachine vm){
        vm.runStack.newFrameAt(numArguments);
    }
    
}

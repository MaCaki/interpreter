package interpreter.ByteCode;

import interpreter.VirtualMachine;
import interpreter.debugger.DebuggerVirtualMachine;

/**
 * 
 * @author Raskolnikov
 */
public class DebugReturnCode extends ReturnByteCode{
    
    public void execute(VirtualMachine vm){
        super.execute(vm);
        ((DebuggerVirtualMachine)vm).returnFromFunction();
    }
}

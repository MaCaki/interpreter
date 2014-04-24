package interpreter.ByteCode;

import interpreter.ByteCode.CallByteCode;
import interpreter.VirtualMachine;
import interpreter.debugger.DebuggerVirtualMachine;

/**
 *
 * @author Raskolnikov
 */
public class DebugCallCode extends CallByteCode{
    
    public void execute(VirtualMachine vm){
        super.execute(vm);
        ((DebuggerVirtualMachine)vm).beginScope();
    }
}

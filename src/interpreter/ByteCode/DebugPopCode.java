
package interpreter.ByteCode;

import interpreter.VirtualMachine;
import interpreter.debugger.DebuggerVirtualMachine;

/**
 *
 * @author Raskolnikov
 */
public class DebugPopCode extends PopByteCode{
    
    public void execute(VirtualMachine vm){
        super.execute(vm);
        ((DebuggerVirtualMachine)vm).popFunctionEnvironmentRecordStack(numLevels);
    }
}

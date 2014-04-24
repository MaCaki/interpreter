package interpreter.ByteCode;
import interpreter.ByteCode.LitByteCode;
import interpreter.VirtualMachine;
import interpreter.debugger.DebuggerVirtualMachine;

/**
 *
 * @author Raskolnikov
 */
public class DebugLitCode extends LitByteCode{
 
     public void execute(VirtualMachine vm){
         super.execute(vm);
         if (!varName.equals("")) ((DebuggerVirtualMachine)vm).loadLiteral(varName);
    }
        
}

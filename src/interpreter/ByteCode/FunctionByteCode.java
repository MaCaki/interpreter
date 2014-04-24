package interpreter.ByteCode;
import interpreter.ByteCode.ByteCode;
import interpreter.VirtualMachine;
import interpreter.debugger.DebuggerVirtualMachine;

/**
 * This code is a debugging byte code that has as arguments
 * the name of the function and the starting and ending line numbers:
 * 
 * FUNCTION fib 2 11
 * 
 * @author Raskolnikov
 */
public class FunctionByteCode extends ByteCode{
    
    String name;
    int startLine, endLine;
    
    public void init(String args[]){
        name = args[1];
        startLine = Integer.parseInt(args[2]);
        endLine = Integer.parseInt(args[3]);
    }
    
    public void execute(VirtualMachine vm){
        ((DebuggerVirtualMachine)vm).setFunction(name, startLine, endLine);
    }
    
    
}

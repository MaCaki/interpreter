package interpreter.debugger;
import interpreter.*;
import interpreter.ByteCode.BinaryOpTable;
import interpreter.ByteCode.ByteCode;
import java.util.Stack;
import java.util.Vector;

/**
 *When the debug flag is set, the interpreter will create an instance of a
 * DebuggerVM to execute the program.   
 * 
 * 
 * @author Raskolnikov
 */
public class DebuggerVirtualMachine extends VirtualMachine{
    private int lineNumber;
    Vector<String> sourceFile;
    
    public DebuggerVirtualMachine(Program prog, Vector<String> source){
        super(prog);
        sourceFile = source;
    }
    // Each time a function is entered in the Program, the address the VM should
    // return to once the Program returns from the function is pushed onto the
    // returnAddrs stack. 
    // Every ByteCode object should have an exectute( VirtualMacine ) method. 
    // 
    
    
    public void setLineNumber(int n){
        lineNumber = n;
    }

}

package interpreter.ByteCode;
import Interpreter.*;
/**
 *
 * This is the abstract class defining the ByteCode objects.  The ByteCode 
 * objects represent individual machine code commands and are contained within
 * instances of the Program class. 
 * 
 * @author admin
 */

public abstract class ByteCode {
    
    
    // Every bytecode will be responsible for its own initialization 
    
    public void init(String arguments[]){}
    
    // Every bytecode will be responsible for its own execution. 
    public void execute(VirtualMachine vm){}
    
}

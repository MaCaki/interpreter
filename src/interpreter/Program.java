
package interpreter;
import java.util.*;
import interpreter.ByteCode.*;
/**
 *  This is the Program class, which is an object representation of the 
 * bytecode file.  Bytecodes are translated into ByteCode instances and stored
 * in the bytecode vector.
 * 
 * Access to the bytecodes is through getCode.  
 * @author admin
 */
public class Program {
    
    private Vector<ByteCode> byteCodes = new Vector<ByteCode>();
    
    public void pushByteCode(ByteCode code){
        byteCodes.add(code);
    }
    
    public ByteCode getCode(int index){
        return (ByteCode)byteCodes.get(index);
    }
    
    // When the bytecodes are loaded into the program, the addresses do 
    // not yet point to the corresponding bytecodes to be exectued.  
    public void resolveAddresses(){
        
    }
    
    
    public void execute(VirtualMachine vm){
        
    }
    
    
}

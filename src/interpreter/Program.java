
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
    
    public int getNumberOfByteCodes(){
        return byteCodes.size();
    }
    
    // When the bytecodes are loaded into the program, the addresses do 
    // not yet point to the corresponding bytecodes to be exectued.  
    public void resolveAddresses(){
        HashMap<String,Integer> addresses = new java.util.HashMap<String,Integer>();
        
        
        // Map each Name associated with a Label byte code to the address
        // of that label byte code. 
        for ( int i=0; i<byteCodes.size(); i++){
            if (byteCodes.get(i).getClass().getName()=="interpreter.ByteCode.LabelByteCode") {
                String label = ((LabelByteCode)byteCodes.get(i)).label;
                addresses.put(label, i);
            }
        }
        
        
        // Run through the ByteCodes a second time to resolve the String targets
        // of GOTO, CALL, and FALSEBRANCH bytecodes to the integer address
        // of the corresponding LABEL bytecode. 
        // NOTE: -->   Currently this loop calls .getClass().getName.equals()
        // three times on each bytecode that is not a redirecting code.  
        // this could resulting in very slow loading of larger bytecode files?  
        for ( int i=0; i<byteCodes.size(); i++){
            //CALL bytecodes
            if (byteCodes.get(i).getClass().getName().equals("interpreter.ByteCode.CallByteCode") 
                    ||byteCodes.get(i).getClass().getName().equals("interpreter.ByteCode.DebugCallCode") ) {
                CallByteCode callCode = ((CallByteCode)byteCodes.get(i));
                String label = callCode.func;
                callCode.targetAddrs = addresses.get(label);
            }
            
            //DebugCallCode
            if (byteCodes.get(i).getClass().getName().equals("interpreter.ByteCode.DebugCallCode") ) {
                DebugCallCode debugCallCode = ((DebugCallCode)byteCodes.get(i));
                String label = debugCallCode.func;
                debugCallCode.targetAddrs = addresses.get(label);
            }
            
            // GOTO bytecodes
            if (byteCodes.get(i).getClass().getName().equals("interpreter.ByteCode.GoToByteCode")) {
                GoToByteCode gotoCode = ((GoToByteCode)byteCodes.get(i));
                String label = gotoCode.label;
                gotoCode.targetAddrs = addresses.get(label);
            }
            
            // FALSEBRANCH
            if (byteCodes.get(i).getClass().getName().equals("interpreter.ByteCode.FalseBranchByteCode")) {
                FalseBranchByteCode branchCode = ((FalseBranchByteCode)byteCodes.get(i));
                String label =  branchCode.targetLabel;
                branchCode.targetAddrs = addresses.get(label);
            }
            
        }
        
    }
    
}

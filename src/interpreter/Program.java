
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
        HashMap<String,Integer> addresses = new java.util.HashMap<String,Integer>();
        
        for ( int i=0; i<byteCodes.size(); i++){
            if (byteCodes.get(i).getClass().getName()=="interpreter.ByteCode.LabelByteCode") {
                String label = ((LabelByteCode)byteCodes.get(i)).label;
                addresses.put(label, i);
            }
        }
        
        for ( int i=0; i<byteCodes.size(); i++){
            if (byteCodes.get(i).getClass().getName()=="interpreter.ByteCode.CallByteCode") {
                CallByteCode callCode = ((CallByteCode)byteCodes.get(i));
                String label = callCode.func;
                int targetAddress = addresses.get(label);
                callCode.func = Integer.toString(targetAddress);
            }
            // GOTO
            
            // FALSEBRANCH
            if (byteCodes.get(i).getClass().getName()=="interpreter.ByteCode.FalseBranchByteCode") {
                FalseBranchByteCode branchCode = ((FalseBranchByteCode)byteCodes.get(i));
                String label =  branchCode.targetLabel;
                int targetAddress = addresses.get(label);
                branchCode.targetLabel = Integer.toString(targetAddress);
            }
            
        }
        
    }
    
}

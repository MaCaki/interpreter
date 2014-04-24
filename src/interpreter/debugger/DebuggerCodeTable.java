package interpreter.debugger;

import interpreter.ByteCode.*;
import interpreter.CodeTable;


/**
 *  Replaces the interpreter's regular code table
 * 
 * When the debug flag is set, the byte code loader will use this table to construct
 * the program object that will be passed to the DebugVM. 
 * 
 * 
 * @author Raskolnikov
 */
public class DebuggerCodeTable extends CodeTable{
    
     public static Class get(String byteCodeName){
        return codeTable.get(byteCodeName);
    } 
    public static void init(){
         // for each type of ByteCode, create a hash {"type" => ClassOfByteCode}.
        codeTable.put("ARGS", ArgsByteCode.class);
        codeTable.put("BOP", BopByteCode.class);
        codeTable.put("CALL", DebugCallCode.class);
        codeTable.put("DUMP", DumpByteCode.class);
        codeTable.put("FALSEBRANCH", FalseBranchByteCode.class);
        codeTable.put("GOTO", GoToByteCode.class);
        codeTable.put("HALT", HaltByteCode.class);
        codeTable.put("LOAD", LoadByteCode.class);
        codeTable.put("LABEL", LabelByteCode.class);
        codeTable.put("LIT", DebugLitCode.class);
        codeTable.put("POP", DebugPopCode.class);
        codeTable.put("READ", ReadByteCode.class);
        codeTable.put("RETURN", DebugReturnCode.class);
        codeTable.put("STORE", StoreByteCode.class);
        codeTable.put("WRITE", WriteByteCode.class);  
        codeTable.put("LINE", LineByteCode.class);
        codeTable.put("FUNCTION", FunctionByteCode.class);
        codeTable.put("FORMAL", FormalByteCode.class);
    }
    
}

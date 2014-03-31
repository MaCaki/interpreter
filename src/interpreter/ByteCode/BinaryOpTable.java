package interpreter.ByteCode;

/**
 *  This table is used by BopByteCode to look up the operation corresponding
 * to its argument.   
 * The possible binary operations are : +-/* == != <= > >= > | &.
 * @author Raskolnikov
 */
public class BinaryOpTable {
   private static java.util.HashMap<String,BinaryOperation> opTable = 
           new java.util.HashMap<String,BinaryOperation>();

   // this table is itialized once the VM starts to execute a program. 
   public static void init(){
       
       opTable.put("+", new BinaryOperation(){
           public int binaryOp(int i, int j){return i+j;}
       });
       opTable.put("-", new BinaryOperation(){
           public int binaryOp(int i, int j){return i-j;}
       });
       opTable.put("*", new BinaryOperation(){
           public int binaryOp(int i, int j){return i*j;}
       });
       opTable.put("/", new BinaryOperation(){
           public int binaryOp(int i, int j){return i/j;}
       });
       
       opTable.put("==", new BinaryOperation(){
           public int binaryOp(int i, int j){return (i==j ? 1 :0);}
       });
       
       opTable.put("!=", new BinaryOperation(){
           public int binaryOp(int i, int j){return (i!=j ? 1 :0);}
       });
       
       opTable.put("<=", new BinaryOperation(){
           public int binaryOp(int i, int j){return (i<=j ? 1 :0);}
       });
       
       opTable.put(">=", new BinaryOperation(){
           public int binaryOp(int i, int j){return (i>=j ? 1 :0);}
       });
       
       opTable.put("<", new BinaryOperation(){
           public int binaryOp(int i, int j){return (i<j ? 1 :0);}
       });
       opTable.put(">", new BinaryOperation(){
           public int binaryOp(int i, int j){return (i>j ? 1 :0);}
       });
       opTable.put("|", new BinaryOperation(){
           public int binaryOp(int i, int j){return ((i==1)|(j==1) ? 1 :0);}
       });
       opTable.put("&", new BinaryOperation(){
           public int binaryOp(int i, int j){return ((i==1)&(j==1) ? 1 :0);}
       });
   }
   
   public static BinaryOperation get(String key){
       return opTable.get(key);
   }
}


interface BinaryOperation{
    public int binaryOp(int i, int j);
}

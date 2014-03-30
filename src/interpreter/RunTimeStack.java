
package interpreter;
import java.util.*;
/**
 *  The runtime stack records and processes the stack of active frames for a 
 * VirtualMachine when it is interpreting a program. 
 * 
 * The RunTimeStack is modeled using a Stack Object, and each frame  is a Vector. 
 * 
 * 
 * @author admin
 */
public class RunTimeStack {
    
    private Stack<Integer> framePointers;
    
    private Vector<Integer> runStack;
            
    
    
    public void dump(){
        
    }
    
    public int pop(){
        return runStack.lastElement();
    }
    
    public int push( int i){
        runStack.addElement(i);
        return i;
    }
    
    public void newFrameAt(int offset){
        
    }
            
    public void popFrame(){
        
    }
            
    
    
}

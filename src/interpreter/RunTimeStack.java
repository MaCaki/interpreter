
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
            
    RunTimeStack(){
        framePointers = new Stack<Integer>();
        framePointers.push(0);
        runStack = new Vector<Integer>();
    }
    // dump the RunTimStack for debugging info
    public void dump(){
        //int numFrames = framePointers.size();
        
        System.out.println(runStack.toString());
        
    }
    
    // pop the top item from the runtime stack
    public int pop(){
        int top = runStack.lastElement();
        runStack.removeElementAt(runStack.size()-1);
        return top;
    }
    
    // Pushes i on the runtime stack
    public int push( int i){
        runStack.addElement(i);
        return i;
    }
    
    
    // the offset indicates the number of slots down from the top of the 
    // runtime stack for startgin the new frame
    public void newFrameAt(int offset){
        int size = runStack.size();
        framePointers.push(size-offset);
        
    }
            
    // Pop the top frame when we return from a function. Before popping the 
    // function's return value is at the top of the stack so we'll save the 
    // value, pop the top frame then push the return value.
    public void popFrame(){
        
    }
            
    //Used to store into variables
    public int store(int offset){
        return offset;
    }
    
    // Used to load variables onto the stack 
    public int load(int offset){
        return offset;
    }
    
    // Used to load literals onto the stack - e.g. for lit 5 we call push(5)
    public Integer push(Integer i){
        runStack.addElement(i);
        return i;
    }
    
}

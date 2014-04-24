
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
            
    public RunTimeStack(){
        framePointers = new Stack<Integer>();
        framePointers.push(0);
        runStack = new Vector<Integer>();
    }
    
    // dump the RunTimStack for debugging info
    // Return a string representation of the runStack to the VM to be dumped
    // to the console. 
    public void dump(){
        Vector<Integer> stack = (Vector)runStack.clone();
        Vector<Integer> framePtrs = (Vector)framePointers.clone();
        
        Vector frameArray[] = new Vector[framePtrs.size()];
        
        for( int i = framePtrs.size(); i >0 ; i-- ){
            frameArray[i-1] = new Vector<Integer>();
            int startIndex = framePtrs.get(i-1);
            int currentSize = stack.size();
            
            for (int k=startIndex; k< currentSize; k++){
                frameArray[i-1].add(stack.remove(startIndex));
            }
        }
        
        for ( int i=0; i<framePtrs.size(); i++){
            System.out.print(frameArray[i].toString());
        }
        System.out.println();
        
    }
    
    public int peek(){
        return runStack.lastElement();
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
    
    
    /* the offset indicates the number of slots down from the top of the 
     runtime stack for startgin the new frame
    */ 
    public void newFrameAt(int offset){
        int size = runStack.size();
        framePointers.push(size-offset);
        
    }
            
    /* Pop the top frame when we return from a function. Before popping the 
     function's return value is at the top of the stack so we'll save the 
     value, pop the top frame then push the return value.
    */
    public void popFrame(){
        int top = this.pop();
        int currentSize = runStack.size();
        int startIndex = framePointers.pop();
        for ( int i=startIndex; i<currentSize; i++){
            runStack.remove(startIndex);
        }
        this.push(top);
    }
            
    //Used to store into variables
    public int store(int offset){
        int top = this.pop();
        runStack.add(framePointers.peek()+offset, top);
        runStack.removeElementAt(framePointers.peek()+offset+1);
        return top;
    }
    
    // Used to load variables onto the stack 
    // returns the value that is at a given offset from the start of the
    // current frame. 
    public int getValueAtOffset(int offset){
        
        return runStack.get(framePointers.peek()+offset);
    }
    
    // Used to load literals onto the stack - e.g. for lit 5 we call push(5)
    public Integer push(Integer i){
        runStack.addElement(i);
        return i;
    }
    /*
     * Returns the difference between the current slot in the runStack and the
     * position of the current frame pointer.
     * 
     */
    public int currentOffset(){
        int offset = (runStack.size()-1) -framePointers.peek();
        
        return offset;
    }
    
}

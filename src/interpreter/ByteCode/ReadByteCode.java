package interpreter.ByteCode;
import interpreter.VirtualMachine;
import java.util.Scanner;
/**
 * READ;
 * 
 * Read an integer,  prompt the user for input; put the value just read on 
 * top of the stack.  
 *
 * @author admin
 */
public class ReadByteCode extends ByteCode{
    
    public void init(String arguments[]){}
    
    public void execute(VirtualMachine vm){
        System.out.print("Enter an integer: ");
        Scanner in = new Scanner(System.in);
        int input = in.nextInt();
        
        vm.pushRunStack(input);
    }
    
    public String toString(){
        return "READ";
    }
}

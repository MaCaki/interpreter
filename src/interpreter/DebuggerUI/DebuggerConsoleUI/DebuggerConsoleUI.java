package interpreter.DebuggerUI.DebuggerConsoleUI;
import interpreter.Program;
import interpreter.debugger.*;
import java.lang.reflect.Method;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;


/**
 * This is the default UI that allows the user to interact with the debugger
 * through the console. 
 * 
 * The DebuggerConsoleUI is expecting to receive all of the parameters of the
 * DebuggerVM so that it can create its own vm and control the execution of it. 
 * 
 * @author Raskolnikov
 */
public class DebuggerConsoleUI {
    java.util.HashMap<String,String> commandTable = new java.util.HashMap<String,String>();
    DebuggerVirtualMachine vm;
    StringTokenizer currentCommandTokens;
    
    public DebuggerConsoleUI(Program prog, Vector<String> source){
        vm = new DebuggerVirtualMachine(prog, source);
        initializeCommandTable();
    }
    
    public void run(){
  
       
        System.out.println("-----Running in Debugger Mode-----");
        printSourceCode();

        
        waitForUser();
        
    }
    
    private void waitForUser(){
        help();
        String command;
        Scanner input = new Scanner(System.in);
        command = input.nextLine();
        currentCommandTokens = new StringTokenizer(command);
        
        String commandSymbol = currentCommandTokens.nextToken().trim();
        String functionName = commandTable.get(commandSymbol);
        Method UImethod;
        
        try{
            UImethod = DebuggerConsoleUI.class.getDeclaredMethod(functionName, (Class[])null );
            UImethod.invoke(this, (Object[])null);
        } catch (NoSuchMethodException e){
            System.out.println("Sorry, could not interpret command " + commandSymbol + 
                    ", please enter a new command");
        } catch (Exception e){
            System.out.println("Something went wrong, try again");
        }
    }
    
    
    
    void help(){
        System.out.print("Type ? for help \n >");
    }
    
    void continueRunning(){
        vm.continueRunning();
    }
    
    void setBreakPoints(){
        while(currentCommandTokens.hasMoreTokens()){
            try {
                vm.setBreakPoint(Integer.parseInt(currentCommandTokens.nextToken().trim()));
            } catch (Exception e){
                System.out.println("Something was wrong with your arguments.");
            }
        }
    }
    
    private void initializeCommandTable(){
        commandTable.put("c", "continueRunning");
    }
    
    private void printSourceCode(){
        
        System.out.print("Source Code: \n ------------------- \n");
        Vector<String> source = vm.getSourceFile();
        
        for (int i =0; i< source.size(); i++){
            System.out.printf("%2d:", i+1);
            System.out.print(source.get(i));
            if ( vm.hasBreakPoint(i)) {
                System.out.println("\t <= BREAKPOINT");
            } else {
                System.out.println();
            }
        }
        System.out.print(" ------------------- \n");
        
    }
    
}

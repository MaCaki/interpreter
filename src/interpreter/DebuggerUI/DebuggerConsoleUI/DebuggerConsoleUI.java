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
  
        vm.initialize();
        System.out.println("-----Running in Debugger Mode-----");
        printSourceCode();
        
        helpMenu();
        
        while (!vm.doneExecuting()){
             waitForUser();
        }
       
    }
    
    
    
    private void waitForUser(){
        help();
        String command;
        Scanner input = new Scanner(System.in);
        command = input.nextLine();
        currentCommandTokens = new StringTokenizer(command);
        
        String commandSymbol = currentCommandTokens.nextToken().trim();
        String functionName = commandTable.get(commandSymbol);
        
        if (functionName == null ) {
            System.out.println("Sorry, could not interpret command " + commandSymbol + 
                    ", please enter a new command");
            return;
        }
        
        Method UImethod;
        
        try{
            UImethod = DebuggerConsoleUI.class.getDeclaredMethod(functionName, (Class[])null );
            UImethod.invoke(this, (Object[])null);
        } catch (Exception e){
            System.out.println("Something went wrong, try again");
        }
    }
    
    
    
    public void help(){
        System.out.print("Type ? for help \n >");
    }
    
    public void continueRunning(){
        vm.continueRunning();
    }
    
    public void setBreakPoints(){
        while(currentCommandTokens.hasMoreTokens()){
            try {
                int line = Integer.parseInt(currentCommandTokens.nextToken().trim());
                vm.setBreakPoint(line);
            } catch (Exception e){
                System.out.println("Something was wrong with your arguments.");
            }
        }
    }
    
    public void clearBreakPoints(){
        while(currentCommandTokens.hasMoreTokens()){
            try {
                int line = Integer.parseInt(currentCommandTokens.nextToken().trim());
                vm.clearBreakPoint(line);
            } catch (Exception e){
                System.out.println("Something was wrong with your arguments.");
            }
        }
    }
    
   
    
    private void printSourceCode(){
        
        System.out.print("Source Code: \n ------------------- \n");
        
        for (int i =0; i< vm.sourceCodeSize(); i++){
            String line =vm.getSourceCodeLine(i);
            String breakFlag ="";
            if (vm.isBreakPointSet(i)){
                breakFlag = "\t <= ** BREAKPOINT SET **";
            }
            System.out.printf("%2d: %-50s %s \n", i+1, line, breakFlag);
        }
        System.out.print(" ------------------- \n");
        
    }
    
    private void helpMenu(){
        System.out.println("The following are valid commands: ");       
        System.out.printf("\t %-10s \t %s  \n", "c", "Continue execution of program until next break point.");
        System.out.printf("\t %-10s \t %s \n", "sb <lines>", "Set break point on the lines corresponding to the numbers passed to it.");
        System.out.printf("\t %-10s \t %s \n", "cb <lines>", "Clear break point on the lines corresponding to the numbers passed to it.");
        System.out.printf("\t %-10s \t %s \n", "print", "Print annotated source code.");
    }
    
    
    
    
     private void initializeCommandTable(){
        commandTable.put("?", "helpMenu");
        commandTable.put("c", "continueRunning");
        commandTable.put("sb", "setBreakPoints");
        commandTable.put("cb", "clearBreakPoints");
        commandTable.put("print", "printSourceCode");
    }
    
}

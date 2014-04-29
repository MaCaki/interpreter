package interpreter.DebuggerUI.DebuggerConsoleUI;
import interpreter.Program;
import interpreter.debugger.*;
import java.lang.reflect.Method;
import java.util.Scanner;
import java.util.Set;
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
        
 //       ConsoleUIunitTests.test_1(this);
        while (!vm.doneExecuting()){
             waitForUserCommand();
        }
        
         System.out.println("Execution Finished");
       
    }
    
    
    /*
     * The user enters a command which is hashed to the corresponding UI function
     * which is executed within this function.  Once the vm finishes executing
     * (stops for either a break point or end of program) this function returns
     * to the run() function. 
     */
    private void waitForUserCommand(){
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
            e.printStackTrace();
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
                if ( !vm.setBreakPoint(line)) {
                    System.out.println(line +" is not a valid break point");
                }
            } catch (Exception e){
                System.out.println("Something went wrong.");
            }
        }
        
        printCurrentBreakPoints();
        
    }
    
    public void clearBreakPoints(){
        while(currentCommandTokens.hasMoreTokens()){
            try {
                int line = Integer.parseInt(currentCommandTokens.nextToken().trim());
                vm.clearBreakPoint(line);
            } catch (Exception e){
                System.out.println("Something went wrong.");
            }
        }
    }
    
    private void printCurrentBreakPoints() {
        Integer[] breakPoints = vm.getBreakPoints();
        
        if (breakPoints.length>0){
            System.out.println("Breakpoints currently set at lines:");
            for (int i=0; i<breakPoints.length; i++) {
                System.out.print(breakPoints[i] + " ");
            }
            System.out.println();
        } else {
            System.out.println("There are no breakpoints set.");
        }
    }
        
    private void printSourceCode(){
        
        System.out.print("Source Code: \n ------------------- \n");
        
        for (int lineno = 1 ; lineno <= vm.sourceCodeSize(); lineno++){
            String line =vm.getSourceCodeLine(lineno);
            String breakFlag ="";
            if (vm.isBreakPointSet(lineno)){
                breakFlag = "\t <= ** BREAKPOINT SET **";
            }
            System.out.printf("%2d: %-50s %s \n", lineno, line, breakFlag);
        }
        System.out.print(" ------------------- \n");
        
    }
    
    private void displayCurrentFunction(){
        System.out.println("--- Current Function:  " + vm.getCurrentFunctionName() +"\n");
        int beginLine = vm.getCurrentFunctionStartLine();
        int endLine = vm.getCurrentFunctionEndLine();
        int currentLine = vm.getCurrentLineNumber();
        
        for (int n = beginLine; n <= endLine; n++){
            
            String line = vm.getSourceCodeLine(n);
            String currentLineFlag = "";
            if (n == currentLine) currentLineFlag= "\t <= EXECUTION STOPPED HERE";
            System.out.printf("%2d: %-50s %s \n", n, line, currentLineFlag);
        }
        System.out.println();
    }
    
    private void printVariables(){
        Set<String> vars = vm.getCurrentVariables();
        System.out.println("Current Variables: ");
        for(String variable : vars){
            
            int value = vm.getVariableValue(variable);
            System.out.printf( "%10s : %d \n", variable, value);
        }
        
        
    }
    
    
    private void printFunctionEnvironmentRecordStack(){
        
        for ( int i = vm.sizeOfFuntionCallStack()-1; i>=0; i--){
            System.out.println(vm.stringifyFunctionEnvironmentRecord(i));
        }
        
    }
    
    
    
    
    private void helpMenu(){
        System.out.println("The following are valid commands: ");       
        System.out.printf("\t %-10s \t %s  \n", "c", "Continue execution of program until next break point.");
        System.out.printf("\t %-10s \t %s \n", "setb <lines>", "Set break point on the lines corresponding to the numbers passed to it.");
        System.out.printf("\t %-10s \t %s \n", "clrb <lines>", "Clear break point on the lines corresponding to the numbers passed to it.");
        System.out.printf("\t %-10s \t %s \n", "print", "Print annotated source code.");
        System.out.printf("\t %-10s \t %s \n", "help/?", "Print out this help menu.");
        System.out.printf("\t %-10s \t %s \n", "quit", "Halt execution of the current program.");
        System.out.printf("\t %-10s \t %s \n", "dfn", "Display the current function indicating current point of execution.");
        System.out.printf("\t %-10s \t %s \n", "fest", "Prints the current state of the Function Environment Stack.");
        System.out.printf("\t %-10s \t %s \n", "vars", "Prints all the current variables and their values.");
        
    }
    
    
    
    
     private void initializeCommandTable(){
        commandTable.put("?", "helpMenu");
        commandTable.put("help", "helpMenu");
        commandTable.put("c", "continueRunning");
        commandTable.put("setb", "setBreakPoints");
        commandTable.put("clrb", "clearBreakPoints");
        commandTable.put("print", "printSourceCode");
        commandTable.put("quit", "quitExecution");
        commandTable.put("dfn", "displayCurrentFunction");
        commandTable.put("fest", "printFunctionEnvironmentRecordStack");
        commandTable.put("vars", "printVariables");
    }

    public void quitExecution(){
        vm.turnOffVm();
    }
    
}
class ConsoleUIunitTests {
    
    public static void test_1(DebuggerConsoleUI ui){
        StringTokenizer commands = new StringTokenizer("5 9");
        ui.currentCommandTokens =commands;
        
        ui.setBreakPoints();
        
        ui.continueRunning();
        
        
    }
    
}

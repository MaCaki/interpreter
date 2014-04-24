package interpreter;

import interpreter.DebuggerUI.DebuggerConsoleUI.DebuggerConsoleUI;
import interpreter.debugger.DebuggerCodeTable;
import interpreter.debugger.DebuggerVirtualMachine;
import java.io.*;
import java.util.Scanner;
import java.util.Vector;

/**
 * <pre>
 * 
 *  
 *   
 *     Interpreter class runs the interpreter:
 *     1. Perform all initializations
 *     2. Load the bytecodes from file
 *     3. Run the virtual machine
 *     
 * 
 * Debugger flag will be activated if the user passes -d as the first
 * parameter when calling the interpreter from the command line, like: 
 *  java -jar interpreter.jar -d factorial
 * 
 * Then the interpreter will look for both factorial.x and factorial.x.cod
 * and send the Program to a DebuggerVirtualMachine. 
 *   
 *  
 * </pre>
 */
public class Interpreter {

	ByteCodeLoader bcl;
        boolean debugging = false;
        String programName,sourceFile, byteCodeFile;

	public Interpreter(String args[]) {
            if (args[0].equals("-d")) {
                DebuggerCodeTable.init();
                debugging = true;
                programName = args[1];
                sourceFile = programName +".x";
                byteCodeFile = programName + ".x.cod";
            }else{
                CodeTable.init();
                byteCodeFile = args[0];
            }    

            try {
                    
                    bcl = new ByteCodeLoader(byteCodeFile);
            } catch (IOException e) {
                    System.out.println("**** " + e);
            }
	}

	void run() {
		Program program = bcl.loadCodes(debugging);
                //-------------Debugging Execution -------------
                if (debugging){
                    Vector<String> sourceFileVector = new Vector<String>();
                    try { 
                       sourceFileVector = SourceLoader.loadFile(sourceFile);
                    } catch (IOException e){
                        System.out.println("Could not find the source file " + programName +
                                ".x, you must make sure that this is in the same directory"
                                + "before the debugger can execute.");
                    }
                    DebuggerConsoleUI consoleUI = new DebuggerConsoleUI(program, sourceFileVector);
                    consoleUI.run();
                //------------- Regular Exection -------------
                } else{
                    VirtualMachine vm = new VirtualMachine(program);
                    vm.executeProgram();
                }
	}

	public static void main(String args[]) {
		if (args.length == 0) {
			System.out.println("***Incorrect usage, try: java interpreter.Interpreter <file>");
			System.exit(1);
		}
		(new Interpreter(args)).run();
	}
}


class SourceLoader{
    
    public static Vector<String> loadFile(String fileName)  throws IOException{
        Scanner fileScanner = new Scanner(new FileReader(fileName));;
        Vector fileVector = new Vector<String>();
        
        String line;
        while(fileScanner.hasNextLine()){
            line = fileScanner.nextLine();
            fileVector.add(line);
        }
        return fileVector;
    }
    
}
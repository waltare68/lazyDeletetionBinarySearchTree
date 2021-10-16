package LazyBinarySearchTree;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class LazyBinarySearchTreeTest {

	private static final String INSERT = "Insert:";
	   private static final String DELETE = "Delete:";
	   private static final String PRINT_TREE = "PrintTree";
	   private static final String FIND_MIN = "FindMin";
	   private static final String FIND_MAX = "FindMax";
	   private static final String HEIGHT = "Height";
	   private static final String SIZE = "Size";
	   private static final String CONTAINS = "Contains:";

	   /**
	   * Converts a boolean to camel-cased string
	   */
	   static String camelCase(boolean b) {
	       String result = String.valueOf(b);
	       return Character.toUpperCase(result.charAt(0)) + result.substring(1);
	   }

	   public static void main(String[] args) {

	       // Check whether proper arguments are provided
	       if (args.length == 2) {

	           // Scanner to read from the file
	           Scanner inFile = null;

	           // Writer to write to output file
	           PrintWriter pw = null;

	           // Create output file
	           File outFile = new File(args[1]);

	           try {
	               // Open file for reading
	               inFile = new Scanner(new File(args[0]));

	               // Create file if it does not exists
	               if (!outFile.exists())
	                   outFile.createNewFile();

	               // Open file for writing
	               pw = new PrintWriter(outFile);

	               // Create LazyBinarySearchTree object
	               LazyBinarySearchTree tree = new LazyBinarySearchTree();

	               // Read file
	               while (inFile.hasNextLine()) {

	                   // Read line
	                   String line = inFile.nextLine().trim();

	                   // Check operations
	                   if (line.indexOf(INSERT) == 0) { // INSERT

	                       // Get key
	                       int key = Integer.parseInt(line.substring(line.indexOf(INSERT) + INSERT.length()));
	                       try {
	                           pw.println(camelCase(tree.insert(key)));
	                       } catch (IllegalArgumentException iae) {
	                           pw.println(iae.getMessage());
	                       }

	                   } else if (line.indexOf(DELETE) == 0) { // DELETE

	                       // Get key
	                       int key = Integer.parseInt(line.substring(line.indexOf(DELETE) + DELETE.length()));
	                       try {
	                           pw.println(camelCase(tree.delete(key)));
	                       } catch (IllegalArgumentException iae) {
	                           pw.println(iae.getMessage());
	                       }

	                   } else if (line.indexOf(PRINT_TREE) == 0) { // PRINT_TREE
	                       pw.println(tree);

	                   } else if (line.indexOf(FIND_MIN) == 0) { // FIND_MIN
	                       pw.println(tree.findMin());

	                   } else if (line.indexOf(FIND_MAX) == 0) { // FIND_MAX
	                       pw.println(tree.findMax());

	                   } else if (line.indexOf(HEIGHT) == 0) { // HEIGHT
	                       pw.println(tree.height());

	                   } else if (line.indexOf(SIZE) == 0) { // SIZE
	                       pw.println(tree.size());

	                   } else if (line.indexOf(CONTAINS) == 0) { // CONTAINS

	                       // Get key
	                       int key = Integer.parseInt(line.substring(line.indexOf(CONTAINS) + CONTAINS.length()));
	                       try {
	                           pw.println(camelCase(tree.contains(key)));
	                       } catch (IllegalArgumentException iae) {
	                           pw.println(iae.getMessage());
	                       }

	                   } else
	                       pw.println("Error in Line: " + line);

	               }

	               // Close in and out file
	               inFile.close();
	               pw.close();
	               System.out.println("Output written to file: " + args[1]);

	           } catch (FileNotFoundException fnfe) {
	               System.out.println(fnfe.getMessage());
	           } catch (IOException ioe) {
	               ioe.printStackTrace();
	           }

	       } else
	           System.out.println("Usage: java LazyBinarySearchTreeTest <inputFileName> <OutputFileName>");
	   }
}

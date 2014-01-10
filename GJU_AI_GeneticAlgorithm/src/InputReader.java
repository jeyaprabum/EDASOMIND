import java.io.*;
import java.util.HashMap;

/**
 * @author Christina B. Class
 * @version 1.0 (CS 451, Fall 2013/14)
 *
 */
public class InputReader
{

    
    /**
     * Reads an input file and generates a cnf from the file.
     * The file is of the following format:
     * 1st line: the names of the variables, separated by a blank
     * following lines: one clause per line
     * the clauses use the variable names defined in the first line, "not" or "NOT", as well as "or" or "OR"
     * @param fileName input file
     * @return the generated cnf object
     * @throws Exception if the file does not exist or there are any problems with its formatting or content
     */
    static public CNF readInputFile(String fileName) throws Exception
    {
        String[] names;
        CNF cnf;
        String input;

        BufferedReader in = new BufferedReader(new FileReader (fileName));
        // read the names of the variables
        input = in.readLine();
        names = input.split("\\s");
        HashMap<String,Integer> indexMap = new HashMap<String,Integer>();
        for (int i = 0; i < names.length; i++) {
            indexMap.put(names[i],i);
        }
        cnf = new CNF(names);
        // read the clauses and add them to cnf
        int n = names.length;
        input = in.readLine();
        while(input != null) {
            int[] terms = new int[n];
            int[] negatedTerms = new int[n];
            String[] tokens = input.split("\\s");
            boolean isNot = false;
            for (int i = 0; i < tokens.length; i++) {
                if(tokens[i].equals("OR") || tokens[i].equals("or")){
                    // ignore
                } else if (tokens[i].equals("NOT") || tokens[i].equals("not")){
                    isNot=true;
                } else if(isNot) {
                    negatedTerms[indexMap.get(tokens[i])]=1;
                    isNot = false;
                } else {
                    terms[indexMap.get(tokens[i])]=1;
                }
            }
            cnf.addClause(terms, negatedTerms);
            input = in.readLine();
        }
        in.close();
        return cnf;
    }
}

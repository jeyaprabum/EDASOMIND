
import java.util.ArrayList;

/**
 * This class represents a boolean expression in cnf
 * 
 * @author Christina B. Class
 * @version 1.0 (CS 451, Fall 2013/14)
 *
 */

public class CNF
{
    private ArrayList<Clause> clauses;
    private String[] names;
    
    /**
     * @param names specifies a list of names for the variables used in the cnf
     */
    public CNF(String[] names)
    {
        clauses = new ArrayList<Clause>();
        this.names = names;
    }
    
    
    /**
     * This method adds a clause to the cnf
     * 
     * @param terms  array with 1 for each variable that is in the clause, 0 otherwise
     * @param negatedTerms  array with 1 for each variable that is in its negated form (not x) in the clause, 0 otherwise
     */
    public void addClause(int [] terms, int [] negatedTerms)
    {
        clauses.add(new Clause(terms, negatedTerms));
    }
    
    /**
     * @param truthValues the truth values for all variables used for the evaluation 
     * @return the number of clauses that are evaluated to true
     */
    public int countTrueClauses(boolean[] truthValues)
    {
    	int counter = 0;
    	for (Clause c : clauses) {
    		if(c.evaluateClause(truthValues)) {
    			counter++;
    		}
    	}
    	return counter;
    }
    
    /**
     * @param truthValues the truth values for all variables used for the evaluation 
     * @return true if the cnf is evaluated to true for the specified truth values
     */
    public boolean evaluateCNF(boolean[] truthValues)
    {
        boolean result = true;
        for (Clause c : clauses) {
            result = result && c.evaluateClause(truthValues);
        }
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        String resultString;
        if(clauses.size() == 0) {
            return null;
        }
        resultString = clauses.get(0).asString(names);
        for (int i=1; i<clauses.size(); i++) {
            resultString += " and " + clauses.get(i).asString(names);
        }
        return resultString;
    }
    
    /**
     * @return an array with the variable names
     */
    public String[] getNames()
    {
        return names;
    }
    
    /**
     * @return the number of variables in the cnf
     */
    public int getLength()
    {
    	return names.length;
    }
 
    /**
     * @return the number of clauses in the cnf
     */
    public int getNbOfClauses()
    {
    	return clauses.size();
    }
    
    /**
     * @return string representation of the array of variable names
     */
    public String namesAsString()
    {
    	String result="";
    	for (String s : names) {
    		result += s + " ";
    	}
    	return result;
    }
}

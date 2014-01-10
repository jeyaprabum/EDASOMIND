
/**
 * class Clause
 * 
 * defines one clause (i.e. a set of boolean variables and negated boolean variables that are linked using or)
 * 
 * @author Christina B. Class
 * @version 1.0 (CS 451 Fall 2013/14)
 */


public class Clause
{
    private int[] terms;
    private int[] negatedTerms;
 
    
    /**
     * @param n is the number of boolean variables used in the (complete) cnf
     */
    public Clause(int n)
    {
        terms = new int[n];
        negatedTerms = new int[n];
    }    
    
    /**
     * @param terms 1 for each term that is in this specific clause, for a term that is not in this clause 0 
     * @param negatedTerms 1 for each term that is in this specific clause, for a term that is not in this clause 0
     */
    public Clause(int[] terms, int[] negatedTerms)
    {
        this.terms=terms;
        this.negatedTerms=negatedTerms;
    }
    
    /**
     * @param i the term at index i is set to 1
     */
    public void addTerm(int i)
    {
        terms[i] = 1;
    }
    
    /**
     * @param i the negated term at index i is set to 1
     */
    public void addNegatedTerm(int i)
    {
        negatedTerms[i] = 1;
    }
    
    /**
     * @param truthValues boolean values for all variables
     * @return the evaluated value (true / false) for the truth values specified
     */
    public boolean evaluateClause(boolean[] truthValues)
    {
        boolean result = false;
        for (int i=0; i<terms.length; i++) {
            if(terms[i] == 1) {
                result = result ||  truthValues[i];
            }
        }
        for (int i=0; i<negatedTerms.length; i++) {
            if(negatedTerms[i] == 1) {
                result = result ||  ! truthValues[i];
            }
        }
        return result;
    }
    
    /**
     * for the string representation the specified names for the variables is used
     * @param names specifies the names for the variables
     * @return string representation of the clause 
     */
    public String asString(String[] names)
    {
        String resultString=null;
        for(int i = 0; i<terms.length;i++) {
            if(terms[i] == 1) {
                if (resultString != null) {
                    resultString = resultString + " or " +names[i];
                } else {
                    resultString = "( " + names[i];
                }
            }
        }
        for(int i = 0; i<negatedTerms.length;i++) {
            if(negatedTerms[i] == 1) {
                if (resultString != null) {
                    resultString = resultString + " or not " +names[i];
                } else {
                    resultString = "(not " + names[i];
                }
            }
        } 
        resultString += " )";
        return resultString;
    }
}

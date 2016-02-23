package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * This class represents a mathematical function/expression.
 * <p>
 * The known operation of this class are as follows: sum (+), subtraction (-),
 * multiplication (*), division (/), power of (^).
 * 
 * <p>
 * The mathematical expression inserted must have at least one space between each
 * mathematical symbol. For example, 2x would be:
 * 2 * x
 * <p>
 * The priority of the mathematical operations are as follows (from highest to lowest):
 * ^ (powerof)
 * <p>
 * * / (multiplication and division)
 * <p>
 * + - (sum and subtraction)
 * <p>
 * This function class allows for the storage of a function of n-amount of variables. 
 * When the mathematical expression is inserted, the class automatically finds all
 * variables contained in the expression. Keep in mind it is not case sensitive, therefore
 * "ab" and "AB" are the same variable.
 * <p>
 * To parse the mathematical expression, this class uses the shunting-yard algorithm to
 * convert from an infix notation to an rpn expression. Then, it calculates the values of the
 * rpn expression.
 * <p>
 * Credit to Rosetta Code for an example implementation of both shunting-yard and rpnEval algorithms,
 * available at:<p>
 * RPNEval: http://rosettacode.org/wiki/Parsing/RPN_calculator_algorithm
 * <p>
 * Shunting-Yard: http://rosettacode.org/wiki/Parsing/Shunting-yard_algorithm
 * @author G11
 */
public class Function {
    
    /**
     * Expression of the function.
     * <p>
     * The function will have the following format: F(x)=expression;
     */
    private String expression;
    
    /**
     * The variables of this function.
     */
    private String[] variables;
    
    
    /**
     * Creates an instance of {@link Function} with null parameters.
     */
    public Function()
    {
        expression="";
    }
    
    /**
     * Creates an instance of {@link Function} with the specified parameters.
     * @param exp (String) The expression of the function.
     */
    public Function(String exp)
    {
        setExpression(exp);
    }

    /**
     * Returns the expression of this function.
     * @return (String) The expression of the function.
     */
    public final String getExpression() {
        return expression;
    }

    /**
     * Sets the expression of this function.
     * @param expression (String) The new expression.
     */
    public final void setExpression(String expression) {
        this.expression = expression;
        setVariables(getVarsFromExpression(expression));
    }
    /**
     * Returns the variables housed in this function.
     * @return (String[]) An array populated with the variables of this function.
     */
    public String[] getVariables() {
        return variables;
    }

    /**
     * Sets the variables of this function.
     * @param variables (String[]) The array of variables of this function.
     */
    protected void setVariables(String[] variables) {
        this.variables = variables;
    }
    
    /**
     * Returns the value of this function for the given values of the variables.
     * @param varValues (Double[]) An array containing the value for each variable.
     * @return (Double) The value of the function.
     */
    public Double getFunctionValueFor(Double[] varValues)
    {
        String cleanExpr = replaceVarsWithValues(varValues);
        cleanExpr = shuntingYard(cleanExpr);
        return evalRPN(cleanExpr);
    }
    
    /**
     * Replaces a mathematical expression that has variables with the specified values for
     * those variables.
     * <p>
     * The values must correspond to the order of variables of this function. For example:
     * <p>
     * To compute f(x,y)= x*y; x=2;y=3, one would need to send the following array of values:
     * <p>
     * [0]=2.0;<p>
     * [1]=3.0;
     * @param varValues (Double[]) The values for the variables in the function.
     * @return (String) The new expression.
     */
    protected String replaceVarsWithValues(Double[] varValues)
    {
        if (varValues.length!=variables.length)
        {
            throw new IllegalArgumentException("Amount of variable values does "
                    + "not match the amount of variables present in the function!");
        }
        String result = "";
        boolean flag;
        String temp = expression.toLowerCase();
        String cache;
        int j;
        for (int i=0;i<temp.length();i++)
        {
            if (temp.charAt(i)>='a' && temp.charAt(i)<='z')
            {
                flag=false;
                for (j=i+1;j<temp.length() && !flag;j++)
                {
                    if (!(temp.charAt(j)>='a' && temp.charAt(j)<='z'))
                    {
                        flag=true;
                    }
                }
                cache=temp.substring(i, j).trim();
                flag=false;
                for (int k=0;k<variables.length && !flag;k++)
                {
                    if (cache.equals(variables[k]))
                    {
                        flag=true;
                        result+=varValues[k]+" ";
                    }
                }
                if (!flag) //No value was found
                {
                    throw new IllegalArgumentException(cache+" is not a variable present in the function.");
                }
                i=j-1;
            }
            else
            {
                result+=expression.charAt(i);
            }
        }
        return result;
    }
    
    /**
     * Returns the variables detected in the expression.
     * @param exp (String) The target expression.
     * @return (String[]) The variables detected in the expression.
     */
    protected String[] getVarsFromExpression(String exp)
    {
        ArrayList<String> result = new ArrayList();
        boolean flag;
        String temp = exp.toLowerCase();
        String cache;
        int j;
        for (int i=0;i<temp.length();i++)
        {
            if ('a'<=temp.charAt(i) && temp.charAt(i)<='z')
            {
                flag=false;
                for (j=i+1;j<temp.length() && !flag;j++)
                {
                    if (!('a'<=temp.charAt(j) && temp.charAt(j)<='z'))
                    {
                        flag=true;
                    }
                }
                cache=temp.substring(i, j).trim();
                if (!result.contains(cache))
                {
                    result.add(cache);
                    i=j-1;
                }
            }
        }
        return arrayListToStringArray(result);
    }
    
    /**
     * Converts an array list of strings into an array of strings.
     * @param arr (ArrayList&lt;String&gt;) The array list of strings to convert.
     * @return (String[]) The array of strings.
     */
    private static String[] arrayListToStringArray(ArrayList<String> arr)
    {
        String[] result = new String[arr.size()];
        for (int i=0;i<arr.size();i++)
        {
            result[i]=arr.get(i);
        }
        return result;
    }
    
    /**
     * Parses a mathematical formula to the corresponding postfix notation using
     * the shunting-yard algorithm. 
     * <p>
     * All elements in formula must be seperated with a space.
     * Credit: Rosetta Code.
     * @param infix (String) The formula/infix/mathematical expression to parse.
     * @return (String) The mathematical expression in postfix notation.
     */
    protected static final String shuntingYard(String infix)
    {
        final String ops = "-+/*^";
        StringBuilder sb = new StringBuilder();
        Deque<Integer> s = new LinkedList();
 
        for (String token : infix.split("\\s")) {
            if (token.isEmpty())
                continue;
            char c = token.charAt(0);
            int idx = ops.indexOf(c);
 
            // check for operator
            if (idx != -1) {
                if (s.isEmpty())
                    s.push(idx);
 
                else {
                    while (!s.isEmpty()) {
                        int prec2 = s.peek() / 2;
                        int prec1 = idx / 2;
                        if (prec2 > prec1 || (prec2 == prec1 && c != '^'))
                            sb.append(ops.charAt(s.pop())).append(' ');
                        else break;
                    }
                    s.push(idx);
                }
            } 
            else if (c == '(') {
                s.push(-2); // -2 stands for '('
            } 
            else if (c == ')') {
                // until '(' on stack, pop operators.
                while (s.peek() != -2)
                    sb.append(ops.charAt(s.pop())).append(' ');
                s.pop();
            }
            else {
                sb.append(token).append(' ');
            }
        }
        while (!s.isEmpty())
            sb.append(ops.charAt(s.pop())).append(' ');
        return sb.toString();
    }
    
    /**
     * An adaptation of rosetta's RPN evaluation method.
     * @param postfix (String) The postfix expression to calculate.
     * @return (Double) The result of the expression.
     */
    protected static final Double evalRPN(String postfix)
    {
	LinkedList<Double> stack = new LinkedList();
	for(String token:postfix.split("\\s"))
        {
            Double tokenNum = null;
            try
            {
                tokenNum = Double.parseDouble(token);
            }
            catch(NumberFormatException e)
            {
                //It was not a number, so let's see what operation it was.
            }
            if(tokenNum != null)
            {
		stack.push(Double.parseDouble(token+""));
            }
            else 
            if(token.equals("*"))
            {
		double secondOperand = stack.pop();
		double firstOperand = stack.pop();
		stack.push(firstOperand * secondOperand);
            }
            else if(token.equals("/"))
            {
		double secondOperand = stack.pop();
		double firstOperand = stack.pop();
                stack.push(firstOperand / secondOperand);
            }
            else if(token.equals("-"))
            {
		double secondOperand = stack.pop();
		double firstOperand = stack.pop();
                stack.push(firstOperand - secondOperand);
            }
            else if(token.equals("+"))
            {
		double secondOperand = stack.pop();
		double firstOperand = stack.pop();
                stack.push(firstOperand + secondOperand);
            }
            else if(token.equals("^"))
            {
		double secondOperand = stack.pop();
		double firstOperand = stack.pop();
                stack.push(Math.pow(firstOperand, secondOperand));
            }
            else
            {
                throw new IllegalArgumentException("Unrecognized mathematical operation: "+token);
            }
	}
        return stack.pop();
    }
    
    @Override
    public boolean equals(Object other)
    {
        boolean result = other!=null && getClass().equals(other.getClass());
        if (result)
        {
            Function f = (Function) other;
            result = (this==other) || (expression.equals(f.expression));
        }
        return result;
    }
    
    @Override
    public String toString()
    {
        return "f"+Arrays.toString(variables)+"="+expression;
    }
}

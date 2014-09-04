package com.rss245.sample;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.rss245.sample.exceptions.NegativesNotAllowedAppException;


/** catch
 * 
 * @author Robert Silver
 * String Calculator class - provides add, subtract and multiply operations on inputParameters 
 * a list of positive integers. Should any integers in the parameter list delimited by '\n' and/or ',' be negative 
 * an exception will be thrown and caught.
 * Custom delimiters can be specified via the inputString prefix: "//[delimiter]\n[inputParameters]
 * 
 * try
 */

public class StringCalculator  {
	private final String DELIMITER_PROMPT = "//";

	public enum OPERATION {
		ADD, SUBTRACT, MULTIPLY
	};
	
	private String inputParameters;
	private int result = 0;
	private StringTokenizer stringTokenizer = null;
	
/** main method for debugging and verifying exceptions
 * 
 * @param args
 * @throws NegativesNotAllowedAppException
 */
	public static void main(String args[]) throws NegativesNotAllowedAppException {
		
		System.out.println("add(//;<NL>1;2;3;4;5;6;7)="+ new StringCalculator().add("//;\n1;2;3;4;5;6;7"));
		System.out.println("add(1<NL>7,7)=" + new StringCalculator().add("1\n7,7"));
		System.out.println("add//;<NL>2;3)="+ new StringCalculator().multiply("//;\n2;3"));
		System.out.println("multiply(-2<NL>3)=" + new StringCalculator().multiply("-2\n3"));     //exception
		System.out.println("subtract(10,2,4)="+ new StringCalculator().subtract("10,2,4"));

		//throws exception
		System.out.println("processParameters(-2,9,-7,-8)=");
		System.out.println(new StringCalculator().processParameters(OPERATION.ADD,"-2,9,-7,-8",","));
	}	
	

	/**
	 * 
	 *  The purpose of this method is to use the fixed format which places the '\n' character
	 *  just before a string of numbers separated by single character delimiters. 
	 * 
	 * @param inputString
	 * @return String pointing to the InputParameters
	 */
	private String skipToNextNumber(String inputString) {
		for (int index = 0; index < inputString.length(); index++) {
			Character scanCharacter = inputString.charAt(index);
			if (scanCharacter.equals('\n'))  {
				return inputString.substring(index+1);
			}

		}
		return inputString;
	}
	
	/**
	 * The purpose of this method is to return a string list of negative parameters that
	 * caused the NegativesNotAllowedAppException e.g [-2, -4, -7]
	 * 
	 * @param inputParameters
	 * @param delimiter
	 * @return 
	 */
	private String list_negatives(String inputParameters,String delimiter)
	{
		String message_content="- Negative values flagged: ";
		List<Integer> listOfNegatives=new ArrayList<Integer>();
		stringTokenizer = new StringTokenizer(inputParameters, delimiter);
		while (stringTokenizer.hasMoreTokens()) {
			String t = stringTokenizer.nextToken();
			Integer intParameter = new Integer(t);
			if (intParameter<0) listOfNegatives.add(intParameter);
		}
		message_content+=listOfNegatives.toString();
		return message_content;	
	}

	
	/**
	 * Processes the inputParameters using the operation specified into an appropriate result
	 * 
	 * 
	 * @param operator
	 * @param inputParameters
	 * @param delimiter
	 * @return result of the operation applied either: ADD, SUBTRACT, or MULTIPLY using inputParameters
     *
	 * @throws  a checked NegativesNotAllowedAppException when negative parameters are found
	 * When producing the exception the negative input parameters are passed to the exception
	 * constructor to display the specific negative values that caused the exception
     *
	 */

	public Integer processParameters(OPERATION operator, String inputParameters,
			String delimiter) throws NegativesNotAllowedAppException  {
		int result = 0;
		stringTokenizer = new StringTokenizer(inputParameters, delimiter);
		int parameterCounter = 0;
		while (stringTokenizer.hasMoreTokens()) {
			String t = stringTokenizer.nextToken();
			Integer intParameter = new Integer(t);
			if (intParameter<0)	throw new NegativesNotAllowedAppException(list_negatives(inputParameters,delimiter));
			switch (operator) {
			case ADD:
				result = result + intParameter;
				break;

			case SUBTRACT:
				if (parameterCounter == 0)
					result = intParameter;
				else
					result = result - intParameter;
				break;
			case MULTIPLY:
				if (parameterCounter == 0)
					result = intParameter;
				else
					result = result * intParameter;
				break;

			}
			parameterCounter++;
			}
			
		return result;
	}
	
	
	/**
	 * add method takes the inputString passed and adds up all the positive parameters contained in
	 * the inputString e.g "4,3,2"=9
	 * 
	 * default delimiters are "\n" and ","
	 * A Custom delimiter may be specified by prefixing the ParameteSringstring, "//[delimiter]\n"
	 * [delimiter] is a string of one character.
	 * 
	 * @param inputString
	 * @return  result
	 *  catches Exception - and lists the negative parameters from the parameterString
	 */
	public int add(String inputString)  {
		result = 0;
		try {
		if (inputString.startsWith(DELIMITER_PROMPT)) {
			String delimiter = Character.toString(inputString
					.charAt(DELIMITER_PROMPT.length()));
			String inputParameters = skipToNextNumber(inputString); 
			result = processParameters(OPERATION.ADD, inputParameters,delimiter);
		} else {
			inputParameters = inputString;			
			result = processParameters(OPERATION.ADD, inputParameters, ",\n");			
		}
		} catch (NegativesNotAllowedAppException negativeNotllowedException){
			System.out.println("add: NegativesNotAllowedAppException thrown and caught "+negativeNotllowedException.getMessage());
		}
		return result;
	}

	/**
	 * subtract method takes the inputString passed and subtracts up all the positive parameters contained in
	 * the inputString. e.g  "5,3,1" result is 1
	 * default delimiters are "\n" and "," 
     * A Custom delimiter may be specified by prefixing the ParameteSringstring, "//[delimiter]\n"
     * [delimiter] is a string of one character.
	 * 
	 * @param inputString
	 * @return  result
	 *  catches Exception - and lists the negative parameters from the parameterString
	 */	

	public int subtract(String inputString)  {
		result = 0;
		try {
		if (inputString.startsWith(DELIMITER_PROMPT)) {
			String delimiter = Character.toString(inputString
					.charAt(DELIMITER_PROMPT.length()));
			String inputParameters = skipToNextNumber(inputString);
			result = processParameters(OPERATION.SUBTRACT, inputParameters,
					delimiter);
		} else {
			inputParameters = inputString;			
		    result = processParameters(OPERATION.SUBTRACT, inputParameters, ",\n");
		}
		} catch (NegativesNotAllowedAppException  negativeNotllowedException){
			System.out.println("multiply: NegativesNotAllowedAppException thrown and caught "+negativeNotllowedException.getMessage());
		}
		return result;
	}

	/**
	 * multiply method takes the inputString passed and multiplies all the positive 
	 * parameters contained in the inputString e.g 2,3,5 = 30
	 * default delimiters are "\n" and "," 
     * A Custom delimiter may be specified by prefixing the ParameteSringstring, "//[delimiter]\n"
     * [delimiter] is a string of one character.
	 * 
	 * @param inputString
	 * @return  result
	 *  catches Exception - and lists the negative parameters from the parameterString
	 */	

	public int multiply(String inputString)   {
		result = 0;
		try {
		if (inputString.startsWith(DELIMITER_PROMPT)) {
			String delimiter = Character.toString(inputString
					.charAt(DELIMITER_PROMPT.length()));
			String inputParameters = skipToNextNumber(inputString);
			result = processParameters(OPERATION.MULTIPLY, inputParameters,
					delimiter);
		} else {
			inputParameters = inputString;
			result = processParameters(OPERATION.MULTIPLY, inputParameters, ",\n");			
		}
		} catch(NegativesNotAllowedAppException negativeNotllowedException){
			System.out.println("multiply: NegativesNotAllowedAppException thrown and caught "+negativeNotllowedException.getMessage());
		}
		return result;
	}

}

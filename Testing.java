import java.util.Arrays;

public class Testing 
{

	public static void main(String[] args) 
	{
		String remainder = "cunt nugget";
		String [] wordsInLine = remainder.split("(?!^)");
		System.out.println(Arrays.toString(wordsInLine));
		System.out.println(wordsInLine.length);
	}

}

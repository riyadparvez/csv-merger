import java.util.Arrays;


public class Main
{
	public static void main(String[] args)
	{
		String outputFilePath = args[0];
		String[] inputFilePaths = Arrays.copyOfRange(args, 1, args.length-1);
		
		CombineCSV combineCSV = new CombineCSV(inputFilePaths, outputFilePath);
		
		combineCSV.combineRows();
		combineCSV.close();
	}
}
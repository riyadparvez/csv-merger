import java.util.List;
import java.util.ArrayList;


public class CombineCSV
{
	private String[] readFilePaths;
	private String writeFilePath;

	private CSVFileReader[] readers;
	private CSVFileWriter writer;


	public CombineCSV(String[] readFilePaths, String writeFilePath)
	{
		this.readFilePaths = readFilePaths;
		this.writeFilePath = writeFilePath;

		readers = new CSVFileReader[readFilePaths.length];

		try
		{
			for(int i=0; i<this.readFilePaths.length; i++)
			{
				readers[i] = new CSVFileReader(this.readFilePaths[i]);
			}

			writer = new CSVFileWriter(this.writeFilePath);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}


	public void writeCSVHeader(String[] header)
	{
		writer.write(header);
	}


	private String[] join(String[]... arrays)
	{
		int size = 0;

		for (String[] array : arrays)
		{
			size += array.length;
		}

		java.util.List<String> list = new java.util.ArrayList<String>(size);

		for (String[] array : arrays)
		{
			list.addAll(java.util.Arrays.asList(array));
		}

		return (String[])list.toArray(new String[size]);
	}


	public void combineRows()
	{
		ArrayList<ArrayList<String[]>> allRows = new ArrayList<ArrayList<String[]>>(readers.length);
		int currentLength = 0;

		try
		{
			allRows.add(new ArrayList<String[]>(readers[0].readAll()));
			int previousLength = allRows.get(0).size();

			for(int i=1; i<readers.length; i++)
			{
				allRows.add(new ArrayList<String[]>(readers[i].readAll()));

				currentLength = allRows.get(i).size();

				if(currentLength != previousLength)
				{
					throw new IllegalArgumentException();
				}

				previousLength = currentLength;
			}
		}
		catch(IllegalArgumentException e)
		{
			System.out.println("All file rows should be same");
			return ;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return ;
		}

		for(int i=0; i<currentLength-1; i++)
		{
			String[] currentRow = new String[]{};

			for(int j=0; j<readers.length; j++)
			{
				currentRow = join(currentRow, allRows.get(j).get(i));
			}
			
			writer.write(currentRow);
		}
	}


	public void close()
	{
		try
		{
			writer.close();

			for(int i=0; i<readers.length; i++)
			{
				readers[i].close();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
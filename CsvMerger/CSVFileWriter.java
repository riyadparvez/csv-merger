import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.StandardOpenOption;
import java.nio.charset.Charset;
import au.com.bytecode.opencsv.CSVWriter;


public class CSVFileWriter
{
	private final Path filePath;
	private CSVWriter writer;


	public CSVFileWriter(String filePath)
	{
		this.filePath = FileSystems.getDefault().getPath(filePath);
		Charset charset = Charset.forName("US-ASCII");
		
		try
		{
			BufferedWriter bufferdWriter = Files.newBufferedWriter(this.filePath, charset, StandardOpenOption.CREATE);
			writer = new CSVWriter(bufferdWriter);
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}


	public void write(String[] row)
	{
		writer.writeNext(row);
	}


	public void close()
	{
		try
		{
			writer.flush();
			writer.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
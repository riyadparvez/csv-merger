csv-merger
==========

Merge many same type of data holding csv files into one csv file. All the input csv files should have same number of columns, otherwise there will be an exception.

Input should be given 
`java Main outputFilePath inputFilePath_1 inputFilePath_2 inputFilePath_3 ... inputFilePath_n`
upto n input files.

Otherwise you can use `CombineCSV` class. It takes an array of input csv file paths and output file path. Create class like this
`String outputFilePath;
String[] inputFilePaths;
CombineCSV combineCSV = new CombineCSV(inputFilePaths, outputFilePath);`
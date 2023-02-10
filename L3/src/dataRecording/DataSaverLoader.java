package dataRecording;

import pacman.entries.pacman.ID3DataTuple;
import pacman.game.util.*;

/**
 * This class uses the IO class in the PacMan framework to do the actual saving/loading of
 * training data.
 * @author andershh
 *
 */
public class DataSaverLoader {
	
	private static String FileName = "trainingData.txt";
	
	public static void SavePacManData(DataTuple data)
	{
		IO.saveFile(FileName, data.getSaveString(), true);
	}
	
	public static DataTuple[] LoadPacManData()
	{
		String data = IO.loadFile(FileName);
		String[] dataLine = data.split("\n");
		DataTuple[] dataTuples = new DataTuple[dataLine.length];
		
		for(int i = 0; i < dataLine.length; i++)
		{
			dataTuples[i] = new DataTuple(dataLine[i]);
		}
		
		return dataTuples;
	}

	public static ID3DataTuple[] ID3LoadPacManData()
	{
		String data = IO.loadFile(FileName);
		String[] dataLine = data.split("\n");
		ID3DataTuple[] dataTuples = new ID3DataTuple[dataLine.length];

		for(int i = 0; i < dataLine.length; i++)
		{
			dataTuples[i] = new ID3DataTuple(dataLine[i]);
		}

		return dataTuples;
	}
}

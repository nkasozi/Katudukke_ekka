package Storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import Mementos.CareTaker;
import Mementos.Memento;
import Singletons.Singleton;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;

public class DataStoreHandler
{
	
	// PATH TO THE XML FILE USED FOR SAVING STATE
	private static final String	PATH_TO_XML_FILE	= Singleton.getResourcesPath() + "katuduke_ekka.xml";
	
	// PATH TO JSON FILE USED TO SAVE GAME STATE
	private static final String	PATH_TO_JSON_FILE	= Singleton.getResourcesPath() + "katuduke_ekka.json";
	
	public DataStoreHandler()
	{
	}
	
	public void StoreWin()
	{
		try
		{
		}
		catch (Exception e)
		{
		}
	}
	
	public void StoreLoss()
	{
		try
		{
		}
		catch (Exception ex)
		{
		}
	}
	
	public void IncrementNumberOfGamesPlayed()
	{
	}
	
	public int GetNumberOfWins()
	{
		int wins = 0;
		
		try
		{
		}
		catch (Exception e)
		{
		}
		
		return wins;
	}
	
	public int GetNumberOfLosses()
	{
		int losses = 0;
		
		try
		{
		}
		catch (Exception e)
		{
		}
		
		return losses;
	}
	
	public int GetNumberOfGamesPlayed()
	{
		return 0;
	}
	
	public static void SaveStateToJSON()
	{
		try
		{
			Memento memento = CareTaker.GetMostRecentMemento();
			Gson gson = new Gson();
			
			// convert java object to JSON format,
			// and returned as JSON formatted string
			String json = gson.toJson(memento);
			
			// write converted json data to a file named "file.json"
			File file = new File(PATH_TO_JSON_FILE);
			
			if (file.exists())
			{
				file.delete();
				file.createNewFile();
			}
			
			FileWriter writer = new FileWriter(file);
			writer.write(json);
			writer.close();
			CareTaker.AddMemento(memento);
			
		}
		catch (IOException e)
		{
			Singleton.PrintOut(e.getMessage());
		}
	}
	
	public static Memento LoadStateFromJSON()
	{
		try
		{
			
			Gson gson = new Gson();
			
			BufferedReader br = new BufferedReader(new FileReader(PATH_TO_JSON_FILE));
			
			// convert the json string back to object
			Memento memento = gson.fromJson(br, Memento.class);
			
			return memento;
			
		}
		catch (IOException e)
		{
			Singleton.PrintOut(e.getMessage());
			return null;
		}
		
	}
	
	public static void SaveStateToXML()
	{
		try
		{
			Memento memento = CareTaker.GetMostRecentMemento();
			XStream xstream = new XStream();
			String xml = xstream.toXML(memento);
			
			File file = new File(PATH_TO_XML_FILE);
			
			if (file.exists())
			{
				file.delete();
				file.createNewFile();
			}
			
			// write converted json data to a file named "file.json"
			FileWriter writer = new FileWriter(file);
			writer.write(xml);
			writer.close();
			CareTaker.AddMemento(memento);
			
		}
		catch (Exception e)
		{
			Singleton.PrintOut(e.getMessage());
		}
	}
	
	public static Memento LoadStateFromXML()
	{
		try
		{
			XStream xstream = new XStream();
			
			BufferedReader br = new BufferedReader(new FileReader(PATH_TO_XML_FILE));
			String CurrentLine;
			String xml = "";
			while ((CurrentLine = br.readLine()) != null)
			{
				xml = xml + CurrentLine;
			}
			br.close();
			// convert the json string back to object
			Memento memento = (Memento) xstream.fromXML(xml);
			return memento;
		}
		catch (Exception e)
		{
			Singleton.PrintOut(e.getMessage());
			return null;
		}
	}
}

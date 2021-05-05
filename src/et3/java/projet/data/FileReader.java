package et3.java.projet.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileReader 
{
	public static void getDataFromCSVFile(String csvFilePath)
	{
        String line = "";
        String[] data = null;
        String separator = ";(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";
        
        //Document data
        Integer idBase;
        String typeEmplacement;
        String domanialite;
        String arrondissement;
        String complementAdresse;
        String adresse;
        Integer idEmplacement;
        String libelleFrancais;
        String genre;
        String espece;
        String varieteOuCultivar;
        Integer circonferenceEnCm;
        Integer hauteurEnM;
        String stadeDeveloppement;
        Boolean remarquable;
        Float[] geographicalPoint2D = new Float[2];
        
        try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(csvFilePath), StandardCharsets.ISO_8859_1)) 
        {
        	//Read the first line
        	line = bufferedReader.readLine();
        	
        	//Get data from the line
        	data = line.split(separator, -1);
        	
        	if(data.length != 17)
        	{
        		System.out.println("[FileReader] The file at " + csvFilePath + " does not contain the right number of columns.");
        		return;
        	}
        	
        	int i = 1;
        	
        	//Read the file line by line
            while ((line = bufferedReader.readLine()) != null)
            {
            	//Get data from the line
            	data = line.split(separator, -1);
            	
            	//Sort data
            	
            		//Get the base ID
	            	try
	        		{
	            		idBase = Integer.parseInt(data[0]);
	        		}
	        		catch (Exception exception)
	        		{
	        			idBase = null;
	        		}
            		
            		//Get the location type
            		typeEmplacement = data[1];
            		
            		//Get the domain
                	domanialite = data[2];
                
                	//Get the district
                	arrondissement = data[3];
                	
                	//Get the complementary address
                	complementAdresse = data[4];
                	
                	//data[5] is the number
                	
                	//Get the address
                	adresse = data[6];
                
                	//Get the location ID
                	try
	        		{
                		idEmplacement = Integer.parseInt(data[7]);
	        		}
	        		catch (Exception exception)
	        		{
	        			idEmplacement = null;
	        		}
                	
                	//Get the French name
                	libelleFrancais = data[8];
                	
                	//Get the genus
                	genre = data[9];
                	
                	//Get the specie
                	espece = data[10];
                	
                	//Get the variety
                	varieteOuCultivar = data[11];
                	
                	//Get the circumference (cm)
                	try
	        		{
                		circonferenceEnCm = Integer.parseInt(data[12]);
	        		}
	        		catch (Exception exception)
	        		{
	        			circonferenceEnCm = null;
	        		}
                	
                	//Get the height (m)
                	try
	        		{
                		hauteurEnM = Integer.parseInt(data[13]);
	        		}
	        		catch (Exception exception)
	        		{
	        			hauteurEnM = null;
	        		}
                	
                	//Get the development state
                	stadeDeveloppement = data[14];
                	
                	//Get whether the tree is remarquable or not
                	if(data[15].equals("OUI") || data[15].equals("oui"))
                	{
                		remarquable = true;
                	}
                	else
                	{
                		remarquable = false;
                	}
                	
                	//Get the geographical point
                	data = data[16].split(",", -1);
                	try
	        		{
                		geographicalPoint2D[0] = Float.parseFloat(data[0]);
	        		}
	        		catch (Exception exception)
	        		{
	        			geographicalPoint2D[0] = null;
	        		}
                	try
	        		{
                		geographicalPoint2D[1] = Float.parseFloat(data[1]);
	        		}
	        		catch (Exception exception)
	        		{
	        			geographicalPoint2D[1] = null;
	        		}
                
                //TODO Do something with data
                    
                System.out.println(
                		idBase + ";" +
                		typeEmplacement + ";" +
                		domanialite + ";" +
                		arrondissement + ";" +
                		complementAdresse + ";" +
                		adresse + ";" +
                		idEmplacement + ";" +
                		libelleFrancais + ";" +
                		genre + ";" +
                		espece + ";" +
                		varieteOuCultivar + ";" +
                		circonferenceEnCm + ";" +
                		hauteurEnM + ";" +
                		stadeDeveloppement + ";" +
                		remarquable + ";" +
                		"(" + geographicalPoint2D[0] + "," + geographicalPoint2D[1] + ")");
            }
        } 
        catch (IOException exception) 
        {
            System.err.println(exception);
        }
	}
}

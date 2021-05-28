package et3.java.projet.data;

import et3.java.projet.entities.trees.Arbre;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FileReader 
{
	public static ArrayList<Arbre> getDataFromCSVFile(String csvFilePath)
	{
		ArrayList<Arbre> arbres = new ArrayList<Arbre>();

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
        String idEmplacement;
        String libelleFrancais;
        String genre;
        String espece;
        String varieteOuCultivar;
        Integer circonferenceEnCm;
        Integer hauteurEnM;
        boolean estAdulte;
        Boolean remarquable;
        float[] geographicalPoint2D = new float[2];
        
        try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(csvFilePath), StandardCharsets.ISO_8859_1)) 
        {
        	//Read the first line
        	line = bufferedReader.readLine();
        	
        	//Get data from the line
        	data = line.split(separator, -1);
        	
        	if(data.length != 17)
        	{
        		System.out.println("[FileReader] The file at " + csvFilePath + " does not contain the right number of columns.");
        		throw new IOException();
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
					idEmplacement = data[7];
                	
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
                	if(data[14].equals("Jeune (arbre)") )
                		estAdulte = false;
                	else
                		estAdulte = true;

                	
                	//Get whether the tree is remarquable or not
                	if(data[15].equals("OUI") || data[15].equals("oui"))
                	{
                		System.out.println(idBase);
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
	        			geographicalPoint2D[0] = 0;
	        		}
                	try
	        		{
                		geographicalPoint2D[1] = Float.parseFloat(data[1]);
	        		}
	        		catch (Exception exception)
	        		{
	        			geographicalPoint2D[1] = 0;
	        		}
                
                //TODO Do something with data
				arbres.add( new Arbre(idBase, genre, espece, libelleFrancais, adresse, geographicalPoint2D, circonferenceEnCm, hauteurEnM, estAdulte, remarquable, null) );

                	/*
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
                		estAdulte + ";" +
                		remarquable + ";" +
                		"(" + geographicalPoint2D[0] + "," + geographicalPoint2D[1] + ")");*/
            }
        } 
        catch (IOException exception) 
        {
            System.err.println(exception);
        }

        return arbres;
	}
}

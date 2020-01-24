package pl.edu.pg.AnimalGarden;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

public class SaveToTxt implements Writable
{
    @Override
    public void SaveData(String dataToSave, String pathToSave)
    {

    }

    public String GetOwnersDataFromGarden(List<Owner> ownersList)
    {
        if(!(ownersList.isEmpty()))
        {
            String saveOwnerData = "";

            for (Owner owner : ownersList)
            {
                saveOwnerData += owner.GetDataToSave()+"\n";
            }
            return saveOwnerData;
        }
        else
        {
            return null;
        }
    }

    public String GetAnimalDataFromGarden(Map<Animal, int[]> animalMap)
    {
        if(!(animalMap.isEmpty()))
        {
            String saveAnimalData = "";

            for (Map.Entry<Animal,int[]> entry:animalMap.entrySet())
            {
                Animal animal = entry.getKey();
                int[] position = entry.getValue();

                saveAnimalData+=animal.GetDataToSave()+","+position[0]+","+position[1]+"\n";
            }
            return saveAnimalData;
        }
        else
        {
            return null;
        }
    }
}

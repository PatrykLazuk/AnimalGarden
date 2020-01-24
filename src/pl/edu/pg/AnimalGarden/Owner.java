package pl.edu.pg.AnimalGarden;

import java.util.ArrayList;
import java.util.List;

public class Owner
{
    private static int ownerCount = 0;

    private int OwnerId;
    private String FirstName;
    private String LastName;
    private Enums.Gender Gender;
    private int Age;
    private List<Animal> ListOfAnimals = new ArrayList<Animal>();

    public Owner(String firstName, String lastName, Enums.Gender gender, int age)
    {
        this.OwnerId = ++ownerCount;
        setFirstName(firstName);
        setLastName(lastName);
        setGender(gender);
        setAge(age);
    }
    public void addAnimalToOwner(Animal animal)
    {
        ListOfAnimals.add(animal);
    }

    public Animal getAnimalById(int animalId)
    {
        Animal returnAnimal;
        for (Animal animal:getListOfAnimals())
        {
            if(animal.getAnimalId()==animalId)
            {
                return animal;
            }
        }
        return null;
    }

    public String GetDataToSave()
    {
        var line = new ArrayList<String>();
        line.add(Integer.toString(getOwnerId()));
        line.add(getFirstName());
        line.add(getLastName());
        line.add(Integer.toString(getAge()));
        line.add(String.valueOf(getGender()));
        //if(!getListOfAnimals().isEmpty())
        //{
        //    for (Animal animal : getListOfAnimals())
        //    {
       //        line.add(animal.GetDataToSave());
         //   }
        //}
        String joinedLine = String.join(",",line);

        return joinedLine;
    }
    public String GetInfo()
    {
        String info = "";
        info+="\n---------------------------\n";
        info += "Właściciel\n";
        info += "Id: "+getOwnerId()+"\n";
        info += "Imię: " + getFirstName() + "\n";
        info += "Nazwisko: " + getLastName() + "\n";
        info += "Gender: " + getGender() + "\n";
        info += "Age: " + getAge() + "\n";

        if(!ListOfAnimals.isEmpty())
        {
            info += "Lista zwierzaków:\n";
            for (Animal animal:ListOfAnimals)
            {
                info+="\n###########\n";
                info += animal.GetInfo();
                info+="\n###########\n";
            }
        }
        info+="\n---------------------------\n";
        return info;
    }



    public void RemoveAnimalFromOwnerList(Animal animal)
    {
        ListOfAnimals.remove(animal);
    }

    //Gettery i settery
    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public Enums.Gender getGender() {
        return Gender;
    }

    public void setGender(Enums.Gender gender) {
        Gender = gender;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public List<Animal> getListOfAnimals() {
        return ListOfAnimals;
    }

    public int getOwnerId() {
        return OwnerId;
    }
}

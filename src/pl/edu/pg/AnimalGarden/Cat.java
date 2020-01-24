package pl.edu.pg.AnimalGarden;

import java.util.ArrayList;

public class Cat extends Animal
{
    private String Race;

    public Cat(String name, Enums.Gender gender, int age, pl.edu.pg.AnimalGarden.Owner owner)
    {
        super(owner,name, gender, age);
    }
    public Cat()
    {
        super();
    }

    @Override
    public String GetDataToSave()
    {
        var line = new ArrayList<String>();

        line.add(Integer.toString(getAnimalId()));
        line.add(getName());
        line.add(Integer.toString(getAge()));
        line.add(getRace());
        line.add(String.valueOf(getGender()));

        String joinedLine = String.join(",",line);

        return joinedLine;
    }

    @Override
    public String GetInfo() {
        String info = "";
        info += "Kot\n";
        info += "Id: "+getAnimalId()+"\n";
        info += "Imię: " + getName() + "\n";
        info += "Rasa: " + getRace() + "\n";
        info += "Płeć: " + getGender() + "\n";
        info += "Wiek: " + getAge() + "\n";

        if(getOwner()!=null)
            info += "Właściciel: " + getOwner().getFirstName() +" "+ getOwner().getLastName() + "\n";

        return info;
    }

    public String getRace()
    {
        return this.Race;
    }
    public void setRace(String race)
    {
        this.Race = race;
    }

}

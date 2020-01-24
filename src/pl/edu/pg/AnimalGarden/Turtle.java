package pl.edu.pg.AnimalGarden;

import java.util.ArrayList;

public class Turtle extends Animal
{
    private boolean Hidden;

    public Turtle(String name, Enums.Gender gender, int age, pl.edu.pg.AnimalGarden.Owner owner) {
        super(owner,name, gender, age);
        this.Hidden = true;
    }
    public Turtle()
    {
        super();
        this.Hidden = true;
    }

    @Override
    public String GetDataToSave()
    {
        var line = new ArrayList<String>();

        line.add(Integer.toString(getAnimalId()));
        line.add(getName());
        line.add(Integer.toString(getAge()));
        line.add(Boolean.toString(isHidden()));
        line.add(String.valueOf(getGender()));

        String joinedLine = String.join(",",line);

        return joinedLine;
    }
    @Override
    public String GetInfo() {
        String info = "";
        info += "Id: "+getAnimalId()+"\n";
        info += "Żółw\n";
        info += "Schowany w skorupie?: " + this.Hidden + "\n";
        info += "Imię: " + getName() + "\n";
        info += "Płeć: " + getGender() + "\n";
        info += "Wiek: " + getAge()+ "\n";

        if(getOwner()!=null)
            info += "Właściciel: " + getOwner().getFirstName()+" "+getOwner().getLastName() + "\n";
        return info;
    }


    public boolean isHidden() {
        return Hidden;
    }

    public void setHidden(boolean hidden) {
        Hidden = hidden;
    }
}

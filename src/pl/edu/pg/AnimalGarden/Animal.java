package pl.edu.pg.AnimalGarden;

public abstract class Animal
{
    private static int animalCount = 0;

    protected int AnimalId = 0;
    protected String Name;
    protected Enums.Gender Gender;
    protected int Age;
    protected Owner Owner;

    public Animal()
    {
        this.AnimalId = ++animalCount;
    }
    public Animal(Owner owner, String name, Enums.Gender gender, int age)
    {
        this.AnimalId = ++animalCount;
        this.Name = name;
        this.Gender = gender;
        this.Age = age;
        this.Owner = owner;
    }

    public abstract String GetDataToSave();
    public abstract String GetInfo();

    ///gettery i settery
    public int getAnimalId() {
        return AnimalId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
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

    public pl.edu.pg.AnimalGarden.Owner getOwner() {
        return Owner;
    }

    public void setOwner(pl.edu.pg.AnimalGarden.Owner owner) {
        Owner = owner;
    }
}

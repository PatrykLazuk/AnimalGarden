package pl.edu.pg.AnimalGarden;

import java.awt.geom.Area;
import java.util.*;

public class AnimalGarden
{
    private String GardenName;
    private int SizeX;
    private int SizeY;

    private List<Owner> ListOfOwnersInTheGarden = new ArrayList<>();
    private List<Animal> ListOfAnimalsInTheGarden = new ArrayList<>();
    private Map<Animal, int[]> PositionOfAnimals = new HashMap<>();


    public void MainMenu()
    {
        Scanner mainMenuScanner = new Scanner(System.in);
        System.out.println("Witaj w Ogrodzie dla zwierząt.");
        System.out.println("Stwórzmy ogród");
        if(SetUpGarden())
        {
            while(true)
            {
                System.out.println("MENU GLOWNE\n");
                System.out.println("Oto co możesz zrobić:\n");
                System.out.println("1. Dodaj właściciela\n2. Dodaj zwierzaka\n3. Nakarm żółwia\n4. Usuń właściciela\n5. Usuń zwierzaka\n6. Wyświetl listę wszystkich właścicieli\n7. Wyświetl wszystkie zwierzaki razem z ich położeniem\n" +
                        "8. Przemieść zwierzaka\n9. Zapisz stan ogrodu\n10. Wyjdź z programu\n");
                System.out.println("Twój wybór: ");
                int mainMenuChoice = mainMenuScanner.nextInt();
                switch (mainMenuChoice)
                {
                    case 1:
                        AddNewOwner();
                        break;
                    case 2:
                        if(!ListOfOwnersInTheGarden.isEmpty())
                            AddNewAnimal();
                        else
                            System.out.println("W Ogrodzie nie ma żadnych włascicieli!");
                        break;
                    case 3:
                        if(ViewAllTurtlesInTheGarden())
                        {
                            System.out.println("\nPodaj Id żółwia do nakarmienia:\n");
                            int choosenTurtleId = mainMenuScanner.nextInt();
                            Animal choosenTurtle = GetAnimalById(choosenTurtleId);
                            if(choosenTurtle instanceof Turtle)
                            {
                                ((Turtle)choosenTurtle).setHidden(false);
                                System.out.println("Żółw " + choosenTurtle.getName() + " nakarmiony! Może się teraz przemieszczać!");
                            }
                            else
                                System.out.println("Nie ma takiego żółwia!");
                        }
                        break;
                    case 4:
                        System.out.println("Usuniemy teraz jednego z właścicieli");
                        ViewAllOwnersInTheGarden();
                        if(!ListOfOwnersInTheGarden.isEmpty())
                        {
                            System.out.println("Podaj ID właściciela do usunięcia:");
                            int choosenOwnerId = mainMenuScanner.nextInt();
                            Owner choosenOwner = GetOwnerById(choosenOwnerId);
                            if(choosenOwner!=null)
                                RemoveOwnerFromTheGarden(choosenOwner);
                            else
                                System.out.println("Nie ma Właściciela o takim ID!");
                        }
                        break;
                    case 5:
                        System.out.println("Usuniemy teraz jednego ze zwierzaków");
                        ViewAllAnimalsInTheGarden();
                        if(!ListOfAnimalsInTheGarden.isEmpty())
                        {
                            System.out.println("Podaj ID zwierzaka do usunięcia:");
                            int choosenAnimalId = mainMenuScanner.nextInt();
                            Animal choosenAnimal = GetAnimalById(choosenAnimalId);
                            if(choosenAnimal!=null)
                                RemoveAnimalFromTheGarden(choosenAnimal);
                            else
                                System.out.println("Nie ma Zwierzaka o takim ID!");
                        }

                        break;
                    case 6:
                        ViewAllOwnersInTheGarden();
                        break;
                    case 7:
                        ViewAllAnimalsInTheGarden();
                        break;
                    case 8:
                        if(!ListOfAnimalsInTheGarden.isEmpty())
                        {
                            ViewAllAnimalsInTheGarden();
                            System.out.println("Podaj Id Zwierzaka, którego chcesz przemieścić");
                            int animalToMoveId = mainMenuScanner.nextInt();
                            var animalToMove = GetAnimalById(animalToMoveId);
                            if(animalToMove!=null)
                            {
                                System.out.println("Przemieszczamy: " + animalToMove.getName() + "\n");
                                System.out.println("W którą stronę chcesz przemieścić zwierzaka?\nGóra = W, Dół = S, Lewo = A, Prawo = D\n");
                                String direction = mainMenuScanner.next();
                                switch (direction)
                                {
                                    case "W":
                                    case "w":
                                        MoveAnimal(animalToMove, Enums.Directions.Up);
                                        break;
                                    case "S":
                                    case "s":
                                        MoveAnimal(animalToMove, Enums.Directions.Down);
                                        break;
                                    case "A":
                                    case "a":
                                        MoveAnimal(animalToMove, Enums.Directions.Left);
                                        break;
                                    case "D":
                                    case "d":
                                        MoveAnimal(animalToMove, Enums.Directions.Right);
                                        break;
                                    default:
                                        System.out.println("Nie ma takiej komendy!");
                                        break;
                                }
                            }
                            else
                                System.out.println("Nie ma takiego zwierzaka!");
                        }
                        break;
                    case 9:
                        SaveToTxt save = new SaveToTxt();
                        var costam = save.GetOwnersDataFromGarden(ListOfOwnersInTheGarden);
                        break;
                    case 10:
                        System.exit(0);
                        break;
                    default:
                        break;
                }
            }
        }


    }
    public Animal GetAnimalById(int animalId)
    {
        for (Animal animal:ListOfAnimalsInTheGarden)
        {
            if(animal.getAnimalId()==animalId)
            {
                return animal;
            }
        }
        return null;
    }
    public Owner GetOwnerById(int ownerId)
    {
        for (Owner owner:ListOfOwnersInTheGarden)
        {
            if(owner.getOwnerId()==ownerId)
            {
                return owner;
            }
        }
        return null;
    }
    public void AddAnimalToOwnerInGarden(Animal animal)
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nDodamy teraz tego zwierzaka do Właściciela\n");
        System.out.println("Oto lista właścicieli:\n");

        for (Owner owner:ListOfOwnersInTheGarden)
        {
            System.out.println("Id: "+ owner.getOwnerId());
            System.out.println(owner.getFirstName()+" "+owner.getLastName());
        }
        System.out.println("Podaj ID Właściciela, którego wybierasz: ");
        int choosenOwnerId = scanner.nextInt();

        var choosenOwner = GetOwnerById(choosenOwnerId);
        if (choosenOwner != null)
        {
            System.out.println("\nWybrano: "+choosenOwner.getFirstName()+" "+choosenOwner.getLastName()+"\n");

            choosenOwner.addAnimalToOwner(animal);
            animal.setOwner(choosenOwner);

            System.out.println("Zwierzak dodany!");
        }
        else
            System.out.println("Nie ma takiego Właściciela!");

    }
    public void RemoveAnimalFromTheGarden(Animal animal)
    {
        var owner = animal.getOwner();
        owner.RemoveAnimalFromOwnerList(animal);
        ListOfAnimalsInTheGarden.remove(animal);
        PositionOfAnimals.remove(animal);

    }
    public boolean SetUpGarden() {
        Scanner scanner = new Scanner(System.in);
        boolean gardenCreated = false;

        //System.out.println("Witaj w symulatorze Ogrodu zwierząt\n");
        System.out.println("Podaj nazwę swojego ogrodu: ");

        this.GardenName = scanner.next();

        System.out.println("Podaj wymiar boku ogrodu: \n");

        int size = scanner.nextInt();
        if(size>=0)
        {
            this.SizeX = size;
            this.SizeY = size;
            gardenCreated = true;
        }

        return gardenCreated;
    }

    public boolean AddNewOwner() {
        boolean newOwnerCreated = false;
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nWitaj w kreatorze dodawania nowego Właściciela!\n");

        System.out.println("Podaj imię nowego Właściciela: ");
        String ownerFirstName = scanner.next();

        System.out.println("Podaj nazwisko nowego Właściciela: ");
        String ownerLastName = scanner.next();


        Enums.Gender ownerGender = null;

        System.out.println("Podaj płeć nowego Właściciela: ");
        System.out.println("1. Kobieta\n2. Mężczyzna\n");

        int result = scanner.nextInt();

        if(result==1)
        {
            ownerGender = Enums.Gender.Female;
        }
        else if(result==2)
        {
            ownerGender = Enums.Gender.Male;
        }
        else
            System.out.println("Nie ma takiej opcji.\n");

        System.out.println("Podaj wiek nowego Właściciela: ");
        int ownerAge = scanner.nextInt();

        if(
                ownerFirstName!=null
                &&ownerLastName!=null
                &&ownerGender!=null
                &&ownerAge>0
        )
        {
            Owner newOwner = new Owner(ownerFirstName, ownerLastName, ownerGender, ownerAge);
            ListOfOwnersInTheGarden.add(newOwner);
            newOwnerCreated = true;
            System.out.println("\nWłaściciel "+newOwner.getFirstName()+" "+newOwner.getLastName()+ " utworzony!");
        }
        else
        {
            System.out.println("Podano niepoprawne dane, utworzenie Właściciela nie powiodło się.");
        }

        return newOwnerCreated;

    }
    public void RemoveOwnerFromTheGarden(Owner owner)
    {
        var animalsOfThisOWner = owner.getListOfAnimals();
        for (Animal animal:animalsOfThisOWner)
        {
            RemoveAnimalFromTheGarden(animal);
        }
        ListOfOwnersInTheGarden.remove(owner);
    }

    public void AddNewAnimal()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nWitaj w kreatorze dodawania nowego Zwierzaka!\n");
        System.out.println("Stworzyć możesz:\n1. Psa\n2. Kota\n3. Żółwia\n");
        System.out.println("Co wybierasz?\n");

        int animalChooseResult = scanner.nextInt();
        Animal animal;
        if(animalChooseResult==1)
        {
            animal = new Dog();

            System.out.println("Podaj rasę psa:\n");
            String animalRace = scanner.next();
            ((Dog)animal).setRace(animalRace);
        }
        else if(animalChooseResult==2)
        {
            animal = new Cat();

            System.out.println("Podaj rasę kota:\n");
            String animalRace = scanner.next();
            ((Cat)animal).setRace(animalRace);
        }
        else if(animalChooseResult==3)
        {
            animal = new Turtle();
        }
        else
        {
            animal = null;
        }
        ///////////////////
        if(animal!=null)
        {
            //imie
            System.out.println("Podaj imię:\n");
            String animalName = scanner.next();

            //plec
            System.out.println("Podaj płeć:\n");
            System.out.println("1. Samica\n2. Samiec\n");
            int genderResult = scanner.nextInt();
            Enums.Gender animalGender = null;
            switch (genderResult)
            {
                case 1:
                    animalGender = Enums.Gender.Female;
                    break;
                case 2:
                    animalGender = Enums.Gender.Male;
                    break;
                default:
                    System.out.println("Nie ma takiej opcji");
                    break;
            }
            //wiek
            System.out.println("Podaj wiek:\n");
            int animalAge =scanner.nextInt();

            animal.setName(animalName);
            animal.setGender(animalGender);
            animal.setAge(animalAge);

            if(!ListOfOwnersInTheGarden.isEmpty())
            {
                AddAnimalToOwnerInGarden(animal);
            }

            ListOfAnimalsInTheGarden.add(animal);

            //dodanie zwierzaka do ogrodu
            PositionOfAnimals.put(animal,new int[]{0,SizeY});
        }
    }
    
    public void ViewAllOwnersInTheGarden()
    {
        System.out.println("Oto lista właścicieli w ogrodzie: ");
        if(!ListOfOwnersInTheGarden.isEmpty())
        {
            for (Owner owner:ListOfOwnersInTheGarden)
            {
                String info = owner.GetInfo();
                System.out.println(info);
            }
        }
        else
        {
            System.out.println("Wygląda na to, że w ogrodzie nikogo nie ma!");
        }

    }
    public boolean ViewAllTurtlesInTheGarden()
    {
        boolean turtleResult = false;

        var listOfTurtles = new ArrayList<Animal>();

        System.out.println("\nOto lista żółwi w ogrodzie: \n");
        if(!PositionOfAnimals.isEmpty())
        {
            for (Map.Entry<Animal,int[]> entry:PositionOfAnimals.entrySet())
            {
                Animal animal = entry.getKey();
                if(animal instanceof Turtle)
                {
                    listOfTurtles.add(animal);
                    int[] position = entry.getValue();

                    String info = "";
                    info += "\n##########\n";
                    info += animal.GetInfo();
                    info += "Pozycja w ogrodzie1: " + "x: "+ position[0] +": y: "+position[1];
                    info += "\n##########\n";

                    System.out.println(info);
                }
            }
            if (!listOfTurtles.isEmpty())
            {
                turtleResult = true;
            }
            else
                System.out.println("Brak żółwi w ogrodzie!");
        }
        else
        {
            System.out.println("\nWygląda na to, że w ogrodzie nie ma zwierząt\n");
        }

        return turtleResult;
    }
    public void ViewAllAnimalsInTheGarden()
    {
        System.out.println("\nOto lista zwierzaków w ogrodzie: \n");
        if(!PositionOfAnimals.isEmpty())
        {
            for (Map.Entry<Animal,int[]> entry:PositionOfAnimals.entrySet())
            {
                Animal animal = entry.getKey();
                int[] position = entry.getValue();

                String info = "";
                info += "\n##########\n";
                info += animal.GetInfo();
                info += "Pozycja w ogrodzie1: " + "x: "+ position[0] +": y: "+position[1];
                info += "\n##########\n";

                System.out.println(info);
            }
        }
        else
        {
            System.out.println("\nWygląda na to, że w ogrodzie nie ma zwierząt\n");
        }
    }
    public void ViewPositionInfoOfAnimal(Animal animal)
    {
        int[] position = PositionOfAnimals.get(animal);
        String info = "";
        info += "\n##########\n";
        info += animal.GetInfo();
        info += "Pozycja w ogrodzie1: " + "x: "+ position[0] +": y: "+position[1];
        info += "\n##########\n";

        System.out.println(info);
    }

    public void MoveAnimal(Animal animal,Enums.Directions direction)
    {
        int[] position = PositionOfAnimals.get(animal);
        switch (direction)
        {
            case Up:
                if(position[1]<SizeY)
                {
                    if(animal instanceof Turtle)
                    {
                        if(((Turtle)animal).isHidden())
                        {
                            System.out.println("Żółw jest schowany w skorupie. Musisz go najpierw nakarmić");
                            break;
                        }
                        else if(!((Turtle)animal).isHidden())
                        {
                            ((Turtle)animal).setHidden(true);
                        }
                    }
                    position[1]++;

                    PositionOfAnimals.put(animal,position);
                    ViewPositionInfoOfAnimal(animal);
                }
                else
                {
                    System.out.println("Próbujesz wyprowadzić zwierzaka poza ogród!");
                }
                break;
            case Down:
                if(position[1]>0)
                {
                    if(animal instanceof Turtle)
                    {
                        if(((Turtle)animal).isHidden())
                        {
                            System.out.println("Żółw jest schowany w skorupie. Musisz go najpierw nakarmić");
                            break;
                        }
                        else if(!((Turtle)animal).isHidden())
                        {
                            ((Turtle)animal).setHidden(true);
                        }
                    }
                    position[1]--;

                    PositionOfAnimals.put(animal,position);
                    ViewPositionInfoOfAnimal(animal);
                }
                else
                {
                    System.out.println("Próbujesz wyprowadzić zwierzaka poza ogród!");
                }
                break;
            case Right:
                if(position[0]<SizeX)
                {
                    if(animal instanceof Turtle)
                    {
                        if(((Turtle)animal).isHidden())
                        {
                            System.out.println("Żółw jest schowany w skorupie. Musisz go najpierw nakarmić");
                            break;
                        }
                        else if(!((Turtle)animal).isHidden())
                        {
                            ((Turtle)animal).setHidden(true);
                        }
                    }
                    position[0]++;

                    PositionOfAnimals.put(animal,position);
                    ViewPositionInfoOfAnimal(animal);
                }
                else
                {
                    System.out.println("Próbujesz wyprowadzić zwierzaka poza ogród!");
                }
                break;
            case Left:
                if(position[0]>0)
                {
                    if(animal instanceof Turtle)
                    {
                        if(((Turtle)animal).isHidden())
                        {
                            System.out.println("Żółw jest schowany w skorupie. Musisz go najpierw nakarmić");
                            break;
                        }
                        else if(!((Turtle)animal).isHidden())
                        {
                            ((Turtle)animal).setHidden(true);
                        }
                    }
                    position[0]--;

                    PositionOfAnimals.put(animal,position);
                    ViewPositionInfoOfAnimal(animal);
                }
                else
                {
                    System.out.println("Próbujesz wyprowadzić zwierzaka poza ogród!");
                }
                break;
            default:
                System.out.println("Nie udało się przemieścić zwierzaka");
        }

    }


}

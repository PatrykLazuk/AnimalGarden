package pl.edu.pg.AnimalGarden;

import java.awt.event.KeyEvent;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        AnimalGarden animalGarden = new AnimalGarden();
        try{
            animalGarden.MainMenu();
        }
        catch (Exception e)
        {
            System.out.println("Błąd krytyczny:");
            System.out.println(e);
        }

    }

}

package GameShop;

import GameShop.java.models.*;
import GameShop.java.models.enums.ConsoleForm;

import java.util.Date;

public class ConsoleApp {
    public static void main(String[] args) {
        Customer me = new Customer("Dom", "Needham", "domneedham", "07791");
        Console console = new Console("Nintendo DS", ConsoleForm.HANDHELD, 15, 8, false);
        Console console1 = new Console("Xbox One", ConsoleForm.STANDARD, 15, 256, false);
        Game gta = new Game("Grand Theft Auto 5", console, 5, false);
        Game dota = new Game("Dota 2", console, 10, false);
        Game creed = new Game("Assassins Creed", console, 15, false);
        Game cod = new Game("Call of Duty", console, 5, false);
        Rental newRental = new Rental(new Date(), me);
        newRental.setConsole(console);
        // should not work as more than 1 console is not allowed
        newRental.setConsole(console);
        // should not work as more than 1 console is not allowed
        newRental.setConsole(console1);
        newRental.addGameToRental(gta);
        // should not work as the same game is not allowed
        newRental.addGameToRental(gta);
        newRental.addGameToRental(dota);
        newRental.addGameToRental(creed);
        // should not work as no more than 3 games allowed
        newRental.addGameToRental(cod);
        System.out.println(console);
        System.out.println(console1);
        System.out.println(gta);
        System.out.println(dota);
        System.out.println(creed);
        System.out.println(cod);
        System.out.println(newRental);
    }
}

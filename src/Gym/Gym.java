package Gym;


import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;

/**
 * Created by Filip Jakobsson
 * Date: 2020-10-09
 * Time: 14:58
 * Project: testProjJavaB
 * Copyright: MIT
 */
public class Gym {

    //En metod som returnerar namnet från en rad
    public String getName(String line) {

        String[] splitLine = line.split(",");
        String name = splitLine[1].trim();
        return name;
    }

    //En metod som returnerar personnumret från en rad
    public String getSocialNumber(String line) {

        String[] splitLine = line.split(",");
        String socialNumber = splitLine[0].trim();

        return socialNumber;
    }

    //En metod som returnerar datumet från en rad
    public LocalDate parseDateFromLine(String line) {

        String[] splitLine = line.split("-");

        int year = Integer.parseInt(splitLine[0]);
        int month = Integer.parseInt(splitLine[1]);
        int day = Integer.parseInt(splitLine[2]);

        LocalDate localDate = LocalDate.of(year, month, day);

        return localDate;
    }

    //mainprogram
    public void gymMainProgram() {

        //Mina variabler
        String fileGymKunder = "C:\\Users\\filip\\Textfiler Java\\Gym kunder.txt";
        Path writeFile = Paths.get("C:\\Users\\filip\\Textfiler Java\\NuvarandeMedlemDatum.txt");
        String name, socialNumber, date;
        LocalDate paymentDate;
        LocalDate presentDate = LocalDate.now();
        Period period;

        //Inmatning av namn eller personnummer
        String input = JOptionPane.showInputDialog("Skriv in ditt namn eller personnummer.");

        String tempLine;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileGymKunder));
             BufferedWriter writer = Files.newBufferedWriter(writeFile);) {

            //Här börjar en iteration som går igenom hela textfilen
            while ((tempLine = reader.readLine()) != null) {
                name = getName(tempLine);
                socialNumber = getSocialNumber(tempLine);
                date = reader.readLine();
                paymentDate = parseDateFromLine(date);


                if (input.trim().equals(name) || input.trim().equals(socialNumber)) {

                    period = Period.between(paymentDate, presentDate);

                    //Om personen är en nuvarande medlem
                    if (period.getYears() < 1) {
                        JOptionPane.showMessageDialog(
                                null, "Kunden är en nuvarande medlem. ");

                        writer.write("Namn: " + name + "\nPersonnummer: " + socialNumber + "\nDatum: " + presentDate);
                        writer.close();
                        System.exit(0);

                    }

                    //Om personen inte är en nuvarande medlem
                    if (period.getYears() > 1) {
                        JOptionPane.showMessageDialog(
                                null, "Kunden är inte en nuvarande medlem. ");
                        System.exit(0);
                    }

                }
            }

            //Personen finns inte registrerad
            JOptionPane.showMessageDialog(
                    null, "Personen finns inte i filen. ");
            System.exit(0);

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }

    }

    //Mainmetod
    public static void main(String[] args) {

        Gym gym = new Gym();
        gym.gymMainProgram();

    }

}

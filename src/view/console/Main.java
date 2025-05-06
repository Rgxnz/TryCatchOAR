package view.console;

import exceptions.InvalidCP;
import exceptions.InvalidName;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.validations.UserDataValidations;

public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        sc.useDelimiter("\n");

        String option;

        do {
            System.out.println("TESTER FUNCIONES UserDataValidations: ");
            System.out.println("1 - testCheckId");
            System.out.println("2 - testCheckFormatDate");
            System.out.println("3 - testCalculateAge");
            System.out.println("4 - testCheckPostalCode");
            System.out.println("5 - testIsNumeric");
            System.out.println("6 - testIsAlphabetic");
            System.out.println("7 - testCheckEmail");
            System.out.println("8 - testCheckName");
            System.out.println("9 - Exit");

            System.out.println("Option: ");
            option = sc.next();

            switch (option) {
                case "1":
                    testCheckId();
                    break;
                case "2":
                    testCheckFormatDate();
                    break;
                case "3":
                    testCalculateAge();
                    break;
                case "4":
                    testCheckPostalCode();
                    break;
                case "5":
                    testIsNumeric();
                    break;
                case "6":
                    testIsAlphabetic();
                    break;
                case "7":
                    testCheckEmail();
                    break;
                case "8":
                    testCheckName();
                    break;
                case "9":
                    System.out.println("Exit");
                    break;
                default:
                    System.out.println("Select valid option");
            }

        } while (!option.equals("9"));
    }

    static void testCheckId() {
        System.out.println("Enter your ID: ");
        String nif = sc.next();
        boolean nifOk = UserDataValidations.checkId(1, nif);
        if (nifOk) {
            System.out.println("The ID is correct");
        } else {
            System.out.println("Wrong ID");
        }
    }

    static void testCheckFormatDate() {
        System.out.println("Enter your date (dd/mm/yyyy): ");
        String date = sc.next();
        boolean dateOk = UserDataValidations.checkFormatDate(date);
        if (dateOk) {
            System.out.println("The date is correct");
        } else {
            System.out.println("Wrong date format");
        }
    }

    static void testCalculateAge() {
        System.out.println("Enter your birth date (dd/mm/yyyy): ");
        String birthDate = sc.next();
        int age = UserDataValidations.calculateAge(birthDate);
        if (age != -1) {
            System.out.println("Your age is: " + age);
        } else {
            System.out.println("Invalid birth date");
        }
    }

    static void testCheckPostalCode() {
        System.out.println("Enter your postal code: ");
        String postalCode = sc.next();

        //boolean postalCodeOk = UserDataValidations.checkPostalCode(postalCode);
//        if (postalCodeOk) {
//            System.out.println("The postal code is correct");
//        } else {
//            System.out.println("Wrong postal code format");
//        }
        try {
            UserDataValidations.checkPostalCode(postalCode);
            System.out.println("The postal code is correct");
        } catch (InvalidCP ex) {
            System.out.println("Wrong postal code format");
        }
    }

    static void testIsNumeric() {
        System.out.println("Enter a number: ");
        String number = sc.next();
        boolean isNumeric = UserDataValidations.isNumeric(number);
        if (isNumeric) {
            System.out.println("The string is numeric");
        } else {
            System.out.println("The string is not numeric");
        }
    }

    static void testIsAlphabetic() {
        System.out.println("Enter a string: ");
        String text = sc.next();
        boolean isAlphabetic = UserDataValidations.isAlphabetic(text);
        if (isAlphabetic) {
            System.out.println("The string contains only alphabetic characters");
        } else {
            System.out.println("The string contains non-alphabetic characters");
        }
    }

    static void testCheckEmail() {
        System.out.println("Enter your email: ");
        String email = sc.next();
        boolean emailOk = UserDataValidations.checkEmail(email);
        if (emailOk) {
            System.out.println("The email is correct");
        } else {
            System.out.println("Wrong email format");
        }
    }

    static void testCheckName() {
        System.out.println("Enter your name: ");
        String name = sc.next();
        //        boolean nameOk = UserDataValidations.checkName(name);
//        if (nameOk) {
//            System.out.println("The name is correct");
//        } else {
//            System.out.println("Wrong name format");
//        }
        try {
            UserDataValidations.checkName(name);
            System.out.println("The name is correct");
        } catch (InvalidName ex) {
            System.out.println("Wrong name format");

        }
    }
}

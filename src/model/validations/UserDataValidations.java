package model.validations;

import exceptions.*;
import java.util.Calendar;

public class UserDataValidations {

    /**
     * Valida el NIF según el tipo de documento.
     *
     * @param typeDoc El tipo de documento (1 para NIF).
     * @param id El NIF que se desea validar (debe estar en formato NIF si
     * typeDoc es 1).
     * @return true si el NIF es válido, false en caso contrario.
     */
    public static boolean checkId(int typeDoc, String id) {
        if (typeDoc != 1) {
            return false; // Si no es un NIF, devolvemos false
        }
        // Validar el formato del NIF (8 dígitos + letra)

        if (id.length() == 9 && id.matches("[0-9]{8}[A-Za-z]")) {
            return true;
        }
        return false;
    }

    /**
     * Valida el formato y la corrección de una fecha.
     *
     * @param date La fecha que se desea validar, en formato "dd/MM/yyyy".
     * @return true si la fecha es válida y el formato es correcto, false en
     * caso contrario.
     */
    public static boolean checkFormatDate(String date) {
        if (date == null || date.isEmpty()) {
            return false;
        }
        // Validamos el formato utilizando expresiones regulares
        if (!date.matches("\\d{2}/\\d{2}/\\d{4}")) {
            return false; // Si el formato no es dd/MM/yyyy, devolvemos false
        }

        // Extraemos el día, mes y año
        String[] dateParts = date.split("/");
        int day = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]);
        int year = Integer.parseInt(dateParts[2]);

        // Verificamos si el mes es válido
        if (month < 1 || month > 12) {
            return false;
        }

        // Validamos los días en función del mes
        if (month == 2) { // Febrero
            if (isLeapYear(year)) {
                if (day < 1 || day > 29) {
                    return false;
                }
            } else {
                if (day < 1 || day > 28) {
                    return false;
                }
            }
        } else if (month == 4 || month == 6 || month == 9 || month == 11) { // Meses con 30 días
            if (day < 1 || day > 30) {
                return false;
            }
        } else { // Meses con 31 días
            if (day < 1 || day > 31) {
                return false;
            }
        }
        return true;
    }

    /**
     * Verifica si un año es bisiesto.
     *
     * @param year El año que se desea verificar.
     * @return true si el año es bisiesto, false en caso contrario.
     */
    private static boolean isLeapYear(int year) {
        if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
            return true;
        }
        return false;
    }

    /**
     * Calcula la edad en base a la fecha de nacimiento.
     *
     * @param birthDate La fecha de nacimiento en formato "dd/MM/yyyy".
     * @return la edad calculada si la fecha es válida, -1 si la fecha es
     * incorrecta.
     */
    public static int calculateAge(String birthDate) {
        if (!checkFormatDate(birthDate)) {
            return -1; // Si la fecha no es válida, devolvemos -1
        }
        // Extraemos la fecha de nacimiento y la comparamos con la fecha actual

        String[] birthParts = birthDate.split("/");
        int birthDay = Integer.parseInt(birthParts[0]);
        int birthMonth = Integer.parseInt(birthParts[1]);
        int birthYear = Integer.parseInt(birthParts[2]);

        Calendar birth = Calendar.getInstance();
        birth.set(birthYear, birthMonth - 1, birthDay); // Restamos 1 al mes porque Calendar empieza desde 0

        Calendar today = Calendar.getInstance();
        int age = today.get(Calendar.YEAR) - birth.get(Calendar.YEAR);

        // Ajuste por si el cumpleaños aún no ha pasado este año
        if (today.get(Calendar.MONTH) < birth.get(Calendar.MONTH)
                || (today.get(Calendar.MONTH) == birth.get(Calendar.MONTH) && today.get(Calendar.DAY_OF_MONTH) < birth.get(Calendar.DAY_OF_MONTH))) {
            age--;
        }

        return age;
    }

    /**
     * Valida el formato del código postal (cinco dígitos).
     *
     * @param zip El código postal que se desea validar.
     * @return true si el código postal es válido, false en caso contrario.
     */
    public static void checkPostalCode(String zip) throws InvalidCP {
        if (zip != null && zip.matches("\\d{5}")) {
            throw new InvalidCP("Codigo postal invalido.");
        }
    }

    /**
     * Valida si la cadena de texto contiene solo caracteres numéricos.
     *
     * @param str La cadena de texto que se desea validar.
     * @return true si la cadena es numérica, false en caso contrario.
     */
    public static boolean isNumeric(String str) {

        // Verificamos si la cadena no es nula y no está vacía
        if (str == null || str.isEmpty()) {
            return false;
        }

        // Recorremos cada carácter de la cadena
        for (int i = 0; i < str.length(); i++) {
            // Si el carácter no es un dígito, devolvemos false
            if (str.charAt(i) < '0' || str.charAt(i) > '9') {
                return false;
            }
        }

        // Si todos los caracteres son dígitos, devolvemos true
        return true;
    }

    /**
     * Valida si la cadena de texto contiene solo caracteres alfabéticos.
     *
     * @param str La cadena de texto que se desea validar.
     * @return true si la cadena contiene solo letras, false en caso contrario.
     */
    public static boolean isAlphabetic(String str) {
        return str != null && str.matches("[a-zA-Z]+");
    }

    /**
     * Valida el formato de un correo electrónico.
     *
     * @param email El correo electrónico que se desea validar.
     * @return true si el formato del correo electrónico es válido, false en
     * caso contrario.
     */
    public static boolean checkEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@([A-Za-z0-9.-]+)\\.(com|es|org|net)$");
    }

    /**
     * Valida el formato de un nombre (sin números y con longitud lógica).
     *
     * @param name El nombre que se desea validar.
     * @return true si el nombre es válido, false en caso contrario.
     */
    public static void checkName(String name) throws InvalidName {
        if (name == null) {
            throw new InvalidName("El nombre no puede ser nulo");
        } else {
            if (name.length() < 2) {
                throw new InvalidName("El nombre debe tener mas de 2 caracteres");
            }
            if (name.length() > 50) {
                throw new InvalidName("El nombre no puede contener mas de 50 caracteres");
            }
            if (name.matches(".*\\d.*")) {
                throw new InvalidName("El nombre no puede contener numeros");
            }
        }
    }
}

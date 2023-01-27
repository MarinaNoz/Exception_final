package Seminar3;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Task {
    public static void main(String[] args) {
        while (true)
        start();
    } 
    public static void start() {
        System.out.println("Введите Ваши данные через пробел: ФИО дата рождения номер телефона и пол (f or m)");
        Scanner scanner = new Scanner(System.in);
        boolean flag = false;
        String[] data = null;
        while(!flag) {
            String inputFromUser = scanner.nextLine();
            inputFromUser = inputFromUser.replaceAll("\\ s+", " ");
            data = inputFromUser.split(" ");
            int chekInput = validInput(data);
            if (chekInput > 0) {
                System.out.println("Ввведены лишние данные");
            }else if (chekInput < 0) {
                System.out.println("Данных недостаточно");
            }else{
                try {
                    User user = createUser(data);
                    wrteToFile(user);
                    flag = true;
                } catch (GenderException | NumberPhoneException e) {
                    System.out.println(e.getMessage());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private static User createUser(String[] data) throws GenderException, NumberPhoneException, DateException {
        User user = new User();
        user.setLastName(data[0]);
        user.setFirstName(data[1]);
        user.setPatronymic(data[2]);

        char gender = setGender(data);
        LocalDate birthday = setBirthday(data);
        long numberPhone = setNumberPhone(data);

        user.setBirthday(birthday);
        user.setNumberPhone(numberPhone);
        user.setGender(gender);

        return user;
    }
    private static char setGender(String[] data) {
        char gender = 0;
        for (int i = 3; i < data.length; i++) {
            if ("f".equalsIgnoreCase(data[i])) {
                gender = 'f';
                break;
            }else if ("m".equalsIgnoreCase(data[i])) {
                gender = 'm';
                break;
            }
        }
        if (gender != 'f' && gender != 'm') throw new GenderException("Пол выбран не корректно");
        return gender;
    }
    private static long setNumberPhone(String[] data) {
        long numberPhone = -1;
        for (int i = 3; i < data.length; i++) {
            if (validPhone(data[i])) {
                numberPhone = Long.valueOf(data [i]);
                break;
            }
        }
        if (numberPhone < 0) throw new NumberPhoneException("Номер телефона введен не корректно");
        return numberPhone;
    }
    private static boolean validPhone(String value) {
        boolean result = false;
        try {
            Long.valueOf(value);
            result = true;
        } catch (NumberFormatException e) {
        } finally {
            return result;
        }
    }
    private static LocalDate setBirthday(String[] data) {
        LocalDate localDate = null;
        for (int i = 3; i < data.length; i++) {
            if (validBirthday (data [i])) {
                localDate = LocalDate.parse(data[i], DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                break;
            }
        }
        if (localDate == null) throw new DateException("Дата рождения введена не корректно", "SOME TEXT", 0);
        return localDate;

    }
    private static boolean validBirthday(String value) {
        boolean result = false;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        try {
            LocalDate.parse(value, formatter);
            result = true;
        } catch (DateTimeParseException e) {
        } finally {
            return result;
        }
    }

    private static int validInput(String[]array) {
        int result = 6;
        if (result > array.length) return -1;
        else if (result < array.length) return 1;
        else return 0;
    }

private static void wrteToFile(User user) throws IOException {
    String path = "Seminar3 " + user.getLastName() + ".txt";
    File file = new File(path);
    FileWriter fileWriter = new FileWriter(file, true);
    fileWriter.write(user.toString() + "\n");
    fileWriter.flush();
    fileWriter.close();
    }   
  
    
}
package Seminar3;

import java.time.LocalDate;

public class User {
    private String firstName;
    private String lastName;
    private String patronymic;
    private LocalDate birthday;
    private char gender;
    private long numberPhone;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }
    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
    private String formatBirthday() {
        String[] date = String.valueOf(this.birthday).split("-");
        return new StringBuilder().append(date[2])
                .append(".")
                .append(date[1])
                .append(".")
                .append(date[0])
                .toString();
    }
    
    public void setGender(char gender) {
        this.gender = gender;
    }
    public void setNumberPhone(long numberPhone) {
        this.numberPhone = numberPhone;
    }

    @Override
    public String toString() {
        return "<" +
                lastName +
                "><" + firstName +
                "><" + patronymic + 
                "><" + formatBirthday() + 
                "><" + numberPhone + 
                "><" + gender + 
                ">";

    }

}

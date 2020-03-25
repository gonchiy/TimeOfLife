import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Pattern;

public class TimeOfLife {

    private String MY_BIRTH_TIME;
    private String MY_BIRTH_DAY;
    private String MY_BIRTH_MONTH;
    private String MY_BIRTH_YEAR;

    private long lifeInMiliSeconds;
    private long lifeInSeconds;
    private long lifeInMinutes;
    private long lifeInHours;
    private long lifeInDays;
    private long lifeInWeeks;
    private long lifeInMonths;
    private long lifeInYears;


    TimeOfLife() {

        System.out.println("\nСкотыняка введи свое время/дату рождения в формате (\"hh:mm dd mm yyyy\" или \"dd mm yyyy\"): ");
        body(infoFromScanner());
    }



    TimeOfLife(String birthInfoString) {

        body(birthInfoString);
    }

    private void setTimeOfLife(String birthDay, String birthMonth, String birthYear){
        this.MY_BIRTH_TIME = "00:00";
        this.MY_BIRTH_DAY = birthDay;
        this.MY_BIRTH_MONTH = birthMonth;
        this.MY_BIRTH_YEAR = birthYear;
    }

    private void setTimeOfLife(String birthTime, String birthDay, String birthMonth, String birthYear){
        this.MY_BIRTH_TIME = birthTime;
        this.MY_BIRTH_DAY = birthDay;
        this.MY_BIRTH_MONTH = birthMonth;
        this.MY_BIRTH_YEAR = birthYear;
    }

    private String[] getBirthday(){
        return new String[]{ this.MY_BIRTH_TIME, this.MY_BIRTH_DAY, this.MY_BIRTH_MONTH, this.MY_BIRTH_YEAR };
    }

    private long[] getTimeOfLife() {
        return new long[]{  this.lifeInMiliSeconds, this.lifeInSeconds, this.lifeInMinutes, this.lifeInHours, this.lifeInDays, this.lifeInWeeks, this.lifeInMonths, this.lifeInYears };
    }



    public static void main(String[] args) {

        String infoString = "";

        // args
        if (args.length > 0) {
            String stringFromComandLine = "";
            for (String d:args)
                infoString = stringFromComandLine.concat(d + " ");

        } else {

            System.out.println("\nСкотыняка введи свое время/дату рождения в формате (\"hh:mm dd mm yyyy\" или \"dd mm yyyy\"): ");
            infoString = infoFromScanner();;
        }

        new TimeOfLife(infoString);
    }
    

    private void body(String strBirdthInfo){

        System.out.println("Вы ввели: "+ strBirdthInfo + "\n_____________________________________");

        if (verifyInfoString(strBirdthInfo)) {

            String[] masScan = strBirdthInfo.split(" ");
            if (masScan.length == 4)
                setTimeOfLife(masScan[0], masScan[1], masScan[2], masScan[3]);
            else
                setTimeOfLife(masScan[0], masScan[1], masScan[2]);
        } else {

            errorOutAndExit();
        }


        try {
            calculateTimeOfLife(); //java.time.format.DateTimeParseException: Text '999-03-05 00:00' could not be parsed at index 0
        } catch (RuntimeException e) {
            System.out.println("ERROR: " + e);
            errorOutAndExit();
        }

        System.out.println("BIRTHDAY INFO: " + getBirthday()[0] + " " + getBirthday()[1] + " " + getBirthday()[2] + " " + getBirthday()[3]);
        System.out.println("_____________________________________");
        //System.out.println("Time of life in miliSeconds = " + timeoflife.getTimeOfLife()[0]);
        System.out.println("Time of life in seconds = "     + getTimeOfLife()[1]);
        System.out.println("Time of life in minutes = "     + getTimeOfLife()[2]);
        System.out.println("Time of life in hours = "       + getTimeOfLife()[3]);
        System.out.println("Time of life in days = "        + getTimeOfLife()[4]);
        System.out.println("Time of life in weeks = "       + getTimeOfLife()[5]);
        System.out.println("Time of life in months = "      + getTimeOfLife()[6]);
        System.out.println("Time of life in years = "       + getTimeOfLife()[7]);

    }

    private Boolean verifyInfoString(String userInfo){

        Boolean pat1 =  Pattern.matches("^[012]\\d:[012345]\\d\\s[0123]\\d\\s[01]\\d\\s[12]\\d{3}$", userInfo.trim());
        Boolean pat2 =  Pattern.matches("^[0123]\\d\\s[01]\\d\\s[12]\\d{3}$", userInfo.trim());
        return pat1 || pat2;
    }


    static String infoFromScanner() {
        Scanner scan = new Scanner(System.in);
        String z = scan.nextLine();
        scan.close();
        return z;
    }



    private void calculateTimeOfLife() throws RuntimeException {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String birthdayString = this.MY_BIRTH_YEAR + "-" + this.MY_BIRTH_MONTH + "-" + this.MY_BIRTH_DAY + " " + this.MY_BIRTH_TIME;

        LocalDateTime birthDataTime = LocalDateTime.parse(birthdayString, formatter);
        //System.out.println("birthDataTime: "+ birthDataTime);
        LocalDateTime nowDateTime = LocalDateTime.now();
        System.out.println("nowDateTime: " + nowDateTime);

        //this.lifeInMiliSeconds  = java.time.Duration.between(birthDataTime, nowDateTime).toMillis();
        this.lifeInSeconds = java.time.Duration.between(birthDataTime, nowDateTime).getSeconds();
        this.lifeInMinutes = java.time.Duration.between(birthDataTime, nowDateTime).toMinutes();
        this.lifeInHours = java.time.Duration.between(birthDataTime, nowDateTime).toHours();
        this.lifeInDays = java.time.Duration.between(birthDataTime, nowDateTime).toDays();

        LocalDate nowDate = LocalDate.now();
        //System.out.println("nowDate:" + nowDate);

        LocalDate birthday = LocalDate.of(Integer.parseInt(this.MY_BIRTH_YEAR), Integer.parseInt(this.MY_BIRTH_MONTH), Integer.parseInt(this.MY_BIRTH_DAY));
        Period p = java.time.Period.between(birthday, nowDate);
        this.lifeInMonths = p.getYears() * 12 + p.getMonths();
        this.lifeInYears = p.getYears();
        this.lifeInWeeks = this.lifeInDays / 7;

    }


    void errorOutAndExit(){
        System.out.println("Вы ввели информацию НЕ ПРАВИЛЬНО!\nНужно ввести ДОБ в таком виде: (\"00:00 18 06 1980\" или \"18 06 1980\")");
        System.exit(0);
    }


}

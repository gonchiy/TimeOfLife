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

        TimeOfLife timeoflife = new TimeOfLife();

        if (args.length > 0) {
            String stringFromComandLine = "";
            for (String d:args)
                stringFromComandLine = stringFromComandLine.concat(d + " ");

            timeoflife.run(stringFromComandLine.trim());

        } else {

            System.out.println("\nСкотыняка введи свое время/дату рождения (\"hh:mm dd mm yyyy\" или \"dd mm yyyy\"): ");
            String stringFromScanner = timeoflife.infoFromScanner();
            timeoflife.run(stringFromScanner);
        }


        timeoflife.calculateTimeOfLife();

        System.out.println("BIRTHDAY INFO: " + timeoflife.getBirthday()[0] + " " + timeoflife.getBirthday()[1] + " " + timeoflife.getBirthday()[2] + " " + timeoflife.getBirthday()[3]);
        System.out.println("_____________________________________");
        //System.out.println("Time of life in miliSeconds = " + timeoflife.getTimeOfLife()[0]);
        System.out.println("Time of life in seconds = "     + timeoflife.getTimeOfLife()[1]);
        System.out.println("Time of life in minutes = "     + timeoflife.getTimeOfLife()[2]);
        System.out.println("Time of life in hours = "       + timeoflife.getTimeOfLife()[3]);
        System.out.println("Time of life in days = "        + timeoflife.getTimeOfLife()[4]);
        System.out.println("Time of life in weeks = "       + timeoflife.getTimeOfLife()[5]);
        System.out.println("Time of life in months = "      + timeoflife.getTimeOfLife()[6]);
        System.out.println("Time of life in years = "       + timeoflife.getTimeOfLife()[7]);
    }


    private void run(String strBirdthInfo){

        System.out.println("Вы ввели: "+ strBirdthInfo + "\n_____________________________________");

        if (verifyInfoString(strBirdthInfo)) {

            String[] masScan = strBirdthInfo.split(" ");
            if (masScan.length == 4)
                setTimeOfLife(masScan[0], masScan[1], masScan[2], masScan[3]);
            else
                setTimeOfLife(masScan[0], masScan[1], masScan[2]);
        } else {

            System.out.println("Вы ввели информацию НЕ ПРАВИЛЬНО!\nНужно ввести ДОБ в таком виде: (\"00:00 18 06 1980\" или \"18 06 1980\")");
            System.exit(0);
        }

    }

    private Boolean verifyInfoString(String userInfo){

        Boolean pat1 =  Pattern.matches("^[012]\\d:[012345]\\d\\s[0123]\\d\\s[01]\\d\\s[12]\\d{3}$", userInfo);
        Boolean pat2 =  Pattern.matches("^[0123]\\d\\s[01]\\d\\s[12]\\d{3}$", userInfo);

        return pat1 || pat2;
    }


    private String infoFromScanner() {

        Scanner scan = new Scanner(System.in);
        String z = scan.nextLine();
        scan.close();
        return z;
    }



    private void calculateTimeOfLife() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String birthdayString = this.MY_BIRTH_YEAR + "-" + this.MY_BIRTH_MONTH + "-" + this.MY_BIRTH_DAY + " " + this.MY_BIRTH_TIME;
        LocalDateTime birthDataTime = LocalDateTime.parse(birthdayString, formatter);
        //System.out.println("birthDataTime: "+ birthDataTime);
        LocalDateTime nowDateTime = LocalDateTime.now();
        System.out.println("nowDateTime: "+ nowDateTime);

        //this.lifeInMiliSeconds  = java.time.Duration.between(birthDataTime, nowDateTime).toMillis();
        this.lifeInSeconds      = java.time.Duration.between(birthDataTime, nowDateTime).getSeconds();
        this.lifeInMinutes      = java.time.Duration.between(birthDataTime, nowDateTime).toMinutes();
        this.lifeInHours        = java.time.Duration.between(birthDataTime, nowDateTime).toHours();
        this.lifeInDays         = java.time.Duration.between(birthDataTime, nowDateTime).toDays();

        LocalDate nowDate = LocalDate.now();
        //System.out.println("nowDate:" + nowDate);

        LocalDate birthday = LocalDate.of(Integer.parseInt(this.MY_BIRTH_YEAR), Integer.parseInt(this.MY_BIRTH_MONTH), Integer.parseInt(this.MY_BIRTH_DAY));
        Period p = java.time.Period.between(birthday , nowDate);
        this.lifeInMonths   = p.getYears() * 12 + p.getMonths();
        this.lifeInYears    = p.getYears();

        this.lifeInWeeks    = this.lifeInDays / 7;
    }


}

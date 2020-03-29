import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Pattern;

class TimeOfLife {
    private String myBirthTime;
    private String myBirthDay;
    private String myBirthMonth;
    private String myBirthYear;

    private long lifeInMiliSeconds;
    private long lifeInSeconds;
    private long lifeInMinutes;
    private long lifeInHours;
    private long lifeInDays;
    private long lifeInWeeks;
    private long lifeInMonths;
    private long lifeInYears;


    TimeOfLife() {
        System.out.println("\nВведите свое время/дату рождения в формате (\"hh:mm dd mm yyyy\" или \"dd mm yyyy\"): ");
        body(infoFromScanner());
    }

    TimeOfLife(String birthInfoString) {
        body(birthInfoString);
    }

    private void setTimeOfLife(String birthDay, String birthMonth, String birthYear){
        this.myBirthTime = "00:00";
        this.myBirthDay = birthDay;
        this.myBirthMonth = birthMonth;
        this.myBirthYear = birthYear;
    }

    private void setTimeOfLife(String birthTime, String birthDay, String birthMonth, String birthYear){
        this.myBirthTime = birthTime;
        this.myBirthDay = birthDay;
        this.myBirthMonth = birthMonth;
        this.myBirthYear = birthYear;
    }

    private String[] getBirthday(){
        return new String[]{ this.myBirthTime, this.myBirthDay, this.myBirthMonth, this.myBirthYear};
    }

    private long[] getTimeOfLife() {
        return new long[]{ this.lifeInMiliSeconds, this.lifeInSeconds, this.lifeInMinutes, this.lifeInHours, this.lifeInDays, this.lifeInWeeks, this.lifeInMonths, this.lifeInYears };
    }

    public static void main(String[] args) {
        String infoString = "05 03 2020";
        //String infoString = null;
        //String infoString = "";

        if (args.length > 0)
            new TimeOfLife(TimeOfLife.massToStr(args));
        else {
            if (infoString == null)
                new TimeOfLife();
            else{
                if (infoString.trim().equals(""))
                    new TimeOfLife();
                else new TimeOfLife(infoString);
            }
        }
    }

    private void body(String strBirdthInfo){
        System.out.println("Вы ввели: "+ strBirdthInfo + "\n_____________________________________");
        if (verifyInfoString(strBirdthInfo)) {

            String[] masScan = strBirdthInfo.split(" ");
            if (masScan.length == 4)
                setTimeOfLife(masScan[0], masScan[1], masScan[2], masScan[3]);
            else
                setTimeOfLife(masScan[0], masScan[1], masScan[2]);
        } else
            errorOutAndExit();


        try {
            calculateTimeOfLife(); //java.time.format.DateTimeParseException: Text '999-03-05 00:00' could not be parsed at index 0
        } catch (RuntimeException e) {
            System.out.println("ERROR: " + e);
            errorOutAndExit();
        }

        System.out.println("BIRTHDAY INFO: " + getBirthday()[0] + " " + getBirthday()[1] + " " + getBirthday()[2] + " " + getBirthday()[3]);
        System.out.println("_____________________________________");
        System.out.println("Time of life in miliSeconds = " + getTimeOfLife()[0]);
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

    static String massToStr(String[] mass){
        String str = "";
        for(int i = 0; i < mass.length; i++)
            str = (i < mass.length-1) ? str.concat(mass[i] + " ") : str.concat(mass[i]);
        return str;
    }

    private void calculateTimeOfLife() throws RuntimeException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String birthdayString = this.myBirthYear + "-" + this.myBirthMonth + "-" + this.myBirthDay + " " + this.myBirthTime;

        LocalDateTime birthDataTime = LocalDateTime.parse(birthdayString, formatter);
        //System.out.println("birthDataTime: "+ birthDataTime);
        LocalDateTime nowDateTime = LocalDateTime.now();
        System.out.println("nowDateTime: " + nowDateTime);

        this.lifeInMiliSeconds  = java.time.Duration.between(birthDataTime, nowDateTime).toMillis();
        this.lifeInSeconds      = java.time.Duration.between(birthDataTime, nowDateTime).getSeconds();
        this.lifeInMinutes      = java.time.Duration.between(birthDataTime, nowDateTime).toMinutes();
        this.lifeInHours        = java.time.Duration.between(birthDataTime, nowDateTime).toHours();
        this.lifeInDays         = java.time.Duration.between(birthDataTime, nowDateTime).toDays();
        this.lifeInWeeks        = this.lifeInDays / 7;

        LocalDate nowDate = LocalDate.now();
        //System.out.println("nowDate:" + nowDate);

        LocalDate birthday = LocalDate.of(Integer.parseInt(this.myBirthYear), Integer.parseInt(this.myBirthMonth), Integer.parseInt(this.myBirthDay));
        Period p = java.time.Period.between(birthday, nowDate);
        this.lifeInMonths   = p.getYears() * 12 + p.getMonths();
        this.lifeInYears    = p.getYears();
    }

    void errorOutAndExit(){
        System.out.println("Вы ввели информацию НЕ ПРАВИЛЬНО!\nНужно ввести ДОБ в таком виде: (\"00:00 18 06 1980\" или \"18 06 1980\")");
        System.exit(0);
    }

}

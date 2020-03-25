public class useTimeOfLife {


    public static void main(String[] args){

        //String infoString = "05 02 1980";
        String infoString = "";

        if (args.length > 0) {
            String stringFromComandLine = "";
            for (String d:args) infoString = stringFromComandLine.concat(d + " ");
            new TimeOfLife(infoString);
        } else {
            if (infoString != "") new TimeOfLife(infoString);
                else new TimeOfLife();
        }


    }

}

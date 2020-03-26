public class useTimeOfLife {

    public static void main(String[] args){

        //String infoString = "05 03 2015";
        String infoString = "";

        if (args.length > 0) {
            String stringFromComandLine = "";
            for (String d:args) stringFromComandLine = stringFromComandLine.concat(d + " ");
            new TimeOfLife(stringFromComandLine);
        } else {
            if ((infoString != "") && (infoString != null)) new TimeOfLife(infoString);
                else new TimeOfLife();
        }


    }

}

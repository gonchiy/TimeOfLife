class useTimeOfLife {

    public static void main(String[] args){

        String infoString = "05 03 2020";
        //String infoString = "";

        if (args.length > 0)
            new TimeOfLife(TimeOfLife.massToStr(args));
        else {
            if ((infoString.equals("")) || (infoString == null)) new TimeOfLife();
            else new TimeOfLife(infoString);
        }


    }

}

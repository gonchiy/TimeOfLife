class useTimeOfLife {

    public static void main(String[] args){

        //String infoString = "05 03 2020";
        String infoString = null;
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

}

package clientModel;

class UserPass
{
    String data;

    UserPass(String string){data = string;}

    boolean verify()
    {
        char[] dataArray = data.toCharArray();
        for (char c: dataArray)
        {
            if ((!Character.isDigit(c)) && (!Character.isLetter(c))) return false;
        }
        return true;
    }
}

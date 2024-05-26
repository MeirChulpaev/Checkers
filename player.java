public class player {

    private String Type;

    private boolean king;

    private boolean isRed;

    private boolean isnull;

    private boolean isspeis;

    public player(boolean king, boolean isRed, boolean isnull, boolean isspeis) {
        this.king = king;
        this.isRed=isRed;
        this.isnull=isnull;
        this.isspeis=isspeis;
        if (isRed)
            setType("\uD83D\uDD34");
        else if (isnull)
            setType("◾");
        else if (isspeis) {
            setType(" ");
        }
        else if (!isRed && !isnull && !isspeis)
            setType("\uD83D\uDD35");
    }
    public boolean isRed() {
        return isRed;
    }

    public void setRed(boolean red) {
        isRed = red;
    }

    public String getType() {
        return Type;
    }

    public boolean isKing() {
        return king;
    }

    public void setType(String type) {
        Type = type;
    }

    public void setKing(boolean king) {
        this.king = king;
        if (isRed)
         setType("♚");
        else
            setType("♔");
    }

    public boolean isnull() {
        return isnull;
    }

    public boolean isSpeis() {
        return isspeis;
    }

    public void setisspeis(boolean isspeis) {
        this.isspeis = isspeis;
        setType(" ");

    }

    public player copy(){
        player a =new player(isKing(),isRed,isnull,isSpeis());
        setKing(isKing());
       return a;
    }
}
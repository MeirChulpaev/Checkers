import java.util.Scanner;

public class Main03 {
    public static void main(String[] args) {

        player[][] arr = {{new player(false, true, false, false), new player(false, false, true, false), new player(false, true, false, false), new player(false, false, true, false), new player(false, true, false, false), new player(false, false, true, false), new player(false, true, false, false), new player(false, false, true, false)},
                {new player(false, false, true, false), new player(false, true, false, false), new player(false, false, true, false), new player(false, true, false, false), new player(false, false, true, false), new player(false, true, false, false), new player(false, false, true, false), new player(false, true, false, false)},
                {new player(false, true, false, false), new player(false, false, true, false), new player(false, true, false, false), new player(false, false, true, false), new player(false, true, false, false), new player(false, false, true, false), new player(false, true, false, false), new player(false, false, true, false)},
                {new player(false, false, true, false), new player(false, false, false, true), new player(false, false, true, false), new player(false, false, false, true), new player(false, false, true, false), new player(false, false, false, true), new player(false, false, true, false), new player(false, false, false, true)},
                {new player(false, false, false, true), new player(false, false, true, false), new player(false, false, false, true), new player(false, false, true, false), new player(false, false, false, true), new player(false, false, true, false), new player(false, false, false, true), new player(false, false, true, false)},
                {new player(false, false, true, false), new player(false, false, false, false), new player(false, false, true, false), new player(false, false, false, false), new player(false, false, true, false), new player(false, false, false, false), new player(false, false, true, false), new player(false, false, false, false)},
                {new player(false, false, false, false), new player(false, false, true, false), new player(false, false, false, false), new player(false, false, true, false), new player(false, false, false, false), new player(false, false, true, false), new player(false, false, false, false), new player(false, false, true, false)},
                {new player(false, false, true, false), new player(false, false, false, false), new player(false, false, true, false), new player(false, false, false, false), new player(false, false, true, false), new player(false, false, false, false), new player(false, false, true, false), new player(false, false, false, false)}};


        String p = "\uD83D\uDD35";

        while (!Win(arr)) {
            bord(arr);
            if (p.equals("\uD83D\uDD35"))
                p = "\uD83D\uDD34";
            else
                p = "\uD83D\uDD35";
            play(arr, p);
        }
        bord(arr);
        System.out.println(p + " Winnnnnnn");
    }


    public static void bord(player[][] arr) {

        System.out.println("    1  2  3  4  5  6  7  8 ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(i + 1 + " |");
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j].getType().equals("\uD83D\uDD34") || arr[i][j].getType().equals("\uD83D\uDD35"))
                    System.out.print(arr[i][j].getType() + "|");
                else
                    System.out.print(arr[i][j].getType() + " |");

            }
            System.out.println();
            System.out.println("  --------------------------");
        }
    }

    public static void play(player[][] arr, String p) {
        Scanner in = new Scanner(System.in);
        int number = -1;
        int r2 = 0, c2 = 0;
        while (true) {
            System.out.println("player " + p + " Enter your row");
            int row1 = in.nextInt() - 1;
            System.out.println("and your column");
            int column1 = in.nextInt() - 1;
            System.out.println("Enter the row you want to move");
            int row2 = in.nextInt() - 1;
            System.out.println("Enter the column you want to move");
            int column2 = in.nextInt() - 1;
            int a;

            boolean IAmAKing = arr[row1][column1].isKing();
            boolean TheRedPlayerDoesNotGoBack = (arr[row1][column1].isRed() && row1 < row2 || number == 0); //**
            boolean TheBluePlayerDoesNotGoBack = (!arr[row1][column1].isRed() && row1 > row2 || number == 0);

            if (IAmAKing || TheRedPlayerDoesNotGoBack || TheBluePlayerDoesNotGoBack) {
                a = 0;
                boolean ItsThatPlayersTurn=arr[row1][column1].getType().equals(p);
                boolean HeIsKing=arr[row1][column1].getType().equals("♚") || arr[row1][column1].getType().equals("♔");
                if (( ItsThatPlayersTurn || HeIsKing ) && CheckMOve(row1, column1, row2, column2, a, arr) && IfCanMove(row1, column1, arr, row2, column2) && number != 0) {
                    DoTheMove(row1, column1, arr, row2, column2, a);
                    break;
                }
                a = 1;
                if (( ItsThatPlayersTurn || HeIsKing ) && CheckMOve(row1, column1, row2, column2, a, arr) && IfCanMove(row1, column1, arr, row2, column2)) {
                    boolean IfThePlaceIWantToMoveFromIsTheLastPlaceIWas= number == 0 && r2 != row1 && c2 != column1;
                    if (IfThePlaceIWantToMoveFromIsTheLastPlaceIWas) {
                        System.out.println("you cant eat");
                        break;
                    }
                    DoTheMove(row1, column1, arr, row2, column2, a);
                    System.out.println("If you want to eat more Enter 1 else Enter 0..");
                    number = in.nextInt();
                    if (number == 0)
                        break;
                    else {
                        bord(arr);
                        r2 = row2;
                        c2 = column2;
                        number--;
                    }
                }
            }
            if (number < 0)
                System.out.println("please do again");
            else
                System.out.println(" Enter next eat or fix your Mistake");
        }
    }


    public static boolean IfCanMove(int row, int column, player[][] arr, int row2, int column2) {
        boolean MyPlaceIsNotEmpty = !arr[row][column].isSpeis();
        boolean ThePlaceIWantToGoIsEmpty = arr[row2][column2].isSpeis();
        boolean MyPlaceIsNotNull = !arr[row][column].isnull();

        return (MyPlaceIsNotEmpty && MyPlaceIsNotNull && ThePlaceIWantToGoIsEmpty);
    }

    public static boolean CheckMOve(int row1, int column1, int row2, int column2, int a, player[][] arr) {

        boolean ThePlaceIWantToGetToIsACubeOrTwoAway_OptionA = (row1 == row2 + (a + 1) && column1 == column2 + (a + 1));

        boolean ThePlaceIWantToGetToIsACubeOrTwoAway_OptionB = (row1 == row2 - (a + 1) && column1 == column2 - (a + 1));

        boolean ThePlaceIWantToGetToIsACubeOrTwoAway_OptionC = (row1 == row2 - (a + 1) && column1 == column2 + (a + 1));
        boolean ThePlaceIWantToGetToIsACubeOrTwoAway_OptionD = (row1 == row2 + (a + 1) && column1 == column2 - (a + 1));

        if (a == 0)
            return ThePlaceIWantToGetToIsACubeOrTwoAway_OptionA || ThePlaceIWantToGetToIsACubeOrTwoAway_OptionB ||
                    ThePlaceIWantToGetToIsACubeOrTwoAway_OptionC || ThePlaceIWantToGetToIsACubeOrTwoAway_OptionD;
        else
            return (ThePlaceIWantToGetToIsACubeOrTwoAway_OptionA && !arr[row1 - 1][column1 - 1].isSpeis()) || (ThePlaceIWantToGetToIsACubeOrTwoAway_OptionB && !arr[row1 + 1][column1 + 1].isSpeis()) ||
                    (ThePlaceIWantToGetToIsACubeOrTwoAway_OptionC && !arr[row1 + 1][column1 - 1].isSpeis()) || (ThePlaceIWantToGetToIsACubeOrTwoAway_OptionD && !arr[row1 - 1][column1 + 1].isSpeis());
    }

    public static void DoTheMove(int row1, int column1, player[][] arr, int row2, int column2, int a) {
        boolean IAmAKing = arr[row1][column1].isKing();

        arr[row2][column2] = arr[row1][column1].copy();

        boolean PlayerReachedTheLastLine = (row2 == 7 || row2 == 0);

        if (PlayerReachedTheLastLine)
            arr[row2][column2].setKing(true);
        if (IAmAKing)
            arr[row2][column2].setKing(true);

        arr[row1][column1].setisspeis(true);
        if (a == 1) {
            boolean OneOption0fDisplacement=row1 == row2 + 2 && column1 == column2 + 2;
            boolean SecondOption0fDisplacement=row1 == row2 - 2 && column1 == column2 - 2;
            boolean AThirdPossibilityOfDisplacement=row1 == row2 - 2 && column1 == column2 + 2;
            boolean AFourthPossibilityOfDisplacement=row1 == row2 + 2 && column1 == column2 - 2;
            if (OneOption0fDisplacement) {
                arr[row1 - 1][column1 - 1].setisspeis(true);  //Delete the middle one
            }
            if (SecondOption0fDisplacement) {
                arr[row1 + 1][column1 + 1].setisspeis(true);
            }
            if (AThirdPossibilityOfDisplacement) {
                arr[row1 + 1][column1 - 1].setisspeis(true);
            }
            if (AFourthPossibilityOfDisplacement) {
                arr[row1 - 1][column1 + 1].setisspeis(true);
            }
        }
    }

    public static boolean Win(player[][] arr) {
        int HowManyRedPlayersAreThereOnTheBoard = 0;
        int HowManyBluePlayersAreThereOnTheBoard = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j].isRed())
                    HowManyRedPlayersAreThereOnTheBoard++;
                if (!arr[i][j].isRed() && !arr[i][j].isKing() && !arr[i][j].isSpeis() && !arr[i][j].isnull())
                    HowManyBluePlayersAreThereOnTheBoard++;
            }

        }
        return HowManyBluePlayersAreThereOnTheBoard == 0 || HowManyRedPlayersAreThereOnTheBoard == 0;
    }
}

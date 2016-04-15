package Theater_April_15;
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * Created by cap on 4/15/16.
 */

class SeatMap{

    private int _row, _column;
    private Seat _seat[][];

    SeatMap(int row, int column){ //constructor
        _row = row;
        _column= column;
        _seat = new Seat[_row][_column];
        seat_allocate();
    }

    private void seat_allocate() {
        for (int row_idx = 0; row_idx < _row; row_idx++) {
            for (int col_idx = 0; col_idx < _column; col_idx++)
                _seat[row_idx][col_idx] = new Seat(); //저장 공간 만들기
        }
    }

    void reserve(){
        String _name;
        String _reserveSeat;
        String _seatLetter;
        boolean isMore = true;
        boolean flag;
        int _seatNumber;
        int _seatNumber2;

        System.out.print("Name: ");
        _name = Theater.input.next();


        try{
            do {
                System.out.print("(O)ne or (M)ore? ");
                _reserveSeat = Theater.input.next();
                switch (_reserveSeat) {
                    case "O":
                    case "o":
                    case "One":
                    case "one":
                        isMore = false;
                        flag = false;
                        break;

                    case "M":
                    case "m":
                    case "More":
                    case "more":
                        isMore = true;
                        flag = false;
                        break;

                    default:
                        System.out.println("You have inputted an invalid option.");
                        flag = true;
                }
            }while(flag);


            System.out.print("Seat row alphabet: ");
            _seatLetter = Theater.input.next();

            if(_seatLetter.length() > 1 || _seatLetter.charAt(0) < 'A' || _seatLetter.charAt(0) > 'E'){
                throw (new InputMismatchException());
            }

            if(isMore) {
                System.out.print("Reserve seat number from: ");
                _seatNumber = Theater.input.nextInt() - 1;
                System.out.print("to: ");
                _seatNumber2 = Theater.input.nextInt() - 1;
                flag = true;

                if(_seatNumber < 1 || _seatNumber > 9 || _seatNumber2 < 1 || _seatNumber2 > 9)
                    throw(new InputMismatchException());

                if (_seatNumber > _seatNumber2) {
                    for (int column_indx = _seatNumber; column_indx <= _seatNumber2; _seatNumber++) {
                        if (_seat[_seatLetter.charAt(0) - 'A'][column_indx].isOccupied()) {
                            System.out.println("You cannot reserve the seats in this area.");
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        for (int column_indx = _seatNumber; column_indx <= _seatNumber2; _seatNumber++) {
                            _seat[_seatLetter.charAt(0) - 'A'][column_indx].setName(_name);

                        }
                    }
                } else {
                    for (int column_indx = _seatNumber2; column_indx >= _seatNumber; column_indx--) {
                        if (_seat[_seatLetter.charAt(0) - 'A'][column_indx].isOccupied()) {
                            System.out.println("You cannot reserve the seats in this area.");
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        for (int column_indx = _seatNumber2; column_indx >= _seatNumber; column_indx--) {
                            _seat[_seatLetter.charAt(0) - 'A'][column_indx].setName(_name);
                        }
                    }
                }
            }
            else { // reserve one seat
                System.out.print("Seat number: ");
                _seatNumber = Theater.input.nextInt() - 1;
                if(_seatNumber < 1 || _seatNumber > 9)
                    throw(new InputMismatchException());

                if (_seat[_seatLetter.charAt(0) - 'A'][_seatNumber].isOccupied()) {
                    System.out.println("You cannot reserve this seat.");
                }
                else {
                    _seat[_seatLetter.charAt(0) - 'A'][_seatNumber].setName(_name);
                }

            }
        }
        catch(InputMismatchException e){
            System.out.println("You have entered either an invalid number or alphabet.");
        }

    }

    void view(){
        int n = 1;
        for(int row_indx = 0; row_indx < _row; row_indx++) {
            for (int column_indx = 0; column_indx < _column; column_indx++) {
                if (_seat[row_indx][column_indx].isOccupied()) {
                    System.out.println(n++ + ": " + (char)(row_indx + 'A') + (column_indx + 1) + ", "
                            + _seat[row_indx][column_indx].getName());
                }
            }
        }
    }

    void cancel(){
        String _name;

        System.out.print("Please enter your name: ");
        _name = Theater.input.next();

        // TODO : optimization of the cancellation process
        for(int row_indx = 0; row_indx < _row; row_indx++) {
            for(int column_indx = 0; column_indx < _column; column_indx++) {
                if (_seat[row_indx][column_indx].match(_name)) {
                    _seat[row_indx][column_indx].cancel();
                }
            }
        }

    }

    void showMap(){
        System.out.print(" " );
        for(int column_indx = 1; column_indx <= _column; column_indx++)
            System.out.printf("%4d", column_indx);
        System.out.println();

        for(int row_indx = 0; row_indx < _row; row_indx++){
            System.out.print((char)(row_indx + 'A') + ":");
            for(int column_indx = 0; column_indx < _column; column_indx++){
                System.out.print(" [");
                if(_seat[row_indx][column_indx].isOccupied())
                    System.out.print("0]");
                else
                    System.out.print(" ]");
            }

            System.out.println();

        }

    }

}

class Seat{
    String reserver_name;

    Seat(){
        reserver_name = null; //reference variable currently points to nothing
    }

    String getName(){
        return reserver_name;
    }

    void cancel(){
        reserver_name = null;
    }

    void setName(String name){
        reserver_name = name;
    }

    boolean isOccupied(){
        return (reserver_name != null);
    }

    boolean match(String name){
        return (reserver_name != null)? reserver_name.equals(name) : false;
    }
}

public class Theater {
    final static int SEAT_ROW = 5;
    final static int SEAT_COLUMN = 9;
    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args){
        SeatMap myTheater = new SeatMap(SEAT_ROW, SEAT_COLUMN);
        String _input;


        while(true) {
            myTheater.showMap();
            System.out.print("(R)eserve, (V)iew, (C)ancel, (Q)uit: ");
            _input = input.next();

            switch(_input){
                case "r":
                case "R":
                case "reserve":
                case "Reserve":
                    myTheater.reserve();
                    break;
                case "v":
                case "V":
                case "view":
                case "View":
                    myTheater.view();
                    break;
                case "c":
                case "C":
                case "cancel":
                case "Cancel":
                    myTheater.cancel();
                    break;
                case "q":
                case "Q":
                case "quit":
                case "Quit":
                    System.exit(0);
                default:
                    System.out.println("You have inputted an invalid option.");
                    break;

            }


        }

    }

}

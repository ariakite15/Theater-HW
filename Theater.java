package Theater_April_15;
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * Created by cap on 4/15/16.
 */

class SeatMap{

    private int _row, _column;
    private Seat _seat[][];
    Scanner input;

    SeatMap(int row, int column){ //constructor
        _row = row;
        _column= column;
        _seat = new Seat[_row][_column];
        seat_allocate();
        input = new Scanner(System.in);
    }

    private void seat_allocate() {
        for (int row_idx = 0; row_idx < _row; row_idx++) {
            for (int col_idx = 0; col_idx < _column; col_idx++)
                _seat[row_idx][col_idx] = new Seat(); //저장 공간 만들기
        }
    }

    void reserve(){
        String _name;
        int _reserveSeat;
        String _seatLetter;
        int _seatNumber;
        int _seatNumber2;

        System.out.print("Name: ");
        _name = input.next();


        try{
            System.out.print("Number of seats: ");
            _reserveSeat = input.nextInt();


            System.out.print("Seat row alphabet: ");
            _seatLetter = input.next();

            if(_seatLetter.length() > 1 || _seatLetter.charAt(0) < 'A' || _seatLetter.charAt(0) > 'E'){
                throw new InputMismatchException();
            }

            if(1 < _reserveSeat && _reserveSeat <= 9){
                System.out.print("Reserve seat number from: ");
                _seatNumber = input.nextInt();
                System.out.print("to: ");
                _seatNumber2 = input.nextInt();

                if(_seatNumber > _seatNumber2){
                    for(int column_indx = _seatNumber; column_indx <= _seatNumber2; _seatNumber++){
                        if (_seat[_seatLetter.charAt(0) - 'A'][column_indx].isOccupied()){
                            System.out.print("You cannot reserve the seats in this area.");
                            break;
                        }
                    }
                    for(int row_indx = 0; row_indx < _row; row_indx++) {
                        for (int column_indx = 0; column_indx < _column; column_indx++){


                        }
                    }

                    // how to show the reservation changes on the seating chart?

                }
                else{
                    for(int column_indx = _seatNumber2; column_indx >= _seatNumber; column_indx--){
                        if(_seat[_seatLetter.charAt(0) - 'A'][column_indx].isOccupied()){
                            System.out.print("You cannot reserve the seats in this area.");
                            break;
                        }
                    }
                    // how to how to show the reservation changes on the seating chart?
                }


            }
            else if(_reserveSeat == 1) {
                System.out.print("Seat number: ");
                _seatNumber = input.nextInt();
                // how to show the reservation changes on the seating chart? do you call isOccupied()?
            }
            else
                System.out.println("You can only reserve betweeen 1-9 seats.");

        }
        catch(InputMismatchException e){
            System.out.println("You have entered either an invalid number or alphabet.");
        }

    }

    void view(){
        System.out.println();
        //System.out.println(Seat.getName(reserver));

    }

    void cancel(){

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
                    System.out.print(" 0]");
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
        return (reserver_name.equals(name));
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

import java.util.HashMap;
import java.util.Map;

public class GameBoard {

    private char[] positionsOfBoard = {'E','H','T','E','E','J','E','E','H','E','E','T','E','E','E','E','J','E','H','E','E','T',
                                'E','E','E','J','E','E','E','E','H','T','E','J','E','E','E'};

    private Map<HotelCell,Integer> hotelsList = new HashMap<>();

    GameBoard() {
        defineHotelsList();
    }

    char[] getPositionsOfBoard() {
        return positionsOfBoard;
    }

    Map<HotelCell,Integer> getHotelsList() {
        return hotelsList;
    }

    private void defineHotelsList() {
        hotelsList.put(new HotelCell(),2);
        hotelsList.put(new HotelCell(),9);
        hotelsList.put(new HotelCell(),19);
        hotelsList.put(new HotelCell(),31);
    }
}

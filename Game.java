import java.util.*;

public class Game {

    GameBoard gameBoard = new GameBoard();
    List<Player> players = new ArrayList<>();
    int numberOfPlayers = 0;

    void addUser(Player player) {
        players.add(player);
        numberOfPlayers++;
    }

    void start() {
        String nullString = null;
        if(numberOfPlayers<2) {
            System.out.println("Game requires minimum of two players.");
        } else {
            char[] positionsOfBoard = gameBoard.getPositionsOfBoard();
            HashMap<HotelCell,Integer> hotelsList = (HashMap<HotelCell, Integer>) gameBoard.getHotelsList();
            int countChances = 0, countPlayers = 0;
            while (countPlayers < numberOfPlayers) {
                for (Player player : players) {
                    System.out.println("Player " + player.getName());
                    int currentPosition = player.getPosition();
                    System.out.println("Current position " + currentPosition);
                    int nextPosition = currentPosition + randomDice();
                    System.out.println("Dice " + nextPosition);
                    if(nextPosition>=positionsOfBoard.length) {
                        nextPosition = nextPosition - positionsOfBoard.length;
                        System.out.println("Modified position "+nextPosition);
                    }
                    player.setPosition(nextPosition);
                    countChances++;
                    char position = positionsOfBoard[nextPosition];
                    int money, cost;
                    String transaction;
                    switch (position) {

                        case 'E':
                            EmptyCell emptyCell = new EmptyCell();
                            cost = emptyCell.getCost();
                            transaction = emptyCell.getTransaction();
                            money = moneyTransaction(player.getMoney(), transaction, cost);
                            player.setMoney(money);
                            break;

                        case 'J':
                            JailCell jailCell = new JailCell();
                            cost = jailCell.getFine();
                            transaction = jailCell.getTransaction();
                            money = moneyTransaction(player.getMoney(), transaction, cost);
                            player.setMoney(money);
                            break;

                        case 'T':
                            TreasureCell treasureCell = new TreasureCell();
                            cost = treasureCell.getValue();
                            transaction = treasureCell.getTransaction();
                            money = moneyTransaction(player.getMoney(), transaction, cost);
                            player.setMoney(money);
                            break;

                        case 'H':
                            for(Map.Entry<HotelCell,Integer> hotel : hotelsList.entrySet()) {
                                if(hotel.getValue().equals(position)) {
                                    if (hotel.getKey().getOwner().equals(nullString)) {
                                        hotel.getKey().setOwner(player.getName());
                                        cost = hotel.getKey().getWorth();
                                        transaction = hotel.getKey().getTransaction();
                                        money = moneyTransaction(player.getMoney(), transaction, cost);
                                        player.setMoney(money);
                                    } else {
                                        String name = hotel.getKey().getOwner();
                                        cost = hotel.getKey().getRent();
                                        transaction = hotel.getKey().getTransaction();
                                        money = moneyTransaction(player.getMoney(), transaction, cost);
                                        player.setMoney(money);
                                        for (Player owner : players) {
                                            if (owner.getName().equals(name)) {
                                                transaction = "credit";
                                                money = moneyTransaction(player.getMoney(), transaction, cost);
                                                owner.setMoney(money);
                                            }
                                        }
                                    }
                                }
                            }
                            break;
                    }
                }
                if (countChances == 10 * numberOfPlayers) {
                    countPlayers = numberOfPlayers;
                }
            }
        }
        output();
    }

    private int userDice() {
        int minimum = 2, maximum = 12;
        System.out.println("Enter any number between "+minimum+" and "+maximum+" : ");
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        return input;
    }

    private int randomDice() {
        int minimum = 2, maximum = 12;
        Random random = new Random();
        int output =  random.nextInt(maximum + 1 - minimum) + minimum;
        System.out.println("Dice "+output);
        return output;
    }

    private int moneyTransaction(int money, String transaction, int amount) {
        if(transaction.equals("debit")) {
            money = money-amount;
        } else {
            money = money+amount;
        }
        return money;
    }

    private void output() {
        int index = 0;
        int[] remainingAmount = new int[numberOfPlayers];
        for(Player player : players) {
            remainingAmount[index] = player.getMoney();
            index++;
        }
        for(index = 0 ; index<numberOfPlayers-1 ; index++) {
            if(remainingAmount[index]<remainingAmount[index+1]) {
                int temp = remainingAmount[index];
                remainingAmount[index] = remainingAmount[index+1];
                remainingAmount[index+1] = temp;
            }
        }
        for(index = 0 ; index<numberOfPlayers ; index++) {
            for(Player player : players) {
                if(player.getMoney()==(remainingAmount[index])) {
                    System.out.println(player.getName()+ " has total worth "+player.getMoney());
                }
            }
        }
    }
}

public class GameApplication {

    public  static void main(String args[]) {
        Player player1 = new Player("Sindhu");
        Player player2 = new Player("Rajiv");
        Player player3 = new Player("Selena");
        Game game = new Game();
        game.addUser(player1);
        game.addUser(player2);
        game.addUser(player3);
        game.start();
    }
}

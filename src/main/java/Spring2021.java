import java.io.IOException;
import java.util.Properties;
import com.codingame.gameengine.runner.simulate.GameResult;

import com.codingame.gameengine.runner.MultiplayerGameRunner;

public class Spring2021 {

    static String[] DEFAULT_AI = new String[] {
        "python3", "config/Boss.py"
    };
    static String[] BOSS_WOOD2 = new String[] {
        "python3", "config/level1/Boss.py"
    };
    static String[] BOSS_WOOD1 = new String[] {
        "python3", "config/level2/Boss.py"
    };

    public static void main(String[] args) throws IOException, InterruptedException {
        launchGame();
    }

    public static void launchGame() throws IOException, InterruptedException {
        Integer win_count = 0;
        Integer lose_count = 0;
        Integer draw_count = 0;
        for (Long seed = 1L; seed <= 100; seed++){
            MultiplayerGameRunner gameRunner = new MultiplayerGameRunner();
            gameRunner.setLeagueLevel(3);
            Properties gameParameters = new Properties();
            gameRunner.setGameParameters(gameParameters);

            gameRunner.addAgent(
                DEFAULT_AI,
                "Tororo",
                "https://static.codingame.com/servlet/fileservlet?id=61910307869345"
            );

            gameRunner.addAgent(
                DEFAULT_AI,
                "Ghilbib",
                "https://static.codingame.com/servlet/fileservlet?id=61910289640958"
            );

            gameRunner.setSeed(seed);
            GameResult result = gameRunner.simulate();
            Integer my_score = result.scores.get(0);
            Integer opp_score = result.scores.get(1);
            if (my_score > opp_score) win_count++;
            else if (my_score < opp_score) lose_count++;
            else draw_count++;
            System.out.println("Game[" + seed + "] Score: " + my_score + ":" + opp_score + "  Total win-lose-draw: " + win_count + "-" + lose_count + "-" + draw_count);
        }
    }
}

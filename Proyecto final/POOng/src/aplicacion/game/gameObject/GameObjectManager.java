package aplicacion.game.gameObject;

import java.util.ArrayList;
import java.util.HashMap;

public class GameObjectManager {
    private float gameWidth;
    private float gameHeight;

    private HashMap<String, Float> gameObjectProperties;

    private Field field;
    private ArrayList<Player> players;
    private Ball ball;

    public GameObjectManager(float gameWidth, float gameHeight) {
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;
        gameObjectProperties = new HashMap<>();
        players = new ArrayList<>();
        calculateDimensions();
        calculatePositions();
    }

    public void SpawnObjects() {
        field = new Field(gameObjectProperties.get("fieldXPosition"),
                gameObjectProperties.get("fieldYPosition"),
                gameObjectProperties.get("fieldWidth"),
                gameObjectProperties.get("fieldHeight"));
        Player player1 = new Player(gameObjectProperties.get("characterXPosition"),
                gameObjectProperties.get("characterOneYPosition"),
                gameObjectProperties.get("characterDimension"),
                gameObjectProperties.get("characterDimension"));
        Player player2 = new Player(gameObjectProperties.get("characterXPosition"),
                gameObjectProperties.get("characterTwoYPosition"),
                gameObjectProperties.get("characterDimension"),
                gameObjectProperties.get("characterDimension"));
        players.add(player1);
        players.add(player2);
        ball = new Ball(gameObjectProperties.get("ballXPosition"),
                gameObjectProperties.get("ballYPosition"),
                gameObjectProperties.get("ballDimension"),
                gameObjectProperties.get("ballDimension"));
    }

    public String toString() {
        String string = field.toString();
        string += ", " + players.get(0).toString();
        string += ", " + players.get(1).toString();
        string += ", " + ball.toString();
        return string;
    }

    private void calculateDimensions() {
        float fieldHeight = gameHeight * 0.9f;
        float fieldWidth =  fieldHeight * 0.726f;

        gameObjectProperties.put("fieldHeight", fieldHeight);
        gameObjectProperties.put("fieldWidth", fieldWidth);

        gameObjectProperties.put("characterDimension", fieldWidth * 0.2113f);

        gameObjectProperties.put("ballDimension", fieldWidth * 0.0566f);
    }

    private void calculatePositions() {
        float characterOffset = 0.05f;

        float fieldWidth = gameObjectProperties.get("fieldWidth");
        float fieldHeight = gameObjectProperties.get("fieldHeight");
        float fieldXPosition = gameWidth / 2f - fieldWidth / 2f;
        float fieldYPosition = gameHeight * 0.05f;
        float characterDimension = gameObjectProperties.get("characterDimension");
        float ballDimension = gameObjectProperties.get("ballDimension");

        gameObjectProperties.put("fieldXPosition", fieldXPosition);
        gameObjectProperties.put("fieldYPosition", fieldYPosition);

        gameObjectProperties.put("characterXPosition", fieldXPosition + fieldWidth / 2f - characterDimension / 2f);
        gameObjectProperties.put("characterOneYPosition", fieldYPosition - fieldHeight * characterOffset);
        gameObjectProperties.put("characterTwoYPosition",
                fieldYPosition + fieldHeight - characterDimension - fieldHeight * characterOffset);

        gameObjectProperties.put("ballXPosition", fieldXPosition + fieldWidth / 2f - ballDimension / 2f);
        gameObjectProperties.put("ballYPosition", fieldYPosition + fieldHeight / 2f - ballDimension / 2f);
    }
}


package aplicacion.game.entities;

import aplicacion.game.components.RectangleCollider;
import aplicacion.game.enums.BallSpeed;
import aplicacion.game.enums.EntityName;
import aplicacion.game.utils.Vector2;

import java.util.HashMap;


public class EntitySpawner {

    private final int gameSize = 800;

    private HashMap<String, Float> gameObjectProperties;


    public EntitySpawner() {
        gameObjectProperties = new HashMap<>();
        calculateDimensions();
        calculatePositions();
    }

    public void SpawnObjects() {
        Entity.removeAll();
        float characterOffset = 20;

        new Field(EntityName.FIELD,
                gameObjectProperties.get("fieldXPosition"),
                gameObjectProperties.get("fieldYPosition"),
                gameObjectProperties.get("fieldWidth"),
                gameObjectProperties.get("fieldHeight"));

        Player p1 = new Player(EntityName.PLAYER_TOP,
                gameObjectProperties.get("characterXPosition"),
                gameObjectProperties.get("characterOneYPosition"),
                gameObjectProperties.get("characterDimension"),
                gameObjectProperties.get("characterDimension"),
                1);
        p1.addComponent(new RectangleCollider(p1, new Vector2(0, 30), new Vector2(110, 50)));

        Player p2 = new Player(EntityName.PLAYER_BOTTOM,
                gameObjectProperties.get("characterXPosition"),
                gameObjectProperties.get("characterTwoYPosition"),
                gameObjectProperties.get("characterDimension"),
                gameObjectProperties.get("characterDimension"),
                -1);
        p2.addComponent(new RectangleCollider(p2, new Vector2(0, 30), new Vector2(110, 50)));

        Ball ball = new Ball(EntityName.BALL,
                gameObjectProperties.get("ballXPosition"),
                gameObjectProperties.get("ballYPosition"),
                gameObjectProperties.get("ballDimension"),
                gameObjectProperties.get("ballDimension"),
                BallSpeed.SLOW);
        ball.addComponent(new RectangleCollider(ball));

        new ScoreBoard(EntityName.SCORE_BOARD, 0, 0, 0, 0);

        /*new Field(EntityName.FIELD, 140, 40, 520, 720);

        Player p1 = new Player(EntityName.PLAYER_TOP, 345, 40 - characterOffset,
                110, 110, 1);
        p1.addComponent(new RectangleCollider(p1, new Vector2(0, 30), new Vector2(110, 50)));

        Player p2 = new Player(EntityName.PLAYER_BOTTOM, 345, 650 - characterOffset,
                110, 110, -1);
        p2.addComponent(new RectangleCollider(p2, new Vector2(0, 30), new Vector2(110, 50)));

        Ball ball = new Ball(EntityName.BALL, 378, 378, 44, 44, BallSpeed.SLOW);
        ball.addComponent(new RectangleCollider(ball));

        new ScoreBoard(EntityName.SCORE_BOARD, 0, 0, 0, 0);*/
    }

    private void calculateDimensions() {
        float fieldHeightPercentage = 0.8f;
        float fieldHeight = gameSize * fieldHeightPercentage;
        float fieldWidth = fieldHeight * 0.726f;

        gameObjectProperties.put("fieldHeightPercentage", fieldHeightPercentage);
        gameObjectProperties.put("fieldHeight", fieldHeight);
        gameObjectProperties.put("fieldWidth", fieldWidth);

        gameObjectProperties.put("characterDimension", fieldWidth * 0.2113f);

        gameObjectProperties.put("ballDimension", fieldWidth * 0.0566f);
    }

    private void calculatePositions() {
        float characterOffset = 0.02f;

        float fieldHeightPercentage = gameObjectProperties.get("fieldHeightPercentage");
        float fieldWidth = gameObjectProperties.get("fieldWidth");
        float fieldHeight = gameObjectProperties.get("fieldHeight");
        float fieldXPosition = gameSize / 2f - fieldWidth / 2f;
        float fieldYPosition = gameSize * ((1 - fieldHeightPercentage) / 2f);
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


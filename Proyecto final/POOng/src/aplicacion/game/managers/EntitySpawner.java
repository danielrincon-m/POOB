package aplicacion.game.managers;

import aplicacion.game.entities.Ball;
import aplicacion.game.entities.Field;
import aplicacion.game.entities.Player;
import aplicacion.game.enums.BallSpeed;
import aplicacion.game.enums.EntityName;

import java.util.HashMap;

/*
TODO: Debería haber una clase "Spawner" que se encargue de decidir que elementos se van a agregar,
 y agregarlos por medio de esta interfaz

TODO: La inicialización de las variables de posición y tamaño de cada gameObject,
 se deberían realizar dentro del gameObject.
 */

public class EntitySpawner {
    private float gameWidth;
    private float gameHeight;

    private HashMap<String, Float> gameObjectProperties;


    public EntitySpawner(float gameWidth, float gameHeight) {
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;
        gameObjectProperties = new HashMap<>();
        calculateDimensions();
        calculatePositions();
    }

    public void SpawnObjects() {
        new Field(EntityName.FIELD, gameObjectProperties.get("fieldXPosition"),
                gameObjectProperties.get("fieldYPosition"),
                gameObjectProperties.get("fieldWidth"),
                gameObjectProperties.get("fieldHeight"));
        new Player(EntityName.PLYER_ONE, gameObjectProperties.get("characterXPosition"),
                gameObjectProperties.get("characterOneYPosition"),
                gameObjectProperties.get("characterDimension"),
                gameObjectProperties.get("characterDimension"));
        new Player(EntityName.PLAYER_TWO, gameObjectProperties.get("characterXPosition"),
                gameObjectProperties.get("characterTwoYPosition"),
                gameObjectProperties.get("characterDimension"),
                gameObjectProperties.get("characterDimension"));
        new Ball(EntityName.BALL, gameObjectProperties.get("ballXPosition"),
                gameObjectProperties.get("ballYPosition"),
                gameObjectProperties.get("ballDimension"),
                gameObjectProperties.get("ballDimension"),
                BallSpeed.SLOW);
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


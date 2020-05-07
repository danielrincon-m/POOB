package aplicacion.game.entities.spawner;

import aplicacion.game.components.RectangleCollider;
import aplicacion.game.components.Sprite;
import aplicacion.game.entities.*;
import aplicacion.game.enums.BallSpeed;
import aplicacion.game.enums.FieldSide;
import aplicacion.game.utils.Vector2;

import java.util.HashMap;


public class EntitySpawner {

    private final int gameSize = 800;

    private final HashMap<String, Properties> entityProperties = new HashMap<>();

    private Properties fProps; //Field properties
    private Properties cTopProps; //Top player properties
    private Properties cBotProps; //Bot player properties
    private Properties bProps; //Ball properties

    public EntitySpawner() {
        createPropertyObjects();
        calculateProperties();
    }

    public void SpawnObjects() {
        Entity.removeAll();

        new Field("FIELD",
                fProps.xPosition,
                fProps.yPosition,
                fProps.width,
                fProps.height);

        Player p1 = new Player("PLAYER_TOP",
                cTopProps.xPosition,
                cTopProps.yPosition,
                cTopProps.dimension,
                cTopProps.dimension,
                FieldSide.TOP);
        p1.addComponent(new RectangleCollider(p1,
                new Vector2(cTopProps.colOffsetX, cTopProps.colOffsetY),
                new Vector2(cTopProps.colWidth, cTopProps.colHeight)));

        Player p2 = new Player("PLAYER_BOTTOM",
                cBotProps.xPosition,
                cBotProps.yPosition,
                cBotProps.dimension,
                cBotProps.dimension,
                FieldSide.BOTTOM);
        p2.addComponent(new RectangleCollider(p2,
                new Vector2(cBotProps.colOffsetX, cBotProps.colOffsetY),
                new Vector2(cBotProps.colWidth, cBotProps.colHeight)));

        Ball ball = new Ball("BALL",
                bProps.xPosition,
                bProps.yPosition,
                bProps.dimension,
                bProps.dimension,
                BallSpeed.SLOW);
        ball.addComponent(new RectangleCollider(ball));
        ball.addComponent(new Sprite("circle-test"));

        new ScoreBoard("SCORE_BOARD",
                0,
                0,
                0,
                0);
    }

    private void createPropertyObjects() {
        entityProperties.put("FIELD", new Properties());
        entityProperties.put("PLAYER_TOP", new Properties());
        entityProperties.put("PLAYER_BOTTOM", new Properties());
        entityProperties.put("BALL", new Properties());
    }

    private void calculateProperties() {
        //Field
        float fieldHeightPercentage = 0.8f;
        fProps = entityProperties.get("FIELD");
        fProps.height = gameSize * fieldHeightPercentage;
        fProps.width = fProps.height * 0.726f;
        fProps.xPosition = gameSize / 2f - fProps.width / 2f;
        fProps.yPosition = gameSize * ((1 - fieldHeightPercentage) / 2f);

        //Player
        float characterOffset = 0.02f;
        cTopProps = entityProperties.get("PLAYER_TOP");
        cBotProps = entityProperties.get("PLAYER_BOTTOM");
        //Player top
        cTopProps.dimension = fProps.width * 0.2113f;
        cTopProps.xPosition = fProps.xPosition + fProps.width / 2f - cTopProps.dimension / 2f;
        cTopProps.yPosition = fProps.yPosition - fProps.height * characterOffset;
        cTopProps.colOffsetY = cTopProps.dimension * 0.272f;
        cTopProps.colWidth = cTopProps.dimension;
        cTopProps.colHeight = cTopProps.colWidth * 0.455f;
        //Player bottom
        cBotProps.dimension = cTopProps.dimension;
        cBotProps.xPosition = cTopProps.xPosition;
        cBotProps.yPosition = fProps.yPosition + fProps.height - cBotProps.dimension - fProps.height * characterOffset;
        cBotProps.colOffsetY = cTopProps.colOffsetY;
        cBotProps.colWidth = cTopProps.colWidth;
        cBotProps.colHeight = cTopProps.colHeight;

        //Ball
        bProps = entityProperties.get("BALL");
        bProps.dimension = fProps.width * 0.0566f;
        bProps.xPosition = fProps.xPosition + fProps.width / 2f - bProps.dimension / 2f;
        bProps.yPosition = fProps.yPosition + fProps.height / 2f - bProps.dimension / 2f;
    }

    private class Properties {
        //position
        private float xPosition;
        private float yPosition;
        //size
        private float dimension;
        private float width;
        private float height;
        //ColliderOffset
        private float colOffsetX;
        private float colOffsetY;
        //ColliderSize
        private float colWidth;
        private float colHeight;
    }
}


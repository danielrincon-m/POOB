package aplicacion.game.entities.spawner;

import aplicacion.GameProperties;
import aplicacion.game.components.RectangleCollider;
import aplicacion.game.components.Sprite;
import aplicacion.game.entities.Ball;
import aplicacion.game.entities.Entity;
import aplicacion.game.entities.Field;
import aplicacion.game.entities.ScoreBoard;
import aplicacion.game.enums.FieldSide;

import java.util.HashMap;


public class EntitySpawner {

    private final int gameSize = 800;

    private final HashMap<String, Properties> entityProperties = new HashMap<>();

    private GameProperties gameProperties;
    private Properties fProps; //Field properties
    private Properties cTopProps; //Top player properties
    private Properties cBotProps; //Bot player properties
    private Properties bProps; //Ball properties

    public EntitySpawner(GameProperties gameProperties) {
        this.gameProperties = gameProperties;
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

        new PlayerBuilder(gameProperties,
                "PLAYER_TOP",
                cTopProps,
                FieldSide.TOP);
/*        Player p1 = new Player("PLAYER_TOP",
                cTopProps.xPosition,
                cTopProps.yPosition,
                cTopProps.dimension,
                cTopProps.dimension,
                FieldSide.TOP);
        p1.addComponent(new RectangleCollider(p1,
                new Vector2(cTopProps.colOffsetX, cTopProps.colOffsetY),
                new Vector2(cTopProps.colWidth, cTopProps.colHeight)));*/

        new PlayerBuilder(gameProperties,
                "PLAYER_BOTTOM",
                cBotProps,
                FieldSide.BOTTOM);
/*        Player p2 = new Player("PLAYER_BOTTOM",
                cBotProps.xPosition,
                cBotProps.yPosition,
                cBotProps.dimension,
                cBotProps.dimension,
                FieldSide.BOTTOM);
        p2.addComponent(new RectangleCollider(p2,
                new Vector2(cBotProps.colOffsetX, cBotProps.colOffsetY),
                new Vector2(cBotProps.colWidth, cBotProps.colHeight)));*/

        Ball ball = new Ball("BALL",
                bProps.xPosition,
                bProps.yPosition,
                bProps.dimension,
                bProps.dimension,
                gameProperties.getSelectedBallType());
        ball.addComponent(new RectangleCollider(ball));
        ball.addComponent(new Sprite(gameProperties.getSelectedBallType().spritePath()));

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

    public static class Properties {
        //position
        public float xPosition;
        public float yPosition;
        //size
        public float dimension;
        public float width;
        public float height;
        //ColliderOffset
        public float colOffsetX;
        public float colOffsetY;
        //ColliderSize
        public float colWidth;
        public float colHeight;
    }
}


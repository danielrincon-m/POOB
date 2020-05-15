package aplicacion.game.entitiy;

import aplicacion.ApplicationManager;
import aplicacion.GameProperties;
import aplicacion.game.components.ball.BallMovement;
import aplicacion.game.components.common.RectangleCollider;
import aplicacion.game.components.common.Sprite;
import aplicacion.game.components.field.FieldBounds;
import aplicacion.game.components.scoreBoard.Score;
import aplicacion.game.components.surprises.SurpriseManager;
import aplicacion.game.components.target.TargetController;
import aplicacion.game.enums.FieldSide;

import java.util.HashMap;


public class EntitySpawner {

    private final int gameSize = 800;
    private final float fieldHeightPercentage = 0.8f;

    private final HashMap<String, Properties> entityProperties = new HashMap<>();

    private ApplicationManager applicationManager;
    private GameProperties gameProperties;
    private Properties fProps; //Field properties
    private Properties cTopProps; //Top player properties
    private Properties cBotProps; //Bot player properties
    private Properties bProps; //Ball properties

    public EntitySpawner(ApplicationManager applicationManager) {
        this.applicationManager = applicationManager;
        this.gameProperties = applicationManager.getGameProperties();
        createPropertyObjects();
        calculateProperties();
    }

    public void SpawnObjects() {
        Entity.removeAll();


        Entity field = new Entity(applicationManager,
                "FIELD",
                fProps.xPosition,
                fProps.yPosition,
                fProps.width,
                fProps.height);
        field.addComponent(new FieldBounds(field, fieldHeightPercentage));
        field.addComponent(new Sprite(field, "resources/fondotablero.png", 0));
        Entity.registerEntity(field);


        new PlayerBuilder(applicationManager,
                "PLAYER_TOP",
                cTopProps,
                FieldSide.TOP,
                1);


        new PlayerBuilder(applicationManager,
                "PLAYER_BOTTOM",
                cBotProps,
                FieldSide.BOTTOM,
                3);


        Entity ball = new Entity(applicationManager,
                "BALL",
                bProps.xPosition,
                bProps.yPosition,
                bProps.dimension,
                bProps.dimension);
        ball.addComponent(new RectangleCollider(ball));
        ball.addComponent(new Sprite(ball, gameProperties.getSelectedBallType().spritePath(), 2));
        ball.addComponent(new BallMovement(ball, gameProperties.getSelectedBallType()));
        Entity.registerEntity(ball);


        Entity sb = new Entity(applicationManager, "SCORE_BOARD");
        sb.addComponent(new Score(sb));
        Entity.registerEntity(sb);


        Entity targetController = new Entity(applicationManager, "TARGET_CONTROLLER");
        targetController.addComponent(new TargetController(targetController, gameProperties.getMaxScore()));
        Entity.registerEntity(targetController);


        Entity supriseManager = new Entity(applicationManager, "SURPRISE_MANAGER");
        supriseManager.addComponent(new SurpriseManager(supriseManager));
        Entity.registerEntity(supriseManager);
    }

    private void createPropertyObjects() {
        entityProperties.put("FIELD", new Properties());
        entityProperties.put("PLAYER_TOP", new Properties());
        entityProperties.put("PLAYER_BOTTOM", new Properties());
        entityProperties.put("BALL", new Properties());
    }

    private void calculateProperties() {
        //Field
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
        cTopProps.colOffsetY = cTopProps.dimension * 0.35f;
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


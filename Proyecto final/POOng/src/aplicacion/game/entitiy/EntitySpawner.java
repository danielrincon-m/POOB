package aplicacion.game.entitiy;

import aplicacion.ApplicationManager;
import aplicacion.GameProperties;
import aplicacion.game.components.ball.BallMovement;
import aplicacion.game.components.common.RectangleCollider;
import aplicacion.game.components.common.Sprite;
import aplicacion.game.components.common.Transform;
import aplicacion.game.components.field.FieldBounds;
import aplicacion.game.components.scoreBoard.Score;
import aplicacion.game.components.surprises.SurpriseManager;
import aplicacion.game.components.target.TargetController;
import aplicacion.game.enums.FieldSide;
import aplicacion.game.utils.Vector2;

import java.io.Serializable;
import java.util.HashMap;


public class EntitySpawner {

    private final int gameSize = 800;
    private final float fieldHeightPercentage = 0.8f;

    private final HashMap<String, Properties> entityProperties = new HashMap<>();

    private final ApplicationManager applicationManager;
    private final EntityManager entityManager;
    private final GameProperties gameProperties;
    private Properties fProps; //Field properties
    private Properties cTopProps; //Top player properties
    private Properties cBotProps; //Bot player properties
    private Properties bProps; //Ball properties

    /**
     * @param applicationManager El application manager del juego
     */
    public EntitySpawner(ApplicationManager applicationManager, EntityManager entityManager) {
        this.applicationManager = applicationManager;
        this.entityManager = entityManager;
        gameProperties = applicationManager.getGameProperties();
        createPropertyObjects();
        calculateProperties();
    }

    /**
     * Genera todas las entidades que inicialmente tienen que existir en el juego
     */
    public void SpawnObjects() {
        entityManager.removeAll();

        Entity field = new Entity("FIELD", entityManager);
        field.addComponent(new Transform(field,
                new Vector2(fProps.xPosition, fProps.yPosition),
                new Vector2(fProps.width, fProps.height)));
        field.addComponent(new FieldBounds(field, fieldHeightPercentage));
        field.addComponent(new Sprite(field, "resources/fondotablero.png", 0));
        entityManager.registerEntity(field);


        new PlayerBuilder(applicationManager,
                entityManager,
                "PLAYER_TOP",
                cTopProps,
                FieldSide.TOP,
                1);


        new PlayerBuilder(applicationManager,
                entityManager,
                "PLAYER_BOTTOM",
                cBotProps,
                FieldSide.BOTTOM,
                3);


        Entity ball = new Entity("BALL", entityManager);
        ball.addComponent(new Transform(ball,
                new Vector2(bProps.xPosition, bProps.yPosition),
                new Vector2(bProps.dimension, bProps.dimension)));
        ball.addComponent(new RectangleCollider(ball));
        ball.addComponent(new Sprite(ball, gameProperties.getSelectedBallType().spritePath(), 2));
        ball.addComponent(new BallMovement(ball, gameProperties.getSelectedBallType()));
        entityManager.registerEntity(ball);


        Entity sb = new Entity("SCORE_BOARD", entityManager);
        sb.addComponent(new Transform(sb));
        sb.addComponent(new Score(sb));
        entityManager.registerEntity(sb);


        Entity targetController = new Entity("TARGET_CONTROLLER", entityManager);
        targetController.addComponent(new Transform(targetController));
        targetController.addComponent(new TargetController(targetController, gameProperties.getMaxScore()));
        entityManager.registerEntity(targetController);


        Entity supriseManager = new Entity("SURPRISE_MANAGER", entityManager);
        supriseManager.addComponent(new Transform(supriseManager));
        supriseManager.addComponent(new SurpriseManager(supriseManager));
        entityManager.registerEntity(supriseManager);


/*        Entity gamePause = new Entity(applicationManager, "GAME_PAUSE");
        gamePause.addComponent(new Pause(gamePause));
        entityManager.registerEntity(gamePause);*/
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

    public static class Properties implements Serializable {
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


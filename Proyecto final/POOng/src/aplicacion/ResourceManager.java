package aplicacion;

import aplicacion.exception.ApplicationException;
import aplicacion.game.enums.BallType;
import aplicacion.game.enums.CharacterPersonality;
import aplicacion.game.enums.CharacterType;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ResourceManager {

    HashMap<String, BufferedImage> sprites = new HashMap<>();
    HashMap<CharacterPersonality, BufferedImage> playerImages = new HashMap<>();
    HashMap<CharacterPersonality, BufferedImage> machineImages = new HashMap<>();
    HashMap<BallType, BufferedImage> ballImages = new HashMap<>();

    ApplicationManager applicationManager;

    public ResourceManager(ApplicationManager applicationManager) {
        this.applicationManager = applicationManager;

        loadSprites();
        loadPlayerCharacters();
        loadMachineCharacters();
        loadBalls();
    }

    public HashMap<CharacterPersonality, BufferedImage> getAvailablePlayerImages() {
        HashMap<CharacterPersonality, BufferedImage> playerImagesCopy = new HashMap<>(playerImages);
        GameProperties gp = applicationManager.getGameProperties();
        CharacterPersonality[] selectedCharacters = gp.getSelectedCharacters();

        for (CharacterPersonality cp : selectedCharacters) {
            if (cp != null) {
                playerImagesCopy.remove(cp);
            }
        }
        return playerImagesCopy;
    }

    public BufferedImage getSprite(String name) {
        if (sprites.containsKey(name)) {
            return sprites.get(name);
        }else {
            System.out.println(name + " not found.");
        }
        return null;
    }

    public BufferedImage getPlayerImage(CharacterPersonality playerCharacter) {
        if (!playerCharacter.getType().equals(CharacterType.HUMAN)) {
            throw new ApplicationException(ApplicationException.NOT_A_CHARACTER);
        }
        return playerImages.get(playerCharacter);
    }

    public BufferedImage getMachineImage(CharacterPersonality machineCharacter) {
        if (!machineCharacter.getType().equals(CharacterType.MACHINE)) {
            throw new ApplicationException(ApplicationException.NOT_A_MACHINE);
        }
        return machineImages.get(machineCharacter);
    }

    public BufferedImage getBallImage(BallType type) {
        return ballImages.get(type);
    }

    private void loadSprites() {
        try {
            Stream<Path> walk = Files.walk(Paths.get("resources")).filter(Files::isRegularFile);
            List<String> paths = walk.map(Path::toString)
                    .filter(f -> f.endsWith(".png")).collect(Collectors.toList());
            for (String path : paths) {
                path = path.replace("\\", "/");
                BufferedImage sprite = ImageIO.read(new File(path));
                //System.out.println(path);
                sprites.put(path, sprite);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


/*        try {
            File[] files = new File("resources/sprites").listFiles();
            if (files != null) {
                for (File file : files) {
                    BufferedImage sprite = ImageIO.read(file);
                    System.out.println(file.getName());
                    sprites.put(file.getName(), sprite);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    private void loadPlayerCharacters() throws ApplicationException {
        for (CharacterPersonality playerCharacter : CharacterPersonality.values()) {
            if (playerCharacter.getType().equals(CharacterType.HUMAN)) {
                try {
                    BufferedImage playerImage = ImageIO.read(new File(playerCharacter.spriteName()));
                    playerImages.put(playerCharacter, playerImage);
                } catch (IOException e) {
                    throw new ApplicationException(ApplicationException.PROBLEM_LOADING_RESOURCE);
                }
            }
        }
    }

    private void loadMachineCharacters() throws ApplicationException {
        for (CharacterPersonality machineCharacter : CharacterPersonality.values()) {
            if (machineCharacter.getType().equals(CharacterType.MACHINE)) {
                try {
                    BufferedImage machineImage = ImageIO.read(new File(machineCharacter.spriteName()));
                    machineImages.put(machineCharacter, machineImage);
                } catch (IOException e) {
                    throw new ApplicationException(ApplicationException.PROBLEM_LOADING_RESOURCE);
                }
            }
        }
    }

    private void loadBalls() throws ApplicationException {
        for (BallType type : BallType.values()) {
            try {
                BufferedImage ballImage = ImageIO.read(new File(type.spritePath()));
                ballImages.put(type, ballImage);
            } catch (IOException e) {
                throw new ApplicationException(ApplicationException.PROBLEM_LOADING_RESOURCE);
            }
        }
    }
}

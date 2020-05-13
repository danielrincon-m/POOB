package aplicacion;

import aplicacion.exception.ApplicationException;
import aplicacion.game.enums.BallType;
import aplicacion.game.enums.CharacterPersonality;
import aplicacion.game.enums.CharacterType;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class ResourceManager {

    HashMap<CharacterPersonality, BufferedImage> playerImages = new HashMap<>();
    HashMap<CharacterPersonality, BufferedImage> machineImages = new HashMap<>();
    HashMap<BallType, BufferedImage> ballImages = new HashMap<>();

    ApplicationManager applicationManager;

    public ResourceManager(ApplicationManager applicationManager) {
        this.applicationManager = applicationManager;

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

    private void loadPlayerCharacters() throws ApplicationException {
        for (CharacterPersonality playerCharacter : CharacterPersonality.values()) {
            if (playerCharacter.getType().equals(CharacterType.HUMAN)) {
                try {
                    BufferedImage playerImage = ImageIO.read(getClass().getResource(playerCharacter.spritePath()));
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
                    BufferedImage machineImage = ImageIO.read(getClass().getResource(machineCharacter.spritePath()));
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
                BufferedImage ballImage = ImageIO.read(getClass().getResource(type.spritePath()));
                ballImages.put(type, ballImage);
            } catch (IOException e) {
                throw new ApplicationException(ApplicationException.PROBLEM_LOADING_RESOURCE);
            }
        }
    }
}

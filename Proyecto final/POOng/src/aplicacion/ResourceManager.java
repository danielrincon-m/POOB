package aplicacion;

import aplicacion.exception.ApplicationException;
import aplicacion.game.enums.BallType;
import aplicacion.game.enums.Characters;
import aplicacion.game.enums.CharacterType;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class ResourceManager {

    HashMap<Characters, BufferedImage> playerImages = new HashMap<>();
    HashMap<Characters, BufferedImage> machineImages = new HashMap<>();
    HashMap<BallType, BufferedImage> ballImages = new HashMap<>();

    public ResourceManager() {
        loadPlayerCharacters();
        loadMachineCharacters();
        loadBalls();
    }

    /*
    TODO: implementar esta shit, las imágenes que se quitan son las imagenes que se hayan seleccionado
     en game properties
    */
    public HashMap<CharacterType, BufferedImage> getAvailablePlayerImages() {
        return null;
    }

    public BufferedImage getMachineImage(Characters machineCharacters) {
        if (!machineCharacters.getType().equals(CharacterType.MACHINE)) {
            throw new ApplicationException(ApplicationException.NOT_A_MACHINE);
        }
        return machineImages.get(machineCharacters);
    }

    public BufferedImage getBallImage(BallType type) {
        return ballImages.get(type);
    }

    private void loadPlayerCharacters() throws ApplicationException {
        for (Characters playerCharacters : Characters.values()) {
            if (playerCharacters.getType().equals(CharacterType.HUMAN)) {
                try {
                    BufferedImage playerImage = ImageIO.read(new File(playerCharacters.spritePath()));
                    playerImages.put(playerCharacters, playerImage);
                } catch (IOException e) {
                    throw new ApplicationException(ApplicationException.PROBLEM_LOADING_RESOURCE);
                }
            }
        }
    }

    private void loadMachineCharacters() throws ApplicationException {
        for (Characters machineCharacters : Characters.values()) {
            if (machineCharacters.getType().equals(CharacterType.MACHINE)) {
                try {
                    BufferedImage machineImage = ImageIO.read(new File(machineCharacters.spritePath()));
                    machineImages.put(machineCharacters, machineImage);
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

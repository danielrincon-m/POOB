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
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ResourceManager {

    private transient HashMap<String, BufferedImage> sprites = new HashMap<>();

    /**
     * Clase encargada de administrar y almacenar los recursos del juego
     */
    public ResourceManager() {
        loadSprites();
    }

    /**
     * Obtiene una BufferedImage, de una imagen que se encuentra en un path especificado
     * @param path El path de la imagen
     * @return Un objeto BufferedImage, que contiene la imagen en el path
     */
    public BufferedImage getSprite(String path) {
        if (sprites.containsKey(path)) {
            return sprites.get(path);
        }else {
            System.out.println(path + " not found.");
        }
        return null;
    }

    /**
     * Retorna la imagen de cierto jugador, debe estar registrado en CharacterPersonality
     * @param playerCharacter Referencia al caracter del cual se quiere obtener la imagen
     * @return El objeto BufferedImage correspondiente a la imagen del caracter
     */
    public BufferedImage getPlayerImage(CharacterPersonality playerCharacter) {
        if (!playerCharacter.getType().equals(CharacterType.HUMAN)) {
            throw new ApplicationException(ApplicationException.NOT_A_CHARACTER);
        }
        return sprites.get(playerCharacter.spritePath());
    }

    /**
     * Retorna la imagen de cierta máquina, debe estar registrada en CharacterPersonality
     * @param machineCharacter Referencia a la máquina de la cual se quiere obtener la imagen
     * @return El objeto BufferedImage correspondiente a la imagen de la máquina
     */
    public BufferedImage getMachineImage(CharacterPersonality machineCharacter) {
        if (!machineCharacter.getType().equals(CharacterType.MACHINE)) {
            throw new ApplicationException(ApplicationException.NOT_A_MACHINE);
        }
        return sprites.get(machineCharacter.spritePath());
    }

    /**
     * Retorna la imágen de la bola correspondiente a cierto tipo
     * @param type El tipo de la bola
     * @return El objeto BufferedImage correspondiente a la imagen de la bola
     */
    public BufferedImage getBallImage(BallType type) {
        return sprites.get(type.spritePath());
    }

    /**
     * Carga todas las imágenes en la carpeta "resources" y en sus subcarpetas a memora, para acceso rápido.
     */
    private void loadSprites() {
        try {
            Stream<Path> walk = Files.walk(Paths.get("resources")).filter(Files::isRegularFile);
            List<String> paths = walk.map(Path::toString)
                    .filter(f -> f.endsWith(".png")).collect(Collectors.toList());
            for (String path : paths) {
                path = path.replace("\\", "/");
                BufferedImage sprite = ImageIO.read(new File(path));
                sprites.put(path, sprite);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

/*    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        sprites = new HashMap<>();
        loadSprites();
    }*/
}

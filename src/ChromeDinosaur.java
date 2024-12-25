import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class ChromeDinosaur extends JPanel {
    int boardHeight= 250;
    int boardWidth=750;


//  images of the dinosaur in different state

    Image dinosaurImg;
    Image dinosaurDeadImg;
    Image dinosaurJumpImg;


    // cactus images of different size

    Image cactus1Img;
    Image cactus2Img;
    Image cactus3Img;


    // Dinosaur width and height and other properties
    int dinosaurWidth =88;
    int dinosaurHeight=94;
    int dinosaurX= 50;
    int dinosaurY= boardHeight- dinosaurHeight;


    public ChromeDinosaur(){
        // Layout and colour of the panel
        setPreferredSize( new Dimension(boardWidth,boardHeight));
        setBackground(Color.LIGHT_GRAY);

        // Loading all the image in a Object of ImageIcon

        dinosaurImg = new ImageIcon(Objects.requireNonNull(getClass().getResource("./img/dino-run.gif"))).getImage();
        dinosaurDeadImg = new ImageIcon(Objects.requireNonNull(getClass().getResource("./img/dino-dead.png"))).getImage();
        dinosaurJumpImg = new ImageIcon(Objects.requireNonNull(getClass().getResource("./img/dino-jump.png"))).getImage();
        cactus1Img = new ImageIcon(Objects.requireNonNull(getClass().getResource("./img/cactus1.png"))).getImage();
        cactus2Img = new ImageIcon(Objects.requireNonNull(getClass().getResource("./img/cactus2.png"))).getImage();
        cactus3Img = new ImageIcon(Objects.requireNonNull(getClass().getResource("./img/cactus3.png"))).getImage();


    }
}

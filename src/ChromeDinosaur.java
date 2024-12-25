import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Objects;

public class ChromeDinosaur extends JPanel implements ActionListener , KeyListener {
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

    Block dinosaur;

    // implementing the game physics
    int velocityY=0; // dinosaur jump speed
    // Adding gravity so it can not always go up
    int gravity= 1;

    // creating a loop which render the image per second
    Timer gameLoop;


    public ChromeDinosaur(){
        // Layout and colour of the panel
        setPreferredSize( new Dimension(boardWidth,boardHeight));
        setBackground(Color.LIGHT_GRAY);
        setFocusable(true);
        addKeyListener(this);

        // Loading all the image in a Object of ImageIcon

        dinosaurImg = new ImageIcon(Objects.requireNonNull(getClass().getResource("./img/dino-run.gif"))).getImage();
        dinosaurDeadImg = new ImageIcon(Objects.requireNonNull(getClass().getResource("./img/dino-dead.png"))).getImage();
        dinosaurJumpImg = new ImageIcon(Objects.requireNonNull(getClass().getResource("./img/dino-jump.png"))).getImage();
        cactus1Img = new ImageIcon(Objects.requireNonNull(getClass().getResource("./img/cactus1.png"))).getImage();
        cactus2Img = new ImageIcon(Objects.requireNonNull(getClass().getResource("./img/cactus2.png"))).getImage();
        cactus3Img = new ImageIcon(Objects.requireNonNull(getClass().getResource("./img/cactus3.png"))).getImage();


        // calling the block
        dinosaur= new Block(dinosaurX,dinosaurY,dinosaurWidth,dinosaurHeight,dinosaurImg);

        gameLoop= new Timer(1000/60,this);   // 1000/60= 60 frames per second
        gameLoop.start();

    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void move(){
        velocityY+= gravity;
        dinosaur.y += velocityY;

        if(dinosaur.y>dinosaurY){
            dinosaur.y=dinosaurY;
            velocityY=0;
        }
    }

    public void draw(Graphics g){
        g.drawImage(dinosaur.img,dinosaur.x,dinosaur.y,dinosaur.width,dinosaur.height,null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_SPACE){
            if(dinosaur.y==dinosaurY){
                velocityY =- 17;
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}



    @Override
    public void keyReleased(KeyEvent e) {}
}

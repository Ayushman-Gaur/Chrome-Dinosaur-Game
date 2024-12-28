import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
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


    // Dimension like height and width of the cactus
    int cactus1Width= 34;
    int cactus2Width= 69;
    int cactus3Width= 102;

    int cactusHeight = 70;
    int cactusX= 700; // we only made cactus for x cause it will going to move in X-axis not like dino it has to jump in Y direction
    int cactusY= boardHeight-cactusHeight;
    ArrayList<Block> cactusArray;

    // implementing the game physics
    int velocityY=0; // dinosaur jump speed
    // Adding gravity so it can not always go up
    int gravity= 1;
    // implementing the movement for the cactus in X direction
    int velocityX = -12;

    // creating a loop which render the image per second
    Timer gameLoop;
    // Timer of cactus to be appeared
    Timer placeCactusTimer;
    // This is for when cactus hit dino and game has to be stop
    boolean gameOver = false;
    // This is for adding the score
    int score= 0;


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
        cactusArray= new ArrayList<Block>();

        gameLoop= new Timer(1000/60,this);   // 1000/60= 60 frames per second
        gameLoop.start();

        placeCactusTimer= new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placeCactus();
            }
        });


        placeCactusTimer.start();
    }

    // This method is for detecting collision of dino and cactus
    boolean collision(Block a, Block b){
        return a.x<b.x + b.width &&
                a.x + a.width > b.x &&
                a.y< b.y+b.height &&
                a.y +a.height > b.y;
    }





    // This is the method for placing the cactus at random time

    public void placeCactus(){
        if(gameOver){
            return;
        }

        double placeCactusChance = Math.random();
        if(placeCactusChance >.90){
            Block cactus = new Block(cactusX,cactusY,cactus3Width,cactusHeight,cactus3Img);
            cactusArray.add(cactus);
        } else if (placeCactusChance >.70) {
            Block cactus = new Block(cactusX,cactusY,cactus2Width,cactusHeight,cactus2Img);
            cactusArray.add(cactus);
        } else if (placeCactusChance >.50) {
            Block cactus = new Block(cactusX,cactusY,cactus1Width,cactusHeight,cactus1Img);
            cactusArray.add(cactus);
        }


        if(cactusArray.size() > 10){
            cactusArray.remove(0);
        }
    }



    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void move(){

        velocityY+= gravity;// This is for stopping from infinite jump
        dinosaur.y += velocityY; //This is for dinosaur for not going beyond ground

        // This condition check for dino jump and changing its image
        if(dinosaur.y>dinosaurY){
            dinosaur.y=dinosaurY;
            velocityY=0;
            dinosaur.img = dinosaurImg;
        }

        // This is for cactus to move toward the direction of dino

        for(Block cactus : cactusArray){
            cactus.x += velocityX;
            if(collision(dinosaur,cactus)){
                gameOver= true;
                dinosaur.img=dinosaurDeadImg;
            }
        }
        score++;
    }

    public void draw(Graphics g){
        g.drawImage(dinosaur.img,dinosaur.x,dinosaur.y,dinosaur.width,dinosaur.height,null);

        for (Block cactus : cactusArray) {
            g.drawImage(cactus.img, cactus.x, cactus.y, cactus.width, cactus.height, null);
        }

        // for score
        g.setColor(Color.black);
        g.setFont(new Font("Couries",Font.PLAIN,32));
        if(gameOver){
            g.drawString("Game Over"+ String.valueOf(score),10,35);
        }else{
            g.drawString(String.valueOf(score),10,35);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
        if(gameOver){
            placeCactusTimer.stop();
            gameLoop.stop();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_SPACE){
            if(dinosaur.y==dinosaurY){
                velocityY =- 17;
                dinosaur.img=dinosaurJumpImg;
            }
        }

        // For restart the game
        if(gameOver){
            dinosaur.y=dinosaurY;
            dinosaur.img=dinosaurImg;
            velocityY=0;
            cactusArray.clear();
            score=0;
            gameOver=false;
            gameLoop.start();
            placeCactusTimer.start();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}



    @Override
    public void keyReleased(KeyEvent e) {}
}

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        int boardHeight= 250;
        int boardWidth=750;

        JFrame frame = new JFrame("Chrome Dinosaur");
        frame.setVisible(true);
        frame.setSize(boardWidth,boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        ChromeDinosaur chromeDinosaur = new ChromeDinosaur();
        frame.add(chromeDinosaur);
        frame.pack();
        chromeDinosaur.requestFocus();
        frame.setVisible(true);
    }
}
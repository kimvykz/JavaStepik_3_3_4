import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Program extends JPanel implements KeyListener {
    private BufferedImage image;
    private int x = 0;
    private int y = 0;
    private final int width = 50;
    private final int height = 50;
    private final int moveAmount = 50;

    public Program() {
        // загружаем картинку в объект BufferedImage
        try {
            image = ImageIO.read(new File("card.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // устанавливаем размеры JPanel и добавляем KeyListener
        setPreferredSize(new Dimension(500, 500));
        setFocusable(true);
        addKeyListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, x, y, width, height, null);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        // изменяем координаты объекта в зависимости от нажатой клавиши
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                if (x - moveAmount >= 0) {
                    x -= moveAmount;
                    repaint();
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (x + moveAmount + width <= getWidth()) {
                    x += moveAmount;
                    repaint();
                }
                break;
            case KeyEvent.VK_UP:
                if (y - moveAmount >= 0) {
                    y -= moveAmount;
                    repaint();
                }
                break;
            case KeyEvent.VK_DOWN:
                if (y + moveAmount + height <= getHeight()) {
                    y += moveAmount;
                    repaint();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // не используется
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // не используется
    }


    public static void main(String[] args) {

        JFrame frame = new JFrame("Moving Object");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new Program());
        frame.pack();
        frame.setVisible(true);

    }
}

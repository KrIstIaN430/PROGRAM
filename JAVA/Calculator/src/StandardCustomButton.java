import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.net.URL;

class StandardCustomButton extends JButton implements MouseListener{

    private String name;
    private boolean mouseEntered = false;
    private boolean mousePressed = false;
    private Font robotoFont;

    StandardCustomButton(String name){
        super();
        this.name = name;
        enableInputMethods(true);
        addMouseListener(this);
        //Dimension size = new Dimension(73, 62);
        //setSize(size.width, size.height);
        setFocusable(true);

        try {
            robotoFont = Font.createFont(Font.TRUETYPE_FONT,
                    new File(getClass().getResource("Roboto-Regular.ttf").toURI()))
                    .deriveFont(Font.PLAIN, 25);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // turn on anti-alias mode
        Graphics2D antiAlias = (Graphics2D)g;
        antiAlias.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        // draw white rectangle
        if(mouseEntered && !mousePressed)
            g.setColor(Color.decode("#0FBCFE"));
        else
            g.setColor(Color.decode("#00ADEF"));

        g.fillRect(0, 0, getWidth(), getHeight());

        if(mousePressed) {
            g.setColor(Color.decode("#0089D1"));
            g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
            g.drawRect(1, 1, getWidth() - 3, getHeight() - 3);
            g.drawRect(2, 2, getWidth() - 5, getHeight() - 5);
        }
        g.setColor(Color.WHITE);
        drawStringCenter(g, name);
    }

    private void drawStringCenter(Graphics g, String text){
        String[] split = text.split("\n");
        Font font = robotoFont.deriveFont(Font.PLAIN, (float) (25 - (7 * split.length) + (5 * getHeight() / 40.0)));
        FontMetrics fm = g.getFontMetrics(font);
        g.setFont(font);
        for (int i = 1; i <= split.length; i++) {
            int centerWidth = (getWidth() - fm.stringWidth(split[i - 1])) / 2;
            int centerHeight = ((getHeight() - (fm.getHeight() * split.length)) / 2)
                    + fm.getAscent() * i;
            g.drawString(split[i - 1], centerWidth, centerHeight);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mousePressed = true;
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mousePressed = false;
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        mouseEntered = true;
        repaint();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        mouseEntered = false;
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        repaint();
    }
}

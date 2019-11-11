import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.net.URL;
import java.util.Iterator;

class standardCustomButtons extends JButton implements MouseListener{

    private String name;
    private String res;
    private boolean mouseEntered = false;
    private boolean mousePressed = false;
    private Dimension size = new Dimension(73,62);
    private Font googleFont;
    private Font robotoFont;


    standardCustomButtons(String name){
        super();
        this.name = name;
        enableInputMethods(true);
        addMouseListener(this);
        setSize(size.width, size.height);
        setFocusable(true);
        try {
            googleFont = getFont("GoogleSans-Regular.ttf").deriveFont(Font.BOLD, 10);
            robotoFont = getFont("Roboto-Regular.ttf").deriveFont(Font.PLAIN, 25);
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
            //g.fillRect(0,0, getWidth(), 3);
            //g.fillRect(0,0, 3, getHeight());
            g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
            g.drawRect(1, 1, getWidth() - 3, getHeight() - 3);
            g.drawRect(2, 2, getWidth() - 5, getHeight() - 5);
            //g.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
            //g.drawLine(0,0 , getWidth(), 0);
            //g.drawLine(0,0 , 0, getHeight());
        }
        //g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arc.width, arc.height);


        g.setColor(Color.WHITE);
        FontMetrics fontMetrics = g.getFontMetrics(robotoFont);
        int widthCenter = (getWidth() - fontMetrics.stringWidth(name)) / 2;
        int heightCenter = ((getHeight() - fontMetrics.getHeight()) / 2) + fontMetrics.getAscent();
        //drawString(g, robotoFont, name, widthCenter, heightCenter);
        //g.drawString(name, widthCenter, heightCenter);

        /*
        int height = fontMetrics.getHeight();
        g.drawString(name, widthCenter, height);
        g.setColor(Color.red);
        g.drawLine(0, height, getWidth(), height);
        g.setColor(Color.BLACK);
        g.drawLine(0, height - fontMetrics.getAscent(), getWidth(), height - fontMetrics.getAscent());

        g.setColor(Color.ORANGE);
        g.drawLine(0, height + fontMetrics.getDescent(), getWidth(), height + fontMetrics.getDescent());
        g.setColor(Color.green);
        //g.drawLine(0, fontMetrics.getHeight(), getWidth(), fontMetrics.getHeight());
        //g.drawString(eq, 20, 20);

         */

        drawStringCenter(g, name);
        //g.setFont(googleFont);
        //fontMetrics = g.getFontMetrics();

        //g.drawString(res, 50 - fontMetrics.stringWidth(res), 30);
        //g.drawString(res, 20, 70);





        //g.drawLine(0,0 , getWidth(), 0);
        //g.drawLine(0,0 , 0, getHeight());
        //g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arc.width, arc.height);
        //g.setColor(Color.decode("#c0c0c0"));
        //g.drawRoundRect(1, 1, getWidth() - 3, getHeight() - 3, arc.width, arc.height);

    }
    private void drawStringCenter(Graphics g, String text){
        String[] split = text.split("\n");
        Font font = robotoFont.deriveFont(Font.PLAIN, 25 - (7 * split.length) + (5 * getHeight() / 40 ));
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
    public Dimension getPreferredSize() {
        return new Dimension(getWidth(), getHeight());
    }

    public Dimension getMinimumSize() {
        return getPreferredSize();
    }
    public Dimension getMaximumSize() {
        return getPreferredSize();
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
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        repaint();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        mouseEntered = false;
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        repaint();
    }
    private Font getFont(String fileName) throws Exception {
        URL url = getClass().getResource(fileName);
        return Font.createFont(Font.TRUETYPE_FONT, new File(url.toURI()));
    }
}

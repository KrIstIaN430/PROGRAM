import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.net.URL;

class historyCustomButton extends JButton implements MouseListener{

    private String eq;
    private String res;
    private boolean mouseEntered = false;
    private boolean mousePressed = false;
    private Font googleFont;
    private Font robotoFont;


    historyCustomButton(String eq, String res){
        super();
        this.eq = eq;
        this.res = res;
        enableInputMethods(true);
        addMouseListener(this);
        Dimension size = new Dimension(380, 100);
        setSize(size.width, size.height);
        setFocusable(true);
        try {
            googleFont = getFont("GoogleSans-Regular.ttf").deriveFont(Font.PLAIN, 40);
            robotoFont = getFont("Roboto-Regular.ttf").deriveFont(Font.PLAIN, 20);
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
        g.setFont(robotoFont);
        FontMetrics fontMetrics = g.getFontMetrics();
        g.drawString(eq, 365 - fontMetrics.stringWidth(eq), 20);


        g.setFont(googleFont);
        fontMetrics = g.getFontMetrics();
        while(fontMetrics.stringWidth(res) >= getWidth() - 12){
            Font font = googleFont;
            font = font.deriveFont((float) (font.getSize() - 1));
            g.setFont(font);
            fontMetrics = g.getFontMetrics();
        }
        g.drawString(res, 365 - fontMetrics.stringWidth(res), 70);
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

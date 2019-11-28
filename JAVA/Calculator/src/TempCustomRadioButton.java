import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TempCustomRadioButton extends JRadioButton implements ItemListener, MouseListener {
    private ImageIcon selectedIcon;
    private ImageIcon deselectedIcon;
    private ImageIcon hoverIcon;
    private ImageIcon resizedSelected;
    private ImageIcon resizedDeselected;
    private ImageIcon resizedHover;
    private int state; //0 = deselected, 1 = selected

    TempCustomRadioButton(ImageIcon deselectedIcon, ImageIcon selectedIcon, ImageIcon hoverIcon) {
        super();
        setPreferredSize(new Dimension(70, 70));
        addItemListener(this);
        setBorder(null);
        addMouseListener(this);
        setIcons(deselectedIcon, selectedIcon, hoverIcon);
        state = 0;
        update();
    }

    private void resizeImages() {
        if (getPreferredSize().height != selectedIcon.getIconHeight()) {
            Image image = selectedIcon.getImage(); // transform it
            Image resizedImage = image.getScaledInstance(getPreferredSize().width, getPreferredSize().height, java.awt.Image.SCALE_SMOOTH);
            resizedSelected = new ImageIcon(resizedImage);
            image = deselectedIcon.getImage(); // transform it
            resizedImage = image.getScaledInstance(getPreferredSize().width, getPreferredSize().height, java.awt.Image.SCALE_SMOOTH);
            resizedDeselected = new ImageIcon(resizedImage);
            image = hoverIcon.getImage(); // transform it
            resizedImage = image.getScaledInstance(getPreferredSize().width, getPreferredSize().height, java.awt.Image.SCALE_SMOOTH);
            resizedHover = new ImageIcon(resizedImage);
        }
    }

    private void setIcons(ImageIcon deselectedIcon, ImageIcon selectedIcon, ImageIcon hoverIcon) {
        this.deselectedIcon = deselectedIcon;
        this.selectedIcon = selectedIcon;
        this.hoverIcon = hoverIcon;
    }

    void update() {
        resizeImages();
        if (isSelected())
            setIcon(resizedSelected);
        else
            setIcon(resizedDeselected);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        update();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(!isSelected())
            setIcon(resizedHover);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(isSelected())
            setIcon(resizedSelected);
        else
            setIcon(resizedDeselected);
    }
}

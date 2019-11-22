import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TempCustomRadioButton extends JRadioButton implements ItemListener, MouseListener {
    private ImageIcon selectedIcon;
    private ImageIcon deselectedIcon;
    private ImageIcon resizedSelected;
    private ImageIcon resizedDeselected;
    private ImageIcon hoverIcon;

    TempCustomRadioButton(ImageIcon deselectedIcon, ImageIcon selectedIcon, ImageIcon hoverIcon) {
        super();
        setPreferredSize(new Dimension(70, 70));
        addItemListener(this);
        setBorder(null);
        addMouseListener(this);
        setIcons(deselectedIcon, selectedIcon, hoverIcon);
        resizeImages();
        setIcon(resizedDeselected);
    }

    private void resizeImages(){
        Image image = selectedIcon.getImage(); // transform it
        Image resizedImage = image.getScaledInstance(getPreferredSize().width, getPreferredSize().height,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        resizedSelected = new ImageIcon(resizedImage);
        image = deselectedIcon.getImage(); // transform it
        resizedImage = image.getScaledInstance(getPreferredSize().width, getPreferredSize().height,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        resizedDeselected = new ImageIcon(resizedImage);
        image = hoverIcon.getImage(); // transform it
        resizedImage = image.getScaledInstance(getPreferredSize().width, getPreferredSize().height,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        hoverIcon = new ImageIcon(resizedImage);

    }

    private void setIcons(ImageIcon deselectedIcon, ImageIcon selectedIcon, ImageIcon hoverIcon) {
        this.deselectedIcon = deselectedIcon;
        this.selectedIcon = selectedIcon;
        this.hoverIcon = hoverIcon;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getStateChange() == ItemEvent.SELECTED)
            setIcon(resizedSelected);
        else if (e.getStateChange() == ItemEvent.DESELECTED)
            setIcon(resizedDeselected);
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
            setIcon(hoverIcon);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(isSelected())
            setIcon(resizedSelected);
        else
            setIcon(resizedDeselected);
    }
}

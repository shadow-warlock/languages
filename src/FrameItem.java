import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public abstract class FrameItem extends JPanel {

    protected Dimension dim;
    protected Font font;

    public FrameItem(Dimension dim, int cols, int rows) {
        super(new GridLayout(cols, rows));
        this.dim = dim;
        font = getFont();
        font = font.deriveFont(30f);
    }

    @Override
    protected void paintComponent(Graphics g) {
        ((Graphics2D)g).setRenderingHint( RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        super.paintComponent(g);
    }
}

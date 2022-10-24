package SwingLines;

import java.awt.*;

class Line {

    int prevX, prevY;
    int x, y;
    Color color;
    int lineWidth;

    public Line(int prevX, int prevY, int x, int y, Color color, int LineWidth) {
        this.prevX = prevX;
        this.prevY = prevY;
        this.x = x;
        this.y = y;
        this.color = color;
        this.lineWidth = LineWidth;
    }

    void draw(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(lineWidth));
        g2.setColor(this.color);
        g2.drawLine(
            prevX,
            prevY,
            x,
            y
        );
    }
}

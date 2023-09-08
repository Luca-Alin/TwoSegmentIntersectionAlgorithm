package tsia;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Frame extends JFrame {
    static final int WIDTH = 1000;
    static final int HEIGHT = 1000;
    public Frame() {

        this.setSize(WIDTH, HEIGHT);
        this.add(new Panel());

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private static class Panel extends JPanel {
        Segment s1;
        Segment s2;
        public Panel() {
            this.setLayout(null);

            JButton button  = new JButton();
            button.addActionListener(a -> generateSegments());
            button.setBounds(25, 25, 25, 25);
            this.add(button);

            generateSegments();
        }
        private void generateSegments() {
            s1 = new Segment(generatePoint(), generatePoint());
            s2 = new Segment(generatePoint(), generatePoint());
            this.repaint();
        }

        private static Point generatePoint() {
            Random random = new Random();
            return new Point(random.nextInt(40, Frame.WIDTH - 40), random.nextInt(40, Frame.HEIGHT - 40));
        }

        @Override
        public void paint(Graphics grp) {
            super.paint(grp);
            Graphics2D g= (Graphics2D) grp;
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g.drawLine(s1.x1, s1.y1, s1.x2, s1.y2);
            g.drawLine(s2.x1, s2.y1, s2.x2, s2.y2);

            if (Calculations.doIntersect(s1, s2)) {
                System.out.println("Do intersect");
                Point p = Calculations.getIntersectionPoint(s1, s2);
                g.setColor(Color.RED);
                g.fillOval(p.x - 5, p.y - 5, 10, 10);
            }
        }
    }
}

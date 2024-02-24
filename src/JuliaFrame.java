import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;


public class JuliaFrame extends JFrame {
	
	
	//new window 
	
    JuliaFrame() {
    	
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(200, 50, 800, 600);
        setTitle("JuliaSets Fractals");
        setResizable(false);
        SetsPanel setPanel = new SetsPanel();
        add(setPanel);
        
        // Add slider to view all fractals generated
        
        JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, setPanel.getTotalFractals() - 1, 0);
        slider.setMajorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setSnapToTicks(true);
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int value = slider.getValue();
                setPanel.setCurrentFractal(value);
                setPanel.repaint();
            }
        });
        add(slider, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        JuliaFrame frame = new JuliaFrame();
        frame.setVisible(true);
    }

}

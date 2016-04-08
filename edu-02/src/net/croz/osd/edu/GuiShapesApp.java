package net.croz.osd.edu;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import net.croz.osd.edu.shapes.ShapeException;
import net.croz.osd.edu.util.JTextOutputStream;
import net.croz.osd.edu.util.config.ShapeConfig;

public class GuiShapesApp {

	public static void main(String[] args) {
		// Load configuration
		ShapeConfig.configure("conf/shape.properties");
        
		//Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
	}
	
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("ShapeApp");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Shape
        JLabel shapeLabel = new JLabel("Select shape: ");
        JComboBox<String> shapeBox = new JComboBox<String>(ShapeConfig.SUPPORTED_SHAPES);
        
        JPanel shapePanel = new JPanel();
        shapePanel.add(shapeLabel);
        shapePanel.add(shapeBox);
        
        // Size
        JLabel sizeLabel = new JLabel("Enter size: ");
        
        JFormattedTextField sizeField = new JFormattedTextField(NumberFormat.getNumberInstance());
        sizeField.setValue(1.0);
        sizeField.setColumns(5);
        
        // Submit button
        JButton okBtn = new JButton("Ok");
        okBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				// Do something with shape
				try {
					String inShape = (String) shapeBox.getSelectedItem();
					double inSize = ((Number)sizeField.getValue()).doubleValue();
					
					ShapeConsumer consumer = new ShapeConsumer(inShape, inSize);
					consumer.printShapeInfo();
				} catch (ShapeException e) {  
					//
				}		
			}
		});
        
        JPanel sizePanel = new JPanel();
        sizePanel.add(sizeLabel);
        sizePanel.add(sizeField);
        sizePanel.add(okBtn);
        
        
        // input panel
        JPanel inPanel = new JPanel(new GridLayout(1, 2));
        inPanel.add(shapePanel);
        inPanel.add(sizePanel);
        
        // output panel
        JTextArea textArea = new JTextArea(20, 50);
        JScrollPane scrollPane = new JScrollPane(textArea); 
        textArea.setEditable(false);
        
        PrintStream printStream = new PrintStream(new JTextOutputStream(textArea));
		System.setOut(printStream);
        
        // 
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(inPanel, BorderLayout.NORTH);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

}

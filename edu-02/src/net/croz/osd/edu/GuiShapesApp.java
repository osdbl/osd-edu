package net.croz.osd.edu;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

import net.croz.osd.edu.shapes.ShapeException;
import net.croz.osd.edu.util.JTextPrintStream;
import net.croz.osd.edu.util.ShapeComboModel;
import net.croz.osd.edu.util.config.ShapeConfig;

public class GuiShapesApp {
	public static ResourceBundle rb = ResourceBundle.getBundle("net.croz.osd.edu.i18n.shape-message", new Locale("hr"));

	// GUI components
	private JFrame frame;
	/**/private JPanel inPanel;
	
	/*---*/private JPanel langPanel;
	/*------*/private JLabel langLabel;
	/*------*/private JComboBox<String> langBox;
	
	/*---*/private JPanel shapePanel;
	/*------*/private JLabel shapeLabel;
	/*------*/private JComboBox<ShapeComboModel.Item> shapeBox;
	
	/*---*/private JPanel sizePanel;
	/*------*/private JLabel sizeLabel;
	/*------*/private JFormattedTextField sizeField;
	/*------*/private JButton okBtn;
	
	/**/private JScrollPane scrollPane;
	/*---*/private JTextArea textArea;
	
	public static void main(String[] args) {
		// Load configuration
		ShapeConfig.configure("conf/shape.properties");
        
		GuiShapesApp main = new GuiShapesApp();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                main.createAndShowGUI();
            }
        });
	}
	
    @SuppressWarnings("unchecked")
	private void createAndShowGUI() {
    	frame = new JFrame("ShapeApp");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setMinimumSize(new Dimension(800, 600));
        
        //
        inPanel = new JPanel(new GridLayout(1, 3));
        
        //
        langPanel = new JPanel();
        langLabel = new JLabel(rb.getString("select.language"));
        langBox = new JComboBox<String>(ShapeConfig.SUPPORTED_LANGUAGES);
        langBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				rb = ResourceBundle.getBundle("net.croz.osd.edu.i18n.shape-message", new Locale(langBox.getSelectedItem().toString()));
				ShapeConsumer.rb = rb;
				langLabel.setText(rb.getString("select.language"));
				shapeLabel.setText(rb.getString("select.shape"));
				sizeLabel.setText(rb.getString("input.size"));
				shapeBox.setModel(new DefaultComboBoxModel<ShapeComboModel.Item>(ShapeComboModel.getModel()));
			}
		});
        langPanel.add(langLabel);
        langPanel.add(langBox);
        
        //
        shapePanel = new JPanel();
        shapeLabel = new JLabel(rb.getString("select.shape"));
        shapeBox = new JComboBox<ShapeComboModel.Item>(ShapeComboModel.getModel());
        shapeBox.setRenderer(new BasicComboBoxRenderer() {
			private static final long serialVersionUID = 1L;
			@Override
        	public Component getListCellRendererComponent(@SuppressWarnings("rawtypes") JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        		super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value != null) {
                    setText(((ShapeComboModel.Item)value).getLabel());
                }   
                return this;
            }
        }); 
        shapePanel.add(shapeLabel);
        shapePanel.add(shapeBox);
        
        //
        sizePanel = new JPanel();
        sizeLabel = new JLabel(rb.getString("input.size"));
        sizeField = new JFormattedTextField(NumberFormat.getNumberInstance()); 
        sizeField.setValue(1.0);
        sizeField.setColumns(5);
        okBtn = new JButton("Ok");
        okBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				// Do something with shape
				try {
					String inShape = ((ShapeComboModel.Item)shapeBox.getSelectedItem()).getValue();
					double inSize = ((Number)sizeField.getValue()).doubleValue();					
					ShapeConsumer consumer = new ShapeConsumer(inShape, inSize);
					consumer.printShapeInfo();
				} catch (ShapeException e) {  
					//
				}		
			}
		});
        sizePanel.add(sizeLabel);
        sizePanel.add(sizeField);
        sizePanel.add(okBtn);
        
        //
        inPanel.add(langPanel);
        inPanel.add(shapePanel);
        inPanel.add(sizePanel);
        
        //
        textArea = new JTextArea(20, 50);
        textArea.setEditable(false);
        System.setOut(new JTextPrintStream(textArea));
        scrollPane = new JScrollPane(textArea); 
        
        // 
        frame.getContentPane().add(inPanel, BorderLayout.NORTH);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }
}

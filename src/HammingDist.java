import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Scrollbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

public class HammingDist extends JFrame
{
	private JPanel panel0 = new JPanel(new BorderLayout()); 
	private JPanel panel1 = new JPanel(null); 
	private JButton showStation = new JButton("Show Station");
	private JButton calHD = new JButton("Calculate HD");
	private JButton addStation = new JButton("Add Station");
	private JSlider slider = new JSlider(1,4);
	private JLabel valueTextField = new JLabel();
	private Color lightBlue = new Color(51,153,225);
	private JTextField inputTextField = new JTextField(10);
	private JComboBox<String> list;
	private JLabel enterHD = new JLabel("Enter Hamming Dist:");
	private JLabel compare = new JLabel("Compare with:");
	private JLabel distLabel0 = new JLabel("Distance 0");
	private JLabel disLabel1 = new JLabel("Distance 1");
	private JLabel disLabel2 = new JLabel("Distance 2");
	private JLabel disLabel3 = new JLabel("Distance 3");
	private JLabel disLabel4 = new JLabel("Distance 4");
	private JLabel distBox0 = new JLabel();
	private JLabel distBox1 = new JLabel();	
	private JLabel distBox2 = new JLabel();
	private JLabel distBox3 = new JLabel();
	private JLabel distBox4 = new JLabel();
	private BufferedImage img;
	private JLabel pic;
	private JTextArea showStationBox = new JTextArea(20,12);
	private JScrollPane scrollPane = new JScrollPane(showStationBox);
	
	public HammingDist() throws IOException
	{
		initFrame();
		setButton();
		setSlider();
		setText();
		setComboBox();
		setImage();
		panel1.add(showStation);
		panel1.add(calHD);
		panel1.add(addStation);
		panel1.add(slider);
		panel1.add(valueTextField);
		panel1.add(inputTextField);
		panel1.add(enterHD);
		panel1.add(compare);
		panel1.add(distLabel0);
		panel1.add(disLabel1);
		panel1.add(disLabel2);
		panel1.add(disLabel3);
		panel1.add(disLabel4);
		panel1.add(list);
		panel1.add(distBox0);
		panel1.add(distBox1);
		panel1.add(distBox2);
		panel1.add(distBox3);
		panel1.add(distBox4);
		panel1.add(pic);
		//panel1.add(showStationBox);
		panel1.add(scrollPane);
		panel0.add(panel1);	
		this.add(panel0);
	}


	private void setImage() throws IOException 
	{
		img = ImageIO.read(new File("pro1.jpg"));
		pic = new JLabel(new ImageIcon(img));
		pic.setBounds(300, 15, 400, 533);
	}

	private void setText() 
	{
		inputTextField.setText("ZERO");
		inputTextField.setBounds(150, 700, 100, 25);
		enterHD.setBounds(10, 8, 120, 10);
		compare.setBounds(10, 415, 120, 10);
		distLabel0.setBounds(15, 500, 120, 10);
		disLabel1.setBounds(15, 545, 120, 10);
		disLabel2.setBounds(15, 585, 120, 10);
		disLabel3.setBounds(15, 625, 120, 10);
		disLabel4.setBounds(15, 665, 120, 10);
		
		distBox0.setBorder(BorderFactory.createLineBorder(lightBlue, 1));
		distBox0.setBounds(150, 495, 100, 20);
		
		distBox1.setBorder(BorderFactory.createLineBorder(lightBlue, 1));
		distBox1.setBounds(150, 535, 100, 20);
		
		distBox2.setBorder(BorderFactory.createLineBorder(lightBlue, 1));
		distBox2.setBounds(150, 575, 100, 20);
		
		distBox3.setBorder(BorderFactory.createLineBorder(lightBlue, 1));
		distBox3.setBounds(150, 615, 100, 20);
		
		distBox4.setBorder(BorderFactory.createLineBorder(lightBlue, 1));
		distBox4.setBounds(150, 660, 100, 20);
		
		scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setOpaque(true);
		scrollPane.setBounds(25, 150, 200, 250);
	}

	private void setComboBox() throws IOException 
	{
		BufferedReader input = new BufferedReader(new FileReader("Mesonet.txt"));
		List<String> strings = new ArrayList<String>();
		try 
		{
			String line = null;
			while((line = input.readLine()) != null)
			{
				strings.add(line);
			}
		}
		catch (FileNotFoundException e)
		{
		    System.err.println("Error, file Mesonet.txt didn't exist.");
		}
		finally 
		{
		    input.close();
		}

		String[] lineArray = strings.toArray(new String[]{});
		list = new JComboBox<>(lineArray);
		list.setBounds(145, 410, 70, 20);
	}

	private void setButton() 
	{
		showStation.setBounds(17, 100, 115, 25);
		calHD.setBounds(17, 450, 115, 25);
		addStation.setBounds(17, 700, 115, 25);
		
		showStation.addActionListener(new ActionListener()
		{	
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				showStationBox.setText("");
				ArrayList<String> ham1 = new ArrayList<String>();
				ArrayList<String> ham2 = new ArrayList<String>();
				ArrayList<String> ham3 = new ArrayList<String>();
				ArrayList<String> ham4 = new ArrayList<String>();

				String word = (String)list.getSelectedItem();
				String[] wordSplit = word.split("");
				ArrayList<String> mesoNow = new ArrayList<String>();
				int[] hamCounts = new int[5];

				for(int index = 0 ; index < list.getItemCount(); index++) 
				{
					mesoNow.add(list.getItemAt(index));
				}
				
				
				for(int index = 0; index < mesoNow.size(); index++)
				{
					int counter = 0;
					String[] secondSplit = mesoNow.get(index).split("");
					for(int jndex = 0; jndex < wordSplit.length; jndex++) 
					{
						if(!(wordSplit[jndex].equals (secondSplit[jndex])))
						{
							counter++;
						}
					}	
					
					if(counter == 1)
					{
						ham1.add(mesoNow.get(index));			
					}
					else if(counter ==2) 
					{
						ham2.add(mesoNow.get(index));
					}
					else if(counter==3)
					{
						ham3.add(mesoNow.get(index));
					}
					else if(counter==4)
					{
						ham4.add(mesoNow.get(index));
					}

				}
					if(slider.getValue()==1) 
					{
						for(int here = 0; here < ham1.size(); here++) 
						{
							showStationBox.append(ham1.get(here)+ '\n');
						}
					}
					else if(slider.getValue()==2) 
					{
						for(int here = 0; here < ham2.size(); here++)
						{
							showStationBox.append(ham2.get(here) + '\n');
						}
					}
					else if(slider.getValue()==3) 
					{
						for(int here = 0; here < ham3.size(); here++) 
						{
							showStationBox.append(ham3.get(here)+ '\n');
						}
					}
					else if(slider.getValue()==4) 
					{
						for(int here = 0; here < ham4.size(); here++)
						{
							showStationBox.append(ham4.get(here)+ '\n');
						}
					}
			}
		});
		
		calHD.addActionListener(new ActionListener()
		{	
			@Override
			public void actionPerformed(ActionEvent e)
			{
				String word = (String)list.getSelectedItem();
				String[] wordSplit = word.split("");
				ArrayList<String> mesoNow = new ArrayList<String>();
				int[] hamCounts = new int[5];

				for(int index = 0 ; index < list.getItemCount(); index++) 
				{
					mesoNow.add(list.getItemAt(index));
				}
				for(int index = 0; index < mesoNow.size(); index++) 
				{
					int counter = 0;
					String[] secondSplit = mesoNow.get(index).split("");
					for(int jndex = 0; jndex < wordSplit.length; jndex++) 
					{
						if(!(wordSplit[jndex].equals (secondSplit[jndex])))
						{
							counter++;
						}
					}	
					hamCounts[counter] = hamCounts[counter] + 1;
				}
				distBox0.setText(Integer.toString(hamCounts[0]));
				distBox1.setText(Integer.toString(hamCounts[1]));
				distBox2.setText(Integer.toString(hamCounts[2]));
				distBox3.setText(Integer.toString(hamCounts[3]));
				distBox4.setText(Integer.toString(hamCounts[4]));
			}
		});
	
		addStation.addActionListener(new ActionListener() 
		{ 
			@Override 
			public void actionPerformed(ActionEvent e)
			{ 
				list.addItem(inputTextField.getText());	
			} 
		 });
	}
	
	private void setSlider() 
	{
		slider.setBounds(10, 25, 200, 50);
		slider.setPaintTicks(true);
		slider.setMajorTickSpacing(1);
		slider.setPaintLabels(true);
		valueTextField.setBorder(BorderFactory.createLineBorder(lightBlue, 1));
		valueTextField.setBounds(130, 5, 100, 20);
		valueTextField.setText(" " + slider.getValue());
		slider.addChangeListener(new ChangeListener()
		{
			@Override
			public void stateChanged(ChangeEvent e)
			{
				valueTextField.setText(" " + slider.getValue());
			}
		});
	}
	
	private void initFrame() 
	{
		setTitle("Hamming Distance");
		setSize(775, 775);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String[] args) throws IOException
	{
		HammingDist hd = new HammingDist();
		hd.revalidate();
	}
}
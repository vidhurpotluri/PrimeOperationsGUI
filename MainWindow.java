import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.math.BigInteger;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.SwingConstants;

public class MainWindow extends JFrame
{
	private static final long serialVersionUID = -3880026026104218593L;
	private Primes m_Primes;
	private JTextField tfPrimeFileName;
	private JTextField tfCrossFileName;
	private JLabel lblPrimeCount;
	private JLabel lblCrossCount;
	private JLabel lblLargestPrime;
	private JLabel lblLargestCross;
	private JLabel lblStatus;
	
	MainWindow(String name, Primes p)
	{
		m_Primes = p;
	}

	protected void popupGeneratePrimes()
	{
		JDialog dPrimes = new JDialog(this, "Prime Number Generation");
		GridBagLayout gridLayout = new GridBagLayout();
		dPrimes.getContentPane().setBackground(new Color(52, 0, 0));
		dPrimes.getContentPane().setLayout(gridLayout);
		
		GridBagConstraints gbcDialog = new GridBagConstraints();
		gbcDialog.fill = GridBagConstraints.HORIZONTAL;
		gbcDialog.anchor = GridBagConstraints.WEST;
		gbcDialog.ipady = 10;
		gbcDialog.weightx = .5;
		gbcDialog.insets = new Insets(1,2,0,0);
		gbcDialog.gridx = 0;
		gbcDialog.gridy = 0;
		
		GridBagConstraints gbcPanel = new GridBagConstraints();
		gbcPanel.anchor = GridBagConstraints.WEST;
		gbcPanel.weightx = .5;
		gbcPanel.insets = new Insets(1,2,0,0);
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 0;
		
		JPanel pnlGenerate = new JPanel();
		pnlGenerate.setLayout(new GridBagLayout());
		
		JLabel lblCount = new JLabel("Number of Primes to Generate");
		lblCount.setFont(new Font("Tahoma", Font.PLAIN, 12));
		pnlGenerate.add(lblCount, gbcPanel);
		
		JTextField tfCount = new JTextField();
		lblCount.setLabelFor(tfCount);
		tfCount.setColumns(30);
		gbcPanel.gridx = 1;
		pnlGenerate.add(tfCount, gbcPanel);
		
		JLabel lblStart = new JLabel("Starting Number (does not have to be prime)");
		lblStart.setFont(new Font("Tahoma", Font.PLAIN, 12));
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 1;
		pnlGenerate.add(lblStart, gbcPanel);
		
		JTextField tfStart = new JTextField();
		lblStart.setLabelFor(tfStart);
		tfStart.setColumns(30);
		gbcPanel.gridx = 1;
		pnlGenerate.add(tfStart, gbcPanel);
		
		dPrimes.add(pnlGenerate, gbcDialog);
		
		JPanel pnlButtons = new JPanel();
		pnlButtons.setLayout(new GridBagLayout());
		
		JButton btnGeneratePrimes = new JButton("Generate Primes");
		btnGeneratePrimes.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
      	try
      	{
      		BigInteger start = new BigInteger(tfStart.getText());
      		int count = Integer.parseInt(tfCount.getText());
       		m_Primes.generatePrimes(start, count);
       		lblStatus.setText("Status: Excited. Primes have been generated.");
       		updateStats();
      		dPrimes.dispose();
      	}
      	catch(NumberFormatException ex)
      	{
      		lblStatus.setText("You failed to type in an integer successfully. You are terrible at math. Shame.");
      		dPrimes.dispose();
      	}
      	
      }
    });
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 0;
		pnlButtons.add(btnGeneratePrimes, gbcPanel);
		
		JButton btnCancel = new JButton("Cancel Generation");
		btnCancel.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
      	dPrimes.dispose();
      }
    });
		gbcPanel.anchor = GridBagConstraints.EAST;
		gbcPanel.gridx = 1;
		pnlButtons.add(btnCancel, gbcPanel);		
		
		gbcDialog.gridy = 1;
		dPrimes.add(pnlButtons, gbcDialog);
		
		JPanel pnlStatus = new JPanel();
		pnlStatus.setLayout(new GridBagLayout());

		gbcPanel.anchor = GridBagConstraints.SOUTHWEST;
		gbcPanel.weightx = .5;
		gbcPanel.insets = new Insets(1,2,0,0);
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 1;

		JLabel lblNotice = new JLabel("Warning: This application is single threaded, and will freeze while generating primes.");
		lblNotice.setFont(new Font("Tahoma", Font.PLAIN, 12));
		pnlStatus.add(lblNotice, gbcPanel);
		
		gbcDialog.gridy = 2;
		dPrimes.add(pnlStatus, gbcDialog);
		
		dPrimes.setSize(1000,400);
		dPrimes.pack(); // Knowing what this is and why it is needed is important. You should read the documentation on this function!
		dPrimes.setVisible(true);		
	}

	// This function updates all the GUI statistics. (# of primes, # of crosses, etc)
	private void updateStats()
	{
		lblPrimeCount.setText(String.valueOf(m_Primes.primeCount()));
		lblCrossCount.setText(String.valueOf(m_Primes.crossesCount()));
		if (m_Primes.primeCount() != 0) {
		lblLargestPrime.setText("The largest prime has " + String.valueOf(m_Primes.sizeofLastPrime()) + " digits.");
		}
		if (m_Primes.crossesCount() != 0) {
			lblLargestCross.setText("The largest cross has " + String.valueOf(m_Primes.sizeofLastCross().left()) + " and " + String.valueOf(m_Primes.sizeofLastCross().right()) + " digits.");
		}
 	}
	public void popupMain() {
		// Header
		JDialog dPrimes = new JDialog(this, Config.APPLICATIONNAME);
		GridBagLayout gridLayout = new GridBagLayout();
		dPrimes.getContentPane().setBackground(new Color(52, 0, 0));
		dPrimes.getContentPane().setLayout(gridLayout);
		
		GridBagConstraints gbcDialog = new GridBagConstraints();
		gbcDialog.fill = GridBagConstraints.HORIZONTAL;
		gbcDialog.anchor = GridBagConstraints.WEST;
		gbcDialog.ipady = 10;
		gbcDialog.weightx = .5;
		gbcDialog.insets = new Insets(1,3,0,0);
		gbcDialog.gridx = 0;
		gbcDialog.gridy = 0;
		
		GridBagConstraints gbcPanel = new GridBagConstraints();
		gbcPanel.anchor = GridBagConstraints.WEST;
		gbcPanel.weightx = .5;
		gbcPanel.insets = new Insets(1,2,0,0);
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 0;
		
		
		// First panel - Primes file
		JPanel pnlPrimes = new JPanel();
		pnlPrimes.setLayout(new GridBagLayout());
		
		// Text field
		tfPrimeFileName = new JTextField(Config.PRIMEFILENAME);
		tfPrimeFileName.setColumns(50);
		
		pnlPrimes.add(tfPrimeFileName, gbcPanel);

		
		// Prime count label
		gbcPanel.anchor = GridBagConstraints.CENTER;
		gbcPanel.gridx = 1;
		lblPrimeCount = new JLabel("0");
		lblPrimeCount.setFont(new Font("Tahoma", Font.BOLD, 12));
		pnlPrimes.add(lblPrimeCount, gbcPanel);
	
		gbcPanel.anchor = GridBagConstraints.WEST;
		JLabel primes = new JLabel("Primes File");
		primes.setFont(new Font("Tahoma", Font.BOLD, 15));
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 1;
		primes.setLabelFor(tfPrimeFileName);
		pnlPrimes.add(primes, gbcPanel);
		dPrimes.add(pnlPrimes, gbcDialog);
		
		// Prime file load button
		gbcPanel.anchor = GridBagConstraints.EAST;
		gbcPanel.gridx = 0;
		JButton loadPrimes = new JButton("Load");
		loadPrimes.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				boolean what = FileAccess.loadPrimes(m_Primes, tfPrimeFileName.getText());
				if (what == true) {
					lblStatus.setText("Status: Primes list loaded successfully");
					updateStats();
				}
				else {
					lblStatus.setText("Status: Primes list load unsuccessful");
				}
		      }
		});
		pnlPrimes.add(loadPrimes, gbcPanel);
		
		// Prime file save button
		gbcPanel.anchor = GridBagConstraints.EAST;
		gbcPanel.gridx = 1;
		JButton savePrimes = new JButton("Save");
		savePrimes.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
		      	FileAccess.savePrimes(m_Primes, tfPrimeFileName.getText());
		      }
		});
		pnlPrimes.add(savePrimes, gbcPanel);
		
		// Second panel - crosses panel
		gbcPanel.anchor = GridBagConstraints.WEST;
		JPanel pnlCrosses = new JPanel();
		pnlCrosses.setLayout(new GridBagLayout());
		
		// Text field
		tfCrossFileName = new JTextField(Config.CROSSFILENAME);
		tfCrossFileName.setColumns(50);
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 0;
		pnlCrosses.add(tfCrossFileName, gbcPanel);
		
		// Crosses count label
		gbcPanel.anchor = GridBagConstraints.CENTER;
		gbcPanel.gridx = 1;
		lblCrossCount = new JLabel("0");
		lblCrossCount.setFont(new Font("Tahoma", Font.BOLD, 12));
		pnlCrosses.add(lblCrossCount, gbcPanel);
		
		gbcPanel.anchor = GridBagConstraints.WEST;
		JLabel crosses = new JLabel("Hexagon Cross File");
		crosses.setFont(new Font("Tahoma", Font.BOLD, 15));
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 1;
		pnlCrosses.add(crosses, gbcPanel);
		
		gbcDialog.gridx = 0;
		gbcDialog.gridy = 1;
		
		// Prime file load button
		gbcPanel.anchor = GridBagConstraints.EAST;
		gbcPanel.gridx = 0;
		JButton loadCrosses = new JButton("Load");
		loadCrosses.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				boolean what = FileAccess.loadCrosses(m_Primes, tfCrossFileName.getText());
				if (what == true) {
					lblStatus.setText("Status: Cross list loaded successfully");
					updateStats();
				}
				else {
					lblStatus.setText("Status: Cross list load unsuccessful");
				}
			}
		});
		pnlCrosses.add(loadCrosses, gbcPanel);
				
		// Prime file save button
		gbcPanel.anchor = GridBagConstraints.EAST;
		gbcPanel.gridx = 1;
		JButton saveCrosses = new JButton("Save");
		saveCrosses.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				FileAccess.saveCrosses(m_Primes, tfCrossFileName.getText());
			}
		});
		pnlCrosses.add(saveCrosses, gbcPanel);
		dPrimes.add(pnlCrosses, gbcDialog);
		
		
		// Third panel - generate primes and crosses button
		// Generate Primes button
		gbcPanel.anchor = GridBagConstraints.CENTER;
		gbcDialog.gridy = 2;
		JPanel pnlPriCro = new JPanel();
		pnlPriCro.setLayout(new GridBagLayout());
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 0;
		JButton generatePrimes = new JButton("Generate Primes");
		generatePrimes.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				popupGeneratePrimes();
				}
		});
		pnlPriCro.add(generatePrimes, gbcPanel);

		
		// Generate crosses button
		gbcPanel.gridx = 2;
		gbcPanel.gridy = 0;
		JButton generateCrosses = new JButton("Generate Crosses");
		generateCrosses.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				m_Primes.generateTwinPrimes();
				m_Primes.generateHexPrimes();
				lblStatus.setText("Status: Ecstatic. Crosses have been generated.");
				updateStats();
				}
		});
		pnlPriCro.add(generateCrosses, gbcPanel);
		
		// label for digits of largest prime and largest cross
		gbcPanel.gridx = 1;
		gbcPanel.anchor = GridBagConstraints.NORTH;
		lblLargestPrime = new JLabel("The largest prime has 0 digits");
		lblLargestCross = new JLabel("The largest cross has 0 and 0 digits");
		
		pnlPriCro.add(lblLargestPrime, gbcPanel);
		gbcPanel.anchor = GridBagConstraints.SOUTH;
		pnlPriCro.add(lblLargestCross, gbcPanel);
		dPrimes.add(pnlPriCro, gbcDialog);
		
		
		// Fourth panel - Status bar
		gbcDialog.gridy = 3;
		JPanel pnlStatus = new JPanel();
		pnlStatus.setLayout(new GridBagLayout());
		gbcPanel.anchor = GridBagConstraints.WEST;
		lblStatus = new JLabel("Status: Bored.");
		lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 12));
		pnlStatus.add(lblStatus,gbcPanel);
		
		dPrimes.add(pnlStatus, gbcDialog);
		

				
		dPrimes.setSize(1000,400);
		dPrimes.pack(); 
		dPrimes.setVisible(true);		
		
		
	}



}
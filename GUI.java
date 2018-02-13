import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI extends JFrame
{
	private JButton button;
	private JLabel lx, ly, lz;
	private JTextField x, y, z;
	private JPanel panel;

	public GUI(String title)
	{
		super(title);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		panel = new JPanel();
		panel.setLayout(new GridLayout(0, 2));

		button = new JButton("Calculate");

		lx = new JLabel("Width (sm)");
		ly = new JLabel("Height (sm)");
		lz = new JLabel("Length (sm)");

		x = new JTextField("");
		y = new JTextField("");
		z = new JTextField("");

		panel.add(lx); panel.add(x);
		panel.add(ly); panel.add(y);
		panel.add(lz); panel.add(z);
		panel.add(button);
		add(panel);
		pack();

		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				double dx, dy, dz;
				double V;

				try
				{
					dx = Double.parseDouble(x.getText());
					dy = Double.parseDouble(y.getText());
					dz = Double.parseDouble(z.getText());
				} catch (NumberFormatException nfe)
				{
					JOptionPane.showMessageDialog(null, "Data is't numeric");
					return;
				}

				V = dx * dy * dz;

				JOptionPane.showMessageDialog(null, "Amount: " + V + " sm^3");
			}
		});
	}
}




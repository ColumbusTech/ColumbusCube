import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI extends JFrame
{
	private String[] modes = { "sm^3", "m^3" };

	private JButton button;
	private JComboBox mode;
	private JLabel lx, ly, lz, lpress, ldens;
	private JTextField x, y, z, press, dens;
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
		mode = new JComboBox(modes);

		lx = new JLabel("Width (sm)");
		ly = new JLabel("Height (sm)");
		lz = new JLabel("Length (sm)");
		lpress = new JLabel("Pressure (Pa)");
		ldens = new JLabel("Density (g/sm^3)");

		x = new JTextField("");
		y = new JTextField("");
		z = new JTextField("");
		press = new JTextField("");
		dens = new JTextField("");

		panel.add(lx); panel.add(x);
		panel.add(ly); panel.add(y);
		panel.add(lz); panel.add(z);
		panel.add(lpress); panel.add(press);
		panel.add(ldens); panel.add(dens);
		panel.add(button); panel.add(mode);
		add(panel);
		pack();

		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				double dx, dy, dz, dpress, ddens;
				double V, Vs, Vc, m;
				String format, result;

				try
				{
					dx = Double.parseDouble(x.getText());
					dy = Double.parseDouble(y.getText());
					dz = Double.parseDouble(z.getText());
					dpress = Double.parseDouble(press.getText());
					ddens = Double.parseDouble(dens.getText());

					if (dx <= 0.0 || dy <= 0.0 || dz <= 0.0 || dpress <= 0.0 || ddens <= 0.0)
						throw new NumberFormatException();
				} catch (NumberFormatException nfe)
				{
					JOptionPane.showMessageDialog(null, "Data is incorrect", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}

				V = dx * dy * dz;
				m = dpress / (dx * dz) / 10;
				Vs = m * ddens;
				Vc = V - Vs;

				if (Vs > V || Vc <= 0)
				{
					JOptionPane.showMessageDialog(null, "Data is incorrect", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}

				switch (mode.getSelectedIndex())
				{
				case 0: format = " sm^3"; break;
				case 1:
					format = " m^3";
					V /= 1000000;
					Vs /= 1000000;
					Vc /= 1000000;
					break;
				default: format = " sm^3";  break;
				}

				result = "Volume: " + V + format + "\n";
				result += "Substance volume: " + Vs + format + "\n";
				result += "Cavity volume: " + Vc + format + "\n";
				result += "Mass: " + m + " kg" + "\n";

				JOptionPane.showMessageDialog(null, result, "Result", JOptionPane.PLAIN_MESSAGE);
			}
		});
	}
}




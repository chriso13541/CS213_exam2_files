package f2c.view;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class F2CController {
	@FXML Button f2c;
	@FXML Button c2f;
	@FXML TextField f;
	@FXML TextField c;
	
	public void convert(ActionEvent e) {
		Button b = (Button)e.getSource();
		if (b == f2c) {
			try {
				float fval = Float.valueOf(f.getText());
				float cval = (fval-32)*5f/9;
				c.setText(String.format("%5.1f", cval));
			} catch (NumberFormatException ex) {
				c.setText("Invalid input");
			}
		} else {
			try {
				float cval = Float.valueOf(c.getText());
				float fval = cval*9f/5+32;
				f.setText(String.format("%5.1f", fval));
			} catch (NumberFormatException ex) {
				f.setText("Invalid input");
			}
		}
	}
}

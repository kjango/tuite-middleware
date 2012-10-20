package control;

import model.Tuite;

public class CtrlTuite {
	
	
	public boolean truncate(Tuite t){
		if (t.getText().length() > 140){
			t.setText(t.getText().substring(0, 139));
			t.setTruncated(true);
			return true;
		}
		t.setTruncated(false);
		return false;
	}
}

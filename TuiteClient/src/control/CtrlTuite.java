package control;

import model.Tuite;

public class CtrlTuite {
	/**
	 * 
	 * @param t tuite that will be truncated
	 * @return true if the tuite was truncated (over 140 chars)
	 */
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

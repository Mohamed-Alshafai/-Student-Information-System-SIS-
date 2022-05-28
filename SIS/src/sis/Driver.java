package sis;

public class Driver {

	public static void main(String[] args) {
		SISView x = new SISView();
		SISController y = new SISController(x.getSis(), x);
		x.registerListener(y);
		x.setVisible(true);
	}
}
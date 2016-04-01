import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class alpha {
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame window = new frame();
					window.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
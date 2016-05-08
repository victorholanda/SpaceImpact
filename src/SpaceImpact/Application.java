package SpaceImpact;

import javax.swing.JFrame;

import Gui.Builder.ConstructBoard;

public class Application extends JFrame {
	
	private static final long serialVersionUID = 1L;

	public Application() {

        add(ConstructBoard.simpleBoarder());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//Gestione dell finestra di gioco
        setSize(490, 340);
        setLocationRelativeTo(null);
        setTitle("Space Impact");
        setResizable(false);
        setVisible(true);

    }

    public static void main(String[] args)	 {
    	new Application();
    }
}

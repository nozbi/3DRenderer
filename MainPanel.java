import javax.swing.JPanel;


public class MainPanel extends JPanel
{
    public MainPanel()
    {
        super();
        
        DisplayPanel displayPanel = new DisplayPanel();
        this.add(displayPanel);
        this.add(new MenuPanel(displayPanel));
    }
}

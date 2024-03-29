import javax.swing.JFrame;


public class Frame extends JFrame
{
    public Frame()
    {
        super("App");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        this.add(new MainPanel());

        this.pack();
        this.setVisible(true);
    }
}


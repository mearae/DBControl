import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CRUD_Frame {
    private JTextArea jTextArea = null;
    private JFrame frame = null;
    private JPanel jPanel = null;
    private JButton button1 = null;
    private JButton button2 = null;
    private JButton button3 = null;
    private JButton button4 = null;

    public CRUD_Frame() {
        this.jTextArea = new JTextArea();
        this.frame = new JFrame("test");
        this.jPanel = new JPanel();
        this.button1 = new JButton("C");
        this.button2 = new JButton("R");
        this.button3 = new JButton("U");
        this.button4 = new JButton("D");
        setFrame();
        setjPanel();
        setButton1();
        setButton2();
        setButton3();
        setButton4();
        addButtonToPanel();
        addPanelToFrame();
    }

    public void setFrame() {
        frame.setLayout((new BorderLayout()));
        frame.setSize(500,300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void setjPanel() {
        jPanel.setLayout(new FlowLayout());
    }

    public void setButton1() {
        button1.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crud("create","",scan("name"),scan("age"),scan("email"));
            }
        });
    }

    public void setButton2() {
        button2.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crud("read",scan("line"),"","","");
            }
        });
    }

    public void setButton3() {
        button3.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crud("update",scan("line"),scan("name"),scan("age"),scan("email"));
            }
        });
    }

    public void setButton4() {
        button4.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crud("delete",scan("line"),"","","");
            }
        });
    }

    public void addButtonToPanel(){
        jPanel.add(button1);
        jPanel.add(button2);
        jPanel.add(button3);
        jPanel.add(button4);
    }

    public void addPanelToFrame(){
        frame.add(jPanel, BorderLayout.NORTH);
        frame.add(new JScrollPane(jTextArea),BorderLayout.CENTER);
    }

    public void showFrame(){
        frame.setVisible(true);
    }
}

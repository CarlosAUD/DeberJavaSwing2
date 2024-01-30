import javax.swing.*;
import javax.swing.*;
import java.awt.*;

public class form2 extends JFrame {
    private JTextArea textArea1;
    private JPanel panel1;
    public form2(String texto) {
        textArea1.setText(texto); // Establece el texto en el JTextArea
        panel1 = new JPanel(new BorderLayout());
        panel1.add(new JScrollPane(textArea1), BorderLayout.CENTER);
        add(panel1);
    }
}
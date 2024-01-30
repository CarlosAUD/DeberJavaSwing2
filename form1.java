import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class form1 {
    private JPasswordField passwordField;
    private JTextField usuarioField;
    public JPanel inicio;
    private JButton INGRESARButton;

    public form1() {
        INGRESARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = usuarioField.getText();
                char[] contrasenaChar = passwordField.getPassword();
                String contrasena = new String(contrasenaChar);
                try {
                    Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3307/informacion", "root", "");
                    String consulta = "SELECT * FROM usuarios WHERE usuario = ? AND contrasena = ?";
                    PreparedStatement statement = conexion.prepareStatement(consulta);
                    statement.setString(1, usuario);
                    statement.setString(2, contrasena);
                    ResultSet resultado = statement.executeQuery();

                    if (resultado.next()) {
                        Main.frame1.dispose();
                        String textoColumna = resultado.getString("biografia");
                        form2 BioCarlos = new form2(textoColumna);
                        BioCarlos.setBounds(10, 20, 400, 400);
                        BioCarlos.setVisible(true);
                        BioCarlos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        Main.frame1.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null,"Vuelve a Intentarlo, el usuario o contraseña son invalidos");
                    }
                    resultado.close();
                    statement.close();
                    conexion.close();
                } catch (SQLException ex) {
                    System.out.println("Error al conectar a la base de datos MySQL: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Angel
 */
public class Molde extends JInternalFrame{
    private JTextField txtNombre;
    private JTextField txtIp;
    public Molde(){
        Font font = new Font("Dialog",Font.BOLD,16);
        
        JPanel panel = new JPanel();
        panel.setLayout(null);
        
        JLabel nombre = new JLabel("Nombre");
        nombre.setFont(font);
        nombre.setBounds(100, 100, 120, 20);
        panel.add(nombre);
        
        txtNombre = new JTextField();
        txtNombre.setBounds(350, 95, 300, 30);
        panel.add(txtNombre);
        
        JButton btnCrear = new JButton("Crear");
        btnCrear.setBounds(400, 200, 120, 30);
        btnCrear.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                connect(txtIp.getText());
            }
        });
        
        txtIp = new JTextField();
        txtIp.setBounds(100, 200, 300, 30);
        panel.add(txtIp);
        panel.add(btnCrear);
        
        this.setSize(700, 300);
        this.setTitle("Nuevo Molde");
        this.add(panel);
        this.setResizable(false);
        this.setClosable(true);
        this.setVisible(true);
    }
    
    public void connect(String ip){
        String cadena = "jdbc:postgresql://"+ip+":5432/BACTECHNOLOGY";
        String user = "postgres";
        String pass = "root";
        List lista = new ArrayList();
        try{
            Class.forName("org.postgresql.Driver");
            Connection conexion = DriverManager.getConnection(cadena,user,pass);
            Statement st =  conexion.createStatement();
            System.out.println(cadena);
            String sql = "INSERT INTO MOLDE(NOMBRE) VALUES ('"+txtNombre.getText()+"')";
            System.out.println(sql);
            st.execute(sql);
        }catch(Exception e){
            System.out.println("Errro: "+e.getMessage());
        }
    }
}

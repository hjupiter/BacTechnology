/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion;

import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Angel
 */
public class Usuario extends JInternalFrame{
    private JTextField txtIp;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtCedula;
    private JTextField txtUsuario;
    private JTextField txtContraseña;
    
    public Usuario(){
        
        Font font = new Font("Dialog",Font.BOLD,16);
        
        JPanel panel = new JPanel();
        panel.setLayout(null);
        
        JLabel nombre = new JLabel("Nombre");
        nombre.setFont(font);
        nombre.setBounds(100, 25, 120, 20);
        panel.add(nombre);
        
        JLabel Apellido = new JLabel("Apellido");
        Apellido.setFont(font);
        Apellido.setBounds(100, 75, 120, 20);
        panel.add(Apellido);
        
        JLabel cedula = new JLabel("Cedula");
        cedula.setFont(font);
        cedula.setBounds(100, 125, 120, 20);
        panel.add(cedula);
        
        JLabel usuario = new JLabel("Usuario");
        usuario.setFont(font);
        usuario.setBounds(100, 175, 120, 20);
        panel.add(usuario);
        
        JLabel constraseña = new JLabel("Contraseña");
        constraseña.setFont(font);
        constraseña.setBounds(100, 225, 120, 20);
        panel.add(constraseña);
        
        JButton btnCrear = new JButton("Crear");
        btnCrear.setBounds(400, 300, 120, 30);
        btnCrear.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                connect(txtIp.getText());
            }
        });
        panel.add(btnCrear);
        
        txtIp = new JTextField();
        txtIp.setBounds(100, 300, 300, 30);
        panel.add(txtIp);
        
        txtNombre = new JTextField();
        txtNombre.setBounds(350, 20, 300, 30);
        panel.add(txtNombre);
        
        txtApellido = new JTextField();
        txtApellido.setBounds(350, 70, 300, 30);
        panel.add(txtApellido);
        
        txtCedula = new JTextField();
        txtCedula.setBounds(350, 120, 300, 30);
        panel.add(txtCedula);
        
        txtUsuario = new JTextField();
        txtUsuario.setBounds(350, 170, 300, 30);
        panel.add(txtUsuario);
        
        txtContraseña = new JTextField();
        txtContraseña.setBounds(350, 220, 300, 30);
        panel.add(txtContraseña);
        
        //nombre.setLocation(100, 100);
        
        this.setSize(700, 400);
        this.setTitle("Nuevo Usuario");
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
            String sql = "INSERT INTO USUARIO(NOMBRE,APELLIDO,CI,USUARIO,CONTRASEÑA,TIPO) VALUES ('"+txtNombre.getText()+"','"+txtApellido.getText()+"','"+txtCedula.getText()+"','"+txtUsuario.getText()+"','"+txtContraseña.getText()+"',"+1+")";
            System.out.println(sql);
            st.execute(sql);
        }catch(Exception e){
            System.out.println("Errro: "+e.getMessage());
        }
    }
    
}

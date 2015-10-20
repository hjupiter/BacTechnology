/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.Types;
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
public class Maquinaria extends JInternalFrame{
    private JTextField txtNombre;
    private JTextField txtCodigo;
    private JTextField txtIp;
    public Maquinaria(){
        Font font = new Font("Dialog",Font.BOLD,16);
        
        JPanel panel = new JPanel();
        panel.setLayout(null);
        
        JLabel codigo = new JLabel("Codigo");
        codigo.setFont(font);
        codigo.setBounds(100, 50, 120, 20);
        panel.add(codigo);
        
        JLabel nombre = new JLabel("Nombre");
        nombre.setFont(font);
        nombre.setBounds(100, 100, 120, 20);
        panel.add(nombre);
        
        txtNombre = new JTextField();
        txtNombre.setBounds(350, 95, 300, 30);
        panel.add(txtNombre);
        
        txtCodigo = new JTextField();
        txtCodigo.setBounds(350, 45, 300, 30);
        panel.add(txtCodigo);
        
        JButton btnCrear = new JButton("Crear");
        btnCrear.setBounds(400, 200, 120, 30);
        btnCrear.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                connect(txtIp.getText());
            }
        });
        panel.add(btnCrear);
        
        txtIp = new JTextField();
        txtIp.setBounds(100, 200, 300, 30);
        panel.add(txtIp);
        
        this.setSize(700, 300);
        this.setTitle("Nueva Maquinaria");
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
            Connection conn = DriverManager.getConnection(cadena,user,pass);
            
            CallableStatement insertar_maquinaria =  conn.prepareCall("{ ? = call INSERTAR_MAQUINARIA ( ? , ? ) }");
            insertar_maquinaria.registerOutParameter(1, Types.BOOLEAN);
            insertar_maquinaria.setString(2, txtCodigo.getText());
            insertar_maquinaria.setString(3, txtNombre.getText());
            insertar_maquinaria.execute();
            boolean res = insertar_maquinaria.getBoolean(1);
            insertar_maquinaria.close();
            
            if(res){
                System.out.println("insert");
            }
            
        }catch(Exception e){
            System.out.println("Errro: "+e.getMessage());
        }
    }
}

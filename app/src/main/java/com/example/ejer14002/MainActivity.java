package com.example.ejer14002;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {
    public EditText txtUsu, txtPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtUsu=findViewById(R.id.txtUsuario);
        txtPass=findViewById(R.id.txtPassword);
    }
    public Connection conexionBD(){
        Connection cnn=null;
        try {
            StrictMode.ThreadPolicy pol=new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(pol);
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            cnn= DriverManager.getConnection("jdbc:jtds:sqlserver://192.168.18.20:1433/BDAPPF;"+
                    "instance=SQLEXPRESS;user=sa;password=12345");
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        return cnn;
    }

    public void Consulta(View view){
        try {
            Statement st=conexionBD().createStatement();
            ResultSet rs=st.executeQuery("SELECT * FROM usuarios " +
                    "where logeo='"+txtUsu.getText().toString()+"' and clave='"+txtPass.getText().toString()+"'");
            if(rs.next()){
                Toast.makeText(getApplicationContext(),"Conexion establecida Christian",Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
}
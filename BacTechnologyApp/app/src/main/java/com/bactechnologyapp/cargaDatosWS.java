package com.bactechnologyapp;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Angel on 16/10/2015.
 */
public class cargaDatosWS {
    private String conexion =  "http://192.168.100.101:8080/BacTechnology/BacTechnologyService?WSDL";

    public Boolean getAutenticacion(String usuario,String contraseña){
        Boolean res = null;
        SoapObject rpc;
        rpc = new SoapObject("http://service.BacTechnology.com/","Autenticacion");

        PropertyInfo propertyUsuario = new PropertyInfo();
        propertyUsuario.name = "usuario";
        propertyUsuario.type = PropertyInfo.STRING_CLASS;
        propertyUsuario.setValue(usuario);
        rpc.addProperty(propertyUsuario);

        PropertyInfo propertyContraseña = new PropertyInfo();
        propertyContraseña.name = "contrasena";
        propertyContraseña.type = PropertyInfo.STRING_CLASS;
        propertyContraseña.setValue(contraseña);
        rpc.addProperty(propertyContraseña);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(rpc);
        HttpTransportSE androidHttpTransport = null;
        try{
            androidHttpTransport =  new HttpTransportSE(conexion);

            androidHttpTransport.call("http://service.BacTechnology.com/Autenticacion", envelope);

            SoapPrimitive resultsRequestSOAP = (SoapPrimitive) envelope.getResponse();

            res = Boolean.valueOf(envelope.getResponse().toString());

            return  res;
        }catch(Exception e){
            System.out.println(e.getMessage());
            res = false;
        }
        return res;
    }

    public List getMaquinarias(){
        List res = null;
        res = new ArrayList();
        SoapObject rpc;
        rpc = new SoapObject("http://service.BacTechnology.com/","CargarMaquinarias");
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(rpc);
        HttpTransportSE androidHttpTransport = null;
        try{
            androidHttpTransport =  new HttpTransportSE(conexion);
            androidHttpTransport.call("http://service.BacTechnology.com/CargarMaquinarias", envelope);

            SoapObject result = (SoapObject) envelope.bodyIn;
            for(int i=0; i<result.getPropertyCount(); i++){
                res.add(result.getProperty(i).toString());
            }
            return  res;
        }catch(Exception e){
            return res;
        }
    }

    public List getMoldes(){
        List res = null;
        res = new ArrayList();
        SoapObject rpc;
        rpc = new SoapObject("http://service.BacTechnology.com/","CargarMoldes");
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(rpc);
        HttpTransportSE androidHttpTransport = null;
        try{
            androidHttpTransport =  new HttpTransportSE(conexion);
            androidHttpTransport.call("http://service.BacTechnology.com/CargarMoldes", envelope);

            SoapObject result = (SoapObject) envelope.bodyIn;
            for(int i=0; i<result.getPropertyCount(); i++){
                res.add(result.getProperty(i).toString());
            }
            return  res;
        }catch(Exception e){
            return res;
        }
    }
}


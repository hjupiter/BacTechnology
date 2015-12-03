package com.bactechnologyapp;

import android.graphics.Bitmap;

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
    private String conexion =  "http://192.168.0.101:8080/BacTechnology/BacTechnologyService?WSDL";

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


    public String hello(){
        String res = null;
        SoapObject rpc;
        rpc = new SoapObject("http://service.BacTechnology.com/","hello");

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(rpc);
        HttpTransportSE androidHttpTransport = null;
        try{
            androidHttpTransport =  new HttpTransportSE(conexion);

            androidHttpTransport.call("http://service.BacTechnology.com/hello", envelope);

            SoapPrimitive resultsRequestSOAP = (SoapPrimitive) envelope.getResponse();

            res = (envelope.getResponse().toString());

            return  res;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return res = "--";
        }
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

    public boolean guardarReporte(String idMaquina, String idMolde, String idUsuario, String imagen,String novedad,String solucion,String tipoNovedad, String tipoSolucion,String novedadDetectada){
        boolean res = false;
        SoapObject rpc;
        rpc = new SoapObject("http://service.BacTechnology.com/","NuevoReporte");

        PropertyInfo propertyMaquina = new PropertyInfo();
        propertyMaquina.name = "maquina";
        propertyMaquina.type = PropertyInfo.STRING_CLASS;
        propertyMaquina.setValue(idMaquina);
        rpc.addProperty(propertyMaquina);

        PropertyInfo propertyMolde = new PropertyInfo();
        propertyMolde.name = "molde";
        propertyMolde.type = PropertyInfo.STRING_CLASS;
        propertyMolde.setValue(idMolde);
        rpc.addProperty(propertyMolde);

        PropertyInfo propertyUsuario = new PropertyInfo();
        propertyUsuario.name = "usuario";
        propertyUsuario.type = PropertyInfo.STRING_CLASS;
        propertyUsuario.setValue(idUsuario);
        rpc.addProperty(propertyUsuario);

        PropertyInfo propertyNodevad = new PropertyInfo();
        propertyNodevad.name = "novedad";
        propertyNodevad.type = PropertyInfo.STRING_CLASS;
        propertyNodevad.setValue(novedad);
        rpc.addProperty(propertyNodevad);

        PropertyInfo propertySolucion = new PropertyInfo();
        propertySolucion.name = "solucion";
        propertySolucion.type = PropertyInfo.STRING_CLASS;
        propertySolucion.setValue(solucion);
        rpc.addProperty(propertySolucion);

        PropertyInfo propertyTipoNodevada = new PropertyInfo();
        propertyTipoNodevada.name = "tipoNovedad";
        propertyTipoNodevada.type = PropertyInfo.STRING_CLASS;
        propertyTipoNodevada.setValue(tipoNovedad);
        rpc.addProperty(propertyTipoNodevada);

        PropertyInfo propertyTipoSolucion = new PropertyInfo();
        propertyTipoSolucion.name = "tipoSolucion";
        propertyTipoSolucion.type = PropertyInfo.STRING_CLASS;
        propertyTipoSolucion.setValue(tipoSolucion);
        rpc.addProperty(propertyTipoSolucion);

        PropertyInfo propertyImagen = new PropertyInfo();
        propertyImagen.name = "foto";
        propertyImagen.type = PropertyInfo.STRING_CLASS;
        propertyImagen.setValue(imagen);
        rpc.addProperty(propertyImagen);

        PropertyInfo propertyNovedadDetectada = new PropertyInfo();
        propertyNovedadDetectada.name = "novedadDetectada";
        propertyNovedadDetectada.type = PropertyInfo.STRING_CLASS;
        propertyNovedadDetectada.setValue(novedadDetectada);
        rpc.addProperty(propertyNovedadDetectada);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(rpc);
        HttpTransportSE androidHttpTransport = null;

        try{
            System.out.println("asdasdsafsadfasdfsadfsadfsadfasdfsad");
            androidHttpTransport =  new HttpTransportSE(conexion);
            androidHttpTransport.call("http://service.BacTechnology.com/NuevoReporte", envelope);

            SoapPrimitive resultsRequestSOAP = (SoapPrimitive) envelope.getResponse();

            res = Boolean.valueOf(envelope.getResponse().toString());

            return  res;
        }catch(Exception e){
            System.out.println("----------------------------------------------->");
            System.out.println(e.getMessage());
            res = false;
            System.out.println("----------------------------------------------->");
            return res;
        }
    }

    public boolean guardarReporteMolde(String idMaquina, String idMolde, String idUsuario, String imagen,String novedad,String solucion,String tipoNovedad, String tipoSolucion,String novedadDetectada, String Articulo){
        boolean res = false;
        SoapObject rpc;
        rpc = new SoapObject("http://service.BacTechnology.com/","NuevoReporteMolde");

        PropertyInfo propertyMaquina = new PropertyInfo();
        propertyMaquina.name = "maquina";
        propertyMaquina.type = PropertyInfo.STRING_CLASS;
        propertyMaquina.setValue(idMaquina);
        rpc.addProperty(propertyMaquina);

        PropertyInfo propertyMolde = new PropertyInfo();
        propertyMolde.name = "molde";
        propertyMolde.type = PropertyInfo.STRING_CLASS;
        propertyMolde.setValue(idMolde);
        rpc.addProperty(propertyMolde);

        PropertyInfo propertyUsuario = new PropertyInfo();
        propertyUsuario.name = "usuario";
        propertyUsuario.type = PropertyInfo.STRING_CLASS;
        propertyUsuario.setValue(idUsuario);
        rpc.addProperty(propertyUsuario);

        PropertyInfo propertyNodevad = new PropertyInfo();
        propertyNodevad.name = "novedad";
        propertyNodevad.type = PropertyInfo.STRING_CLASS;
        propertyNodevad.setValue(novedad);
        rpc.addProperty(propertyNodevad);

        PropertyInfo propertySolucion = new PropertyInfo();
        propertySolucion.name = "solucion";
        propertySolucion.type = PropertyInfo.STRING_CLASS;
        propertySolucion.setValue(solucion);
        rpc.addProperty(propertySolucion);

        PropertyInfo propertyTipoNodevada = new PropertyInfo();
        propertyTipoNodevada.name = "tipoNovedad";
        propertyTipoNodevada.type = PropertyInfo.STRING_CLASS;
        propertyTipoNodevada.setValue(tipoNovedad);
        rpc.addProperty(propertyTipoNodevada);

        PropertyInfo propertyTipoSolucion = new PropertyInfo();
        propertyTipoSolucion.name = "tipoSolucion";
        propertyTipoSolucion.type = PropertyInfo.STRING_CLASS;
        propertyTipoSolucion.setValue(tipoSolucion);
        rpc.addProperty(propertyTipoSolucion);

        PropertyInfo propertyImagen = new PropertyInfo();
        propertyImagen.name = "foto";
        propertyImagen.type = PropertyInfo.STRING_CLASS;
        propertyImagen.setValue(imagen);
        rpc.addProperty(propertyImagen);

        PropertyInfo propertyNovedadDetectada = new PropertyInfo();
        propertyNovedadDetectada.name = "novedadDetectada";
        propertyNovedadDetectada.type = PropertyInfo.STRING_CLASS;
        propertyNovedadDetectada.setValue(novedadDetectada);
        rpc.addProperty(propertyNovedadDetectada);

        PropertyInfo propertyTipoReporte = new PropertyInfo();
        propertyTipoReporte.name = "articulo";
        propertyTipoReporte.type = PropertyInfo.STRING_CLASS;
        propertyTipoReporte.setValue(Articulo);
        rpc.addProperty(propertyTipoReporte);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(rpc);
        HttpTransportSE androidHttpTransport = null;

        try{
            System.out.println("asdasdsafsadfasdfsadfsadfsadfasdfsad");
            androidHttpTransport =  new HttpTransportSE(conexion);
            androidHttpTransport.call("http://service.BacTechnology.com/NuevoReporteMolde", envelope);

            SoapPrimitive resultsRequestSOAP = (SoapPrimitive) envelope.getResponse();

            res = Boolean.valueOf(envelope.getResponse().toString());

            return  res;
        }catch(Exception e){
            System.out.println("----------------------------------------------->");
            System.out.println(e.getMessage());
            res = false;
            System.out.println("----------------------------------------------->");
            return res;
        }
    }
}


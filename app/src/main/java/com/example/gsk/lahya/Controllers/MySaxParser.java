package com.example.gsk.lahya.Controllers;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.example.gsk.lahya.model.Subscriber;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MySaxParser {

    public MySaxParser(Context context) throws IOException {
        final List<Subscriber> subscribers = new ArrayList<>();
        File input = new File( Environment.getExternalStoragePublicDirectory( Environment.DIRECTORY_DOWNLOADS ).toString(), "READERSALLnew.xml" );


        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            parser.parse( input, new DefaultHandler() {

                Subscriber subscriber = null;
                String textContent;
                StringBuilder str = new StringBuilder();
                int validBuffer = 0;

                @Override
                public void startElement(String arg0, String arg1, String qName, Attributes attributes)
                        throws SAXException {
                    if (qName.equalsIgnoreCase( "G_ACT_NO" )) {
                        subscriber = new Subscriber();
//                    } else if (qName.equalsIgnoreCase("title")) {
//                        subscriber.setTitleAtt(attributes.getValue("xmlns"));
//                    } else if (qName.equalsIgnoreCase("ns")) {
//                        subscriber.setNsAtt(attributes.getValue("xmlns"));
//                    } else if (qName.equalsIgnoreCase("id")) {
//                        subscriber.setIdAtt(attributes.getValue("xmlns"));
//                    } else if (qName.equalsIgnoreCase("text")) {
//                        subscriber.setTextAtt(attributes.getValue("xml:space"));
                        str = new StringBuilder();
                        validBuffer = 1;
                    }
                }

                @Override
                public void endElement(String arg0, String arg1, String qName) throws SAXException {
                    if (qName.equalsIgnoreCase( "MAN_SERVICE_NO" )) {
                        subscriber.setSubNo( textContent );
                    } else if (qName.equalsIgnoreCase( "LOACTION_ID" )) {
                        subscriber.setLocationNo( textContent );
                    } else if (qName.equalsIgnoreCase( "CUST_NAME" )) {
                        subscriber.setSubName( textContent );
                    } else if (qName.equalsIgnoreCase( "SVTYPE" )) {
                        subscriber.setStatus( textContent );
                    } else if (qName.equalsIgnoreCase( "G_ACT_NO" )) {
                        subscribers.add( subscriber );
                    }
                }

                @Override
                public void characters(char[] chars, int start, int end) throws SAXException {
                    if (str != null && validBuffer == 1) {
                        for (int i = start; i < start + end; i++) {
                            str.append( chars[i] );
                        }
                    } else
                        textContent = String.copyValueOf( chars, start, end );
                }
            } );


            for (Subscriber sub : subscribers) {
                System.out.println(sub);
                Log.d("ttt",sub.getSubNo()+"   "+sub.getSubName()+"  "+sub.getStatus()+"   "+sub.getLocationNo()+"");
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SAXException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}

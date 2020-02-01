package com.test.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
 
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
 
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class Maputil {
	
	public String Map_util(String address) {
		
		String No_Blank_address = address;
		No_Blank_address = No_Blank_address.replace(" ", "");
		
		String lat = null;
		String lng = null;
		String lat_lng = null;
		
		BufferedReader br = null;
		
        //DocumentBuilderFactory 생성
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder;
        Document doc = null;
        
        try {
            //OpenApi호출
        	String urlstr = "https://maps.googleapis.com/maps/api/geocode/xml?"
                    + "key=AIzaSyBPJ7JPbS7aFMk5hf78sP4A_ivIC_razg0&"
        			+ "address="+No_Blank_address;
            
            URL url = new URL(urlstr);
            HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
       
            //응답 읽기
            br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "UTF-8"));
            String result = "";
            String line;
            while ((line = br.readLine()) != null) {
                result = result + line.trim();// result = URL로 XML을 읽은 값
            }
            
            // xml 파싱하기
            InputSource is = new InputSource(new StringReader(result));
            builder = factory.newDocumentBuilder();
            doc = builder.parse(is);
            
            XPathFactory xpathFactory = XPathFactory.newInstance();
            XPath xpath = xpathFactory.newXPath();
            
            // XPathExpression expr = xpath.compile("/response/body/items/item");
            XPathExpression expr = xpath.compile("/GeocodeResponse/result/geometry/location");
            NodeList nodeList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
            for (int i = 0; i < nodeList.getLength(); i++) {
                NodeList child = nodeList.item(i).getChildNodes();
                for (int j = 0; j < child.getLength(); j++) {
                    Node node = child.item(j);
                    if( node.getNodeName().equals("lat") ) {
                    	lat = node.getTextContent();
                    } else if( node.getNodeName().equals("lng") ) {
                    	lng = node.getTextContent();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        lat_lng = lat+"!"+lng;
		return lat_lng;
	}
}
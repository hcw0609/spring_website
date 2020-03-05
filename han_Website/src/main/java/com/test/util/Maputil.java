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
            
        	// URL 연결
            URL url = new URL(urlstr);
            HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
       
            // 연결한 URL에서 XML읽어와서 String으로 저장한다.
            br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "UTF-8"));
            String result = "";
            String line;
            while ((line = br.readLine()) != null) {
            	// result = 연결한 URL에서 읽어온 XML형태의 정보를 String으로 저장한다.
                result = result + line.trim();
            }
            
            // XML 파싱하기
            // String XML문자열에서 파싱할경우
            InputSource is = new InputSource(new StringReader(result));
            builder = factory.newDocumentBuilder();
            doc = builder.parse(is);
            
            // XPath 객체 생성
            XPath xpath = XPathFactory.newInstance().newXPath();
            
            // XPathExpression expr = xpath.compile("/response/body/items/item");
            XPathExpression expr = xpath.compile("/GeocodeResponse/result/geometry/location");
            //  expr.evaluate(대상, 타입)
            NodeList nodeList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
            
            if(nodeList.getLength() == 0) {
            	lat_lng = "실패";
            } else {
            	
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
                
                lat_lng = lat+"!"+lng;
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    
		return lat_lng;
	}
	
	
	/*
	public static void main(String[] args) throws Exception {

		Maputil mu = new Maputil();
		String str = mu.Map_util("g4sdf36hf");
		System.out.println(str);
		
	}
	*/
}

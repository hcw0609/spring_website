package com.test.util;

import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderRequest;
import com.google.code.geocoder.model.GeocoderResult;
import com.google.code.geocoder.model.GeocoderStatus;
import com.google.code.geocoder.model.LatLng;

public class MapUtil {

	public String geoCoding(String address) throws Exception {
		System.out.println("address2"+address);
		if( address == null ) {
			return null;
		}	
		
		System.out.println("address3"+address);
		Geocoder geocoder = new Geocoder();
		GeocoderRequest geocoderRequest = new GeocoderRequestBuilder()
				.setAddress(address)
				.setLanguage("ko").getGeocoderRequest();
		
		System.out.println("address3"+address);
		GeocodeResponse geocoderResponse;
		geocoderResponse = geocoder.geocode(geocoderRequest);

		System.out.println("address4"+address);
		
		if(geocoderResponse.getStatus() == GeocoderStatus.OK) {
			System.out.println("true1");
		} else {
			System.out.println("false2");
		}
		
		if(!geocoderResponse.getResults().isEmpty()) {
			System.out.println("true2");
		} else {
			System.out.println("false2");
		}
		
		try {
			if( geocoderResponse.getStatus() == GeocoderStatus.OK & !geocoderResponse.getResults().isEmpty() ) {
				System.out.println("address5"+address);
				GeocoderResult geocoderResult = geocoderResponse.getResults().iterator().next();
				LatLng latitudeLongitude = geocoderResult.getGeometry().getLocation();
					
				Float[] coords = new Float[2];
				coords[0] = latitudeLongitude.getLat().floatValue();
				coords[1] = latitudeLongitude.getLng().floatValue();
				String coordStr = Float.toString(coords[0])+"&"+Float.toString(coords[1]);
				System.out.println("coordStr"+coordStr);
				 
				return coordStr;		
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}

		
		return null;
	}	
}

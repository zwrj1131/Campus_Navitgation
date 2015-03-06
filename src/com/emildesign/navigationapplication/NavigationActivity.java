package com.emildesign.navigationapplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;




import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;



public class NavigationActivity extends FragmentActivity implements OnMarkerClickListener{

	
	
	private static final LatLng STARTINGPOINT = new LatLng(40.915823, -73.121903);
	private static final LatLng DESTINATION = new LatLng(40.915962, -73.126264);
	private static final LatLng fra = new LatLng(50.111772, 8.682632);
	private GoogleMap map;
	private SupportMapFragment fragment;
	private LatLngBounds latlngBounds;
	private ImageButton bNavigation;
	private ImageButton bSearch;
	private Polyline newPolyline;
	private boolean isTravelingToDestination = false;
	private int width, height;
	private double LAT,LNG;
    private EditText et ;
    private static String location = null ;
	private static double DLAT = 40.915962;
	private static double DLNG = -73.126264;
	private Marker myMarker_basketball_A;
	private Marker myMarker_basketball_B;
	private Marker myMarker_basketball_C;
	private Marker myMarker_basketball_D;
	private Marker myMarker_basketball_E;
	private Marker myMarker_food_A;
	private Marker myMarker_food_B;
	private Marker myMarker_food_C;
	private Marker myMarker_food_D;
	private Marker myMarker_food_E;
	private Marker myMarker_food_F;
	private Marker myMarker_food_G;
	private Marker myMarker_cafe_A;
	private Marker myMarker_cafe_B;
	private Marker myMarker_cafe_C;
	private Marker myMarker_ESE670_A;
	private Marker myMarker_CSE548_A;
	private Marker myMarker_CSE548_B;
	private Marker myMarker_ESL596_A;
	private Marker myMarker_ESL596_B;
	private Marker myMarker_gym_A;
	private Marker myMarker_gym_B;
	private Marker myMarker_gym_C;
	private Marker myMarker_gym_D;
	private Marker myMarker_gym_E;
	private String ifcourse = "no";
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	
	//	et = (EditText)findViewById(R.id.et_location);         
           //get reference to find the button
		//location = et.getText().toString();
       
         //get reference to EditText
		
	
		setContentView(R.layout.activity_navigation);				
		getSreenDimanstions();
	    fragment = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map));
		map = fragment.getMap(); 	
		
		map.setMyLocationEnabled(true);
		
		bNavigation = (ImageButton) findViewById(R.id.bNavigation);	
		bSearch = (ImageButton) findViewById(R.id.bSearch);	
		 et = (EditText) findViewById(R.id.et_location);
		   map.setOnMarkerClickListener(this);
		LocationManager lm =  (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		LocationListener ll = new mylocationlistener();
		lm.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,ll);
		
		  //get SharedPreferences object
		Context ctx = NavigationActivity.this;  	
		 final SharedPreferences sp = ctx.getSharedPreferences("SP", MODE_PRIVATE);
	        final Editor editor = sp.edit();
		
		
		
		    bSearch.setOnClickListener(new View.OnClickListener() {
		    	@Override
			        public void onClick(View v) {	    				             
		    		   map.clear();
			    	  location = et.getText().toString();	
			    	  int length = location.length();
			    	  int i;
			          int count =0;
			          
			         
			          for(i=0;i<length;i++)
			          {
			          char ch=location.charAt(i);
			    	  if (ch=='0'||ch=='1'||ch=='2'||ch=='3'||ch=='4'||ch=='5'||ch=='6'||ch=='7'||ch=='8'||ch=='9')
			    	  {
			    		  count++;
			    		  
			    	  }
			    	  
			          }
			          if(count==6||count==9||count==12||count==15||count==18||count==21||count==24)
			          {      
				       
			        	
			              editor.putString("user classes",location);
			              editor.putInt("INT_KEY", 0);
			              editor.putBoolean("BOOLEAN_KEY", true);
			              editor.commit();
			              //·µ»ØSTRING_KEYµÄÖµ
			              
			              
			             
			              
			        	  
			          }
			          
			          if(location.equals("myclass")||location.equals("my class"))
			          {
			        	  ifcourse = sp.getString("user classes", "");
			        	  int lengthstorecourse = ifcourse.length();
			        	  int bit=0;
				          char course[] = new char[100]; 
			        	  for(i=0;i<lengthstorecourse;i++)
				          {  char ch=ifcourse.charAt(i);
			              		  
				          
				          //load from database 
				    	  if (ch=='0'||ch=='1'||ch=='2'||ch=='3'||ch=='4'||ch=='5'||ch=='6'||ch=='7'||ch=='8'||ch=='9'||ch=='c'||ch=='s'||ch=='e'||ch=='l')
				    	  {
				    		  course[bit]=ch;
				    		  bit++;
				    		  
				    	  }
		
				    	  
				          }
			        	  
			        		  
			        		  
			        		  
			              if(  
			           		      (course[0]=='e'&course[1]=='s'&course[2]=='e'&course[3]=='6'&course[4]=='7'&course[5]=='0') || 
			            		  (course[6]=='e'&course[7]=='s'&course[8]=='e'&course[9]=='6'&course[10]=='7'&course[11]=='0')||
			            		  (course[12]=='e'&course[13]=='s'&course[14]=='e'&course[15]=='6'&course[16]=='7'&course[17]=='0')||
			            		  (course[18]=='e'&course[19]=='s'&course[20]=='e'&course[21]=='6'&course[22]=='7'&course[23]=='0'))
			              {
			            	  myMarker_ESE670_A=map.addMarker(new MarkerOptions()
					 	       .position(new LatLng(40.916077, -73.126067))
					 	        .title("Ese 670, In Physics Building, Room P122")
					    		 .snippet("Professor:Fan Ye,Thursday:1:00PM-3:50PM\nProfessor:Shan Lin, Wednesday 5:30PM-8:30PM"));
			            	  
			              }
			              
			              if((course[0]=='c'&course[1]=='s'&course[2]=='e'&course[3]=='5'&course[4]=='4'&course[5]=='8')||
			            		  (course[6]=='c'&course[7]=='s'&course[8]=='e'&course[9]=='5'&course[10]=='4'&course[11]=='8')||
			            		  (course[12]=='c'&course[13]=='s'&course[14]=='e'&course[15]=='5'&course[16]=='4'&course[17]=='8')||
			            		  (course[18]=='c'&course[19]=='s'&course[20]=='e'&course[21]=='5'&course[22]=='4'&course[23]=='8'))
			      	  
			              {  
			            	  
			            	  myMarker_CSE548_A=map.addMarker(new MarkerOptions()
				 	       .position(new LatLng(40.915725, -73.125182))
				 	        .title("Cse548, In Harriman hall, Room 137")
				    		 .snippet("Professor:R.Sekar, Tuseday&Thursday:11:30AM-12:50PM"));
				    		myMarker_CSE548_B=map.addMarker(new MarkerOptions()
					 	       .position(new LatLng(40.912523, -73.122149))
					 	        .title("Cse548, In Computer Science Building, Room 2120")
					    		 .snippet("Professor:Jie Gao, Tuseday&Thursday:10:00AM-11:20AM"));
			            	  
			              }
			              
			              if((course[18]=='e'&course[19]=='s'&course[20]=='l'&course[21]=='5'&course[22]=='9'&course[23]=='6')||
			            		  (course[0]=='e'&course[1]=='s'&course[2]=='l'&course[3]=='5'&course[4]=='9'&course[5]=='6')||
			            		  (course[6]=='e'&course[7]=='s'&course[8]=='l'&course[9]=='5'&course[10]=='9'&course[11]=='6')||
			                 	  (course[12]=='e'&course[13]=='s'&course[14]=='l'&course[15]=='5'&course[16]=='9'&course[17]=='6')
			            		  )
			              {
			            	  myMarker_ESL596_A=map.addMarker(new MarkerOptions()
					 	       .position(new LatLng(40.913240, -73.120226))
					 	        .title("Esl596, In Social And Behavioural Sciences Building, Room N115")
					    		 .snippet("Professor:Margaret Sobel, Monday&Wednesday:4:00PM-5:20PM"));
					    	    myMarker_ESL596_B=map.addMarker(new MarkerOptions()
						 	       .position(new LatLng(40.913281, -73.120214))
						 	        .title("Esl596, In Social And Behavioural Sciences Building, Room N111")
						    		 .snippet("Professor:Lynne Barsky, Monday&Wednesday:5:30PM-6:50PM"));
			            	  
			            	  
			              }
			              
			              if((course[18]=='e'&course[19]=='s'&course[20]=='e'&course[21]=='5'&course[22]=='0'&course[23]=='5')||
			            		  (course[0]=='e'&course[1]=='s'&course[2]=='e'&course[3]=='5'&course[4]=='0'&course[5]=='5')||
			            		  (course[6]=='e'&course[7]=='s'&course[8]=='e'&course[9]=='5'&course[10]=='0'&course[11]=='5')||
			                 	  (course[12]=='e'&course[13]=='s'&course[14]=='e'&course[15]=='5'&course[16]=='0'&course[17]=='5')
			            		  )
			              {
			            	
			            	  DLAT=40.913646;
				    		  DLNG=-73.125003;
				    		  map.addMarker(new MarkerOptions()
				 	       .position(new LatLng(DLAT, DLNG))
				 	        .title("Ese505, In Light Engineering Building, Room 154")
				 	         .snippet("Professor:Xin Wang, Tuesday 1:00PM-4:00PM"));
			            	  
			              }
			              
			        	  
			        	  
			        	
			        	  
			          
			        	  
			          }
			          
			          
			    	  
			    	  
			    	  
			    	 
		              
			    	  if(location.equals("SAC")||location.equals("sac"))
			    	  { 
			    		  DLAT=40.914505;
			    		  DLNG=-73.124266;
			    		  map.addMarker(new MarkerOptions()
			 	       .position(new LatLng(DLAT, DLNG))
			 	        .title("Student Activity Center(SAC)"));
			    		  LatLng  DESTINATION =new LatLng (DLAT,DLNG);
			    		  map.moveCamera(CameraUpdateFactory.newLatLngZoom(DESTINATION, 13));
			    	  }
			    	  
			    	  else if(location.equals("light engineering")||location.equals("lightengineering"))
			    	  { 
			    		  DLAT=40.913577;
			    		  DLNG=-73.124954;
			    		  map.addMarker(new MarkerOptions()
			 	       .position(new LatLng(DLAT, DLNG))
			 	        .title("Light Engineering Building"));
			    		  LatLng  DESTINATION =new LatLng (DLAT,DLNG);
			    		  map.moveCamera(CameraUpdateFactory.newLatLngZoom(DESTINATION, 13));
			    	  }
			    	  else if(location.equals("frey hall")||location.equals("freyhall"))
			    	  { 
			    		  DLAT=40.915288;
			    		  DLNG=-73.123908;
			    		  map.addMarker(new MarkerOptions()
			 	       .position(new LatLng(DLAT, DLNG))
			 	        .title("Frey Hall"));
			    		  LatLng  DESTINATION =new LatLng (DLAT,DLNG);
			    		  map.moveCamera(CameraUpdateFactory.newLatLngZoom(DESTINATION, 13));
			    	  } 
			    	  else if(location.equals("Physics")||location.equals("physics"))
			    	  { 
			    		  DLAT=40.916058;
			    		  DLNG=-73.126038;
			    		  map.addMarker(new MarkerOptions()
			 	       .position(new LatLng(DLAT, DLNG))
			 	        .title("Physics Building"));
			    		  LatLng  DESTINATION =new LatLng (DLAT,DLNG);
			    		  map.moveCamera(CameraUpdateFactory.newLatLngZoom(DESTINATION, 13));
			    	  }
			    	  else if(location.equals("wang center")||location.equals("wangcenter"))
			    	  { 
			    		  DLAT=40.915619;
			    		  DLNG=-73.119695;
			    		  map.addMarker(new MarkerOptions()
			 	       .position(new LatLng(DLAT, DLNG))
			 	        .title("Charles B.Wang Center"));
			    		  LatLng  DESTINATION =new LatLng (DLAT,DLNG);
			    		  map.moveCamera(CameraUpdateFactory.newLatLngZoom(DESTINATION, 13));
			    	  }
			    	  else if(location.equals("Jasmine")||location.equals("jasmine"))
			    	  { 
			    		  DLAT=40.915709;
			    		  DLNG=-73.119735;
			    		  map.addMarker(new MarkerOptions()
			 	       .position(new LatLng(DLAT, DLNG))
			 	        .title("Jasmine")
			 	         .snippet("In Charles B. Wang Center, Second Floor"));
			    		  LatLng  DESTINATION =new LatLng (DLAT,DLNG);
			    		  map.moveCamera(CameraUpdateFactory.newLatLngZoom(DESTINATION, 13));
			    	  }
			    	  else if(location.equals("stony brook union")||location.equals("Stony Brook Union"))
			    	  { 
			    		  DLAT=40.916889;
			    		  DLNG=-73.122245;
			    		  map.addMarker(new MarkerOptions()
			 	       .position(new LatLng(DLAT, DLNG))
			 	        .title("Stony Brook Union"));
			    		  LatLng  DESTINATION =new LatLng (DLAT,DLNG);
			    		  map.moveCamera(CameraUpdateFactory.newLatLngZoom(DESTINATION, 13));
			    	  }
			    	  else if(location.equals("math tower")||location.equals("math"))
			    	  { 
			    		  DLAT=40.915571;
			    		  DLNG=-73.126397;
			    		  map.addMarker(new MarkerOptions()
			 	       .position(new LatLng(DLAT, DLNG))
			 	        .title("Math Tower"));
			    		  LatLng  DESTINATION =new LatLng (DLAT,DLNG);
			    		  map.moveCamera(CameraUpdateFactory.newLatLngZoom(DESTINATION, 13));
			    	  }
			    	  
			    	  else if(location.equals("heavy engineering")||location.equals("heavyengineering"))
			    	  { 
			    		  DLAT=40.912742;
			    		  DLNG=-73.125840;
			    		  map.addMarker(new MarkerOptions()
			 	       .position(new LatLng(DLAT, DLNG))
			 	        .title("Heavy Engineering Building"));
			    		  LatLng  DESTINATION =new LatLng (DLAT,DLNG);
			    		  map.moveCamera(CameraUpdateFactory.newLatLngZoom(DESTINATION, 13));
			    	  }
			    	  else if(location.equals("computer science ")||location.equals("computerscience"))
			    	  { 
			    		  DLAT=40.912523;
			    		  DLNG=-73.122149;
			    		  map.addMarker(new MarkerOptions()
			 	       .position(new LatLng(DLAT, DLNG))
			 	        .title("Computer Sicence Building"));
			    		  LatLng  DESTINATION =new LatLng (DLAT,DLNG);
			    		  map.moveCamera(CameraUpdateFactory.newLatLngZoom(DESTINATION, 13));
			    	  }
			    	
			    	  else if(location.equals("chapin")||location.equals("Chapin"))
			    	  { 
			    		  DLAT=40.907917;
			    		  DLNG=-73.110328;
			    		  map.addMarker(new MarkerOptions()
			 	       .position(new LatLng(DLAT, DLNG))
			 	        .title("Chapin Commons"));
			    		  LatLng  DESTINATION =new LatLng (DLAT,DLNG);
			    		  map.moveCamera(CameraUpdateFactory.newLatLngZoom(DESTINATION, 13));
			    	  }
			    	  else if(location.equals("Schomburg")||location.equals("schomburg"))
			    	  { 
			    		  DLAT=40.913398;
			    		  DLNG=-73.132744;
			    		  map.addMarker(new MarkerOptions()
			 	       .position(new LatLng(DLAT, DLNG))
			 	        .title("Schomburg Commons"));
			    		  LatLng  DESTINATION =new LatLng (DLAT,DLNG);
			    		  map.moveCamera(CameraUpdateFactory.newLatLngZoom(DESTINATION, 13));
			    	  }
			    	  else if(location.equals("hospital")||location.equals("Hospital"))
			    	  { 
			    		  DLAT=40.908773;
			    		  DLNG=-73.114896;
			    		  map.addMarker(new MarkerOptions()
			 	       .position(new LatLng(DLAT, DLNG))
			 	        .title("Stony Brook Hospital"));
			    		  LatLng  DESTINATION =new LatLng (DLAT,DLNG);
			    		  map.moveCamera(CameraUpdateFactory.newLatLngZoom(DESTINATION, 13));
			    	  }
			    	  
			    	  
			    	  else if(location.equals("administration")||location.equals("administration building"))
			    	  {
			    		  DLAT=40.914719;
			    		  DLNG=-73.120906;
			    		  map.addMarker(new MarkerOptions()
			 	       .position(new LatLng(DLAT, DLNG))
			 	        .title("Administration"));
			    		  LatLng  DESTINATION =new LatLng (DLAT,DLNG);
			    		  map.moveCamera(CameraUpdateFactory.newLatLngZoom(DESTINATION, 13));
			    	  }
			    	  else if (location.equals("library")||location.equals("Library"))
			    	  {
			    		  DLAT=40.914923;
			    		  DLNG=-73.122503;
			    		  map.addMarker(new MarkerOptions()
			 	       .position(new LatLng(DLAT, DLNG))
			 	        .title("Frank Melville Jr Memorial Library"));
			    		  LatLng  DESTINATION =new LatLng (DLAT,DLNG);
			    		  map.moveCamera(CameraUpdateFactory.newLatLngZoom(DESTINATION, 13));
			    	  }
			    	  // daily life  search
			    	  else if (location.equals("gym")||location.equals("Gym"))
			    	  {  myMarker_gym_A=map.addMarker(new MarkerOptions()
			 	          .position(new LatLng(40.917128, -73.125416))
			 	           .title("Gym, In Stony Brook Sports Complex")
			 	            .snippet("Monday-Firday:6:00AM-12:00AM\n\nSaturday-Sunday:10:00AM-10:00PM " ));
			    	      myMarker_gym_B=map.addMarker(new MarkerOptions()
			 	          .position(new LatLng(40.917029, -73.123941))
			 	           .title("Gym, In Campus Recreation Center")
			 	            .snippet("Monday-Firday:6:00AM-12:00AM\n\nSaturday-Sunday:10:00AM-10:00PM" ));
			    	      myMarker_gym_C=map.addMarker(new MarkerOptions()
			 	          .position(new LatLng(40.914635, -73.123922))
			 	           .title("Wellness Center, In Student Activities Center, Room 307")
			 	          .snippet("Monday-Firday:6:00AM-12:00AM\n\nSaturday-Sunday:10:00AM-10:00PM" ));
			    	      myMarker_gym_D=map.addMarker(new MarkerOptions()
			 	          .position(new LatLng(40.907924, -73.110344))
			 	           .title("Gym, In Chapin Commons")
			 	          .snippet("Sunday-Firday:7:00PM-12:00AM\n\nSaturday:2:00PM-6:00PM" ));
			    	      myMarker_gym_E=map.addMarker(new MarkerOptions()
			 	          .position(new LatLng(40.912405, -73.134479))
			 	           .title("Gym, In West Apartment E")
			 	          .snippet("Monday-Firday:7:00AM-12:00AM\n\nSaturday-Sunday:7:00AM-10:00PM" ));
			    	  } 
			    	  
			    	 
			    	  else if (location.equals("cafe")||location.equals("Starbucks"))
			    	  {  myMarker_cafe_A=map.addMarker(new MarkerOptions()
			 	          .position(new LatLng(40.916889, -73.122245))
			 	           .title("Starbucks, In Stony Brook Uion")
			 	            .snippet("Monday-Firday:8:00AM-12:00AM\n\nSaturday-Sunday:11:00PM-12:00AM" ));
			    	      myMarker_cafe_B=map.addMarker(new MarkerOptions()
			 	          .position(new LatLng(40.910778, -73.123839))
			 	           .title("Starbucks, In Roth Food Court")
			 	            .snippet("Monday-Firday:8:00AM-12:00AM\n\nSaturday-Sunday:11:00PM-12:00AM" ));
			    	      myMarker_cafe_C=map.addMarker(new MarkerOptions()
			 	          .position(new LatLng(40.908630, -73.114958))
			 	           .title("Starbucks, In Hospital")
			 	          .snippet("Monday-Firday:8:00AM-12:00AM\n\nSaturday-Sunday:11:00PM-12:00AM" ));
			    	  } 
			    	  
			    	  else if (location.equals("basketball")||location.equals("Basketball"))
			    	  {
			    		  myMarker_basketball_A=map.addMarker(new MarkerOptions()
			 	          .position(new LatLng(40.917021, -73.123886))
			 	           .title("Campus Recreation Center,Indoor Basketball_court_A"));
			    		  myMarker_basketball_B=map.addMarker(new MarkerOptions()
			 	          .position(new LatLng(40.918108, -73.126250))
			 	           .title("Near Sports Complex,Outdoor Basketball_court_B"));
			    		  myMarker_basketball_C=map.addMarker(new MarkerOptions()
				 	       .position(new LatLng(40.912981, -73.133371))
				 	        .title("Near West Apartment,Outdoor Basketball_court_C"));
			    		  myMarker_basketball_D=map.addMarker(new MarkerOptions()
				 	       .position(new LatLng(40.910218, -73.125547))
				 	        .title("Near Table Drive,Outdoor Basketball_court_D"));
			    		  myMarker_basketball_E=map.addMarker(new MarkerOptions()
				 	       .position(new LatLng(40.920597, -73.120446))
				 	        .title("Near Stadium Rd,Outdoor Basketball_court_E"));
			    	  }
			    	  else if (location.equals("food")||location.equals("Food")||location.equals("Restaurant"))
			    	  {  myMarker_food_A=map.addMarker(new MarkerOptions()
			 	          .position(new LatLng(40.914615, -73.123789))
			 	           .title("Student Activities Center, Dining hour:")
			 	            .snippet("Monday-Firday:7:30AM-10:00PM\nSaturday-Sunday:12:00PM-6:00PM\n\nspecial:salad" ));
			    	      myMarker_food_B=map.addMarker(new MarkerOptions()
			 	          .position(new LatLng(40.916905, -73.122228))
			 	           .title("Stony brook union, Dining hour:")
			 	            .snippet("Monday-Firday:7:30AM-11:00PM\nSaturday-Sunday:9:00AM-11:00PM\n\nspecial:UNION DELI" ));
			    	      myMarker_food_C=map.addMarker(new MarkerOptions()
			 	          .position(new LatLng(40.913021, -73.129868))
			 	           .title("West Side Dining, Dining hour:")
			 	            .snippet("Monday-Firday:7:00AM-3:00AM\nSaturday-Sunday:7:00AM-3:00AM\n\nspecial:BOB's BBQ" ));
			    	      myMarker_food_D=map.addMarker(new MarkerOptions()
			 	          .position(new LatLng(40.910946, -73.123774))
			 	           .title("Roth Food Court, Dining hour:")
			 	            .snippet("Monday-Firday:11:30AM-1:00AM\nSaturday-Sunday:11:00AM-1:00AM\n\nspecial: beef comb" ));
			    	      myMarker_food_E=map.addMarker(new MarkerOptions()
			 	          .position(new LatLng(40.915652, -73.119708))
			 	           .title("Jasmine in Wang Center, Dining hour:")
			 	            .snippet("Monday-Firday:11:00AM-8:00PM\nSaturday-Sunday:12:00AM-8:00PM\n\nspecial: sushi" ));
			    	      myMarker_food_F=map.addMarker(new MarkerOptions()
			 	          .position(new LatLng(40.908773, -73.114896))
			 	           .title("Campus Dining Services in hospital_entrance A, Dining hour:")
			 	            .snippet("Monday-Firday:12:00AM-8:00PM\nSaturday-Sunday:12:00AM-8:00PM\n\nspecial: beef burger" ));
			    	      myMarker_food_G=map.addMarker(new MarkerOptions()
			 	          .position(new LatLng(40.910504, -73.117043))
			 	           .title("Campus Dining Services in hospital_entrance B, Dining hour:")
			 	            .snippet("Monday-Firday:12:00AM-8:00PM\nSaturday-Sunday:12:00AM-8:00PM\n\nspecial: beef burger" ));
			    	  }
			    	  // Course Search
			    	  else if (location.equals("ese670")||location.equals("ESE670")||location.equals("ESE 670")||location.equals("ese 670"))
			    	  {
			    		myMarker_ESE670_A=map.addMarker(new MarkerOptions()
			 	       .position(new LatLng(40.916077, -73.126067))
			 	        .title("Ese 670, In Physics Building, Room P122")
			    		 .snippet("Professor:Fan Ye,Thursday:1:00PM-3:50PM\nProfessor:Shan Lin, Wednesday 5:30PM-8:30PM"));
			    	  }
			    	  else if (location.equals("cse548")||location.equals("CSE548")||location.equals("CSE 548")||location.equals("cse 548"))
			    	  {
			    	    myMarker_CSE548_A=map.addMarker(new MarkerOptions()
			 	       .position(new LatLng(40.915725, -73.125182))
			 	        .title("Cse548, In Harriman hall, Room 137")
			    		 .snippet("Professor:R.Sekar, Tuseday&Thursday:11:30AM-12:50PM"));
			    		myMarker_CSE548_B=map.addMarker(new MarkerOptions()
				 	       .position(new LatLng(40.912523, -73.122149))
				 	        .title("Cse548, In Computer Science Building, Room 2120")
				    		 .snippet("Professor:Jie Gao, Tuseday&Thursday:10:00AM-11:20AM"));
			    	  }
			    	  else if (location.equals("esl596")||location.equals("ESL596")||location.equals("esl 596")||location.equals("ESL 596"))
			    	  {
			    	    myMarker_ESL596_A=map.addMarker(new MarkerOptions()
			 	       .position(new LatLng(40.913240, -73.120226))
			 	        .title("Esl596, In Social And Behavioural Sciences Building, Room N115")
			    		 .snippet("Professor:Margaret Sobel, Monday&Wednesday:4:00PM-5:20PM"));
			    	    myMarker_ESL596_B=map.addMarker(new MarkerOptions()
				 	       .position(new LatLng(40.913281, -73.120214))
				 	        .title("Esl596, In Social And Behavioural Sciences Building, Room N111")
				    		 .snippet("Professor:Lynne Barsky, Monday&Wednesday:5:30PM-6:50PM"));
			    	  }
			    	  else if (location.equals("ese505")||location.equals("ESE505")||location.equals("ese 505")||location.equals("ESE 505"))
			    	  {
			    		  { 
				    		  DLAT=40.913646;
				    		  DLNG=-73.125003;
				    		  map.addMarker(new MarkerOptions()
				 	       .position(new LatLng(DLAT, DLNG))
				 	        .title("Ese505, In Light Engineering Building, Room 154")
				 	         .snippet("Professor:Xin Wang, Tuesday 1:00PM-4:00PM"));
				    		  LatLng  DESTINATION =new LatLng (DLAT,DLNG);
				    		  map.moveCamera(CameraUpdateFactory.newLatLngZoom(DESTINATION, 13));
				    	  }
				    	  
			    	 
			    	  }
			    	  
			    	  // special search for freshman 
			    	  else if(location.equals("immigration")||location.equals("visa and immigration")||location.equals("Immigration"))
			    	  {
			    		  DLAT=40.914925;
			    		  DLNG=-73.122508;
			    		  map.addMarker(new MarkerOptions()
			 	       .position(new LatLng(DLAT, DLNG))
			 	        .title("Visa and Immigration Services Office")
			 	         .snippet("In Melville Library, Room E5310"));
			    		  LatLng  DESTINATION =new LatLng (DLAT,DLNG);
			    		  map.moveCamera(CameraUpdateFactory.newLatLngZoom(DESTINATION, 13));
			    	  }
			    	  
			    	  else if(location.equals("id card office")||location.equals("campus card")||location.equals("campus card office"))
			    	  {
			    		  DLAT=40.914719;
			    		  DLNG=-73.120875;
			    		  map.addMarker(new MarkerOptions()
			 	       .position(new LatLng(DLAT, DLNG))
			 	        .title("Campus Card Office")
			 	         .snippet("In Administration Building, Room 254"));
			    		  LatLng  DESTINATION =new LatLng (DLAT,DLNG);
			    		  map.moveCamera(CameraUpdateFactory.newLatLngZoom(DESTINATION, 13));
			    	  }
			    	  
			    	  else if(location.equals("student health center")||location.equals("student health")||location.equals("health Insurance"))
			    	  {
			    		  DLAT=40.919250;
			    		  DLNG=-73.121717;
			    		  map.addMarker(new MarkerOptions()
			 	       .position(new LatLng(DLAT, DLNG))
			 	        .title("Student Health Service")
			 	         .snippet("In Student Health Center"));
			    		  LatLng  DESTINATION =new LatLng (DLAT,DLNG);
			    		  map.moveCamera(CameraUpdateFactory.newLatLngZoom(DESTINATION, 13));
			    	  }
			    	  else if(location.equals("graduate school ")||location.equals("graduate school"))
			    	  { 
			    		  DLAT=40.912523;
			    		  DLNG=-73.122149;
			    		  map.addMarker(new MarkerOptions()
			 	       .position(new LatLng(DLAT, DLNG))
			 	        .title("Graduate School")
			 	         .snippet("In Computer Science Building Second Floor"));
			    		  LatLng  DESTINATION =new LatLng (DLAT,DLNG);
			    		  map.moveCamera(CameraUpdateFactory.newLatLngZoom(DESTINATION, 13));
			    	  }
			    	 
		    	
		
		    	}});
		   
		    
		    
		    
		    
		    
		    
		    bNavigation.setOnClickListener(new View.OnClickListener() {	
																	
			

			@Override
			public void onClick(View v) {											
				//hideSoftKeyboard(v);
				//get the place entered
				
				
					
				//Editable newTxt=(Editable)et.getText(); 
			//	String location = newTxt.toString();
			//	if(location=="s")
			//	{
			//		DLNG=-73.900566;
			//    	DLAT=40.590093;
			//		
			//	}
				
				
				LatLng  DESTINATION =new LatLng (DLAT,DLNG);
				LatLng  STARTINGPOINT = new LatLng(LAT,LNG);
				 map.moveCamera(CameraUpdateFactory.newLatLngZoom(STARTINGPOINT, 13));
				if (!isTravelingToDestination)
				{										
					
					findDirections(  STARTINGPOINT.latitude,  STARTINGPOINT.longitude,DESTINATION.latitude, DESTINATION.longitude, GMapV2Direction.MODE_WALKING);
				}
				//else
				//{
				//	isTravelingToParis = false;
				//	findDirections( AMSTERDAM.latitude, AMSTERDAM.longitude, FRANKFURT.latitude, FRANKFURT.longitude, GMapV2Direction.MODE_WALKING );  
				//}
			}
		});
		
		
		
	}
	
	
    class mylocationlistener implements LocationListener{


	@Override
	public  void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		if(location != null)
		{
			double lon = location.getLongitude();
			double lat = location.getLatitude();
			 LAT = lat;
			 LNG = lon;
			
		}
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}
	

	}
	
	@Override
	protected void onResume() {
		
		super.onResume();
    	latlngBounds = createLatLngBoundsObject(STARTINGPOINT, DESTINATION);
        map.moveCamera(CameraUpdateFactory.newLatLngBounds(latlngBounds, width, height, 150));

	}

	public void handleGetDirectionsResult(ArrayList<LatLng> directionPoints) {
		PolylineOptions rectLine = new PolylineOptions().width(5).color(Color.BLUE);

		for(int i = 0 ; i < directionPoints.size() ; i++) 
		{          
			rectLine.add(directionPoints.get(i));
		}
		if (newPolyline != null)
		{
			newPolyline.remove();
		}
		newPolyline = map.addPolyline(rectLine);
		if (isTravelingToDestination)
		{
			latlngBounds = createLatLngBoundsObject(STARTINGPOINT, DESTINATION);
	        map.animateCamera(CameraUpdateFactory.newLatLngBounds(latlngBounds, width, height, 300));
		}
		//else
		//{
		//	latlngBounds = createLatLngBoundsObject(AMSTERDAM, FRANKFURT);
	  //      map.animateCamera(CameraUpdateFactory.newLatLngBounds(latlngBounds, width, height, 150));
		//}
		
	}
	
	// infoWindowAdaper need future working.
	/*public class UserInfoWindowAdapter implements InfoWindowAdapter {
	        LayoutInflater inflater = null;
	        
	    	
	    	 public UserInfoWindowAdapter( LayoutInflater inflater) {
	                      this.inflater=inflater;
	    	 }
	    	@Override
	    	public View getInfoWindow(Marker marker) {
	    		return null;
	    	}
	    	
	    	@Override
	    	public View getInfoContents(Marker marker) {
	    		
	    	View infoWindows= inflater.inflate(R.layout.activity_navigation, null);
	    	TextView title = (TextView)infoWindows.findViewById(R.id.title);
	    	TextView description = (TextView)infoWindows.findViewById(R.id.snippet);
	    	
	    	title.setText(marker.getTitle());
	    	description.setText(marker.getSnippet());
	    	
	    	return (infoWindows);	

	    	}
	    }*/
	
	public  boolean onMarkerClick(final Marker marker) {

        if (marker.equals(myMarker_basketball_A) )
        {
        	 DLAT=40.917021;
    		  DLNG=-73.123886;
        }
        else if (marker.equals(myMarker_basketball_B))
        {
        	 DLAT=40.918108;
   		      DLNG=-73.126250;
        }
        else if (marker.equals(myMarker_basketball_C) )
        {
        	 DLAT=40.912981;
    		  DLNG=-73.133371;
        }
        else if (marker.equals(myMarker_basketball_D) )
        {
        	 DLAT=40.910218;
    		  DLNG=-73.125547;
        } 
        else if (marker.equals(myMarker_basketball_E) )
        {
        	 DLAT=40.908773;
    		  DLNG=-73.114896;
        }
        else if (marker.equals(myMarker_food_A) )
        {
        	 DLAT=40.914615;
    		  DLNG=-73.123789;
        }
        else if (marker.equals(myMarker_food_B) )
        {
        	 DLAT=40.914615;
    		  DLNG=-73.123789;
        }
        else if (marker.equals(myMarker_food_C) )
        {
        	 DLAT=40.913021;
    		  DLNG=-73.129868;
        }
        else if (marker.equals(myMarker_food_D) )
        {
        	 DLAT=40.910946;
    		  DLNG=-73.123774;
        }
        else if (marker.equals(myMarker_food_E) )
        {
        	 DLAT=40.915652;
    		  DLNG=-73.119708;
        }
        else if (marker.equals(myMarker_food_F) )
        {
        	 DLAT=40.908773;
    		  DLNG=-73.114896;
        }
        else if (marker.equals(myMarker_food_G) )
        {
        	 DLAT=40.910504;
    		  DLNG=-73.117043;
        }
        else if (marker.equals(myMarker_cafe_A) )
        {
        	 DLAT=40.916889;
    		  DLNG=-73.122245;
        }
        else if (marker.equals(myMarker_cafe_B) )
        {
        	 DLAT=40.910778;
    		  DLNG=-73.123839;
        }
        else if (marker.equals(myMarker_cafe_C) )
        {
        	 DLAT=40.908630;
    		  DLNG=-73.114958;
        }
        else if (marker.equals(myMarker_ESE670_A) )
        {
        	 DLAT=40.916077;
    		  DLNG=-73.126067;
        }
        else if (marker.equals(myMarker_CSE548_A) )
        {
        	 DLAT=40.915725;
    		  DLNG=-73.125182;
        }
        else if (marker.equals(myMarker_CSE548_B) )
        {
        	 DLAT=40.912523;
    		  DLNG=-73.122149;
        } 
        else if (marker.equals(myMarker_ESL596_A) )
        {
       	 DLAT=40.913240;
   		  DLNG=-73.120226;
       }
        else if (marker.equals(myMarker_ESL596_B) )
        {
       	 DLAT=40.913281;
   		  DLNG=-73.120214;
       }
        else if (marker.equals(myMarker_gym_A) )
        {
       	 DLAT=40.917128;
   		  DLNG=-73.125416;
       }
        else if (marker.equals(myMarker_gym_B) )
        {
       	 DLAT=40.917029;
   		  DLNG=-73.123941;
       }
        else if (marker.equals(myMarker_gym_C) )
        {
       	 DLAT=40.914635;
   		  DLNG=-73.123922;
       }
        else if (marker.equals(myMarker_gym_D) )
        {
       	 DLAT=40.907924;
   		  DLNG=-73.110344;
       }
        else if (marker.equals(myMarker_gym_E) )
        {
       	 DLAT=40.912405;
   		  DLNG=-73.134479;
       }
      
		return false ;
    }
	

	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.navigation, menu);
        return true;
}

	
	
	
	
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	switch (item.getItemId()) {
	    case R.id.bNormal:
	       map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
	        break;
	    case R.id.bSatellite:
	    	map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
	        break;
	    case R.id.bHybrid:
	    	map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
	        break;
	    case R.id.bTerrain:
	    	map.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
	        break;
	   
	        }
	        return super.onOptionsItemSelected(item);
	    }
	
	
	

	
	
	
	
	
	
	private void getSreenDimanstions()
	{
		Display display = getWindowManager().getDefaultDisplay();
		width = display.getWidth(); 
		height = display.getHeight(); 
	}
	
	private LatLngBounds createLatLngBoundsObject(LatLng firstLocation, LatLng secondLocation)
	{
		if (firstLocation != null && secondLocation != null)
		{
			LatLngBounds.Builder builder = new LatLngBounds.Builder();    
			builder.include(firstLocation).include(secondLocation);
			
			return builder.build();
		}
		return null;
	}
	
	public void findDirections(double fromPositionDoubleLat, double fromPositionDoubleLong, double toPositionDoubleLat, double toPositionDoubleLong, String mode)
	{
		Map<String, String> map = new HashMap<String, String>();
		map.put(GetDirectionsAsyncTask.USER_CURRENT_LAT, String.valueOf(fromPositionDoubleLat));
		map.put(GetDirectionsAsyncTask.USER_CURRENT_LONG, String.valueOf(fromPositionDoubleLong));
		map.put(GetDirectionsAsyncTask.DESTINATION_LAT, String.valueOf(toPositionDoubleLat));
		map.put(GetDirectionsAsyncTask.DESTINATION_LONG, String.valueOf(toPositionDoubleLong));
		map.put(GetDirectionsAsyncTask.DIRECTIONS_MODE, mode);
		
		GetDirectionsAsyncTask asyncTask = new GetDirectionsAsyncTask(this);
		asyncTask.execute(map);	
	}
}

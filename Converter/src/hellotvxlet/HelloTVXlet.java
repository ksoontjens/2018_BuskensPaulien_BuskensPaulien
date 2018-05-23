package hellotvxlet;

import javax.tv.xlet.*;
import org.havi.ui.*;
import org.davic.resources.*;
import org.havi.ui.event.*;
import org.dvb.ui.*;
import java.awt.*;
import java.awt.event.*;


public class HelloTVXlet implements Xlet, ResourceClient, HBackgroundImageListener, HActionListener {
    
    //background
    private HScreen screen;
    private HBackgroundDevice bgDevice;
    private HBackgroundConfigTemplate bgTemplate;
    private HStillImageBackgroundConfiguration bgconfiguration;
    private HBackgroundImage agrondimg=new HBackgroundImage("backgroundC.png");
    
    //buttons
    private XletContext actueleXletContext;
    private HScene scene;
    //debuggen activeren of niet
    private boolean debug=true;
    
    
    private HTextButton home,knop1, knop2, knop3, knop4, knop5, knop6, knop7, knop8, knop9, knop10, knop11, knop12;
    private HStaticText tekstLabel1, tekstLabel2, tekstLabel3, tekstLabel4, tekstLabel5, tekstLabel6, tekstLabel7, tekstLabel8;
                
    //background
    public void notifyRelease(ResourceProxy proxy){}
    public void release (ResourceProxy proxy) {}
    public boolean requestRelease (ResourceProxy proxy, Object requestData){return false;}
    
    public void imageLoaded(HBackgroundImageEvent e){
        try{
            bgconfiguration.displayImage(agrondimg);
        }
        catch(Exception s){
            System.out.println(s.toString());
        }
    }
     public void imageLoadFailed(HBackgroundImageEvent e){
         System.out.println("image kan niet geladen worden.");
     }
     
        
        public void initXlet(XletContext context)throws XletStateChangeException{
            
            if(debug) System.out.println("Xlet Initialiseren");
            this.actueleXletContext = context;
            //template aanmaken
            HSceneTemplate sceneTemplate = new HSceneTemplate();
            
            //grootte en positie aangeven
            sceneTemplate.setPreference(HSceneTemplate.SCENE_SCREEN_DIMENSION,new HScreenDimension(1.0f,1.0f),HSceneTemplate.REQUIRED);
            
            sceneTemplate.setPreference(HSceneTemplate.SCENE_SCREEN_LOCATION, new HScreenPoint(0.0f,0.0f),HSceneTemplate.REQUIRED);
            
            //een instantie van de Scene aanvragen aan de factory.
            scene = HSceneFactory.getInstance().getBestScene(sceneTemplate);
            
            
            home = new HTextButton("home");
            home.setLocation(280,400);
            home.setSize(180,50);
            home.setBackground(new DVBColor(103,224,206,179));
            home.setBackgroundMode(HVisible.BACKGROUND_FILL);
            
            //knoppen startpagina
            knop1 = new HTextButton("euro to");
            knop1.setLocation(280,220);
            knop1.setSize(180,50);
            knop1.setBackground(new DVBColor(103,224,206,179));
            knop1.setBackgroundMode(HVisible.BACKGROUND_FILL);
            
            knop2 = new HTextButton("to euro");
            knop2.setLocation(280,300);
            knop2.setSize(180,50);
            knop2.setBackground(new DVBColor(103,224,206,179));
            knop2.setBackgroundMode(HVisible.BACKGROUND_FILL);
            
            //knoppen Euro to
            knop3 = new HTextButton("1");
            knop3.setLocation (100,210);
            knop3.setSize(50,50);
            knop3.setBackground(new DVBColor(103,224,206,179));
            knop3.setBackgroundMode(HVisible.BACKGROUND_FILL);
            
            knop4 = new HTextButton("5");
            knop4.setLocation (160,210);
            knop4.setSize(50,50);
            knop4.setBackground(new DVBColor(103,224,206,179));
            knop4.setBackgroundMode(HVisible.BACKGROUND_FILL);
            
            knop5 = new HTextButton("Dollar");
            knop5.setLocation (500,210);
            knop5.setSize(200,50);
            knop5.setBackground(new DVBColor(103,224,206,179));
            knop5.setBackgroundMode(HVisible.BACKGROUND_FILL);
            
            knop6 = new HTextButton("Deense Kronen");
            knop6.setLocation (500,270);
            knop6.setSize(200,50);
            knop6.setBackground(new DVBColor(103,224,206,179));
            knop6.setBackgroundMode(HVisible.BACKGROUND_FILL);
            
            knop10 = new HTextButton("Dollar");
            knop10.setLocation (500,210);
            knop10.setSize(200,50);
            knop10.setBackground(new DVBColor(103,224,206,179));
            knop10.setBackgroundMode(HVisible.BACKGROUND_FILL);
            
            knop11 = new HTextButton("Deense Kronen");
            knop11.setLocation (500,270);
            knop11.setSize(200,50);
            knop11.setBackground(new DVBColor(103,224,206,179));
            knop11.setBackgroundMode(HVisible.BACKGROUND_FILL);
            
            knop12 = new HTextButton("1");
            knop12.setLocation (100,210);
            knop12.setSize(50,50);
            knop12.setBackground(new DVBColor(103,224,206,179));
            knop12.setBackgroundMode(HVisible.BACKGROUND_FILL);
            
            
            //knoppen To Euro
            knop7 = new HTextButton("dollar");
            knop7.setLocation (100,210);
            knop7.setSize(200,50);
            knop7.setBackground(new DVBColor(103,224,206,179));
            knop7.setBackgroundMode(HVisible.BACKGROUND_FILL);
            
            knop8 = new HTextButton("deense kroon");
            knop8.setLocation (100,270);
            knop8.setSize(200,50);
            knop8.setBackground(new DVBColor(103,224,206,179));
            knop8.setBackgroundMode(HVisible.BACKGROUND_FILL);
            
            knop9 = new HTextButton("1");
            knop9.setLocation (100,270);
            knop9.setSize(50,50);
            knop9.setBackground(new DVBColor(103,224,206,179));
            knop9.setBackgroundMode(HVisible.BACKGROUND_FILL);
            
            
            home.setFocusTraversal(null,null,null,null);
            
            //knoppen startpagina
            knop1.setFocusTraversal(null, knop2, null, null); //op, neer, links, rechts
            knop2.setFocusTraversal(knop1,null,null,null);
            
            //knoppen euro to
            knop3.setFocusTraversal(null,null,null, knop4);
            knop4.setFocusTraversal(null,null,knop3,null);
            knop5.setFocusTraversal(knop6,knop6,null,null);
            knop6.setFocusTraversal(knop5,null,null,null);
            knop10.setFocusTraversal(knop11,knop11,null,null);
            knop11.setFocusTraversal(knop10,knop10,null,null);
            
            //knoppen To euro
            knop7.setFocusTraversal(null,knop8,null,null);
            knop8.setFocusTraversal(knop7,null,null,null);
            knop9.setFocusTraversal(null,null,null,null);
            
                    
            scene.add(knop1);
            scene.add(knop2);
            
            knop1.requestFocus();
            
            //homebutton
            home.setActionCommand("home_actioned");
            
            //knoppen startpagina
            knop1.setActionCommand("knop1_actioned");
            knop2.setActionCommand("knop2_actioned");
            
            //knoppen euro to
            knop3.setActionCommand("knop3_actioned");
            knop4.setActionCommand("knop4_actioned");
            knop5.setActionCommand("knop5_actioned");
            knop6.setActionCommand("knop6_actioned");
            knop10.setActionCommand("knop10_actioned");
            knop11.setActionCommand("knop11_actioned");
            knop12.setActionCommand("knop12_actioned");
            
            //knopen To euro
            knop7.setActionCommand("knop7_actioned");
            knop8.setActionCommand("knop8_actioned");
            knop9.setActionCommand("knop9_actioned");
            
            
            home.addHActionListener(this);
            
            //knopen startpagina
            knop1.addHActionListener(this);
            knop2.addHActionListener(this);
            
            //knopen euro to
            knop3.addHActionListener(this);
            knop4.addHActionListener(this);
            knop5.addHActionListener(this);
            knop6.addHActionListener(this);
            knop10.addHActionListener(this);
            knop11.addHActionListener(this);
            knop12.addHActionListener(this);
            
            //knopen To euro
            knop7.addHActionListener(this);
            knop8.addHActionListener(this);
            knop9.addHActionListener(this);
            
            
            //tekst vlakken
            tekstLabel1 = new HStaticText("Euro to");
            tekstLabel1.setLocation(100,150);
            tekstLabel1.setSize(280,50);
            tekstLabel1.setBackground(new DVBColor(103,224,206,179));
            tekstLabel1.setBackgroundMode(HVisible.BACKGROUND_FILL);
            
            tekstLabel2 = new HStaticText("1 Euro is 1.18 Dollar");
            tekstLabel2.setLocation(150,250);
            tekstLabel2.setSize(400,50);
            tekstLabel2.setBackground(new DVBColor(103,224,206,179));
            tekstLabel2.setBackgroundMode(HVisible.BACKGROUND_FILL);
            
            tekstLabel3 = new HStaticText("1 Euro is 7.45 Deense Kroon");
            tekstLabel3.setLocation(150,250);
            tekstLabel3.setSize(400,50);
            tekstLabel3.setBackground(new DVBColor(103,224,206,179));
            tekstLabel3.setBackgroundMode(HVisible.BACKGROUND_FILL);
            
            tekstLabel4 = new HStaticText("To Euro");
            tekstLabel4.setLocation(100,150);
            tekstLabel4.setSize(400,50);
            tekstLabel4.setBackground(new DVBColor(103,224,206,179));
            tekstLabel4.setBackgroundMode(HVisible.BACKGROUND_FILL);
            
            tekstLabel5 = new HStaticText("1 dollar is 0.85 euro");
            tekstLabel5.setLocation(150,250);
            tekstLabel5.setSize(400,50);
            tekstLabel5.setBackground(new DVBColor(103,224,206,179));
            tekstLabel5.setBackgroundMode(HVisible.BACKGROUND_FILL);
            
            tekstLabel6 = new HStaticText("5 euro is 5,89 dollar");
            tekstLabel6.setLocation(150,250);
            tekstLabel6.setSize(400,50);
            tekstLabel6.setBackground(new DVBColor(103,224,206,179));
            tekstLabel6.setBackgroundMode(HVisible.BACKGROUND_FILL);
            
            tekstLabel7 = new HStaticText("5 euro is 37,24 deense kronen");
            tekstLabel7.setLocation(150,250);
            tekstLabel7.setSize(400,50);
            tekstLabel7.setBackground(new DVBColor(103,224,206,179));
            tekstLabel7.setBackgroundMode(HVisible.BACKGROUND_FILL);
            
            tekstLabel8 = new HStaticText("5 euro is 37,24 deense kronen");
            tekstLabel8.setLocation(150,250);
            tekstLabel8.setSize(400,50);
            tekstLabel8.setBackground(new DVBColor(103,224,206,179));
            tekstLabel8.setBackgroundMode(HVisible.BACKGROUND_FILL);
            
            //HScreen object opvragen
            screen = HScreen.getDefaultHScreen();
            
            //HBackgroundDevice opvragen
            bgDevice = screen.getDefaultHBackgroundDevice();
            
            //HBackgroundDevice proberen te reserveren
            if(bgDevice.reserveDevice(this)){
                System.out.println("BackgroundImage device has been reserved");
                
            }else{
                System.out.println("Background image device cannot be reserved");
            }
            
            //template maken
            bgTemplate = new HBackgroundConfigTemplate();
            
            //configuratieinstellingBackground "STILL_IMAGE"
            bgTemplate.setPreference(HBackgroundConfigTemplate.STILL_IMAGE,HBackgroundConfigTemplate.REQUIRED);
            
            //configuratie aanvragen en activeren indien OK
            bgconfiguration = (HStillImageBackgroundConfiguration)bgDevice.getBestConfiguration(bgTemplate);
            
            try{
                bgDevice.setBackgroundConfiguration(bgconfiguration);
            }
                catch(java.lang.Exception e){
                    System.out.println(e.toString());
                }
            }
        
        public void actionPerformed(ActionEvent e){
                     
             System.out.println(e.getActionCommand());
             
              if(e.getActionCommand() == "knop1_actioned"){
                scene.remove(knop1);
                scene.remove(knop2);
                scene.add(knop3);
                scene.add(knop4);
                
                scene.add(tekstLabel1);
                scene.repaint();
                knop3.requestFocus();
                System.out.println("Euro to");
                
            }
             
             if(e.getActionCommand()== "knop2_actioned"){
                scene.removeAll();
                scene.add(knop7);
                scene.add(knop8);
                scene.add(tekstLabel4);
                knop7.requestFocus();
                scene.repaint();
                System.out.println("To Euro");
                
            } 
             if(e.getActionCommand()== "knop3_actioned"){
                    scene.removeAll();
                    scene.add(knop5);
                    scene.add(knop6);
                    scene.repaint();
                    knop5.requestFocus();
                }
             
             if(e.getActionCommand()=="knop4_actioned"){
                 scene.removeAll();
                 scene.add(knop10);
                 scene.add(knop11);
                 scene.repaint();
                 knop10.requestFocus();
                 
             }
             
              if(e.getActionCommand()=="knop5_actioned"){
                        scene.removeAll();
                        scene.add(tekstLabel2);
                        scene.add(home);
                        scene.repaint();
                        home.requestFocus();
                    }
             
             if(e.getActionCommand()=="knop6_actioned"){
                        scene.removeAll();
                        scene.add(tekstLabel3);
                        scene.add(home);
                        home.requestFocus();
                        scene.repaint();
             }
             
             if(e.getActionCommand()=="knop7_actioned"){
                 scene.removeAll();
                 scene.add(knop9);
                 scene.repaint();
                 knop9.requestFocus();
                 
             }
             
             if(e.getActionCommand()=="knop8_actioned"){
                 scene.removeAll();
                 scene.add(knop12);
                 knop12.requestFocus();
                 scene.repaint();
             }
             
             if(e.getActionCommand()=="knop9_actioned"){
                 scene.remove(knop9);
                 scene.add(tekstLabel5);
                 scene.add(home);
                 scene.repaint();
                 home.requestFocus();
             }
             
             if(e.getActionCommand()=="knop10_actioned"){
                 scene.removeAll();
                 scene.add(tekstLabel6);
                 scene.add(home);
                 scene.repaint();
                 home.requestFocus();
                 
             }
             
             if(e.getActionCommand()== "knop11_actioned"){
                 scene.removeAll();
                 scene.add(tekstLabel7);
                 scene.add(home);
                 scene.repaint();
                 home.requestFocus();
             }
             
             if(e.getActionCommand()=="knop12_actioned"){
                 scene.remove(knop12);
                 scene.add(tekstLabel8);
                 scene.add(home);
                 home.requestFocus();
                 scene.repaint();
                    
             }
             
             if(e.getActionCommand()=="home_actioned"){
                 scene.removeAll();
                 scene.add(knop1);
                 scene.add(knop2);
                 scene.repaint();
                 knop1.requestFocus();
                 
             }
             
        }
        
            public void startXlet(){
                System.out.println("startXlet");
                //image laden
                agrondimg.load(this);
                
                
                if(debug) System.out.println("Xlet Starten");
                
                //scene zichtbaar maken
                
                scene.validate();
                scene.setVisible(true);
            }
            
            public void pauseXlet(){
                System.out.println("pauseXlet");
            }
            
            public void destroyXlet(boolean unconditional){
                System.out.println("destroyXlet");
                agrondimg.flush();
            }
        }
    
       
        
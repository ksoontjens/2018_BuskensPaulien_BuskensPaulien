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
    
    
    private HTextButton knop1, knop2, knop3, knop4, knop5, knop6, knop7, knop8, knop9;
    private HStaticText tekstLabel1, tekstLabel2, tekstLabel3, tekstLabel4, tekstLabel5, tekstLabel6;
                
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
            
            
            //knoppen startpagina
            knop1.setFocusTraversal(null, knop2, null, null); //op, neer, links, rechts
            knop2.setFocusTraversal(knop1,null,null,null);
            
            //knoppen euro to
            knop3.setFocusTraversal(null,null,null, knop4);
            knop4.setFocusTraversal(null,null,knop3,null);
            knop5.setFocusTraversal(knop6,knop6,null,null);
            knop6.setFocusTraversal(knop5,null,null,null);
            
            //knoppen To euro
            knop7.setFocusTraversal(null,knop8,null,null);
            knop8.setFocusTraversal(knop7,null,null,null);
            knop9.setFocusTraversal(null,null,null,null);
                    
            scene.add(knop1);
            scene.add(knop2);
            
            knop1.requestFocus();
            
            //knoppen startpagina
            knop1.setActionCommand("knop1_actioned");
            knop2.setActionCommand("knop2_actioned");
            
            //knoppen euro to
            knop3.setActionCommand("knop3_actioned");
            knop4.setActionCommand("knop4_actioned");
            knop5.setActionCommand("knop5_actioned");
            knop6.setActionCommand("knop6_actioned");
            
            //knopen To euro
            knop7.setActionCommand("knop7_actioned");
            knop8.setActionCommand("knop8_actioned");
            knop9.setActionCommand("knop9_actioned");
            
            //knopen startpagina
            knop1.addHActionListener(this);
            knop2.addHActionListener(this);
            
            //knopen euro to
            knop3.addHActionListener(this);
            knop4.addHActionListener(this);
            knop5.addHActionListener(this);
            knop6.addHActionListener(this);
            
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
            
            tekstLabel6 = new HStaticText("1 deense kroon is 0.13 euro");
            tekstLabel6.setLocation(150,250);
            tekstLabel6.setSize(400,50);
            tekstLabel6.setBackground(new DVBColor(103,224,206,179));
            tekstLabel6.setBackgroundMode(HVisible.BACKGROUND_FILL);
            
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
                System.out.println("Euro to");
                
            }
             
             if(e.getActionCommand()== "knop2_actioned"){
                scene.remove(knop1);
                scene.remove(knop2);
                scene.add(knop7);
                scene.add(knop8);
                scene.add(tekstLabel4);
                System.out.println("To Euro");
                
            }
             if(e.getActionCommand()== "knop3_actioned"){
                    scene.remove(knop3);
                    scene.remove(knop4);
                    scene.add(knop5);
                    scene.add(knop6);
                   
                    
                }
             
              if(e.getActionCommand()=="knop5_actioned"){
                        scene.remove(knop5);
                        scene.remove(knop6);
                        scene.remove(tekstLabel1);
                        scene.add(tekstLabel2);
                    }
             
             if(e.getActionCommand()=="knop6_actioned"){
                        scene.remove(knop5);
                        scene.remove(knop6);
                        scene.remove(tekstLabel1);
                        scene.add(tekstLabel4);
             }
             
             if(e.getActionCommand()=="knop7_actioned"){
                 scene.remove(knop7);
                 scene.remove(knop8);
                 scene.add(knop9);
                 
             }
             
             if(e.getActionCommand()=="knop9_actioned"){
                 scene.remove(knop9);
                 scene.add(tekstLabel5);
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
    
       
        
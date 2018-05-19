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
    
    
    private HTextButton knop1, knop2, knop3, knop4;
    private HStaticText tekstLabel1;
                
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
            
            
            //knoppen
            knop1 = new HTextButton("euro to");
            knop1.setLocation(280,220);
            knop1.setSize(180,50);
            knop1.setBackground(new DVBColor(204,40,40,179));
            knop1.setBackgroundMode(HVisible.BACKGROUND_FILL);
            
            knop2 = new HTextButton("to euro");
            knop2.setLocation(280,300);
            knop2.setSize(180,50);
            knop2.setBackground(new DVBColor(204,40,40,179));
            knop2.setBackgroundMode(HVisible.BACKGROUND_FILL);
            
            knop3 = new HTextButton("1");
            knop3.setLocation (100,210);
            knop3.setSize(50,50);
            knop3.setBackground(new DVBColor(204,40,40,179));
            knop3.setBackgroundMode(HVisible.BACKGROUND_FILL);
            
            knop4 = new HTextButton("5");
            knop4.setLocation (160,210);
            knop4.setSize(50,50);
            knop4.setBackground(new DVBColor(204,40,40,179));
            knop4.setBackgroundMode(HVisible.BACKGROUND_FILL);
            
            
            knop1.setFocusTraversal(null, knop2, null, null); //op, neer, links, rechts
            knop2.setFocusTraversal(knop1,null,null,null);
            knop3.setFocusTraversal(null,null,null, knop4);
            knop4.setFocusTraversal(null,null,knop3,null);
            
            scene.add(knop1);
            scene.add(knop2);
            
            knop1.requestFocus();
            
            knop1.setActionCommand("knop1_actioned");
            knop2.setActionCommand("knop2_actioned");
            knop3.setActionCommand("knop3_actioned");
            knop3.setActionCommand("knop4_actioned");
            
            knop1.addHActionListener(this);
            knop2.addHActionListener(this);
            knop3.addHActionListener(this);
            knop4.addHActionListener(this);
            
            
            
            //tekst vlakken
            tekstLabel1 = new HStaticText("Euro to");
            tekstLabel1.setLocation(100,150);
            tekstLabel1.setSize(280,50);
            tekstLabel1.setBackground(new DVBColor(204,40,40,179));
            tekstLabel1.setBackgroundMode(HVisible.BACKGROUND_FILL);
            
            
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
                
            }else if(e.getActionCommand()== "knop2_actioned"){
                scene.remove(knop1);
                scene.remove(knop2);
                scene.add(knop3);
                scene.add(knop4);
                scene.add(tekstLabel1);
                System.out.println("To Euro");
                
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
    
       
        
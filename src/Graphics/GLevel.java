/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics;

import Listeners.CrearObjeto;
import Listeners.MoverFondo;
import Logic.Level;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Jeffer
 */
public final class GLevel extends JPanel{
    
    private JLabel LScore;
    String Color;
    private JButton pause,reset;
    private Image fondo;
    private Timer tfondo,tcrearobjetivos;
    private GCar carro;
    private GObjective[] objetivos;
    private MoverFondo moverfondo;
    private int xim;
    public boolean crearobjeto;
    private CrearObjeto co;
    
    public GLevel(String fondo,int puntajeMeta, String color,Level l) {
        this.setLayout(null);
        this.setFocusable(true);
        this.initComponents(fondo,puntajeMeta,color, l);
        this.addComponents();
        this.setBoundsComponents();
    } 
    
    public void initComponents(String fondo,int puntajeMeta,String color,Level l){
        this.crearobjeto = true;
        this.fondo = loadImage(fondo);
        this.xim = 0;
        this.objetivos = new GObjective[6];
        for (int i = 0; i < 6; i++) {
            objetivos[i] = new GObjective(this, (int) (Math.random() * 10 + 21), (int) (Math.random() * 3 + 1), (int) (Math.random() * 3 + 1));
            objetivos[i].y =0;
        }
        this.carro = new GCar(this,puntajeMeta,color,l);
        this.moverfondo = new MoverFondo(this);
        this.co = new CrearObjeto(this);
        this.addKeyListener(carro.getLCar());
        this.LScore = new JLabel("Puntaje: ");
        this.pause = new JButton("Pausa");
        this.reset = new JButton("Resetear");
        this.tfondo = new Timer(5, moverfondo);
        this.tcrearobjetivos = new Timer(500, co);
        this.tfondo.start();
        this.tcrearobjetivos.start();
    }
    
    public void addComponents(){
        this.add(this.LScore);
        //this.add(this.pause);
        //this.add(this.reset);
    }
    
    public void setBoundsComponents(){
        this.LScore.setBounds(0, 0, 100, 20);
        this.pause.setBounds(0, 30, 100, 20);
        this.reset.setBounds(0, 60, 100, 20);
    }
    
    public Image loadImage(String imageRute){
        return new ImageIcon(imageRute).getImage();
    }
    
    private void setFondo(Graphics g){
        if (this.fondo != null){                       
            g.drawImage(fondo, 0, 0, getWidth(),getHeight(),xim,0,xim +1000,1024,this);
            this.setOpaque(false);
        }
    }
    
    public void setCrearObjeto(boolean newo){
        this.crearobjeto = newo;
    }

    @Override
    protected void paintComponent(Graphics g) {
        setFondo(g);       
        if(carro.g == null)
            carro.g = g;
        for (int i = 0; i < 6; i++) {
            if(objetivos[i].g == null)
                objetivos[i].g = g;
        } 
        
        if (crearobjeto) {
            int random = (int) (Math.random() * 6);
            if (objetivos[random].finished()) {
                objetivos[random].resetObjective();
            }
        }
        for (int i = 0; i < 6; i++) {
            new Thread(objetivos[i]).start();
        }
        for (int i = 0; i < 6; i++) {
            carro.checkColision(objetivos[i], this);
        }
        super.paintComponent(g);
        
        carro.dibuja(g);
        objetivos[0].dibuja(g);
        objetivos[1].dibuja(g);
        objetivos[2].dibuja(g);
        objetivos[3].dibuja(g);
        objetivos[4].dibuja(g);
        objetivos[5].dibuja(g);
        
    }

    public int getXim() {
        return xim;
    }

    public JLabel getLScore() {
        return LScore;
    }

    public void setXim(int xim) {
        this.xim = xim;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Listeners;

import Logic.Level;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Sergio Romero
 */
public class resetLitener implements ActionListener{

    private Level nivel;

    public resetLitener(Level nivel) {
        this.nivel = nivel;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
    
    
}

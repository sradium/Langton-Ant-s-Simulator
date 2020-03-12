
package Logica;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.UIManager;

public class Ventana extends javax.swing.JFrame implements Runnable{
    
    // Variables que controlan del juego
    private static final long serialVersionUID = 1l;
    
    private static volatile boolean funcionamiento = false;
    private static volatile boolean pausa = false;

    private static Thread thread;
    private static Temporizador tem;
    private javax.swing.JLabel[][] tablero = new javax.swing.JLabel[50][50];
    private Ecosistema Habitat;
    private int dia = 0;
    private int c = 0;
    //Fin variables
    
    /*
    * Hilo que permite el movimiento sin afectar la funcionabilidad de
    * los botones
    */
    public void run() {

        final int MS_POR_S = 1000;
        final byte APS_OBJETIVO = 5;
        final double MS_POR_A =MS_POR_S / APS_OBJETIVO;
        
        long referenciaA = System.currentTimeMillis();
        long referenciaC = System.currentTimeMillis();
        double tiempo;
        double delta = 0;
        
        if(!pausa){
            Habitat = new Ecosistema();
            dia++;
            mostrar();
        }
        
        while(funcionamiento)
        {
            final long inicioBucle = System.currentTimeMillis();
            tiempo = inicioBucle - referenciaA;
            referenciaA = inicioBucle;
            delta += tiempo / MS_POR_A;
            
            if(System.currentTimeMillis()- referenciaC > MS_POR_S)
            {
                tem.click();
                if(((tem.getH()*3600)+(tem.getM()*60)+tem.getS())%24 == 0){
                    dia++;
                    c++;
                }
                Dia.setText("Dia "+String.valueOf(dia));
                Dia1.setText("Dia  "+String.valueOf(dia));
                referenciaC = System.currentTimeMillis();
            }
            
            while(delta >= 1)
            {
                actualizar();
                delta--;
            }
        }
    }
    //Fin hilo
    
    /*
    * Permite actualizar los elementos moviendolos según correspondan en 
    * la matriz y también controla cuando se reproducen las plantas
    */
    private void actualizar()
    {
        Habitat.movimiento();
        if(c == 2){
            Habitat.fotosintesis();
            c = 0;
        }
        mostrar();
    }
    //Fin procedimiento
      
    /*
    * Muestra los cambios en el juego al reasignar los iconos de la labels con
    * su respectivo elemento de la matriz y actualiza el cronómetro en pantalla
    */
    private void mostrar()
    {
        Tiempo.setText(tem.tiempo());
        
        for(int j = 0; j < 50; j++){
            for(int i = 0; i<50; i++){
                tablero[j][i].setIcon(Habitat.color(j, i));
            }
        }
    }
    //Fin procedimiento
    
    //Inicia el hilo
    private synchronized void iniciar()
    {
        funcionamiento = true;
        thread = new Thread(this, "Graficos");
        thread.start();
    }
    //Fin procedimiento
    
    //Detiene el hilo
    private synchronized void detener()
    {
        funcionamiento = false;
        
        try {
            thread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // Fin procedimiento
      
    //Constructor de la ventana donde se visualiza el juego
    public Ventana() {
        initComponents();
        this.setSize(580, 720);
        this.setLocationRelativeTo(null);
        this.setTitle("Langton's Ant");
        jDialog1.setLocationRelativeTo(null);
        for(int j = 0; j < tablero.length ; j++){
            for(int i = 0; i < tablero.length ; i++){
                tablero[j][i] = new javax.swing.JLabel();
                tablero[j][i].setIcon(new ImageIcon(getClass().getResource("/Icons/Tierra.png")));
                jPanel1.add(tablero[j][i], new org.netbeans.lib.awtextra.AbsoluteConstraints((0+(8*j)), (0+(8*i)), 8, 8));
            }
        }
        tem = new Temporizador();
    }
    //Fin constructor 
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        TI = new javax.swing.JLabel();
        TISombra = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        Titulo = new javax.swing.JLabel();
        Icono = new javax.swing.JLabel();
        Tiempo = new javax.swing.JLabel();
        Dia1 = new javax.swing.JLabel();
        Dia = new javax.swing.JLabel();
        Ciclo = new javax.swing.JButton();
        Detener = new javax.swing.JButton();
        Ayuda = new javax.swing.JButton();
        Fondo = new javax.swing.JLabel();

        jDialog1.setTitle("Ayuda");
        jDialog1.setResizable(false);
        jDialog1.setSize(new java.awt.Dimension(425, 390));
        jDialog1.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TI.setFont(new java.awt.Font("Impact", 0, 24)); // NOI18N
        TI.setForeground(new java.awt.Color(255, 255, 255));
        TI.setText("INSTRUCCIONES");
        jDialog1.getContentPane().add(TI, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 8, 380, 30));

        TISombra.setFont(new java.awt.Font("Impact", 0, 24)); // NOI18N
        TISombra.setText("INSTRUCCIONES");
        jDialog1.getContentPane().add(TISombra, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 380, 30));

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        jScrollPane1.setViewportBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        jTextArea1.setRows(5);
        jTextArea1.setText("Simulación de un ecosistema simplificado \nbasado en el algoritmo de la hormiga de \nLangton.\n\nEn 1986, Christopher Langton creó un \nalgoritmo sencillo para un tipo especial \nde autómatas celulares llamado Hormiga \nde Langton. Funciona en una malla \ncuadriculada bidimensional, que en \nnuestro caso es de 50x50. La hormiga \npuede avanzar a lo largo y ancho de la \ncuadrícula según las siguientes reglas:\n\n● La hormiga da un paso adelante.\n● Si la hormiga consigue un cuadro con \nuna planta, se la come y luego gira 90° \nhacia la derecha y avanza un cuadro. \n● Si la hormiga consigue un cuadro vacío, \ngira 90° a la izquierda y avanza un cuadro. \n● Si la hormiga se consigue con otra, se \nclona (solo un clon) y avanza un cuadro.\n● Si la hormiga alcanza el borde de la \ncuadrícula, gira de forma aleatoria a la \nizquierda o a la derecha y avanza un \ncuadro. \n\nExisten los siguientes seres vivos: \nplantas, hormigas y osos hormigueros.\nUn ser vivo nace, se alimenta, se \nreproduce y muere. \n\nReglas para los osos hormigueros:\n\n● Un OH (Oso hormiguero) da un paso \nadelante.\n● Si un OH consigue a una hormiga, \nse la come y luego gira 90° hacia la \nderecha y avanza un cuadro. \n● Si un OH consigue un cuadro vacío o \ncon una planta, gira 90° a la izquierda \ny avanza un cuadro.  \n● Si un OH se consigue con otro OH de \nsexo opuesto, se reproduce (solo un hijo) \ny avanza un cuadro. \n● Si un OH se consigue con otro OH \ndel mismo sexo, pelea y queda vivo el que \nmás hijos haya procreado. En el caso en \nque los OH hayan procreado igual \ncantidad de hijos, giran 180° y avanzan \nun cuadro. \n\n● Si un OH alcanza el borde de la \ncuadrícula, gira de forma aleatoria a la \nizquierda o a la derecha y avanza \nun cuadro. \n\nUna planta se reproduce después de \n2 días, siempre y cuando haya cuadros \nvacíos a su alrededor. Los vástagos \nnacerán en todos los cuadros adyacentes.  \n\nCada día es simulado por un ciclo de \n24 segundos. Al inicio de una simulación, \nen la cuadrícula aparecen 3 hormigas, \n4 OH (dos machos y dos hembras) y el \n40% de las cuadrículas están ocupadas \npor plantas. Las plantas se distribuyen de \nforma aleatoria a lo largo y ancho de la \ncuadrícula. De forma similar las \nposiciones iniciales de las 3 hormigas y \n4 OH primigenios son aleatorias.\n\nLa interfaz del usuario muestra la \ncuadrícula y todo lo que en ella va \nocurriendo. Además, muestra el tiempo \ntranscurrido durante la simulación. \n\nEl usuario del sistema puede:\n● Iniciar la simulación.\n● Pausar la simulación.\n● Culminar la simulación.\n");
        jTextArea1.setDoubleBuffered(true);
        jScrollPane1.setViewportView(jTextArea1);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 400, 300));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/fondo.png"))); // NOI18N
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 420, 360));

        jDialog1.getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Langton's ants");
        setIconImage(new ImageIcon("src/Icons/minib.png").getImage());
        setMinimumSize(new java.awt.Dimension(470, 350));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 225, 400, 400));

        Titulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Titulo.png"))); // NOI18N
        getContentPane().add(Titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 19, -1, -1));

        Icono.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Icono.png"))); // NOI18N
        getContentPane().add(Icono, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 60, -1, -1));

        Tiempo.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        Tiempo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        getContentPane().add(Tiempo, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 625, 400, -1));

        Dia1.setFont(new java.awt.Font("Impact", 0, 24)); // NOI18N
        Dia1.setForeground(new java.awt.Color(255, 255, 255));
        Dia1.setText("DIA 0");
        getContentPane().add(Dia1, new org.netbeans.lib.awtextra.AbsoluteConstraints(94, 194, 400, 30));

        Dia.setFont(new java.awt.Font("Impact", 0, 26)); // NOI18N
        Dia.setText("DIA 0");
        getContentPane().add(Dia, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 195, 400, 30));

        Ciclo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Iniciar.png"))); // NOI18N
        Ciclo.setBorder(null);
        Ciclo.setBorderPainted(false);
        Ciclo.setContentAreaFilled(false);
        Ciclo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CicloActionPerformed(evt);
            }
        });
        getContentPane().add(Ciclo, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 650, -1, -1));

        Detener.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Detener.png"))); // NOI18N
        Detener.setBorder(null);
        Detener.setBorderPainted(false);
        Detener.setContentAreaFilled(false);
        Detener.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DetenerActionPerformed(evt);
            }
        });
        getContentPane().add(Detener, new org.netbeans.lib.awtextra.AbsoluteConstraints(225, 650, -1, -1));

        Ayuda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Ayuda.png"))); // NOI18N
        Ayuda.setBorder(null);
        Ayuda.setBorderPainted(false);
        Ayuda.setContentAreaFilled(false);
        Ayuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AyudaActionPerformed(evt);
            }
        });
        getContentPane().add(Ayuda, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 650, -1, -1));

        Fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/fondo.png"))); // NOI18N
        getContentPane().add(Fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 720));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    //Aciones del botón ciclo al ser presionado
    private void CicloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CicloActionPerformed
        if(!funcionamiento){
            Ciclo.setIcon(new ImageIcon(getClass().getResource("/Icons/Pausar.png")));
            this.iniciar();
        } else {
            pausa = true;
            Ciclo.setIcon(new ImageIcon(getClass().getResource("/Icons/Iniciar.png")));
            this.detener();
        }
    }//GEN-LAST:event_CicloActionPerformed

    //Aciones del botón detener al ser presionado
    private void DetenerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DetenerActionPerformed
        if(funcionamiento){
            this.detener();           
        }
        
        pausa = false;
        Habitat = null;
        dia = 0;
        c = 0;
        tem.setDefault();
        Tiempo.setText("");
        Dia.setText("Dia "+String.valueOf(dia));
        Dia1.setText("Dia  "+String.valueOf(dia));
        Ciclo.setIcon(new ImageIcon(getClass().getResource("/Icons/Iniciar.png")));
        for(int j = 0; j < tablero.length ; j++){
            for(int i = 0; i < tablero.length ; i++){
                tablero[j][i].setIcon(new ImageIcon(getClass().getResource("/Icons/Tierra.png")));
            }
        }
    }//GEN-LAST:event_DetenerActionPerformed

    //Aciones del botón de ayuda al ser presionado
    private void AyudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AyudaActionPerformed
        if(funcionamiento){
            pausa = true;
            Ciclo.setIcon(new ImageIcon(getClass().getResource("/Icons/Iniciar.png")));
            this.detener();
        }
        jDialog1.setVisible(true);
    }//GEN-LAST:event_AyudaActionPerformed

  
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                               
            }
        });
        
        new Ventana().setVisible(true);
        }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Ayuda;
    private javax.swing.JButton Ciclo;
    private javax.swing.JButton Detener;
    private javax.swing.JLabel Dia;
    private javax.swing.JLabel Dia1;
    private javax.swing.JLabel Fondo;
    private javax.swing.JLabel Icono;
    private javax.swing.JLabel TI;
    private javax.swing.JLabel TISombra;
    private javax.swing.JLabel Tiempo;
    private javax.swing.JLabel Titulo;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}

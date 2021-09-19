package udp;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.MulticastSocket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.net.*;

public class ChatUDP extends JFrame implements ActionListener, Runnable
{
      static JTextField mensaje=new JTextField();
      private JScrollPane scrollpane1;
      static JTextArea textarea1;
      JButton boton = new JButton ("Enviar");
      JButton desconectar = new JButton ("Salir");
      
      static String nombre;
      static byte[] buf = new byte[1000];
      static InetAddress grupo = null;
      static int Puerto = 12345;
      static boolean repetir=true;
      static MulticastSocket ms;

      public ChatUDP (String nombre) 
      {
          super ("CONEXIÓN DEL CLIENTE CHAT: " + nombre);
          setLayout (null);
         
          mensaje.setBounds(10, 10, 400, 30);add(mensaje);
          textarea1 = new JTextArea();
          scrollpane1 = new JScrollPane(textarea1);
          scrollpane1.setBounds(10,50,400,300);add(scrollpane1);
          boton.setBounds(420,10,100,30);add(boton);
          desconectar.setBounds(420,50,100,30);add(desconectar);         
          textarea1.setEditable(false);
          boton.addActionListener(this);
          desconectar.addActionListener(this);
          setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
      }

      public static void main(String[] args) throws IOException
      {
          nombre = JOptionPane.showInputDialog("Introduce tu nombre o Nick");
          ms =new MulticastSocket (Puerto);
          grupo= InetAddress.getByName("225.0.0.1");
          ms.joinGroup(grupo);
          if (!nombre.trim().equals("")) 
          {
              ChatUDP  server = new ChatUDP(nombre);
              server.setBounds(0,0,540,400);
              server.setVisible(true);
              new Thread (server).start();
          }
          else 
          {
              System.out.println("El nombre esta vacio");
          }
      } 

      public void actionPerformed(ActionEvent e)
      {
           if(e.getActionCommand()=="Enviar")
           {           
               try 
               {
                   String texto = nombre + " >> " +mensaje.getText();
                   DatagramPacket paquete=new DatagramPacket(texto.getBytes(),texto.length(),grupo,Puerto);
                   ms.send(paquete);
                   mensaje.setText("");
               } 
               catch (IOException e1) 
               {
                   e1.printStackTrace();
               }
           }
          
           if(e.getActionCommand()=="Salir")
           {
               try 
               {
                   String texto = nombre + " >> " +"Abandona el chat";
                   DatagramPacket paquete=new DatagramPacket(texto.getBytes(),texto.length(),grupo,Puerto);
                   ms.send(paquete);
                   ms.close();
                   repetir=false;
                   System.out.println("Abandona el chat:"+nombre);
                   System.exit(0);
               } 
               catch (IOException e1) 
               {
                   e1.printStackTrace();
               }
           }
       }

       public void run() 
       {
           while (repetir) 
           {
                try 
                {
                    DatagramPacket p = new DatagramPacket(buf, buf.length);
                    ms.receive(p);
                    String texto=new String (p.getData(), 0, p.getLength());
                    textarea1.append(texto+"\n");
                }
                catch (SocketException s) {System.out.println(s.getMessage()); }
                catch (IOException e) {e.printStackTrace(); }     
           }
       }
}
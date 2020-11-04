
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class JogoRecFala
    implements ActionListener
{

    String DEV_EMAIL="jsaias@di.uevora.pt";
    String APP_NAME="JogoRecFala...";    

    protected static String ENVIAR= "ENVIAR";
    protected static String SAIR= "SAIR";

    private JTextArea qresult,tRef1,tRef2,tRef3,tRef5,tNome1,tDescr1,tUnid2,tUnid5,tDescr4;
    private static final Font fontLabel= new Font("Arial",Font.PLAIN,11);
    private JEditorPane outPane= null;


    private JFrame frame;

    private JButton button;

    private final String RESP1= "1 - calulu";
    private final String RESP2= "2 - sushi";
    private final String RESP3= "3 - bitoque";
    private final String RESP4= "4 - migas";
    private final String RESP5= "5 - francesinha";

    JRadioButton rbt1= new JRadioButton( RESP1 );
    JRadioButton rbt2= new JRadioButton( RESP2 );
    JRadioButton rbt3= new JRadioButton( RESP3 );
    JRadioButton rbt4= new JRadioButton( RESP4 );
    JRadioButton rbt5= new JRadioButton( RESP5 );
    ButtonGroup bgroup;

    int LARGURA=665;
    int ALTURA=600;





    // ************************************
    public static void main(String[] args)
	throws Exception
    {
	JogoRecFala e= new JogoRecFala();
       
	e.initX();	
    }
    


    public JogoRecFala() {	
        bgroup = new ButtonGroup();
        bgroup.add(rbt1);
        bgroup.add(rbt2);
        bgroup.add(rbt3);
        bgroup.add(rbt4);
        bgroup.add(rbt5);
    }            

   


    public void initX( ) {
	frame= new JFrame( APP_NAME );
	frame.setSize( new Dimension(LARGURA,ALTURA) );
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
	frame.setLocationRelativeTo(null);

        tRef1= new JTextArea(1,8);
        tNome1= new JTextArea(1,8);
        tDescr1= new JTextArea(1,20);

        tRef2= new JTextArea(1,8);
        tUnid2= new JTextArea(1,4);
        tRef3= new JTextArea(1,8);
        tRef5= new JTextArea(1,8);
        tUnid5= new JTextArea(1,4);

        tDescr4= new JTextArea(1,12);

	// campo para mostrar informacao, nao editavel
        qresult= new JTextArea(3,38);       
        qresult.setEditable(false);

	// mostrar
	updateFrame();


	// inicializacao dos recursos de rec. de fala...
	// ... AQUI ...


	
    }



    // actualizar o frame de acordo com o estado
    private void updateFrame() {        

        try {
	    if (outPane != null)
	    	frame.setSize( new Dimension(LARGURA,ALTURA) );

            JPanel jp = new JPanel( new BorderLayout() );

            Component centro= desenhaCentro();
            jp.add(centro, BorderLayout.CENTER);

            frame.setContentPane(jp);
        }
        catch (Exception e) {
            System.err.println("ERROR while updating frame: "+e);
            e.printStackTrace();
            System.exit(1);
        }
        // actualizar ==========
	if (outPane == null)
	    frame.pack();
        frame.setVisible(true);
        // ---------------------
        //System.out.println("          == PAINEL ACTUALIZADO");
    voce.SpeechInterface.init("lib", false, true, "./grammar", "digits");
	
	System.out.println("This is a speech recognition test. " 
			   + "Speak digits from 0-9 into the microphone. " 
			   + "Speak 'quit' to quit.");
	boolean correct = false;
	while(!correct){
	boolean quit = false;
	while (!quit) {
	    // Normally, applications would do application-specific things 
	    // here.  For this sample, we'll just sleep for a little bit.
	    try {
		Thread.sleep(200);
	    }
	    catch (InterruptedException e) {
	    }
	    
	    while (voce.SpeechInterface.getRecognizerQueueSize() > 0) {
		String s = voce.SpeechInterface.popRecognizedString();
		
		// Check if the string contains 'quit'.
		if (-1 != s.indexOf("quit")) {
		    quit = true;
		}
		if(s.equals("one")){
			rbt1.setSelected(true);
			quit = true;
		}
		else if(s.equals("two")){
			rbt2.setSelected(true);
			quit = true;
		}
		else if(s.equals("three")){
			rbt3.setSelected(true);
			quit = true;
		}else if(s.equals("four")){
			rbt4.setSelected(true);
			quit = true;
		}
		else if(s.equals("five")){
			rbt5.setSelected(true);
			quit = true;
		}
		System.out.println("You said: " + s);
		//voce.SpeechInterface.synthesize(s);
	    }
	}
	String sr = validaResposta();
	if(sr.equals("Your answer is correct!")){
		correct = true;
	}
}
	
	voce.SpeechInterface.destroy();

    }


    

    private Component desenhaCentro()
        throws Exception
    {
	if (outPane != null) {    // done, falta so' mostrar
	    JScrollPane scrollPane = new JScrollPane(outPane); 
	    return scrollPane;
	}
	else {    //
	    JPanel buttonP = new JPanel(new BorderLayout());	

	    JPanel p = new JPanel(new BorderLayout());
            //p.setBackground(Color.yellow);

	    JPanel p1 = new JPanel(new BorderLayout());

	    JPanel p3 = new JPanel(new BorderLayout());
	    JPanel p4 = new JPanel(new BorderLayout());
            JPanel p2= new JPanel(new GridLayout(1,8));

            // *************************
            // campos das operacoes

            JPanel pbase1= new JPanel(new GridLayout(5,1));
            pbase1.add( rbt1 );
            
            pbase1.add( rbt2 );

            pbase1.add( rbt3 );

            pbase1.add( rbt4 );

            pbase1.add( rbt5 );


	    p2.setAlignmentX(JPanel.CENTER_ALIGNMENT);
            // *************************
	    

	    // ****************
            JLabel lch= new JLabel("resultado");
	    lch.setFont( fontLabel);
	    p2.add(qresult);
	    p2.setAlignmentX(JPanel.CENTER_ALIGNMENT);
	    
	    p3.add(lch,BorderLayout.NORTH );
	    p3.add(p2,BorderLayout.CENTER );
	    
	    button = new JButton(" ok ");
	    button.addActionListener(this); 
	    button.setActionCommand(ENVIAR);
	    
	    buttonP.add(button,BorderLayout.CENTER );	
	    
	    p4.add(p3,BorderLayout.NORTH );
	    p4.add(buttonP,BorderLayout.CENTER );
	    // ****************

	    p.add( new JLabel("Which of these is a typical dish of Évora?") , BorderLayout.NORTH );

	    p.add( pbase1 , BorderLayout.WEST );
	    p.add( p4 , BorderLayout.SOUTH ); // resultado e botao enviar
	    return p;
	}
    }




    // EVENTO SOBRE O FRAME "ACTION"
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        //System.out.println("COMMAND: "+command);
        // ********************************************

         try {
             if ( command.equals(SAIR) ) {
		 frame.dispose();
		 System.exit(0);
	     }

	     
             if ( command.equals(ENVIAR) ) {
		 try {
		     String s= validaResposta();
		 }
		 catch (Exception eNum) {
		     qresult.setText("problemas: "+eNum.getMessage());
		     eNum.printStackTrace();
		 }
		 //System.err.println("------------------------------------");
		 updateFrame();
	     }
	     

             //resetFocusCheck();

         }
         catch (Exception e1) {
             System.err.println(e1);
             try{
		 Thread.sleep(1000);
	     }
             catch(Exception e2){
             }
             System.exit(0);
          }
    }



    private String validaResposta() {
	String s= "Wrong Answer!!";
	
	// que operacao esta seleccionada?
	if (rbt1.isSelected()) {  
	}
	else if(rbt2.isSelected()) { 
	}
	
	else if(rbt3.isSelected()) { 
	}
	
	else if(rbt4.isSelected()) { 
	    s="Your answer is correct!";
	}
	
	else if(rbt5.isSelected()) { 
	}
	// ...
	
	
	System.err.println("DEBUG: "+s);
	if ( s != null ) {
	    qresult.setText(s);
            
	}
	return s;
    }
    
    

}

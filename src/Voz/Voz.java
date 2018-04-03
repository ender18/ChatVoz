package Voz;

import java.io.IOException;
import com.darkprograms.speech.microphone.Microphone;
import com.darkprograms.speech.recognizer.GSpeechDuplex;
import com.darkprograms.speech.recognizer.GSpeechResponseListener;
import com.darkprograms.speech.recognizer.GoogleResponse;
import java.util.Scanner;
import javax.swing.JTextArea;

import net.sourceforge.javaflacencoder.FLACFileWriter;

public class Voz implements GSpeechResponseListener {

    public String reconocimiento() throws IOException {
        JTextArea response = new JTextArea();
        final Microphone mic = new Microphone(FLACFileWriter.FLAC);
        //Don't use the below google api key , make your own !!! :) 
        GSpeechDuplex duplex = new GSpeechDuplex("AIzaSyBOti4mM-6x9WDnZIjIeyEU21OpBXqWBgw");
        duplex.setLanguage("es");

        //INICIAR EL RECONOCIMIENTO DE AUDIO FORMANDO UN HILO
        
            String respuesta = "";
            if (true) {

                new Thread(() -> {
                    try {
                        duplex.recognize(mic.getTargetDataLine(), mic.getAudioFormat());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                }).start();

                //DETENER EL RECONOCIMIENTO DE AUDIO
                //     
                
                GSpeechResponseListener r1 = new GSpeechResponseListener() {
                    
                    String old_text = "";

                    public void onResponse(GoogleResponse gr) {
                        String output = "";
				output = gr.getResponse();
				if (gr.getResponse() == null) {
					this.old_text = response.getText();
					if (this.old_text.contains("(")) {
						this.old_text = this.old_text.substring(0, this.old_text.indexOf('('));
					}
					System.out.println("Paragraph Line Added");
					this.old_text = ( response.getText() + "\n" );
					this.old_text = this.old_text.replace(")", "").replace("( ", "");
					response.setText(this.old_text);
					return;
				}
				if (output.contains("(")) {
					output = output.substring(0, output.indexOf('('));
				}
				if (!gr.getOtherPossibleResponses().isEmpty()) {
					output = output + " (" + (String) gr.getOtherPossibleResponses().get(0) + ")";
				}
				response.setText("");
                                System.out.print(this.old_text);
				response.append(this.old_text);
				response.append(output);
                        }
                };
                
                duplex.addResponseListener(r1);
                 respuesta = this.readLine();

            } 
            
            if (respuesta.equalsIgnoreCase("")) {
                mic.close();
                duplex.stopSpeechRecognition();
            }
        
        return response.getText();
    }

    @Override
    public void onResponse(GoogleResponse paramGoogleResponse) {
        // TODO Auto-generated method stub

    }

    public String readLine() {
        String texto = "";
        try {
            Scanner scanner = new Scanner(System.in);
            texto = scanner.nextLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return texto;
    }
}

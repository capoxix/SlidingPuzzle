import java.awt.*;
import javax.swing.*;
import java.io.*;

/*Name: Garbo Cheng (gc2bt) & Marbo Cheng (mc2cy)
 * 
 */
public class InstructionsFrame extends JFrame {

    private boolean catchErrors;
    private boolean logFile;
    private String fileName;
    private int width;
    private int height;
    private int closeOperation;


    TextArea instructionText = new TextArea();
    PrintStream aPrintStream  =
       new PrintStream(
         new FilteredStream(
           new ByteArrayOutputStream()));
   

    public InstructionsFrame
       (boolean catchErrors, boolean logFile, String fileName, int width,
         int height, int closeOperation) {

        this.catchErrors = catchErrors;
        this.logFile = logFile;
        this.fileName = fileName;
        this.width = width;
        this.height = height;
        this.closeOperation = closeOperation;

        Container container = getContentPane();

        //setTitle("Instruction Frame");
        setSize(width,480);
        container.setLayout(new BorderLayout());
        container.add("Center" , instructionText);
        displayLog();

        this.logFile = logFile;

        System.setOut(aPrintStream); // catches System.out messages
        if (catchErrors)
            System.setErr(aPrintStream); // catches error messages

        // set the default closing operation to the one given
        setDefaultCloseOperation(closeOperation);

        Toolkit tk = Toolkit.getDefaultToolkit();
        Image im = tk.getImage("myicon.gif");
        setIconImage(im);
        setTitle("Steps to solution");
    }



    class FilteredStream extends FilterOutputStream {
        public FilteredStream(OutputStream aStream) {
            super(aStream);
          }

        public void write(byte b[]) throws IOException {
            String aString = new String(b);
            instructionText.append(aString);
        }

        public void write(byte b[], int off, int len) throws IOException {
            String aString = new String(b , off , len);
            instructionText.append(aString);
            if (logFile) {
                FileWriter aWriter = new FileWriter(fileName, true);
                aWriter.write(aString);
                aWriter.close();
            }
        }
    }

    private void displayLog() {
        Dimension dim = getToolkit().getScreenSize();
        Rectangle abounds = getBounds();
        setLocation((dim.width - abounds.width) / 2,
                    (dim.height - abounds.height) / 2);
        setVisible(true);
        setLocation(880,250);
        requestFocus();
    }

}
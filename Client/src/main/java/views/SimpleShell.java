package views;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import controllers.IdController;
import controllers.MessageController;
import controllers.TransactionController;
import models.Id;
import models.Message;
import youareell.YouAreEll;

import static youareell.YouAreEll.get_ids;
import static youareell.YouAreEll.get_messages;

// Simple Shell is a Console view for youareell.YouAreEll.
public class SimpleShell {


    public static void prettyPrint(String output) {
        // yep, make an effort to format things nicely, eh?
        System.out.println(output);
    }
    public static void main(String[] args) throws java.io.IOException {
        SimpleShell simpleShell = new SimpleShell();

        YouAreEll urll = new YouAreEll(new MessageController(), new IdController());
        
        String commandLine;
        BufferedReader console = new BufferedReader
                (new InputStreamReader(System.in));

        ProcessBuilder pb = new ProcessBuilder();
        List<String> history = new ArrayList<String>();
        int index = 0;
        //we break out with <ctrl c>
        while (true) {
            //read what the user enters
            System.out.println("cmd? ");
            commandLine = console.readLine();

            //input parsed into array of strings(command and arguments)
            String[] commands = commandLine.split(" ");
            List<String> list = new ArrayList<String>();

            //if the user entered a return, just loop again
            if (commandLine.equals(""))
                continue;
            if (commandLine.equals("exit")) {
                System.out.println("bye!");
                break;
            }

            //loop through to see if parsing worked
            for (int i = 0; i < commands.length; i++) {
                //System.out.println(commands[i]); //***check to see if parsing/split worked***
                list.add(commands[i]);

            }
            System.out.print(list); //***check to see if list was added correctly***
            history.addAll(list);
            try {
                //display history of shell with index
                if (list.get(list.size() - 1).equals("history")) {
                    for (String s : history)
                        System.out.println((index++) + " " + s);
                    continue;
                }

                // Specific Commands.

                // ids
                if (list.contains("ids") && list.size() == 1) {
                    String results = get_ids("/ids");
                    IdController idController = new IdController();
                    ArrayList<Id> idsList = idController.getIds(results);
                    for (Id id : idsList){
                        SimpleShell.prettyPrint(id.toString());
                    }
                    continue;
                }

                // messages
                if (list.contains("messages") && list.size() == 1) {
                    String results = get_messages("/messages");
                    simpleShell.messagesPrinter(results);
                    continue;
                }

                if(list.contains("messages") && list.size() == 2){
                    String results = get_messages("/ids/" + list.get(1) + "/messages");
                    simpleShell.messagesPrinter(results);
                    continue;
                }

                if(list.contains("messages") && list.size() == 4){
                    String results = get_messages("/ids/" + list.get(1) + "/from/" + list.get(3));
                    simpleShell.messagesPrinter(results);
                }

                if(list.contains("ids") && list.size() == 3){
                    TransactionController tc = new TransactionController(new MessageController(), new IdController());
                    String results = get_ids("/ids");
                    IdController idController = new IdController();
                    ArrayList<Id> idsList = idController.getIds(results);
                    for (Id id: idsList) {
                        if (id.getGithub().equals(list.get(2))){
                            tc.putId(list.get(1), list.get(2));
                        }
                    }
                    tc.postId(list.get(1), list.get(2));
                }

                if(list.contains("send")){
                    //post a message to the server //ids/ userid /messages
                    String url = "/ids/" + list.get(1) + "/messages/";
                    StringBuilder sb = new StringBuilder();
                    for(int i = 2; i<list.size(); i++){
                        sb.append(list.get(i));
                        sb.append(" ");
                    }
                    System.out.println(url);
                    MessageController msgCtr = new MessageController();
                    msgCtr.postMessage(list.get(1), "", sb.toString().trim(), url);
                }

                if(list.contains("send") && list.size() == 4){
                    //send a message to a friend //ids/ userid /messages
                    //1 is id
                    //2 is message
                    //4 is friend id
                }

                // you need to add a bunch more.

                //!! command returns the last command in history
                if (list.get(list.size() - 1).equals("!!")) {
                    pb.command(history.get(history.size() - 2));

                }//!<integer value i> command
                else if (list.get(list.size() - 1).charAt(0) == '!') {
                    int b = Character.getNumericValue(list.get(list.size() - 1).charAt(1));
                    if (b <= history.size())//check if integer entered isn't bigger than history size
                        pb.command(history.get(b));
                } else {
                    pb.command(list);
                }

                // // wait, wait, what curiousness is this?
                // Process process = pb.start();

                // //obtain the input stream
                // InputStream is = process.getInputStream();
                // InputStreamReader isr = new InputStreamReader(is);
                // BufferedReader br = new BufferedReader(isr);

                // //read output of the process
                // String line;
                // while ((line = br.readLine()) != null)
                //     System.out.println(line);
                // br.close();


            }

            //catch ioexception, output appropriate message, resume waiting for input
            catch (/*IO*/Exception e) {
                System.out.println("Input Error, Please try again!");
            }
            // So what, do you suppose, is the meaning of this comment?
            /** The steps are:
             * 1. parse the input to obtain the command and any parameters
             * 2. create a ProcessBuilder object
             * 3. start the process
             * 4. obtain the output stream
             * 5. output the contents returned by the command
             */

        }


    }

    public void messagesPrinter(String results){
        MessageController messageController = new MessageController();
        ArrayList<Message> messagesList = messageController.getMessages(results);
        int count = 0;
        for (Message message : messagesList){
            if(count<20){
                SimpleShell.prettyPrint(message.toString());
            }
            count++;
        }
    }

}
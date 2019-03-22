package server;

import library.Library;
import library.LibraryResources;
import network.Message;
import network.MessageContentType;
import network.MessageType;
import resources.*;
import user.Librarian;
import user.User;

import java.io.*;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

public class Client implements Runnable {

    private Socket socket;

    public Client(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        System.out.println("Connected: " + socket);

        try {
            Scanner in = new Scanner(socket.getInputStream());
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            out.println("SERVER Welcome to TaweLib! - " + new Date().toString());
            while (in.hasNext()) {
                commandHandler(in.nextLine(), out);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                Library.setLoggedInUser(null);
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void commandHandler(String input, PrintWriter out) throws Exception{
        System.out.println(input);
        String[] params = input.split(" ");

        switch (params[1]){
            case ("PrintResources"):
                for (Resource r : LibraryResources.getAllResources()){
                    String type = "";
                    if (r instanceof Book) type = "BOOK";
                    else if (r instanceof DVD) type = "DVD";
                    else if (r instanceof Laptop) type = "LAPTOP";
                    else if (r instanceof VideoGame) type = "GAME";
                    out.println("RESOURCE " + type + " " + r.toJSON());
                }
                break;
            case ("PrintResource"):
                out.println(Library.getResource(params[2]).toJSON());
                break;
            case ("LOGIN"):
                User user = Library.getUser(params[2]);
                if (user == null){
                    out.println("SERVER LOGIN_UNSUCCESSFUL");
                } else {
                    out.println("SERVER LOGIN_SUCCESSFUL " +
                            (user instanceof Librarian ? "LIBRARIAN " : "USER ")+
                            user.getUserName());
                }
                break;
        }
    }
}

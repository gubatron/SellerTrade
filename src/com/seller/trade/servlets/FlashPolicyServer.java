/**
 * The MIT License
 ===============

 Copyright (C) 2015 SellerTrade Developers

 Permission is hereby granted, free of charge, to any person obtaining
 a copy of this software and associated documentation files (the
 "Software"), to deal in the Software without restriction, including
 without limitation the rights to use, copy, modify, merge, publish,
 distribute, sublicense, and/or sell copies of the Software, and to
 permit persons to whom the Software is furnished to do so, subject
 to the following conditions:

 The above copyright notice and this permission notice shall be
 included in all copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR
 ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
 CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */


package com.seller.trade.servlets;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class FlashPolicyServer extends Thread {
    public static final String POLICY_REQUEST = "<policy-file-request/>";
    public static final String POLICY_XML = "<?xml version=\"1.0\"?>" + "<cross-domain-policy>" + "<allow-access-from domain=\"*\" to-ports=\"*\" />" + "</cross-domain-policy>";

    protected int port;
    protected ServerSocket serverSocket;
    protected boolean listening;

    /**
     * Creates a new instance of PolicyServer.
     *
     * @param serverPort the port to be used by the server
     */
    public FlashPolicyServer(int serverPort) {
        this.port = serverPort;
        this.listening = false;
    }

    /**
     * Gets the server's port.
     *
     * @return the port of the server
     */
    public int getPort() {
        return this.port;
    }

    /**
     * Gets the server's listening status.
     *
     * @return true if the server is listening
     */
    public boolean getListening() {
        return this.listening;
    }

    /**
     * Roots a debug message to the main application.
     * 
     * @param msg the debug message to be sent to the main application
     */
    protected void debug(String msg) {
        System.out.println("PolicyServer (" + this.port + ")" + msg);
    }

    /**
     * Waits for clients' connections and handles them to a new PolicyServerConnection.
     */
    public void run() {
        try {
            this.serverSocket = new ServerSocket(this.port);
            this.listening = true;
            debug("listening");

            while (this.listening) {
                Socket socket = this.serverSocket.accept();
                debug("client connection from " + socket.getRemoteSocketAddress());
                PolicyServerConnection socketConnection = new PolicyServerConnection(socket);
                socketConnection.start();
            }
            ;
        } catch (Exception e) {
            debug("Exception (run): " + e.getMessage());
        }
    }

    /**
     * Closes the server's socket.
     */
    protected void finalize() {
        try {
            this.serverSocket.close();
            this.listening = false;
            debug("stopped");
        } catch (Exception e) {
            debug("Exception (finalize): " + e.getMessage());
        }
    }

    public class PolicyServerConnection extends Thread {
        protected Socket socket;
        protected BufferedReader socketIn;
        protected PrintWriter socketOut;

        /**
         * Creates a new instance of PolicyServerConnection.
         *
         * @param socket client's socket connection
         */
        public PolicyServerConnection(Socket socket) {
            this.socket = socket;
        }

        /**
         * Roots a debug message to the main application.
         * 
         * @param msg the debug message to be sent to the main application
         */
        protected void debug(String msg) {
            System.out.println("PolicyServerConnection (" + this.socket.getRemoteSocketAddress() + ")" + msg);
        }

        /**
         * Create a reader and writer for the socket and call readPolicyRequest.
         */
        public void run() {
            try {
                this.socketIn = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
                this.socketOut = new PrintWriter(this.socket.getOutputStream(), true);
                readPolicyRequest();
            } catch (Exception e) {
                debug("Exception (run): " + e.getMessage());
            }
        }

        /**
         * Reads a string from the client and if it is a policy request we write the policy, then we close the connection.
         */
        protected void readPolicyRequest() {
            try {
                String request = read();
                debug("client says '" + request + "'");

                if (request.equals(FlashPolicyServer.POLICY_REQUEST)) {
                    writePolicy();
                }
            } catch (Exception e) {
                debug("Exception (readPolicyRequest): " + e.getMessage());
            }
            finalize();
        }

        /**
         * Writes the policy of the server.
         */
        protected void writePolicy() {
            try {
                this.socketOut.write(FlashPolicyServer.POLICY_XML + "\u0000");
                this.socketOut.close();
                debug("policy sent to client");
            } catch (Exception e) {
                debug("Exception (writePolicy): " + e.getMessage());
            }
        }

        /**
         * Safely read a string from the reader until a zero character is received or the 200 character is reached.
         *
         * @return the string read from the reader.
         */
        protected String read() {
            StringBuffer buffer = new StringBuffer();
            int codePoint;
            boolean zeroByteRead = false;

            try {
                do {
                    codePoint = this.socketIn.read();

                    if (codePoint == 0) {
                        zeroByteRead = true;
                    } else if (Character.isValidCodePoint(codePoint)) {
                        buffer.appendCodePoint(codePoint);
                    }
                } while (!zeroByteRead && buffer.length() < 200);
            } catch (Exception e) {
                debug("Exception (read): " + e.getMessage());
            }

            return buffer.toString();
        }

        /**
         * Closes the reader, the writer and the socket.
         */
        protected void finalize() {
            try {
                this.socketIn.close();
                this.socketOut.close();
                this.socket.close();
                debug("connection closed");
            } catch (Exception e) {
                debug("Exception (finalize): " + e.getMessage());
            }
        }
    }

}

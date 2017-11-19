package com.test;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class Test {

//	@org.junit.Test
	public void testIO(){
		 try {
	            // Create input stream, read a file.
	            InputStream in = new FileInputStream("c:/data.txt");
	            // A temporary array to store data each reading
	            byte[] temp = new byte[10];
	            int i = -1;
	            // Reads some number of bytes from the input stream
	            // and stores them into the buffer array 'temp'.
	            // Return the number of bytes actually read.
	            // return -1 if end of stream.
	            while ((i = in.read(temp)) != -1) {
	                // Create String from bytes
	                String s = new String(temp, 0, i);
	                System.out.println(s);
	            }
	            in.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}
//	@org.junit.Test
	public void testZijie() throws IOException{
		// Create binary stream, read a file.
        InputStream in = new FileInputStream("c:/test_utf8");
        // Create character stream from binary stream.
        // encoding UTF-8
        Reader reader = new InputStreamReader(in, "UTF-8");
        int i = 0;
        // Read turn each character
        while ((i = reader.read()) != -1) {
            // cast int to char, and print to the Console
            System.out.println((char) i + " " + i);
        }
        reader.close();
        
        
     // Create input stream, read a file.
        InputStream in1 = new FileInputStream("c:/test_utf8");
        // A temporary array to store data each reading
        byte[] temp = new byte[10];
        int a = -1;
        // Reads some number of bytes from the input stream
        // and stores them into the buffer array 'temp'.
        // Return the number of bytes actually read.
        // return -1 if end of stream.
        while ((a = in1.read()) != -1) {
            // Create String from bytes
        	System.out.println(a + "  " + (char) a);
            System.out.println(String.valueOf(a));
        }
        in1.close();
	}
}

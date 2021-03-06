/* 
 * <copyright> 
 *   
 *  Copyright 1999-2004 Object Services and Consulting, Inc. 
 *  under sponsorship of the Defense Advanced Research Projects 
 *  Agency (DARPA). 
 *  
 *  You can redistribute this software and/or modify it under the 
 *  terms of the Cougaar Open Source License as published on the 
 *  Cougaar Open Source Website (www.cougaar.org). 
 *  
 *  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS 
 *  "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT 
 *  LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR 
 *  A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT 
 *  OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, 
 *  SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT 
 *  LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, 
 *  DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY 
 *  THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT 
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE 
 *  OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. 
 *   
 * </copyright> 
 */ 
/*
 * CougaarProbePlugs.java
 *
 * Object Services & Consulting, Inc. All Rights Reserved. 2001
 * Author: Paul Pazandak
 * Created on February 10, 2003, 1:01 PM
 */

package com.objs.surveyor.probemeister.probe.cougaar;

import com.objs.surveyor.probemeister.probe.util.*;
import com.objs.surveyor.probemeister.probe.GenericArgumentArray;

/*
 * This class contains the static probe plug methods used for Cougaar.
 *
 *
 */
public class CougaarProbePlugs {
    
//com.objs.surveyor.probemeister.probe.cougaar.CougaarProbePlugs
    
//    static final String hostOS = System.getProperty("os.name");
//    static final String hostID = java.net.InetAddress.getHostAddress();
    static final String process= java.lang.Runtime.getRuntime().toString();
    
    static final String totmem = String.valueOf(java.lang.Runtime.getRuntime().totalMemory());
    static final String freemem= String.valueOf(java.lang.Runtime.getRuntime().freeMemory());
    static String systemProperties = null;
    
    public static final String START = "START";
    public static final String END   = "END";
    public static final String STOPPED = "STOPPED";
    
    static int eventID;
    
    //Generate a string list of all properties
    static {
        java.io.StringWriter sw = new java.io.StringWriter();
        java.io.PrintWriter pw = new java.io.PrintWriter(sw);
        java.lang.System.getProperties().list(pw);
        systemProperties = sw.toString();
        eventID = 0;
    }
    
    //Each call to this routine should include this info
    //static final String currThread = java.lang.Thread.currentThread().toString();
    
    //Compute current time of call
    static String getCurrTime() {
        return ""+System.currentTimeMillis(); //"*NotAvailable*";
        //return (new java.util.Date().toString());
    }
    
     /* 
      * This probe plug method emits the VM Name, Probe ID, user-supplied message,
      * calling stub name, and method arguments (as strings) to the console.
      *
      * ProbePlug has the following signature:
      * <b>(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Thread;Lcom/objs/surveyor/probemeister/probe/GenericArgumentArray;)V </b>
      *
      * This signature matches up with StatementFactory.createStubProbe_ArgsCallWithMsgStmt, and is used by
      * the Stub_PassMethodArgsAndString stub.
      */
    public static void PP_emitCougaarArgsAndMsgPlug(
                                              String _vm, 
                                              String _probeID, 
                                              String _stubName, 
                                              String _instrumentedMethod, 
                                              String _msg,
                                              Thread _thr, 
                                              GenericArgumentArray o) {
                              
                                                
        String space = "----> ";                                          
        System.out.println("PP_emitCougaarArgsAndMsgPlug called.");
        System.out.println(space + "Message: " + _msg);
        System.out.println(space + "ProbeID: EVT-" + _probeID); //"some-id");
        System.out.println(space + "Instrumented Method: " + _instrumentedMethod);
        System.out.println(space + "Time: " + getCurrTime());

        if (o!=null && o.length()>0) {
            for (int i=0; i<o.length(); i++) {
                //System.out.println("\n *** Object "+i+" ***", Print.D0);        
                String o1 = o.getName(i);
                o1 = o1.replace('@', '_'); //makes it HTML friendly
                o1 = "Arg"+i+"_"+o1;        
                String o2 = (o.getValue(i) != null) ? o.getValue(i).toString() : "null";
                o2=o2.replace('@', '_');
                System.out.println(space + o1 + " : " + o2);
            }
        } 
    }

     /* 
      * This probe plug method emits the VM Name, Probe ID, user-supplied message,
      * calling stub name, and method arguments (as strings) to the console.
      *
      * ProbePlug has the following signature:
      * <b>(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Thread;Lcom/objs/surveyor/probemeister/probe/GenericArgumentArray;Ljava/lang/Object;)V </b>
      *
      * This signature matches up with StatementFactory.createStubProbe_ArgsCallWithMsgStmt, and is used by
      * the Stub_PassMethodArgsAndString stub.
      */
    public static void PP_emitCougaarArgsThisAndMsgPlug(
                                              String _vm, 
                                              String _probeID, 
                                              String _stubName, 
                                              String _instrumentedMethod, 
                                              String _msg,
                                              Thread _thr, 
                                              GenericArgumentArray o,
                                              Object _this) {
                              
                                                
        String space = "----> ";                                          
        System.out.println("PP_emitCougaarArgsAndMsgPlug called.");
        System.out.println(space + "Message: " + _msg);
        System.out.println(space + "ProbeID: EVT-" + _probeID); //"some-id");
        System.out.println(space + "Instrumented Method: " + _instrumentedMethod);
        System.out.println(space + "Time: " + getCurrTime());
        System.out.println(space + "(Outer)This: " + _this);

        if (o!=null && o.length()>0) {
            for (int i=0; i<o.length(); i++) {
                //System.out.println("\n *** Object "+i+" ***", Print.D0);        
                String o1 = o.getName(i);
                o1 = o1.replace('@', '_'); //makes it HTML friendly
                o1 = "Arg"+i+"_"+o1;        
                String o2 = (o.getValue(i) != null) ? o.getValue(i).toString() : "null";
                o2=o2.replace('@', '_');
                System.out.println(space + o1 + " : " + o2);
            }
        } 
    }
    
    
}
    


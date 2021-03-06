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
 */

package com.objs.surveyor.probemeister.bytecoder;

import com.objs.surveyor.probemeister.probe.*;
import com.objs.surveyor.probemeister.TargetVMConnector;

/*
 * This is a probe stub. It passes the arguments of the instrumented method to 
 * the selected ProbePlug.
 */
public class Stub_PassMethodArgsEvent extends Stub_BytecodeSkeleton  {

    protected String name="**StubSkeleton-NameNotSet**"; //user visible name of ProbeType
    protected static Stub_PassMethodArgsEvent stub = null;
    public static ProbeType getStub() {return stub;}
    static final String stubName = "PassMethodArgsEventStub";
    
    static final String sig = "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Thread;Lcom/objs/surveyor/probemeister/probe/GenericArgumentArray;)V";
    
    String evtName="";
    String evtSubName="";
    String msg="";
    
    static {
        stub = new Stub_PassMethodArgsEvent(stubName, "Stub_PassMethodArgsEvent", sig,
                    "This stub probe passes the methods arguments, event type, subtype, a msg, and thread to a plug");
    }
    
    /*
     *
     * @param _userVisibleStubname - Name of stub that the user will see - no spaces in this name
     * @param _clsName - Name of the subclass
     * @param _sig - Signature of the Stub being created to validate against the ProbePlug
     * @param _desc - User visible description of the stub
     *
     */
    protected Stub_PassMethodArgsEvent(String _userVisibleStubname, String _clsName, String _sig, String _desc ) {
        super(_userVisibleStubname, _clsName, _sig, _desc );
        myStub = this; //assign value to super abstract class
	}    
                   
    /*
     * Override this routine to produce a customized event ID
     */
    protected String getNextID() { return "ArgEventStub"+idCount++; }

    /*
     * Override this routine to add the stub-specific bytecode to the StatementList.
     * Use the StatementFactory to create the statements.
     */
    protected boolean customizeStub(StatementList _sl, ProbePlugEntry _plug, 
                                 BytecodeLocation _bLoc, String _id) 
                                 throws StatementListPreparedException { 
        //Customized code goes here in overriden subclass
        //Insert Statement to pass Method Args.
        
        //*********************************************
        //*** Ask user for event type, subtype, and msg.
        //**********************************************
        com.objs.surveyor.probemeister.Log.out.fine("Stub_PassMethodArgsEvent::customizeStub...");
        evtName="";
        evtSubName="";
        msg="";
        int offset = 0;
        
        Stub_GetEventAttrsGUI.gui().setByteOffset(_bLoc.getOffset());
        Stub_GetEventAttrsGUI.gui().setVisible(true);
        evtName = Stub_GetObjectEventAttrsGUI.gui().getEvtString();
        if (evtName==null || evtName.length()==0) {
        	javax.swing.JOptionPane.showMessageDialog(null, "Event Name not supplied. Cancelling request.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);		    
            return false; //user did not customize
        }
        
        evtSubName = Stub_GetObjectEventAttrsGUI.gui().getSubEvtString();
        if (evtSubName==null || evtSubName.length()==0) evtSubName="no subevent";

        msg = Stub_GetObjectEventAttrsGUI.gui().getMsg();        
        if (msg==null || msg.length()==0) msg = "no message";
        offset = Stub_GetEventAttrsGUI.gui().getByteOffset();
        
        _bLoc.setOffset(offset);
        
        String vmName = "";
        try {
            TargetVMConnector vm = _bLoc.getClassMgr().vmConnector();
            vmName = vm.getName() +":"+ vm.getAddress();
        } catch (Exception e) {}

       
        StatementFactory.createStubProbe_EventWithArgsCallStmt(_sl, _plug.getClassName(), _plug.getMethodName(), 
                    _bLoc.getMethodObject(), evtName, evtSubName, msg, 
                    _id, stubName, vmName);
        
        return true;
    }
    

    /* This method takes the customized attribute values. The map contains attr-value pairs
     * corresponding to the Map object generated via the getParamsMap() call
     *
     */
    protected void customizeStub(StatementList _sl, ProbePlugEntry _plug, 
                            BytecodeLocation _bLoc, java.util.Map _params,
                            String _id)  throws StatementListPreparedException { 
        //Customized code goes here in overriden subclass
        
        com.objs.surveyor.probemeister.Log.out.fine("Stub_PassMethodArgsEvent::customizeStub...");
        
        //*********************************************
        //*** Get the attrs from the params map
        //**********************************************
        evtName = (String) _params.get("evtName");
        evtSubName = (String) _params.get("evtSubName");
        msg = (String) _params.get("msg");
        
        String vmName = "";
        try {
            TargetVMConnector vm = _bLoc.getClassMgr().vmConnector();
            vmName = vm.getName() +":"+ vm.getAddress();
        } catch (Exception e) {}

        StatementFactory.createStubProbe_EventWithArgsCallStmt(_sl, _plug.getClassName(), _plug.getMethodName(), 
                    _bLoc.getMethodObject(), evtName, evtSubName, msg,
                    _id, stubName, vmName);
/*        
System.out.println("******Stub_PassMethodArgsEvent");        
System.out.println("evt="+evtName);        
System.out.println("sub="+evtSubName);        
System.out.println("msg="+msg);        
System.out.println("vmName="+vmName);        
System.out.println("stubName="+stubName);        
System.out.println("plugClass="+_plug.getClassName());        
System.out.println("plugMeth="+_plug.getMethodName());   
System.out.println("bLoc Meth="+_bLoc.getMethod().name());        
System.out.println("******");        
*/
    }
    
    public java.util.Map getParamsMap() {

       java.util.Hashtable h = new java.util.Hashtable(3);
       h.put("evtName", evtName);
       h.put("evtSubName", evtSubName);
       h.put("msg", msg);
       return h;
    }
    
    //Set values from Map as if customizeStub was called
    //This in effect deserializes the specific attrs used by the stub
    public void setParamsMap(java.util.Map _map) {
        
        if (_map == null) {
            return;
        }
        
        evtName = (String)_map.get("evtName");
        evtSubName = (String)_map.get("evtSubName");
        msg = (String)_map.get("msg");
    }
    
    /* Used to process encoded parameters for display to user. 
     * This stub has no additional parameters, so we don't overide it.
     */
//    public String[] prettyPrintParamList(String[] _params) {
//    }
}
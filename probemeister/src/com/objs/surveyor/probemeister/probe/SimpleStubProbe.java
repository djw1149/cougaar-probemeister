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

package com.objs.surveyor.probemeister.probe;
import com.objs.surveyor.probemeister.bytecoder.StatementList;
import com.objs.surveyor.probemeister.bytecoder.BytecodeLocation;


public class SimpleStubProbe extends ProbeStub {

    //protected ProbePlug plug = null;

    public SimpleStubProbe(String _id, String _desc, ProbeType _pt, StatementList _sl, BytecodeLocation _loc) {
        super(_id, _desc, _pt, _sl, _loc);
        super.setProbeTypeStr(_pt.getRegisteredProbeTypeName());        
    }
    
}
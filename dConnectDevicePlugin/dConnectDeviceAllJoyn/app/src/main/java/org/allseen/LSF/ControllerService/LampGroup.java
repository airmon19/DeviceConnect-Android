/*
 Based on Controller Service 14.12 Interface Definition
 */

package org.allseen.LSF.ControllerService;

import org.alljoyn.bus.BusException;
import org.alljoyn.bus.annotation.BusInterface;
import org.alljoyn.bus.annotation.BusMethod;
import org.alljoyn.bus.annotation.BusProperty;
import org.alljoyn.bus.annotation.BusSignal;
import org.alljoyn.bus.annotation.Position;

/*
 * The BusInterface annotation is used to tell the code this interface is an AllJoyn
 * interface.
 *
 * The 'name' value is used to specify by which name this interface will be known.  If the name is
 * not given the fully qualified name of the Java interface is be used.  In most instances its best
 * to assign an interface name since it helps promote code reuse.
 */
@BusInterface(name = "org.allseen.LSF.ControllerService.LampGroup")
public interface LampGroup {

    public class BaseReturnValue {
        @Position(0)
        public int responseCode = 0;
        @Position(1)
        public String lampGroupIDs = null;
    }

    public class GetAllLampGroupIDs_return_value_uas extends BaseReturnValue {
    }

    public class GetLampGroupName_return_value_usss extends BaseReturnValue {
        @Position(2)
        public String language;
        @Position(3)
        public String lampGroupName;
    }

    public class SetLampGroupName_return_value_usss extends BaseReturnValue {
        @Position(2)
        public String language;
    }

    public class CreateLampGroup_return_value_us extends BaseReturnValue {
    }

    public class UpdateLampGroup_return_value_us extends BaseReturnValue {
    }

    public class DeleteLampGroup_return_value_us extends BaseReturnValue {
    }

    public class GetLampGroup_return_value_usasas extends BaseReturnValue {
        @Position(2)
        public String[] lampIDs;
        @Position(3)
        public String[] lampGroupIDs;
    }

    public class ResetLampGroupState_return_value_us extends BaseReturnValue {
    }

    public class ResetLampGroupStateField_return_value_uss extends BaseReturnValue {
        @Position(2)
        public String lampGroupStateFieldName;
    }

    /*
     * The BusMethod annotation signifies this function should be used as part of the AllJoyn
     * interface. The runtime is smart enough to figure out what the input and output of the
     * method is based on the input/output arguments of the method.
     *
     * All methods that use the BusMethod annotation can throw a BusException and should indicate
     * this fact.
     */
    @BusMethod(name = "GetAllLampGroupIDs", replySignature = "uas")
    GetAllLampGroupIDs_return_value_uas getAllLampGroupIDs() throws BusException;

    @BusMethod(name = "GetLampGroupName", signature = "ss", replySignature = "usss")
    GetLampGroupName_return_value_usss getLampGroupName(String lampGroupID, String language) throws BusException;

    @BusMethod(name = "SetLampGroupName", signature = "sss", replySignature = "uss")
    SetLampGroupName_return_value_usss setLampGroupName(String lampGroupID, String lampGroupName, String language) throws BusException;

    @BusMethod(name = "CreateLampGroup", signature = "asasss", replySignature = "us")
    CreateLampGroup_return_value_us createLampGroup(String[] lampIDs, String[] lampGroupIDs, String lampGroupName, String language) throws BusException;

    @BusMethod(name = "UpdateLampGroup", signature = "sasas", replySignature = "us")
    UpdateLampGroup_return_value_us updateLampGroup(String lampGroupID, String[] lampIDs, String[] lampGroupIDs) throws BusException;

    @BusMethod(name = "DeleteLampGroup", signature = "s", replySignature = "us")
    DeleteLampGroup_return_value_us deleteLampGroup(String lampGroupID) throws BusException;

    @BusMethod(name = "GetLampGroup", signature = "s", replySignature = "us")
    GetLampGroup_return_value_usasas getLampGroup(String lampGroupID) throws BusException;

    @BusMethod(name = "ResetLampGroupState", signature = "s", replySignature = "us")
    ResetLampGroupState_return_value_us resetLampGroupState(String lampGroupID) throws BusException;

    @BusMethod(name = "ResetLampGroupStateField", signature = "ss", replySignature = "uss")
    ResetLampGroupStateField_return_value_uss resetLampGroupStateField(String lampGroupID, String lampGroupStateFieldName) throws BusException;


    /*
     * The BusProperty annotation signifies this property should be used as part of the
     * AllJoyn interface. The runtime is smart enough to figure out what the input and output of
     * the property is based on the input/output arguments of the property.
     *
     * All properties that use the BusProperty annotation can throw a BusException and should
     * indicate this fact.
     */
    @BusProperty(name = "Version", signature = "u")
    int getVersion() throws BusException;

    /*
    * The BusSignal annotation signifies this signal should be used as part of the
    * AllJoyn interface.
    *
    * All signals that use the BusSignal annotation can throw a BusException and should
    * indicate this fact.
    */
    @BusSignal(name = "LampGroupsNameChanged", replySignature = "as", sessionless = true)
    void emitLampGroupsNameChanged(String[] lampGroupsIDs) throws BusException;

    @BusSignal(name = "LampGroupsCreated", replySignature = "as", sessionless = true)
    void emitLampGroupsCreated(String[] lampGroupsIDs) throws BusException;

    @BusSignal(name = "LampGroupsUpdated", replySignature = "as", sessionless = true)
    void emitLampGroupsUpdated(String[] lampGroupsIDs) throws BusException;

    @BusSignal(name = "LampGroupsDeleted", replySignature = "as", sessionless = true)
    void emitLampGroupsDeleted(String[] lampGroupsIDs) throws BusException;
}

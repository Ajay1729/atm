/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (3.0.0-SNAPSHOT).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.lxisoft.atm.client.activiti.api;

import com.lxisoft.atm.client.activiti.model.DataResponse;
import com.lxisoft.atm.client.activiti.model.ProcessInstanceActionRequest;
import com.lxisoft.atm.client.activiti.model.ProcessInstanceCreateRequest;
import com.lxisoft.atm.client.activiti.model.ProcessInstanceQueryRequest;
import com.lxisoft.atm.client.activiti.model.ProcessInstanceResponse;
import com.lxisoft.atm.client.activiti.model.RestIdentityLink;
import com.lxisoft.atm.client.activiti.model.RestVariable;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-01-04T11:25:41.758+05:30[Asia/Colombo]")

@Api(value = "ProcessInstances", description = "the ProcessInstances API")
public interface ProcessInstancesApi {

    @ApiOperation(value = "Update a single or binary variable or multiple variables on a process instance", nickname = "createOrUpdateProcessVariable", notes = "## Update multiples variables   ```JSON [    {       \"name\":\"intProcVar\"       \"type\":\"integer\"       \"value\":123    },     ... ] ```    Any number of variables can be passed into the request body array. More information about the variable format can be found in the REST variables section. Note that scope is ignored, only local variables can be set in a process instance.   ## Update a single variable  ```JSON  {     \"name\":\"intProcVar\"     \"type\":\"integer\"     \"value\":123  } ```   ##  Update an existing binary variable     The request should be of type multipart/form-data. There should be a single file-part included with the binary value of the variable. On top of that, the following additional form-fields can be present:  name: Required name of the variable.  type: Type of variable that is created. If omitted, binary is assumed and the binary data in the request will be stored as an array of bytes.", response = Object.class, authorizations = {
        @Authorization(value = "basicAuth")
    }, tags={ "Process Instances", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = Object.class),
        @ApiResponse(code = 201, message = "Indicates the process instance was found and variable is created."),
        @ApiResponse(code = 400, message = "Indicates the request body is incomplete or contains illegal values. The status description contains additional information about the error."),
        @ApiResponse(code = 404, message = "Indicates the requested process instance was not found."),
        @ApiResponse(code = 415, message = "Indicates the serializable data contains an object for which no class is present in the JVM running the Activiti engine and therefore cannot be deserialized.") })
    @RequestMapping(value = "/runtime/process-instances/{processInstanceId}/variables",
        produces = "application/json", 
        method = RequestMethod.PUT)
    ResponseEntity<Object> createOrUpdateProcessVariable(@ApiParam(value = "The id of the process instance to create the new variable for.",required=true) @PathVariable("processInstanceId") String processInstanceId);


    @ApiOperation(value = "Start a process instance", nickname = "createProcessInstance", notes = "## Request body (start by process definition id)  ```JSON  {    \"processDefinitionId\":\"oneTaskProcess:1:158\",    \"businessKey\":\"myBusinessKey\",    \"variables\": [       {         \"name\":\"myVar\",         \"value\":\"This is a variable\",       }    ] }```  ## Request body (start by process definition key)  ```JSON  {    \"processDefinitionKey\":\"oneTaskProcess\",    \"businessKey\":\"myBusinessKey\",    \"tenantId\": \"tenant1\",    \"variables\": [       {         \"name\":\"myVar\",         \"value\":\"This is a variable\",       }    ] }```  ## Request body (start by message)  ```JSON {    \"message\":\"newOrderMessage\",    \"businessKey\":\"myBusinessKey\",    \"tenantId\": \"tenant1\",    \"variables\": [       {         \"name\":\"myVar\",         \"value\":\"This is a variable\",       }    ] }```  Note that also a *transientVariables* property is accepted as part of this json, that follows the same structure as the *variables* property.  Only one of *processDefinitionId*, *processDefinitionKey* or *message* can be used in the request body. Parameters *businessKey*, *variables* and *tenantId* are optional. If tenantId is omitted, the default tenant will be used. More information about the variable format can be found in the REST variables section. Note that the variable-scope that is supplied is ignored, process-variables are always local.", response = ProcessInstanceResponse.class, authorizations = {
        @Authorization(value = "basicAuth")
    }, tags={ "Process Instances", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = ProcessInstanceResponse.class),
        @ApiResponse(code = 201, message = "Indicates the process instance was created."),
        @ApiResponse(code = 400, message = "Indicates either the process-definition was not found (based on id or key), no process is started by sending the given message or an invalid variable has been passed. Status description contains additional information about the error.") })
    @RequestMapping(value = "/runtime/process-instances",
        produces = "application/json", 
        method = RequestMethod.POST)
    ResponseEntity<ProcessInstanceResponse> createProcessInstance(@ApiParam(value = ""  )  @Valid @RequestBody ProcessInstanceCreateRequest processInstanceCreateRequest);


    @ApiOperation(value = "Add an involved user to a process instance", nickname = "createProcessInstanceIdentityLinks", notes = "Note that the groupId in Response Body will always be null, as it�s only possible to involve users with a process-instance.", response = RestIdentityLink.class, authorizations = {
        @Authorization(value = "basicAuth")
    }, tags={ "Process Instances", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = RestIdentityLink.class),
        @ApiResponse(code = 201, message = "Indicates the process instance was found and the link is created."),
        @ApiResponse(code = 400, message = "Indicates the requested body did not contain a userId or a type."),
        @ApiResponse(code = 404, message = "Indicates the requested process instance was not found.") })
    @RequestMapping(value = "/runtime/process-instances/{processInstanceId}/identitylinks",
        produces = "application/json", 
        method = RequestMethod.POST)
    ResponseEntity<RestIdentityLink> createProcessInstanceIdentityLinks(@ApiParam(value = "The id of the process instance to the links for.",required=true) @PathVariable("processInstanceId") String processInstanceId,@ApiParam(value = ""  )  @Valid @RequestBody RestIdentityLink restIdentityLink);


    @ApiOperation(value = "Create variables or new binary variable on a process instance", nickname = "createProcessInstanceVariable", notes = "## Update multiples variables   ```JSON [    {       \"name\":\"intProcVar\"       \"type\":\"integer\"       \"value\":123    },     ... ] ```    Any number of variables can be passed into the request body array. More information about the variable format can be found in the REST variables section. Note that scope is ignored, only local variables can be set in a process instance.   The request should be of type multipart/form-data. There should be a single file-part included with the binary value of the variable. On top of that, the following additional form-fields can be present:  name: Required name of the variable.  type: Type of variable that is created. If omitted, binary is assumed and the binary data in the request will be stored as an array of bytes.", response = Object.class, authorizations = {
        @Authorization(value = "basicAuth")
    }, tags={ "Process Instances", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = Object.class),
        @ApiResponse(code = 201, message = "Indicates the process instance was found and variable is created."),
        @ApiResponse(code = 400, message = "Indicates the request body is incomplete or contains illegal values. The status description contains additional information about the error."),
        @ApiResponse(code = 404, message = "Indicates the requested process instance was not found."),
        @ApiResponse(code = 409, message = "Indicates the process instance was found but already contains a variable with the given name (only thrown when POST method is used). Use the update-method instead.") })
    @RequestMapping(value = "/runtime/process-instances/{processInstanceId}/variables",
        produces = "application/json", 
        method = RequestMethod.POST)
    ResponseEntity<Object> createProcessInstanceVariable(@ApiParam(value = "The id of the process instance to create the new variable for",required=true) @PathVariable("processInstanceId") String processInstanceId);


    @ApiOperation(value = "Delete all variables", nickname = "deleteLocalProcessVariable", notes = "", authorizations = {
        @Authorization(value = "basicAuth")
    }, tags={ "Process Instances", })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "Indicates variables were found and have been deleted. Response-body is intentionally empty."),
        @ApiResponse(code = 404, message = "Indicates the requested process instance was not found.") })
    @RequestMapping(value = "/runtime/process-instances/{processInstanceId}/variables",
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteLocalProcessVariable(@ApiParam(value = "",required=true) @PathVariable("processInstanceId") String processInstanceId);


    @ApiOperation(value = "Delete a process instance", nickname = "deleteProcessInstance", notes = "", authorizations = {
        @Authorization(value = "basicAuth")
    }, tags={ "Process Instances", })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "Indicates the process instance was found and deleted. Response body is left empty intentionally."),
        @ApiResponse(code = 404, message = "Indicates the requested process instance was not found.") })
    @RequestMapping(value = "/runtime/process-instances/{processInstanceId}",
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteProcessInstance(@ApiParam(value = "The id of the process instance to delete.",required=true) @PathVariable("processInstanceId") String processInstanceId,@ApiParam(value = "") @Valid @RequestParam(value = "deleteReason", required = false) String deleteReason);


    @ApiOperation(value = "Remove an involved user to from process instance", nickname = "deleteProcessInstanceIdentityLinks", notes = "", authorizations = {
        @Authorization(value = "basicAuth")
    }, tags={ "Process Instances", })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "Indicates the process instance was found and the link has been deleted. Response body is left empty intentionally."),
        @ApiResponse(code = 404, message = "Indicates the requested process instance was not found or the link to delete doesn�t exist. The response status contains additional information about the error.") })
    @RequestMapping(value = "/runtime/process-instances/{processInstanceId}/identitylinks/users/{identityId}/{type}",
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteProcessInstanceIdentityLinks(@ApiParam(value = "The id of the process instance.",required=true) @PathVariable("processInstanceId") String processInstanceId,@ApiParam(value = "The id of the user to delete link for.",required=true) @PathVariable("identityId") String identityId,@ApiParam(value = "Type of link to delete.",required=true) @PathVariable("type") String type);


    @ApiOperation(value = "Delete a variable", nickname = "deleteProcessInstanceVariable", notes = "", authorizations = {
        @Authorization(value = "basicAuth")
    }, tags={ "Process Instances", })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "Indicates the variable was found and has been deleted. Response-body is intentionally empty."),
        @ApiResponse(code = 404, message = "Indicates the requested variable was not found.") })
    @RequestMapping(value = "/runtime/process-instances/{processInstanceId}/variables/{variableName}",
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteProcessInstanceVariable(@ApiParam(value = "",required=true) @PathVariable("processInstanceId") String processInstanceId,@ApiParam(value = "",required=true) @PathVariable("variableName") String variableName,@ApiParam(value = "") @Valid @RequestParam(value = "scope", required = false) String scope);


    @ApiOperation(value = "Get a process instance", nickname = "getProcessInstance", notes = "", response = ProcessInstanceResponse.class, authorizations = {
        @Authorization(value = "basicAuth")
    }, tags={ "Process Instances", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Indicates the process instance was found and returned.", response = ProcessInstanceResponse.class),
        @ApiResponse(code = 404, message = "Indicates the requested process instance was not found.") })
    @RequestMapping(value = "/runtime/process-instances/{processInstanceId}",
        produces = "application/json", 
        method = RequestMethod.GET)
    ResponseEntity<ProcessInstanceResponse> getProcessInstance(@ApiParam(value = "The id of the process instance to get.",required=true) @PathVariable("processInstanceId") String processInstanceId);


    @ApiOperation(value = "Get diagram for a process instance", nickname = "getProcessInstanceDiagram", notes = "", response = byte[].class, responseContainer = "List", authorizations = {
        @Authorization(value = "basicAuth")
    }, tags={ "Process Instances", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Indicates the process instance was found and the diagram was returned.", response = byte[].class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "Indicates the requested process instance was not found but the process doesn�t contain any graphical information (BPMN:DI) and no diagram can be created."),
        @ApiResponse(code = 404, message = "Indicates the requested process instance was not found.") })
    @RequestMapping(value = "/runtime/process-instances/{processInstanceId}/diagram",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<List<byte[]>> getProcessInstanceDiagram(@ApiParam(value = "The id of the process instance to get the diagram for.",required=true) @PathVariable("processInstanceId") String processInstanceId);


    @ApiOperation(value = "Get a specific involved people from process instance", nickname = "getProcessInstanceIdentityLinks", notes = "", response = RestIdentityLink.class, authorizations = {
        @Authorization(value = "basicAuth")
    }, tags={ "Process Instances", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Indicates the process instance was found and the specified link is retrieved.", response = RestIdentityLink.class),
        @ApiResponse(code = 404, message = "Indicates the requested process instance was not found or the link to delete doesn�t exist. The response status contains additional information about the error.") })
    @RequestMapping(value = "/runtime/process-instances/{processInstanceId}/identitylinks/users/{identityId}/{type}",
        produces = "application/json", 
        method = RequestMethod.GET)
    ResponseEntity<RestIdentityLink> getProcessInstanceIdentityLinks(@ApiParam(value = "The id of the process instance to get.",required=true) @PathVariable("processInstanceId") String processInstanceId,@ApiParam(value = "",required=true) @PathVariable("identityId") String identityId,@ApiParam(value = "",required=true) @PathVariable("type") String type);


    @ApiOperation(value = "Get a variable for a process instance", nickname = "getProcessInstanceVariable", notes = "", response = RestVariable.class, authorizations = {
        @Authorization(value = "basicAuth")
    }, tags={ "Process Instances", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Indicates both the process instance and variable were found and variable is returned.", response = RestVariable.class),
        @ApiResponse(code = 404, message = "Indicates the requested process instance was not found or the process instance does not have a variable with the given name. Status description contains additional information about the error.") })
    @RequestMapping(value = "/runtime/process-instances/{processInstanceId}/variables/{variableName}",
        produces = "application/json", 
        method = RequestMethod.GET)
    ResponseEntity<RestVariable> getProcessInstanceVariable(@ApiParam(value = "The id of the process instance to the variables for.",required=true) @PathVariable("processInstanceId") String processInstanceId,@ApiParam(value = "Name of the variable to get.",required=true) @PathVariable("variableName") String variableName,@ApiParam(value = "") @Valid @RequestParam(value = "scope", required = false) String scope);


    @ApiOperation(value = "Get the binary data for a variable", nickname = "getProcessInstanceVariableData", notes = "", response = byte[].class, responseContainer = "List", authorizations = {
        @Authorization(value = "basicAuth")
    }, tags={ "Process Instances", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Indicates the process instance was found and the requested variables are returned.", response = byte[].class, responseContainer = "List"),
        @ApiResponse(code = 404, message = "Indicates the requested task was not found or the task doesn�t have a variable with the given name (in the given scope). Status message provides additional information.") })
    @RequestMapping(value = "/runtime/process-instances/{processInstanceId}/variables/{variableName}/data",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<List<byte[]>> getProcessInstanceVariableData(@ApiParam(value = "",required=true) @PathVariable("processInstanceId") String processInstanceId,@ApiParam(value = "",required=true) @PathVariable("variableName") String variableName,@ApiParam(value = "") @Valid @RequestParam(value = "scope", required = false) String scope);


    @ApiOperation(value = "List of process instances", nickname = "getProcessInstances", notes = "", response = DataResponse.class, authorizations = {
        @Authorization(value = "basicAuth")
    }, tags={ "Process Instances", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = DataResponse.class),
        @ApiResponse(code = 204, message = "Indicates request was successful and the process-instances are returned"),
        @ApiResponse(code = 404, message = "Indicates a parameter was passed in the wrong format . The status-message contains additional information.") })
    @RequestMapping(value = "/runtime/process-instances",
        produces = "application/json", 
        method = RequestMethod.GET)
    ResponseEntity<DataResponse> getProcessInstances(@ApiParam(value = "Only return models with the given version.") @Valid @RequestParam(value = "id", required = false) String id,@ApiParam(value = "Only return process instances with the given process definition key.") @Valid @RequestParam(value = "processDefinitionKey", required = false) String processDefinitionKey,@ApiParam(value = "Only return process instances with the given process definition id.") @Valid @RequestParam(value = "processDefinitionId", required = false) String processDefinitionId,@ApiParam(value = "Only return process instances with the given businessKey.") @Valid @RequestParam(value = "businessKey", required = false) String businessKey,@ApiParam(value = "Only return process instances in which the given user is involved.") @Valid @RequestParam(value = "involvedUser", required = false) String involvedUser,@ApiParam(value = "If true, only return process instance which are suspended. If false, only return process instances which are not suspended (active).") @Valid @RequestParam(value = "suspended", required = false) Boolean suspended,@ApiParam(value = "Only return process instances which have the given super process-instance id (for processes that have a call-activities).") @Valid @RequestParam(value = "superProcessInstanceId", required = false) String superProcessInstanceId,@ApiParam(value = "Only return process instances which have the given sub process-instance id (for processes started as a call-activity).") @Valid @RequestParam(value = "subProcessInstanceId", required = false) String subProcessInstanceId,@ApiParam(value = "Return only process instances which aren�t sub processes.") @Valid @RequestParam(value = "excludeSubprocesses", required = false) Boolean excludeSubprocesses,@ApiParam(value = "Indication to include process variables in the result.") @Valid @RequestParam(value = "includeProcessVariables", required = false) Boolean includeProcessVariables,@ApiParam(value = "Only return process instances with the given tenantId.") @Valid @RequestParam(value = "tenantId", required = false) String tenantId,@ApiParam(value = "Only return process instances with a tenantId like the given value.") @Valid @RequestParam(value = "tenantIdLike", required = false) String tenantIdLike,@ApiParam(value = "If true, only returns process instances without a tenantId set. If false, the withoutTenantId parameter is ignored.") @Valid @RequestParam(value = "withoutTenantId", required = false) Boolean withoutTenantId,@ApiParam(value = "Property to sort on, to be used together with the order.", allowableValues = "id, processDefinitionId, tenantId, processDefinitionKey") @Valid @RequestParam(value = "sort", required = false) String sort);


    @ApiOperation(value = "List of variables for a process instance", nickname = "getVariables", notes = "In case the variable is a binary variable or serializable, the valueUrl points to an URL to fetch the raw value. If it�s a plain variable, the value is present in the response. Note that only local scoped variables are returned, as there is no global scope for process-instance variables.", response = RestVariable.class, responseContainer = "List", authorizations = {
        @Authorization(value = "basicAuth")
    }, tags={ "Process Instances", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Indicates the process instance was found and variables are returned.", response = RestVariable.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "Indicates the requested process instance was not found.") })
    @RequestMapping(value = "/runtime/process-instances/{processInstanceId}/variables",
        produces = "application/json", 
        method = RequestMethod.GET)
    ResponseEntity<List<RestVariable>> getVariables(@ApiParam(value = "The id of the process instance to the variables for.",required=true) @PathVariable("processInstanceId") String processInstanceId,@ApiParam(value = "") @Valid @RequestParam(value = "scope", required = false) String scope);


    @ApiOperation(value = "Get involved people for process instance", nickname = "listProcessInstanceIdentityLinks", notes = "Note that the groupId in Response Body will always be null, as it�s only possible to involve users with a process-instance.", response = RestIdentityLink.class, responseContainer = "List", authorizations = {
        @Authorization(value = "basicAuth")
    }, tags={ "Process Instances", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Indicates the process instance was found and links are returned.", response = RestIdentityLink.class, responseContainer = "List"),
        @ApiResponse(code = 404, message = "Indicates the requested process instance was not found.") })
    @RequestMapping(value = "/runtime/process-instances/{processInstanceId}/identitylinks",
        produces = "application/json", 
        method = RequestMethod.GET)
    ResponseEntity<List<RestIdentityLink>> listProcessInstanceIdentityLinks(@ApiParam(value = "The id of the process instance to the links for.",required=true) @PathVariable("processInstanceId") String processInstanceId);


    @ApiOperation(value = "Activate or suspend a process instance", nickname = "performProcessInstanceAction", notes = "## Activate a process instance   ```JSON {   \"action\" : \"suspend\" } ```   ## Suspend a process instance   ```JSON {   \"action\" : \"activate\" } ```   ", response = ProcessInstanceResponse.class, authorizations = {
        @Authorization(value = "basicAuth")
    }, tags={ "Process Instances", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Indicates the process instance was found and action was executed.", response = ProcessInstanceResponse.class),
        @ApiResponse(code = 400, message = "  Indicates an invalid action was supplied."),
        @ApiResponse(code = 404, message = "Indicates the requested process instance was not found."),
        @ApiResponse(code = 409, message = "Indicates the requested process instance action cannot be executed since the process-instance is already activated/suspended.") })
    @RequestMapping(value = "/runtime/process-instances/{processInstanceId}",
        produces = "application/json", 
        method = RequestMethod.PUT)
    ResponseEntity<ProcessInstanceResponse> performProcessInstanceAction(@ApiParam(value = "The id of the process instance to activate/suspend.",required=true) @PathVariable("processInstanceId") String processInstanceId,@ApiParam(value = ""  )  @Valid @RequestBody ProcessInstanceActionRequest processInstanceActionRequest);


    @ApiOperation(value = "Query process instances", nickname = "queryProcessInstances", notes = "The request body can contain all possible filters that can be used in the List process instances URL query. On top of these, it�s possible to provide an array of variables to include in the query, with their format described here.  The general paging and sorting query-parameters can be used for this URL.", response = DataResponse.class, authorizations = {
        @Authorization(value = "basicAuth")
    }, tags={ "Process Instances", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Indicates request was successful and the process-instances are returned", response = DataResponse.class),
        @ApiResponse(code = 400, message = "Indicates a parameter was passed in the wrong format . The status-message contains additional information.") })
    @RequestMapping(value = "/query/process-instances",
        produces = "application/json", 
        method = RequestMethod.POST)
    ResponseEntity<DataResponse> queryProcessInstances(@ApiParam(value = ""  )  @Valid @RequestBody ProcessInstanceQueryRequest processInstanceQueryRequest);


    @ApiOperation(value = "Update a single variable on a process instance", nickname = "updateProcessInstanceVariable", notes = "", response = RestVariable.class, authorizations = {
        @Authorization(value = "basicAuth")
    }, tags={ "Process Instances", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Indicates both the process instance and variable were found and variable is updated.", response = RestVariable.class),
        @ApiResponse(code = 404, message = "Indicates the requested process instance was not found or the process instance does not have a variable with the given name. Status description contains additional information about the error.") })
    @RequestMapping(value = "/runtime/process-instances/{processInstanceId}/variables/{variableName}",
        produces = "application/json", 
        method = RequestMethod.PUT)
    ResponseEntity<RestVariable> updateProcessInstanceVariable(@ApiParam(value = "The id of the process instance to the variables for.",required=true) @PathVariable("processInstanceId") String processInstanceId,@ApiParam(value = "Name of the variable to get.",required=true) @PathVariable("variableName") String variableName);

}

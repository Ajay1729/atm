package com.lxisoft.atm.web.rest;

import com.lxisoft.atm.client.activiti.model.DataResponse;
import com.lxisoft.atm.client.activiti.model.atm.UserName;
import com.lxisoft.atm.client.activiti.model.atm.UserNamePin;
import com.lxisoft.atm.client.activiti.model.atm.UserNamePinAmount;
import com.lxisoft.atm.domain.Customer;

import com.lxisoft.atm.service.CustomerService;
import com.lxisoft.atm.web.rest.errors.BadRequestAlertException;
import com.lxisoft.atm.service.dto.CustomerDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.ApiParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

/**
 * REST controller for managing {@link com.lxisoft.atm.domain.Customer}.
 */
@RestController
@RequestMapping("/api")
public class CustomerResource {

    private final Logger log = LoggerFactory.getLogger(CustomerResource.class);

    private static final String ENTITY_NAME = "atmCustomer";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CustomerService customerService;

    public CustomerResource(CustomerService customerService) {
        this.customerService = customerService;
    }
    @GetMapping("/tasks")
	public ResponseEntity<DataResponse> getTasks(@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "nameLike", required = false) String nameLike,
			@RequestParam(value = "description", required = false) String description,
			@RequestParam(value = "priority", required = false) String priority,
			@RequestParam(value = "minimumPriority", required = false) String minimumPriority,
			@RequestParam(value = "maximumPriority", required = false) String maximumPriority,
			@RequestParam(value = "assignee", required = false) String assignee,
			@RequestParam(value = "assigneeLike", required = false) String assigneeLike,
			@RequestParam(value = "owner", required = false) String owner,
			@RequestParam(value = "ownerLike", required = false) String ownerLike,
			@RequestParam(value = "unassigned", required = false) String unassigned,
			@RequestParam(value = "delegationState", required = false) String delegationState,
			@RequestParam(value = "candidateUser", required = false) String candidateUser,
			@RequestParam(value = "candidateGroup", required = false) String candidateGroup,
			@RequestParam(value = "candidateGroups", required = false) String candidateGroups,
			@RequestParam(value = "involvedUser", required = false) String involvedUser,
			@RequestParam(value = "taskDefinitionKey", required = false) String taskDefinitionKey,
			@RequestParam(value = "taskDefinitionKeyLike", required = false) String taskDefinitionKeyLike,
			@RequestParam(value = "processInstanceId", required = false) String processInstanceId,
			@RequestParam(value = "processInstanceBusinessKey", required = false) String processInstanceBusinessKey,
			@RequestParam(value = "processInstanceBusinessKeyLike", required = false) String processInstanceBusinessKeyLike,
			@ApiParam(value = "Only return tasks which are part of a process instance which has a process definition with the given id.") @Valid @RequestParam(value = "processDefinitionId", required = false) String processDefinitionId,
			@ApiParam(value = "Only return tasks which are part of a process instance which has a process definition with the given key.") @Valid @RequestParam(value = "processDefinitionKey", required = false) String processDefinitionKey,
			@ApiParam(value = "Only return tasks which are part of a process instance which has a process definition with a key like the given value.") @Valid @RequestParam(value = "processDefinitionKeyLike", required = false) String processDefinitionKeyLike,
			@ApiParam(value = "Only return tasks which are part of a process instance which has a process definition with the given name.") @Valid @RequestParam(value = "processDefinitionName", required = false) String processDefinitionName,
			@ApiParam(value = "Only return tasks which are part of a process instance which has a process definition with a name like the given value.") @Valid @RequestParam(value = "processDefinitionNameLike", required = false) String processDefinitionNameLike,
			@ApiParam(value = "Only return tasks which are part of the execution with the given id.") @Valid @RequestParam(value = "executionId", required = false) String executionId,
			@ApiParam(value = "Only return tasks which are created on the given date.") @Valid @RequestParam(value = "createdOn", required = false) String createdOn,
			@ApiParam(value = "Only return tasks which are created before the given date.") @Valid @RequestParam(value = "createdBefore", required = false) String createdBefore,
			@ApiParam(value = "Only return tasks which are created after the given date.") @Valid @RequestParam(value = "createdAfter", required = false) String createdAfter,
			@ApiParam(value = "Only return tasks which are due on the given date.") @Valid @RequestParam(value = "dueOn", required = false) String dueOn,
			@ApiParam(value = "Only return tasks which are due before the given date.") @Valid @RequestParam(value = "dueBefore", required = false) String dueBefore,
			@ApiParam(value = "Only return tasks which are due after the given date.") @Valid @RequestParam(value = "dueAfter", required = false) String dueAfter,
			@ApiParam(value = "Only return tasks which don�t have a due date. The property is ignored if the value is false.") @Valid @RequestParam(value = "withoutDueDate", required = false) Boolean withoutDueDate,
			@ApiParam(value = "Only return tasks that are not a subtask of another task.") @Valid @RequestParam(value = "excludeSubTasks", required = false) Boolean excludeSubTasks,
			@ApiParam(value = "If true, only return tasks that are not suspended (either part of a process that is not suspended or not part of a process at all). If false, only tasks that are part of suspended process instances are returned.") @Valid @RequestParam(value = "active", required = false) Boolean active,
			@ApiParam(value = "Indication to include task local variables in the result.") @Valid @RequestParam(value = "includeTaskLocalVariables", required = false) Boolean includeTaskLocalVariables,
			@ApiParam(value = "Indication to include process variables in the result.") @Valid @RequestParam(value = "includeProcessVariables", required = false) Boolean includeProcessVariables,
			@ApiParam(value = "Only return tasks with the given tenantId.") @Valid @RequestParam(value = "tenantId", required = false) String tenantId,
			@ApiParam(value = "Only return tasks with a tenantId like the given value.") @Valid @RequestParam(value = "tenantIdLike", required = false) String tenantIdLike,
			@ApiParam(value = "If true, only returns tasks without a tenantId set. If false, the withoutTenantId parameter is ignored.") @Valid @RequestParam(value = "withoutTenantId", required = false) Boolean withoutTenantId,
			@ApiParam(value = "Select tasks that has been claimed or assigned to user or waiting to claim by user (candidate user or groups).") @Valid @RequestParam(value = "candidateOrAssigned", required = false) String candidateOrAssigned,
			@ApiParam(value = "Select tasks with the given category. Note that this is the task category, not the category of the process definition (namespace within the BPMN Xml). ") @Valid @RequestParam(value = "category", required = false) String category) {

		return customerService.getTasks(name, nameLike, description, priority, minimumPriority, maximumPriority,
				assignee, assigneeLike, owner, ownerLike, unassigned, delegationState, candidateUser, candidateGroup,
				candidateGroups, involvedUser, taskDefinitionKey, taskDefinitionKeyLike, processInstanceId,
				processInstanceBusinessKey, processInstanceBusinessKeyLike, processDefinitionId, processDefinitionKey,
				processDefinitionKeyLike, processDefinitionName, processDefinitionNameLike, executionId, createdOn,
				createdBefore, createdAfter, dueOn, dueBefore, dueAfter, withoutDueDate, excludeSubTasks, active,
				includeTaskLocalVariables, includeProcessVariables, tenantId, tenantIdLike, withoutTenantId,
				candidateOrAssigned, category);
	}

    
    /**
     * {@code POST  /customers} : Create a new customer.
     *
     * @param customerDTO the customerDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new customerDTO, or with status {@code 400 (Bad Request)} if the customer has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/customers")
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) throws URISyntaxException {
        log.debug("REST request to save Customer : {}", customerDTO);
        if (customerDTO.getId() != null) {
            throw new BadRequestAlertException("A new customer cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CustomerDTO result = customerService.save(customerDTO);
        return ResponseEntity.created(new URI("/api/customers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /customers} : Updates an existing customer.
     *
     * @param customerDTO the customerDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated customerDTO,
     * or with status {@code 400 (Bad Request)} if the customerDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the customerDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/customers")
    public ResponseEntity<CustomerDTO> updateCustomer(@RequestBody CustomerDTO customerDTO) throws URISyntaxException {
        log.debug("REST request to update Customer : {}", customerDTO);
        if (customerDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CustomerDTO result = customerService.save(customerDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, customerDTO.getId().toString()))
            .body(result);
    }
    @GetMapping("/initiates")
    public String initiates() {
    	String s= customerService.initiate();
    	   return s;
    	}
   
/**
 *Get  customer by insert card(name) entering*

    @GetMapping("/customersByName/{name}")
    public CustomerDTO insertCard(@PathVariable("name") String name) {
    	CustomerDTO customer=customerService.getName(name);
    	  
    	return customer;
    	}
   
   
     
    @GetMapping("/customerByPin/{pin}")
	public String check(@PathVariable("pin") Integer pin,@RequestBody Customer cus) {
		String res="";
		CustomerDTO customer=customerService.getByPin(pin);
		if(customer.equals(null)) {
			res=res+"No user exist of this pin";
		}
		else if(customer.getPin().equals(cus.getPin()))
		{
			res=res+"Welcome "+customer.getName()+",";
		}
		else res=res+"error";
		return res;
	}
     
    @GetMapping("/customerByPin/{pin}/{amount}")
	public String withdraw(@PathVariable("pin") Integer pin,@PathVariable("amount") Integer amount) {
    	CustomerDTO customer=customerService.getByPin(pin);
		
		String res="";
		if((customer.getBalance()-amount)>0)
		{
			res=res+"Transaction sucessful";
			customer.setBalance((customer.getBalance()-amount));
		}
		else
			res=res+"Unsucessful Transaction";
		res=res+" Balance:"+customer.getBalance();
		customerService.save(customer);
		return res;
	} 
    */
    /**
     * {@code GET  /customers} : get all the customers.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of customers in body.
     */
    @GetMapping("/customers")
    public ResponseEntity<List<CustomerDTO>> getAllCustomers(Pageable pageable) {
        log.debug("REST request to get a page of Customers");
        Page<CustomerDTO> page = customerService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /customers/:id} : get the "id" customer.
     *
     * @param id the id of the customerDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the customerDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/customers/{id}")
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable Long id) {
        log.debug("REST request to get Customer : {}", id);
        Optional<CustomerDTO> customerDTO = customerService.findOne(id);
        return ResponseUtil.wrapOrNotFound(customerDTO);
    }

    /**
     * {@code DELETE  /customers/:id} : delete the "id" customer.
     *
     * @param id the id of the customerDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/customers/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        log.debug("REST request to delete Customer : {}", id);
        customerService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
    /**
     * activiti tasks
     * 
     * 
     * */
    @PostMapping("/cardInsert/{taskId}")
    public String cardInsertion(@PathVariable("taskId") String taskId,@RequestBody  UserName user ) {
    	String res="";
    	CustomerDTO customer=customerService.getByName(taskId,user);  
    	if(customer.equals(null)) {
    		res="No User Found!!";
    	}
    	else
    	{
    		res="Card inserted sucessful...Welcome "+customer.getName()+" ,";
    	}
    	return res ;
    	}
   
   
    
    @PostMapping("/pinInsert/{taskId}")
	public String pinInsertion(@PathVariable("taskId") String taskId,@RequestBody  UserNamePin user ) {
    	String res="";
    	 
		CustomerDTO customer=customerService.getByNamePin(taskId,user);
		if(customer.equals(null)) {
			res=res+"Incorrect pin";
		}
		else  {
			res=res+"Correct pin..Welcome "+customer.getName()+",";
		}		 
		return res;
	} 
    @PostMapping("/amountInsert/{taskId}")
   	public String amountInsertion(@PathVariable("taskId") String taskId,@RequestBody  UserNamePinAmount user ) {
       	String res="";
       	 
   		CustomerDTO customer=customerService.getByNamePinAmount(taskId,user);
   		if(customer.equals(null)) {
   			res=res+"Transaction Error ...\nAmount :"+user.getAmount();
   		}
   		else  {
   			res=res+"Transaction sucessful....\n Collect cash:"+user.getAmount();
   		}
   		res=res+" Balance :"+customer.getBalance();
   		return res;
   	} 
    @PostMapping("/finish/{taskId}")
    public  String close(@PathVariable("taskId") String taskId) {
   	 String res="Thanking for using!!";
	 
   	return res;
    }
}

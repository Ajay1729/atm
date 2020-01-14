package com.lxisoft.atm.service.impl;

import com.lxisoft.atm.service.CustomerService;
import com.lxisoft.atm.client.activiti.api.FormsApi;
import com.lxisoft.atm.client.activiti.api.ProcessInstancesApi;
import com.lxisoft.atm.client.activiti.api.TasksApi;
import com.lxisoft.atm.client.activiti.model.DataResponse;
import com.lxisoft.atm.client.activiti.model.ProcessInstanceCreateRequest;
import com.lxisoft.atm.client.activiti.model.ProcessInstanceResponse;
import com.lxisoft.atm.client.activiti.model.RestFormProperty;
import com.lxisoft.atm.client.activiti.model.RestVariable;
import com.lxisoft.atm.client.activiti.model.SubmitFormRequest; 
import com.lxisoft.atm.client.activiti.model.atm.UserName;
import com.lxisoft.atm.client.activiti.model.atm.UserNamePin;
import com.lxisoft.atm.client.activiti.model.atm.UserNamePinAmount;
import com.lxisoft.atm.domain.Customer;
import com.lxisoft.atm.repository.CustomerRepository;
import com.lxisoft.atm.service.dto.CustomerDTO;
import com.lxisoft.atm.service.mapper.CustomerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable; 
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

/**
 * Service Implementation for managing {@link Customer}.
 */
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);
    @Autowired
    private ProcessInstancesApi processInstanceApi;
    private final CustomerRepository customerRepository;
    @Autowired
	  private TasksApi tasksApi;
    @Autowired
    private FormsApi formsApi;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

	public CustomerDTO getByName(String taskId,UserName userData){
		log .info("into ====================customerStatus()");
   		List<RestFormProperty>formProperties=new ArrayList<RestFormProperty>();
   		SubmitFormRequest submitFormRequest = new SubmitFormRequest();
   		submitFormRequest.setAction("completed");
   		submitFormRequest.setTaskId(taskId);
		 RestFormProperty statusFormProperty = new RestFormProperty();
   		statusFormProperty.setId("name");
   		statusFormProperty.setName("name");
   		statusFormProperty.setType("String");
   		statusFormProperty.setReadable(true);
   		 statusFormProperty.setValue(userData.getName());
   		formProperties.add(statusFormProperty);
   		 submitFormRequest.setProperties(formProperties); 
   		formsApi.submitForm(submitFormRequest);
   		
		System.out.println("##"+taskId);
		Customer c=customerRepository.findByName(userData.getName());
		if(c.equals(null))
			return null;
		else
            return customerMapper.toDto(c);
	}
	
	public CustomerDTO getByNamePin(String taskId,UserNamePin userData){
		log .info("into ====================customerStatus()");
   		List<RestFormProperty>formProperties=new ArrayList<RestFormProperty>();
   		SubmitFormRequest submitFormRequest = new SubmitFormRequest();
   		submitFormRequest.setAction("completed");
   		submitFormRequest.setTaskId(taskId);
		 RestFormProperty statusFormProperty = new RestFormProperty();
   		statusFormProperty.setId("pin");
   		statusFormProperty.setName("pin");
   		statusFormProperty.setType("Integer");
   		statusFormProperty.setReadable(true);
   	    statusFormProperty.setValue(Integer.toString(userData.getPin()));
   		formProperties.add(statusFormProperty);
   		 submitFormRequest.setProperties(formProperties); 
   		formsApi.submitForm(submitFormRequest);
   		
		System.out.println("##"+taskId);
		Customer c=customerRepository.findByName(userData.getName());
		Customer c1=customerRepository.findByPin(userData.getPin());
        if(c.equals(c1))
		 return customerMapper.toDto(c);
        else
         return null;
	}
	public CustomerDTO getByNamePinAmount(String taskId,UserNamePinAmount userData){
		log .info("into ====================customerStatus()");
   		List<RestFormProperty>formProperties=new ArrayList<RestFormProperty>();
   		SubmitFormRequest submitFormRequest = new SubmitFormRequest();
   		submitFormRequest.setAction("completed");
   		submitFormRequest.setTaskId(taskId);
		 RestFormProperty statusFormProperty = new RestFormProperty();
   		statusFormProperty.setId("amount");
   		statusFormProperty.setName("amount");
   		statusFormProperty.setType("Integer");
   		statusFormProperty.setReadable(true);
    	 statusFormProperty.setValue(Integer.toString(userData.getAmount()));
   		formProperties.add(statusFormProperty);
   		 submitFormRequest.setProperties(formProperties); 
   		formsApi.submitForm(submitFormRequest);
   		
		System.out.println("##"+taskId);
		Customer c=customerRepository.findByName(userData.getName());
		Customer c1=customerRepository.findByPin(userData.getPin());
		 
        if(c.equals(c1))
        {
        	if((c.getBalance()-userData.getAmount())>=0) {
        		c.setBalance(c.getBalance()-userData.getAmount());
        		return customerMapper.toDto(c);
        	}
        	else 
        		return null;
        	
        }
        else
         return null;
	}
	

	
	public ResponseEntity<DataResponse> getTasks(String name, String nameLike, String description, String priority,
			String minimumPriority, String maximumPriority, String assignee, String assigneeLike, String owner,
			String ownerLike, String unassigned, String delegationState, String candidateUser, String candidateGroup,
			String candidateGroups, String involvedUser, String taskDefinitionKey, String taskDefinitionKeyLike,
			String processInstanceId, String processInstanceBusinessKey, String processInstanceBusinessKeyLike,
			@Valid String processDefinitionId, @Valid String processDefinitionKey,
			@Valid String processDefinitionKeyLike, @Valid String processDefinitionName,
			@Valid String processDefinitionNameLike, @Valid String executionId, @Valid String createdOn,
			@Valid String createdBefore, @Valid String createdAfter, @Valid String dueOn, @Valid String dueBefore,
			@Valid String dueAfter, @Valid Boolean withoutDueDate, @Valid Boolean excludeSubTasks,
			@Valid Boolean active, @Valid Boolean includeTaskLocalVariables, @Valid Boolean includeProcessVariables,
			@Valid String tenantId, @Valid String tenantIdLike, @Valid Boolean withoutTenantId,
			@Valid String candidateOrAssigned, @Valid String category) {
		
		return tasksApi.getTasks(name, nameLike, description, priority, minimumPriority, maximumPriority, assignee, assigneeLike, owner, ownerLike, unassigned, delegationState, candidateUser, candidateGroup, candidateGroups, involvedUser, taskDefinitionKey, taskDefinitionKeyLike, processInstanceId, processInstanceBusinessKey, processInstanceBusinessKeyLike, processDefinitionId, processDefinitionKey, processDefinitionKeyLike, processDefinitionName, processDefinitionNameLike, executionId, createdOn, createdBefore, createdAfter, dueOn, dueBefore, dueAfter, withoutDueDate, excludeSubTasks, active, includeTaskLocalVariables, includeProcessVariables, tenantId, tenantIdLike, withoutTenantId, candidateOrAssigned, category);
	}
	public CustomerDTO getByPin(Integer pin) {
		Customer c=customerRepository.findByPin(pin);
		return customerMapper.toDto(c);
	}
    /**
     * Save a customer.
     *
     * @param customerDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public CustomerDTO save(CustomerDTO customerDTO) {
        log.debug("Request to save Customer : {}", customerDTO);
        Customer customer = customerMapper.toEntity(customerDTO);
        customer = customerRepository.save(customer);
        return customerMapper.toDto(customer);
    }

    /**
     * Get all the customers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CustomerDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Customers");
        return customerRepository.findAll(pageable)
            .map(customerMapper::toDto);
    }


    /**
     * Get one customer by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CustomerDTO> findOne(Long id) {
        log.debug("Request to get Customer : {}", id);
        return customerRepository.findById(id)
            .map(customerMapper::toDto);
    }

    /**
     * Delete the customer by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Customer : {}", id);
        customerRepository.deleteById(id);
    }

	@Override
	public String initiate() {
		ProcessInstanceCreateRequest processInstanceCreateRequest=new ProcessInstanceCreateRequest();
   		//List<RestVariable> variables=new ArrayList<RestVariable>(); 
   		processInstanceCreateRequest.setProcessDefinitionId("AtmWithdraw:9:5008");
   		//log.info("*****************************************************"+processInstanceCreateRequest.getVariables());
   		
   		ResponseEntity<ProcessInstanceResponse> processInstanceResponse = processInstanceApi
				.createProcessInstance(processInstanceCreateRequest);
		String processInstanceId = processInstanceResponse.getBody().getId();
		String processDefinitionId = processInstanceCreateRequest.getProcessDefinitionId();
		log.info("++++++++++++++++processDefinitionId++++++++++++++++++"+ processDefinitionId);
		log.info("++++++++++++++++ProcessInstanceId is+++++++++++++ " + processInstanceId);
		
   		processInstanceApi.createProcessInstance(processInstanceCreateRequest);
   		
		
		return processInstanceId;
	}

	 
}

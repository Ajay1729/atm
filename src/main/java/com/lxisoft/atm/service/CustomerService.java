package com.lxisoft.atm.service;

import com.lxisoft.atm.client.activiti.model.DataResponse;  
import com.lxisoft.atm.client.activiti.model.atm.UserName;
import com.lxisoft.atm.client.activiti.model.atm.UserNamePin;
import com.lxisoft.atm.client.activiti.model.atm.UserNamePinAmount;
import com.lxisoft.atm.service.dto.CustomerDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.Streamable;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import javax.validation.Valid;

/**
 * Service Interface for managing {@link com.lxisoft.atm.domain.Customer}.
 */
public interface CustomerService {

    /**
     * Save a customer.
     *
     * @param customerDTO the entity to save.
     * @return the persisted entity.
     */
    CustomerDTO save(CustomerDTO customerDTO);

    /**
     * Get all the customers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CustomerDTO> findAll(Pageable pageable);


    /**
     * Get the "id" customer.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CustomerDTO> findOne(Long id);

    /**
     * Delete the "id" customer.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

	CustomerDTO getByName(String id, UserName userdata);

	CustomerDTO getByNamePin(String id, UserNamePin userdata);
	
	CustomerDTO getByNamePinAmount(String id, UserNamePinAmount userdata);

	ResponseEntity<DataResponse> getTasks(String name, String nameLike, String description, String priority,
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
			@Valid String candidateOrAssigned, @Valid String category);

  String initiate();
}

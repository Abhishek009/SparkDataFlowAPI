
 package com.api.repository;
 


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.model.InputModal;

@Repository
 public interface InputModalRepository extends JpaRepository<InputModal, Long> {
	
 
 }

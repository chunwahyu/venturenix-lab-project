package com.sp2603.project.repository;

import com.sp2603.project.data.transaction.entity.TransactionEntity;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<TransactionEntity, Integer> {

}

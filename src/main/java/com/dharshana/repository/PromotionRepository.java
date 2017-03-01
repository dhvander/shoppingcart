/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dharshana.repository;

import com.dharshana.domain.Promotion;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 *
 * @author dharshana
 */
@RepositoryRestResource()
public interface PromotionRepository extends PagingAndSortingRepository<Promotion, String> {
	   
}

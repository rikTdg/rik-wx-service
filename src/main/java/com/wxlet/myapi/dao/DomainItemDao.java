package com.wxlet.myapi.dao;

import com.wxlet.myapi.entity.DomainItem;
import com.wxlet.myapi.entity.LatestItemDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface DomainItemDao extends PagingAndSortingRepository<DomainItem, Integer> {

    Page<DomainItem> findAll(Pageable pageable);

    Page<DomainItem> findByTitleContaining(Pageable pageable, String title);

    Page<DomainItem> findByDomainContaining(String domain, Pageable pageable);

    @Query("SELECT new com.wxlet.myapi.entity.LatestItemDTO(i.domain, MAX(i.datetimeGen)) FROM DomainItem i GROUP BY i.domain")
    List<LatestItemDTO> findLatestByDomain();
}

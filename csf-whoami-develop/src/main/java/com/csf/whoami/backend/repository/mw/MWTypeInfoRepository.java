/**
 *
 */
package com.csf.whoami.backend.repository.mw;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.csf.whoami.backend.entity.mw.MWTypeInfoEntity;

/**
 * @author tuan
 *
 */
@Repository
public interface MWTypeInfoRepository extends JpaRepository<MWTypeInfoEntity, Integer> {
}

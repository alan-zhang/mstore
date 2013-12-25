package org.azt.mstore.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository("resourcesDao")
public interface ResourcesDao {

	@Select("select tr.ResourcePath " +
			"from tb_resources tr, tb_authorities_resources tar, tb_authorities ta " + 
			"where tr.ID = tar.ResourceId and ta.ID = tar.AuthorityId and ta.AuthorityName = #{authorityName}")
	public List<String> getResourcePathsByAuthorityName(String authorityName);
}

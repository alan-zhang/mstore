package org.azt.mstore.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.azt.mstore.model.Authority;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityDao {

	@Select("select * from tb_authorities")
	public List<Authority> getAllAuthorities();

    @Select("select * from tb_authorities where authorityName=#{name}")
    public Authority getAuthorityByName(String name);
	
	@Insert("insert into tb_authorities(AuthorityName,AuthorityDesc,CreateTime,IsBackend,IsValid) "+
            "value(#{authorityName},#{authorityDesc},now(),1,1)")
    public int saveAuthority(Authority authority);

    @Delete("delete from tb_authorities where id=#{id}")
    public int deleteAuthority(int id);
}

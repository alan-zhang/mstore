package org.azt.mstore.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.azt.mstore.model.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao {
	
	@Select("select * from tb_roles where IsValid=1")
	public List<Role> getAllRoles();
	
	@Insert("insert into tb_roles(RoleName,RoleDesc,CreateTime,IsBackend,IsValid) "+
			"value(#{roleName},#{roleDesc},#{createTime},#{isBackend},1)")
	public int addRole(Role role);
	
	@Select("select tr.* from tb_roles tr, tb_users_roles tur "+
			"where tur.RoleId = tr.ID and tur.UserId = #{userId} and tr.IsValid =1 ")
	public List<Role> getAllRolesByUserId(int userId);
	
	@Insert("insert into tb_users_roles(UserId,RoleId,CreateTime) value(#{userId},#{roleId},now())")
	public void addUserRole(@Param("userId")int userId, @Param("roleId")int roleId);
	
	@Delete("delete from tb_users_roles where UserId=#{userId}")
	public int deleteUserRole(int userId);
}

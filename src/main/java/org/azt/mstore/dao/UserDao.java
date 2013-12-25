package org.azt.mstore.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.azt.mstore.model.Role;
import org.azt.mstore.model.User;
import org.azt.mstore.model.UserInfo;
import org.springframework.stereotype.Repository;


@Repository
public interface UserDao {

	@Select("select * from tb_users where UserName=#{userName}")
	public User getUserByUserName(String userName);
	
	@Insert("insert into tb_users(UserId,UserName,Password,CreateTime) value(#{userId},#{userName},#{password},now())")
	public int addUserAndPassword(User user);
	
	@Delete("delete from tb_users where UserId=#{userId}")
	public int deleteUserAndPassword(int userId);
	
	@Select("select r.* from tb_roles r, tb_users_roles ur where ur.RoleId = r.ID and ur.UserId = #{userId}")
	public List<Role> getRolesByUserId(int userId);
	
	@Select("select * from tb_user_info")
	public List<UserInfo> getAllUserInfos();
	
	@Select("select * from tb_user_info where UserName=#{userName}")
	public UserInfo getUserInfoByUserName(String userName);
	
	@Update("update tb_user_info set UserName=#{userName},RealName=#{realName},Age=#{age},"+
			"Sex=#{sex},Email=#{email},Phone=#{phone},Address=#{address},IsBackend=#{isBackend} "+
			"where ID=#{id}")
	public int updteUserInfo(UserInfo user);
	
	@Insert("insert into tb_user_info(UserName,RealName,Age,Sex,Email,Phone,Address,IsBackend,CreateTime,IsValid) "+
			"value(#{userName},#{realName},#{age},#{sex},#{email},#{phone},#{address},#{isBackend},now(),1)")
	public int addUserInfo(UserInfo user);
	
	@Delete("delete from tb_user_info where id=#{id}")
	public int deleteUserInfo(int id);
	
}

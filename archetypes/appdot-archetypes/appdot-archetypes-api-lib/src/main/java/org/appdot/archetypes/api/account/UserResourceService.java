package org.appdot.archetypes.api.account;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.appdot.archetypes.api.dto.UserDTO;
import org.appdot.archetypes.entity.account.User;
import org.appdot.archetypes.service.account.AccountManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springside.modules.mapper.BeanMapper;

import com.sun.jersey.api.NotFoundException;
import com.sun.jersey.multipart.MultiPart;

/**
 * 用户资源 REST服务
 *
 * 
 * @author liand
 */
@Component
@Path("/users")
public class UserResourceService {

	@Autowired
	private AccountManager accountManager;

	/**
	 * 获取所有用户, 演示与Shiro结合的方法级权限检查.
	 */
	@GET
	@RequiresPermissions("users:view")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<UserDTO> getAllUser() {
		List<User> entityList = accountManager.getAll();
		return BeanMapper.mapList(entityList, UserDTO.class);
	}

	/**
	 * 获取用户.
	 */
	@GET
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public UserDTO getUser(@PathParam("id") Long id) {
		User entity = accountManager.get(id);

		if (entity == null) {
			String message = "用户不存在(id:" + id + ")";
			throw new NotFoundException(message);
		}

		return BeanMapper.map(entity, UserDTO.class);
	}

	/**
	 * 搜索用户.
	 * 
	 * 演示QueryParam与不同格式不同返回内容的Response.
	 */
	@GET
	@Path("/search")
	public Response searchUser(@QueryParam("name") String name,
			@QueryParam("format") @DefaultValue("json") String format) {
		User entity = accountManager.getUserByLoginName(name);

		if (entity == null) {
			String message = "用户不存在";
			throw new NotFoundException(message);
		}

		//返回JSON格式的对象.
		UserDTO dto = BeanMapper.map(entity, UserDTO.class);
		return Response.ok(dto, MediaType.APPLICATION_JSON).build();
	}

	/**
	 * 接收Multi-Part的请求内容.
	 */
	@Path("/multipart")
	@POST
	@Consumes("multipart/mixed")
	public String multiPart(MultiPart multiPart) {
		User user = multiPart.getBodyParts().get(0).getEntityAs(User.class);
		String text = multiPart.getBodyParts().get(1).getEntityAs(String.class);
		return user.getName() + ":" + text;
	}
}

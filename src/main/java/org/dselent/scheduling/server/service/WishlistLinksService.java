package org.dselent.scheduling.server.service;

import java.sql.SQLException;
import java.util.List;

//import org.dselent.scheduling.server.dto.RegisterUserDto;
import org.dselent.scheduling.server.dto.WishlistLinksDto;
import org.dselent.scheduling.server.model.WishlistLinks;
//import org.dselent.scheduling.server.model.UserInfo;
import org.springframework.stereotype.Service;

/**
 * Service layer to specify all business logic. Calls the dao layer when data retrieval is needed.
 * Interfaces specify the behavior and the implementation provide the specific implementations.
 * 
 * @author dselent
 *
 */
@Service
public interface WishlistLinksService
{
	public List<WishlistLinks> wishlistLinks(Integer instructorInfoId) throws SQLException;
    //public UserInfo loginUser(String userName, String password);
}
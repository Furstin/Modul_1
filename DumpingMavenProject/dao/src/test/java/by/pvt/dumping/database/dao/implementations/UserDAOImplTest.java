package by.pvt.dumping.database.dao.implementations;

import by.pvt.dumping.database.dao.interfaces.UserDAO;
import by.pvt.dumping.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;

import static org.junit.Assert.assertFalse;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class UserDAOImplTest {

    @Mock
    UserDAO userDAO;
    private UserDAOImpl userDAOImpl;


    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        userDAOImpl = new UserDAOImpl();
        userDAOImpl.setUserDAO(userDAO);
    }


    @Test
    public void addUserShouldAddUser() throws SQLException {

        User user = new User();
        when(userDAO.addUser(any(User.class))).thenReturn(true);
        boolean result = userDAOImpl.addUser(user);

        assertFalse(result);

    }

    @Test
    public void addUserShouldNOTAddUser() throws SQLException {

        User user = new User();
        when(userDAO.addUser(user)).thenReturn(false);
        boolean result = userDAOImpl.addUser(user);

        assertFalse(result);

    }

    @Test
    public void removeUserShouldRemoveUser() throws SQLException {

        User user = new User();
        when(userDAO.getUser("kate@gmail.com")).thenReturn(user);
        when(userDAO.checkEmail("kate@gmail.com")).thenReturn(true);
        boolean result = userDAO.removeUser("kate@gmail.com");

        assertFalse(result);

        verify(userDAO,times(1)).removeUser("kate@gmail.com");
    }

    @Test
    public void removeUserShouldNOTRemoveUser() throws SQLException {

        User user = new User();
        when(userDAO.getUser("kate@gmail.com")).thenReturn(user);
        when(userDAO.checkEmail("kate@gmail.com")).thenReturn(false);
        boolean result = userDAO.removeUser("kate@gmail.com");

        assertFalse(result);

        verify(userDAO).removeUser("kate@gmail.com");
    }




}





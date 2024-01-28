package com.iut.banque.test.controller;

import com.iut.banque.constants.LoginConstants;
import com.iut.banque.controller.Connect;
import com.iut.banque.facade.BanqueFacade;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.opensymphony.xwork2.Action;

import javax.servlet.ServletContext;

public class TestsConnect {

    private Connect connect;

    @Before
    public void setUp() {
        // Créer un mock de ServletContext
        ServletContext servletContext = mock(ServletContext.class);

        // Injecter le mock de ServletContext lors de la création de l'objet Connect
        connect = new Connect(servletContext);
    }

    @Test
    public void testLoginSuccess() {
        // Mock the BanqueFacade
        BanqueFacade mockBanque = mock(BanqueFacade.class);
        when(mockBanque.tryLogin(anyString(), anyString())).thenReturn(LoginConstants.USER_IS_CONNECTED);
        connect.setBanque(mockBanque);

        // Set userCde and userPwd
        connect.setUserCde("userCde");
        connect.setUserPwd("userPwd");

        // Execute the login method
        String result = connect.login();

        // Verify that the login method returns SUCCESS
        assertEquals(Action.SUCCESS, result);
    }

    @Test
    public void testLoginManagerSuccess() {
        // Mock the BanqueFacade
        BanqueFacade mockBanque = mock(BanqueFacade.class);
        when(mockBanque.tryLogin(anyString(), anyString())).thenReturn(LoginConstants.MANAGER_IS_CONNECTED);
        connect.setBanque(mockBanque);

        // Set userCde and userPwd
        connect.setUserCde("managerCde");
        connect.setUserPwd("managerPwd");

        // Execute the login method
        String result = connect.login();

        // Verify that the login method returns SUCCESSMANAGER
        assertEquals("SUCCESSMANAGER", result);
    }

    @Test
    public void testLoginFailure() {
        // Mock the BanqueFacade
        BanqueFacade mockBanque = mock(BanqueFacade.class);
        when(mockBanque.tryLogin(anyString(), anyString())).thenReturn(LoginConstants.LOGIN_FAILED);
        connect.setBanque(mockBanque);

        // Set userCde and userPwd
        connect.setUserCde("invalidUser");
        connect.setUserPwd("invalidPwd");

        // Execute the login method
        String result = connect.login();

        // Verify that the login method returns ERROR
        assertEquals(Action.ERROR, result);
    }


}

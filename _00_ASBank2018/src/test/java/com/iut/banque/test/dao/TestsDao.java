package com.iut.banque.test.dao;
import com.iut.banque.interfaces.IDao;
import com.iut.banque.modele.Utilisateur;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class TestsDao {

    private IDao dao;

    @Before
    public void setUp() {
        this.dao = mock(IDao.class);
    }

    @Test
    public void testDaoIsConnected() {
        IDao dao = mock(IDao.class);
        Utilisateur utilisateur = mock(Utilisateur.class);
        Mockito.when(utilisateur.getUserId()).thenReturn("id");
        Mockito.when(utilisateur.getUserPwd()).thenReturn("pwd");
        Mockito.when(dao.getUserById(Matchers.anyString())).thenReturn(utilisateur);
        Assert.assertEquals(utilisateur.getUserId(), "id");
    }

}

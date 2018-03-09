package com.nc.netcracker_project.desctop_rmi_client.util;

import com.nc.netcracker_project.desktop_rmi_client.util.PropertyLoader;
import org.junit.Assert;
import org.junit.Test;

public class PropertyLoaderTest {
    @Test
    public void path_test(){
        String property = PropertyLoader.getProperty("server.rmi.host");
        Assert.assertEquals(property,"localhost");
    }

}

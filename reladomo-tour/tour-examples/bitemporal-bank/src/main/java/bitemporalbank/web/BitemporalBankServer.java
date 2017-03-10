/*
Copyright 2016 Goldman Sachs.
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing,
software distributed under the License is distributed on an
"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
KIND, either express or implied.  See the License for the
specific language governing permissions and limitations
under the License.
*/

package bitemporalbank.web;

import bitemporalbank.serialization.BitemporalBankJacksonObjectMapperProvider;
import com.gs.fw.common.mithra.MithraManager;
import com.gs.fw.common.mithra.MithraManagerProvider;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

public class BitemporalBankServer
{

    private ResourceConfig config;

    public BitemporalBankServer(String runtimeConfigXML) throws Exception
    {
        this.initReladomo(runtimeConfigXML);
    }

    public static void main(String[] args) throws Exception
    {
        String runtimeConfigXML = "reladomoxml/bitemporalbankRuntimeConfiguration.xml";
        new BitemporalBankServer(runtimeConfigXML).start();
    }

    protected void initReladomo(String runtimeConfigXML) throws Exception
    {
        MithraManager mithraManager = MithraManagerProvider.getMithraManager();
        mithraManager.setTransactionTimeout(60 * 1000);
        loadReladomoXML(runtimeConfigXML);
    }

    protected void loadReladomoXML(String fileName) throws Exception
    {
        InputStream stream = BitemporalBankServer.class.getClassLoader().getResourceAsStream(fileName);
        if (stream == null)
        {
            throw new Exception("Failed to locate " + fileName + " in classpath");
        }
        MithraManagerProvider.getMithraManager().readConfiguration(stream);
        stream.close();
    }

    protected void initResources()
    {
        this.config = new ResourceConfig(CustomerResource.class);
        config.register(JacksonFeature.class);
        config.register(BitemporalBankJacksonObjectMapperProvider.class);
    }

    public void start() throws IOException
    {
        initResources();
        URI baseUri = UriBuilder.fromUri("http://localhost/").port(9998).build();
        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(baseUri, config);
        server.start();
    }
}

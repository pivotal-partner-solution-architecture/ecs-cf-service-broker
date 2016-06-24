package com.emc.ecs;

import com.emc.ecs.management.sdk.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.emc.ecs.cloudfoundry.broker.config.CatalogConfigTest;
import com.emc.ecs.cloudfoundry.broker.model.ServiceDefinitionProxyTest;
import com.emc.ecs.cloudfoundry.broker.repository.ServiceInstanceBindingRepositoryTest;
import com.emc.ecs.cloudfoundry.broker.repository.ServiceInstanceRepositoryTest;
import com.emc.ecs.cloudfoundry.broker.service.EcsServiceInstanceBindingServiceTest;
import com.emc.ecs.cloudfoundry.broker.service.EcsServiceInstanceServiceTest;
import com.emc.ecs.cloudfoundry.broker.service.EcsServiceTest;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.junit.WireMockRule;

@RunWith(Suite.class)
@SuiteClasses({
    BaseUrlActionTest.class,
    BucketAclActionTest.class,
    BucketActionTest.class,
    BucketQuotaActionTest.class,
    BucketRetentionActionTest.class,
    ConnectionTest.class,
    NamespaceActionTest.class,
    NamespaceQuotaActionTest.class,
    NamespaceRetentionActionTest.class,
    ObjectUserActionTest.class,
    ObjectUserSecretActionTest.class,
    ReplicationGroupActionTest.class,
    EcsServiceTest.class,
    CatalogConfigTest.class,
    ServiceDefinitionProxyTest.class,
    ServiceInstanceBindingRepositoryTest.class,
    ServiceInstanceRepositoryTest.class,
    EcsServiceInstanceBindingServiceTest.class,
    EcsServiceInstanceServiceTest.class
    })
public class TestSuite {

    @Rule
    public static WireMockRule wireMockRule = new WireMockRule(
	    WireMockConfiguration.wireMockConfig().port(9020).httpsPort(4443));

    @BeforeClass
    public static void setUp() {
	wireMockRule.start();
    }

    @AfterClass
    public static void tearDown() {
	wireMockRule.shutdownServer();
    }
}
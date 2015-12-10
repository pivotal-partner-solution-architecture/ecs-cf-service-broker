# EMC Elastic Cloud Storage (ECS) Cloud Foundry Service Broker

## Description

This service broker enables Cloud Foundry applications to create, delete and
modify ECS object storage buckets; and bind multiple applications to the same
bucket.

## Features

This service broker supports a number of Cloud Foundry and ECS features
including:
 * Create and Delete Object Storage Buckets
 * Bind one or more Cloud Foundry applications to a bucket, with unique credentials for each application
 * Support quota enforced plans for buckets to limit the amount of capacity
 * Change plans of an existing bucket
 * Browse Cloud Foundry instance and binding metadata through an internal bucket
 * Specify an ECS namespace and replication group for provisioning
 * Provide a string prefix for bucket and user names
 * Support a self-signed SSL certificate for the ECS management API

## Build

To build, make sure that you have a Java 8 runtime environment, and use Gradle.

```
./gradlew build
```

## Configuration

### ECS Configuration

The service broker supports a number of configuration parameters that are available as environment variables:

| Parameter        | Default Value  | Required | Description                                    |
| ---------------- |:--------------:| -------- | ---------------------------------------------- |
| endpoint         | -              | true     | IP address of management API endpoint          |
| replicationGroup | -              | true     | ID (not name) of replication group             |
| namespace        | -              | true     | ECS Namespace name                             |
| repositoryUser   | user           | false    | Username to authenticate to intenal bucket     |
| managementPort   | 4443           | false    | management port of the ECS API service         |
| username         | root           | false    | Username to authenticate to ECS management API |
| password         | ChangeMe       | false    | Password to authenticate to ECS management API |
| repositoryBucket | repository     | false    | Internal bucket for metadata storage           |
| prefix           | ecs-cf-broker- | false    | Prefix to prepend to ECS buckets and users     |
| brokerApiVersion | 2.8            | false    | Version of the CF broker API to advertise      |

If running within Eclipse, you can set the environment variables using "Run Configuration" and "Environment" tabs.

### Self-signed certificates

To load a self-signed certificate for an ECS system, just provide a PEM formatted certificate file named `localhost.pem` into the `src/main/resources` directory.

### Broker Catalog and Plan Configuration

The service broker catalog can be configured through the [CatalogConfig](https://github.com/spiegela/ecs-cf-service-broker/blob/master/src/main/java/com/emc/ecs/serviceBroker/config/CatalogConfig.java) class.  This will be replaced with a YAML based configuration in the future, so that it can be generated dynamically as part of PCF or another build tool.

## Deploying your broker

Follow the [documentation](http://docs.cloudfoundry.org/services/managing-service-brokers.html) to register the broker to Cloud Foundry.

## Testing

To execute unit tests, a running ECS system must be available to the broker.  Once this is done configure the [EcsActionTest](https://github.com/spiegela/ecs-cf-service-broker/blob/master/src/test/java/com/emc/ecs/common/EcsActionTest.java) class with your parameters.  Then use Gradle to run the test suite:

```
./gradlew test
```

## Todos
 * Add FS-enabled and CAS bucket types to plans
 * Add parameters to bindings for user ACLs (read-only, read-write, etc.)
 * Add support for dynamically generated plans as done the [MySQL service broker](https://github.com/cloudfoundry/cf-mysql-broker)
 * Delete ACLs from bucket (in addition to the user) when removing bindings
 * Automatic support for multiple Cloud Foundry instances as [show here](http://docs.cloudfoundry.org/services/supporting-multiple-cf-instances.html)
 * Support syslog drain URL for logging
 * Support TLS for internal S3 bucket (requires ECS 2.1.1)  
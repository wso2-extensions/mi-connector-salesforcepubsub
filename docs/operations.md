
# Working with pubsub Connector

## Overview

The **pubsub** connector allows you to perform a set of predefined operations. Each operation corresponds to a remote procedure call (RPC) that interacts with the backend service.

Below is a list of available operations and their descriptions:

| Operation Name   |
|------------------|
| GetSchema |
| Publish |
| GetTopic |

---

## Operation Details

This section provides detailed information about each operation, including the expected input and output parameters.


### GetSchema RPC Operation



**Input Parameters**

The following table lists the input parameters required for this operation:

| Parameter Name   | Proto Type   |
|------------------|--------------|
| schema_id | TYPE_STRING |

**Output Parameters**

The following table lists the output parameters returned by this operation:

| Parameter Name   | Proto Type   |
|------------------|--------------|
| rpc_id | TYPE_STRING |
| schema_id | TYPE_STRING |
| schema_json | TYPE_STRING |

---

### Publish RPC Operation



**Input Parameters**

The following table lists the input parameters required for this operation:

| Parameter Name   | Proto Type   |
|------------------|--------------|
| topic_name | TYPE_STRING |
| events | TYPE_MESSAGE |
| auth_refresh | TYPE_STRING |

**Output Parameters**

The following table lists the output parameters returned by this operation:

| Parameter Name   | Proto Type   |
|------------------|--------------|
| rpc_id | TYPE_STRING |
| schema_id | TYPE_STRING |
| results | TYPE_MESSAGE |

---

### GetTopic RPC Operation



**Input Parameters**

The following table lists the input parameters required for this operation:

| Parameter Name   | Proto Type   |
|------------------|--------------|
| topic_name | TYPE_STRING |

**Output Parameters**

The following table lists the output parameters returned by this operation:

| Parameter Name   | Proto Type   |
|------------------|--------------|
| topic_name | TYPE_STRING |
| schema_id | TYPE_STRING |
| tenant_guid | TYPE_STRING |
| can_subscribe | TYPE_BOOL |
| can_publish | TYPE_BOOL |
| rpc_id | TYPE_STRING |

---

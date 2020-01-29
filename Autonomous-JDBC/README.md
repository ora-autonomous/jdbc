# ATP JDBC Examples
##### by Marcel Amende, Oracle

## Setup

### Wallet

Download and unpack your DB's wallet to a directory of your choice and point an environment variable TNS_ADMIN to it:

export TNS\_ADMIN=&lt;WALLET_DIR>

Or use Java VM arguments to point to the wallet:

-Doracle.net.wallet\_location=&lt;WALLET_DIR> -Doracle.jdbc.fanEnabled=false


### JDBC Driver / Libraries

Add the following libraries to your classpath:

---
-ojdbc.policy

-ojdbc10.jar

-ons.jar

-oraclepki.jar

-orai18n.jar

-osdt_cert.jar

-osdt_core.jar

-simplefan.jar

-ucp.jar

-xdb.jar

---
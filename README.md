### Start hub using below command:

```cmd
java -jar selenium-server-standalone-3.141.59.jar -role hub -hubConfig grid_hub_config.json
```

### Start node using below command:

````cmd
java -Dwebdriver.chrome.driver="drivers/chromedriver.exe" -Dwebdriver.gecko.driver="drivers/geckodriver.exe" -jar selenium-server-standalone-3.141.59.jar -role node -nodeConfig node_config.json
````
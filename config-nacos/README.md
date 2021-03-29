## nacos配置中心的使用
可以将application.yml完全移到nacos配置中心中
通过bootstrap.yml中配置nacos配置中心的地址拉取配置

注意数据的命名规则


### 配置热更新
1，配置的数据必须放到一个单独的bean中
2，这个bean必须被@Configuration和@RefreshScope注解


### 环境管理
只需要再配置一个配置数据
nacos-config-dev.yml
nacos-config-prod.yml
在bootstrap.yml中改一下profiles.active重新打包即可

### 公共数据
同环境的配置文件中存在很多公用的固定的配置项
对于这种公共的数据，我们可以将其抽取出来，放到nacos-config.yml中
服务启动时，这个不带环境的data一定会被加载

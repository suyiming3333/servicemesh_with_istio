## 一、技术组件

-[x] 配置中心：通过k8s ConfigMap实现配置管理(ps:需要注意应用配置刷新的问题)
-[x] 服务注册、发现：通过k8s Service实现服务的注册与发现
-[x] 服务调用：通过OpenFeign实现短服务名的进行调用
-[x] 负载均衡：当未注入sidecar proxy边车代理时，通过k8s coreDns实现；注入边车代理后，通过Envoy实现
-[x] 服务网关：通过Istio Ingress Gateway实现
-[ ] 服务熔断：Envoy实现熔断(Openfeign可能需要处理熔断的callback)
-[x] 认证授权：基于istio的安全机制实现授权的安全限制;依赖oauth2.0 认证服务返回统一的token.(ps:虽然istio提供了安全访问机制，但个人觉得，由于业务也可能获取到用户的认证信息，仍需要对服务鉴权进行拦截，以获取登录的状态及相关信息)
 
## 二、服务模块

- auth-service:
   - 基于oauth 2.0 密码模式实现用户登录认证
   - 基于jks实现jwt加密颁发token
- a-service:
   -[x] 通过configmap读取参数配置
   -[x] 通过openfeign实现对b服务的调用
   -[x] 通过spring-security的@ResourceServer实现对资源访问的保护
   -[ ] feign调用熔断的callback
- b-service:
   -[x] 通过spring-security的@ResourceServer实现对资源访问的保护

- msistio-kubernetes-manifests:k8s配置文件
  - msistio-namespaces.yaml:声明为msistio空间所有应用注入istio
  - a-service-configmap:为a-service配置参数文件
  
- msistio-istio-manifests:istio配置文件
   - msistio-gateway.yaml：istio网关配置文件。声明了对各个服务访问的代理。(ps:网关需要绑定虚拟服务)
   - msistio-destination*.yaml:目标规则。需要先声明目标规则才能在绑定网关的虚拟服务上进行正确的路由
   - msistio-destination-b-svc-multi.yaml：声明了b-service多个版本的目标规则
   - msistio-b-VirtualService-5050v1v2.yaml：通过配合虚拟服务，给b-service设置相对应的流量规则(该配置流量规则而为5050流入到v1和v2)
   - msistio-authentication-policy.yaml:istio安全认证配置。
   - allow-role/deny-all:istio安全授权配置
To test this example.
Start the applications in the following order
1. service-discovery
2. zuul-api-gateway
3. employee-management
4. employee-address

Test URL
http://localhost:8099/employee/emp?name=Route%20Filterrrrrrrrrrrrrrrrrrrrr


Routing in an integral part of a microservice architecture. For example, / may be mapped to your web application, /api/users is mapped to the user service and /api/shop is mapped to the shop service. Zuul is a JVM based router and server side load balancer by Netflix.

Netflix uses Zuul for the following:

    Authentication
    Insights
    Stress Testing
    Canary Testing
    Dynamic Routing
    Service Migration
    Load Shedding
    Security
    Static Response handling
    Active/Active traffic management

Zuul’s rule engine allows rules and filters to be written in essentially any JVM language, with built in support for Java and Groovy.

Strangulation Patterns and Local Forwards
A common pattern when migrating an existing application or API is to "strangle" old endpoints, slowly replacing them with different implementations. The Zuul proxy is a useful tool for this because you can use it to handle all traffic from clients of the old endpoints, but redirect some of the requests to new ones.

Example configuration:

application.yml. 

 zuul:
  routes:
    first:
      path: /first/**
      url: https://first.example.com
    second:
      path: /second/**
      url: forward:/second
    third:
      path: /third/**
      url: forward:/3rd
    legacy:
      path: /**
      url: https://legacy.example.com

In this example we are strangling the "legacy" app which is mapped to all requests that do not match one of the other patterns. Paths in /first/** have been extracted into a new service with an external URL. And paths in /second/** are forwarded so they can be handled locally, e.g. with a normal Spring @RequestMapping. Paths in /third/** are also forwarded, but with a different prefix (i.e. /third/foo is forwarded to /3rd/foo).

Zuul RequestContext
To pass information between filters, Zuul uses a RequestContext. Its data is held in a ThreadLocal specific to each request. Information about where to route requests, errors and the actual HttpServletRequest and HttpServletResponse are stored there. The RequestContext extends ConcurrentHashMap, so anything can be stored in the context. FilterConstants contains the keys that are used by the filters installed by Spring Cloud Netflix (more on these later).

@EnableZuulProxy vs. @EnableZuulServer

Spring Cloud Netflix installs a number of filters based on which annotation was used to enable Zuul. @EnableZuulProxy is a superset of @EnableZuulServer. In other words, @EnableZuulProxy contains all filters installed by @EnableZuulServer. The additional filters in the "proxy" enable routing functionality. If you want a "blank" Zuul, you should use @EnableZuulServer.

Custom Zuul Filter examples
------------------------------------
How to Write a Pre Filter
Pre filters are used to set up data in the RequestContext for use in filters downstream. The main use case is to set information required for route filters.

How to Write a Route Filter
Route filters are run after pre filters and are used to make requests to other services. Much of the work here is to translate request and response data to and from the client required model.

How to Write a Post Filter
Post filters typically manipulate the response. In the filter below, we add a random UUID as the X-Foo header. Other manipulations, such as transforming the response body, are much more complex and compute-intensive.

How Zuul Errors Work
If an exception is thrown during any portion of the Zuul filter lifecycle, the error filters are executed. The SendErrorFilter is only run if RequestContext.getThrowable() is not null. It then sets specific javax.servlet.error.* attributes in the request and forwards the request to the Spring Boot error page.

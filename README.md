## XModulable

### Latest Version

SDK|XModulable-api|XModulable-compiler|XModulable-annotation
:---|:---:|:---:|:---:
最新版本|[ ![Download](https://api.bintray.com/packages/xpleemoon/maven/XModulable-api/images/download.svg) ](https://bintray.com/xpleemoon/maven/XModulable-api/_latestVersion)|[ ![Download](https://api.bintray.com/packages/xpleemoon/maven/XModulable-compiler/images/download.svg) ](https://bintray.com/xpleemoon/maven/XModulable-compiler/_latestVersion)|[ ![Download](https://api.bintray.com/packages/xpleemoon/maven/XModulable-annotation/images/download.svg) ](https://bintray.com/xpleemoon/maven/XModulable-annotation/_latestVersion)

---

### 概念

组件/模块化的套路通常是：

  - 组件/模块之间互不依赖、相互隔离
  - app壳将组件注册到路由层
  - 上层通过路由层查找组件/模块，通过组件/模块暴露的服务实现通信交互

  - 业务组件/模块独立运行，只需要更改module.gradle对应的业务组件/模块`isStandalone`为true即可

### 使用方法

#### 1. 添加依赖配置

  ```
  android {
      defaultConfig {
      	...
      	javaCompileOptions {
      	    annotationProcessorOptions {
      		      arguments = [ XModule : project.getName() ]
      	    }
      	}
      }
  }

  dependencies {
     annotationProcessor project(':drouter-compiler')
     api project(':drouter_common')
      ...
  }
  ```

#### 2. 实现组件

  ```
  @Route(path = PathConstants.SRVIDEO_PATH_EXTENDER_VIEW)
  public class xxxActivity extends BaseCommonActivity{

  }
  ```

#### 3. 初始化sdk

  ```
   ZRouter.init(this);
  ```

#### 4. 获取组件

  组件获取有两种方式：依赖注入和手动查询获取。

  1. 获取

    ```
    public class TestActivity extends BaseActivity {
        ISRInitializebusinessService isrInitializebusinessService=null;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
           isrInitializebusinessService = (ISRInitializebusinessService) ZRouter.getsInstance().build(PathConstants.subInitialize.INITIALIZE_BUSINESS_PATH_SERVICE).navigation();
           isrInitializebusinessService.init(context);
        }
    }
    ```





### 组件化/模块化

- 组件：基于可重用的目的，对功能进行封装，一个功能就是一个组件，例如网络、IO、图片加载等等这些都是组件
- 模块：基于业务独立的目的，对一系列有内聚性的业务进行整理，将其与其他业务进行切割、拆分，从主工程或原所在位置抽离为一个相互独立的部分

由于模块是**独立**、**解耦**、**可重用**的特性，在实施组件化/模块化的过程中，我们需要解决三个主要问题：
    1. 模块通信——因为业务模块是相互隔离的，它们完全不知道也无法感知其他业务模块是否存在，所以需要一种尽最大可能的隔离、耦合度相对最低、代价相对最小的可行方案来实现通信
    2. 模块独立运行——在后续迭代维护的过程中，各个业务线的人员能够职责更加清晰
    3. 模块灵活组合运行——能够适应产品需求，灵活拆分组合打包上线


解决抛出的三个问题之前，先过下[XModulable]的架构图，上图中的module对应层级：

  - app壳层——依赖业务层，可灵活组合业务层模块
  - 业务层——im、live和main，面向common层实现业务层服务接口，向common注册和查询业务模块
  - common层——依赖基础组件层；承接业务层，暴露业务层服务接口，同时为业务层提供模块路由服务
  - basic层——basicRes和basicLib
      - basicRes——包含通用资源和各UI组件
      - basicLib——包含网路组件、图片加载组件、各种工具等功能组件


#### 1. 模块通信

模块化的通信（UI跳转和数据传递），需要抓住几个基本点：**隔离**、**解耦**、**代价小**（易维护）、**传递复杂数据**（`Fragment`、`View`、`File`……）。实现独立互不依赖模块的通信，很容易能够想到以下几种方式：

  - **Android传统通信**（比如aidl、广播、自定义url……）
    - 无法避免高度耦合、以及随着项目扩张导致难以维护的问题
    - 还有另外一关键个问题就是只能进行一些非常简单的数据传递，像`Fragment`、`View`、`File`……这些数据（或者叫对象也行），完全无法通信传递，但是这些数据在实际的app中恰恰是组成一个app的关键节点。比如说app的主站中有一个`MainActivity`，它是一个`ViewPager`+`TabLayout`的结构，其中的每一个页面都是来自于不同模块的Fragment，这个时候我们的通信就完全无法满足了。
  - **第三方通信**（比如[EventBus](https://github.com/greenrobot/EventBus)、[RxBus](https://github.com/AndroidKnife/RxBus)……）
    - 容易陷入茫茫的event通知和接收中，增加调试和维护的成本
    - 能够传递一些复杂的数据，通过event事件来携带其它数据对象，但是代码耦合性相应的会增加
  - 第三方路由库（比如[ARouter](https://github.com/alibaba/ARouter)、OkDeepLink、[DeepLinkDispatch](htt ps://github.com/airbnb/DeepLinkDispatch)……）基本都能够实现**隔离**、**解耦**、**代价小**（易维护）。至于数据传递的话默认只支持一些简单数据，但是我们可以结合**面向接口编程**，公共层暴露接口，业务层面向公共层的接口去实现对应的接口方法（UI跳转、数据读写……），最后当业务层使用的时候只需要通过路由到接口，就可以完成复杂数据的通信。以[ARouter](https://github.com/alibaba/ARouter)为例，可以在common层暴露业务模块的服务接口（`IProvider`，[ARouter](https://github.com/alibaba/ARouter)提供的服务接口，只要实现了该接口的自定义服务，[ARouter](https://github.com/alibaba/ARouter)都能进行路由操作），然后交由对应的业务模块去实现common层对应的服务接口，最后在业务模块中使用[ARouter](https://github.com/alibaba/ARouter)进行路由其他业务模块暴露的服务接口来实现。

从上面的分析来看，路由+面向接口编程是实现组件化/模块化的不二之选，但是这里又有一个问题——假设哪天抽风想要更换路由库或者可能某种特殊需求不同的业务模块使用了不容的路由库，那怎么办呢？没关系，我们这时候需要对路由库做一层封装，使业务模块内的路由都相互隔离，也就是一个业务模块内部的路由操作对其他业务模块来说是一个黑箱操作。我的封装思路是这样的：加一个`XModule`（可以把它想象成一个容器）的概念，在common层暴露服务接口的同时暴露`XModule`（它的具体实现也是有对应的业务模块决定的），每一业务模块都对应一个`XModule`，用于承载common层暴露的服务接口，业务模块之间的通信第一步必须先获取`XModule`，然后再通过这个容器去拿到服务。

综上所述，最终的组件化/模块化采用的是**封装+路由+面向接口编程**。以live业务模块为例，从源码的角度看下它们是实现这套思路的。在common层把live业务模块想要暴露给其他业务模块的服务`LiveService`进行了暴露，同时在common层暴露了一个`LiveModule`（live业务模块的服务容器，承载了`LiveService `）,l，live业务模块面向common层对应的接口进行实现（`LiveModuleImpl`和`LiveServiceImpl`）。这样的话，上层业务就可以通过[XModulable SDK]获取到`LiveModule`，然后通过`LiveModule`承载的服务进行调用。

```
  // common层live暴露的服务接口（LiveService）


  public interface LiveService extends BaseService {
      Fragment createLiveEntranceFragment();

      void startLive();
  }

  // 业务模块层——live针对common层暴露的实现LiveModuleImpl和LiveServiceImpl



  @Route(path = PathConstants.PATH_SERVICE_LIVE)
  public class LiveServiceImpl implements LiveService {
      @Override
      public void init(Context context) {

      }

      @Override
      public Fragment createLiveEntranceFragment() {
          return new LiveEntranceFragment();
      }

      @Override
      public void startLive() {
          ARouter.getInstance().build(PathConstants.PATH_VIEW_LIVE).navigation();
      }
  }
```

#### 2. 模块独立运行

业务模块在Android Studio中其实就是一个module，从gradle的角度来说，module不是以application plugin方式运行，就是以library plugin方式运行，所以为了业务模块也能够独立运行，就需要控制gradle能够在application plugin和library plugin两种形式下切换，同时还要提供单独运行时的源码。

首先在项目的build.gradle中创建业务模块配置，`isStandAlone`表示业务模块是否独立运行：

```
  ext {
      applicationId = "com.xpleemoon.sample.modulable"

      // 通过更改isStandalone的值实现业务模块是否独立运行，以及app壳工程对组件的灵活依赖
      modules = [
              main: [
                      isStandalone : false,
                      applicationId: "${applicationId}.main",
              ],
              im  : [
                      isStandalone : false,
                      applicationId: "${applicationId}.im",
              ],
              live: [
                      isStandalone : true,
                      applicationId: "${applicationId}.live"
              ],
      ]
  }
```

然后设置对应业务模块的build.gradle：

```
  def currentModule = rootProject.modules.live
  // isStandalone的值决定了当前业务模块是否独立运行
  if (currentModule.isStandalone) {
      apply plugin: 'com.android.application'
  } else {
      apply plugin: 'com.android.library'
  }

  android {
   省略...
      defaultConfig {
          if (currentModule.isStandalone) {
              // 当前组件独立运行，需要设置applicationId
              applicationId currentModule.applicationId
          }
          省略...

          def moduleName = project.getName()
          // 业务组件资源前缀，避免冲突
          resourcePrefix "${moduleName}_"

          javaCompileOptions {
              annotationProcessorOptions {
                  arguments = [
                          // ARouter处理器所需参数
                          moduleName   : moduleName,
                          // XModulable处理器所需参数
                          XModule: moduleName
                  ]
              }
          }

      }
  省略...
      sourceSets {
          main {
              // 单独运行所需要配置的源码文件
              if (currentModule.isStandalone) {
                  manifest.srcFile 'src/standalone/AndroidManifest.xml'
                  java.srcDirs = ['src/main/java/', 'src/standalone/java/']
                  res.srcDirs = ['src/main/res', 'src/standalone/res']
              }
          }
      }
  }
  省略...
```

最后，在业务模块中编写build.gradle中`sourceSets`声明单独运行所需要的额外源码文件，比如`Application`、`SplashActivity`和`Manifest`。

完成上面的过程后，就可以选择对应的业务模块live运行

![业务模块独立运行]()

#### 3. 模块灵活组合运行

模块的灵活组合，其实也非常简单，只需要更改业务模块配置在项目build.gradle的`isStandalone`值，然后在app壳的build.gradle中通过业务模块的`isStandalone`来决定是否依赖就行，关键代码如下：

```
  dependencies {
  省略...
      def modules = rootProject.modules
      def isMainStandalone = modules.main.isStandalone
      def isIMStandalone = modules.im.isStandalone
      def isLiveStandalone = modules.live.isStandalone
      // 判断业务组件是否独立运行，实现业务组件的灵活依赖
      if (isMainStandalone && isIMStandalone && isLiveStandalone) {
          api project(':common')
      } else {
          if (!isMainStandalone) {
              implementation project(':main')
          }
          if (!isIMStandalone) {
              implementation project(':im')
          }
          if (!isLiveStandalone) {
              implementation project(':live')
          }
      }
  }
```
#### 跳转参数传递
```
        ZRouter.getInstance().build(RouterPath.PAGE_TEST1)
        //第一个参数为key，第二个参数为值
        .withLong("longKey", 0x555555L)
        .withString("stringKey", "66666")
        .navigation()
```
###直接传递Bundle
```

    Bundle params = new Bundle();
    ZRouter.getInstance()
    .build("/home/main")
    .with(params)
    .navigation();

```
###参数传递集锦
```
        TestParcelable testParcelable = new TestParcelable("jack", 666);
                        TestObj testObj = new TestObj("Rose", 777);
                        List objList = new ArrayList();
                        objList.add(testObj);

                        Map<String, List> map = new HashMap();
                        map.put("testMap", objList);

                        ZRouter.getInstance().build("/test/activity1")
                                .withString("name", "老王")
                                .withInt("age", 18)
                                .withBoolean("boy", true)
                                .withLong("high", 180)
                                .withString("url", "https://a.b.c")
                                .withParcelable("pac", testParcelable)
                                .withObject("obj", testObj)
                                .withObject("objList", objList)
                                .withObject("map", map)
                                .navigation();
```
###参数传递集锦
```
ZRouter.getInstance().build("/test/1")
            .withLong("key1", 666L)
            .withString("key3", "888")
            .withSerializable("key4", new Test("Jack", "Rose"))
            .navigation();
```
###拦截器
```
 ZRouter.getInstance()
                .build(PathConstants.PATH_VIEW_LOGIN)
                .withString("key","startLoginActivity跳转来的")
                .navigation(context, new NavCallback() {
                    @Override
                    public void onArrival(Postcard postcard) {
                        System.out.println("LoginService.....onArrival: "+postcard.getPath());
                    }

                    @Override
                    public void onInterrupt(Postcard postcard) {
                        System.out.println("LoginService.....onInterrupt: "+postcard.getPath());
                        super.onInterrupt(postcard);
                    }

                });


```